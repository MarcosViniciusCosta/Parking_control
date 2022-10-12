package com.cloud.parking.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class EstacionamentoNaoEncontrado extends RuntimeException{

	private static final long serialVersionUID = -5077398062497130514L;

	public EstacionamentoNaoEncontrado(UUID uuid) 
	{
		super("Estacionamento com Id " + uuid.toString()+" não encontrado");
	}
	
}
