package com.udacity.backing_app.core.dagger;

import com.udacity.backing_app.core.rx.*;
import dagger.*;
import rx.subscriptions.CompositeSubscription;

@Module
public class RxModule {

    @Provides
    RxSchedulers provideRxSchedulers() {
        return new RxSchedulersImpl();
    }

    @Provides
    CompositeSubscription provideSubscription() {
        return new CompositeSubscription();
    }

    @Provides
    RxStates provideRxStates() {
        return new RxStates();
    }
}
