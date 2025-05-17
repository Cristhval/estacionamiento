package com.mistletoe.estaciona.base.controller.dao.dao_models;

import com.mistletoe.estaciona.base.controller.dao.AdapterDao;
import com.mistletoe.estaciona.base.models.Administrador;

public class DaoAdministrador extends AdapterDao<Administrador>{
    private Administrador obj;

    public DaoAdministrador() {
        super(Administrador.class);
        //TODO Auto-generated constructor stub
    }
    
    public Administrador getObj() {
        return this.obj;
    }

    public void setObj(Administrador obj) {
        this.obj = obj;
    }

    public Boolean save() {
        try {
            obj.setId(listAll().getLength()+1);
            this.persist(obj);
            return true;
        } catch (Exception e) {
            //TODO
            return false;
            // TODO: handle exception
        }
    }

    public Boolean update(Integer pos) {
        try {
            this.update(obj, pos);
            return true;
        } catch (Exception e) {
            //TODO
            return false;
            // TODO: handle exception
        }
    }

}
