package com.example.archivosdetexto1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;


public class ListadoArchivos extends Activity {
	ListView lv1;
	String[] archivos;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado_archivos);
		lv1=(ListView)findViewById(R.id.listView1);
		archivos=fileList();//devuelve un vector con todos los nombres de los archivos
		ArrayAdapter<String> adaptador1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, archivos);
		lv1.setAdapter(adaptador1);
		lv1.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//Toast.makeText(ListadoArchivos.this, archivos[arg2], Toast.LENGTH_LONG).show();
				Intent intento1=new Intent(ListadoArchivos.this, MostrarArchivo.class);
				intento1.putExtra("nombrearchivo", archivos[arg2]);
				startActivity(intento1);
				
			}
			
		});
	}

	


}
