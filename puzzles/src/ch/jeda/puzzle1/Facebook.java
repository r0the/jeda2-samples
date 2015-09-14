package ch.jeda.puzzle1;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;
// ------------------------------------------------------------------------

public class Facebook extends Program {

    private User[] users;

    @Override
    public void run() {
        users = new User[2000000000];
        users[0] = new User("zuck", "Mark Zuckerberg", "1234");
        users[1] = new User("pchan", "Priscilla Chan", "5678");
        // ...
        login("hacker", "1234");
        login("zuck", "bla");
        login("zuck", "1234");
    }

// ------------------------------------------------------------------------

    void login(String login, String password) {
        User user = findUser(login);
        if (user == null) {
            write("Unbekannter Benutzer: ", login, "\n");
            return;
        }

        if (user.checkPassword(password)) {
            write("Willkommen ", user.getName(), "\n");
        }
        else {
            write("Haben sie ihr Passwort vergessen?\n");
        }
    }

// ------------------------------------------------------------------------

    User findUser(final String login) {
        int i = 0;
        while (i < users.length) {
            if (users[i].getLogin().equals(login)) {
                return users[i];
            }
            i = i + 1;
        }

        return null;
    }

// ------------------------------------------------------------------------

}
