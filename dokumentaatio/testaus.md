## Testausdokumentti

### Sovelluslogiikka

Pakkauksen [dao](https://github.com/ssuihko/ot-harjoitustyo/tree/master/Miinaharava/src/main/java/dao) luokkia testaavat testit [FakeAliasDao](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/src/test/java/dao/FakeAliasDao.java) ja [FileAliasDaoTest](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/src/test/java/dao/FileAliasDaoTest.java). 
Pakkauksen [domain](https://github.com/ssuihko/ot-harjoitustyo/tree/master/Miinaharava/src/main/java/domain) luokkia testaavat  [AliasTest](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/src/test/java/domain/AliasTest.java), [HighScoreComparatorTest](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/src/test/java/domain/HighScoreComparatorTest.java), [HighScoreManagerTest](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/src/test/java/domain/HighScoreManagerTest.java) sekä [PointsTest](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/src/test/java/domain/PointsTest.java). 


### Testikattavuus

Testauksen rivikattavuus on 68% ja haaraumakattavuus 83%. 

<img src="https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testikat.png">

### Sovellukseen jääneet ongelmat

Sovellus ei testaa kovin kattavasti luokan [HighScoreManager](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/src/test/java/domain/HighScoreManagerTest.java) toimintaa. 

Top 10 listaan ei voi päästä käyttäjäaliaksella, joka on yli 30 kirjainta pitkä, mutta aliaksen voi antaa tyhjänä.
