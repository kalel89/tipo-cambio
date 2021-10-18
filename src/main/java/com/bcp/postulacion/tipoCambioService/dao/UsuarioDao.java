package com.bcp.postulacion.tipoCambioService.dao;

import com.bcp.postulacion.tipoCambioService.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, String> {


}
