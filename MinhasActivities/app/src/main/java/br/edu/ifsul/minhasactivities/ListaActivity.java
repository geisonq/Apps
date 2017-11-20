package br.edu.ifsul.minhasactivities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListaActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);


        ListView lista = (ListView) findViewById(R.id.lista);

        DatabaseHandler db = new DatabaseHandler(context);


        List<Contato> contatos = db.getContatos();
        ArrayAdapter<Contato> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contatos);
        lista.setAdapter(adapter);


        db.close();
    }
}
