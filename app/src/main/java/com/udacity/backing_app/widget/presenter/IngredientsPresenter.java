package com.udacity.backing_app.widget.presenter;

import com.udacity.backing_app.core.presenter.Presenter;
import rx.Subscription;

public interface IngredientsPresenter extends Presenter {
    void setWidget(int appWidgetId);
    void deleteWidget(int appWidgetId);
}
