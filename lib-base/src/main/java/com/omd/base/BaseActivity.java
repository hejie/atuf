package com.omd.base;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class BaseActivity extends Activity {

    public void showToast(final String msg) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

}
