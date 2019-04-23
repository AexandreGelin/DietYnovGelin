package com.ynov.dietynovgelin;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ynov.dietynovgelin.models.Ingredient;
import com.ynov.dietynovgelin.models.Nutrition;
import com.ynov.dietynovgelin.models.Recipes;
import com.ynov.dietynovgelin.models.Step;
import com.ynov.dietynovgelin.models.Time;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListRecipeActivity extends ListActivity {


    private TextView text;
    private List<Recipes> listRecipe;
    private List<Ingredient> listIngredient;
    private List<Step> listSteps;
    private ProgressDialog pDialog;
    private RecipeAdapter adapter;
    // URL to get prospects JSON
    private static String url = "http://dev.audreybron.fr/flux/flux_recettes.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);
        listRecipe = new ArrayList<Recipes>();
        listIngredient = new ArrayList<Ingredient>();
        listSteps = new ArrayList<Step>();
        text = (TextView) findViewById(R.id.mainText);
        // initiate the listadapter
        adapter = new RecipeAdapter(this,listRecipe);
        GetRecipes task=new GetRecipes();
        task.execute();
        // assign the list adapter
        setListAdapter(adapter);


    }

    // when an item of the list is clicked
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);
        Recipes selectedItem = (Recipes) getListView().getItemAtPosition(position);
        text.setText("Click sur " + selectedItem.getTitle() + " à la position " + position);

        Intent i = new Intent(ListRecipeActivity.this, RecipeDetailActivity.class);
        i.putExtra("detail", selectedItem);
        startActivity(i);

    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetRecipes extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            //Log.e("YNOV", "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray jsonArr = jsonObj.getJSONArray("result");

                    for (int i = 0; i < jsonArr.length(); i++) {
                        JSONObject c = jsonArr.getJSONObject(i);


                        String title = c.getString("title");
                        int portions = c.getInt("portions");
                        String image = c.getString("picture_url");

                        JSONObject time = c.getJSONObject("time");
                        int total = time.getInt("total");
                        int baking = time.getInt("baking");
                        int prep = time.getInt("prep");

                        JSONObject nut = c.getJSONObject("nutrition");
                        int kcal = nut.getInt("kcal");
                        int protein = nut.getInt("protein");
                        int fat = nut.getInt("fat");
                        int carbohydrate = nut.getInt("carbohydrate");
                        int sugar = nut.getInt("sugar");
                        int satFat = nut.getInt("sat_fat");
                        int fiber = nut.getInt("fiber");
                        int sodium = nut.getInt("sodium");

                        Nutrition rNut = new Nutrition();
                        rNut.setKcal(kcal);
                        rNut.setProtein(protein);
                        rNut.setFat(fat);
                        rNut.setCarbohydrate(carbohydrate);
                        rNut.setSugar(sugar);
                        rNut.setSatFat(satFat);
                        rNut.setFiber(fiber);
                        rNut.setSodium(sodium);

                        Time rTime = new Time();
                        rTime.setTotaTime(total);
                        rTime.setBakingTime(baking);
                        rTime.setPrepTime(prep);

                        JSONArray jsonArrIng = c.getJSONArray("ingredients");
                        for(int j = 0; j<jsonArrIng.length(); j++){
                            JSONObject d = jsonArrIng.getJSONObject(j);

                            int quantity = d.getInt("quantity");
                            String unit = d.getString("unit");
                            String name = d.getString("name");

                            Ingredient ing = new Ingredient();
                            ing.setiName(name);
                            ing.setQuantity(quantity);
                            ing.setUnit(unit);

                            listIngredient.add(ing);
                        }


                        JSONArray jsonArrStep = c.getJSONArray("steps");
                        for(int k = 0; k<jsonArrStep.length(); k++){
                            JSONObject a = jsonArrStep.getJSONObject(k);

                            int order = a.getInt("order");
                            String step = a.getString("step");

                            Step rStep = new Step();
                            rStep.setOrder(order);
                            rStep.setStep(step);

                            listSteps.add(rStep);
                        }


                        Recipes recipe = new Recipes();
                        recipe.setTitle(title);
                        recipe.setrImage(image);
                        recipe.setTime(rTime);
                        recipe.setPortions(portions);
                        recipe.setNutrition(rNut);
                        recipe.setListIngredient(listIngredient);
                        recipe.setListStep(listSteps);

                        // adding contact to contact list
                        listRecipe.add(recipe);
                    }
                } catch (final JSONException e) {
                    Log.e("YNOV", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e("YNOV", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog

            // Mise à jour de la liste
            //adapter.addAll(listRecipe);
            adapter.notifyDataSetChanged();
            int size = listRecipe.size();

        }

    }

    public class RecipeAdapter extends ArrayAdapter<Recipes> {
        public RecipeAdapter(Context context, List<Recipes> recipes) {
            super(context, 0, recipes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Recipes recipes = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);
            }
            // Lookup view for data population
            TextView title = (TextView) convertView.findViewById(R.id.title);
            // Populate the data into the template view using the data object
            title.setText(recipes.getTitle());

            TextView time = (TextView) convertView.findViewById(R.id.time);
            time.setText("temps total : " + recipes.getTime().getTotaTime() + "min");

            // Return the completed view to render on screen
            return convertView;
        }
    }

    public void destroy (View v){
        finish();
    }
}
