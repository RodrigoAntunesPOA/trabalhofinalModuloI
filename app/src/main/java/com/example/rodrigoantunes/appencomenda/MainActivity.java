package com.example.rodrigoantunes.appencomenda;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<Pedido> listaPedido=new ArrayList<>();
    public PedidoBD pedidoBD;
    PedidoAdapter adaptador;
    ListView listViewPedido;

    Integer iPagos=0, iPendentes=1, iQtdeTotal=0;
    Float fValorTotal;

    TextView txtTotal , txtQtdTotal ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarTela();
        /*
        pedidoBD = new PedidoBD(this);

        listViewPedido = (ListView) findViewById(R.id.listView);

        adaptador= new PedidoAdapter(listaPedido, this);

        listViewPedido.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Log.i("tagBA","Entrou");
                        Pedido pedido = listaPedido.get(position);

                        Intent it = new Intent(MainActivity.this,  PedidoEdicao.class);
                        it.putExtra("pedido",pedido);
                        startActivity(it);
                    }
                }
        );
        */

    }

    public void carregarTela(){

        pedidoBD = new PedidoBD(this);

        listaPedido.clear();

        listaPedido = pedidoBD.listar(iPendentes, iPagos);

        listViewPedido = (ListView) findViewById(R.id.listView);

        adaptador= new PedidoAdapter(listaPedido, this);

        listViewPedido.setAdapter(adaptador);


        listViewPedido.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Log.i("tagBA","Entrou");
                        Pedido pedido = listaPedido.get(position);

                        Intent it = new Intent(MainActivity.this,  PedidoEdicao.class);
                        it.putExtra("pedido",pedido);
                        startActivity(it);
                    }
                }
        );



    }


    public void listarPedidos(View view) {

        CheckBox chkPendentes = (CheckBox) findViewById(R.id.chkFilPendentes);

        CheckBox chkPagos = (CheckBox) findViewById(R.id.chkFilPagos);

        txtTotal = (TextView) findViewById(R.id.txtTotal);

        txtQtdTotal = (TextView) findViewById(R.id.txtqtdTotal);


        if (chkPagos.isChecked()) {
            iPagos = 1;
        } else
            iPagos = 0;

        if (chkPendentes.isChecked()) {
            iPendentes = 1;
        } else
            iPendentes = 0;

        //listaPedido.clear();
        //listaPedido.add(new Pedido("Cliente","10/10/2010","99760000","isso e aquilo",Float.parseFloat("5.99"), bPagos , bPendentes));
        //listaPedido.add(new Pedido("Joao","15/11/2017","3344556677", "bolo e espinafre bolo e espinafre  bolo e espinafre bolo e espinafre bolo e espinafre bolo e espinafre bolo e espinafre ", Float.parseFloat("100.99"), Boolean.parseBoolean("False"),Boolean.parseBoolean("True")));

        //Solcita consulta de Pedidos
        /*
        listaPedido.clear();

        listaPedido = pedidoBD.listar(iPendentes, iPagos);

        //listViewPedido = (ListView) findViewById(R.id.listView);

        adaptador= new PedidoAdapter(listaPedido, this);

        listViewPedido.setAdapter(adaptador);

        adaptador.notifyDataSetChanged();
        */

        carregarTela();

        if (listaPedido.size() == 0) {

            txtTotal.setText("0,00");
            txtQtdTotal.setText("0");

            Toast.makeText(this, "Nenhum pedido encontrado!", Toast.LENGTH_LONG)
                    .show();
        } else {
            iQtdeTotal=adaptador.getCount();
            txtQtdTotal.setText(iQtdeTotal.toString());
            txtTotal.setText(pedidoBD.getfValorTotal().toString());
        }

    }

    public void abrirCadastro(View view){

        Intent it = new Intent(this, PedidoInclusao.class);

        startActivity(it);

        adaptador.notifyDataSetChanged();
    }

    public void sairApp (View view){

        finish() ;

    }

}
