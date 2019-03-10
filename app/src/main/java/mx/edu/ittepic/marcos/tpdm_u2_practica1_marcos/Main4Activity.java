package mx.edu.ittepic.marcos.tpdm_u2_practica1_marcos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    EditText descripcionE, ubicacionE, fechaE, presupuestoE;
    Button modificar, regresar3, eliminar;
    Integer id;
    Proyectos[] vector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        descripcionE = findViewById(R.id.editadescripcion);
        ubicacionE = findViewById(R.id.editaubicacion);
        fechaE = findViewById(R.id.editafecha);
        presupuestoE = findViewById(R.id.editapresupuesto);

        modificar = findViewById(R.id.btnModificar);
        regresar3 = findViewById(R.id.btnRegresar3);
        eliminar = findViewById(R.id.btnEliminar);

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        final EditText peticion = new EditText(this);

        alerta.setTitle("Atención")
                .setMessage("¿Cuál es la descripción o ID del proyecto que desea modificar/eliminar?")
                .setView(peticion)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        extraerDatos(peticion.getText().toString());
                    }
                })
                .show();

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifcar();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });

        regresar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(Main4Activity.this, MainActivity.class);
                startActivity(regresar);
            }
        });

    }

    private void extraerDatos( String descrip){
        Proyectos proyecto = new Proyectos(this);
        vector = proyecto.consultar(descrip);


        if(vector == null){
            String datos = "No existe proyecto con el parámetro de búsqueda introducido ";
            Toast.makeText(this,datos, Toast.LENGTH_SHORT).show();
            Intent regresar = new Intent(Main4Activity.this, MainActivity.class);
            startActivity(regresar);

        } else{
            descripcionE.setText(vector[0].getDescripcion());
            ubicacionE.setText(vector[0].getUbicacion());
            fechaE.setText(vector[0].getFecha());
            presupuestoE.setText(vector[0].getPresupuesto()+"");
            id = vector[0].getId();
        }
    }

    private void modifcar(){
        Proyectos proyecto = new Proyectos(this);
        String mensaje="";

        boolean respuesta = proyecto.actualizar(new Proyectos(id,descripcionE.getText().toString(),ubicacionE.getText().toString(),fechaE.getText().toString(),Float.parseFloat(presupuestoE.getText().toString())));

        if(respuesta){
            mensaje = "Se actualizó correctamente el proyecto. ";
         }else{
            mensaje = "Error! Algo salió mal: "+proyecto.error;
         }

        Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show();
    }

    private void eliminar(){
        Proyectos proyecto = new Proyectos(this);
        String mensaje = "";

        boolean respuesta = proyecto.eliminar(new Proyectos(id,descripcionE.getText().toString(),ubicacionE.getText().toString(),fechaE.getText().toString(),Float.parseFloat(presupuestoE.getText().toString())));

        if(respuesta){
            mensaje = "Se eliminó correctamente el proyecto "+descripcionE.getText().toString();
        } else{
            mensaje = "Error! Algo salió mal: "+proyecto.error;
        }

        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
        Intent salir = new Intent(Main4Activity.this, MainActivity.class);
        startActivity(salir);

    }
}
