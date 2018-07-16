package fudi.freddy.mockitosample2;

import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ${Comments}
 *
 * @author Romell Domínguez
 * @version ${MODULE_VERSION} 23/02/2017
 * @since 1.0
 */
public interface MainContract {

    interface View {
        void clearEdittext();
        void viewLoadData(String text);
        String getData();
    }

    public abstract class ViewModel implements View{
        ViewModel(AppCompatActivity activity){
            ButterKnife.bind(this,activity);
        }

        @OnClick(R.id.save)
        public void save(){

        }

        @OnClick(R.id.load)
        public void load(){

        }
    }

    interface UserAccionsListener{
        void saveInput(String text);
        void loadLastText();
        void pullAllData();
    }
}
