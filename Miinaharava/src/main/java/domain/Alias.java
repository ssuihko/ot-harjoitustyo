package domain;

import java.io.Serializable;

public class Alias implements Serializable {

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
    /**
     * 
     * @param obj
     * @return 
     */

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Alias)) {
            return false;
        }

        Alias other = (Alias) obj;
        return username.equals(other.username);
    }

    @Override
    public String toString() {
        return username;
    }
    

}


