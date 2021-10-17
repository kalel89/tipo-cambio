package com.bcp.postulacion.tipoCambioService.rest.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class TipoCambioDto {
    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal tipoCambio;
}
