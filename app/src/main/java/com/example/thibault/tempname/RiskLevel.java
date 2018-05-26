package com.example.thibault.tempname;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RiskLevel extends AppCompatActivity {

    int result = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_level);

        View good_image = findViewById(R.id.goodImage);

        if (result == 0)
        {
            good_image.setVisibility(View.VISIBLE);
        }
        else
        {
            good_image.setVisibility(View.GONE);
        }


        //TODO Display adapted risk level from activity FIRST ( Thibault: I ll take care

    }
}