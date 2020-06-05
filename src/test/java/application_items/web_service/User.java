package application_items.web_service;

public class User {

    private final int id;
    private final String username;
    private final String realname;
    private final String password;
    private final String email;

    public User (int id, String username, String realname, String password, String email){
        this.id = id;
        this.username = username;
        this.realname = realname;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRealname() {
        return realname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
