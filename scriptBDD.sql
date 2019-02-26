set define off
DROP TABLE Produits CASCADE CONSTRAINTS;

drop sequence seqNumProduit;

create sequence seqNumProduit
start with 1;

CREATE TABLE Produits
(numProduit NUMBER, nomProduit VARCHAR(20) not null, prixHT NUMBER, quantite INTEGER,
CONSTRAINT pk_numProduit PRIMARY KEY (numProduit),
constraint u_nomProduit UNIQUE (nomProduit));



create or replace procedure addProduit(p_nom Produits.nomProduit%TYPE,p_prix Produits.prixHT%TYPE,p_quantite Produits.quantite%TYPE)is
BEGIN
INSERT INTO Produits (numProduit, nomProduit, prixHT, quantite) VALUES (seqNumProduit.nextval,p_nom,p_prix,p_quantite);
end;



call addProduit('Treets', 1.3,5);
call addProduit('M&Ms', 2.5,5);
call addProduit('Raider', 1.0,5);
call addProduit('Smarties', 1.5,5);
call addProduit('Twix', 1.2,5);
call addProduit('Mars', 1.1,5);


delete from Produits where nomProduit="Mars";

delete from Produits where nomProduit="M&M\'s";
delete from Produits where nomProduit="Raider";

delete from Produits where nomProduit="Smarties";
delete from Produits where nomProduit="Twix";
delete from Produits where nomProduit="Treets";

create TABLE Catalogue(
numCatalogue NUMBER,
nomCatalogue VARCHAR(20)
constraint u_catalogue PRIMARY KEY (numCatalogue)
);


ALTER TABLE Produits add numCatalogue NUMBER;

update Produits set numCatalogue=1;

insert table Catalogue values(1,"Snack");

ALTER TABLE Produits 
ADD FOREIGN KEY (numCatalogue) REFERENCES Catalogue(numCatalogue);
