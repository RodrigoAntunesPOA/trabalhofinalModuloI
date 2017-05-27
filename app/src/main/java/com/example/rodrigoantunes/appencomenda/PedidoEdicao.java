package com.example.rodrigoantunes.appencomenda;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class PedidoEdicao extends AppCompatActivity  {

    Pedido pedidoEditar;

    EditText textQuemAlt, textQdoAlt, textContatoAlt, textPedidoAlt,textQtoAlt;
    CheckBox chekPagoAlt, chekEntregueAlt;
    PedidoBD pedidoBD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_edicao);

        pedidoBD=new PedidoBD(this);

        Intent it = getIntent();
        this.pedidoEditar = (Pedido) it.getSerializableExtra("pedido");

        textQuemAlt= (EditText) findViewById(R.id.txtQuemAlt);
        textQuemAlt.setText(pedidoEditar.getCliente().toString());

        textQdoAlt= (EditText) findViewById(R.id.txtQuandoAlt);
        textQdoAlt.setText(pedidoEditar.getEntrega().toString());

        textPedidoAlt= (EditText) findViewById(R.id.txtPedidoAlt);
        textPedidoAlt.setText(pedidoEditar.getPedido().toString());

        textQtoAlt= (EditText) findViewById(R.id.txtQuandoAlt);
        textQtoAlt.setText(pedidoEditar.getValor().toString());

        textContatoAlt= (EditText) findViewById(R.id.txtContatoAlt);
        textContatoAlt.setText(pedidoEditar.getContato().toString());

        chekEntregueAlt= (CheckBox) findViewById(R.id.chkEntregueAlt);
        if (pedidoEditar.getEntregue()==1){
            chekEntregueAlt.setChecked(true);
        }
        else{
            chekEntregueAlt.setChecked(false);
        }

        chekPagoAlt= (CheckBox) findViewById(R.id.chkPagoAlt);
        if (pedidoEditar.getPago()==1){
            chekPagoAlt.setChecked(true);
        }
        else{
            chekPagoAlt.setChecked(false);
        }

    }

    public void excluir (View view){

        pedidoBD.excluir(pedidoEditar.getCodigo());

        Toast.makeText(this, "Pedido excluído com sucesso!", Toast.LENGTH_LONG)
                .show();
    }

    public void salvarEdicao (View view){

        pedidoEditar.setCliente(textQuemAlt.getText().toString());
        pedidoEditar.setEntrega(textQdoAlt.getText().toString());
        pedidoEditar.setPedido(textPedidoAlt.getText().toString());
        pedidoEditar.setValor(Float.parseFloat(textQtoAlt.getText().toString()));
        pedidoEditar.setContato(textContatoAlt.getText().toString());

        if (chekEntregueAlt.isChecked()){
            pedidoEditar.setEntregue(1);
        }
        else{
            pedidoEditar.setEntregue(0);
        }

        if (chekPagoAlt.isChecked()){
            pedidoEditar.setPago(1);
        }
        else{
            pedidoEditar.setPago(0);
        }

        pedidoBD.alterar(pedidoEditar);

        Toast.makeText(this, "Pedido alterado com sucesso!", Toast.LENGTH_LONG)
                .show();

    }


    public void cancelarEdicao(View view){
        finish();
    }


}
