package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Category;
import com.dp.dpshopbackend.models.Scategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ScategoryRepositoryTest {

    @Autowired
    private ScategoryRepository scategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Rollback(false)
    public void testCreateScategory() {
        Long catId = (long) 1;
        Category category = categoryRepository.findById(catId).orElse(null);

        String codeScategory = "scat1";
        String libelle = "SacVoyage";
        Scategory scategoryDto = new Scategory(1L, codeScategory, libelle, category);

        Scategory scategoryDtoResult = scategoryRepository.save(scategoryDto);

        assertNotNull(scategoryDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateScategory() {
        Category categoryDto = new Category(8L, "sac", "PAPIER RAM");
        Category categoryResut = categoryRepository.save(categoryDto);
        String codeScategory = "scat1";
        String libelle = "SacVoyage";
        Scategory scategoryDto = new Scategory(1L, codeScategory, libelle, categoryResut);
        scategoryRepository.save(scategoryDto);

        String libelle1 = "SacAMain";
        scategoryDto.setId(3L);
        scategoryDto.setLibelle(libelle1);
        Scategory scategoryUpdate = scategoryRepository.save(scategoryDto);

        assertThat(scategoryUpdate.getLibelle()).isEqualTo(libelle1);
        assertThat(scategoryUpdate.getCode()).isEqualTo(scategoryDto.getCode());

    }

    @Test
    public void testFindById() {
        Long catId = (long) 1;
        Category category = categoryRepository.findById(catId).orElse(null);

        Scategory scategory = new Scategory(1L, "sac", "sac a mai", category);

        Scategory categoryDtoResult = scategoryRepository.save(scategory);

        boolean isExistScategory = scategoryRepository.findById(categoryDtoResult.getId()).isPresent();

        assertTrue(isExistScategory);

    }

    @Test
    public void testFindByLibelle() {
        Long catId = (long) 1;
        Category category = categoryRepository.findById(catId).orElse(null);

        Scategory scategory = new Scategory(1L, "Robe", "RobeMariage", category);
        Scategory scategoryResult = scategoryRepository.save(scategory);

        String libelleScategory = "RobeMariage";

        assertThat(scategoryResult.getLibelle()).isEqualTo(libelleScategory);

    }

    @Test
    public void testFindAll() {
        Long catId = (long) 1;
        Category category = categoryRepository.findById(catId).orElse(null);

        Scategory scategory = new Scategory(1L, "Robe", "RobeElite", category);
        scategoryRepository.save(scategory);

        Scategory scategory1 = new Scategory(2L, "Jupe", "JupeElite", category);
        scategoryRepository.save(scategory);

        List<Scategory> scategoryList = scategoryRepository.findAll();

        assertThat(scategoryList).size().isGreaterThan(0);


    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Long catId = (long) 1;
        Category category = categoryRepository.findById(catId).orElse(null);

        Scategory scategory = new Scategory(1L, "Chemise", "String", category);

        Scategory scategoryDelete = scategoryRepository.save(scategory);

        boolean isExistBeforeDelete = scategoryRepository.findById(scategoryDelete.getId()).isPresent();

        scategoryRepository.deleteById(scategoryDelete.getId());

        boolean notExistAfterDelete = scategoryRepository.findById(scategoryDelete.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
