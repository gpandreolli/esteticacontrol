package com.gpa.esteticacontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ClienteActivity extends AppCompatActivity{

    private EditText edtNomeCliente;
    private EditText edtSobrenome;
    private EditText edtDataNascimento;
    private RadioButton rdbFeminino;
    private RadioButton rdbMasculino;
    private CheckBox chkIndicacao;
    private Spinner spnOndeEncontrou;
    private Button btnSalvar;
    private Button btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        spnOndeEncontrou = (Spinner) findViewById(R.id.spnOndeEncontrou);
        edtNomeCliente = (EditText) findViewById(R.id.edtNomeCliente);
        edtSobrenome = (EditText) findViewById(R.id.edtSobrenome);
        edtDataNascimento = (EditText) findViewById(R.id.edtDataNasc);
        chkIndicacao = (CheckBox)findViewById(R.id.chkIndicacao);
        rdbFeminino = (RadioButton) findViewById(R.id.rdbFeminino);
        rdbMasculino = (RadioButton)findViewById(R.id.rdbMasculino);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        Object ondeEncontrouSelectedItem = spnOndeEncontrou.getSelectedItem();
        ondeEncontrouSelectedItem.toString();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar(v);
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos(v);
            }
        });


    }

    public void limparCampos(View view){
        edtNomeCliente.setText(null);
        edtSobrenome.setText(null);
        edtDataNascimento.setText(null);
        chkIndicacao.setChecked(false);
        rdbFeminino.setChecked(false);
        rdbMasculino.setChecked(false);
    }

    private void salvar(View v) {
        String genero;

        if(rdbMasculino.isChecked()){
            genero = "masculino";
        }else{
            genero = "feminino";
        }
        String textToast = "nome:"+edtNomeCliente.getText().toString()+
                "\nsobronome: "+edtSobrenome.getText().toString()+
                "\ndata Nasc.:"+edtDataNascimento.getText().toString()+
                "\nOnde Encontrou"+spnOndeEncontrou.getSelectedItem().toString()+
                "\nindicaçao: "+chkIndicacao.getText().toString()+
                "\ngenero:"+genero;

      Toast.makeText(ClienteActivity.this,textToast, Toast.LENGTH_LONG).show();

    }


}