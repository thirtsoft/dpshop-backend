package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.BlogDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BlogService {

    BlogDto save(BlogDto blogDto);

    BlogDto saveBlogWithFile(String BlogDto, MultipartFile photoBlog) throws IOException;

    BlogDto update(Long id, BlogDto blogDto);

    BlogDto findById(Long id);

    BlogDto findByTitle(String title);

    List<BlogDto> findAll();

    List<BlogDto> findTop5ByOrderByCreateDateDesc();

    List<BlogDto> findByOrderByIdDesc();

    void delete(Long id);
}
