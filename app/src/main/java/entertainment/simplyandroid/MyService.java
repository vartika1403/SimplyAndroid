package entertainment.simplyandroid;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private final Binder binder = new LocalBinder();
    NotificationManager notificationManager;
    NotificationCompat.Builder mBuilder;
    Callbacks activity;
    private long startTime = 0;
    private long millis = 0;
    private final IBinder mBinder = new LocalBinder();
    Handler handler = new Handler();
    Runnable serviceRunnable = new Runnable() {
        @Override
        public void run() {
            millis = System.currentTimeMillis() - startTime;
            activity.updateClient(millis); //Update Activity (client) by the implementd callback
            handler.postDelayed(this, 1000);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        public MyService getServiceInstance() {
            return MyService.this;
        }
    }

    //Here Activity register to the service as Callbacks client
    public void registerClient(Activity activity){
        this.activity = (Callbacks)activity;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    public void startCounter(){
        startTime = System.currentTimeMillis();
        handler.postDelayed(serviceRunnable, 0);
        Toast.makeText(getApplicationContext(), "Counter started", Toast.LENGTH_SHORT).show();
    }

    public void stopCounter(){
        handler.removeCallbacks(serviceRunnable);
    }


    //callbacks interface for communication with service clients!
    public interface Callbacks{
        public void updateClient(long data);
    }
}
