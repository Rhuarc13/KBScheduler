package cs246.jared.datastorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class stuff extends AppCompatActivity {

    TextView count;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuff);
        count = (TextView) findViewById(R.id.numberThing);
        number = getPreferences(0).getInt("count", 0);
        count.setText(Integer.toString(number));
    }

    public void onAdvance(View v) {
        String temp = count.getText().toString();
        number = Integer.parseInt(temp);
        number++;
        count.setText(Integer.toString(number));
    }

    public void onSave(View v) {
        SharedPreferences editor = getPreferences(0);
        editor.edit().putInt("count", number).commit();
    }
}
