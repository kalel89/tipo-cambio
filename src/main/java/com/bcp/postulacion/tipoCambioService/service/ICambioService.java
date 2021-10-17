package com.bcp.postulacion.tipoCambioService.service;

import com.bcp.postulacion.tipoCambioService.rest.dto.CambioDto;
import io.reactivex.Maybe;

import java.math.BigDecimal;


public interface ICambioService {
    Maybe<CambioDto> getCambio(String origen, String destino, BigDecimal monto);
}
