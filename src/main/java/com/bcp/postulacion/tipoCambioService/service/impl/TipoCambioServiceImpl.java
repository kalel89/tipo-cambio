package com.bcp.postulacion.tipoCambioService.service.impl;

import com.bcp.postulacion.tipoCambioService.dao.TipoCambioDao;
import com.bcp.postulacion.tipoCambioService.model.TipoCambio;
import com.bcp.postulacion.tipoCambioService.model.TipoCambioId;
import com.bcp.postulacion.tipoCambioService.rest.dto.CambioDto;
import com.bcp.postulacion.tipoCambioService.rest.dto.TipoCambioDto;
import com.bcp.postulacion.tipoCambioService.service.ICambioService;
import com.bcp.postulacion.tipoCambioService.service.ITipoCambioService;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TipoCambioServiceImpl implements ITipoCambioService {

    @Autowired
    private TipoCambioDao tipoCambioDao;

    private final MathContext mc = new MathContext(34);

    @Override
    public Observable<TipoCambioDto> listarTodosTiposCambio() {
        return Observable.fromIterable(tipoCambioDao.findAll())
                .map(this::assemblerToTipoCambioDto);
    }

    @Override
    public Maybe<TipoCambioDto> obtenerItem(String origen, String destino) {
        return obtenerItemById(origen, destino)
                .map(Pair::getKey)
                .map(this::assemblerToTipoCambioDto);
    }

    @Override
    public Maybe<TipoCambioDto> guardar(String origen, String destino, BigDecimal tipoCambio) {
        return obtenerItemById(origen, destino)
                .map(registroExistente -> {
                    registroExistente.getKey().setTipoCambio(tipoCambio);
                    registroExistente.getKey().setFechaActualizacion(LocalDateTime.now());
                    return registroExistente;
                })
                .map(this::adaptarTipoCambioPorOrigenDeMoneda)
                .defaultIfEmpty(new TipoCambio(origen, destino, tipoCambio, LocalDateTime.now(), null))
                .map(tipoCambioDao::save)
                .map(this::assemblerToTipoCambioDto);
    }

    private TipoCambio adaptarTipoCambioPorOrigenDeMoneda(Pair<TipoCambio, Boolean> pair) {
        pair.getKey().setTipoCambio(
                pair.getValue()
                        ? pair.getKey().getTipoCambio()
                        : BigDecimal.ONE.divide(pair.getKey().getTipoCambio(), mc).setScale(3));
        return pair.getKey();
    }

    @Override
    public Maybe<TipoCambioDto> eliminar(String origen, String destino) {
        return obtenerItemById(origen, destino)
                .map(x -> {
                    tipoCambioDao.delete(x.getKey());
                    return  x.getKey();
                })
                .map(this::assemblerToTipoCambioDto);
    }

    private Maybe<Pair<TipoCambio, Boolean>> obtenerItemById(String origen, String destino) {
        return Maybe.just(
                tipoCambioDao.findById(new TipoCambioId(origen, destino)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(x -> new Pair<TipoCambio, Boolean>(x, Boolean.TRUE))
                .switchIfEmpty(
                        Maybe.just(
                                tipoCambioDao.findById(new TipoCambioId(destino, origen)))
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .map(x -> new Pair<TipoCambio, Boolean>(x, Boolean.FALSE))
                );
    }

    private TipoCambioDto assemblerToTipoCambioDto(TipoCambio t) {
        return TipoCambioDto.builder()
                .monedaOrigen(t.getMonedaOrigen())
                .monedaDestino(t.getMonedaDestino())
                .tipoCambio(t.getTipoCambio())
                .build();
    }
}
