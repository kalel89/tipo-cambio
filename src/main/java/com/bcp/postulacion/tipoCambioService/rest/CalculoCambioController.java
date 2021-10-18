package com.bcp.postulacion.tipoCambioService.rest;

import com.bcp.postulacion.tipoCambioService.dao.TipoCambioDao;
import com.bcp.postulacion.tipoCambioService.rest.dto.CambioDto;
import com.bcp.postulacion.tipoCambioService.service.ICambioService;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("cambio")
public class CalculoCambioController {

    @Autowired
    private ICambioService service;

    @GetMapping(value = "/{monto}/{origen}/a/{destino}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Maybe<CambioDto> getMontoCambiado(
            @PathVariable("origen") String origen,
            @PathVariable("destino") String destino,
            @PathVariable("monto") BigDecimal monto) {
        return service.getCambio(origen, destino, monto);
    }

}
