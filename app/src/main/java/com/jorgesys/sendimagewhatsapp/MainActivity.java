package com.jorgesys.sendimagewhatsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.btnWhatsApp)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*The number must be exactly as is stored in your contacts, without "+" prefix!.
                * Your image must be located into the external storage, you can modify the path.*/
                sendImageWhatsApp("1234512430903", "cats.jpg");
            }
        });


    }


    private void sendImageWhatsApp(String phoneNumber, String imageName) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory() + "/" + imageName));
            intent.putExtra("jid", phoneNumber + "@s.whatsapp.net"); //phone number without "+" prefix!
            intent.setPackage("com.whatsapp");
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "Whatsapp is not installed.", Toast.LENGTH_LONG).show();
        }
    }
}
