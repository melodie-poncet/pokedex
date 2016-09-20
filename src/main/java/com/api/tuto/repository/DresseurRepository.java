package com.api.tuto.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.api.tuto.domain.Dresseur;

public interface DresseurRepository extends JpaRepository<Dresseur, Integer> {

	Dresseur findById(int id);
}
