package com.cloud.parking.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.parking.entity.VagaEstacionamento;

@Repository
public interface VagaEstacionamentoRepository extends JpaRepository<VagaEstacionamento, UUID>
{
	
}
