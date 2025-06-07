package com.mistletoe.estaciona.base.controller.dao.dao_models;

import com.mistletoe.estaciona.base.controller.dao.AdapterDao;

import com.mistletoe.estaciona.base.models.RolEnum;
import com.mistletoe.estaciona.base.models.Vehiculo;

public class DaoVehiculo extends AdapterDao<Vehiculo>{
    private Vehiculo obj;

    public DaoVehiculo() {
        super(Vehiculo.class);

    }

    public Vehiculo getObj() {
        if (obj == null)
            this.obj = new Vehiculo();
        return this.obj;
    }

    public void setObj(Vehiculo obj) {
        this.obj = obj;
    }

    public Boolean save() {
        try {
            obj.setId(listAll().getLength()+1);
            this.persist(obj);
            return true;
        } catch (Exception e) {

            return false;

        }
    }

    public Boolean update(Integer pos) {
        try {
            this.update(obj, pos);
            return true;
        } catch (Exception e) {

            return false;

        }
    }

    public static void main(String[] args) {
        DaoVehiculo da = new DaoVehiculo();
        da.getObj().setId(da.listAll().getLength() + 1);
        da.getObj().setColor("azul");
        da.getObj().setModelo("hyundai");
        da.getObj().setPlaca("PLC-123");
        da.getObj().setMarca("Pepsi");
        da.getObj().setId_Persona(1);

        if (da.save())
            System.out.println("GUARDADO");
        else
            System.out.println("Hubo un error");
    }
}
