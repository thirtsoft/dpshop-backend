package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.dto.CategorieDto;
import com.dp.dpshopbackend.dto.ScategorieDto;
import com.dp.dpshopbackend.models.Categorie;
import com.dp.dpshopbackend.models.Scategorie;
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
public class ScategoryRepositoryTest {

    @Autowired
    private ScategorieRepository scategorieRepository;

    @Test
    @Rollback(false)
    public void testCreateScategory() {
        CategorieDto categorieDto = new CategorieDto(1,"sac", "sac a mai");
        String codeScategory = "scat1";
        String libelle = "SacVoyage";
        ScategorieDto scategorieDto = new ScategorieDto();
        scategorieDto.setCode(codeScategory);
        scategorieDto.setLibelle(libelle);
        scategorieDto.setCategorieDto(categorieDto);

        ScategorieDto scategoryDtoResult = ScategorieDto.fromEntityToDto(
                scategorieRepository.save(
                        ScategorieDto.fromDtoToEntity(scategorieDto)
                )
        );

        assertNotNull(scategoryDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateScategory() {
        CategorieDto categorieDto = new CategorieDto(1,"sac", "PAPIER RAM");
        String codeScategory = "scat1";
        String libelle = "SacVoyage";
        ScategorieDto scategorieDto = new ScategorieDto();
        scategorieDto.setCode(codeScategory);
        scategorieDto.setLibelle(libelle);
        scategorieDto.setCategorieDto(categorieDto);

        ScategorieDto scategoryDtoResult = ScategorieDto.fromEntityToDto(
                scategorieRepository.save(
                        ScategorieDto.fromDtoToEntity(scategorieDto)
                )
        );

        String scategoryCode = "Bur";
        String scategoryLibelle = "Bureau";
        ScategorieDto scategoryUpdateDto = new ScategorieDto();
        scategoryUpdateDto.setCode(scategoryCode);
        scategoryUpdateDto.setLibelle(scategoryLibelle);
        scategoryUpdateDto.setCategorieDto(categorieDto);


        scategoryUpdateDto.setId((long) 1);
        ScategorieDto.fromEntityToDto(scategorieRepository.save(ScategorieDto.fromDtoToEntity(scategoryUpdateDto)));

        assertThat(scategoryUpdateDto.getLibelle()).isEqualTo(scategoryLibelle);

    }

    @Test
    public void testFindById() {

        CategorieDto categorieDto = new CategorieDto(1,"tshirt", "Tshirt-Man");
        String scategoryCode = "tis";
        String scategoryLibelle = "Tshirt-Mans";
        ScategorieDto scategoryDto = new ScategorieDto();
        scategoryDto.setCode(scategoryCode);
        scategoryDto.setLibelle(scategoryLibelle);
        scategoryDto.setCategorieDto(categorieDto);

        ScategorieDto scategoryDtoResult = ScategorieDto.fromEntityToDto(
                scategorieRepository.save(
                        ScategorieDto.fromDtoToEntity(scategoryDto)
                )
        );

        Optional<Scategorie> scategorie = scategorieRepository.findById(scategoryDtoResult.getId());

        assertNotNull(scategorie);

    }

    @Test
    public void testFindByLibelle() {
        CategorieDto categorieDto = new CategorieDto(1,"Ordi", "Ordinateurs");
        String scategoryCode = "scat2";
        String scategoryLibelle = "HP-ProBook";
        ScategorieDto scategorieDto = new ScategorieDto();
        scategorieDto.setCode(scategoryCode);
        scategorieDto.setLibelle(scategoryLibelle);
        scategorieDto.setCategorieDto(categorieDto);

        ScategorieDto scategoryDtoResult = ScategorieDto.fromEntityToDto(
                scategorieRepository.save(
                        ScategorieDto.fromDtoToEntity(scategorieDto)
                )
        );
        String libelleFind = "HP-ProBook";
        assertThat(scategoryDtoResult.getLibelle()).isEqualTo(libelleFind);
    }

    @Test
    public void testFindAll() {
        CategorieDto categorieDto = new CategorieDto(1,"Robe", "RobeElite");
        String scategoryCode = "scat3";
        String scategoryLibelle = "RobeElite3";
        ScategorieDto scategorieDto = new ScategorieDto();
        scategorieDto.setCode(scategoryCode);
        scategorieDto.setLibelle(scategoryLibelle);
        scategorieDto.setCategorieDto(categorieDto);

        ScategorieDto scategoryDtoResult = ScategorieDto.fromEntityToDto(
                scategorieRepository.save(
                        ScategorieDto.fromDtoToEntity(scategorieDto)
                )
        );
        CategorieDto categorieDto1 = new CategorieDto(1,"Panthalon", "Panthalon homme");
        String scategoryCode1 = "scat4";
        String scategoryLibelle1 = "PanthallonHomme1";
        ScategorieDto scategorieDto1 = new ScategorieDto();
        scategorieDto.setCode(scategoryCode1);
        scategorieDto.setLibelle(scategoryLibelle1);
        scategorieDto.setCategorieDto(categorieDto1);
        ScategorieDto scategoryDtoResult1 = ScategorieDto.fromEntityToDto(
                scategorieRepository.save(
                        ScategorieDto.fromDtoToEntity(scategorieDto1)
                )
        );

        CategorieDto categorieDto2 = new CategorieDto(1,"Chemise", "Chemise Femme");
        String scategoryCode2 = "scat5";
        String scategoryLibelle2 = "Chemise-Femme";
        ScategorieDto scategorieDto2 = new ScategorieDto();
        scategorieDto.setCode(scategoryCode2);
        scategorieDto.setLibelle(scategoryLibelle2);
        scategorieDto.setCategorieDto(categorieDto2);

        ScategorieDto categoryDtoResult2 = ScategorieDto.fromEntityToDto(
                scategorieRepository.save(
                        ScategorieDto.fromDtoToEntity(scategorieDto2)
                )
        );

        List<?> scategories = scategorieRepository.findAll();

        assertThat(scategories).size().isGreaterThan(2);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        CategorieDto categorieDto = new CategorieDto(1,"Chemise", "String");
        String scategoryCode = "scat5";
        String scategoryLibelle = "String-Plage";
        ScategorieDto scategorieDto = new ScategorieDto();
        scategorieDto.setCode(scategoryCode);
        scategorieDto.setLibelle(scategoryLibelle);
        scategorieDto.setCategorieDto(categorieDto);

        ScategorieDto scategoryDtoResult2 = ScategorieDto.fromEntityToDto(
                scategorieRepository.save(
                        ScategorieDto.fromDtoToEntity(scategorieDto)
                )
        );

        Long id = (long) 1;

        Optional<Scategorie> scategorie = scategorieRepository.findById(scategoryDtoResult2.getId());

        boolean isExistBeforeDelete = scategorieRepository.findById(scategoryDtoResult2.getId()).isPresent();

        scategorieRepository.deleteById(scategoryDtoResult2.getId());

        boolean notExistAfterDelete = scategorieRepository.findById(scategoryDtoResult2.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
