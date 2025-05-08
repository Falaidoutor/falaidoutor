package com.pex.falaidoutor.domain.repository;

import com.pex.falaidoutor.domain.model.entity.QueueTriage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<QueueTriage, Long> {

    Optional<QueueTriage> findByPatientCpfAndQueueTicket(String cpf, String queueTicket);
}
