package com.udacity.backing_app.core.dagger;

import com.udacity.backing_app.core.App;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        WidgetBuilderModule.class,
        AppModule.class,
        ApiModule.class,
        NetworkModule.class,
        RxModule.class})
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {}
}
