package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.dto.CategorieDto;
import com.dp.dpshopbackend.models.Categorie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {

    @Autowired
    private CategorieRepository categorieRepository;

    @Test
    @Rollback(false)
    public void testCreateCategory() {
        CategorieDto categorieDto = new CategorieDto(1,"sac", "sac a mai");
        CategorieDto categoryDtoResult = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto)
                )
        );

        assertNotNull(categoryDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateCategory() {
        CategorieDto categorieDto = new CategorieDto(1,"sac", "PAPIER RAM");
        CategorieDto categoryDtoResult = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto)
                )
        );

        String categoryDesignation = "Bureau";
        CategorieDto categorieUpdateDto = new CategorieDto(1,"Bureau", categoryDesignation);

        categorieUpdateDto.setId((long) 1);
        CategorieDto.fromEntityToDto(categorieRepository.save(CategorieDto.fromDtoToEntity(categorieUpdateDto)));

        assertThat(categorieUpdateDto.getDesignation()).isEqualTo(categoryDesignation);

    }

    @Test
    public void testFindById() {

        CategorieDto categorieDto = new CategorieDto(1,"sac", "sac a mai");
        CategorieDto categoryDtoResult = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto)
                )
        );

        Long cat_id = (long) 1;
        Optional<Categorie> categorie = categorieRepository.findById(categoryDtoResult.getId());

      /*  boolean isCategory = categorieRepository.findById(cat_id).get();
        assertTrue(isCategory);
        assertTrue(isCategory);
        */
        assertNotNull(categorie);
    }

    @Test
    public void testFindByDesignation() {
        CategorieDto categorieDto = new CategorieDto(1,"Robe", "RobeMariage");
        CategorieDto categoryDtoResult = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto)
                )
        );
        String catDesignation = "RobeMariage";
        assertThat(categoryDtoResult.getDesignation()).isEqualTo(catDesignation);
    }

    @Test
    public void testFindAll() {
        CategorieDto categorieDto = new CategorieDto(1,"Robe", "sac a mai");
        CategorieDto categoryDtoResult = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto)
                )
        );
        CategorieDto categorieDto1 = new CategorieDto(1,"Panthalon", "Panthalon homme");
        CategorieDto categoryDtoResult1 = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto1)
                )
        );

        CategorieDto categorieDto2 = new CategorieDto(1,"Chemise", "Chemise Femme");
        CategorieDto categoryDtoResult2 = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto2)
                )
        );

        List<?> categories = categorieRepository.findAll();

        assertThat(categories).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {

        CategorieDto categorieDto = new CategorieDto(1,"Chemise", "Chemise Femme");
        CategorieDto categoryDtoResult2 = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto)
                )
        );

        Optional<Categorie> categorie = categorieRepository.findById(categoryDtoResult2.getId());

       // assertNotNull(categorie);

        Long id = (long) 1;

        boolean isExistBeforeDelete = categorieRepository.findById(categoryDtoResult2.getId()).isPresent();

        categorieRepository.deleteById(categoryDtoResult2.getId());

        boolean notExistAfterDelete = categorieRepository.findById(categoryDtoResult2.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }
}
