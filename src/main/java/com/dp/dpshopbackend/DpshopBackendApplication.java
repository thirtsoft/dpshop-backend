package com.dp.dpshopbackend;

import com.dp.dpshopbackend.dto.*;
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

        Category c1 = new Category((long) 1,"cat1", "cat1");
		Category c2 = new Category((long) 2,"cat2", "cat2");
		Category c3 = new Category((long) 3,"cat3", "cat3");
		categoryRepository.save(c1);categoryRepository.save(c2);categoryRepository.save(c3);
/*
		CategoryDto.fromEntityToDto(categoryRepository.save(CategoryDto.fromDtoToEntity(c1)));
		CategoryDto.fromEntityToDto(categoryRepository.save(CategoryDto.fromDtoToEntity(c2)));
		CategoryDto.fromEntityToDto(categoryRepository.save(CategoryDto.fromDtoToEntity(c3)));
*/
		String codescat = "scat1"; String libellescat = "scat1";
        Scategory sc1 = new Scategory();
        sc1.setCode(codescat); sc1.setLibelle(libellescat); sc1.setCategory(c1);
		String codescat2 = "scat2"; String libellescat2 = "scat2";
		Scategory sc2 = new Scategory();
		sc2.setCode(codescat2); sc2.setLibelle(libellescat2); sc2.setCategory(c2);
		String codescat3 = "scat3"; String libellescat3 = "scat3";
		Scategory sc3 = new Scategory();
		sc3.setCode(codescat3); sc3.setLibelle(libellescat3); sc3.setCategory(c3);

		String codescat4 = "scat4"; String libellescat4 = "scat4";
		Scategory sc4 = new Scategory();
		sc4.setCode(codescat4); sc4.setLibelle(libellescat4); sc4.setCategory(c2);
		scategoryRepository.save(sc1);scategoryRepository.save(sc2);
		scategoryRepository.save(sc3);scategoryRepository.save(sc4);
/*
		ScategoryDto.fromEntityToDto(scategoryRepository.save(ScategoryDto.fromDtoToEntity(sc1)));
		ScategoryDto.fromEntityToDto(scategoryRepository.save(ScategoryDto.fromDtoToEntity(sc2)));
		ScategoryDto.fromEntityToDto(scategoryRepository.save(ScategoryDto.fromDtoToEntity(sc3)));
		ScategoryDto.fromEntityToDto(scategoryRepository.save(ScategoryDto.fromDtoToEntity(sc4)));
*/
        Article p1= new Article(1L,"prod1","prod1", 150,1700.0,1800.0,true,true,"prod1","photo1.jpg",sc1);
		Article p2= new Article(2L,"prod2","prod2", 150,1700.0,1800.0,true,true,"prod1","photo2.jpg",sc1);
		Article p3= new Article(3L,"prod3","prod3", 150,1700.0,1800.0,false,false,"prod1","photo3.jpg",sc1);
		Article p4= new Article(4L,"prod4","prod4", 150,1700.0,1800.0,true,false,"prod1","photo4.jpg",sc1);
		Article p5= new Article(5L,"prod5","prod5", 150,1700.0,1800.0,false,true,"prod1","photo5.jpg",sc1);
		Article p6= new Article(6L,"prod6","prod6", 150,1700.0,1800.0,true,true,"prod1","photo5.jpg",sc1);
		Article p7= new Article(7L,"prod7","prod7", 150,1700.0,1800.0,true,true,"prod1","photo7.jpg",sc1);
		Article p8= new Article(8L,"prod8","prod8", 150,1700.0,1800.0,false,false,"prod1","photo8.jpg",sc1);
		Article p9= new Article(9L,"prod9","prod9", 150,1700.0,1800.0,true,false,"prod1","photo9.jpg",sc1);
		Article p10= new Article(10L,"prod10","prod10", 150,1700.0,1800.0,false,true,"prod1","photo10.jpg",sc1);

		articleRepository.save(p1);articleRepository.save(p2);articleRepository.save(p3);
		articleRepository.save(p4);articleRepository.save(p5);
		articleRepository.save(p6);articleRepository.save(p7);articleRepository.save(p8);
		articleRepository.save(p9);articleRepository.save(p10);
/*
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p1)));
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p2)));
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p3)));
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p4)));
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p5)));
*/
		Fournisseur f1 = new Fournisseur((long)1, "f1","f1","f1","f1","f1","f1","f1","f1", p1);
		Fournisseur f2 = new Fournisseur((long)2, "f2","f2","f2","f2","f2","f2","f2","f2", p2);
		Fournisseur f3 = new Fournisseur((long)3, "f3","f3","f3","f3","f3","f3","f3","f3", p2);
		Fournisseur f4 = new Fournisseur((long)4, "f4","f4","f4","f4","f4","f4","f4","f4", p1);
		fournisseurRepository.save(f1);fournisseurRepository.save(f2);
		fournisseurRepository.save(f3);fournisseurRepository.save(f4);
/*
		FournisseurDto.fromEntityToDto(fournisseurRepository.save(FournisseurDto.fromDtoToEntity(f1)));
		FournisseurDto.fromEntityToDto(fournisseurRepository.save(FournisseurDto.fromDtoToEntity(f2)));
		FournisseurDto.fromEntityToDto(fournisseurRepository.save(FournisseurDto.fromDtoToEntity(f3)));
		FournisseurDto.fromEntityToDto(fournisseurRepository.save(FournisseurDto.fromDtoToEntity(f4)));
*/
		Client cl1 = new Client((long)1, "cl1","cl1","cl1","cl1","cl1");
		Client cl2 = new Client((long)2, "cl2","cl2","cl2","cl2","cl2");
		Client cl3 = new Client((long)3, "cl3","cl3","cl3","cl3","cl3");
		ClientDto cl4 = new ClientDto((long)4, "cl4","cl4","cl4","cl4","cl4");
		clientRepository.save(cl1);clientRepository.save(cl2);clientRepository.save(cl3);
/*
		ClientDto.fromEntityToDto(clientRepository.save(ClientDto.fromDtoToEntity(cl1)));
		ClientDto.fromEntityToDto(clientRepository.save(ClientDto.fromDtoToEntity(cl2)));
		ClientDto.fromEntityToDto(clientRepository.save(ClientDto.fromDtoToEntity(cl3)));
		ClientDto.fromEntityToDto(clientRepository.save(ClientDto.fromDtoToEntity(cl4)));
*/

	}
}
