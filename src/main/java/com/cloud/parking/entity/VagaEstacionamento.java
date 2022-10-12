package com.cloud.parking.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class VagaEstacionamento 
{
	@Id
	private UUID uuid;
	@NotBlank(message="Licensa não pode ser vazia")
	private String licensa;
	@NotBlank(message="Estado não pode ser vazio")
	private String estado;
	@NotBlank(message="Modelo não pode ser vazio")
	private String modelo;
	@NotBlank(message="Cor não pode ser vazia")
	private String cor;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataEntrada;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataSaida;
	private Double valorConta;
	
	public VagaEstacionamento() 
	{
		this.uuid = UUID.randomUUID();
	}
	
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getLicensa() {
		return licensa;
	}
	public void setLicensa(String licensa) {
		this.licensa = licensa;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public LocalDateTime getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Double getValorConta() {
		return valorConta;
	}
	public void setValorConta(Double valorConta) {
		this.valorConta = valorConta;
	}
}
