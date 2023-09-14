package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.BlogApi;
import com.dp.dpshopbackend.dto.BlogDto;
import com.dp.dpshopbackend.services.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(origins = "https://soulbusinesse.com")
@RestController
public class BlogController implements BlogApi {

    private final BlogService blogService;
    private final String blogPhotosDir = "C://Users//Folio9470m//shopmania//blogphotos//";
    @Autowired
    ServletContext context;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @Override
    public ResponseEntity<BlogDto> save(BlogDto blogDto) {
        return ResponseEntity.ok(blogService.save(blogDto));
    }

    @Override
    public ResponseEntity<BlogDto> saveBlogWithFile(String blogDto, MultipartFile photoBlog) throws IOException {
        BlogDto blogDtoResult = new ObjectMapper().readValue(blogDto, BlogDto.class);
        if (photoBlog != null && !photoBlog.isEmpty()) {
            blogDtoResult.setImage(photoBlog.getOriginalFilename());
            photoBlog.transferTo(new File(blogPhotosDir + photoBlog.getOriginalFilename()));
        }

        return ResponseEntity.ok(blogService.save(blogDtoResult));
    }

    @Override
    public ResponseEntity<BlogDto> saveBlogWithFileInFolder(String blog, MultipartFile photoBlog) throws IOException {
        BlogDto blogDto = new ObjectMapper().readValue(blog, BlogDto.class);
        if (photoBlog != null && !photoBlog.isEmpty()) {

            String filename = photoBlog.getOriginalFilename();
            String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
            File serverFile = new File(context.getRealPath("/PhotoBlogs/" + File.separator + newFileName));
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, photoBlog.getBytes());

            blogDto.setImage(filename);
        }

        return ResponseEntity.ok(blogService.save(blogDto));
    }

    @Override
    public ResponseEntity<BlogDto> update(Long id, BlogDto blogDto) {
        blogDto.setId(id);
        return ResponseEntity.ok(blogService.save(blogDto));
    }

    @Override
    public ResponseEntity<BlogDto> findById(Long id) {
        return ResponseEntity.ok(blogService.findById(id));
    }

    @Override
    public ResponseEntity<BlogDto> findByTitle(String title) {
        return ResponseEntity.ok(blogService.findByTitle(title));
    }

    @Override
    public ResponseEntity<List<BlogDto>> findAll() {
        List<BlogDto> blogDtoList = blogService.findAll();
        return new ResponseEntity<>(blogDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BlogDto>> getTop5ByOrderByCreateDateDesc() {
        List<BlogDto> blogDtoList = blogService.findTop5ByOrderByCreateDateDesc();
        return new ResponseEntity<>(blogDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BlogDto>> getAllBlogsOrderByIdDesc() {
        List<BlogDto> blogDtoList = blogService.findByOrderByIdDesc();
        return new ResponseEntity<>(blogDtoList, HttpStatus.OK);
    }

    @Override
    public byte[] getPhotoBlogle(Long id) throws Exception {

        BlogDto blogDto = blogService.findById(id);

        System.out.println("Article DTO -- " + blogDto);
        System.out.println("Article DTO Designation -- " + blogDto.getDescription());
        System.out.println("Article DTO Photo -- " + blogDto.getImage());

        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/shopmania/blogphotos/" + blogDto.getImage()));

    }

    @Override
    public byte[] getPhotoBlogleInContext(Long id) throws Exception {
        BlogDto blogDto = blogService.findById(id);
        return Files.readAllBytes(Paths.get(context.getRealPath("/PhotoBlogs/") + blogDto.getImage()));
    }

    @Override
    public void uploadPhotoBlog(MultipartFile file, Long id) throws IOException {
        BlogDto blogDto = blogService.findById(id);
        blogDto.setImage(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home") + "/shopmania/blogphotos/" + blogDto.getImage()), file.getBytes());

        blogService.save(blogDto);
    }

    @Override
    public void uploadPhotoBlogInContext(MultipartFile file, Long id) throws IOException {
        BlogDto blogDto = blogService.findById(id);
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/PhotoBlogs/" + File.separator + newFileName));

        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

            blogDto.setImage(filename);

            blogService.save(blogDto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        blogService.delete(id);
    }
}
