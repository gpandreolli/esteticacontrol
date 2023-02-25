package com.gpa.esteticacontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ClienteActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtNomeCliente;
    private EditText edtSobrenome;
    private EditText edtDataNascimento;
    private RadioButton rdbFeminino;
    private RadioButton rdbMasculino;
    private CheckBox chkIndicacao;
    private Spinner spnOndeEncontrou;
    private Button btnSalvar;
    private Button btnLimpar;
    private String nome, sobrenome;

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


    }

    public void limparCampos(View view){
        edtNomeCliente.setText(null);
        edtSobrenome.setText(null);
        edtDataNascimento.setText(null);
        spnOndeEncontrou.setSelected(false);
        chkIndicacao.setSelected(false);
        rdbFeminino.setSelected(true);
        rdbMasculino.setSelected(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLimpar:
                limparCampos(v);
                break;

            case R.id.btnSalvar:
                salvar(v);
                break;
        }

    }

    private void salvar(View v) {
        String genero;
        Context contexto = getApplicationContext();
        if(rdbMasculino.isChecked()){
            genero = "masculino";
        }else{
            genero = "feminino";
        }
        String textToast = "nome:"+edtNomeCliente.getText().toString()+
                "sobronome: "+edtSobrenome.getText().toString()+
                "data Nasc.:"+edtDataNascimento.getText().toString()+
                "Onde Encontrou"+spnOndeEncontrou.getSelectedItem().toString()+
                "indicaçao: "+chkIndicacao.getText().toString()+" genero:"+genero;

        Toast toast = Toast.makeText(contexto,textToast
            , Toast.LENGTH_SHORT);
        toast.show();
    }


}