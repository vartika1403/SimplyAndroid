package entertainment.simpleDagger.dagger;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import entertainment.simpleDagger.car.Engine;
import entertainment.simpleDagger.car.PetrolEngine;

@Module
public  class PetrolEngineModule {
    private int horsePower;

    public PetrolEngineModule(int horsePower) {
        this.horsePower = horsePower;
    }


    @Provides
    Engine providesEngine(PetrolEngine petrolEngine) {
        return new PetrolEngine(horsePower);
    }
}
