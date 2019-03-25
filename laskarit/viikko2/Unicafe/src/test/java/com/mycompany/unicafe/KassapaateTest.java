/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
        
    }

    @Test
    public void rahamaaraJaLounaatOikein() {

        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());

    }
    @Test
    public void MaksuRiittaaKassanRahamaaraKasvaaJaVaihtorahanSuuruusOikeat() {
        paate.syoMaukkaasti(500);
        
        assertEquals(100400, paate.kassassaRahaa());
    }
    @Test
    public void MyytyjenLounaidenMaaraKasvaa() {
        paate.syoEdullisesti(260);
        paate.syoMaukkaasti(600);
        
        assertEquals(1, paate.edullisiaLounaitaMyyty());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
        
         assertEquals(100640, paate.kassassaRahaa());
    }
    @Test
    public void MyytyjenLounaidenMaaraEiKasva() {
        paate.syoEdullisesti(100);
        paate.syoMaukkaasti(300);
        
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        
        assertEquals(100000, paate.kassassaRahaa());
    }
    @Test
    public void kortillaTarpeeksiRahaa() {
        assertEquals(true, paate.syoEdullisesti(kortti));
        assertEquals(true, paate.syoMaukkaasti(kortti));
        
        assertEquals(360, kortti.saldo());
    }
    @Test
    public void MyytyjenLounaidenMaaraKasvaaKortilla() {
        
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        
        assertEquals(1, paate.edullisiaLounaitaMyyty());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void RahatEivatRiitaKortilla() {
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        
        assertEquals(false, paate.syoMaukkaasti(kortti));
        assertEquals(false, paate.syoEdullisesti(kortti));
        assertEquals(200, kortti.saldo());
        assertEquals(2, paate.maukkaitaLounaitaMyyty());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        
        
        
    }
    @Test
    public void KassanRahamaaraEiMuutuKorttiostoissa() {
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        
        assertEquals(100000, paate.kassassaRahaa());
    }
    @Test
    public void RahanLatausToimiiOikein() {
        paate.lataaRahaaKortille(kortti, 500);
        
        assertEquals(1500, kortti.saldo());
        assertEquals(100500, paate.kassassaRahaa());
    }
    @Test
    public void EiVoiLadataNegatiivistaSaldoa() {
        paate.lataaRahaaKortille(kortti, -100);
        
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
    }
}
