package com.cloud.parking.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.parking.DTO.VagaEstacionamentoDTO;
import com.cloud.parking.entity.VagaEstacionamento;
import com.cloud.parking.service.VagaEstacionamentoService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class VagaEstacionamentoController 
{
	private final VagaEstacionamentoService vagaEstacionamentoService;
	
	public VagaEstacionamentoController(VagaEstacionamentoService vagaEstacionamentoService)
	{
		this.vagaEstacionamentoService = vagaEstacionamentoService;
	}

	@GetMapping("/")
	public ResponseEntity<Object> helloWorld()
	{
		Optional<List<VagaEstacionamentoDTO>> lista = vagaEstacionamentoService.buscarTodos();
		
		if(lista.isPresent())
		{
			return ResponseEntity.ok(lista.get());
		}
			return ResponseEntity.badRequest().body("Lista de Estacionamento vazia");
		
	}
}
