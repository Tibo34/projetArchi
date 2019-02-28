set define off
DROP TABLE Produits CASCADE CONSTRAINTS;
drop table Catalogues CASCADE CONSTRAINTS;
drop table Categories CASCADE constraint;
drop sequence seqNumProduit;
drop sequence seqCatalogue;
drop SEQUENCE seqCategorie;

create sequence seqCategorie
start with 1;

create sequence seqNumProduit
start with 1;

create sequence seqCatalogue
start with 1;


create TABLE Catalogues(
numCatalogue NUMBER,
nomCatalogue VARCHAR(20),
constraint u_catalogue PRIMARY KEY (numCatalogue),
constraint u_nomCatalogue UNIQUE (nomCatalogue)
);

create Table Categories(
numCategorie number,
nomCategorie varchar(20),
tauxTVA number not null,
CONSTRAINT pk_numCategorie PRIMARY KEY (numCategorie),
constraint u_nomCategorie UNIQUE (nomCategorie)
);

CREATE TABLE Produits
(
numProduit NUMBER, nomProduit VARCHAR(20) not null, prixHT NUMBER, quantite INTEGER, numCatalogue number,numCategorie number,
CONSTRAINT pk_numProduit PRIMARY KEY (numProduit),
constraint u_nomProduit UNIQUE (nomProduit),
constraint fk_numCatalogue FOREIGN KEY (numCatalogue) REFERENCES Catalogues(numCatalogue),
constraint fk_numCategorie FOREIGN KEY (numCategorie) REFERENCES Categories(numCategorie)
);


create or replace procedure addProduit(p_nom Produits.nomProduit%TYPE,p_prix Produits.prixHT%TYPE,p_quantite Produits.quantite%TYPE,p_Catalogue Catalogues.numCatalogue%TYPE,p_categorie Categories.numCategorie%TYPE)is
BEGIN
INSERT INTO Produits (numProduit, nomProduit, prixHT, quantite,numCatalogue,numCategorie) VALUES (seqNumProduit.nextval,p_nom,p_prix,p_quantite,p_Catalogue,p_categorie);
end;

create or replace procedure addCatalogue(p_nom Catalogues.nomCatalogue%TYPE)is
begin
insert into Catalogues(numCatalogue,nomCatalogue)values(seqCatalogue.nextval,p_nom);
end;


create or replace procedure addCategorie(p_nom Categories.nomCategorie%TYPE,p_taux Categories.tauxTVA%TYPE)is
begin
insert into Categories(numCategorie,nomCategorie,tauxTVA)values(seqCategorie.nextval,p_nom,p_taux);
end;


create or replace procedure deleteCatalogue(p_nom Catalogues.nomCatalogue%TYPE)is
v_idCatalogue Catalogues.numCatalogue%TYPE;
begin
select numCatalogue into v_idCatalogue
from Catalogues
where nomCatalogue=p_nom;
delete from Produits where numCatalogue=v_idCatalogue;
delete from Catalogues where nomCatalogue=p_nom;
end;


create or replace procedure deleteCategorie(p_nom Categories.nomCategorie%TYPE)is
v_nbProduits number;
begin
select count(*) into v_nbProduits
from Produits 
natural join Categories
where nomCategorie=p_nom;
if v_nbProduits>0 then
	RAISE TOO_MANY_ROWS;
end if;	
delete from Categories where nomCategorie=p_nom;
end;

call addCatalogue('Snack');
call addCategorie('TVANormale',0.2);
call addProduit('Treets', 1.3,5,1,1);
call addProduit('M&Ms', 2.5,5,1,1);
call addProduit('Raider', 1.0,5,1,1);
call addProduit('Smarties', 1.5,5,1,1);
call addProduit('Twix', 1.2,5,1,1);
call addProduit('Mars', 1.1,5,1,1);







