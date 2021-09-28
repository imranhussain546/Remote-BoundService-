package com.imran.remoteboundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.telecom.ConnectionService;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Messenger myservice=null;
    Boolean isConnected=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this,MyRemoteService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void sendmessage(View view) {
        EditText editText= findViewById(R.id.editext);
        Bundle bundle = new Bundle();
        bundle.putString("MSG_RCV",editText.getText().toString());
        Message msg=Message.obtain();
        msg.setData(bundle);

        try {
            myservice.send(msg);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

      ServiceConnection serviceConnection =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myservice =new Messenger(iBinder);
            isConnected=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isConnected=false;
        }
    };
}