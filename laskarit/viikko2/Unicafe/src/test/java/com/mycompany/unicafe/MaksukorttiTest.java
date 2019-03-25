package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void OikeaSaldoAlussa() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void RahamaaranKasvatusToimiiOikein() {
        kortti.lataaRahaa(120);
        assertEquals("saldo: 1.30", kortti.toString());
    }
    @Test
    public void RahanOttoToimiiJosSitaOnTarpeeksi() {
        kortti.lataaRahaa(200);
        kortti.otaRahaa(50);
        
        assertEquals("saldo: 1.60", kortti.toString());
    }
    @Test
    public void SaldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(50);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void RahatEivatRiittaneet() {
        assertEquals(false, kortti.otaRahaa(30));
    }
    @Test
    public void RahatRiittivat() {
        assertEquals(true, kortti.otaRahaa(5));
    }
}
