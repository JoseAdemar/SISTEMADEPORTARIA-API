package com.sistemadeportaria.api.infrastructure.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.swing.text.DateFormatter;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.sistemadeportaria.api.model.Visita;
import com.sistemadeportaria.api.repository.VisitaRepositoryQueries;

@Repository
public class VisitaRepositoryImpl implements VisitaRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Visita> find(String setor, LocalDateTime dataDaVisita) {
		
		var jpql = new StringBuilder();
		jpql.append("from Visita where 0 = 0 ");
		
		var parametros = new HashMap<String, Object>();
		
		if (StringUtils.hasLength(setor)) {
			jpql.append("and setor like :setor ");
			parametros.put("setor", "%" + setor + "%");
		}
		
		if (dataDaVisita != null) {
			jpql.append("and dataDaVisita = :dataDaVisita ");
			parametros.put("dataDaVisita",  dataDaVisita );
		}
		
	
		TypedQuery<Visita> query = manager
				.createQuery(jpql.toString(), Visita.class);
		
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

		return query.getResultList();
		
		
	}

}
