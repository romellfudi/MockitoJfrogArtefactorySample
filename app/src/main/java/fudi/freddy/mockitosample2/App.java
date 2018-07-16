package fudi.freddy.mockitosample2;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by USUARIO on 29/12/2016.
 */

public class App extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context= getApplicationContext();
    }

    public static SharedPreferences getSharePreference() {
        return context.getSharedPreferences(App.class.getName(), Context.MODE_PRIVATE);
    }
}
