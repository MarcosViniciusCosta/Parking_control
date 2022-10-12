package com.cloud.parking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.parking.DTO.CriarVagaEstacionamentoDTO;
import com.cloud.parking.entity.VagaEstacionamento;
import com.cloud.parking.exception.EstacionamentoNaoEncontrado;
import com.cloud.parking.repository.VagaEstacionamentoRepository;
import com.cloud.parking.mapper.Mapeador;
@Service
public class VagaEstacionamentoService 
{
	private final Mapeador mapeador;
	
	private final VagaEstacionamentoRepository vagaEstacionamentoRepository;
	
	public VagaEstacionamentoService(Mapeador mapeador,VagaEstacionamentoRepository vagaEstacionamentoRepository){
		this.mapeador = mapeador;
		this.vagaEstacionamentoRepository = vagaEstacionamentoRepository;
	}
	
	@Transactional(readOnly = true,propagation=Propagation.SUPPORTS)
	public Optional<List<VagaEstacionamento>> buscarTodos()
	{
		return Optional.of(vagaEstacionamentoRepository.findAll());
	}


	@Transactional(readOnly = true,propagation=Propagation.SUPPORTS)
	public Optional<VagaEstacionamento> buscarPeloId(UUID uuid) {
		
		Optional<VagaEstacionamento> vagaEncontrada = vagaEstacionamentoRepository.findById(uuid);
		
		if(vagaEncontrada.isEmpty())
		{
			throw new EstacionamentoNaoEncontrado(uuid);
		}
		return vagaEncontrada;
	}
	
	@Transactional()
	public VagaEstacionamento criar(CriarVagaEstacionamentoDTO criarvagaEstacionamentoDTO) 
	{
		VagaEstacionamento vagaASerCriada = mapeador.converterCriarVagaEstacionamento(criarvagaEstacionamentoDTO);
		vagaASerCriada.setDataEntrada(LocalDateTime.now());
		VagaEstacionamento vagaCriada = vagaEstacionamentoRepository.save(vagaASerCriada);
		return vagaCriada;
	}

	@Transactional()
	public void deletar(UUID uuid) 
	{
		buscarPeloId(uuid);
		vagaEstacionamentoRepository.deleteById(uuid);
	}

	@Transactional()
	public VagaEstacionamento atualizar(UUID uuid, CriarVagaEstacionamentoDTO atualizarVagaEstacionamentoDTO) 
	{
		Optional<VagaEstacionamento> OptionalVagaBuscada = vagaEstacionamentoRepository.findById(uuid);
		VagaEstacionamento vagaBuscada = null;
		if(OptionalVagaBuscada.isPresent())
		{
			vagaBuscada = OptionalVagaBuscada.get();
			vagaBuscada.setCor(atualizarVagaEstacionamentoDTO.getCor());
			vagaBuscada.setModelo(atualizarVagaEstacionamentoDTO.getModelo());
			vagaBuscada.setEstado(atualizarVagaEstacionamentoDTO.getEstado());
			vagaBuscada.setLicensa(atualizarVagaEstacionamentoDTO.getLicensa());
			return vagaEstacionamentoRepository.save(vagaBuscada);
		}
		
		return vagaBuscada;
	}

	@Transactional()
	public VagaEstacionamento liberarVaga(UUID uuid) 
	{
		Optional<VagaEstacionamento> optionalVagaEstacionamentoAchada = vagaEstacionamentoRepository.findById(uuid);
		VagaEstacionamento vagaEstacionamentoAchada = null;
		if(optionalVagaEstacionamentoAchada.isPresent())
		{
			vagaEstacionamentoAchada = optionalVagaEstacionamentoAchada.get();
			vagaEstacionamentoAchada.setDataSaida(LocalDateTime.now());
			vagaEstacionamentoAchada.setValorConta(CalcularConta.calcularValorFinal(vagaEstacionamentoAchada));
			
		}
		
		//setar hora de saida, calcular gasto e atualizar objeto	
		return vagaEstacionamentoAchada;
	}

	
		
}
