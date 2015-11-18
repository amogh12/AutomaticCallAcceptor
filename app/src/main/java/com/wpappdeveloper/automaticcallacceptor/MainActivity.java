package com.wpappdeveloper.automaticcallacceptor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    TextView tVProximity;
    private static final String tag = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void setDetectEnable(boolean enable) {

        Intent intent = new Intent(this, CallDetectService.class);
        if(enable) {
            startService(intent);
            Log.i(tag, "Starting service");
        } else {
            stopService(intent);
            Log.i(tag, "Stopping service");
        }
    }

    public void detectToggleButtonClick(View v) {
        setDetectEnable(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
