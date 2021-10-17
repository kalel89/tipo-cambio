package com.bcp.postulacion.tipoCambioService.dao;

import com.bcp.postulacion.tipoCambioService.model.TipoCambio;
import com.bcp.postulacion.tipoCambioService.model.TipoCambioId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCambioDao extends JpaRepository<TipoCambio, TipoCambioId> {


}
