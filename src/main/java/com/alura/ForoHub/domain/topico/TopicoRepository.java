package com.alura.ForoHub.domain.topico;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Topico t WHERE t.id = :id")
    void deleteTopicoById(@Param("id") Long id);
}
