package proyecto1_tbd2g3;

/**
 *
 * @author Rui
 */
public class Cliente {
    private int id;
    private String user;

    public Cliente() {
    }

    public Cliente(int id, String user) {
        this.id = id;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    
}
