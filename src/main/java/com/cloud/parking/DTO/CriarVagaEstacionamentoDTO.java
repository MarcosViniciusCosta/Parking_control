package com.cloud.parking.DTO;

public class CriarVagaEstacionamentoDTO 
{
	private String licensa;
	private String estado;
	private String modelo;
	private String cor;
	
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
}
