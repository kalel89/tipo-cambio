package com.bcp.postulacion.tipoCambioService.rest.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class CambioDto {
    private BigDecimal monto;
    private BigDecimal montoConTipoCambio;
    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal tipoCambio;
}
