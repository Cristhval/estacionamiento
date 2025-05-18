package com.mistletoe.estaciona.base.controller.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mistletoe.estaciona.base.controller.dao.dao_models.DaoPlaza;
import com.mistletoe.estaciona.base.models.Plaza;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@BrowserCallable
@Transactional(propagation = Propagation.REQUIRES_NEW)
@AnonymousAllowed

public class PlazaService {
    private DaoPlaza da;
    public PlazaService() {
        da = new DaoPlaza();
    }

    public void createPlaza(@NotEmpty String codigo, @NotEmpty Integer plazasTotales,@NotEmpty Integer plazasDisponibles,@NotEmpty Integer idPaqueadero) throws Exception{
        if(codigo.trim().length() > 0 && plazasTotales.toString().length() > 0 && plazasDisponibles.toString().length() > 0 && idPaqueadero.toString().length() > 0){
            da.getObj().setId(da.listAll().getLength()+1);
            da.getObj().setCodigo(codigo);
            da.getObj().setPlazasTotales(plazasDisponibles);
            da.getObj().setPlazasDisponibles(plazasDisponibles);
            da.getObj().setIdPaqueadero(idPaqueadero);
            if(!da.save()){
                throw new Exception("No se pudo guardar los datos de la Plaza");
            }
        }
    }
    

    public void updatePlaza(Integer id, @NotEmpty String codigo, @NotEmpty Integer plazasTotales,@NotEmpty Integer plazasDisponibles,@NotEmpty Integer idPaqueadero) throws Exception{
        //System.out.println("El id que está ingresando eeeesssss:  " + id);
        if(id != null && id > 0 && codigo.trim().length() > 0 && plazasTotales.toString().length() > 0 && plazasDisponibles.toString().length() > 0 && idPaqueadero.toString().length() > 0){
            da.setObj(da.listAll().get(id - 1));
            da.getObj().setCodigo(codigo);
            da.getObj().setPlazasTotales(plazasTotales);
            da.getObj().setPlazasDisponibles(plazasDisponibles);
            da.getObj().setIdPaqueadero(idPaqueadero);

           
            if(!da.update(id - 1)){
                throw new Exception("No se pudo modificar los datos de la Plaza");
            }
        }
    }

    public List<Plaza> listAllPlaza(){
        return Arrays.asList(da.listAll().toArray());
    }
}
