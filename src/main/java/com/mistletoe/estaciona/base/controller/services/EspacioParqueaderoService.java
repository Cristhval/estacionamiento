package com.mistletoe.estaciona.base.controller.services;

import com.mistletoe.estaciona.base.controller.dao.dao_models.DaoEspacioParqueadero;
import com.mistletoe.estaciona.base.models.EspacioParqueadero;
import com.mistletoe.estaciona.base.models.EstadoEnum;
import com.mistletoe.estaciona.base.controller.data_struct.list.LinkedList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EspacioParqueaderoService {

    private DaoEspacioParqueadero daoEspacioParqueadero;
    private Map<String, EspacioParqueadero> espaciosPorCodigo;
    private Map<Integer, EspacioParqueadero> espaciosPorId;
    private Map<Integer, java.util.List<EspacioParqueadero>> espaciosPorPlaza;

    public EspacioParqueaderoService() {
        daoEspacioParqueadero = new DaoEspacioParqueadero();
        inicializarCaches();
    }

    private void inicializarCaches() {
        espaciosPorCodigo = new HashMap<>();
        espaciosPorId = new HashMap<>();
        espaciosPorPlaza = new HashMap<>();

        java.util.List<EspacioParqueadero> espacios = listarTodosEspacios();
        if (espacios != null) {
            for (EspacioParqueadero espacio : espacios) {
                if (espacio != null) {
                    if (espacio.getCodigo() != null) {
                        espaciosPorCodigo.put(espacio.getCodigo(), espacio);
                    }

                    if (espacio.getId() != null) {
                        espaciosPorId.put(espacio.getId(), espacio);
                    }

                    if (espacio.getIdPlaza() != null) {
                        if (!espaciosPorPlaza.containsKey(espacio.getIdPlaza())) {
                            espaciosPorPlaza.put(espacio.getIdPlaza(), new java.util.ArrayList<>());
                        }
                        espaciosPorPlaza.get(espacio.getIdPlaza()).add(espacio);
                    }
                }
            }
        }
    }

    public void crearEspacioParqueadero(EspacioParqueadero espacioParaGuardar) throws Exception {
        if (espacioParaGuardar == null) {
            throw new Exception("Error: No se puede guardar un objeto EspacioParqueadero nulo.");
        }

        if (espacioParaGuardar.getCodigo() != null &&
                buscarEspacioPorCodigo(espacioParaGuardar.getCodigo()) != null) {
            throw new Exception("Error: Ya existe un espacio con el código " + espacioParaGuardar.getCodigo());
        }

        daoEspacioParqueadero.setObj(espacioParaGuardar);

        if (!daoEspacioParqueadero.save()) {
            throw new Exception("No se pudo guardar los datos del espacio de parqueadero.");
        }

        if (espacioParaGuardar.getCodigo() != null) {
            espaciosPorCodigo.put(espacioParaGuardar.getCodigo(), espacioParaGuardar);
        }
        if (espacioParaGuardar.getId() != null) {
            espaciosPorId.put(espacioParaGuardar.getId(), espacioParaGuardar);
        }
        if (espacioParaGuardar.getIdPlaza() != null) {
            if (!espaciosPorPlaza.containsKey(espacioParaGuardar.getIdPlaza())) {
                espaciosPorPlaza.put(espacioParaGuardar.getIdPlaza(), new java.util.ArrayList<>());
            }
            espaciosPorPlaza.get(espacioParaGuardar.getIdPlaza()).add(espacioParaGuardar);
        }
    }

    public java.util.List<EspacioParqueadero> listarTodosEspacios() {
        LinkedList<EspacioParqueadero> listaDelDao = daoEspacioParqueadero.listAll();

        if (listaDelDao != null) {
            return Arrays.asList(listaDelDao.toArray());
        } else {
            return Arrays.asList();
        }
    }

    public EspacioParqueadero obtenerEspacioPorId(Integer id) {
        if (id == null || id <= 0) {
            return null;
        }
        return espaciosPorId.get(id);
    }

    public EspacioParqueadero buscarEspacioPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }
        return espaciosPorCodigo.get(codigo.trim());
    }

    public java.util.List<EspacioParqueadero> buscarEspaciosPorPlaza(Integer idPlaza) {
        if (idPlaza == null || idPlaza <= 0) {
            return new java.util.ArrayList<>();
        }
        return espaciosPorPlaza.getOrDefault(idPlaza, new java.util.ArrayList<>());
    }

    public java.util.List<EspacioParqueadero> buscarEspaciosDisponibles() {
        java.util.List<EspacioParqueadero> disponibles = new java.util.ArrayList<>();

        for (EspacioParqueadero espacio : espaciosPorId.values()) {
            if (espacio != null && espacio.getEstado() == EstadoEnum.DISPONIBLE) {
                disponibles.add(espacio);
            }
        }

        return disponibles;
    }

    public void actualizarEspacioParqueadero(EspacioParqueadero espacioActualizado) throws Exception {
        if (espacioActualizado == null || espacioActualizado.getId() == null) {
            throw new Exception("Error: Datos de espacio inválidos para actualizar.");
        }

        java.util.List<EspacioParqueadero> espacios = listarTodosEspacios();
        int posicion = -1;

        if (espacios != null) {
            int i = 0;
            for (EspacioParqueadero e : espacios) {
                if (e != null && e.getId() != null && e.getId().equals(espacioActualizado.getId())) {
                    posicion = i;
                    break;
                }
                i++;
            }
        }

        if (posicion != -1) {
            EspacioParqueadero espacioAntiguo = espaciosPorId.get(espacioActualizado.getId());
            if (espacioAntiguo != null) {
                if (espacioAntiguo.getCodigo() != null) {
                    espaciosPorCodigo.remove(espacioAntiguo.getCodigo());
                }
                if (espacioAntiguo.getIdPlaza() != null && espaciosPorPlaza.containsKey(espacioAntiguo.getIdPlaza())) {
                    espaciosPorPlaza.get(espacioAntiguo.getIdPlaza()).remove(espacioAntiguo);
                }
            }

            daoEspacioParqueadero.setObj(espacioActualizado);
            if (!daoEspacioParqueadero.update(posicion)) {
                throw new Exception("No se pudo actualizar los datos del espacio en la posición " + posicion);
            }

            if (espacioActualizado.getCodigo() != null) {
                espaciosPorCodigo.put(espacioActualizado.getCodigo(), espacioActualizado);
            }
            espaciosPorId.put(espacioActualizado.getId(), espacioActualizado);
            if (espacioActualizado.getIdPlaza() != null) {
                if (!espaciosPorPlaza.containsKey(espacioActualizado.getIdPlaza())) {
                    espaciosPorPlaza.put(espacioActualizado.getIdPlaza(), new java.util.ArrayList<>());
                }
                espaciosPorPlaza.get(espacioActualizado.getIdPlaza()).add(espacioActualizado);
            }
        } else {
            throw new Exception("Espacio con ID " + espacioActualizado.getId() + " no encontrado para actualizar.");
        }
    }

    public void cambiarEstadoEspacio(Integer idEspacio, EstadoEnum nuevoEstado) throws Exception {
        EspacioParqueadero espacio = obtenerEspacioPorId(idEspacio);
        if (espacio == null) {
            throw new Exception("No se encontró el espacio con ID " + idEspacio);
        }

        espacio.setEstado(nuevoEstado);
        actualizarEspacioParqueadero(espacio);
    }
}