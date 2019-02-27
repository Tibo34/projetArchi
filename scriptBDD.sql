set define off
DROP TABLE Produits CASCADE CONSTRAINTS;
drop table Catalogues CASCADE CONSTRAINTS;

drop sequence seqNumProduit;

create sequence seqNumProduit
start with 1;

drop sequence seqCatalogue;

create sequence seqCatalogue
start with 1;


create TABLE Catalogues(
numCatalogue NUMBER,
nomCatalogue VARCHAR(20),
constraint u_catalogue PRIMARY KEY (numCatalogue)
);

CREATE TABLE Produits
(
numProduit NUMBER, nomProduit VARCHAR(20) not null, prixHT NUMBER, quantite INTEGER, numCatalogue number,
CONSTRAINT pk_numProduit PRIMARY KEY (numProduit),
constraint u_nomProduit UNIQUE (nomProduit),
constraint fk_numCatalogue FOREIGN KEY (numCatalogue) REFERENCES Catalogues(numCatalogue)
);


create or replace procedure addProduit(p_nom Produits.nomProduit%TYPE,p_prix Produits.prixHT%TYPE,p_quantite Produits.quantite%TYPE,p_Catalogue Catalogues.numCatalogue%TYPE)is
BEGIN
INSERT INTO Produits (numProduit, nomProduit, prixHT, quantite,numCatalogue) VALUES (seqNumProduit.nextval,p_nom,p_prix,p_quantite,p_Catalogue);
end;

create or replace procedure addCatalogue(p_nom Catalogues.nomCatalogue%TYPE)is
begin
insert into Catalogues(numCatalogue,nomCatalogue)values(seqCatalogue.nextval,p_nom);
end;

call addCatalogue('Snack');



call addProduit('Treets', 1.3,5,1);
call addProduit('M&Ms', 2.5,5,1);
call addProduit('Raider', 1.0,5,1);
call addProduit('Smarties', 1.5,5,1);
call addProduit('Twix', 1.2,5,1);
call addProduit('Mars', 1.1,5,1);







