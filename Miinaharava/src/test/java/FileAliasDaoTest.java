/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Alias;
import dao.AliasDao;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.TemporaryFolder;
import dao.FileAliasDao;
import org.junit.Rule;

public class FileAliasDaoTest {

   @Rule 
   public TemporaryFolder testFolder = new TemporaryFolder();

    File aliasFile;
    AliasDao dao;

   @Before
    public void setUp() throws Exception {
        
        aliasFile = testFolder.newFile("testfile_users.txt");

        try (FileWriter file = new FileWriter(aliasFile.getAbsolutePath())) {
            file.write("testertester;Teppo Testaaja\n");
        }

        dao = new FileAliasDao(aliasFile.getAbsolutePath());
    }
    @Test
    public void usersAreReadCorrectlyFromFile() {
        List<Alias> aliases = dao.getAll();
        assertEquals(1, aliases.size());
        Alias alias = aliases.get(0);
        assertEquals("testertester", alias.getUsername());
}

    @Test
    public void nonExistingAliasIsFound() {
        Alias alias = dao.findByAlias("armas");
        assertEquals(null, alias);
    }

    @Test
    public void savedUserIsFound() throws Exception {
        Alias nowAlias = new Alias("hellas");
        dao.create(nowAlias);

        Alias alias = dao.findByAlias("hellas");
        assertEquals("hellas", alias.getUsername());

    }
   
    @After
    public void tearDown() {
        aliasFile.delete();
    }
}
