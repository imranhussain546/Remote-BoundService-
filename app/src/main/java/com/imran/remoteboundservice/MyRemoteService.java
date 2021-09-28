package com.imran.remoteboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MyRemoteService extends Service {
    final Messenger mymessenger = new Messenger(new ReceiveMessageHandler());
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Servieclass", "onBind: " );
        return mymessenger.getBinder();
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e("Servieclass", "onRebind: ");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Servieclass", "onUnbind: " );
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("Servieclass", "onDestroy: " );
        super.onDestroy();
    }

    class ReceiveMessageHandler extends Handler{

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            String receiveMsg = bundle.getString("MSG_RCV");
            Log.e("handlemsg", "handleMessage: " +receiveMsg );
            Toast.makeText(getApplicationContext(), receiveMsg, Toast.LENGTH_SHORT).show();
        }
    }
}