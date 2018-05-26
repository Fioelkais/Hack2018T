package com.example.thibault.tempname;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RiskLevel extends AppCompatActivity {

    int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_level);


        Bundle extras = getIntent().getExtras();
        int risklevel = extras.getInt ("risklevel");
        result = risklevel;

        View goodImage = findViewById(R.id.goodImage);
        View avgImage = findViewById(R.id.avgImage);
        View badImage = findViewById(R.id.badImage);
        View terribleImage = findViewById(R.id.terribleImage);


        goodImage.setVisibility(View.GONE);
        avgImage.setVisibility(View.GONE);
        badImage.setVisibility(View.GONE);
        terribleImage.setVisibility(View.GONE);

        switch (result){
            case 0: goodImage.setVisibility(View.VISIBLE);
                    break;
            case 1: avgImage.setVisibility(View.VISIBLE);
                    break;
            case 2: badImage.setVisibility(View.VISIBLE);
                    break;
            case 3: terribleImage.setVisibility(View.VISIBLE);
                    break;
        }

    }

    public void goToContact(View v){
        Intent intent= new Intent(this, Contact.class);
        startActivity(intent);

    }
}