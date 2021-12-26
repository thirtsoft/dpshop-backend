package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.BlogDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Blog;
import com.dp.dpshopbackend.repository.BlogRepository;
import com.dp.dpshopbackend.services.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogDto save(BlogDto blogDto) {
        return BlogDto.fromEntityToDto(
                blogRepository.save(
                        BlogDto.fromDtoToEntity(blogDto)
                )
        );
    }

    @Override
    public BlogDto saveBlogWithFile(String blogDto, MultipartFile photoBlog) throws IOException {
        BlogDto blogDtoSearch = new ObjectMapper().readValue(blogDto, BlogDto.class);
        System.out.println(blogDtoSearch);

        blogDtoSearch.setImage(photoBlog.getOriginalFilename());

        return BlogDto.fromEntityToDto(
                blogRepository.save(
                        BlogDto.fromDtoToEntity(blogDtoSearch)
                )
        );
    }

    @Override
    public BlogDto update(Long id, BlogDto blogDto) {
        if (!blogRepository.existsById(id)) {
            throw new ResourceNotFoundException("Blog not found");
        }

        Optional<Blog> optionalBlog = blogRepository.findById(id);

        if (!optionalBlog.isPresent()) {
            throw new ResourceNotFoundException("Blog not found");
        }

        BlogDto blogDtoResult = BlogDto.fromEntityToDto(optionalBlog.get());
        blogDtoResult.setTitle(blogDto.getTitle());
        blogDtoResult.setImage(blogDto.getImage());
        blogDtoResult.setDescription(blogDto.getDescription());
        blogDtoResult.setCreateDate(blogDto.getCreateDate());
        blogDtoResult.setLastUpDated(blogDto.getLastUpDated());


        return BlogDto.fromEntityToDto(
                blogRepository.save(
                        BlogDto.fromDtoToEntity(blogDtoResult)
                )
        );
    }

    @Override
    public BlogDto findById(Long id) {
        if (id == null) {
            log.error("Article Id is null");
            return null;
        }
        Optional<Blog> optionalBlog = blogRepository.findById(id);

        return Optional.of(BlogDto.fromEntityToDto(optionalBlog.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Blog avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public BlogDto findByTitle(String title) {
        if (!StringUtils.hasLength(title)) {
            log.error("Blog Title is null");
        }

        Optional<Blog> optionalBlog = blogRepository.findBlogByTitle(title);

        return Optional.of(BlogDto.fromEntityToDto(optionalBlog.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Blog avec l'Id = " + title + "n'a été trouvé")
        );
    }

    @Override
    public List<BlogDto> findAll() {
        return blogRepository.findAll().stream()
                .map(BlogDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BlogDto> findTop5ByOrderByCreateDateDesc() {
        return blogRepository.findTop5ByOrderByCreateDate().stream()
                .map(BlogDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BlogDto> findByOrderByIdDesc() {
        return blogRepository.findByOrderByIdDesc().stream()
                .map(BlogDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Blog Id is null");
            return;
        }
        blogRepository.deleteById(id);
    }
}
