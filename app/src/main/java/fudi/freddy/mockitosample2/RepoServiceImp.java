package fudi.freddy.mockitosample2;

import java.util.List;

/**
 * Created by USUARIO on 23/02/2017.
 */
public class RepoServiceImp implements RepoService {

    private final List<User> USERS_SERVICE_DATA;

    public RepoServiceImp() {
        USERS_SERVICE_DATA = UserServiceApiEndPoint.loadPersistendUser();
    }

    @Override
    public void loadUsers(UserServiceCallback<List<User>> callback) {
        callback.load(USERS_SERVICE_DATA);
    }

    @Override
    public void loadUser(int index, UserServiceCallback<User> callback) {
        User user = USERS_SERVICE_DATA.get(index - 1);
        callback.load(user);
    }

    @Override
    public int save(User user) {
        USERS_SERVICE_DATA.add(user);
        return USERS_SERVICE_DATA.size();
    }
}
