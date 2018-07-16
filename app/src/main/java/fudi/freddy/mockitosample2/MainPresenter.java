package fudi.freddy.mockitosample2;

import java.util.List;

import fudi.freddy.mockitosample2.general.Contracts;

/**
 * ${Comments}
 *
 * @author Romell Domínguez
 * @version ${MODULE_VERSION} 23/02/2017
 * @since 1.0
 */
public class MainPresenter implements MainContract.UserAccionsListener {

    private MainContract.View view;
    private RepoService repoService = Contracts.getRepoService();

    private int lastAdd;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void saveInput(String text) {
        lastAdd = repoService.save(new User(text));
        view.clearEdittext();
    }

    @Override
    public void loadLastText() {
        repoService.loadUser(lastAdd, new RepoService.UserServiceCallback<User>() {
            @Override
            public void load(User data) {
                view.viewLoadData(data.getData());
            }
        });
    }

    @Override
    public void pullAllData() {
        repoService.loadUsers(new RepoService.UserServiceCallback<List<User>>() {
            @Override
            public void load(List<User> data) {
                view.viewLoadData(String.valueOf(data.size()));
            }
        });
    }
}
