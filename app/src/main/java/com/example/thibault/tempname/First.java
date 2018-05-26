package com.example.thibault.tempname;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
    int riskscore,t , size, score, down, death;
    boolean updated;
    ConstraintLayout layout;

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
        down=0;
        death=0;
        updated=false;
        //yn.set;\
        layout=(ConstraintLayout)findViewById(R.id.activity_first);

        t=1;

        riskscore=0;

        spinner1.setVisibility(View.INVISIBLE);
        yes.setVisibility(View.INVISIBLE);
        no.setVisibility(View.INVISIBLE);
        yes2.setVisibility(View.INVISIBLE);
        no2.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        askQuestion(t);

    }

    public void askQuestion(int i){
        updated=false;
        //question1.setText(getString(getResources().getIdentifier("question"+i,this.getPackageName())));
        question.setText(getResources().getString(getResources().getIdentifier("question"+i,"string","com.example.thibault.tempname")));
        //answer.setText(getString(R.string.answer));
        next.setText("");
        answer.setHint("Answer here");

        List<String> values;

        switch (i){
            case 1: layout.setBackgroundResource(R.drawable.slide2);
            break;
            case 4: //spinner1.setVisibility(View.VISIBLE);
                    //values= Arrays.asList("yes", "no");
                    //addItemsOnSpinner1(values);

                View view = this.getCurrentFocus();
                InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                next.setVisibility(View.INVISIBLE);
                yes.setText("yes");
                no.setText("no");
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
                    yes2.setBackgroundColor(getResources().getColor(R.color.yellow));
                    //TODO Add color yellow
                    no.setText("Hay");
                    no.setBackgroundColor(getResources().getColor(R.color.green));
                    no2.setText("Tapioca");
                    no2.setBackgroundColor(getResources().getColor(R.color.red));
                    yes2.setVisibility(View.VISIBLE);
                    no2.setVisibility(View.VISIBLE);
                    break;
            case 8: yes.setText("50-70g Mg oxide/cow/day");
                    yes2.setText("lower than recommended");
                    //TODO Add color yellow
                    no.setText("60g ");
                    no2.setText("no");
                    break;
        }


    }

    public void check(View view) {



        switch (t){
            case 1:
                size= Integer.parseInt(answer.getText().toString());
                break;
            case 2:
                down= Integer.parseInt(answer.getText().toString());
                break;
            case 3:
                death= Integer.parseInt(answer.getText().toString());
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                if(((ColorDrawable) view.getBackground()).getColor()== Color.parseColor("#ffff0000")){
                score+=1;
                updated=true;
                }
                break;

        }


        if (t==8)
        {
            //TODO go to risk level and send intent
            Intent intent= new Intent(getApplicationContext(), RiskLevel.class);
            intent.putExtra("risklevel",(score+1)/2);
            startActivity(intent);
        }
        else{
            t+=1;
            riskscore=riskscore+5;//TODO change to depend on answer answer.gettext() case might no be used if going with compute risk function
            //spinner1.getSelectedItem(
            askQuestion(t);
        }
        /*
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
                            //spinner1.getSelectedItem(
                            askQuestion(t);
                        }


                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(updated){
                            score-=1;
                        }
                        dialog.dismiss();
                    }
                });
        alertDialog.show();*/
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

