package com.mistletoe.estaciona.base.controller.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mistletoe.estaciona.base.controller.dao.dao_models.DaoAdministrador;
import com.mistletoe.estaciona.base.models.Administrador;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@BrowserCallable
@Transactional(propagation = Propagation.REQUIRES_NEW)
@AnonymousAllowed

public class AdministradorService {
    private DaoAdministrador da;
    public AdministradorService() {
        da = new DaoAdministrador();
    }

    public void createAdministrador(@NotEmpty Integer idParqueadero, @NotEmpty String ruc) throws Exception{
        if(idParqueadero.toString().length() > 0 && ruc.trim().length() > 0 ){
            da.getObj().setId(da.listAll().getLength()+1);
            da.getObj().setIdParqueadero(idParqueadero);
            da.getObj().setRuc(ruc);
            if(!da.save()){
                throw new Exception("No se pudo guardar los datos de la Administrador");
            }
        }
    }
    

    public void updateAdministrador(Integer id, @NotEmpty Integer idParqueadero, @NotEmpty String ruc) throws Exception{
        //System.out.println("El id que está ingresando eeeesssss:  " + id);
        if(id != null && id > 0 && idParqueadero.toString().length() > 0 && ruc.trim().length() > 0 ){
            da.setObj(da.listAll().get(id - 1));
            da.getObj().setIdParqueadero(idParqueadero);
            da.getObj().setRuc(ruc);
           
            if(!da.update(id - 1)){
                throw new Exception("No se pudo modificar los datos de la Administrador");
            }
        }
    }

    public List<Administrador> listAllAdministrador(){
        return Arrays.asList(da.listAll().toArray());
    }
}
