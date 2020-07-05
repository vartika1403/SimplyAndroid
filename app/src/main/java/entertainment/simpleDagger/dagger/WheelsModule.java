package entertainment.simpleDagger.dagger;

import dagger.Module;
import dagger.Provides;
import entertainment.simpleDagger.car.Rims;
import entertainment.simpleDagger.car.Tires;
import entertainment.simpleDagger.car.Wheels;

@Module
public class WheelsModule {

    @Provides
    static Rims provideRims() {
        return new Rims();
    }

    @Provides
    static Tires provideTires() {
        Tires tires = new Tires();
        tires.inflate();
        return tires;
    }

    @Provides
    static Wheels provideWheels(Rims rims, Tires tires) {
        return new Wheels(rims, tires);
    }
}
