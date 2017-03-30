package it.englab.androidcourse.justdrink.threads;

import android.os.HandlerThread;

/**
 * Created by spawn on 30/03/17.
 */

public class MyHandlerThread extends HandlerThread {

    public MyHandlerThread(String name) {
        super(name);
    }

    public MyHandlerThread(String name, int priority) {
        super(name, priority);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();



    }
}
