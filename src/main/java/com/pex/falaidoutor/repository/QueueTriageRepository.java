package com.pex.falaidoutor.repository;

import com.pex.falaidoutor.model.entity.QueueTriage;
import com.pex.falaidoutor.model.entity.StatusQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface QueueTriageRepository extends JpaRepository<QueueTriage, Long> {

    Optional<QueueTriage> findByPatientCpfAndQueueTicket(String cpf, String queueTicket);

    List<QueueTriage> findByStatus(StatusQueue status);

    Optional<QueueTriage> findByIdAndQueueTicket(Long id, String queueTicket);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE falaidoutor.queue_triage
           SET triage_id = :triageId,
               status_id    = 1
         WHERE id = :queueId
        """,
            nativeQuery = true
    )
    void linkTriageAndUpdateStatus(
            @Param("queueId") Long queueId,
            @Param("triageId") Long triageId
    );
}



