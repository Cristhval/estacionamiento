package com.mistletoe.estaciona.base.controller.services;

import com.mistletoe.estaciona.base.controller.dao.dao_models.DaoCliente;
import com.mistletoe.estaciona.base.models.Cliente;
import com.mistletoe.estaciona.base.controller.data_struct.list.LinkedList;

import java.util.Arrays;

public class ClienteService {

    private DaoCliente daoCliente;

    public ClienteService() {
        daoCliente = new DaoCliente();
    }

    public void crearCliente(Cliente clienteParaGuardar) throws Exception {
        if (clienteParaGuardar == null) {
            throw new Exception("Error: No se puede guardar un objeto Cliente nulo.");
        }

        daoCliente.setObj(clienteParaGuardar);

        if (!daoCliente.save()) {
            throw new Exception("No se pudo guardar los datos del cliente.");
        }
    }

    public java.util.List<Cliente> listarTodosClientes() {
        LinkedList<Cliente> listaDelDao = daoCliente.listAll();

        if (listaDelDao != null) {
             return Arrays.asList(listaDelDao.toArray());
        } else {
             return Arrays.asList();
        }
    }

    public Cliente obtenerClientePorId(Integer id) {
        if (id == null || id <= 0) {
             return null;
        }

        java.util.List<Cliente> clientes = listarTodosClientes();

        if (clientes != null) {
            for (Cliente c : clientes) {
                if (c != null && c.getId() != null && c.getId().equals(id)) {
                    return c;
                }
            }
        }

        return null;
    }

    public Cliente buscarClientePorCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
             return null;
        }

        java.util.List<Cliente> clientes = listarTodosClientes();

        if (clientes != null) {
            for (Cliente c : clientes) {
                if (c != null && c.getCedula() != null && c.getCedula().equals(cedula.trim())) {
                    return c;
                }
            }
        }

        return null;
    }


    public void actualizarCliente(Cliente clienteActualizado) throws Exception {
         if (clienteActualizado == null || clienteActualizado.getId() == null) {
             throw new Exception("Error: Datos de cliente inválidos para actualizar.");
         }

         java.util.List<Cliente> clientes = listarTodosClientes();
         int posicion = -1;

         if (clientes != null) {
             int i = 0;
             for (Cliente c : clientes) {
                 if (c != null && c.getId() != null && c.getId().equals(clienteActualizado.getId())) {
                     posicion = i;
                     break;
                 }
                 i++;
             }
         }

         if (posicion != -1) {
             daoCliente.setObj(clienteActualizado);
             if (!daoCliente.update(posicion)) {
                  throw new Exception("No se pudo actualizar los datos del cliente en la posición " + posicion);
             }
         } else {
             throw new Exception("Cliente con ID " + clienteActualizado.getId() + " no encontrado para actualizar.");
         }
    }
}