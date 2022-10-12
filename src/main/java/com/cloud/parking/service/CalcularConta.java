package com.cloud.parking.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.cloud.parking.entity.VagaEstacionamento;

public class CalcularConta 
{
	
	public static final int UMA_HORA = 60;
	public static final int UM_DIA = 24 * UMA_HORA;
	public static final double VALOR_POR_HORA = 3;
	public static final double VALOR_POR_DIARIA = 12;
	
	
	public static double calcularValorFinal(VagaEstacionamento vaga)
	{
		return calcularValorFinal(vaga.getDataEntrada(),vaga.getDataSaida());
	}
	
	private static double calcularValorFinal(LocalDateTime entrada, LocalDateTime saida)
	{
		long diferencaMinutos = entrada.until(saida, ChronoUnit.MINUTES);
		double valorContaFinal = 0;
		while (diferencaMinutos>=0)
		{
			if(diferencaMinutos >= UM_DIA)
			{
				valorContaFinal += VALOR_POR_DIARIA;
				diferencaMinutos-=UM_DIA;
			}else
			{
				valorContaFinal += VALOR_POR_HORA;
				diferencaMinutos-=UMA_HORA;
			}
		}
			
		return valorContaFinal;
	}
	
}
