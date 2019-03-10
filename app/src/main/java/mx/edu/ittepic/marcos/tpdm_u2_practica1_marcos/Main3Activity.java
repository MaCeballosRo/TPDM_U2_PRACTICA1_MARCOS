package mx.edu.ittepic.marcos.tpdm_u2_practica1_marcos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    EditText busqueda;
    Button consultar, regresar2;
    TextView resultado;
    Proyectos[] vector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        busqueda = findViewById(R.id.busqueda);
        consultar = findViewById(R.id.consultar);
        regresar2 = findViewById(R.id.regresar2);
        resultado = findViewById(R.id.lblresultado);

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar();
            }
        });

        regresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(regresar);
            }
        });
    }

    private void consultar(){
        Proyectos proyecto = new Proyectos(this);
        vector = proyecto.consultar(busqueda.getText().toString());

        String datos = "" ;
        resultado.setText("");

        if(vector == null){
            datos = "No existe proyecto con el parámetro de búsqueda introducido ";

        } else{
                String des = "Descripción: "+vector[0].getDescripcion();
                String ubi = "Ubicación: "+vector[0].getUbicacion();
                String fecha = "Fecha: "+vector[0].getFecha();
                String pres = "Presupuesto: "+vector[0].getPresupuesto();
                datos = des  +"\n"+ubi+"\n"+fecha+"\n"+pres+"\n";
        }
        resultado.setText(datos);
        vector = null;
    }
}
