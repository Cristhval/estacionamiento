package com.mistletoe.estaciona.base.controller.services;

import com.mistletoe.estaciona.base.controller.dao.dao_models.DaoUsuario;
import com.mistletoe.estaciona.base.models.Usuario;
import java.util.List;

public class UsuarioService {

    private DaoUsuario daoUsuario;

    public UsuarioService() {
        daoUsuario = new DaoUsuario();
    }

    public void crearUsuario(Usuario usuarioParaGuardar) throws Exception {
        if (usuarioParaGuardar == null) {
            throw new Exception("Error: No se puede guardar un objeto Usuario nulo.");
        }

        daoUsuario.setObj(usuarioParaGuardar);

        if (!daoUsuario.save()) {
            throw new Exception("No se pudo guardar los datos del usuario.");
        }
    }

    public List<Usuario> listarTodosUsuarios() {
        List<Usuario> listaDelDao = (List<Usuario>) daoUsuario.listAll();

        return listaDelDao;
    }
}