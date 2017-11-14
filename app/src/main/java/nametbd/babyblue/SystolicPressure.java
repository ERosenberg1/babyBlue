package nametbd.babyblue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.Random;

public class SystolicPressure extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    PointsGraphSeries<DataPoint> series;
    int lastX = 0;
    Button Restart;

    public void openRefresh(View view) {
        Intent Refresh = new Intent(this, SystolicPressure.class);
        startActivity(Refresh);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systolic_pressure);
        Restart=(Button)findViewById(R.id.Refresh);

        GraphView graph = (GraphView) findViewById(R.id.Sysgraph);
        series = new PointsGraphSeries<>();
        graph.addSeries(series);
        series.setSize(5);
        //create the graph
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setXAxisBoundsManual(true);
        viewport.setMaxX(150);
        viewport.setMinY(0);
        viewport.setMaxY(150);
        viewport.setScrollable(true);

    }
    //real time code with thread?
    @Override
    protected void onResume(){
        super.onResume();
        new Thread(new Runnable(){

            @Override
            public void run(){
                for (int  i = 0; i < 1000; i++){
                    runOnUiThread(new Runnable(){

                        @Override
                        public void run(){
                            addEntry();
                        }
                    });
                    //sleep to slow down the addition of entries
                    try {
                        Thread.sleep(3000);
                    } catch(InterruptedException e) {
                    }
                }

            }
        })
                .start();
    }
    //THIS IS WHERE WE ADD DATA FOR NOW IT WILL BE RANDOM
    private void addEntry(){
        series.appendData(new DataPoint(lastX++, RANDOM.nextDouble() * 50d), false, 10);
    }

}
