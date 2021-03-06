package entertainment.simplyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements MyService.Callbacks{
    private static final String TAG = MainActivity.class.getSimpleName();
    ToggleButton toggleButton;
    ToggleButton toggleIntentService;
    ToggleButton tbStartTask;
    TextView tvServiceState;
    TextView tvServiceOutput;
    Intent serviceIntent;
    Intent intentServiceIntent;
    MyService myService;
    MyIntentService myIntentService;
    int seconds;
    int minutes;
    int hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceIntent = new Intent(MainActivity.this, MyService.class);
        serviceIntent.putExtra("message", "Hi i am MyService");
        intentServiceIntent = new Intent(this, MyIntentService.class);
        intentServiceIntent.putExtra("message", "Hi i am MyIntentService");
        setViewsWidgets();
    }

    private void setViewsWidgets() {
        toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(btListener);
        tbStartTask = (ToggleButton)findViewById(R.id.tbStartServiceTask);
        tbStartTask.setOnClickListener(btListener);
        tvServiceState = (TextView)findViewById(R.id.tvServiceState);
        tvServiceOutput = (TextView)findViewById(R.id.tvServiceOutput);
        toggleIntentService = (ToggleButton)findViewById(R.id.tbStartIntentService);
        toggleIntentService.setOnClickListener(btListener);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Toast.makeText(MainActivity.this, "Service Connected", Toast.LENGTH_SHORT).show();
           // MyService.
            // We've binded to LocalService, cast the IBinder and get LocalService instance
            Log.d(TAG, "onServiceConnected called, ");

            MyService.LocalBinder binder = (MyService.LocalBinder) iBinder;
            myService = binder.getServiceInstance(); //Get instance of your service!
            myService.registerClient(MainActivity.this); //Activity register in the service as client for callabcks!
            tvServiceState.setText("Connected to service...");
            tbStartTask.setEnabled(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconncted called, ");
            Toast.makeText(MainActivity.this, "Service DisConnected", Toast.LENGTH_SHORT).show();

        }
    };


    View.OnClickListener btListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == toggleButton){
                if(toggleButton.isChecked()){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(serviceIntent); //Starting the service
                    } else {
                        startService(serviceIntent);
                    }
                     bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE); //Binding to the service!
                    Toast.makeText(MainActivity.this, "Button checked", Toast.LENGTH_SHORT).show();
                }else{
//                    unbindService(serviceConnection);
                    stopService(serviceIntent);
                    tvServiceState.setText("Service disconnected");
                    Toast.makeText(MainActivity.this, "Button unchecked", Toast.LENGTH_SHORT).show();
                    tbStartTask.setEnabled(false);
                }
            }

            if (v == toggleIntentService) {
                if (toggleIntentService.isChecked()) {
                    startService(intentServiceIntent);
                    Toast.makeText(MainActivity.this,
                            "Intent serviceButton checked", Toast.LENGTH_SHORT).show();
                    bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE); //Binding to the service!
                } else {
                    stopService(intentServiceIntent);
                    Toast.makeText(MainActivity.this, "Intent serviceButton unchecked", Toast.LENGTH_SHORT).show();
                }
            }

            if(v == tbStartTask){
                if(tbStartTask.isChecked()){
                    if (myService != null)
                    myService.startCounter();
                }else{
                    if (myService != null)
                    myService.stopCounter();
                }
            }
        }
    };

    @Override
    public void updateClient(long millis) {
        seconds = (int) (millis / 1000) % 60 ;
        minutes = (int) ((millis / (1000*60)) % 60);
        hours   = (int) ((millis / (1000*60*60)) % 24);

        tvServiceOutput.setText((hours>0 ? String.format("%d:", hours) : "") + ((this.minutes<10 && this.hours > 0)? "0" + String.format("%d:", minutes) :  String.format("%d:", minutes)) + (this.seconds<10 ? "0" + this.seconds: this.seconds));
    }
}
