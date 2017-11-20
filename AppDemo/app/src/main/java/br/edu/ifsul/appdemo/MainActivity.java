package br.edu.ifsul.appdemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnOk;
    private EditText txtNome;
    private TextView textNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Criar
        btnOk = (Button) findViewById(R.id.btnOk);
        txtNome = (EditText) findViewById(R.id.txtNome);
        textNome =  (TextView) findViewById(R.id.textNome);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast =  Toast.makeText(MainActivity.this, "Oi " + txtNome.getText() + "!", Toast.LENGTH_LONG);
                //toast.setGravity(Gravity.TOP|Gravity.RIGHT, 100, 250);
                toast.show();
            }
        });


    }
}
