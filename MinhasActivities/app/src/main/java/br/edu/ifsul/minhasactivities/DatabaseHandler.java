package br.edu.ifsul.minhasactivities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pc on 08/11/2017.
 */

public class DatabaseHandler  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "projeto";
    private static final String TABLE_CONTATO = "contato";
    private static final String KEY_ID = "id";
    private static final String NOME = "nome";
    private static final String EMAIL = "email";
    private static final String SENHA = "senha";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COLETA_TABLE = "CREATE TABLE " + TABLE_CONTATO + " ("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + NOME + " TEXT,"
                + EMAIL + " TEXT,"
                + SENHA + " TEXT)";
        db.execSQL(CREATE_COLETA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTATO);
        onCreate(db);
    }

    public void addContato(Contato contato) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME, contato.getNome());
        values.put(EMAIL, contato.getEmail());
        values.put(SENHA, contato.getSenha());

        db.insert(TABLE_CONTATO, null, values);
        db.close();
    }

    public Contato getContato(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTATO, new String[]{KEY_ID,
                        NOME, SENHA, EMAIL}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Contato contato = new Contato();
        contato.setId(Integer.parseInt(cursor.getString(0)));
        contato.setNome(cursor.getString(1));
        contato.setSenha(cursor.getString(2));
        contato.setEmail(cursor.getString(3));

        return contato;
    }


    public List<Contato> getContatos() {
        List<Contato> coletaList = new ArrayList<Contato>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTATO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Contato contato = new Contato();
                contato.setId(Integer.parseInt(cursor.getString(0)));
                contato.setNome(cursor.getString(1));
                contato.setSenha(cursor.getString(3));
                contato.setEmail(cursor.getString(2));

                coletaList.add(contato);
            } while (cursor.moveToNext());
        }
        return coletaList;
    }

    public String getLastId() {
        String id = "0";

        String countQuery = "select max(id)+1 as masx_id from " + TABLE_CONTATO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        if (id == null || cursor.getCount() == 0) {
            id = "1";
        }

        cursor.close();

        return id;

    }

    public List<Contato> demo() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Contato> contatos = this.getContatos();
        for (Contato cn : contatos) {
            String log = "Id: " + cn.getId() + " ,Nome: " + cn.getNome() + " ,Email: " + cn.getEmail() + " ,Senha: " + cn.getSenha();
            Log.i("Database 1", log);
        }

        return contatos;

    }
}