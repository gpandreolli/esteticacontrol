package com.gpa.esteticacontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.gpa.esteticacontrol.model.Cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

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
                try {
                    salvar(v);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
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

    private void salvar(View v) throws ParseException {
        String genero;
        boolean indicacao = false;
        boolean validaDados = false;
        long idRandom = new Random().nextLong();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        if(chkIndicacao.isChecked()){
            indicacao = true;
        }
        if(rdbMasculino.isChecked()){
            genero = "masculino";
        }else{
            genero = "feminino";
        }
        validaDados = validaFormulario();

        String dataNasc = edtDataNascimento.getText().toString();
        System.out.println(dataNasc);

        if(validaDados){
            Intent intent = new Intent();
            intent.putExtra("id", idRandom);
            intent.putExtra("nome", edtNomeCliente.getText().toString());
            intent.putExtra("sobreNome", edtSobrenome.getText().toString());
            intent.putExtra("dtNasc", dataNasc);
            intent.putExtra("genero", genero);
            intent.putExtra("indicacao", indicacao);
            intent.putExtra("ondeEncontrou", spnOndeEncontrou.getSelectedItem().toString());

            setResult(Activity.RESULT_OK,intent);
            finish();

        }else{
            Toast.makeText(ClienteActivity.this,"Favor preencher os dados corretamente", Toast.LENGTH_LONG).show();
        }

    }

    private boolean validaFormulario() {
        boolean retorno = false;

        if(!TextUtils.isEmpty(edtNomeCliente.getText().toString())){
            retorno = true;
        }else{
            edtNomeCliente.setError("Favor preencher o nome");
            edtNomeCliente.requestFocus();
        }

        if(!TextUtils.isEmpty(edtSobrenome.getText().toString())){
            retorno = true;
        }else{
            edtSobrenome.setError("Favor preencher o sobrenome");
            edtSobrenome.requestFocus();
        }

        if(!TextUtils.isEmpty(edtDataNascimento.getText().toString())){
            retorno = true;
        }else{
            edtDataNascimento.setError("*");
            edtDataNascimento.requestFocus();
        }

        return retorno;
    }


}