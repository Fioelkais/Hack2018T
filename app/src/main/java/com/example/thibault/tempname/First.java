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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class First extends AppCompatActivity {


    TextView question;
    EditText answer ;
    Button next,yes,no,yes2,no2;
    Spinner yn,spinner1;
    int riskscore,t , size, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        next=(Button) findViewById(R.id.button);
        yes=(Button) findViewById(R.id.yes);
        no=(Button) findViewById(R.id.no);
        yes2=(Button) findViewById(R.id.yes2);
        no2=(Button) findViewById(R.id.no2);
        question= (TextView) findViewById(R.id.question1);
        answer=(EditText) findViewById(R.id.answer1);
        yn= (Spinner) findViewById(R.id.spinner1);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        size=0;
        score=0;
        //yn.set;

        t=1;

        riskscore=0;

        spinner1.setVisibility(View.INVISIBLE);
        yes.setVisibility(View.INVISIBLE);
        no.setVisibility(View.INVISIBLE);
        yes2.setVisibility(View.INVISIBLE);
        no2.setVisibility(View.INVISIBLE);
        askQuestion(t);

    }

    public void askQuestion(int i){
        //question1.setText(getString(getResources().getIdentifier("question"+i,this.getPackageName())));
        question.setText(getResources().getString(getResources().getIdentifier("question"+i,"string","com.example.thibault.tempname")));
        //answer.setText(getString(R.string.answer));
        next.setText("");

        List<String> values;
        switch (i){
            case 4: //spinner1.setVisibility(View.VISIBLE);
                    //values= Arrays.asList("yes", "no");
                    //addItemsOnSpinner1(values);
                    answer.setVisibility(View.INVISIBLE);
                    yes.setVisibility(View.VISIBLE);
                    yes.setBackgroundColor(getResources().getColor(R.color.red));
                    no.setBackgroundColor(getResources().getColor(R.color.green));
                    no.setVisibility(View.VISIBLE);
                    break;
            case 5:
                    break;
            case 6:
                    yes.setBackgroundColor(getResources().getColor(R.color.green));
                    no.setBackgroundColor(getResources().getColor(R.color.red));
                    break;
            case 7:
                    yes.setText("Palm Kerrel");
                    yes2.setText("Fodder beet");
                    //TODO Add color yellow
                    no.setText("Hay");
                    no.setBackgroundColor(getResources().getColor(R.color.green));
                    no2.setText("Tapioca");
                    no2.setBackgroundColor(getResources().getColor(R.color.red));
                    break;
            case 8: yes.setText("50-70g Mg oxide/cow/day");
                    yes2.setText("lower than recommended");
                    //TODO Add color yellow
                    no.setText("60g ");
                    no2.setText("no");
                    break;
        }


    }

    public void check(View v) {
        final AlertDialog alertDialog = new AlertDialog.Builder(First.this).create();
        alertDialog.setTitle("Is this your definitive answer?");
        alertDialog.setMessage("");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (t==8)
                        {
                            //TODO go to risk level and send intent
                            Intent intent= new Intent(getApplicationContext(), RiskLevel.class);
                            startActivity(intent);
                        }
                        else{
                            t+=1;
                            riskscore=riskscore+5;//TODO change to depend on answer answer.gettext() case might no be used if going with compute risk function
                            //spinner1.getSelectedItem();

                            askQuestion(t);
                        }
                        switch (t){
                            case 1:
                                size= Integer.parseInt(answer.getText().toString());
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;

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

    /*public void addListenerOnSpinnerItemSelection() {
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }*/

    public void addItemsOnSpinner1(List<String> values) {
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, values);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }
}

