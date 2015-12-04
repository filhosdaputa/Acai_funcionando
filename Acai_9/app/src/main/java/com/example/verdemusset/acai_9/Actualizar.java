package com.example.verdemusset.acai_9;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Verde Musset on 27/11/2015.
 */
public class Actualizar extends AsyncTask
{
    JSONObject mjson;
    String UrlTxt = "http://jsonip.com";
    String elTextoBuffer, elTextoFinal;

    @Override
    protected Void doInBackground(Object... params) {
        try {
            URL elUrl;
            elUrl = new URL(UrlTxt);
            BufferedReader elBufferReader = new BufferedReader(new InputStreamReader(elUrl.openStream()));

            //Leemos linea por linea el contenido de lo leido
            while ((elTextoBuffer = elBufferReader.readLine()) != null) {
                elTextoFinal += elTextoBuffer;

            }
        } catch (MalformedURLException e) {

            System.out.println("Error al ir al webService");
            e.printStackTrace();
            Log.d("==>>Error: ", e.toString());
        } catch (IOException e) {
            System.out.println("Error al ir al cargar los datos");
            e.printStackTrace();
            Log.d("==>>Error: ", e.toString());
        }
        try {
            mjson = new JSONObject(elTextoFinal);
        } catch (Exception e) {
            System.out.println("No se creo el json");
            System.out.println("No se creo el json");
            System.out.println("No se creo el json");

        }
        try{
            ConnectionDB.sfenvio = mjson.getString("ip");
            ConnectionDB.stitulo = mjson.getString("ip");} catch (JSONException e){
            System.out.println("No se guardaron los JSON n las variables staticas");
        }
return null;
    }

}


