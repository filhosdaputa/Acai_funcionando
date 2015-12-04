package com.example.verdemusset.acai_9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Verde Musset on 25/11/2015.
 */
public class ConnectionDB extends SQLiteOpenHelper {

    public static String sfenvio;
    public static String stitulo;

    //String finales donde caeran los string que se guardaran en la BD
    //String de Tabla Mensajes:
    public static final String CAIDMensaje = "_IdMensaje";
    public static final String CAFechaEnvio = "_Fecha de envio";
    public static final String CATitulo = "_Titulo del mensaje";
    public static final String CACuerpo = "_Cuerpo del mensaje";
    public static final String CAFActividad = "_Fecha de la actividad";
    public static final String CAFCaducidad = "_Fecha de caducidad";

    //String de Tabla Persona:
    public static final String CAIDPersona = "_Id_Persona";
    public static final String CANombre = "_Nombre";
    public static final String CAApellido1 = "_Apellido1";
    public static final String CAApellido2 = "_Apellido2";
    public static final String CACorreo = "_Correo";
    public static final String CAFNacimiento = "_Fecha de Nacimiento";

    //Nombre de la base de datos y tabla

    private static final String BDACAI = "BDACAI";
    private static final String TMensaje = "TMensaje";
    private static final String TPersona = "TPersona";

    public ConnectionDB(Context context) {
        super(context, BDACAI, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE" + TMensaje + " (" + CAIDMensaje + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CAFechaEnvio + " TEXT," + CATitulo + " TEXT," + CACuerpo + " TEXT," + CAFActividad +
                " TEXT," + CAFCaducidad + " TEXT)");
       /* db.execSQL("CREATE TABLE" + TPersona + " (" + CAIDPersona + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CANombre + " TEXT," + CAApellido1 + " TEXT," + CAApellido2 + " TEXT," + CACorreo +
                " TEXT," + CAFNacimiento + " TEXT)");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TMensaje);
        //db.execSQL("DROP TABLE IF EXISTS" + TPersona);
        onCreate(db);
    }

    public void close() {
        this.close();
    }



    public void ingresarMensajes(String fenvio, String titulo,
                                String cuerpo, String factividad, String fcaducidad) {

        ContentValues insertValMensaje = new ContentValues();

        insertValMensaje.put(CAFechaEnvio, fenvio);
        insertValMensaje.put(CATitulo, titulo);
        insertValMensaje.put(CACuerpo, cuerpo);
        insertValMensaje.put(CAFActividad, factividad);
        insertValMensaje.put(CAFCaducidad, fcaducidad);

        try {

            this.getWritableDatabase().insert(TMensaje, null, insertValMensaje);
        } catch (Exception e) {
            System.out.println("NO se pudo escribir en la BD");


        }
        System.out.println("Se guardaron en la base de datos");
        close();

    }


    // falta hacer un metodo identico al anterior pero con la tabla persona

    public Cursor insertarDatosArreglo() {


        String columnas[] = {CAIDMensaje, CAFechaEnvio, CATitulo, CACuerpo, CAFActividad, CAFCaducidad};
        Cursor c = this.getReadableDatabase().query(TMensaje, columnas, null, null, null, null, null);

        return c;


    }

    // Quedaste aqui+++++


}