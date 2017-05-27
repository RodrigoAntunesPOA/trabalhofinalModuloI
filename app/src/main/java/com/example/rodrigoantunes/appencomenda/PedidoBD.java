package com.example.rodrigoantunes.appencomenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RodrigoAntunes on 25/05/17.
 */

public class PedidoBD {

    private Float fValorTotal;

    public Float getfValorTotal() {
        return fValorTotal;
    }

    private SQLiteDatabase banco;

    private static String NOME_BD="pedidos.db";

    private static String create="CREATE TABLE IF NOT EXISTS pedidos" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "cliente VARCHAR(50),"+
            "dataEntrega DATE,"+
            "contato VARCHAR(20),"+
            "pedido VARCHAR(500)," +
            "valor REAL," +
            "entregue INTEGER," +
            "pago INTEGER);";

    public PedidoBD(Context contexto) {

        banco = contexto.openOrCreateDatabase(
                NOME_BD, contexto.MODE_PRIVATE, null);

        //TESTE
        //banco.execSQL("DROP TABLE IF EXISTS pedidos");

        banco.execSQL(create);

    }

    public void inserir(Pedido pedido){

        ContentValues valorAInserir = new ContentValues();
        valorAInserir.put("cliente", pedido.getCliente().toString());
        valorAInserir.put("dataEntrega", pedido.getEntrega().toString());
        valorAInserir.put("contato", pedido.getContato().toString());
        valorAInserir.put("pedido", pedido.getPedido().toString());
        valorAInserir.put("valor", Float.parseFloat(pedido.getValor().toString()));
        valorAInserir.put("entregue", Integer.parseInt(pedido.getEntregue().toString()));
        valorAInserir.put("pago", Integer.parseInt(pedido.getPago().toString()));

        banco.insert( "pedidos", null, valorAInserir);

    }

    public void alterar (Pedido pedido){

        ContentValues valores = new ContentValues();
        valores.put("cliente", pedido.getCliente().toString());
        valores.put("dataEntrega", pedido.getEntrega().toString());
        valores.put("contato", pedido.getContato().toString());
        valores.put("pedido", pedido.getPedido().toString());
        valores.put("valor", Float.parseFloat(pedido.getValor().toString()));
        valores.put("entregue", Integer.parseInt(pedido.getEntregue().toString()));
        valores.put("pago", Integer.parseInt(pedido.getPago().toString()));

        banco.update("pedidos",valores,"id=?",
                new String[]{String.valueOf(pedido.getCodigo())});

    }

    public void excluir (Integer id ) {

        if (id != 0) {
            banco.delete("pedidos", "id=?", new String[]{id.toString()});
        }
    }

    public List<Pedido> listar(Integer iPendentes, Integer iPagos) {

        List<Pedido> listaPedidos = new ArrayList<>();

        //listaPedidos.add(new Pedido(1,"Cliente","10/10/2010","99760000","isso e aquilo",Float.parseFloat("5.99"), bPagos , bPendentes));
        //listaPedidos.add(new Pedido(2,"Joao","15/11/2017","3344556677", "bolo e espinafre bolo e espinafre  bolo e espinafre bolo e espinafre bolo e espinafre bolo e espinafre bolo e espinafre ", Float.parseFloat("100.99"), Boolean.parseBoolean("False"),Boolean.parseBoolean("True")));

        Log.i("LogBA", iPendentes.toString());

        Log.i("LogBA", iPagos.toString());

        Cursor cursor = banco.query("pedidos",
                new String[]{"id", "cliente", "dataEntrega", "contato", "pedido", "valor", "entregue", "pago"},
                null, //"(entregue=? AND pago=?)",
                null, //new String []{iPendentes.toString(), iPagos.toString()},
                null,
                null,
                "dataEntrega");

        fValorTotal=Float.parseFloat("0");

        while(cursor.moveToNext())
            {
            Integer id = cursor.getInt(cursor.getColumnIndex("id"));
            String cliente = cursor.getString(cursor.getColumnIndex("cliente"));
            String dataEntrega = cursor.getString(cursor.getColumnIndex("dataEntrega"));
            String contato = cursor.getString(cursor.getColumnIndex("contato"));
            String pedidoDet = cursor.getString(cursor.getColumnIndex("pedido"));
            Float valor = cursor.getFloat(cursor.getColumnIndex("valor"));
            Integer entregue = Integer.parseInt( cursor.getString(cursor.getColumnIndex("entregue")));
            Integer pago = Integer.parseInt( cursor.getString(cursor.getColumnIndex("pago")));

            Pedido pedido = new Pedido(id, cliente, dataEntrega, contato, pedidoDet, valor, entregue, pago);
            listaPedidos.add(pedido);
            fValorTotal+=valor;
        }

        return listaPedidos;
    }


}
