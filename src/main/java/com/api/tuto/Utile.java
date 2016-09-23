package com.api.tuto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.tuto.domain.Dresseur;
import com.api.tuto.domain.PokeCapture;
import com.api.tuto.repository.DresseurRepository;

public class Utile {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	public static int getAutoID(DresseurRepository dresseurRepository){
		int cpt = 1;
		for(Dresseur d : dresseurRepository.findAll()){
			if(d.getId()>cpt){
				cpt = d.getId();
			}
		}
		return cpt + 1;
	}
	
	public void addPokeCapture(PokeCapture poke){
		Session s = this.sessionFactory.getCurrentSession();
	}

}
