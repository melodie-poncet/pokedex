package com.api.tuto;

import com.api.tuto.domain.Dresseur;
import com.api.tuto.repository.DresseurRepository;

public class Utile {
	
	public static int getAutoID(DresseurRepository dresseurRepository){
		int cpt = 1;
		for(Dresseur d : dresseurRepository.findAll()){
			if(d.getId()>cpt){
				cpt = d.getId();
			}
		}
		return cpt + 1;
	}

}
