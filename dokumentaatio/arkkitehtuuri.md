## Kansiorakenne

Kansiorakenne on kolmikerroksinen. Kansioon application sisältyy Miinaharavapelin käyttöliittymä sekä Tile -luokan kautta sovelluslogiikkaa, kansiossa domain on sekä pysyväistallennukseen että sovelluslogiikkaan liittyvää koodia. Pakkauksessa dao on taas vain aliasten pysyväistallennukseen liittyvää koodia.

<img src="https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kansiot.png">

## Käyttöliittymä

Käyttöliittymä on näkymä Miinaharavapelistä. Pelin ylänurkasta löytyy myös valikko, jonka kautta pääsee top 10 -näkymään, sekä voittotilanteessa avautuva näkymä aliaksen kirjoittamiseksi tietokantaan. 

Kaikki näkymät on toteutettu [Main.java](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/src/main/java/application/Main.java) -luokassa.

## Sekvenssikaavio

<img src="https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/IMG_2830.jpg" width="500" heigth="550">

## Sovelluslogiikka

Sovelluksen perustoiminnallisuuden muodostavat luokat [Main](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/src/main/java/application/Main.java) ja [Tile](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/src/main/java/application/Tile.java). 

Dataa käsittelevät luokat ovat [Alias](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/src/main/java/domain/Alias.java), [Points](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/src/main/java/domain/Points.java), ja points.txt -tiedostoon tallennettuja pisteitä käsittelevät luokat [HighScoreComparator](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/src/main/java/domain/HighScoreComparator.java) ja [HighScoreManager](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/src/main/java/domain/HighScoreManager.java). 

