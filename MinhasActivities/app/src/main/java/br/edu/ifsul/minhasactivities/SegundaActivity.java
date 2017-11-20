package br.edu.ifsul.minhasactivities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SegundaActivity extends AppCompatActivity {

    Button btnSalvar;
    EditText txtNome;
    EditText txtSenha;
    EditText txtEmail;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        txtNome = (EditText) findViewById(R.id.txtNome);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        txtEmail = (EditText) findViewById(R.id.txtEmail);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contato contato = new Contato();
                contato.setNome(txtNome.getText().toString());
                contato.setSenha(txtSenha.getText().toString());
                contato.setEmail(txtEmail.getText().toString());

                DatabaseHandler db = new DatabaseHandler(context);
                db.addContato(contato);
                db.getContatos();
                db.close();
            }
        });



    }

    public void ListaTela(View view){
        Intent intent = new Intent(this, ListaActivity.class);
        startActivity(intent);
    }
}
