package com.mistletoe.estaciona.base.models;

import java.time.LocalDateTime;

public class Tiicket {
    private Integer id;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private Double tarifa;
    private Double totalPagar;
    private Integer idEspacioPaqueadero;

    public Tiicket(Integer id, LocalDateTime horaEntrada, LocalDateTime horaSalida, Double tarifa, Double totalPagar, Integer idEspacioPaqueadero) {
        this.id = id;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.tarifa = tarifa;
        this.totalPagar = totalPagar;
        this.idEspacioPaqueadero = idEspacioPaqueadero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Double getTarifa() {
        return tarifa;
    }

    public void setTarifa(Double tarifa) {
        this.tarifa = tarifa;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public Integer getIdEspacioPaqueadero() {
        return idEspacioPaqueadero;
    }

    public void setIdEspacioPaqueadero(Integer idEspacioPaqueadero) {
        this.idEspacioPaqueadero = idEspacioPaqueadero;
    }
}
