DROP TABLE Produits CASCADE CONSTRAINTS;

create sequence seqNumProduit;

CREATE TABLE Produits
(numProduit NUMBER, nomProduit VARCHAR(20), prixHT NUMBER, quantite INTEGER,
CONSTRAINT pk_numProduit PRIMARY KEY (numProduit)) ;

INSERT INTO Produits (codePersonne, nomPersonne, prenomPersonne, agePersonne) VALUES ('1', 'Zétofrais', 'Mélanie',28);
INSERT INTO Produits (codePersonne, nomPersonne, prenomPersonne, agePersonne) VALUES ('2', 'Bricot', 'Juda',46);
INSERT INTO Produits (codePersonne, nomPersonne, prenomPersonne, agePersonne) VALUES ('3', 'Némard', 'Jean',33);
INSERT INTO Produits (codePersonne, nomPersonne, prenomPersonne, agePersonne) VALUES ('4', 'Zeblouze', 'Agathe',NULL);
INSERT INTO Produits (codePersonne, nomPersonne, prenomPersonne, agePersonne) VALUES ('5', 'Ouzy', 'Jacques',12);
INSERT INTO Produits (codePersonne, nomPersonne, prenomPersonne, agePersonne) VALUES ('6', 'Deuf', 'John',NULL);
INSERT INTO Produits (codePersonne, nomPersonne, prenomPersonne, agePersonne) VALUES ('7', 'Titouplin', NULL,0);

COMMIT;

create or replace procedure addProduit(p_nom Produits.nomProduit%TYPE,p_prix Produits.prixHT%TYPE,p_quantite Produits.quantite%TYPE)is
BEGIN
INSERT INTO Produits (numProduit, nomProduit, prixHT, quantite) VALUES (seqNumProduit.nextval,p_nom,p_prix,p_quantite);
end;

create or replace sequence seqNumProduit
start with 1;