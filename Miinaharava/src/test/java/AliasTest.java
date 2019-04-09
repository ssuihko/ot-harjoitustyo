/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Alias;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AliasTest {

    @Test
    public void nonEqualWhenDifferentType() {
        Alias a = new Alias("tester");
        Object o = new Object();
        assertFalse(a.equals(o));
    }

    @Test
    public void equalWhenSameAlias() {
        Alias aa = new Alias("aslan");
        Alias bb = new Alias("aslan");
        assertTrue(aa.equals(bb));
    }

    @Test
    public void notEqualWhenNotSameAlias() {
        Alias aa = new Alias("aslan");
        Alias bb = new Alias("simba");
        assertFalse(aa.equals(bb));
    }
    @Test
    public void setUserNameWorks() {
        Alias a = new Alias("newnew");
        String name = "newnewnew";
        a.setUsername(name);
        assertEquals("newnewnew", a.getUsername());
        
        
    }
}
