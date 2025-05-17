package com.mistletoe.estaciona.base.models;

public class Cliente extends Usuario{
    private String cedula;

    public String getCedula() {
        return this.cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Cliente(Integer id, String nombre, String apellido, String correoElectronico) {
        super(id, nombre, apellido, correoElectronico);
    }

}
