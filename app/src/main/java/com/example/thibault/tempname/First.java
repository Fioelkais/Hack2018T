package com.example.thibault.tempname;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;



public class First extends AppCompatActivity {


    TextView question;
    EditText answer ;
    Button next;
    int riskscore;
    int t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        next=(Button) findViewById(R.id.button);
        question= (TextView) findViewById(R.id.question1);
        answer=(EditText) findViewById(R.id.answer1);

        t=1;

        riskscore=0;
        askQuestion(t);

    }

    public void askQuestion(int i){
        //question1.setText(getString(getResources().getIdentifier("question"+i,this.getPackageName())));
        question.setText(getResources().getString(getResources().getIdentifier("question"+i,"string","com.example.thibault.tempname")));
        answer.setText(getString(R.string.answer));
        next.setText("Next");

    }

    public void check(View v) {
        final AlertDialog alertDialog = new AlertDialog.Builder(First.this).create();
        alertDialog.setTitle("Is this your definitive answer?");
        alertDialog.setMessage("");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (t==8)//TODO change to last question
                        {
                            //TODO go to risk level and send intent
                            Intent intent= new Intent(getApplicationContext(), RiskLevel.class);
                            startActivity(intent);
                        }
                        else{
                            t+=1;
                            riskscore=riskscore+5;//TODO change to depend on answer answer.gettext() case might no be used if going with compute risk function
                            askQuestion(t);
                        }

                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void computeRisk(){
        //TODO Compute risk from answers
    }

    public void goToRisk(View v){
        Intent intent= new Intent(this, RiskLevel.class);
        startActivity(intent);
    }
}

