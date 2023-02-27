package com.gpa.esteticacontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.gpa.esteticacontrol.adapter.AdapterCliente;
import com.gpa.esteticacontrol.model.Cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListaClienteActivity extends AppCompatActivity {



    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ListView listViewClientes;
    private Button btnAdicionar, btnSobre;


    public ListaClienteActivity() throws ParseException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);


        listViewClientes = findViewById(R.id.listViewClientes);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnSobre = findViewById(R.id.btnSobre);

        listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            String indicacao = "Não";
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente cliente = (Cliente) listViewClientes.getItemAtPosition(position);
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

        try {
            addClientes();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        AdapterCliente adapterClientes = new AdapterCliente(ListaClienteActivity.this, R.layout.layout_item_cliente,clientes);
        View header = (View) getLayoutInflater().inflate(R.layout.layout_item_cliente, null);
        listViewClientes.setAdapter(adapterClientes);

        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySobre(v);
            }
        });







    }

    private void openActivitySobre(View v) {
        Intent intent = new Intent(this, SobreActivity.class);
        startActivity(intent);
    }

    private void addClientes() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Cliente c1 = new Cliente(1L,"Maria","Priscote",formatter.parse("01/01/1995"),"Feminino", false,"Google");
        Cliente c2 = new Cliente(2L,"Joana","Trello",formatter.parse("25/05/2005"),"Feminino", false,"Google");
        Cliente c3 = new Cliente(3L,"Clesia","Sofjoska",formatter.parse("05/08/2001"),"Feminino", true,"");
        Cliente c4 = new Cliente(4L,"Anotelia","Krapinkowska",formatter.parse("23/01/2003"),"Feminino", false,"Instagram");
        Cliente c5 = new Cliente(5L,"Sophia","Silva",formatter.parse("17/10/1999"),"Feminino", false,"Google");
        Cliente c6 = new Cliente(6L,"Carolina","Silva",formatter.parse("17/05/1998"),"Feminino", false,"Instagram");
        Cliente c7 = new Cliente(7L,"Cristina","Souza",formatter.parse("19/11/2003"),"Feminino", false,"Google");
        Cliente c8 = new Cliente(8L,"Talita","Anturiana",formatter.parse("04/11/2000"),"Feminino", false,"Facebook");
        Cliente c9 = new Cliente(9L,"Kalinkca","Cletosia",formatter.parse("04/04/1995"),"Feminino", false,"Google");
        Cliente c10 = new Cliente(10L,"Pamela","Pereira",formatter.parse("05/06/1993"),"Feminino", true,"");
        clientes.add(c1);
        clientes.add(c2);
        clientes.add(c3);
        clientes.add(c4);
        clientes.add(c5);
        clientes.add(c6);
        clientes.add(c7);
        clientes.add(c8);
        clientes.add(c9);
        clientes.add(c10);
    }
}