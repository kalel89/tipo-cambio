package com.bcp.postulacion.tipoCambioService.service;

import com.bcp.postulacion.tipoCambioService.rest.dto.CambioDto;
import com.bcp.postulacion.tipoCambioService.rest.dto.TipoCambioDto;
import io.reactivex.Maybe;
import io.reactivex.Observable;

import java.math.BigDecimal;
import java.util.List;


public interface ITipoCambioService {
    Observable<TipoCambioDto> listarTodosTiposCambio();
    Maybe<TipoCambioDto> obtenerItem(String origen, String destino);
    Maybe<TipoCambioDto> guardar(String origen, String destino, BigDecimal tipoCambio);
    Maybe<TipoCambioDto> eliminar(String origen, String destino);
    Observable<TipoCambioDto> guardarMasivo(List<TipoCambioDto> lista);
}
