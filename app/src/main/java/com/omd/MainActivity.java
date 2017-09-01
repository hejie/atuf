package com.omd;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.omd.base.BaseActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToast("更新1");
    }

    //打开远程bundle
    public void remote(View view) {
        Intent intent = new Intent();
        intent.setClassName(view.getContext(), "com.omd.login.LoginActivity");
        startActivity(intent);
    }

    //打开本地bundle
    public void local(View view) {
        Intent intent = new Intent();
        intent.setClassName(view.getContext(), "com.omd.home.HomeActivity");
        startActivity(intent);
    }

    //更新补丁
    public void update(View view) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Updater.update(getBaseContext());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
//                Toast.makeText(MainActivity.this, "更新完成，请重启", Toast.LENGTH_LONG).show();
            }
        }.execute();
    }


}
