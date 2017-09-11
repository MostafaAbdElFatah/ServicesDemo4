package com.mostafaabdel_fatah.servicesdemo4;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import android.os.Messenger;
import android.support.annotation.Nullable;
import android.widget.Toast;


public class MyService extends Service {

    static final int Job_1 = 1;
    static final int Job_2 = 2;
    Messenger messenger = new Messenger(new IcomingHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "Service Binding...", Toast.LENGTH_SHORT).show();
        return messenger.getBinder();
    }

    class IcomingHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case Job_1:
                    Toast.makeText(getApplicationContext(), "Hello from Job 1", Toast.LENGTH_SHORT).show();
                    break;
                case Job_2:
                    Toast.makeText(getApplicationContext(), "Hello from Job 2", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
