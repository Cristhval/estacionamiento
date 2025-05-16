package com.mistletoe.estaciona.base.models;

public class Administrador extends Usuario{
    private Integer idParqueadero;

    public Administrador(Integer id, String nombre, String apellido, String correoElectronico, Integer idParqueadero) {
        super(id, nombre, apellido, correoElectronico);
        this.idParqueadero = idParqueadero;
    }

    public Integer getIdParqueadero() {
        return idParqueadero;
    }

    public void setIdParqueadero(Integer idParqueadero) {
        this.idParqueadero = idParqueadero;
    }
}
