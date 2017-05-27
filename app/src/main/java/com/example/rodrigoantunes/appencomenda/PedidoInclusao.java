package com.example.rodrigoantunes.appencomenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;

public class PedidoInclusao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_inclusao);
    }

    private Boolean validarDados (Pedido pedidoValidar){

        return Boolean.TRUE;

    }

    public void cancelar(View view){
        finish();
    }



    public void salvarPedido (View view){

        String sData,sContato,sCliente,sPedido;
        Float fValor;
        Integer iPago, iEntregue;

        //Captura os valores
        //Adiciona a classe pedido
        //chama salvar da classe PedidoBD

        EditText textData = (EditText) findViewById(R.id.txtQuando);

        EditText textContato = (EditText) findViewById(R.id.txtContato);

        EditText textCliente = (EditText) findViewById(R.id.txtQuem);

        EditText textValor = (EditText) findViewById(R.id.txtQuanto);

        EditText textPedido = (EditText) findViewById(R.id.txtOq);

        CheckBox chkEntregue = (CheckBox) findViewById(R.id.chkEntregue);

        CheckBox chkPago = (CheckBox) findViewById(R.id.chkPago);


        sCliente = textCliente.getText().toString();
        sData= textData.getText().toString();
        sContato=textContato.getText().toString();
        sPedido=textPedido.getText().toString();
        fValor=Float.parseFloat(textValor.getText().toString());

        if (chkPago.isChecked()){
            iPago=1;}
            else
            iPago=0;

        if (chkEntregue.isChecked()){
            iEntregue=1;}
            else
                iEntregue=0;


        Pedido pedido = new Pedido(sCliente,sData,sContato, sPedido, fValor,iPago,iEntregue);

        //Salva

        if (validarDados(pedido)) {

            PedidoBD pedidoBD = new PedidoBD(this);

            pedidoBD.inserir(pedido);

            //Finaliza
            Toast.makeText(this, "Pedido incluído com sucesso!", Toast.LENGTH_LONG)
                            .show();

            finish();
        }



    }
}
