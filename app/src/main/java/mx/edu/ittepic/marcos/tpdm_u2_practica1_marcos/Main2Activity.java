package mx.edu.ittepic.marcos.tpdm_u2_practica1_marcos;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText descripcion, ubicacion, fecha, presupuesto;
    Button insertar, regresar;
    Proyectos[] vector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        descripcion = findViewById(R.id.descripcion);
        ubicacion = findViewById(R.id.ubicacion);
        fecha = findViewById(R.id.fecha);
        presupuesto = findViewById(R.id.presupuesto);
        insertar = findViewById(R.id.btnInsertar);
        regresar = findViewById(R.id.btnRegresar);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(regresar);
            }
        });
    }

    private void insertar(){
        String mensaje = "";
        Proyectos proyecto = new Proyectos(this);

        vector = proyecto.consultar(descripcion.getText().toString());

        if(vector == null){
            boolean respuesta = proyecto.insertar(new Proyectos(-1,descripcion.getText().toString(),ubicacion.getText().toString(),fecha.getText().toString(),Float.parseFloat(presupuesto.getText().toString())));

            if(respuesta){
                mensaje = "Se insertó correctamente el proyecto '"+descripcion.getText().toString()+"'";
                descripcion.setText("");
                ubicacion.setText("");
                fecha.setText("");
                presupuesto.setText("");
            } else{
                mensaje = "Error! No se pudo crear el nuevo proyecto, SQLite dice: "+ proyecto.error;
            }
        } else{
            mensaje = "Error! Ya existe un proyecto con la descripción establecida.";
        }

        Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show();
    }
}
