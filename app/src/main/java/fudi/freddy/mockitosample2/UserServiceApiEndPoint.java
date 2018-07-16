package fudi.freddy.mockitosample2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USUARIO on 23/02/2017.
 */
public class UserServiceApiEndPoint {
    private final static List<User> map;

    static {
        map = new ArrayList<>(5);
        addUser("o");
        addUser("e");
        addUser("u");
        addUser("i");
        addUser("a");
    }

    private static void addUser(String data) {
        User user = new User(data);
        map.add(user);
    }

    public static List<User> loadPersistendUser() {
        return map;
    }
}
