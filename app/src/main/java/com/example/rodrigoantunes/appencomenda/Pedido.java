package com.example.rodrigoantunes.appencomenda;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by RodrigoAntunes on 23/05/17.
 */

public class Pedido implements Serializable{

    private Integer codigo;
    private String cliente;
    private String entrega;
    private String contato;
    private String pedido;
    private Float valor;
    private Integer entregue;
    private Integer pago;

    public Pedido(String cliente, String entrega, String contato, String pedido, Float valor, Integer entregue, Integer pago) {
        this.cliente = cliente;
        this.entrega = entrega;
        this.contato = contato;
        this.pedido = pedido;
        this.valor = valor;
        this.entregue = entregue;
        this.pago = pago;
    }

    public Pedido(Integer codigo, String cliente, String entrega, String contato, String pedido, Float valor, Integer entregue, Integer pago) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.entrega = entrega;
        this.contato = contato;
        this.pedido = pedido;
        this.valor = valor;
        this.entregue = entregue;
        this.pago = pago;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Integer getEntregue() {
        return entregue;
    }

    public void setEntregue(Integer entregue) {
        this.entregue = entregue;
    }

    public Integer getPago() {
        return pago;
    }

    public void setPago(Integer pago) {
        this.pago = pago;
    }
}
