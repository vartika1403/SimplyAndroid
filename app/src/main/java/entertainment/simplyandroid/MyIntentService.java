package entertainment.simplyandroid;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {
    private static final String TAG = MyIntentService.class.getSimpleName();
    private final IBinder mBinder = new MyIntentService.LocalBinder();
    private Handler handler;

    public MyIntentService() {
        super("MyIntentService");
    }

    public class LocalBinder extends Binder {
        public MyIntentService getServiceInstance() {
            return MyIntentService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate called," + Thread.currentThread());

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind called, " + intent.getAction());
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d(TAG, "onStartCommand called, " + startId + ", flag, " + flags);
        return START_NOT_STICKY;
    }


    @Override
    public void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent called, " + intent.getAction());
        handler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                super.handleMessage(message);
                Log.d(TAG, "onCreate Message called," + message.arg1);
            }
        };
      //  super.onHandleIntent(intent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // handler.removeMessages();
        Log.d(TAG, "onDestroy called, ");
    }
}
