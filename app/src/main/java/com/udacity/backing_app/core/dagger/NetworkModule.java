package com.udacity.backing_app.core.dagger;

import android.content.Context;
import dagger.*;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.*;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import java.io.File;

@Module
public class NetworkModule {

    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";
    private static final String OK_HTTP_LOGGING_TAG = "OkHttp";

    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }

    @Provides
    Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    CallAdapter.Factory provideCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();
    }

    @Provides
    Cache provideCache(File file) {
        return new Cache(file, 10 * 10 * 1000);
    }

    @Provides
    File provideCacheFile(Context context) {
        return context.getFilesDir();
    }

    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor(HttpLoggingInterceptor.Logger httpLogger) {
        return new HttpLoggingInterceptor(httpLogger);
    }

    @Provides
    HttpLoggingInterceptor.Logger provideHttpLogger() {
       return message -> Timber.tag(OK_HTTP_LOGGING_TAG).d(message);
    }
}
