package entertainment.simpleDagger.car;

import android.util.Log;

import javax.inject.Inject;

import dagger.Provides;

public class PetrolEngine implements Engine {
    private static final String TAG = "Car";

    private int horsePower;

    @Inject
    public PetrolEngine() {

    }

    public PetrolEngine(int horsePower) {
      this.horsePower = horsePower;
    }

    @Override
    public void start() {
        Log.d(TAG, "Petrol Engine started: " + horsePower);
    }
}
