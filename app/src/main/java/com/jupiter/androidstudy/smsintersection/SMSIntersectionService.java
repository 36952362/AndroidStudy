package com.jupiter.androidstudy.smsintersection;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSIntersectionService extends Service {
    private static final String TAG = SMSIntersectionService.class.getName();

    private SMSReceiver smsReceiver;

    public SMSIntersectionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Toast.makeText(this, "开始拦截短信",Toast.LENGTH_SHORT).show();
        Log.d(TAG, "开始拦截");
        smsReceiver = new SMSReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        intentFilter.setPriority(10000);
        this.registerReceiver(smsReceiver,intentFilter);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        this.unregisterReceiver(smsReceiver);
    }

    public class SMSReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "接受到短信");
            Toast.makeText(context,"接受到短信",Toast.LENGTH_SHORT).show();

            // 解析短信内容
            Object[] objects = (Object[])intent.getExtras().get("pdus");
            for(Object object : objects){
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])object);

                //获取短信发送方号码
                String number = smsMessage.getOriginatingAddress();

                //获取短信内容
                String msg = smsMessage.getMessageBody();

                Toast.makeText(context, "number:" + number + ", msg: " + msg, Toast.LENGTH_LONG).show();

                //对于特定的号码进行拦截，也就是说不在继续广播
                if("123456".equals(number)){
                    Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
                    this.abortBroadcast();
                }
            }
        }
    }
}
