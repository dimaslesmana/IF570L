package id.ac.umn.dimas.week10c_41281;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CustomBoundService customBoundService;
    boolean isBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CustomBoundService.CustomLocalBinder binder = (CustomBoundService.CustomLocalBinder) service;

            customBoundService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent serviceIntent = new Intent(this, SimpleIntentService.class);
        startService(serviceIntent);

        Button btnStartService = findViewById(R.id.main_button_startService);
        btnStartService.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CustomService.class);
            startService(intent);
        });

        Intent intent2 = new Intent(this, CustomBoundService.class);
        bindService(intent2, serviceConnection, Context.BIND_AUTO_CREATE);

        Button btnShowTime = findViewById(R.id.main_button_showTime);
        btnShowTime.setOnClickListener(v -> {
            String currentTime = customBoundService.getCurrentTime();

            Toast.makeText(getApplicationContext(), currentTime, Toast.LENGTH_LONG).show();
        });
    }
}