package com.bcp.postulacion.tipoCambioService.rest;

import com.bcp.postulacion.tipoCambioService.rest.dto.CambioDto;
import com.bcp.postulacion.tipoCambioService.service.ICambioService;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("tipo-cambio")
public class TipoCambioController {

    @Autowired
    private ICambioService service;

    @GetMapping("/{monto}/{origen}/a/{destino}")
    public Maybe<CambioDto> getMontoCambiado(
            @PathVariable("origen") String origen,
            @PathVariable("destino") String destino,
            @PathVariable("monto") BigDecimal monto) {
        return service.getCambio(origen, destino, monto);
    }

}
