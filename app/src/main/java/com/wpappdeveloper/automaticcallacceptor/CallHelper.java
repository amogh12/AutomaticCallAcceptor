package com.wpappdeveloper.automaticcallacceptor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by aphadke on 6/9/2015.
 */
public class CallHelper {
    private Context ctx;
    private TelephonyManager tm;
    private CallStateListener callStateListener;
    private OutgoingReceiver outgoingReceiver;
    private ScreenReceiver screenReceiver;

    public CallHelper(Context ctx) {
        this.ctx = ctx;

        callStateListener = new CallStateListener();
        outgoingReceiver = new OutgoingReceiver();
        screenReceiver = new ScreenReceiver();
    }

    public void start() {
        tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE);

//        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL);
//        ctx.registerReceiver(outgoingReceiver, intentFilter);
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
        ctx.registerReceiver(screenReceiver, intentFilter);
    }

    public void stop() {
        ctx.unregisterReceiver(outgoingReceiver);
    }

    //listener to detect incoming calls
    private class CallStateListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    // called when someone is ringing to this phone

                    Toast.makeText(ctx,
                            "Incoming: " + incomingNumber,
                            Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    private class OutgoingReceiver extends BroadcastReceiver {

        public OutgoingReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Toast.makeText(ctx,
                    "Outgoing: " + number,
                    Toast.LENGTH_LONG).show();
        }
    }

    private class ScreenReceiver extends  BroadcastReceiver {

        public ScreenReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Toast.makeText(ctx,
                        "Screen ON ",
                        Toast.LENGTH_LONG).show();
            }

            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Toast.makeText(ctx,
                        "Screen OFF ",
                        Toast.LENGTH_LONG).show();
            }

            if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
                Toast.makeText(ctx,
                        "Screen Unlocked ",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}

