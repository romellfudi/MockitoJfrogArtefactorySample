package fudi.freddy.api;

import java.util.List;

/**
 * Created by USUARIO on 9/01/2017.
 */

public interface ListaInterface<T> {
    int ordernar(T objectA, T objectB);
    void listaOrdenada(List<T> listSorted);
    void catchError(Exception e);
}
