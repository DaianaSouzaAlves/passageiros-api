package com.roota.passageirosapi.repository;

import com.roota.passageirosapi.entity.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {
    Optional<Passageiro> findByEmail(String email);

    List<Passageiro> findByNomeContainingIgnoreCase(String nome);
}
