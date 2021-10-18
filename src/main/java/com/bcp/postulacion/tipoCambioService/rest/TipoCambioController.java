package com.bcp.postulacion.tipoCambioService.rest;

import com.bcp.postulacion.tipoCambioService.rest.dto.CambioDto;
import com.bcp.postulacion.tipoCambioService.rest.dto.TipoCambioDto;
import com.bcp.postulacion.tipoCambioService.service.ICambioService;
import com.bcp.postulacion.tipoCambioService.service.ITipoCambioService;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("tipo-cambio")
public class TipoCambioController {

    @Autowired
    private ITipoCambioService service;

    @GetMapping()
    public Observable<TipoCambioDto> getList() {
        return service.listarTodosTiposCambio();
    }

    @GetMapping("/{origen}/a/{destino}")
    public Maybe<TipoCambioDto> getItem(
            @PathVariable("origen") String origen,
            @PathVariable("destino") String destino) {
        return service.obtenerItem(origen, destino);
    }

    @PostMapping()
    public Maybe<TipoCambioDto> guardar(@RequestBody(required = true) TipoCambioDto tipoCambioDto) {
        return service.guardar(
                tipoCambioDto.getMonedaOrigen(),
                tipoCambioDto.getMonedaDestino(),
                tipoCambioDto.getTipoCambio());
    }

    @DeleteMapping()
    public Maybe<TipoCambioDto> eliminar(@RequestBody(required = true) TipoCambioDto tipoCambioDto) {
        return service.eliminar(
                tipoCambioDto.getMonedaOrigen(),
                tipoCambioDto.getMonedaDestino());
    }

}
