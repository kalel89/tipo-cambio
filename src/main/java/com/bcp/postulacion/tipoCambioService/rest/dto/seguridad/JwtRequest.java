package com.bcp.postulacion.tipoCambioService.rest.dto.seguridad;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class JwtRequest implements Serializable {
    private String username;
    private String password;

}
