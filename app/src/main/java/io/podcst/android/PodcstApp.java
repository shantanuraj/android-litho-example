package io.podcst.android;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.soloader.SoLoader;

/**
 * Created by eve on 19/04/17.
 */

public class PodcstApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SoLoader.init(this, false);
        Fresco.initialize(this);
    }
}
