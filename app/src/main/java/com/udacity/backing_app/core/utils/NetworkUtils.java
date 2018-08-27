package com.udacity.backing_app.core.utils;

import android.content.Context;
import android.net.*;
import rx.Observable;

public class NetworkUtils {

    public static Observable<Boolean> isNetworkAvailableObservable(Context context) {
        return Observable.just(isNetworkAvailable(context));
    }

    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
