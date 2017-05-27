package com.example.rodrigoantunes.appencomenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rodrigoantunes.appencomenda.Pedido;

import java.util.List;

/**
 * Created by RodrigoAntunes on 23/05/17.
 */

public class PedidoAdapter extends BaseAdapter {

    private List<Pedido> listapedidos;
    private Context contexto;


    public PedidoAdapter(List<Pedido> listapedidos, Context contexto) {
        this.listapedidos = listapedidos;
        this.contexto = contexto;
    }

    //public float pedidoValor(){

//    }


    @Override
    public int getCount() {
        return listapedidos.size();
    }

    @Override
    public Object getItem(int position) {
        return listapedidos.get(position)   ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Pedido pedido  = listapedidos.get(position);

        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_detalhe,null);

        TextView textData = (TextView) view.findViewById(R.id.txtData);
        textData.setText(pedido.getEntrega());

        TextView textContato = (TextView) view.findViewById(R.id.txtFone);
        textContato.setText(pedido.getContato());

        TextView textCliente = (TextView) view.findViewById(R.id.txtCliente);
        textCliente.setText(pedido.getCliente());

        TextView textValor = (TextView) view.findViewById(R.id.txtValor);
        textValor.setText(pedido.getValor().toString());

        TextView textPedido = (TextView) view.findViewById(R.id.txtDetPedido);
        textPedido.setText(pedido.getPedido());

        return view;

    }
}
