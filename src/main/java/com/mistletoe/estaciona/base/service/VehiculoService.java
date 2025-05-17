package com.mistletoe.estaciona.base.service;

import com.mistletoe.estaciona.base.controller.dao.dao_models.DaoCliente;
import com.mistletoe.estaciona.base.controller.dao.dao_models.DaoParqueadero;
import com.mistletoe.estaciona.base.controller.dao.dao_models.DaoTiicket;
import com.mistletoe.estaciona.base.controller.dao.dao_models.DaoVehiculo;
import com.mistletoe.estaciona.base.models.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@BrowserCallable
@AnonymousAllowed
public class VehiculoService {
    private DaoVehiculo dv;
    public VehiculoService(){
        dv = new DaoVehiculo();
    }

    public void createVehiculo(@NotEmpty String placa,@NotEmpty String marca,@NotEmpty String modelo,@NotEmpty String color,
                               @NotEmpty String tipo, Integer idTicket, Integer idParqueadero, Integer idCliente) throws Exception {
        if(placa.trim().length() > 0 && marca.trim().length() > 0 && modelo.trim().length() > 0 && color.trim().length() > 0 &&
                tipo.trim().length() > 0 && idTicket > 0 && idParqueadero > 0 && idCliente > 0) {
            dv.getObj().setPlaca(placa);
            dv.getObj().setMarca(marca);
            dv.getObj().setModelo(modelo);
            dv.getObj().setColor(color);
            dv.getObj().setTipo(TipoEnum.valueOf(tipo));
            dv.getObj().setIdTicket(idTicket);
            dv.getObj().setIdPaqueadero(idParqueadero);
            dv.getObj().setIdCliente(idCliente);
            if(!dv.save())
                throw new  Exception("No se pudo guardar los datos de la banda");
        }
    }

    public void update(Integer id, @NotEmpty String placa,@NotEmpty String marca,@NotEmpty String modelo,@NotEmpty String color,
                       @NotEmpty String tipo, Integer idTicket, Integer idParqueadero, Integer idCliente) throws Exception {
        if(placa.trim().length() > 0 && marca.trim().length() > 0 && modelo.trim().length() > 0 &&
                color.trim().length() > 0 &&  tipo.trim().length() > 0 && idTicket > 0 && idParqueadero > 0 && idCliente > 0) {
            dv.setObj(dv.listAll().get(id - 1));
            dv.getObj().setPlaca(placa);
            dv.getObj().setMarca(marca);
            dv.getObj().setModelo(modelo);
            dv.getObj().setColor(color);
            dv.getObj().setTipo(TipoEnum.valueOf(tipo));
            dv.getObj().setIdTicket(idTicket);
            dv.getObj().setIdPaqueadero(idParqueadero);
            dv.getObj().setIdCliente(idCliente);
            if(!dv.update(id - 1))
                throw new  Exception("No se pudo modificar los datos de la banda");
        }
    }

    public List<HashMap> listaAlbumTicket() {
        List<HashMap> lista = new ArrayList<>();
        DaoTiicket da = new DaoTiicket();
        if(!da.listAll().isEmpty()) {
            Tiicket[] arreglo = da.listAll().toArray();
            for(int i = 0; i < arreglo.length; i++) {
                HashMap<String, String> aux = new HashMap<>();
                aux.put("value", arreglo[i].getId().toString(i));
                aux.put("label", arreglo[i].getHoraEntrada().toString());
                lista.add(aux);
            }
        }
        return lista;
    }

    public List<HashMap> listaAlbumParqueadero() {
        List<HashMap> lista = new ArrayList<>();
        DaoParqueadero da = new DaoParqueadero();
        if(!da.listAll().isEmpty()) {
            Parqueadero[] arreglo = da.listAll().toArray();
            for(int i = 0; i < arreglo.length; i++) {
                HashMap<String, String> aux = new HashMap<>();
                aux.put("value", arreglo[i].getId().toString(i));
                aux.put("label", arreglo[i].getNombre());
                lista.add(aux);
            }
        }
        return lista;
    }

    public List<HashMap> listaAlbumCliente() {
        List<HashMap> lista = new ArrayList<>();
        DaoCliente da = new DaoCliente();
        if(!da.listAll().isEmpty()) {
            Cliente[] arreglo = da.listAll().toArray();
            for(int i = 0; i < arreglo.length; i++) {
                HashMap<String, String> aux = new HashMap<>();
                aux.put("value", arreglo[i].getId().toString(i));
                aux.put("label", arreglo[i].getNombre());
                lista.add(aux);
            }
        }
        return lista;
    }

    public List<String> listTipo() {
        List<String> lista = new ArrayList<>();
        for(TipoEnum r: TipoEnum.values()) {
            lista.add(r.toString());
        }
        return lista;
    }

    public List<HashMap> listVehiculo(){
        List<HashMap> lista = new ArrayList<>();
        if(!dv.listAll().isEmpty()) {
            Vehiculo [] arreglo = dv.listAll().toArray();
            for(int i = 0; i < arreglo.length; i++) {

                HashMap<String, String> aux = new HashMap<>();
                aux.put("id", arreglo[i].getId().toString(i));
                aux.put("placa", arreglo[i].getPlaca());
                aux.put("cliente", new DaoCliente().listAll().get(arreglo[i].getIdCliente() -1).getNombre());
                aux.put("idCliente", new DaoCliente().listAll().get(arreglo[i].getIdCliente() -1).getId().toString());
                aux.put("parqueadero", new DaoParqueadero().listAll().get(arreglo[i].getIdPaqueadero() -1).getNombre());
                aux.put("idParqueaderp", new DaoParqueadero().listAll().get(arreglo[i].getIdPaqueadero() -1).getId().toString());
                aux.put("marca", arreglo[i].getMarca());
                aux.put("modelo", arreglo[i].getModelo());
                aux.put("color", arreglo[i].getColor());
                aux.put("tipo", arreglo[i].getTipo().toString());
                lista.add(aux);
            }
        }
        return lista;
    }
}






