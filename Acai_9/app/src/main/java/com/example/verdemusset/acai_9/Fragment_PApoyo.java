package com.example.verdemusset.acai_9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Verde Musset on 24/11/2015.
 */
public class Fragment_PApoyo extends Fragment {

    View rootView;
    int contador = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fm_papoyo, container, false);
       // TextView texto = (TextView) rootView.findViewById(R.id.texto_pedidos);
        //texto.setText("Incrementador de Pedidos" + "\n\n" + contador);
       // contador++;

        return rootView;
    }

}
