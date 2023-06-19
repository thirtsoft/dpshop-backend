                    /* CATEGORY */
INSERT INTO category(id,code,designation,actif) VALUES(1,'CAT-HAB','Mode & Fashion', 1);
INSERT INTO category(id,code,designation,actif) VALUES(2,'CAT-FB','Santé & Beauté', 1);
INSERT INTO category(id,code,designation,actif) VALUES(3,'CAT-MB','Maison & Bureau', 1);
INSERT INTO category(id,code,designation,actif) VALUES(4,'CAT-EL','Electronique', 1);
INSERT INTO category(id,code,designation,actif) VALUES(5,'CAT-ELM','Electroménager', 1);
INSERT INTO category(id,code,designation,actif) VALUES(6,'CAT-INF','Informatique', 1);
INSERT INTO category(id,code,designation,actif) VALUES(7,'CAT-PAR','Produits pour bébé', 1);
INSERT INTO category(id,code,designation,actif) VALUES(8,'CAT-AUT','Autres catégories', 1);

                    /* SUBCATEGORY */
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(1,'MODE-F','Mode Femme',1,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(2,'MODE-H','Mode Homme',1,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(3,'CHAUSS-F','Chaussures Femme',1,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(4,'CHAUSS-H','Chaussures Homme',1,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(5,'BIJ-F','Bijoux pour Femme',1,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(6,'SAC-F','Sacs à main',1,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(7,'SAC-H','Sacs pour Homme',1,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(8,'MONT-F','Montres pour Femme',1,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(9,'MONT-H','Montres pour Homme',1,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(11,'PARF-F','Parfum Femme',2,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(12,'PARF-H','Parfum Homme',2,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(13,'CREM-F','Créme Femme',2,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(14,'CREM-H','Créme Homme',2,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(15,'SAV-F','Savon Femme',2,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(16,'SAV-F','Savon Homme',2,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(17,'CHEV','Soins de cheveu',2,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(18,'MAQ-F','Maquillages',2,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(19,'HYG-F','Meilleur hygiène',2,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(20,'MOQ','Moquettes',3,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(21,'VESS','Vaisselles',3,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(22,'PARF-CH','Parfum de chambre',3,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(23,'PARF-B','Parfum de bureau',3,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(24,'TELV','Téléviseurs',4,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(25,'TEL','Téléphones portables',4,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(26,'TAB','Tablettes',4,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(27,'ECOUT','Ecouteurs',4,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(28,'CLIMA','Climatiseurs',4,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(29,'REF','Réfrigérateurs',5,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(30,'CONG','Congélateurs',5,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(31,'CUIS','Cuisinières',5,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(32,'CREM-H','Machine à laver',5,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(33,'ORD-PORT','Ordinateurs portable',6,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(34,'ORD-BUR','Ordinateurs bureau',6,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(35,'SOUR','Souris',6,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(36,'CLAV','Clavier',6,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(37,'PROD-BB','Produit pour bébé',7,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(38,'AUT-01','Autre catégories 01',8,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(39,'AUT-02','Autre catégories 02',8,1);
INSERT INTO scategorie(id,code,libelle,catId,actif) VALUES(40,'AUT-03','Autre catégories 03',8,1);

                    /* article */
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(1,'prod1','HP-ProBooks',32,1700,1800.0, true, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod1','prod1','article-1.jpg',1,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(2,'prod2','Mac-Pro',12,1900.0,1800.0, true, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod2','prod2','article-2.jpg',3,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(3,'prod3','Robe-Mariage',16,30000.0,25000.0, true, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod3','prod3','article-3.jpg',2,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES (4,'prod4','Jupe-Nuit',32,1700,1800.0, true, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod4','prod4','article-4.jpg',1,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(5,'prod5','HP EliteBook',22,17000,18000.0, false, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod5','prod5','article-5.jpg',4,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(6,'prod6','Mac-OS',18,19000,18000.0, true, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod6','prod6','article-6.jpg',1,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(7,'prod7','Pantallon',32,22000,18000.0, true, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod7','prod7','article-7.jpg',1,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(8,'prod8','Cullote',13,5000,4500.0, false, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod8','prod8','article-8.jpg',3,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(9,'prod9','Ensembes',32,26000,22000.0, true, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod9','prod9','article-9.jpg',3,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(10,'prod10','Pantallon',32,21000,23000.0, false, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod10','prod10','article-10.jpg',4,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(11,'prod11','Robe Ete',32,50000.0,45000.0, true, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod11','prod11','photo1.jpg',1,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(12,'prod12','HuperCool',12,35000.0,33000.0, true, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod12','prod12','photo2.jpg',3,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(13,'prod13','Parfums',16,13000.0,12000.0, false, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod13','prod13','photo3.jpg',2,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(14,'prod14','Eaux de Cologne',32,17000,15000.0, true, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod14','prod14','photo4.jpg',1,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(15,'prod15','Ordinateurs',19,17000,18000.0, false, false, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod15','prod15','photo5.jpg',4,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(16,'prod16','Mini PC',18,190000,180000.0, true, true, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod16','prod16','photo6.jpg',1,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(17,'prod17','article-17',32,16000,14000.0, true, false, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod17','prod17','photo7.jpg',1,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(18,'prod18','article-18',13,15000,14500.0, false, false, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod18','prod18','photo8.jpg',3,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(19,'prod19','article-19',32,12500,11000.0, true, false, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod19','prod19','photo9.jpg',3,1);
INSERT INTO article(id,reference,designation,quantity,price,currentPrice,promo,selected,createDate,lastUpDated,description,manufactured,photo,scat_id,actif) VALUES(20,'prod20','article-20',32,8500,9000.0, false, false, '2023-06-17 12:36:48','2023-06-17 12:36:48','prod20','prod20','photo10.jpg',4,1);

            /* REGIONS */
INSERT INTO country(id,code,name,actif) VALUES(1,'DK','Dakar');
INSERT INTO country(id,code,name,actif) VALUES(2,'DL','Diourbel');
INSERT INTO country(id,code,name,actif) VALUES(3,'FT','Fatick');
INSERT INTO country(id,code,name,actif) VALUES(4,'KF','Kaffrine');
INSERT INTO country(id,code,name,actif) VALUES(5,'KL','Kaolack');
INSERT INTO country(id,code,name,actif) VALUES(6,'KD','Kolda');
INSERT INTO country(id,code,name,actif) VALUES(7,'LG','Louga');
INSERT INTO country(id,code,name,actif) VALUES(8,'ST','Saint-Louis');
INSERT INTO country(id,code,name,actif) VALUES(9,'SD','Sedhiou');
INSERT INTO country(id,code,name,actif) VALUES(10,'TB','Tambacounda');
INSERT INTO country(id,code,name,actif) VALUES(11,'TH','Thiès');
INSERT INTO country(id,code,name,actif) VALUES(12,'ZG','Ziguinchor');

                    /* DEPARTEMENTS */
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Amitié 1', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Amitié 2', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Amitié 3', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Bambilor', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Bargny', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Bel Air 1', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Bop', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Camberene', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Castor', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Centenaire', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Cité Keur Damel', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Cité Keur Gorgui', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Colobane', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Castor', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Dalifort', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Derklet', 1,1);
INSERT INTO state(id,name,countryId,actif) VALUES(1,'Diamaguene', 1,1);



                    /* ROLES */
INSERT INTO roles(id,name) VALUES(1,'ROLE_VENDEUR');
INSERT INTO roles(id,name) VALUES(1,'ROLE_GERANT');
INSERT INTO roles(id,name) VALUES(1,'ROLE_MANAGER');
INSERT INTO roles(id,name) VALUES(1,'ROLE_ADMIN');

                    /* UTILISATEURS */
INSERT INTO utilisateur(id,name,username,email,password,photo,isActive,actif) VALUES(1,'Admin','admin','admin@gmail.com','$2a$10$R44dGnwuk2cMQFQXc35fXOER2c8XLC6Sll4j/fqjepLplg4gBG.sK','',true);
INSERT INTO utilisateur(id,name,username,email,password,photo,isActive,actif) VALUES(1,'Vendeur','vendeur','vendeur@gmail.com','$2a$10$v5RaZqqWEs.iupW7r.oErOSqlb4CJjWUXY5XepZ4O8Vt.lOYQiDMi','',true);
INSERT INTO utilisateur(id,name,username,email,password,photo,isActive,actif) VALUES(1,'Gerant','gerant','gerant@gmail.com','$2a$10$1Ch4l3YxBNzsQXTOLflqmOnxLQbp9XwGkSeTEmcucCtHvcYauXzo2','',true);
INSERT INTO utilisateur(id,name,username,email,password,photo,isActive,actif) VALUES(1,'Manageur','manageur','manageur@gmail.com','$2a$10$QCtMpd6M1ShbWcnDGrIYzO1hfQc0FS.EWrt68hDLe2mh64gEYVHqe','',true);

