package com.cloud.parking.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		Optional<VagaEstacionamento> objetoEncontrado = vagaEstacionamentoService.buscarPeloId(uuid);
		
		if(objetoEncontrado.isPresent())
		{
			return ResponseEntity.ok(mapeador.converterVagaEstacionamentoDTO(objetoEncontrado.get()));
		}
			return ResponseEntity.badRequest().body("Lista de Estacionamento vazia");
		
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<Object> deletar(@PathVariable UUID uuid)
	{
			vagaEstacionamentoService.deletar(uuid);
			
			return ResponseEntity.ok().body("Vaga de estacionamento deletada");
			
	}
	
	
	
	@PostMapping()
	public ResponseEntity<Object> criar(@RequestBody CriarVagaEstacionamentoDTO criarVagaEstacionamentoDTO)
	{
		VagaEstacionamento objetoCriado = vagaEstacionamentoService.criar(criarVagaEstacionamentoDTO);
		objetoCriado.setDataEntrada(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CREATED).body(mapeador.converterVagaEstacionamentoDTO(objetoCriado));
		
	}
	
	
	@PutMapping("/atualizar/{uuid}")
	public ResponseEntity<Object> atualizar(@RequestBody CriarVagaEstacionamentoDTO atualizarVagaEstacionamentoDTO,@PathVariable UUID uuid)
	{
		VagaEstacionamento objetoAtualizado = vagaEstacionamentoService.atualizar(uuid,atualizarVagaEstacionamentoDTO);
		return ResponseEntity.status(HttpStatus.OK).body(mapeador.converterVagaEstacionamentoDTO(objetoAtualizado));
		
	}
	
	
	@PutMapping("/liberarVaga/{uuid}")
	public ResponseEntity<Object> liberarVaga(@PathVariable UUID uuid)
	{
		System.out.println("UUID = " + uuid);
		VagaEstacionamento objetoAtualizado = vagaEstacionamentoService.liberarVaga(uuid);
		return ResponseEntity.status(HttpStatus.OK).body(mapeador.converterVagaEstacionamentoDTO(objetoAtualizado));
		
	}
	
	
	
	
}
