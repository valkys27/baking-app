package com.udacity.backing_app.core.rx;

import rx.Scheduler;

public interface RxSchedulers {

    Scheduler runOnBackground();

    Scheduler io();

    Scheduler compute();

    Scheduler androidThread();

    Scheduler internet() ;
}
