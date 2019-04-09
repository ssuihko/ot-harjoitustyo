
package dao;


import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import application.Alias;

public class FileAliasDao implements AliasDao{
    private List<Alias> aliases;
    private String file;

    public FileAliasDao(String file) throws Exception {
        aliases = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while(reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                Alias a = new Alias(parts[0]);
                aliases.add(a);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for(Alias al : aliases) {
                writer.write(al.getUsername() + ";" + "\n");
            }
        }
    }
    @Override
    public List<Alias> getAll() {
        return aliases;
    }
    @Override
    public Alias findByAlias(String username) {
        return aliases.stream()
                .filter(u->u.getUsername()
                .equals(username))
                .findFirst()
                .orElse(null);
    }
    @Override
    public Alias create(Alias alias) throws Exception {
        aliases.add(alias);
        save();
        return alias;
    }
    
    
    
}
