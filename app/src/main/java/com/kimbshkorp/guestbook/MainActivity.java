package com.kimbshkorp.guestbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Button to open signature panel
        Button btn_get_sign = (Button) findViewById(R.id.signature);


        btn_get_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Function call for Digital Signature
                Intent intent = new Intent(MainActivity.this, Signature.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_middle, R.anim.to_middle);

            }
        });


    }


}