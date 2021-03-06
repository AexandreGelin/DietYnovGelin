package com.ynov.dietynovgelin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.ynov.dietynovgelin.DataBase.WeightBDD;
import com.ynov.dietynovgelin.models.Weight;

public class WeightActivity extends AppCompatActivity {

    protected Weight weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(onButtonShowPopupWindowClick);






    }

    public View.OnClickListener onButtonShowPopupWindowClick = new View.OnClickListener() {
        public void onClick(View view) {

            // inflate the layout of the popup window
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.popup_add_weight, null);
            Button cancel = (Button) popupView.findViewById(R.id.cancel);
            Button val = (Button) popupView.findViewById(R.id.validate);
            final EditText mesure = (EditText) popupView.findViewById(R.id.input_weight);
            final EditText date = (EditText) popupView.findViewById(R.id.input_date);
            // create the popup window
            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            boolean focusable = true; // lets taps outside the popup also dismiss it
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

            // show the popup window
            // which view you pass in doesn't matter, it is only used for the window tolken
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

            val.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    weight = new Weight();
                    weight.setMesure(mesure.toString());
                    weight.setDate(date.toString());

                    WeightBDD weightBdd = new WeightBDD(v.getContext());

                    weightBdd.insertWeight(weight);

                    Weight weightFromBdd = weightBdd.getWeightWithDate(weight.getDate());
                    if(weightFromBdd != null){

                        Log.i("YNOV",weightFromBdd.toString() );
                    }
                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }
    };



}
