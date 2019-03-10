package mx.edu.ittepic.marcos.tpdm_u2_practica1_marcos;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Proyectos vector[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.listaProyectos);

    }


    protected void onStart(){
       Proyectos proyecto = new Proyectos(this);
       vector = proyecto.consultar();
       String[] descripcion = null;

       if(vector == null){
           descripcion = new String[1];
           descripcion[0] = "No hay proyetos aún";
       } else{
           descripcion = new String [vector.length];
           for(int i=0; i<vector.length;i++){
               descripcion[i] = vector[i].getDescripcion();
           }
       }

       ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,descripcion);

       lista.setAdapter(adaptador);

       super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opciones,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.insertarProyecto:
                Intent insertarP = new Intent(this,Main2Activity.class);
                startActivity(insertarP);
                break;
            case R.id.consultarProyecto:
                Intent consultarP = new Intent(this,Main3Activity.class);
                startActivity(consultarP);
                break;

            case R.id.modificarEliminar:
                Intent modificarP = new Intent(this,Main4Activity.class);
                startActivity(modificarP);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
