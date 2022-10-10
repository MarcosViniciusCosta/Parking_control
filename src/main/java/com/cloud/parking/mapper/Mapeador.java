package com.cloud.parking.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.cloud.parking.entity.VagaEstacionamento;
import com.cloud.parking.DTO.VagaEstacionamentoDTO;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapeador 
{
	
	private static final ModelMapper MODEL_MAPPER = new ModelMapper();
	
	public VagaEstacionamentoDTO converterVagaEstacionamentoDTO(VagaEstacionamento vaga)
	{
		return MODEL_MAPPER.map(vaga, VagaEstacionamentoDTO.class);
	}
	
	
	public List<VagaEstacionamentoDTO> converterVagasEstacionamentoDTO(List<VagaEstacionamento> listaVagaEstacionamento)
	{
		return listaVagaEstacionamento.stream().map(this::converterVagaEstacionamentoDTO).collect(Collectors.toList());
	}
	
}
