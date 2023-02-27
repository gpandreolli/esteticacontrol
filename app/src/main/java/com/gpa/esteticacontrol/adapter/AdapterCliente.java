package com.gpa.esteticacontrol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.gpa.esteticacontrol.R;
import com.gpa.esteticacontrol.model.Cliente;

import java.util.ArrayList;

public class AdapterCliente extends ArrayAdapter<Cliente> {

    private Context context;
    int layoutResourceId;
    private ArrayList<Cliente> listaClientes;


    public AdapterCliente(@NonNull Context context, int layoutResourceId, ArrayList<Cliente> listaClientes) {
        super(context, layoutResourceId,listaClientes);
        this.context = context;
        this.listaClientes = listaClientes;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Cliente cliente = this.listaClientes.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.layout_item_cliente,null);

        TextView txtNomeCliente = (TextView) convertView.findViewById(R.id.txtNomeCliente_item);
        txtNomeCliente.setText(cliente.getNome());

        TextView txtSobrenomeCliente = (TextView) convertView.findViewById(R.id.txtSobreNomeCliente_item);
        txtSobrenomeCliente.setText(cliente.getSobreNome());

        return convertView;
    }
}
