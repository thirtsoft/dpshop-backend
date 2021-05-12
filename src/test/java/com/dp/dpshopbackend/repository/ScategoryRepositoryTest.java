package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.dto.CategoryDto;
import com.dp.dpshopbackend.dto.ScategoryDto;
import com.dp.dpshopbackend.models.Scategory;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
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
public class ScategoryRepositoryTest {

    @Autowired
    private ScategoryRepository scategoryRepository;

    @Test
    @Rollback(false)
    public void testCreateScategory() {
        CategoryDto categoryDto = new CategoryDto(null, "sac", "sac a mai");
        String codeScategory = "scat1";
        String libelle = "SacVoyage";
        ScategoryDto scategoryDto = new ScategoryDto();
        scategoryDto.setCode(codeScategory);
        scategoryDto.setLibelle(libelle);
        scategoryDto.setCategoryDto(categoryDto);

        ScategoryDto scategoryDtoResult = ScategoryDto.fromEntityToDto(
                scategoryRepository.save(
                        ScategoryDto.fromDtoToEntity(scategoryDto)
                )
        );

        assertNotNull(scategoryDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateScategory() {
        CategoryDto categoryDto = new CategoryDto(null, "sac", "PAPIER RAM");
        String codeScategory = "scat1";
        String libelle = "SacVoyage";
        ScategoryDto scategoryDto = new ScategoryDto();
        scategoryDto.setCode(codeScategory);
        scategoryDto.setLibelle(libelle);
        scategoryDto.setCategoryDto(categoryDto);

        ScategoryDto scategoryDtoResult = ScategoryDto.fromEntityToDto(
                scategoryRepository.save(
                        ScategoryDto.fromDtoToEntity(scategoryDto)
                )
        );

        String scategoryCode = "Bur";
        String scategoryLibelle = "Bureau";
        ScategoryDto scategoryUpdateDto = new ScategoryDto();
        scategoryUpdateDto.setCode(scategoryCode);
        scategoryUpdateDto.setLibelle(scategoryLibelle);
        scategoryUpdateDto.setCategoryDto(categoryDto);


        scategoryUpdateDto.setId(1);
        ScategoryDto.fromEntityToDto(scategoryRepository.save(ScategoryDto.fromDtoToEntity(scategoryUpdateDto)));

        assertThat(scategoryUpdateDto.getLibelle()).isEqualTo(scategoryLibelle);

    }

    @Test
    public void testFindById() {

        CategoryDto categoryDto = new CategoryDto(null, "tshirt", "Tshirt-Man");
        String scategoryCode = "tis";
        String scategoryLibelle = "Tshirt-Mans";
        ScategoryDto scategoryDto = new ScategoryDto();
        scategoryDto.setCode(scategoryCode);
        scategoryDto.setLibelle(scategoryLibelle);
        scategoryDto.setCategoryDto(categoryDto);

        ScategoryDto scategoryDtoResult = ScategoryDto.fromEntityToDto(
                scategoryRepository.save(
                        ScategoryDto.fromDtoToEntity(scategoryDto)
                )
        );

        Optional<Scategory> scategorie = scategoryRepository.findById(scategoryDtoResult.getId());

        assertNotNull(scategorie);

    }

    @Test
    public void testFindByLibelle() {
        CategoryDto categoryDto = new CategoryDto(null, "Ordi", "Ordinateurs");
        String scategoryCode = "scat2";
        String scategoryLibelle = "HP-ProBook";
        ScategoryDto scategoryDto = new ScategoryDto();
        scategoryDto.setCode(scategoryCode);
        scategoryDto.setLibelle(scategoryLibelle);
        scategoryDto.setCategoryDto(categoryDto);

        ScategoryDto scategoryDtoResult = ScategoryDto.fromEntityToDto(
                scategoryRepository.save(
                        ScategoryDto.fromDtoToEntity(scategoryDto)
                )
        );
        String libelleFind = "HP-ProBook";
        assertThat(scategoryDtoResult.getLibelle()).isEqualTo(libelleFind);
    }

    @Test
    public void testFindAll() {
        CategoryDto categoryDto = new CategoryDto(null, "Robe", "RobeElite");
        String scategoryCode = "scat3";
        String scategoryLibelle = "RobeElite3";
        ScategoryDto scategoryDto = new ScategoryDto();
        scategoryDto.setCode(scategoryCode);
        scategoryDto.setLibelle(scategoryLibelle);
        scategoryDto.setCategoryDto(categoryDto);

        ScategoryDto scategoryDtoResult = ScategoryDto.fromEntityToDto(
                scategoryRepository.save(
                        ScategoryDto.fromDtoToEntity(scategoryDto)
                )
        );
        CategoryDto categoryDto1 = new CategoryDto(null, "Panthalon", "Panthalon homme");
        String scategoryCode1 = "scat4";
        String scategoryLibelle1 = "PanthallonHomme1";
        ScategoryDto scategoryDto1 = new ScategoryDto();
        scategoryDto.setCode(scategoryCode1);
        scategoryDto.setLibelle(scategoryLibelle1);
        scategoryDto.setCategoryDto(categoryDto1);
        ScategoryDto scategoryDtoResult1 = ScategoryDto.fromEntityToDto(
                scategoryRepository.save(
                        ScategoryDto.fromDtoToEntity(scategoryDto1)
                )
        );

        CategoryDto categoryDto2 = new CategoryDto(null, "Chemise", "Chemise Femme");
        String scategoryCode2 = "scat5";
        String scategoryLibelle2 = "Chemise-Femme";
        ScategoryDto scategoryDto2 = new ScategoryDto();
        scategoryDto.setCode(scategoryCode2);
        scategoryDto.setLibelle(scategoryLibelle2);
        scategoryDto.setCategoryDto(categoryDto2);

        ScategoryDto categoryDtoResult2 = ScategoryDto.fromEntityToDto(
                scategoryRepository.save(
                        ScategoryDto.fromDtoToEntity(scategoryDto2)
                )
        );

        List<?> scategories = scategoryRepository.findAll();

        assertThat(scategories).size().isGreaterThan(2);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        CategoryDto categoryDto = new CategoryDto(null, "Chemise", "String");
        String scategoryCode = "scat5";
        String scategoryLibelle = "String-Plage";
        ScategoryDto scategoryDto = new ScategoryDto();
        scategoryDto.setCode(scategoryCode);
        scategoryDto.setLibelle(scategoryLibelle);
        scategoryDto.setCategoryDto(categoryDto);

        ScategoryDto scategoryDtoResult2 = ScategoryDto.fromEntityToDto(
                scategoryRepository.save(
                        ScategoryDto.fromDtoToEntity(scategoryDto)
                )
        );

        Long id = (long) 1;

        Optional<Scategory> scategorie = scategoryRepository.findById(scategoryDtoResult2.getId());

        boolean isExistBeforeDelete = scategoryRepository.findById(scategoryDtoResult2.getId()).isPresent();

        scategoryRepository.deleteById(scategoryDtoResult2.getId());

        boolean notExistAfterDelete = scategoryRepository.findById(scategoryDtoResult2.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
