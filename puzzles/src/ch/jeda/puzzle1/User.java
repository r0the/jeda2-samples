package ch.jeda.puzzle1;

// ------------------------------------------------------------------------

public class User {

    private String login;
    private String name;
    private String password;

    public User(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
    }

// ------------------------------------------------------------------------

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

// ------------------------------------------------------------------------

}
