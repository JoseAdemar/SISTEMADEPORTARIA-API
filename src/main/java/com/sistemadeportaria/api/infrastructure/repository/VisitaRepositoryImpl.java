package com.sistemadeportaria.api.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sistemadeportaria.api.model.Visita;
import com.sistemadeportaria.api.model.Visitante;
import com.sistemadeportaria.api.repository.VisitaRepositoryQueries;

@Repository
public class VisitaRepositoryImpl implements VisitaRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Visita> find(LocalDateTime dataDaVisita, Visitante visitante) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Visita> criteria = builder.createQuery(Visita.class);
		Root<Visita> root = criteria.from(Visita.class);
		
	    Predicate dataDaVisitaPredicate = builder.like(root.get
				("dataDaVisita"), "%" + dataDaVisita + "%");
		
		
		
		criteria.where(dataDaVisitaPredicate);
		
		TypedQuery<Visita> query = manager.createQuery(criteria);
		return query.getResultList();
	}

}
