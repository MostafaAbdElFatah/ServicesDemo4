package com.mostafaabdel_fatah.servicesdemo4;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Boolean isBind = false;
    Messenger messenger = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BindService_btnClicked(View view){
        Intent intent = new Intent(MainActivity.this,MyService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void SayHello_btnClicked(View view) {
        String text = ((Button)view).getText().toString();
        if (isBind){
            if (text.equals("Say Hello")){
                Message message = Message.obtain(null,MyService.Job_1,0,0,0);
                try {
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }else if (text.equals("Say Hello Again")) {
                Message message = Message.obtain(null, MyService.Job_2, 0, 0, 0);
                try {
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }else {
            Toast.makeText(getApplicationContext(), "Bind Service  First...", Toast.LENGTH_SHORT).show();
        }
    }
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            messenger = new Messenger(iBinder);
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBind = false;
            messenger = null;
        }
    };

    @Override
    protected void onStop() {
        isBind = false;
        messenger = null;
        unbindService(serviceConnection);
        super.onStop();
    }
}
