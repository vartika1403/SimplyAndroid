package entertainment.simpleDagger;

import dagger.Component;

@Component
public interface CarComponent {

    Car getCar();
    void inject(NewActivity newActivity);
}
