package com.cloud.parking.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.parking.DTO.CriarVagaEstacionamentoDTO;
import com.cloud.parking.DTO.VagaEstacionamentoDTO;
import com.cloud.parking.entity.VagaEstacionamento;
import com.cloud.parking.mapper.Mapeador;
import com.cloud.parking.service.VagaEstacionamentoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/vagaEstacionamento")
public class VagaEstacionamentoController 
{
	private final VagaEstacionamentoService vagaEstacionamentoService;
	private final Mapeador mapeador;
	
	public VagaEstacionamentoController(VagaEstacionamentoService vagaEstacionamentoService
			,Mapeador mapeador)
	{
		this.vagaEstacionamentoService = vagaEstacionamentoService;
		this.mapeador = mapeador;
	}

	@GetMapping()
	public ResponseEntity<Object> buscarTodos()
	{
		
		Optional<List<VagaEstacionamento>> lista = vagaEstacionamentoService.buscarTodos();
		
		if(lista.isPresent())
		{
			return ResponseEntity.ok(mapeador.converterVagasEstacionamentoDTO(lista.get()));
		}
			return ResponseEntity.badRequest().body("Lista de Estacionamento vazia");
		
	}
	
	@GetMapping("/{uuid}")
	public ResponseEntity<Object> buscarPeloId(@PathVariable UUID uuid)
	{
		Optional<VagaEstacionamento> objetoEncontrado = vagaEstacionamentoService.buscarPeloId();
		
		if(objetoEncontrado.isPresent())
		{
			return ResponseEntity.ok(mapeador.converterVagaEstacionamentoDTO(objetoEncontrado.get()));
		}
			return ResponseEntity.badRequest().body("Lista de Estacionamento vazia");
		
	}
	
	
	@PostMapping()
	public ResponseEntity<Object> criar(@RequestBody CriarVagaEstacionamentoDTO criarVagaEstacionamentoDTO)
	{
		VagaEstacionamento objetoCriado = vagaEstacionamentoService.criar(criarVagaEstacionamentoDTO);
		objetoCriado.setDataEntrada(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CREATED).body(mapeador.converterVagaEstacionamentoDTO(objetoCriado));
		
	}
	
	
	
	
	
	
}
