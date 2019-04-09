
package dao;

import java.util.List;
import domain.Alias;

public interface AliasDao {
    
    Alias create(Alias alias) throws Exception;
    Alias findByAlias(String alias);
    List<Alias> getAll();
    
    
}
