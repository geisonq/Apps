package br.edu.ifsul.incremento_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtNumero;
    Button btn;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumero =  (EditText) findViewById(R.id.txtNumero);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = Integer.parseInt(txtNumero.getText().toString());
                txtNumero.setText(String.valueOf(++total));

                btn.setVisibility(View.INVISIBLE);
            }
        });
    }


}