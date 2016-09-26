package com.api.tuto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.tuto.domain.PokeCapture;

public interface PokeCaptureRepository extends JpaRepository<PokeCapture, PKeyPokeCapture>{

}
