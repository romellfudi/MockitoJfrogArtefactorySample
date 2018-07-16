package fudi.freddy.mockitosample2;

import java.util.List;

/**
 * Created by USUARIO on 23/02/2017.
 */
public interface RepoService {
    interface UserServiceCallback<T> {
        void load(T data);
    }

    void loadUsers(UserServiceCallback<List<User>> usuarios);

    void loadUser(int id, UserServiceCallback<User> usuario);

    int save(User user);
}
