package com.pex.falaidoutor.repository;

import com.pex.falaidoutor.model.entity.QueueTriage;
import com.pex.falaidoutor.model.entity.StatusQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface QueueTriageRepository extends JpaRepository<QueueTriage, Long> {

    Optional<QueueTriage> findByPatientCpfAndQueueTicket(String cpf, String queueTicket);

    List<QueueTriage> findByStatus(StatusQueue status);
}


