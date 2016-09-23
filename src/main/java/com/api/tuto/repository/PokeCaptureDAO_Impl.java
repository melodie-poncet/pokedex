package com.api.tuto.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.tuto.domain.PokeCapture;

@Repository
public class PokeCaptureDAO_Impl implements PokeCaptureDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}


	@Override
	public void addPokeCapture(PokeCapture poke) {
		Session s = this.sessionFactory.getCurrentSession();
		s.persist(poke);
	}

	@Override
	public void updatePokeCapture(PokeCapture poke) {
		Session s = this.sessionFactory.getCurrentSession();
		s.update(poke);
	}


	@Override
	public boolean removePokeCapture(int id_dresseur, int id_typePokemon) {
		Session s = this.sessionFactory.getCurrentSession();
		Query query = s.createQuery("delete from poke_capture where id_pokemon = :id_p and id_dresseur = :id_d");
 		query.setParameter("id_p", id_typePokemon);
 		query.setParameter("id_d", id_dresseur);
 		int i = query.executeUpdate();
 		if (i != 1) {
 			return false;
 		}
 		return true;
	}
	
	@Override
	public boolean removePokeCapture(PokeCapture poke) {
		
		Session s = this.sessionFactory.getCurrentSession();
		s.delete(poke);
 		return true;
	}


	@Override
	public List<PokeCapture> getPokeCapturesByIdDresseur(int id_dresseur) {
		Session s = this.sessionFactory.getCurrentSession();
		Query query = s.createQuery("from poke_capture pc where pc.id_dresseur=:id");
		query.setParameter("id", id_dresseur);
		List<PokeCapture> result = query.list();
		return result;
	}


	@Override
	public List<PokeCapture> getPokeCapturesByIdTypePokemon(int id_typePokemon) {
		Session s = this.sessionFactory.getCurrentSession();
		Query query = s.createQuery("from poke_capture pc where pc.id_pokemon=:id");
		query.setParameter("id", id_typePokemon);
		List<PokeCapture> result = query.list();
		return result;
	}


	@Override
	public PokeCapture getPokeCaptureByIdTypePokemonAndIdDresseur(int id_typePokemon, int id_dresseur) {
		Session s = this.sessionFactory.getCurrentSession();
		Query query = s.createQuery("from poke_capture pc where pc.id_pokemon=:id");
		query.setParameter("id", id_typePokemon);
		PokeCapture result = (PokeCapture)query.list().get(0);
		return result;
		
	}




}
