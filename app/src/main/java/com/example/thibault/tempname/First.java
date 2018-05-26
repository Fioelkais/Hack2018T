package com.example.thibault.tempname;

import android.content.DialogInterface;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        TextView question1 = (TextView) findViewById(R.id.question1);
        EditText answer1 = (EditText) findViewById(R.id.answer1);
        Button next = (Button) findViewById(R.id.button);

        final AlertDialog alertDialog = new AlertDialog.Builder(First.this).create();
        alertDialog.setTitle("Is this your definitive answer?");
        alertDialog.setMessage("");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

        int t =1;

        int riskscore=0;


            question1.setText(getString(R.string.question1));
            answer1.setText(getString(R.string.answer1));
            next.setText("Next");

            next.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    alertDialog.show();
                }
            });


    }
}


