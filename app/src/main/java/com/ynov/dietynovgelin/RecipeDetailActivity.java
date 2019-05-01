package com.ynov.dietynovgelin;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ynov.dietynovgelin.models.Ingredient;
import com.ynov.dietynovgelin.models.Recipes;
import com.ynov.dietynovgelin.models.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailActivity extends ListActivity {

    private List<Ingredient> listIngredient;
    private IngredientAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);


        Recipes theRecipe = new Recipes();
        theRecipe = (Recipes) getIntent().getSerializableExtra("detail");

        listIngredient = theRecipe.getListIngredient();

        ImageView img = (ImageView) findViewById(R.id.imgRecipe);

        TextView title = (TextView) findViewById(R.id.detail_title);
        title.setText(theRecipe.getTitle());

        TextView portion = (TextView) findViewById(R.id.detail_portion);
        portion.setText("portions : " + theRecipe.getPortions() + " personnes");

        TextView totalTime = (TextView) findViewById(R.id.detail_total);
        totalTime.setText("temps total : " + theRecipe.getTime().getTotaTime() + " min");

        TextView prepTime = (TextView) findViewById(R.id.detail_prep);
        prepTime.setText("temps de préparation : " + theRecipe.getTime().getPrepTime() + " min");

        TextView bakeTime = (TextView) findViewById(R.id.detail_bake);
        bakeTime.setText("temps de cuisson : " + theRecipe.getTime().getBakingTime() + " min");


        adapter = new IngredientAdapter(this,listIngredient);
        setListAdapter(adapter);

        Button btnStep = (Button) findViewById(R.id.StepLink);
        btnStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecipeDetailActivity.this, StepListActivity.class);
                i.putExtra("step", getIntent().getSerializableExtra("detail") );
                startActivity(i);

            }
        });

    }



    public class IngredientAdapter extends ArrayAdapter<Ingredient> {
        public IngredientAdapter(Context context, List<Ingredient> ingredients) {
            super(context, 0, ingredients);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Ingredient ingredient = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_ingredient, parent, false);
            }
            // Lookup view for data population
            TextView name = (TextView) convertView.findViewById(R.id.name);
            // Populate the data into the template view using the data object
            name.setText(ingredient.getiName());

            TextView quant = (TextView) convertView.findViewById(R.id.quantity);
            quant.setText(Integer.toString(ingredient.getQuantity()));

            TextView unit = (TextView) convertView.findViewById(R.id.unit);
            unit.setText(ingredient.getUnit());

            // Return the completed view to render on screen
            return convertView;
        }
    }

}
