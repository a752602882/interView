package com.example.administrator.interview.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.interview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/18.
 package com.example.administrator.interview.activity;

 import android.content.ComponentName;
 import android.content.Context;
 import android.content.Intent;
 import android.content.ServiceConnection;
 import android.os.Bundle;
 import android.os.IBinder;
 import android.support.annotation.Nullable;
 import android.support.v7.app.AppCompatActivity;
 import android.view.View;
 import android.widget.Button;

 import com.example.administrator.interview.R;
 import com.example.administrator.interview.service.ExampleService;

 import butterknife.BindView;
 import butterknife.ButterKnife;
 import butterknife.OnClick;

 /**
 * Created by Administrator on 2016/11/18.
 */

public class ServiceActivity extends AppCompatActivity {


    @BindView(R.id.bind)
    Button bind;
    @BindView(R.id.unbind)
    Button unbind;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.stop)
    Button stop;


    /** 是否绑定 */
    boolean mIsBound = false;
    ExampleService mBoundService;

    private Intent start_intent;

    private ServiceConnection conn  = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ExampleService mBinder =   ((ExampleService.MyBinder) iBinder).getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBoundService = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_main);
        ButterKnife.bind(this);
       /* start_intent = =new Intent(this,ExampleService.class)*/
        start_intent=new Intent(this,ExampleService.class);
    }

    @OnClick({R.id.bind, R.id.unbind, R.id.start, R.id.stop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bind:
                bindService(new Intent(this, ExampleService.class), conn, Context.BIND_AUTO_CREATE);
                mIsBound = true;
                break;
            case R.id.unbind:
                unbindService(conn);
                mIsBound = false;
                break;
            case R.id.start:
                startService(start_intent);
                break;
            case R.id.stop:
                stopService(start_intent);
                break;
        }
    }
}
