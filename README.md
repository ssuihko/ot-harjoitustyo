
# Miinaharava

Sovellus on miinaharavapeli. Sovellus ei vaadi varsinaista rekisteröitymistä, mutta pelaajien valitsemien lempinimien avulla pidetään kirjaa heidän parhaista pisteistään Top 10:ssä.

## Dokumentaatio

[vaatimusmäärittely](https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)

[työaikakirjanpito](https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[arkkitehtuurikuvaus](https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[käyttöohje](https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[testaustiedosto](https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

## Releaset

[viikko 5](https://github.com/ssuihko/ot-harjoitustyo/releases)

[viikko 6](https://github.com/ssuihko/ot-harjoitustyo/releases/tag/viikko6)

## Komentorivikomennot

### Testaus


Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```
Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_


### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/ssuihko/ot-harjoitustyo/blob/master/Miinaharava/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Miinaharava-1.0-SNAPSHOT.jar_


