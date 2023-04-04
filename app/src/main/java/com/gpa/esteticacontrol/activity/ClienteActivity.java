package com.gpa.esteticacontrol.activity;

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

import com.gpa.esteticacontrol.R;

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
    private String MODO = "MODO";
    public static final int    NOVO    = 1;
    public static final int    ALTERAR = 2;
    private int modo;

    private String nome;
    private String sobreNome;
    private String genero;
    private Boolean indicacao;
    private String ondeEncontrou;
    private String dataNascimento;

    private Long idCliente;

    SimpleDateFormat formatter;

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

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            modo = bundle.getInt(MODO, NOVO);

            if(modo == NOVO){




            }else{
            //    formatter = new SimpleDateFormat("dd/MM/yyyy");
                nome = bundle.getString("Nome");
                sobreNome = bundle.getString("SobreNome");
                dataNascimento = bundle.getString("DataNasc");
                indicacao = bundle.getBoolean("Indicacao");
                genero = bundle.getString("Genero");
                ondeEncontrou = bundle.getString("OndeEncontrou");
                idCliente = bundle.getLong("Id");

                edtNomeCliente.setText(nome);
                edtSobrenome.setText(sobreNome);
                edtDataNascimento.setText(dataNascimento);
                chkIndicacao.setChecked(indicacao);

                if(genero.equals("feminino")){
                    rdbFeminino.setChecked(true);
                    rdbMasculino.setChecked(false);
                }else{
                    rdbFeminino.setChecked(false);
                    rdbMasculino.setChecked(true);
                }

                switch (ondeEncontrou){
                    case "Google":
                        spnOndeEncontrou.setSelection(1);
                        break;
                    case "Instagram":
                        spnOndeEncontrou.setSelection(2);
                        break;
                    case "Facebook":
                        spnOndeEncontrou.setSelection(3);
                        break;
                    case "Linkedin":
                      spnOndeEncontrou.setSelection(4);
                        break;
                    default:
                        spnOndeEncontrou.setSelection(0);
                }
            }
        }

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

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
        finish();
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
        if (modo == ALTERAR ){

        }
        Intent intent = new Intent();
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


        if(validaDados && modo != ALTERAR){

            intent.putExtra("id", idRandom);
            intent.putExtra("nome", edtNomeCliente.getText().toString());
            intent.putExtra("sobreNome", edtSobrenome.getText().toString());
            intent.putExtra("dtNasc", dataNasc);
            intent.putExtra("genero", genero);
            intent.putExtra("indicacao", indicacao);
            intent.putExtra("ondeEncontrou", spnOndeEncontrou.getSelectedItem().toString());

            setResult(Activity.RESULT_OK,intent);
            finish();

        }else if (modo == ALTERAR && validaDadosAlterados()){
            intent.putExtra("id", idRandom);
            intent.putExtra("nome", edtNomeCliente.getText().toString());
            intent.putExtra("sobreNome", edtSobrenome.getText().toString());
            intent.putExtra("dtNasc", dataNasc);
            intent.putExtra("genero", genero);
            intent.putExtra("indicacao", indicacao);
            intent.putExtra("ondeEncontrou", spnOndeEncontrou.getSelectedItem().toString());
            intent.putExtra(MODO,ALTERAR);

            setResult(Activity.RESULT_OK,intent);
            finish();
        }else{
            Toast.makeText(ClienteActivity.this,"Favor preencher os dados corretamente", Toast.LENGTH_LONG).show();
        }

    }

    private boolean validaDadosAlterados() {
        String generoNovo;
        boolean indicacaoNovo =false;
        if(rdbMasculino.isChecked()){
            generoNovo = "masculino";
        }else{
            generoNovo = "feminino";
        }

        if(chkIndicacao.isChecked()){
            indicacaoNovo = true;
        }

        if(nome.equals(edtNomeCliente.getText())){
            if(sobreNome.equals(edtSobrenome.getText())){
                if(dataNascimento.equals(edtDataNascimento.getText())){
                    if(genero.equals(generoNovo)){
                        if(indicacaoNovo == indicacao){
                            if(ondeEncontrou.equals(spnOndeEncontrou.getSelectedItem().toString())){
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean validaFormulario() {
        boolean retorno = false;

        if(!TextUtils.isEmpty(edtNomeCliente.getText().toString())){
            retorno = true;
        }else{
            edtNomeCliente.setError("Favor preencher o nome");
            edtNomeCliente.requestFocus();
            retorno = false;
        }

        if(!TextUtils.isEmpty(edtSobrenome.getText().toString())){
            retorno = true;
        }else{
            edtSobrenome.setError("Favor preencher o sobrenome");
            edtSobrenome.requestFocus();
            retorno = false;
        }

        if(!TextUtils.isEmpty(edtDataNascimento.getText().toString())){
            retorno = true;
        }else{
            edtDataNascimento.setError("Favor informar a data de nascimento");
            edtDataNascimento.requestFocus();
            retorno = false;
        }

        return retorno;
    }


}