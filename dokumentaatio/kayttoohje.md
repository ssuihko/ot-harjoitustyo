# Käyttöohje

Lataa tiedosto [Miinaharava-1.0-SNAPSHOT.jar](https://github.com/ssuihko/ot-harjoitustyo/releases/tag/viikko6)


## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar todoapp.jar
```

## Peli

Sovellus on miinaharava peli. Peli avautuu automaattisesti kun sovellus käynnistetään. Peliruudun ylänurkassa näkyy kolme valikkoa Size, Difficulty ja LeaderBoard. 

<img src="https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Miinaharavataulu.png">

Valikosta Size pelaaja voi säätää kentän kokoa 10x10, 15x15 ja 20x20 kentän välillä.
Valikosta Difficulty pelaaja voi valita asteiden Easy (10%), Medium (15%), Hard (20%), sekä kustomoidun vaikeusasteen välillä. Vaikeusastetta ei voi asettaa alle 5 %. 

<img src="https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Limit.png">

Voittaessaan pelaaja voi asettaa itselleen aliaksen ja nähdä, pääsikö hän 10 parhaan joukkoon suorituksellaan, mutta vain jos hän on voittanut pelissä yli 20% vaikeusasteella.

<img src="https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/youwon.png">

<img src="https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/setalias.png">

Hävitessään pelaaja voi aloittaa peli uudelleen.

<img src="https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/explosion.png">

Sekä aliaksen annettuaan että näytön leaderboard valikosta pelaaja voi nähdä tämänhetkisen highscore -listauksen top 10 napista painamalla. Listauksessa näkyvät pelaajan antama alias sekä se aika, jolla hän on voittanut pelin. Kuten aiemmin mainittu, listaukseen voi päästä vain, jos on pelannut yli 20% vaikeusasteella. 

<img src="https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/top10.png">
