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

    Optional<QueueTriage> findById(Long id);

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

    @Query(value = """
        SELECT  
            qt.id as queue_id,
            p.name AS name,
            p.gender AS gender,
            p.age,
            qt.queue_ticket AS queueTicket, 
            t.risk AS risk
        FROM falaidoutor.queue_triage qt
        LEFT JOIN falaidoutor.patient p ON qt.patient_id = p.id 
        LEFT JOIN falaidoutor.triage t ON qt.triage_id = t.id
        LEFT JOIN falaidoutor.status_queue s ON qt.status_id = s.id
        WHERE qt.status_id = 1
        ORDER BY
            CASE 
                WHEN t.risk = 'Urgente' THEN 1
                WHEN t.risk = 'Grave' THEN 2
                WHEN t.risk = 'Moderado' THEN 3
                WHEN t.risk = 'Baixo' THEN 4
                WHEN t.risk = 'Nao Urgente' THEN 5
                ELSE 6
            END, qt.queue_ticket ASC
        """, nativeQuery = true)
        List<Object[]> findAllFinalizedTriageData();
}



