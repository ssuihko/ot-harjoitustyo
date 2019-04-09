/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import application.Alias;
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
}
