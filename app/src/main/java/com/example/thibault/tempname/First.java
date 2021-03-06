package com.example.thibault.tempname;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
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
import java.util.Locale;


public class First extends AppCompatActivity implements TextToSpeech.OnInitListener{


    TextView question;
    EditText answer ;
    Button next,yes,no,yes2,no2;
    Spinner yn,spinner1;
    int riskscore,t , size, score, down, death;
    boolean updated;
    ConstraintLayout layout;
    private Button btnSpeak;
    TextToSpeech tts;

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

        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.q1);

        tts = new TextToSpeech(this, this);
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
        answer.setText("");
        answer.setHint("Answer here");

        List<String> values;

        switch (i){
            case 1: layout.setBackgroundResource(R.drawable.q1);
            break;
            case 2: layout.setBackgroundResource(R.drawable.downs);
                break;
            case 3: layout.setBackgroundResource(R.drawable.death);
                break;
            case 4: //spinner1.setVisibility(View.VISIBLE);
                    //values= Arrays.asList("yes", "no");
                    //addItemsOnSpinner1(values);

                layout.setBackgroundResource(R.drawable.witness);
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
                    layout.setBackgroundResource(R.drawable.phos);
                    break;
            case 6:
                    layout.setBackgroundResource(R.drawable.soiltest);
                    yes.setBackgroundColor(getResources().getColor(R.color.green));
                    no.setBackgroundColor(getResources().getColor(R.color.red));
                    break;
            case 7:
                    layout.setBackgroundResource(R.drawable.feed);
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
            case 8:
                layout.setBackgroundResource(R.drawable.mag);
                yes.setText("+-60g per cow per day");
                yes.setGravity(Gravity.CENTER_HORIZONTAL);
                    yes2.setText("less than 60g");
                    //TODO Add color yellow
                    no.setText("60g ");
                    no.setVisibility(View.INVISIBLE);
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
                if((down*100)/size>3){
                    score+=1;
                }
                break;
            case 3:
                death= Integer.parseInt(answer.getText().toString());
                if((death*100)/size>3){
                    score+=1;
                }
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
            intent.putExtra("risklevel",(Integer)(score+1)/2);
            intent.putExtra("size", size);
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

    public void audio(View v){
       speakOut();
    }

    public void goToRisk(View v){
        Intent intent= new Intent(this, Contact.class);
        startActivity(intent);
    }

    /*public void addListenerOnSpinnerItemSelection() {
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }*/

    /*public void addItemsOnSpinner1(List<String> values) {
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, values);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }*/

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                //btnSpeak.setEnabled(true);
                //speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }
    private void speakOut() {

        String text = question.getText().toString();

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}

