package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.dto.CategoryDto;
import com.dp.dpshopbackend.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Rollback(false)
    public void testCreateCategory() {
        CategoryDto categoryDto = new CategoryDto(null, "sac", "sac a mai");
        CategoryDto categoryDtoResult = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto)
                )
        );

        assertNotNull(categoryDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateCategory() {
        CategoryDto categoryDto = new CategoryDto(null, "sac", "PAPIER RAM");
        CategoryDto categoryDtoResult = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto)
                )
        );

        String categoryDesignation = "Bureau";
        CategoryDto categorieUpdateDto = new CategoryDto(null, "Bureau", categoryDesignation);

        categorieUpdateDto.setId((long) 1);
        CategoryDto.fromEntityToDto(categoryRepository.save(CategoryDto.fromDtoToEntity(categorieUpdateDto)));

        assertThat(categorieUpdateDto.getDesignation()).isEqualTo(categoryDesignation);

    }

    @Test
    public void testFindById() {

        CategoryDto categoryDto = new CategoryDto(null, "sac", "sac a mai");
        CategoryDto categoryDtoResult = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto)
                )
        );

        Long cat_id = (long) 1;
        Optional<Category> categorie = categoryRepository.findById(categoryDtoResult.getId());

        assertNotNull(categorie);
    }

    @Test
    public void testFindByDesignation() {
        CategoryDto categoryDto = new CategoryDto(null, "Robe", "RobeMariage");
        CategoryDto categoryDtoResult = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto)
                )
        );
        String catDesignation = "RobeMariage";
        assertThat(categoryDtoResult.getDesignation()).isEqualTo(catDesignation);
    }

    @Test
    public void testFindAll() {
        CategoryDto categoryDto = new CategoryDto(null, "Robe", "sac a mai");
        CategoryDto categoryDtoResult = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto)
                )
        );
        CategoryDto categoryDtonull = new CategoryDto(null, "Panthalon", "Panthalon homme");
        CategoryDto categoryDtoResultnull = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDtonull)
                )
        );

        CategoryDto categoryDto2 = new CategoryDto(null, "Chemise", "Chemise Femme");
        CategoryDto categoryDtoResult2 = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto2)
                )
        );

        List<?> categories = categoryRepository.findAll();

        assertThat(categories).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {

        CategoryDto categoryDto = new CategoryDto(null, "Chemise", "Chemise Femme");
        CategoryDto categoryDtoResult2 = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto)
                )
        );

        Optional<Category> categorie = categoryRepository.findById(categoryDtoResult2.getId());

        Long id = (long) 1;

        boolean isExistBeforeDelete = categoryRepository.findById(categoryDtoResult2.getId()).isPresent();

        categoryRepository.deleteById(categoryDtoResult2.getId());

        boolean notExistAfterDelete = categoryRepository.findById(categoryDtoResult2.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }
}
