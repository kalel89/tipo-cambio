package com.bcp.postulacion.tipoCambioService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TipoCambioId.class)
public class TipoCambio {
    @Id
    private String monedaOrigen;

    @Id
    private String monedaDestino;

    private BigDecimal tipoCambio;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

}
