package com.dp.dpshopbackend;

import com.dp.dpshopbackend.dto.ClientDto;
import com.dp.dpshopbackend.models.*;
import com.dp.dpshopbackend.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class DpshopBackendApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DpshopBackendApplication.class);

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ScategoryRepository scategoryRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private FournisseurRepository fournisseurRepository;

    public static void main(String[] args) {
        SpringApplication.run(DpshopBackendApplication.class, args);
        createDirectoryIfItDoesntExist();
    }

    private static void createDirectoryIfItDoesntExist() {
        Path path = Paths.get(System.getProperty("user.home") + "/shopmania/productphotos/");

        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException ie) {
                LOG.error(String.format("Problem creating directory %s", path));
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {

        Category c1 = new Category(1L, "cat1", "cat1");
        Category c2 = new Category(2L, "cat2", "cat2");
        Category c3 = new Category(3L, "cat3", "cat3");
        categoryRepository.save(c1);
        categoryRepository.save(c2);
        categoryRepository.save(c3);

        Scategory sc1 = new Scategory(1L, "scat1", "Fashion & Beauty", c1);
        Scategory sc2 = new Scategory(2L, "scat2", "Kids & Babies Clothes", c2);
        Scategory sc3 = new Scategory(3L, "scat3", "Men & Women Clothes", c3);
        Scategory sc4 = new Scategory(4L, "scat4", "Gadgets & Accessories", c2);
        Scategory sc5 = new Scategory(5L, "scat5", "Electronics & Accessories", c3);

        scategoryRepository.save(sc1);
        scategoryRepository.save(sc2);
        scategoryRepository.save(sc3);
        scategoryRepository.save(sc4);
        scategoryRepository.save(sc5);

        Article p1 = new Article(1L, "prod1", "HP-ProBooks", 150, 1700.0, 1800.0, true, true, "prod1", "product-1.jpg", sc1);
        Article p2 = new Article(2L, "prod2", "Mac-Pro", 150, 1900.0, 1800.0, true, true, "prod1", "product-2.jpg", sc3);
        Article p3 = new Article(3L, "prod3", "Robe-Mariage", 150, 2000.0, 1800.0, false, true, "prod1", "product-3.jpg", sc2);
        Article p4 = new Article(4L, "prod4", "Jupe-Nuit", 150, 3000.0, 1800.0, true, true, "prod1", "product-4.jpg", sc1);
        Article p5 = new Article(5L, "prod5", "HP EliteBook", 150, 1700.0, 1800.0, false, true, "prod1", "product-5.jpg", sc4);
        Article p6 = new Article(6L, "prod6", "Mac-OS", 150, 1900.0, 1800.0, true, true, "prod1", "product-6.jpg", sc1);
        Article p7 = new Article(7L, "prod7", "Pantallon", 150, 2000.0, 1800.0, true, true, "prod1", "product-7.jpg", sc1);
        Article p8 = new Article(8L, "prod8", "Cullote", 150, 3000.0, 1800.0, false, true, "prod1", "product-8.jpg", sc3);
        Article p9 = new Article(9L, "prod9", "Ensembes", 150, 5000.0, 1800.0, true, true, "prod1", "product-9.jpg", sc3);
        Article p10 = new Article(10L, "prod10", "Pantallon", 150, 4000.0, 1800.0, false, true, "prod1", "product-10.jpg", sc4);
        Article p11 = new Article(11L, "prod11", "Robe Ete", 150, 50000.0, 1800.0, true, true, "prod1", "photo1.jpg", sc1);
        Article p12 = new Article(12L, "prod12", "HuperCool", 150, 20000.0, 1800.0, true, true, "prod1", "photo2.jpg", sc3);
        Article p13 = new Article(13L, "prod13", "Parfums", 150, 1600.0, 1800.0, false, true, "prod1", "photo3.jpg", sc2);
        Article p14 = new Article(14L, "prod14", "Eaux de Cologne", 150, 1500.0, 1800.0, true, false, "prod1", "photo4.jpg", sc1);
        Article p15 = new Article(15L, "prod15", "Ordinateurs", 150, 1700.0, 1800.0, false, false, "prod1", "photo5.jpg", sc4);
        Article p16 = new Article(16L, "prod16", "Mini PC", 150, 20000.0, 1800.0, true, true, "prod1", "photo6.jpg", sc1);
        Article p17 = new Article(17L, "prod17", "product-17", 150, 14000.0, 1800.0, true, false, "prod1", "photo7.jpg", sc1);
        Article p18 = new Article(18L, "prod18", "product-18", 150, 12000.0, 1800.0, false, false, "prod1", "photo8.jpg", sc3);
        Article p19 = new Article(19L, "prod19", "product-19", 150, 10000.0, 1800.0, true, false, "prod1", "photo9.jpg", sc3);
        Article p20 = new Article(20L, "prod20", "product-20", 150, 12000.0, 1800.0, false, false, "prod1", "photo10.jpg", sc4);

        articleRepository.save(p1);
        articleRepository.save(p2);
        articleRepository.save(p3);
        articleRepository.save(p4);
        articleRepository.save(p5);
        articleRepository.save(p6);
        articleRepository.save(p7);
        articleRepository.save(p8);
        articleRepository.save(p9);
        articleRepository.save(p10);
        articleRepository.save(p11);
        articleRepository.save(p12);
        articleRepository.save(p13);
        articleRepository.save(p14);
        articleRepository.save(p15);
        articleRepository.save(p16);
        articleRepository.save(p17);
        articleRepository.save(p18);
        articleRepository.save(p19);
        articleRepository.save(p20);
/*
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p1)));
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p2)));
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p3)));
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p4)));
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p5)));
*/
        Fournisseur f1 = new Fournisseur((long) 1, "f1", "f1", "f1", "f1", "f1", "f1", "f1", "f1", p1);
        Fournisseur f2 = new Fournisseur((long) 2, "f2", "f2", "f2", "f2", "f2", "f2", "f2", "f2", p2);
        Fournisseur f3 = new Fournisseur((long) 3, "f3", "f3", "f3", "f3", "f3", "f3", "f3", "f3", p2);
        Fournisseur f4 = new Fournisseur((long) 4, "f4", "f4", "f4", "f4", "f4", "f4", "f4", "f4", p1);
        fournisseurRepository.save(f1);
        fournisseurRepository.save(f2);
        fournisseurRepository.save(f3);
        fournisseurRepository.save(f4);
/*
		FournisseurDto.fromEntityToDto(fournisseurRepository.save(FournisseurDto.fromDtoToEntity(f1)));
		FournisseurDto.fromEntityToDto(fournisseurRepository.save(FournisseurDto.fromDtoToEntity(f2)));
		FournisseurDto.fromEntityToDto(fournisseurRepository.save(FournisseurDto.fromDtoToEntity(f3)));
		FournisseurDto.fromEntityToDto(fournisseurRepository.save(FournisseurDto.fromDtoToEntity(f4)));
*/
        Client cl1 = new Client((long) 1, "cl1", "cl1", "cl1", "cl1", "cl1");
        Client cl2 = new Client((long) 2, "cl2", "cl2", "cl2", "cl2", "cl2");
        Client cl3 = new Client((long) 3, "cl3", "cl3", "cl3", "cl3", "cl3");
        ClientDto cl4 = new ClientDto(4, "cl4", "cl4", "cl4", "cl4", "cl4");
        clientRepository.save(cl1);
        clientRepository.save(cl2);
        clientRepository.save(cl3);
/*
		ClientDto.fromEntityToDto(clientRepository.save(ClientDto.fromDtoToEntity(cl1)));
		ClientDto.fromEntityToDto(clientRepository.save(ClientDto.fromDtoToEntity(cl2)));
		ClientDto.fromEntityToDto(clientRepository.save(ClientDto.fromDtoToEntity(cl3)));
		ClientDto.fromEntityToDto(clientRepository.save(ClientDto.fromDtoToEntity(cl4)));
*/

    }
}
