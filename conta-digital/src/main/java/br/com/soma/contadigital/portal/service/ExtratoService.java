package br.com.soma.contadigital.portal.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.soma.contadigital.portal.model.Extrato;

@Service
public class ExtratoService {
	
	public String calculoSaldo(List<Extrato> extratos) {
		return extratos.stream().map(item->item.getValor())
					.reduce(BigDecimal.ZERO,BigDecimal::add).toString();
	}
}
