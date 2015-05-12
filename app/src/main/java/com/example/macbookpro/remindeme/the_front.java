package com.example.macbookpro.remindeme;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;


public class the_front extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_front);


    }

    public void anotherActivity (View view){

        Intent intent = new Intent(the_front.this, sign_in.class);
        startActivity(intent);
    }


}
