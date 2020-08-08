package com.example.archivosdetexto1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;


public class MostrarArchivo extends Activity {
	EditText et1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_archivo);
		et1=(EditText)findViewById(R.id.editText1);
		Bundle datos=getIntent().getExtras();
		String nombrearchivo=datos.getString("nombrearchivo");
		
		try {
			InputStreamReader archivo=new InputStreamReader(openFileInput(nombrearchivo));
			BufferedReader br=new BufferedReader(archivo);
			String linea=br.readLine();//lee una línea completa pero no el salto de línea
			String contenido="";
			
			while (linea!=null) {
				contenido=contenido+linea+"\n"; //se podría poner también como: contenido += linea+"\n";
				linea=br.readLine();
			}
			br.close();
			archivo.close();
			et1.setText(contenido);			
			
		} catch (IOException err) {
			Toast.makeText(this, "Problemas en la lectura del archivo", Toast.LENGTH_LONG).show();
		} 
	}

	public void retornar (View v) {
		finish();
	}

}
