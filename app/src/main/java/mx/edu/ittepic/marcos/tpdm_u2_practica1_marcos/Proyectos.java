package mx.edu.ittepic.marcos.tpdm_u2_practica1_marcos;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.Date;

public class Proyectos {

    private BaseDatos base;
    private int id;
    private String descripcion, ubicacion, fecha;
    private float presupuesto;
    protected String error;

    public Proyectos (Activity activity){ base = new BaseDatos(activity,"civil",null,1);}

    public Proyectos(int id, String descripcion, String ubicacion, String fecha, float presupuesto){
        this.id = id;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.presupuesto = presupuesto;
    }


    public Proyectos[] consultar(){
        Proyectos[] resultado = null;

        try{
            SQLiteDatabase transaccionConsultar = base.getReadableDatabase();
            Cursor c = transaccionConsultar.rawQuery("SELECT * FROM Proyectos",null);

            if(c.moveToFirst()){
                resultado = new Proyectos[c.getCount()];
                int i = 0;
                do{
                    int id = c.getInt(0);
                    String descripcion = c.getString(1);
                    String ubicacion = c.getString(2);
                    String fecha = c.getString(3);
                    float presupuesto = c.getFloat(4);
                    resultado[i] = new Proyectos(id,descripcion,ubicacion,fecha,presupuesto);
                    i++;

                }while (c.moveToNext());
            }else{
                error = "Error! No hay datos que mostrar.";
            }
            transaccionConsultar.close();

        }catch(SQLiteException e){
            error = e.getMessage();
            return null;
        }
        return resultado;
    }

    public Proyectos[] consultar(String clave){
        Proyectos[] resultado = null;

        try{
            SQLiteDatabase transaccionConsultar = base.getReadableDatabase();
            String[] vector = {clave,clave};
            Cursor c = transaccionConsultar.rawQuery("SELECT * FROM Proyectos WHERE DESCRIPCION = ? OR IDPROYECTO = ?", vector);


            if(c.moveToFirst()){
                resultado = new Proyectos[c.getCount()];
                int i = 0;
                do{
                    int id = c.getInt(0);
                    String descripcion = c.getString(1);
                    String ubicacion = c.getString(2);
                    String fecha = c.getString(3);
                    float presupuesto = c.getFloat(4);
                    resultado[i] = new Proyectos(id,descripcion,ubicacion,fecha,presupuesto);
                    i++;

                }while (c.moveToNext());
            }else{
                error = "Error! No hay datos que mostrar.";
            }
            transaccionConsultar.close();
        }catch(SQLiteException e){
            error = e.getMessage();
            return null;
        }
        return resultado;
    }

    public boolean insertar (Proyectos proyecto){

        try{
            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", proyecto.getDescripcion());
            datos.put("UBICACION",proyecto.getUbicacion());
            datos.put("FECHA",proyecto.getFecha());
            datos.put("PRESUPUESTO",proyecto.getPresupuesto());

            long resultado = transaccionInsertar.insert("Proyectos","IDPROYECTO", datos);

            transaccionInsertar.close();

            if(resultado == -1){
                return false;
            }
        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }

        return true;
    }

    public boolean eliminar (Proyectos proyecto){
        int resultado;
        try{
            String[] vector = {proyecto.getId()+""};
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();

            resultado = transaccionEliminar.delete("Proyectos", "IDPROYECTO =?", vector );

            transaccionEliminar.close();

            if(resultado==-1){
                return false;
            }
        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }

        return resultado>0;
    }

    public boolean actualizar(Proyectos proyecto){

        try{
            String[] vector = {proyecto.getId()+""};
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", proyecto.getDescripcion());
            datos.put("UBICACION", proyecto.getUbicacion());
            datos.put("FECHA",proyecto.getFecha());
            datos.put("PRESUPUESTO",proyecto.getPresupuesto());

            SQLiteDatabase transaccionActualizar = base.getWritableDatabase();

            long resultado = transaccionActualizar.update("Proyectos", datos,"IDPROYECTO = ?",vector);

            transaccionActualizar.close();

            if(resultado == -1){
                return false;
            }

        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }

        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }
}
