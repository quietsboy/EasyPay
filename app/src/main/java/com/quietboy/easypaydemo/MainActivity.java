package com.quietboy.easypaydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.quietboy.easypay.AlipayUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlipayUtils.newInstance(this).pay("").setPayListener(new AlipayUtils.PayListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onFailure(String message) {

            }
        });
    }
}
