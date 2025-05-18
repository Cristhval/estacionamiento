package com.mistletoe.estaciona.base.controller.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mistletoe.estaciona.base.controller.dao.dao_models.DaoParqueadero;
import com.mistletoe.estaciona.base.models.Parqueadero;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@BrowserCallable
@Transactional(propagation = Propagation.REQUIRES_NEW)
@AnonymousAllowed


public class ParqueaderoService {
    private DaoParqueadero da;
    public ParqueaderoService() {
        da = new DaoParqueadero();
    }

    public void createParqueadero(@NotEmpty String direccion, @NotEmpty String nombre) throws Exception{
        if(direccion.trim().length() > 0 && nombre.trim().length() > 0 ){
            da.getObj().setId(da.listAll().getLength()+1);
            da.getObj().setDireccion(direccion);
            da.getObj().setNombre(nombre);
            if(!da.save()){
                throw new Exception("No se pudo guardar los datos de la Parqueadero");
            }
        }
    }
    

    public void updateParqueadero(Integer id, @NotEmpty String direccion, @NotEmpty String nombre) throws Exception{
        //System.out.println("El id que está ingresando eeeesssss:  " + id);
        if(id != null && id > 0 && direccion.trim().length() > 0 && nombre.trim().length() > 0){
            da.setObj(da.listAll().get(id - 1));
            da.getObj().setDireccion(direccion);
            da.getObj().setNombre(nombre);
           
            if(!da.update(id - 1)){
                throw new Exception("No se pudo modificar los datos de la Parqueadero");
            }
        }
    }

    public List<Parqueadero> listAllParqueadero(){
        return Arrays.asList(da.listAll().toArray());
    }
}
