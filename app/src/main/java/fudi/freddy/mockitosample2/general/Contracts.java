package fudi.freddy.mockitosample2.general;

import fudi.freddy.mockitosample2.RepoService;
import fudi.freddy.mockitosample2.RepoServiceImp;

/**
 * Created by USUARIO on 27/02/2017.
 */

public class Contracts {

    public static RepoService getRepoService(){
        return new RepoServiceImp();
    }
}
