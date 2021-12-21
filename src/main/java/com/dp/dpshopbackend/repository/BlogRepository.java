package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    Optional<Blog> findBlogByTitle(String title);

    List<Blog> findTop5ByOrderByCreateDateDesc();

    List<Blog> findByOrderByIdDesc();

}
