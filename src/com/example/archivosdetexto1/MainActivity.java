package com.example.archivosdetexto1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;


public class MainActivity extends Activity {
	EditText et1,et2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.editText1);
        et2=(EditText)findViewById(R.id.editText2);
        
    }

    public void guardar (View v) {
    	String nombre=et1.getText().toString();
    	String contenido=et2.getText().toString();
    	try {
	    		OutputStreamWriter archi=new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));
	    		archi.write(contenido);//escribe, se puede hacer varios write y luego recién el flush
	    		archi.flush();//guarda
	    		archi.close();//libera de memoria
	    		et1.setText("");
	    		et2.setText("");
	    		Toast.makeText(this, "El archivo se creó correctamente", Toast.LENGTH_LONG).show();
	    	} catch (IOException ex) {  
	    		Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
	    	} catch (IllegalArgumentException err) {  
	        	Toast.makeText(this, "Nombre de archivo no válido", Toast.LENGTH_LONG).show();
	        }
    	}
    
    public void recuperar (View v) {
    	String nombre=et1.getText().toString();
    	try {
    		InputStreamReader archi=new InputStreamReader(openFileInput(nombre));
    		BufferedReader br=new BufferedReader(archi);
    		String linea=br.readLine();
    		String contenido="";
    		while (linea!=null) {
    			contenido=contenido+linea+"\n";
    			linea=br.readLine();
    		}
    		br.close();
    		archi.close();
    		et2.setText(contenido);
    	} catch (IOException ex) {  
    		Toast.makeText(this, "Archivo no existente"+"\n"+ex.getMessage(), Toast.LENGTH_LONG).show();
    	}
    }
    
    public void ver (View v) {
    	Intent intento1=new Intent(this, ListadoArchivos.class);
    	startActivity(intento1);
    }
    
    public void borrar (View v) {
    	if (deleteFile(et1.getText().toString())) {
            et1.setText("");
            et2.setText("");
            Toast.makeText(this, "El archivo ha sido borrado correctamente", Toast.LENGTH_LONG).show();
    	}else{
    		Toast.makeText(this, "No existe el archivo", Toast.LENGTH_LONG).show();
    	}
    }
}
    

