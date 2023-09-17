
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
    (1,1,null,1200,'HP-ProBooks','HP-ProBooks',null,'Turkey','photo01',38000, true,2,'product-1',true,1);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
    (2,1,null,1200,'Pantallon','Pantallon',null,'Turkey','photo02',38000, true,2,'product-2',true,1);

INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
    (3,1,null,1200,'Robe','Robe',null,'Turkey','photo03',38000, true,2,'product-3',true,1);
INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
    (4,1,null,1200,'Jupe','Jupe',null,'Turkey','photo04',38000, true,2,'product-4',true,2);
INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
    (5,1,null,1200,'Sac','Sac',null,'Turkey','photo05',38000, true,2,'product-5',true,2);
INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
    (6,1,null,1200,'Ordinateur','Ordinateur',null,'Turkey','photo06',38000, true,2,'product-6',true,3);
INSERT INTO article(id,actif,create_date,current_price,description,designation,last_up_dated,manufactured,photo,
                    price,promo,quantity,reference,selected,scat_id) VALUES
    (7,1,null,1200,'Parfum','Parfum',null,'Turkey','photo07',38000, true,2,'product-7',true,4);