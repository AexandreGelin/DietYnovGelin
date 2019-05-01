package com.ynov.dietynovgelin.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ynov.dietynovgelin.models.Weight;


public class WeightBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "ynov.db";

    private static final String TABLE_WEIGHT = "table_weight";
    private static final String COL_ID = "ID";
    private static final String COL_DATE = "Date";
    private static final String COL_MESURE = "Mesure";
    private static final int NUM_COL_ID = 0;
    private static final int NUM_COL_DATE = 1;
    private static final int NUM_COL_MESURE = 2;

    private SQLiteDatabase bdd;

    private WeightSQLite maBaseSQLite;

    public WeightBDD(Context context){
        //On crée la BDD et sa table
        maBaseSQLite = new WeightSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertWeight(Weight weight){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_DATE, weight.getDate());
        values.put(COL_MESURE, weight.getMesure());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_WEIGHT, null, values);
    }

    public int updateWeight(int id, Weight weight){
        //La mise à jour d'un prospect dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel prospect on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_MESURE, weight.getDate());
        values.put(COL_DATE, weight.getMesure());
        return bdd.update(TABLE_WEIGHT, values, COL_ID + " = " +id, null);
    }

    public int removeWeightWithID(int id){
        //Suppression d'un prospect de la BDD grâce à l'ID
        return bdd.delete(TABLE_WEIGHT, COL_ID + " = " +id, null);
    }

    public Weight getWeightWithDate(String date){
        //Récupère dans un Cursor les valeurs correspondant à un prospect contenu dans la BDD (ici on sélectionne le prospect grâce à son mail)
        Cursor c = bdd.query(TABLE_WEIGHT, new String[] {COL_ID, COL_DATE, COL_MESURE}, COL_DATE + " LIKE \"" + date +"\"", null, null, null, null);
        return cursorToWeight(c);
    }

    //Cette méthode permet de convertir un cursor en un prospect
    private Weight cursorToWeight(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un prospect
        Weight weight = new Weight();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        weight.setDate(c.getString(NUM_COL_DATE));
        weight.setMesure(c.getString(NUM_COL_MESURE));
        //On ferme le cursor
        c.close();

        //On retourne le prospect
        return weight;
    }
}
