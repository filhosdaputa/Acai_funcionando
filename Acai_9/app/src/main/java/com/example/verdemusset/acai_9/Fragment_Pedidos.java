package com.example.verdemusset.acai_9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_Pedidos extends Fragment {
	
	View rootView;
	int contador = 0;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fm_pedidos, container, false);
		TextView texto = (TextView) rootView.findViewById(R.id.texto_pedidos);
		texto.setText("Incrementador de Pedidos" + "\n\n" + contador);
		contador++;
		
		return rootView;
	}
}