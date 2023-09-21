
/****************************** Categories ********************/
INSERT INTO category(id,actif,code,designation) VALUES(1,1,'CAT-01','Jardinage');
INSERT INTO category(id,actif,code,designation) VALUES(2,1,'CAT-02','Categorie02');
INSERT INTO category(id,actif,code,designation) VALUES(3,1,'CAT-03','Categorie03');
INSERT INTO category(id,actif,code,designation) VALUES(4,1,'CAT-04','Categorie04');
INSERT INTO category(id,actif,code,designation) VALUES(5,1,'CAT-05','Categorie05');
INSERT INTO category(id,actif,code,designation) VALUES(6,1,'CAT-06','Categorie06');

/*********************** Scategorie ***************************/
INSERT INTO scategorie(id,actif,code,libelle,cat_id) VALUES(1,1,'SCAT-01','Accessoire',1);
INSERT INTO scategorie(id,actif,code,libelle,cat_id) VALUES(2,1,'SCAT-02','Habillement',1);
INSERT INTO scategorie(id,actif,code,libelle,cat_id) VALUES(3,1,'SCAT-03','Chaussures',2);
INSERT INTO scategorie(id,actif,code,libelle,cat_id) VALUES(4,1,'SCAT-04','Informatique',2);
INSERT INTO scategorie(id,actif,code,libelle,cat_id) VALUES(5,1,'SCAT-05','Bureatique',3);
INSERT INTO scategorie(id,actif,code,libelle,cat_id) VALUES(6,1,'SCAT-06','Scolaire',3);
INSERT INTO scategorie(id,actif,code,libelle,cat_id) VALUES(7,1,'SCAT-07','Maison',4);
INSERT INTO scategorie(id,actif,code,libelle,cat_id) VALUES(8,1,'SCAT-08','Bureau',4);

/************************** Article **************************/
INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(1,1,'2023-09-17 20:36:48',1200,'HP-ProBooks','HP-ProBooks','2023-09-17 20:36:48','Turkey','photo01',38000, true,2,'product-1',true,1);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(2,1,'2023-09-17 20:36:48',1200,'Pantallon','Pantallon','2023-09-17 20:36:48','Turkey','photo02',38000, true,2,'product-2',true,1);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(3,1,'2023-09-17 20:36:48',1200,'Robe','Robe','2023-09-17 20:36:48','Turkey','photo03',38000, true,2,'product-3',true,1);
INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(4,1,'2023-09-17 20:36:48',1200,'Jupe','Jupe','2023-09-17 20:36:48','Turkey','photo04',38000, true,2,'product-4',true,2);
INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(5,1,'2023-09-17 20:36:48',1200,'Sac','Sac','2023-09-17 20:36:48','Turkey','photo05',38000, false,2,'product-5',false,2);
INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(6,1,'2023-09-17 20:36:48',1200,'Ordinateur','Ordinateur','2023-09-17 20:36:48','Turkey','photo06',38000, false,2,'product-6',false,3);
INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(7,1,'2023-09-17 20:36:48',1200,'Parfum','Parfum','2023-09-17 20:36:48','Turkey','photo07',38000, true,2,'product-7',true,4);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(8,1,'2023-09-17 20:36:48',1200,'Calesson','Calesson','2023-09-17 20:36:48','Turkey','photo08',38000, true,2,'product-8',true,5);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(9,1,'2023-09-17 20:36:48',1200,'Chemise','Chemise','2023-09-17 20:36:48','Turkey','photo09',38000, true,2,'product-9',true,3);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(10,1,'2023-09-17 20:36:48',1200,'Culotte','Culotte','2023-09-17 20:36:48','Turkey','photo10',38000, false,2,'product-10',false,6);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(11,1,'2023-09-17 20:36:48',1200,'Armoire','Armoire','2023-09-17 20:36:48','Turkey','photo11',38000, true,2,'product-11',true,3);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(12,1,'2023-09-17 20:36:48',1200,'Table','Table','2023-09-17 20:36:48','Turkey','photo12',38000, true,2,'product-12',true,4);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(13,1,'2023-09-17 20:36:48',1200,'Chaise','Chaise','2023-09-17 20:36:48','Turkey','photo13',38000, true,2,'product-13',true,4);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(14,1,'2023-09-17 20:36:48',1200,'Patte','Patte','2023-09-17 20:36:48','Turkey','photo14',38000, true,2,'product-14',true,4);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(15,1,'2023-09-17 20:36:48',1200,'Air force','Air force','2023-09-17 20:36:48','Turkey','photo15',38000, true,2,'product-15',true,4);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(16,1,'2023-09-17 20:36:48',1200,'Souris','Souris','2023-09-17 20:36:48','Turkey','photo16',38000, true,2,'product-16',true,4);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(17,1,'2023-09-17 20:36:48',1200,'Ecran','Ecran','2023-09-17 20:36:48','Turkey','photo17',38000, true,2,'product-17',true,4);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(18,1,'2023-09-17 20:36:48',1200,'Portable','Portable','2023-09-17 20:36:48','Turkey','photo18',38000, true,2,'product-18',true,4);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(19,1,'2023-09-17 20:36:48',1200,'Samsung','Samsung','2023-09-17 20:36:48','Turkey','photo19',38000, true,2,'product-19',true,4);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
(20,1,'2023-09-17 20:36:48',1200,'Android','Android','2023-09-17 20:36:48','Turkey','photo20',38000, false,2,'product-20',false,4);



/* REGIONS */
INSERT INTO country(id,actif,code,name) VALUES(1,1,'DK','Dakar');
INSERT INTO country(id,actif,code,name) VALUES(2,1,'DL','Diourbel');
INSERT INTO country(id,actif,code,name) VALUES(3,1,'FT','Fatick');
INSERT INTO country(id,actif,code,name) VALUES(4,1,'KF','Kaffrine');
INSERT INTO country(id,actif,code,name) VALUES(5,1,'KL','Kaolack');
INSERT INTO country(id,actif,code,name) VALUES(6,1,'KD','Kolda');
INSERT INTO country(id,actif,code,name) VALUES(7,1,'LG','Louga');
INSERT INTO country(id,actif,code,name) VALUES(8,1,'ST','Saint-Louis');
INSERT INTO country(id,actif,code,name) VALUES(9,1,'SD','Sedhiou');
INSERT INTO country(id,actif,code,name) VALUES(10,1,'TB','Tambacounda');
INSERT INTO country(id,actif,code,name) VALUES(11,1,'TH','Thiès');
INSERT INTO country(id,actif,code,name) VALUES(12,1,'ZG','Ziguinchor');

/* DEPARTEMENTS */
INSERT INTO state(id,actif,name,country_id) VALUES(1,1,'Amitié 1', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(2,1,'Amitié 2', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(3,1,'Amitié 3', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(4,1,'Almadies', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(5,1,'Bambilor', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(6,1,'Bargny', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(7,1,'Bel Air 1', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(8,1,'Bop', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(9,1,'Camberene', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(10,1,'Castor', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(11,1,'Centenaire', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(12,1,'Cité Keur Damel', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(13,1,'Cité Keur Gorgui', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(14,1,'Colobane', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(15,1,'Castor', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(16,1,'Dalifort', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(17,1,'Dakar plateau', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(18,1,'Dalifort', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(19,1,'Derklet', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(20,1,'Diamaguene', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(21,1,'Centre ville Dakar', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(22,1,'Médina',1);
INSERT INTO state(id,actif,name,country_id) VALUES(23,1,'HLM', 1);
INSERT INTO state(id,actif,name,country_id) VALUES(24,1,'Hann-Mariste',1);
INSERT INTO state(id,actif,name,country_id) VALUES(25,1,'Diapeul',1);
INSERT INTO state(id,actif,name,country_id) VALUES(26,1,'Colobanne',1);