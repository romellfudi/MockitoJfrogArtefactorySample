package fudi.freddy.mockitosample2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import fudi.freddy.api.ApiController;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    Button save, load;
    EditText editText;

    MainContract.UserAccionsListener userAccionsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (ApiController.Return(true)) {
            setContentView(R.layout.activity_main);
            editText = (EditText) findViewById(R.id.sec);
            userAccionsListener = new MainPresenter(this);
            save = (Button) findViewById(R.id.save);
            load = (Button) findViewById(R.id.load);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userAccionsListener.saveInput(editText.getText().toString());
                }
            });
            load.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userAccionsListener.loadLastText();
                }
            });
//        }
    }

    @Override
    public void clearEdittext() {
        editText.setText("");
    }

    @Override
    public void viewLoadData(String text) {
        editText.setText(text);
    }

    @Override
    public String getData() {
        return editText.getText().toString();
    }
}
