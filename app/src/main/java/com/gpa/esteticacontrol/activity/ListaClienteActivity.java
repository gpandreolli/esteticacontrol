package com.gpa.esteticacontrol.activity;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gpa.esteticacontrol.R;
import com.gpa.esteticacontrol.adapter.AdapterCliente;
import com.gpa.esteticacontrol.database.EsteticaDatabase;
import com.gpa.esteticacontrol.model.Cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListaClienteActivity extends AppCompatActivity {



    private ArrayList<Cliente> clientes ;
    private ListView listViewClientes;
    SimpleDateFormat formatter;

    private ActionMode actionMode;

    private AdapterCliente adapterClientes ;
    private int posicao;
    private View viewSelecionada;
    public static final int ALTERAR = 2;
    public static final int NOVO = 1;
    EsteticaDatabase database;


    private ActionMode.Callback actionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater =mode.getMenuInflater();
            inflater.inflate(R.menu.cliente_menu_acoes,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.menuEditarCliente:
                    editarCliente();
                    mode.finish();
                    return true;
                case R.id.menuExcluirCliente:
                    excluirCliente();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            if (viewSelecionada != null){
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }

            actionMode         = null;
            viewSelecionada    = null;

            listViewClientes.setEnabled(true);

        }
    };


    public ListaClienteActivity() throws ParseException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);
        clientes = new ArrayList<>();
        listViewClientes = findViewById(R.id.listViewClientes);

        database = EsteticaDatabase.getDatabase(this);
        clientes = (ArrayList<Cliente>) database.clienteDao().queryAll();
        adapterClientes = new AdapterCliente(ListaClienteActivity.this, R.layout.layout_item_cliente,clientes);
        listViewClientes.setAdapter(adapterClientes);





        View header = (View) getLayoutInflater().inflate(R.layout.layout_item_cliente, null);

        //popularListaClientes();
        listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            String indicacao = "Não";

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente cliente = (Cliente) listViewClientes.getItemAtPosition(position);
                formatter = new SimpleDateFormat("dd/MM/yyyy");
                if(cliente.isIndicacao()){
                     indicacao = "Sim";
                }

                String texto = null;
                texto = "Nome: "+cliente.getNome()+
                        "\n Sobrenome: "+cliente.getSobreNome()+
                        "\n Gênero: "+ cliente.getGenero()+
                        "\n Data Nascimento: "+ formatter.format(cliente.getDtNasc())+
                        "\n Foi indicação: "+indicacao+
                        "\n Onde Encontrou: "+cliente.getOndeEncontrou();

                Toast.makeText(ListaClienteActivity.this, texto, Toast.LENGTH_SHORT).show();
            }
        });

        listViewClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null){
                    return false;
                }

                posicao = position;

                view.setBackgroundColor(Color.DKGRAY);

                viewSelecionada = view;

                listViewClientes.setEnabled(false);

                actionMode = startSupportActionMode(actionModeCallBack);

                return true;
            }
        });




    }

    private void popularListaClientes() {

        clientes = (ArrayList<Cliente>) database.clienteDao().queryAll();
        listViewClientes.setAdapter(adapterClientes);

  }

    private void adicionar( AdapterCliente adapterCliente) {
        Intent intent = new Intent(this,ClienteActivity.class);
        startActivityForResult(intent,NOVO);
        adapterCliente.notifyDataSetChanged();
    }

    private void openActivitySobre() {
        Intent intent = new Intent(this, SobreActivity.class);
        startActivity(intent);
    }
    
    public void editarCliente(){
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        Cliente cliente = (Cliente) listViewClientes.getItemAtPosition(posicao);
        Intent intent = new Intent(this,ClienteActivity.class);
        intent.putExtra("Genero", cliente.getGenero());
        intent.putExtra("Nome", cliente.getNome());
        intent.putExtra("SobreNome", cliente.getSobreNome());
        intent.putExtra("DataNasc", formatter.format(cliente.getDtNasc()));
        intent.putExtra("OndeEncontrou", cliente.getOndeEncontrou());
        intent.putExtra("Indicacao", cliente.isIndicacao());
        intent.putExtra("Id", cliente.getId());
        intent.putExtra("MODO", ALTERAR);

        startActivityForResult(intent,ALTERAR);


    }

    private void excluirCliente() {
        Cliente cliente = clientes.get(posicao);
        clientes.remove(posicao);
        database.clienteDao().delete(cliente);
        adapterClientes.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        listViewClientes.requestLayout();
        if(resultCode == Activity.RESULT_OK){

            Bundle bundle = data.getExtras();

            if(bundle!= null){

               if(bundle.getInt("MODO") == ALTERAR){
                   //formatter = new SimpleDateFormat("dd/MM/yyyy");
                   Cliente cliente = (Cliente) listViewClientes.getItemAtPosition(posicao);
                   cliente.setId(bundle.getLong("id"));
                   cliente.setNome(bundle.getString("nome"));
                   cliente.setSobreNome(bundle.getString("sobreNome"));
                   try {
                       cliente.setDtNasc(formatter.parse(bundle.getString("dtNasc")));
                   } catch (ParseException e) {
                       throw new RuntimeException(e);
                   }
                   cliente.setGenero(bundle.getString("genero"));
                   cliente.setIndicacao(bundle.getBoolean("indicacao"));
                   cliente.setOndeEncontrou(bundle.getString("ondeEncontrou"));

                   EsteticaDatabase database = EsteticaDatabase.getDatabase(this);

                   database.clienteDao().update(cliente);

                   adapterClientes.notifyDataSetChanged();
                   posicao = -1;

               }else{
                   try {
                       addClientes(data.getExtras());

                   } catch (ParseException e) {
                       throw new RuntimeException(e);
                   }

               }
                if (clientes.size()> 0){
                    adapterClientes.notifyDataSetChanged();
                }

            }
        }else {
            Toast.makeText(this, "Cadastro cancelado", Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addClientes(Bundle bundle) throws ParseException {
        Cliente cliente = new Cliente();
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        EsteticaDatabase database = EsteticaDatabase.getDatabase(this);



        cliente.setId(bundle.getLong("id"));
        cliente.setNome(bundle.getString("nome"));
        cliente.setSobreNome(bundle.getString("sobreNome"));
        cliente.setDtNasc(formatter.parse(bundle.getString("dtNasc")));
        cliente.setGenero(bundle.getString("genero"));
        cliente.setIndicacao(bundle.getBoolean("indicacao"));
        cliente.setOndeEncontrou(bundle.getString("ondeEncontrou"));
        //clientes.add(cliente);
        database.clienteDao().insert(cliente);
        popularListaClientes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cliente_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuItemAdicionar:
                adicionar(adapterClientes);
                return true;
            case R.id.menuItemSobre:
                openActivitySobre();
                return true;
            default:
               return super.onOptionsItemSelected(item);
        }
    }
}