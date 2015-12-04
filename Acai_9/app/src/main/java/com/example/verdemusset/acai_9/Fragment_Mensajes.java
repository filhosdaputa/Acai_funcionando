package com.example.verdemusset.acai_9;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Verde Musset on 24/11/2015.
 */
public class Fragment_Mensajes extends Fragment implements View.OnClickListener {

    ListView lv;
  //adapter Fila = adaptador Fila;
    View rootView;
    List<String> item = null;
    ArrayList<Integer> itemImagen = null;
    ArrayList<String> itemTitulo = null;
    ArrayList<String> itemContenido = null;
    ArrayList<String> itemFActividad = null;
    Button bt;
    ArrayAdapter<String> adaptadorX;
    Actualizar actMensajes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fm_mensajes, container, false);
        lv = (ListView) rootView.findViewById(R.id.listView1);
        bt = (Button) rootView.findViewById(R.id.btnActualizar);
        bt.setOnClickListener(this /*new View.OnClickListener()

        {
            @Override//Metoo on click del boton ACTUALIZAR
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnActualizar:
                        //Creamos una instancia de la clase: miTarea y la ejecutamos
                        miTarea laTarea = new miTarea();
                        laTarea.execute();
                        muestraMensaje();
                        adaptador.notifyDataSetChanged();
                        break;
                }
            }
        }*/);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int laPosicion = position + 1;
                String elementoSeleccionado =
                        (String) lv.getItemAtPosition(position);

                Toast.makeText(getContext(), "Seleccionaste el item: " +
                        laPosicion + ") " +
                        elementoSeleccionado, Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }


    //Este es el onClick de la pagina Mensaje, si no me equivoco aqui es donde se invocara que al tocarse el listview nos mande a ver sus datos
    // Aqui hay que usar el codigo del proferos del get position para llamar y mostrar los datos segun la posicion (eso creo)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnActualizar:
                System.out.println("Antes de entrar a clase interna: ACTUALIZAR");
                actMensajes = new Actualizar();
                actMensajes.execute();
                System.out.println("Saliendo de clase interna: ACTUALIZAR");
                break;
        }
    }


    protected class Actualizar extends AsyncTask<Void, Void, Void> {


        AdaptadorBD adb = new AdaptadorBD(getContext());
        JSONObject mjson;
        String UrlTxt = "http://jsonip.com";
        String elTextoBuffer;
        String elTextoFinal = "";
        String s = "";



        private void muestraMensaje() {
            Cursor c = adb.getNotes();
            item = new ArrayList<String>();
            String titulo = "", fenvio = "", idMsj = "";
            if (c.moveToFirst()) {
                //recorremos el objeto CURSOR el cual tiene todos los datos
                do {
                    titulo = c.getString(2); // Aqui obtenemos el registro de la colimna indicada en los (), y el titulo se encuentra en la columna 3
                    fenvio = c.getString(1);
                    idMsj = c.getString(0);
                    item.add(idMsj + "-" + titulo + "-" + fenvio);//aquise estan enviando los datos juntos y me parece que no es as<<<REVISAR

                } while (c.moveToNext());

                adaptadorX = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, item);
                lv.setAdapter(adaptadorX);
            }
            adaptadorX.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... params) {

            URL elUrl;
            try {
                System.out.println("Nos conectamos y leemos del Servicio Web");
                elUrl = new URL(UrlTxt);
                BufferedReader elBufferReader = new BufferedReader(new InputStreamReader(elUrl.openStream()));

                //Leemos linea por linea el contenido de lo leido
                while ((elTextoBuffer = elBufferReader.readLine()) != null) {
                    elTextoFinal += elTextoBuffer;
                }
                System.out.println("saliendo del bufferReader: " + elTextoFinal);
                elBufferReader.close();//Cerramos el buffer

            } catch (MalformedURLException e) {
                System.out.println("Este es el objeto json: ");
                e.printStackTrace();
                Log.d("==>>Error: ", e.toString());
            } catch (IOException e) {
                System.out.println("Este es el objeto json: ");
                e.printStackTrace();
                Log.d("==>>Error: ", e.toString());
            }
            try {
                //Guardamos los datos en un objeto JSON
                System.out.println("antes de crear el json");
                mjson = new JSONObject(elTextoFinal);

                String p = mjson.getString("ip");
                System.out.println("el MjsonP fue creado = " + p);

                JSONObject clienteJSON = new JSONObject(new String(elTextoFinal));
                System.out.println("el jsonfue creado = " + clienteJSON.getString("ip"));

                //Mostramos un valor del JSON
                s = clienteJSON.getString("ip");

            } catch (JSONException e) {
                System.out.println("Error al crear el json!");
                e.printStackTrace();
                Log.d("==>>Error: ", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                adb.addMensaje(s, s);
            } catch (Exception e) {
                Toast.makeText(getContext(), "addMensaje fail", Toast.LENGTH_SHORT).show();
            }
            muestraMensaje();
            super.onPostExecute(aVoid);
        }
    }


    /*private void muestraMensaje2() {
        int contadorArreglo = 0;
        Cursor c = adb.getNotes();
        itemImagen = new ArrayList<Integer>();
        itemContenido = new ArrayList<String>();
        itemTitulo = new ArrayList<String>();
        itemFActividad = new ArrayList<String>();

        if (c.moveToFirst()) {
            //recorremos el objeto CURSOR el cual tiene todos los datos
            do {


                itemImagen.add(contadorArreglo, Integer.parseInt(c.getString(0)));
                itemTitulo.add(contadorArreglo, c.getString(1));
                itemContenido.add(contadorArreglo, c.getString(2));
                itemFActividad.add(contadorArreglo, c.getString(0));
                contadorArreglo++;


            } while (c.moveToNext());
            adaptador Fila = new adapter Fila(getContext(), itemImagen, itemTitulo, itemContenido, itemFActividad);
            lv.setAdapter(adaptadorFila);
            adaptador Fila.notifyDataSetChanged();

        }
    }*/

}



