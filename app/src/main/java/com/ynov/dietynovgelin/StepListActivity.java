package com.ynov.dietynovgelin;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ynov.dietynovgelin.models.Recipes;
import com.ynov.dietynovgelin.models.Step;

import java.util.List;

public class StepListActivity extends ListActivity {

    private StepAdapter sAdapter;
    private List<Step> listSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_list);

        Recipes theRecipe = new Recipes();
        theRecipe = (Recipes) getIntent().getSerializableExtra("step");
        listSteps = theRecipe.getListStep();

        sAdapter = new StepAdapter(this,listSteps);
        setListAdapter(sAdapter);
    }

    public class StepAdapter extends ArrayAdapter<Step> {
        public StepAdapter(Context context, List<Step> step) {
            super(context, 0, step);
        }

        @Override
        public View getView(int position, View convertView2, ViewGroup parent2) {
            // Get the data item for this position
            Step step = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView2 == null) {
                convertView2 = LayoutInflater.from(getContext()).inflate(R.layout.layout_step, parent2, false);
            }
            // Lookup view for data population
            TextView order = (TextView) convertView2.findViewById(R.id.order);
            // Populate the data into the template view using the data object
            order.setText(Integer.toString(step.getOrder()));

            TextView etape = (TextView) convertView2.findViewById(R.id.step);
            etape.setText(step.getStep());


            // Return the completed view to render on screen
            return convertView2;
        }
    }
}
