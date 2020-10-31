package entertainment.simpleDagger.dagger;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;
import entertainment.simpleDagger.car.Car;
import entertainment.simpleDagger.NewActivity;

@Component(modules = {WheelsModule.class,PetrolEngineModule.class})
public interface CarComponent {

    Car getCar();
    void inject(NewActivity newActivity);
}
