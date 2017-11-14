package nametbd.babyblue;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Room101 extends Activity {

    TextView PulseValue;
    TextView TemperatureValue;
    TextView SystolicValue; //this if for PressVal1
    TextView DiastolicValue; //this is for PressVal2
    TextView O2Value;
    TextView RespirationValue;
    Button Start;
    Button IV1;
    public static List<Double> UpstreamPressure = new ArrayList<Double>();
    public Set<Double> Ups = new HashSet<>();
    public static List<Double> VolumeInfused = new ArrayList<Double>();
    public Set<Double> Vol = new HashSet<>();
    public static List<Double> FlowRate = new ArrayList<Double>();
    public Set<Double> Flow = new HashSet<>();
    public static List<Double> SecondsRTS = new ArrayList<Double>();
    public Set<Double> Sec = new HashSet<>();
    public static List<Double> DownstreamPressure = new ArrayList<Double>();
    public Set<Double> Down = new HashSet<>();
    File sdcard = Environment.getExternalStorageDirectory();
    File file = new File(sdcard, "test.txt");


    public void openInfusion1(View view) {
        Intent Infusion1 = new Intent(Room101.this, Graph1.class);
        startActivity(Infusion1);

    }

    public void openSysPressure(View view) {
        Intent SysPressure = new Intent(this, SystolicPressure.class);
        startActivity(SysPressure);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room101);
        PulseValue = (TextView) findViewById(R.id.PulseVal);
        TemperatureValue = (TextView) findViewById(R.id.TempVal);
        SystolicValue = (TextView) findViewById(R.id.PressVal1);
        DiastolicValue = (TextView) findViewById(R.id.PressVal2);
        O2Value = (TextView) findViewById(R.id.o2val);
        RespirationValue = (TextView) findViewById(R.id.RespVal);
        Start = (Button) findViewById(R.id.start);
        IV1 = (Button) findViewById(R.id.IV1);

        Start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Client client = new Client();
                client.start();
            }
        });

    }

    private class Client extends Thread {
        String IPaddress = "192.168.0.108";
        int portnumber = 60004;

        @Override
        public void run() {
            Socket socket = null;

            try {
                socket = new Socket(IPaddress, portnumber);
                File file = new File(
                        Environment.getExternalStorageDirectory(),
                        "test.txt");
                byte[] bytes = new byte[1024];
                while(socket!=null)
                {
                    InputStream is = socket.getInputStream();
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    int bytesRead = is.read(bytes, 0, bytes.length);
                    bos.write(bytes, 0, bytesRead);
                    bos.close();
                    readvalue();
                    simplify();

                    Room101.this.runOnUiThread(new Runnable() {

                        @Override//check
                        public void run() {
                            for (int i = 0; i < SecondsRTS.size(); i++) {
                                SystolicValue.setText(Double.toString(SecondsRTS.get(i)));
                            }
                        }
                    });
                }

            } catch (IOException e) {

                e.printStackTrace();

                final String eMsg = "Something wrong: " + e.getMessage();
                Room101.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(Room101.this,
                                eMsg,
                                Toast.LENGTH_LONG).show();
                    }
                });
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                final String Msg = "Value was not sent";
                Room101.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(Room101.this,
                                Msg,
                                Toast.LENGTH_LONG).show();
                    }
                });
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        }

        public void readvalue() {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] result = line.split(",");
//                    double[] ary = new double[result.length];
                    //string has 10 values to splice - takes values, splices and converts to string
                    for (int n = 0; n < 10; n++) {
                        result[3] = result[3].replace("\"", "");
                        UpstreamPressure.add(Double.parseDouble(result[3]));
                        result[4] = result[4].replace("\"", "");
                        VolumeInfused.add(Double.parseDouble(result[4]));
                        result[5] = result[5].replace("\"", "");
                        FlowRate.add(Double.parseDouble(result[5]));
                        result[6] = result[6].replace("\"", "");
                        SecondsRTS.add(Double.parseDouble(result[6]));
                        result[8] = result[8].replace("\"", "");
                        DownstreamPressure.add(Double.parseDouble(result[8]));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                IV1.setText("IV 1: " + result[5] + " ml/hr");
                            }
                        });
                    }

                }
                br.close();
            } catch (IOException e) {
            }
        }
        public void simplify(){
            VolumeInfused.removeAll(Arrays.asList(Double.valueOf(0.0)));
//            Vol.addAll(VolumeInfused);
//            VolumeInfused.clear();
//            VolumeInfused.addAll(Vol);

            UpstreamPressure.removeAll(Arrays.asList(Double.valueOf(0.0)));
//            Ups.addAll(UpstreamPressure);
//            UpstreamPressure.clear();
//            UpstreamPressure.addAll(Ups);

            DownstreamPressure.removeAll(Arrays.asList(Double.valueOf(0.0)));
//            Down.addAll(DownstreamPressure);
//            DownstreamPressure.clear();
//            DownstreamPressure.addAll(Down);

            VolumeInfused.removeAll(Arrays.asList(Double.valueOf(0.0)));
//            Vol.addAll(VolumeInfused);
//            VolumeInfused.clear();
//            VolumeInfused.addAll(Vol);

            FlowRate.removeAll(Arrays.asList(Double.valueOf(0.0)));
//            Flow.addAll(FlowRate);
//            FlowRate.clear();
//            FlowRate.addAll(Flow);

            SecondsRTS.removeAll(Arrays.asList(Double.valueOf(0.0)));
            Sec.addAll(SecondsRTS);
            SecondsRTS.clear();
            SecondsRTS.addAll(Sec);
            Collections.sort(SecondsRTS);
        }
    }
}








