package com.cloud.parking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cloud.parking.DTO.VagaEstacionamentoDTO;
import com.cloud.parking.entity.VagaEstacionamento;
import com.cloud.parking.mapper.Mapeador;
@Service
public class VagaEstacionamentoService 
{
	private final Mapeador mapeador;
	
	public VagaEstacionamentoService(Mapeador mapeador) {
		this.mapeador = mapeador;
	}
	
	
	
	public Optional<List<VagaEstacionamentoDTO>> buscarTodos()
	{
		List<VagaEstacionamento> lista = new ArrayList<>();
		VagaEstacionamento vaga = new VagaEstacionamento();
		vaga.setCor("Branco");
		vaga.setEstado("Ocupada");
		vaga.setLicensa("Válida");
		vaga.setModelo("Mercedes F1");
		vaga.setUuid(UUID.randomUUID());
		vaga.setValorConta(0.0);
		lista.add(vaga);
		return Optional.of(mapeador.converterVagasEstacionamentoDTO(lista));
	}
}
