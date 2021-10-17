package com.bcp.postulacion.tipoCambioService.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TipoCambioId implements Serializable {
    private String monedaOrigen;
    private String monedaDestino;
}
