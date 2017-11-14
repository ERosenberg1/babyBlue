package nametbd.babyblue;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Graph1 extends Activity {

    PointsGraphSeries<DataPoint> series;
    int x = 0;
    private List<Double> UpstreamPressure = Room101.UpstreamPressure;
    private List<Double> VolumeInfused = Room101.VolumeInfused;
    private List<Double> FlowRate = Room101.FlowRate;
    private List<Double> SecondsRTS = Room101.SecondsRTS;
    private List<Double> DownstreamPressure = Room101.DownstreamPressure;
    Double[] Downstream = DownstreamPressure.toArray(new Double[DownstreamPressure.size()]);
    Double[] Flow = FlowRate.toArray(new Double[FlowRate.size()]);
    Double[] Seconds = SecondsRTS.toArray(new Double[SecondsRTS.size()]);
    Double[] VolumeInfused2 = VolumeInfused.toArray(new Double[VolumeInfused.size()]);
    Double[] Upstream = UpstreamPressure.toArray(new Double[UpstreamPressure.size()]);
    TextView secondsvalue;
    GraphView graph;
    public int seconds;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph1);

        Button start = (Button) findViewById(R.id.start);
        GraphView graph = (GraphView) findViewById(R.id.PressureGraph);
        secondsvalue = (TextView) findViewById(R.id.secondsval);
        series = new PointsGraphSeries<>();
        series.setSize(5);
        graph.addSeries(series);
        //create the graph
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setXAxisBoundsManual(true);
        graph.getGridLabelRenderer().setNumHorizontalLabels(4);
        viewport.setMinY(0);
        viewport.setMaxY(40);
        viewport.setScrollable(true);

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Run beginrun = new Run();
                beginrun.start();
            }
        });
    }

    private class Run extends Thread {
        public void run() {
            Collections.sort(SecondsRTS);
            DataPoint[] datapoints = new DataPoint[SecondsRTS.size()];
            if (SecondsRTS.size() > 0) {
                try {
                    for (x = 0; x < SecondsRTS.size(); x++) {
                        datapoints[x] = new DataPoint(SecondsRTS.get(x), DownstreamPressure.get(x));
                        series.appendData(datapoints[x], true, 5);
                        Thread.sleep(4000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Graph1.this.runOnUiThread(new Runnable() {
                    public void run() {
                        secondsvalue.setText("No Values to Graph");
                    }
                });
            }
        }
    }
}


