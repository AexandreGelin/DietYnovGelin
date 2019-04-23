package com.ynov.dietynovgelin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ynov.dietynovgelin.models.Recipes;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Recipes theRecipe = new Recipes();
        theRecipe = (Recipes) getIntent().getSerializableExtra("detail");

        TextView title = (TextView) findViewById(R.id.detail_title);
        title.setText(theRecipe.getTitle());

        TextView totalTime = (TextView) findViewById(R.id.detail_total);
        totalTime.setText("Total Time : " + theRecipe.getTime().getTotaTime());
    }
}
