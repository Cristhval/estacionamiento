package com.mistletoe.estaciona.base.controller.services;

import com.mistletoe.estaciona.base.controller.dao.dao_models.DaoReserva;
import com.mistletoe.estaciona.base.models.Reserva;
import com.mistletoe.estaciona.base.controller.data_struct.list.LinkedList;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

public class ReservaService {

    private DaoReserva daoReserva;

    public ReservaService() {
        daoReserva = new DaoReserva();
    }

    public void crearReserva(Reserva reservaParaGuardar) throws Exception {
        if (reservaParaGuardar == null) {
            throw new Exception("Error: No se puede guardar un objeto Reserva nulo.");
        }

        daoReserva.setObj(reservaParaGuardar);

        if (!daoReserva.save()) {
            throw new Exception("No se pudo guardar los datos de la reserva.");
        }
    }

    public java.util.List<Reserva> listarTodasReservas() {
        LinkedList<Reserva> listaDelDao = daoReserva.listAll();

        if (listaDelDao != null) {
            return Arrays.asList(listaDelDao.toArray());
        } else {
            return Arrays.asList();
        }
    }

    public Reserva obtenerReservaPorId(Integer id) {
        if (id == null || id <= 0) {
            return null;
        }

        java.util.List<Reserva> reservas = listarTodasReservas();

        if (reservas != null) {
            for (Reserva r : reservas) {
                if (r != null && r.getId() != null && r.getId().equals(id)) {
                    return r;
                }
            }
        }

        return null;
    }

    public java.util.List<Reserva> buscarReservasPorCliente(Integer idCliente) {
        if (idCliente == null || idCliente <= 0) {
            return new java.util.ArrayList<>();
        }

        java.util.List<Reserva> resultado = new java.util.ArrayList<>();
        java.util.List<Reserva> reservas = listarTodasReservas();

        if (reservas != null) {
            for (Reserva r : reservas) {
                if (r != null && r.getIdCliente() != null && r.getIdCliente().equals(idCliente)) {
                    resultado.add(r);
                }
            }
        }

        return resultado;
    }

    public java.util.List<Reserva> buscarReservasPorEspacio(Integer idEspacio) {
        if (idEspacio == null || idEspacio <= 0) {
            return new java.util.ArrayList<>();
        }

        java.util.List<Reserva> resultado = new java.util.ArrayList<>();
        java.util.List<Reserva> reservas = listarTodasReservas();

        if (reservas != null) {
            for (Reserva r : reservas) {
                if (r != null && r.getIdEspacioParqueadero() != null &&
                        r.getIdEspacioParqueadero().equals(idEspacio)) {
                    resultado.add(r);
                }
            }
        }

        return resultado;
    }

    public void actualizarReserva(Reserva reservaActualizada) throws Exception {
        if (reservaActualizada == null || reservaActualizada.getId() == null) {
            throw new Exception("Error: Datos de reserva inválidos para actualizar.");
        }

        java.util.List<Reserva> reservas = listarTodasReservas();
        int posicion = -1;

        if (reservas != null) {
            int i = 0;
            for (Reserva r : reservas) {
                if (r != null && r.getId() != null && r.getId().equals(reservaActualizada.getId())) {
                    posicion = i;
                    break;
                }
                i++;
            }
        }

        if (posicion != -1) {
            daoReserva.setObj(reservaActualizada);
            if (!daoReserva.update(posicion)) {
                throw new Exception("No se pudo actualizar los datos de la reserva en la posición " + posicion);
            }
        } else {
            throw new Exception("Reserva con ID " + reservaActualizada.getId() + " no encontrada para actualizar.");
        }
    }

    public boolean validarDisponibilidadHorario(Reserva reserva) {
        if (reserva == null || reserva.getHoraEntrada() == null || reserva.getHoraSalida() == null) {
            return false;
        }

        if (reserva.getHoraEntrada().isAfter(reserva.getHoraSalida())) {
            return false;
        }

        java.util.List<Reserva> reservas = buscarReservasPorEspacio(reserva.getIdEspacioParqueadero());

        for (Reserva r : reservas) {
            if (reserva.getId() != null && reserva.getId().equals(r.getId())) {
                continue;
            }

            if (!(r.getHoraSalida().isBefore(reserva.getHoraEntrada()) ||
                    r.getHoraEntrada().isAfter(reserva.getHoraSalida()))) {
                return false;
            }
        }

        return true;
    }
}