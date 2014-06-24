package com.lai.test.app;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import org.w3c.dom.Text;

import java.io.DataOutputStream;
import java.lang.Object;


public class MainActivity extends Activity { //implements OnClickListener {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //registerReceiver(SDCardBroadcastReceiver, filter);
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_MOUNTED);
        intentFilter.addDataScheme("file");
        registerReceiver(SDCardBroadcastReceiver, intentFilter);
        TextView txview = (TextView) findViewById(R.id.textView);
        txview.setText(Intent.ACTION_MEDIA_MOUNTED);


       /*// Button showme = (Button) findViewById(R.id.button);
        //showme.setOnClickListener(this);
        KeyguardManager keyguardManager = (KeyguardManager)getSystemService(Activity.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
        lock.disableKeyguard();*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



   /* @Override
    public void onClick(View v) {
        TextView abcd = (TextView) findViewById(R.id.textView);
        if( v.getId() == R.id.button)
        {
            try {
                Process process = Runtime.getRuntime().exec("su");
                DataOutputStream os = new DataOutputStream(process.getOutputStream());
                os.writeBytes("am instrument -w android.support.v7.appcompat.test/android.test.InstrumentationTestRunner;");
                os.flush();
                os.close();
                process.waitFor();
            }
            catch (Throwable e) {e.printStackTrace();}



            abcd.setText("It's working");

        }


    }
    @Override
    protected void onResume(){

        super.onResume();


    }*/

   private final BroadcastReceiver SDCardBroadcastReceiver = new BroadcastReceiver(){

        public void onReceive(Context context, Intent intent) {
           /* TextView txview = (TextView) findViewById(R.id.textView);
            txview.setText(intent.getAction());
            TextView txview2 = (TextView) findViewById(R.id.textView2);
            txview2.setText("hahaha");*/
            if (intent.getAction().equals(Intent.ACTION_MEDIA_MOUNTED)) {

                try {
                    Process process = Runtime.getRuntime().exec("su");
                    DataOutputStream os = new DataOutputStream(process.getOutputStream());
                    os.writeBytes("am instrument -w android.support.v7.appcompat.test/android.test.InstrumentationTestRunner;");
                    os.flush();
                    os.close();
                    process.waitFor();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
              //  txview.setText("HEAR YE! HEAR YE! THE MEDIA HAS BEEN MOUNTED!");
            }
        }
    };
    // IntentFilter filter = new IntentFilter();
    // filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
    // filter.addDataScheme("file");
    // filter.setPriority(999
}



