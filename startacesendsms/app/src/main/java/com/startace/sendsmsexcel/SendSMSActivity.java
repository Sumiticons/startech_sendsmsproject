package com.startace.sendsmsexcel;

/**
 * Created by sumit on 3/22/2017.
 */
import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendSMSActivity extends Activity {

    Button buttonSend;
    EditText textSMS;
    String excel_data;
    String numbers[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendsms);

        buttonSend = (Button) findViewById(R.id.buttonSend);
        textSMS = (EditText) findViewById(R.id.editTextSMS);

        if (getIntent().hasExtra("data")){
            Bundle bundle = getIntent().getBundleExtra("data");
            excel_data = bundle.getString("excel_data");
            numbers= excel_data.split(", *");
        }

        buttonSend.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String phoneNo = excel_data;
                String sms = textSMS.getText().toString();

                try {

                    for(String number : numbers) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(number, null, sms, null, null);
                    }

                    Toast.makeText(getApplicationContext(), "SMS Sent!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });
    }
}