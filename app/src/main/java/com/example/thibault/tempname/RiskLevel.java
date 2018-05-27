package com.example.thibault.tempname;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RiskLevel extends AppCompatActivity {

    int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_level);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.activity_risk_level);


        Bundle extras = getIntent().getExtras();
        int risklevel = extras.getInt ("risklevel");
        int size=extras.getInt("size");
        result = risklevel;

        /*View goodImage = findViewById(R.id.goodImage);
        View avgImage = findViewById(R.id.avgImage);
        View badImage = findViewById(R.id.badImage);
        View terribleImage = findViewById(R.id.terribleImage);


        goodImage.setVisibility(View.GONE);
        avgImage.setVisibility(View.GONE);
        badImage.setVisibility(View.GONE);
        terribleImage.setVisibility(View.GONE);*/

        TextView info=(TextView) findViewById(R.id.textView);

        switch (result){
            case 0: //goodImage.setVisibility(View.VISIBLE);
                    layout.setBackgroundResource(R.drawable.happy_cow);
                    info.setText("Congratulations, you're an amazing farmer!");
                    break;
            case 1: //avgImage.setVisibility(View.VISIBLE);
                layout.setBackgroundResource(R.drawable.avg_cow);
                int nbr=size/50;
                int money=nbr*300;
                int time=nbr*4;
                info.setText("You could save "+money+" $ and "+time+" hours by getting to industry standards");
                    break;
            case 2: //badImage.setVisibility(View.VISIBLE);
                layout.setBackgroundResource(R.drawable.sad_cow);
                nbr=size/25;
                money=nbr*300;
                time=nbr*4;
                info.setText("You could save "+money+" $ and "+time+" hours by getting to industry standards");
                    break;
            case 3: //terribleImage.setVisibility(View.VISIBLE);
                nbr=size/12;
                money=nbr*300;
                time=nbr*4;
                info.setText("You could save "+money+" $ and "+time+" hours by getting to industry standards");
                layout.setBackgroundResource(R.drawable.terrible_cow);
                    break;
        }

    }

    public void goToContact(View v){
        Intent intent= new Intent(this, Contact.class);
        startActivity(intent);

    }
}