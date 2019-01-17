DROP TABLE Produits CASCADE CONSTRAINTS;

create or replace sequence seqNumProduit
start with 1;

CREATE TABLE Produits
(numProduit NUMBER, nomProduit VARCHAR(20), prixHT NUMBER, quantite INTEGER,
CONSTRAINT pk_numProduit PRIMARY KEY (numProduit)) ;



create or replace procedure addProduit(p_nom Produits.nomProduit%TYPE,p_prix Produits.prixHT%TYPE,p_quantite Produits.quantite%TYPE)is
BEGIN
INSERT INTO Produits (numProduit, nomProduit, prixHT, quantite) VALUES (seqNumProduit.nextval,p_nom,p_prix,p_quantite);
end;



call addProduit('Treets', 1.3,5);
call addProduit('M&M\'s', 2.5,5);
call addProduit('Raider', 1.0,5);
call addProduit('Smarties', 1.5,5);
call addProduit('Twix', 1.2,5);
call addProduit('Mars', 1.1,5);