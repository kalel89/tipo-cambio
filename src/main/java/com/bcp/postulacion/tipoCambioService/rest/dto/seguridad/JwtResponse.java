package com.bcp.postulacion.tipoCambioService.rest.dto.seguridad;

import lombok.*;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class JwtResponse implements Serializable {
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }
}
