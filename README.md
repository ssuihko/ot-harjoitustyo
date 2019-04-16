
# Miinaharava

Sovellus on miinaharavapeli. Sovellus ei vaadi varsinaista rekisteröitymistä, mutta pelaajien valitsemien lempinimien avulla pidetään kirjaa heidän parhaista pisteistään Top 10:ssä.

## Dokumentaatio

[vaatimusmäärittely](https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)

[työaikakirjanpito](https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[alustava rakenne](https://github.com/ssuihko/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[release](https://github.com/ssuihko/ot-harjoitustyo/releases)

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


