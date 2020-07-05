package entertainment.simpleDagger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import javax.inject.Inject;

import entertainment.simplyandroid.R;

public class NewActivity extends AppCompatActivity {
    private static final String TAG = "NewActivity";

    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        CarComponent carComponent = DaggerCarComponent.create();
        carComponent.inject(this);
        car.drive();
    }
}
