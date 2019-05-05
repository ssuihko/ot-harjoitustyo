package dao;


import domain.Alias;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import dao.AliasDao;
import java.util.ArrayList;
import java.util.List;

public class FakeAliasDao implements AliasDao {
    List<Alias> aliases = new ArrayList<>();
    
    public FakeAliasDao() {
     aliases.add(new Alias("testerrr"));
    }
    
    @Override
    public Alias findByAlias(String username) {
        return aliases.stream().filter(u->u.getUsername().equals(username)).findFirst().orElse(null);
    }
    @Override 
    public Alias create(Alias alias) {
        aliases.add(alias);
        return alias;
    }
    @Override
    public List<Alias> getAll() {
        return aliases;
    }
}
