package com.example.icaro.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm running", Toast.LENGTH_SHORT).show();

        Intent intent1 = new Intent(context, MyNewIntentService.class);
        intent1.putExtra("tipo",intent.getStringExtra("tipo"));
        intent1.putExtra("titulo",intent.getStringExtra("titulo"));

        context.startService(intent1);
    }
}