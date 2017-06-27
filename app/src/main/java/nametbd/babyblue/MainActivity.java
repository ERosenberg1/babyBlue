package nametbd.babyblue;

import java.util.Random;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import static nametbd.babyblue.R.id.fab;

public class MainActivity extends AppCompatActivity {

    TextView PulseValue;
    TextView TemperatureValue;
    TextView SystolicValue; //this if for PressVal1
    TextView DiastolicValue; //this is for PressVal2
    TextView O2Value;
    TextView RespirationValue;
    Button GenRan;
    Random Number;
    int Rnumber;
    //Private Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PulseValue = (TextView) findViewById(R.id.PulseVal);
        TemperatureValue = (TextView) findViewById(R.id.TempVal);
        SystolicValue = (TextView) findViewById(R.id.PressVal1);
        DiastolicValue = (TextView) findViewById(R.id.PressVal2);
        O2Value = (TextView) findViewById(R.id.o2val);
        RespirationValue = (TextView) findViewById(R.id.RespVal);
        GenRan = (Button) findViewById(R.id.start);
        GenRan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Number = new Random();
                Rnumber = Number.nextInt(100);
                if (Rnumber <= 10) {
                    PulseValue.setText(Integer.toString(40));
                    TemperatureValue.setText(Double.toString(98.7));
                    SystolicValue.setText(Integer.toString(139));
                    DiastolicValue.setText(Integer.toString(61));
                    O2Value.setText(Integer.toString(50));
                    RespirationValue.setText(Integer.toString(60));
                } else if (Rnumber <= 20 && Rnumber > 10) {
                    PulseValue.setText(Integer.toString(45));
                    TemperatureValue.setText(Double.toString(98.2));
                    SystolicValue.setText(Integer.toString(135));
                    DiastolicValue.setText(Integer.toString(81));
                    O2Value.setText(Integer.toString(50));
                    RespirationValue.setText(Integer.toString(60));
                } else if (Rnumber <= 30 && Rnumber > 20) {
                    PulseValue.setText(Integer.toString(47));
                    TemperatureValue.setText(Double.toString(97.4));
                    SystolicValue.setText(Integer.toString(120));
                    DiastolicValue.setText(Integer.toString(101));
                    O2Value.setText(Integer.toString(50));
                    RespirationValue.setText(Integer.toString(60));
                } else if (Rnumber <= 40 && Rnumber > 30) {
                    PulseValue.setText(Integer.toString(55));
                    TemperatureValue.setText(Double.toString(97.1));
                    SystolicValue.setText(Integer.toString(123));
                    DiastolicValue.setText(Integer.toString(62));
                    O2Value.setText(Integer.toString(50));
                    RespirationValue.setText(Integer.toString(60));
                } else if (Rnumber <= 50 && Rnumber > 40) {
                    PulseValue.setText(Integer.toString(57));
                    TemperatureValue.setText(Double.toString(96.1));
                    SystolicValue.setText(Integer.toString(98));
                    DiastolicValue.setText(Integer.toString(83));
                    O2Value.setText(Integer.toString(50));
                    RespirationValue.setText(Integer.toString(60));
                } else if (Rnumber <= 60 && Rnumber > 50) {
                    PulseValue.setText(Integer.toString(72));
                    TemperatureValue.setText(Double.toString(99.2));
                    SystolicValue.setText(Integer.toString(102));
                    DiastolicValue.setText(Integer.toString(85));
                    O2Value.setText(Integer.toString(50));
                    RespirationValue.setText(Integer.toString(60));
                } else if (Rnumber <= 70 && Rnumber > 60) {
                    PulseValue.setText(Integer.toString(61));
                    TemperatureValue.setText(Double.toString(99.8));
                    SystolicValue.setText(Integer.toString(119));
                    DiastolicValue.setText(Integer.toString(80));
                    O2Value.setText(Integer.toString(50));
                    RespirationValue.setText(Integer.toString(60));
                } else if (Rnumber <= 80 && Rnumber > 70) {
                    PulseValue.setText(Integer.toString(58));
                    TemperatureValue.setText(Double.toString(94.2));
                    SystolicValue.setText(Integer.toString(123));
                    DiastolicValue.setText(Integer.toString(74));
                    O2Value.setText(Integer.toString(50));
                    RespirationValue.setText(Integer.toString(60));
                } else if (Rnumber <= 90 && Rnumber > 80) {
                    PulseValue.setText(Integer.toString(69));
                    TemperatureValue.setText(Double.toString(95.2));
                    SystolicValue.setText(Integer.toString(128));
                    DiastolicValue.setText(Integer.toString(68));
                    O2Value.setText(Integer.toString(50));
                    RespirationValue.setText(Integer.toString(60));
                } else {
                    PulseValue.setText(Integer.toString(66));
                    TemperatureValue.setText(Double.toString(96.4));
                    SystolicValue.setText(Integer.toString(86));
                    DiastolicValue.setText(Integer.toString(86));
                    O2Value.setText(Integer.toString(50));
                    RespirationValue.setText(Integer.toString(60));
                }
            }

        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
