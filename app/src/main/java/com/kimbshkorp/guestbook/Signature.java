package com.kimbshkorp.guestbook;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signature extends AppCompatActivity {


    private static final String TAG = Signature.class.getSimpleName();
    EditText nameS, emailS, phoneS, companyS, productS;
    boolean isConnected;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    Button mSave, mClear;
    private String nameString, emailString, companyString, phoneString, productString;

    public boolean isInternetAvailable() {

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        nameS = (EditText) findViewById(R.id.name_field);
        emailS = (EditText) findViewById(R.id.email_field);
        phoneS = (EditText) findViewById(R.id.phone_field);
        companyS = (EditText) findViewById(R.id.company_field);
        productS = (EditText) findViewById(R.id.product_field);
        mSave = (Button) findViewById(R.id.save);
        mClear = (Button) findViewById(R.id.clear);
// Button to open signature panel
        nameString = nameS.getText().toString().trim();
        emailString = emailS.getText().toString().trim();
        phoneString = phoneS.getText().toString().trim();
        companyString = companyS.getText().toString().trim();
        productString = productS.getText().toString().trim();

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isInternetAvailable();
                if (!TextUtils.isEmpty(nameS.getText().toString().trim()) && !TextUtils.isEmpty(phoneS.getText().toString().trim()) && !TextUtils.isEmpty(emailS.getText().toString().trim()) && !TextUtils.isEmpty(companyS.getText().toString().trim())) {
                    if (isConnected) {
                        doAll();
                    } else {
                        Toast.makeText(Signature.this, "failed to Save as no internet connection found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Signature.this, "All the fields are required", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameS.getText().clear();
                emailS.getText().clear();
                phoneS.getText().clear();
                companyS.getText().clear();
                productS.getText().clear();
            }
        });

    }


    private void doAll() {

        addUser(nameS.getText().toString().trim(), emailS.getText().toString().trim(), phoneS.getText().toString().trim(), companyS.getText().toString().trim(), productS.getText().toString().trim());
        sendMail();


        Toast.makeText(Signature.this, "Successfully Saved", Toast.LENGTH_SHORT).show();


    }

    private void sendMail() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Saving data");
        dialog.setMessage("Thanks for your interest in our booth");

        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MailSender sender = new MailSender("xxxx@yyyy.com", "password");
                    sender.sendMail(   nameS.getText().toString().trim()+", Good to see you at ICT 2021",


                            "Hi " + nameS.getText().toString().trim() + ", \n \n" + "	Many thanks for dropping by our booth at ICT 2021. We would like to thank you personally for visiting us.\n 	We hope you enjoyed the event and learned more about our unparalleled solutions for FTTH and Fiber networks.\n	 We noticed that you are interested in " + productS.getText().toString().trim() + ", A Koinonia representative will be in touch with you shortly if you requested more information about this product.\n	 If you need immediate help, please do not hesitate to contact us at: sales@koinoniaenterprises.com.au with any inquiries or further information.\n	At last, As a kind gesture for you, if you place any order within next 3 months, you will get a special discount",
                            "Sales@koinoniaenterprises.com.au",
                            emailS.getText().toString());
                    dialog.dismiss();
                    Intent home = new Intent(Signature.this, MainActivity.class);
                    home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(home);
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }

    private void addUser(String userName, String userMail, String userPhone, String userCompany, String userProduct) {
        // Add a new document with a generated ID
        DatabaseReference current_user_db = db.child("ICT2021").child(userName);
        current_user_db.child("Name").setValue(userName);
        current_user_db.child("Mail").setValue(userMail);
        current_user_db.child("Phone").setValue(userPhone);
        current_user_db.child("Company").setValue(userCompany);
        current_user_db.child("Product").setValue(userProduct);


    }
}

