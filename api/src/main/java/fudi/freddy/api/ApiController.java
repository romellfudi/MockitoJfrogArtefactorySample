package fudi.freddy.api;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.zip.DataFormatException;

/**
 * Created by USUARIO on 27/12/2016.
 */

public class ApiController<T> {

    public static boolean no(boolean bool) {
        return !bool;
    }

    public static boolean Return(boolean bool) {
        return bool;
    }

    public void obtenerListaOrdenada(final Callback callback) {
        try {
            obtenerListaOrdenada_(callback);
        } catch (Exception e) {
            callback.catchError(e);
        }
    }

    private void obtenerListaOrdenada_(final Callback callback) throws Exception {
        Collections.sort(null, new Comparator<T>() {

            @Override
            public int compare(T o1, T o2) {
                return callback.ordernar(o1, o2);
            }
        });
        callback.listaOrdenada(null);

//        ...
        throw new ParseException("Cadena invalida",12);

//        ...
    }

    public static abstract class Callback<T> implements ListaInterface {
    }
}
