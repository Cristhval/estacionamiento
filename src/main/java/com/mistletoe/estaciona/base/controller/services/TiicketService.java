package com.mistletoe.estaciona.base.controller.services;

import com.mistletoe.estaciona.base.controller.dao.dao_models.DaoTiicket;
import com.mistletoe.estaciona.base.models.Tiicket;

import java.util.List;
import java.time.temporal.ChronoUnit;

public class TiicketService {

    private DaoTiicket daoTiicket;

    public TiicketService() {
        daoTiicket = new DaoTiicket();
    }

    public void crearTiicket(Tiicket tiicketParaGuardar) throws Exception {
        if (tiicketParaGuardar == null) {
            throw new Exception("Error: No se puede guardar un objeto Tiicket nulo.");
        }

        daoTiicket.setObj(tiicketParaGuardar);

        if (!daoTiicket.save()) {
            throw new Exception("No se pudo guardar los datos del tiicket.");
        }
    }

    public List<Tiicket> listarTodosTiickets() {
        List<Tiicket> listDelDao = (List<Tiicket>) daoTiicket.listAll();

        if (listDelDao != null) {
             return listDelDao;
        } else {
             return java.util.Collections.emptyList();
        }
    }

    public Tiicket obtenerTiicketPorId(Integer id) {
        if (id == null || id <= 0) {
             return null;
        }
        java.util.List<Tiicket> tiickets = listarTodosTiickets();

        if (tiickets != null) {
            for (Tiicket t : tiickets) {
                if (t != null && t.getId() != null && t.getId().equals(id)) {
                    return t;
                }
            }
        }
        return null;
    }

    public void actualizarTiicket(Tiicket tiicketActualizado) throws Exception {
         if (tiicketActualizado == null || tiicketActualizado.getId() == null) {
             throw new Exception("Error: Datos de tiicket inválidos para actualizar.");
         }

         java.util.List<Tiicket> tiickets = listarTodosTiickets();

         int posicion = -1;

         if (tiickets != null) {
             int i = 0;
             for (Tiicket t : tiickets) {
                 if (t != null && t.getId() != null && t.getId().equals(tiicketActualizado.getId())) {
                     posicion = i;
                     break;
                 }
                 i++;
             }
         }

         if (posicion != -1) {
             daoTiicket.setObj(tiicketActualizado);
             if (!daoTiicket.update(posicion)) {
                  throw new Exception("No se pudo actualizar los datos del tiicket en la posición " + posicion);
             }
         } else {
             throw new Exception("Tiicket con ID " + tiicketActualizado.getId() + " no encontrado para actualizar.");
         }
    }

    public double calcularTotal(Tiicket tiicket) {
        if (tiicket == null || tiicket.getHoraEntrada() == null || tiicket.getTarifa() == null) {
            return 0.0;
        }

        if (tiicket.getHoraSalida() == null) {
            return 0.0;
        }

        long minutos = ChronoUnit.MINUTES.between(tiicket.getHoraEntrada(), tiicket.getHoraSalida());
        double horas = (double) minutos / 60.0;
        double total = horas * tiicket.getTarifa();

        return Math.max(0.0, total);
    }
}