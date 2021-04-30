package com.dp.dpshopbackend;

import com.dp.dpshopbackend.dto.*;
import com.dp.dpshopbackend.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	}

	@Override
	public void run(String... args) throws Exception {

        CategoryDto c1 = new CategoryDto((long) 1,"cat1", "cat1");
		CategoryDto c2 = new CategoryDto((long) 2,"cat2", "cat2");
		CategoryDto c3 = new CategoryDto((long) 3,"cat3", "cat3");
		CategoryDto.fromEntityToDto(categoryRepository.save(CategoryDto.fromDtoToEntity(c1)));
		CategoryDto.fromEntityToDto(categoryRepository.save(CategoryDto.fromDtoToEntity(c2)));
		CategoryDto.fromEntityToDto(categoryRepository.save(CategoryDto.fromDtoToEntity(c3)));

		String codescat = "scat1"; String libellescat = "scat1";
        ScategoryDto sc1 = new ScategoryDto();
        sc1.setCode(codescat); sc1.setLibelle(libellescat); sc1.setCategoryDto(c1);
		String codescat2 = "scat2"; String libellescat2 = "scat2";
		ScategoryDto sc2 = new ScategoryDto();
		sc2.setCode(codescat2); sc2.setLibelle(libellescat2); sc2.setCategoryDto(c2);
		String codescat3 = "scat3"; String libellescat3 = "scat3";
		ScategoryDto sc3 = new ScategoryDto();
		sc3.setCode(codescat3); sc3.setLibelle(libellescat3); sc3.setCategoryDto(c3);

		String codescat4 = "scat4"; String libellescat4 = "scat4";
		ScategoryDto sc4 = new ScategoryDto();
		sc4.setCode(codescat4); sc4.setLibelle(libellescat4); sc4.setCategoryDto(c2);

		ScategoryDto.fromEntityToDto(scategoryRepository.save(ScategoryDto.fromDtoToEntity(sc1)));
		ScategoryDto.fromEntityToDto(scategoryRepository.save(ScategoryDto.fromDtoToEntity(sc2)));
		ScategoryDto.fromEntityToDto(scategoryRepository.save(ScategoryDto.fromDtoToEntity(sc3)));
		ScategoryDto.fromEntityToDto(scategoryRepository.save(ScategoryDto.fromDtoToEntity(sc4)));

        ArticleDto p1= new ArticleDto((long)1,"prod1","prod1", 150,1700.0,1800.0,true,true,"prod1","photo",sc1);
		ArticleDto p2= new ArticleDto((long)2,"prod2","prod2", 150,1700.0,1800.0,true,true,"prod1","photo",sc1);
		ArticleDto p3= new ArticleDto((long)3,"prod3","prod3", 150,1700.0,1800.0,false,false,"prod1","photo",sc1);
		ArticleDto p4= new ArticleDto((long)4,"prod4","prod4", 150,1700.0,1800.0,true,false,"prod1","photo",sc1);
		ArticleDto p5= new ArticleDto((long)5,"prod5","prod5", 150,1700.0,1800.0,false,true,"prod1","photo",sc1);
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p1)));
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p2)));
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p3)));
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p4)));
		ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(p5)));

		FournisseurDto f1 = new FournisseurDto((long)1, "f1","f1","f1","f1","f1","f1","f1","f1", p1);
		FournisseurDto f2 = new FournisseurDto((long)2, "f2","f2","f2","f2","f2","f2","f2","f2", p2);
		FournisseurDto f3 = new FournisseurDto((long)3, "f3","f3","f3","f3","f3","f3","f3","f3", p2);
		FournisseurDto f4 = new FournisseurDto((long)4, "f4","f4","f4","f4","f4","f4","f4","f4", p1);
		FournisseurDto.fromEntityToDto(fournisseurRepository.save(FournisseurDto.fromDtoToEntity(f1)));
		FournisseurDto.fromEntityToDto(fournisseurRepository.save(FournisseurDto.fromDtoToEntity(f2)));
		FournisseurDto.fromEntityToDto(fournisseurRepository.save(FournisseurDto.fromDtoToEntity(f3)));
		FournisseurDto.fromEntityToDto(fournisseurRepository.save(FournisseurDto.fromDtoToEntity(f4)));

		ClientDto cl1 = new ClientDto((long)1, "cl1","cl1","cl1","cl1","cl1");
		ClientDto cl2 = new ClientDto((long)2, "cl2","cl2","cl2","cl2","cl2");
		ClientDto cl3 = new ClientDto((long)3, "cl3","cl3","cl3","cl3","cl3");
		ClientDto cl4 = new ClientDto((long)4, "cl4","cl4","cl4","cl4","cl4");
		ClientDto.fromEntityToDto(clientRepository.save(ClientDto.fromDtoToEntity(cl1)));
		ClientDto.fromEntityToDto(clientRepository.save(ClientDto.fromDtoToEntity(cl2)));
		ClientDto.fromEntityToDto(clientRepository.save(ClientDto.fromDtoToEntity(cl3)));
		ClientDto.fromEntityToDto(clientRepository.save(ClientDto.fromDtoToEntity(cl4)));


	}
}
