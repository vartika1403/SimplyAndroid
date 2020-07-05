package entertainment.simpleDagger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import javax.inject.Inject;

import entertainment.simpleDagger.car.Car;
import entertainment.simpleDagger.dagger.CarComponent;
import entertainment.simpleDagger.dagger.DaggerCarComponent;
import entertainment.simpleDagger.dagger.PetrolEngineModule;
import entertainment.simplyandroid.R;

public class NewActivity extends AppCompatActivity {
    private static final String TAG = "NewActivity";

    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        CarComponent carComponent = DaggerCarComponent.builder()
                .petrolEngineModule(new PetrolEngineModule(100)).build();
        carComponent.inject(this);
        car.drive();
    }
}
