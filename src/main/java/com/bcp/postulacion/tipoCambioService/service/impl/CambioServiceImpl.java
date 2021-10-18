package com.bcp.postulacion.tipoCambioService.service.impl;

import com.bcp.postulacion.tipoCambioService.dao.TipoCambioDao;
import com.bcp.postulacion.tipoCambioService.model.TipoCambio;
import com.bcp.postulacion.tipoCambioService.model.TipoCambioId;
import com.bcp.postulacion.tipoCambioService.rest.dto.CambioDto;
import com.bcp.postulacion.tipoCambioService.service.ICambioService;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;

@Service
public class CambioServiceImpl implements ICambioService {

    @Autowired
    private TipoCambioDao tipoCambioDao;

    private final MathContext mc = new MathContext(34);

    @Override
    public Maybe<CambioDto> getCambio(String origen, String destino, BigDecimal monto) {
        return Maybe.just(
                tipoCambioDao.findById(new TipoCambioId(origen, destino)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(t-> multiplyTipoCambio(t, monto))
                .switchIfEmpty(
                        Maybe.just(
                                tipoCambioDao.findById(new TipoCambioId(destino, origen)))
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .map(t-> divideTipoCambio(t, monto))
                )
                .switchIfEmpty(Maybe.error(new Exception("No se encuentra configuracion asignada")));

    }

    private CambioDto multiplyTipoCambio(TipoCambio t, BigDecimal monto) {
        return CambioDto.builder()
                .monto(monto)
                .monedaOrigen(t.getMonedaOrigen())
                .monedaDestino(t.getMonedaDestino())
                .tipoCambio(t.getTipoCambio())
                .montoConTipoCambio(t.getTipoCambio()
                        .multiply(monto, mc).setScale(3))
                .build();
    }

    private CambioDto divideTipoCambio(TipoCambio t, BigDecimal monto) {
        return CambioDto.builder()
                .monto(monto)
                .monedaOrigen(t.getMonedaOrigen())
                .monedaDestino(t.getMonedaDestino())
                .tipoCambio(t.getTipoCambio())
                .montoConTipoCambio(BigDecimal.ONE.divide(t.getTipoCambio(), mc)
                        .multiply(monto, mc).setScale(3))
                .build();
    }

}
