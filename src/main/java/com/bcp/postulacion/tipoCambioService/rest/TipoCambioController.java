package com.bcp.postulacion.tipoCambioService.rest;

import com.bcp.postulacion.tipoCambioService.rest.dto.CambioDto;
import com.bcp.postulacion.tipoCambioService.rest.dto.TipoCambioDto;
import com.bcp.postulacion.tipoCambioService.service.ICambioService;
import com.bcp.postulacion.tipoCambioService.service.ITipoCambioService;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("tipo-cambio")
public class TipoCambioController {

    @Autowired
    private ITipoCambioService service;

    @GetMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Observable<TipoCambioDto> getList() {
        return service.listarTodosTiposCambio();
    }

    @GetMapping(value = "/{origen}/a/{destino}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Maybe<TipoCambioDto> getItem(
            @PathVariable("origen") String origen,
            @PathVariable("destino") String destino) {
        return service.obtenerItem(origen, destino);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Maybe<TipoCambioDto> guardar(@RequestBody(required = true) TipoCambioDto tipoCambioDto) {
        return service.guardar(
                tipoCambioDto.getMonedaOrigen(),
                tipoCambioDto.getMonedaDestino(),
                tipoCambioDto.getTipoCambio());
    }

    @PostMapping(value = "/masivo",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Observable<TipoCambioDto> guardarMasivo(@RequestBody(required = true) List<TipoCambioDto> tipoCambioDto) {
        return service.guardarMasivo(tipoCambioDto);
    }

    @DeleteMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Maybe<TipoCambioDto> eliminar(@RequestBody(required = true) TipoCambioDto tipoCambioDto) {
        return service.eliminar(
                tipoCambioDto.getMonedaOrigen(),
                tipoCambioDto.getMonedaDestino());
    }

}
