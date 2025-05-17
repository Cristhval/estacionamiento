package com.mistletoe.estaciona.base.models;

public class Vehiculo {
    private Integer id;
    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private TipoEnum tipo;
    private Integer idTicket;
    private Integer idPaqueadero;
    private Integer idCliente;

    public Vehiculo(Integer id, String placa, String marca, String modelo, String color, String tipo, Integer idTicket, Integer idPaqueadero, Integer idCliente) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipo = TipoEnum.valueOf(tipo);
        this.idTicket = idTicket;
        this.idPaqueadero = idPaqueadero;
        this.idCliente = idCliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getIdPaqueadero() {
        return idPaqueadero;
    }

    public void setIdPaqueadero(Integer idPaqueadero) {
        this.idPaqueadero = idPaqueadero;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
}
