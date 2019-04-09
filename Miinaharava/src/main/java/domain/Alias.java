package domain;

public class Alias {

    private String username;

    public Alias(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String setUsername(String username) {
        return this.username = username;
        
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Alias)) {
            return false;
        }

        Alias other = (Alias) obj;
        return username.equals(other.username);
    }

}


