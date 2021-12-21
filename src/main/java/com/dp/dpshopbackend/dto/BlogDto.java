package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Blog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {

    private Long id;

    private String title;

    private String image;

    private String description;

    private Date createDate;

    private Date lastUpDated;

    public static BlogDto fromEntityToDto(Blog blog) {
        if (blog == null) {
            return null;
        }

        return BlogDto.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .image(blog.getImage())
                .description(blog.getDescription())
                .createDate(blog.getCreateDate())
                .lastUpDated(blog.getLastUpDated())
                .build();
    }

    public static Blog fromDtoToEntity(BlogDto blogDto) {
        if (blogDto == null) {
            return null;
        }

        Blog blog = new Blog();
        blog.setTitle(blogDto.title);
        blog.setImage(blogDto.getImage());
        blog.setDescription(blogDto.getDescription());
        blog.setCreateDate(blogDto.getCreateDate());
        blog.setLastUpDated(blogDto.getLastUpDated());

        return blog;
    }
}
