package nametbd.babyblue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.graphics.Color;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button P1 = (Button)findViewById(R.id.Patient1); Button P2 = (Button)findViewById(R.id.Patient2);
        Button P3 = (Button)findViewById(R.id.Patient3); Button P4 = (Button)findViewById(R.id.Patient4);
        Button P5 = (Button)findViewById(R.id.Patient5);
        P1. setBackgroundColor(Color.rgb(9,169,15));
        P2. setBackgroundColor(Color.rgb(198,53,14));
        P3. setBackgroundColor(Color.rgb(216,216,17));
        P4. setBackgroundColor(Color.rgb(9,169,15));
        P5. setBackgroundColor(Color.rgb(9,169,15));
    }
    public void openRoom101(View view) {
        Intent room101 = new Intent(this, Room101.class);
        startActivity(room101);
    }

}
;