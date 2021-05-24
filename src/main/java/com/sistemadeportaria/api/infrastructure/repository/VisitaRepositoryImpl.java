package com.sistemadeportaria.api.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sistemadeportaria.api.model.Visita;
import com.sistemadeportaria.api.model.Visitante;
import com.sistemadeportaria.api.repository.VisitaRepositoryQueries;

@Repository
public class VisitaRepositoryImpl implements VisitaRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Visita> find(LocalDateTime dataDaVisita, String setor, Visitante visitante) {
		
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Visita> criteria = builder.createQuery(Visita.class);
//		Root<Visita> root = criteria.from(Visita.class);
//		
//		var predicates = new ArrayList<Predicate>();
//		
//
//		if (dataDaVisita != null) {
//			predicates.add(builder.greaterThanOrEqualTo(root.get("dataDaVisita"),  dataDaVisita));
//		}
//		
//		if (setor != null)  {
//			predicates.add(builder.like(root.get("setor"), "%" + setor + "%"));
//		}
//		
//		if (visitante != null) {
//			predicates.add(builder.like(root.get("visitante"), "%" + visitante.getCpf().toLowerCase() + "%"));
//		}
//		
//		
//		
//		
//		criteria.where(predicates.toArray(new Predicate[0]));
//		
//		var query = manager.createQuery(criteria);
//		return query.getResultList();
		
		
		
		var jpql = new StringBuilder();
		jpql.append("from Visita where 0 = 0 ");
		
		var parametros = new HashMap<String, Object>();
		
		if (StringUtils.hasLength(setor)) {
			jpql.append("and setor like :setor ");
			parametros.put("setor", "%" + setor + "%");
		}
		
		if (dataDaVisita != null) {
			jpql.append("and dataDaVisita like :dataDaVisita ");
			parametros.put("dataDaVisita", dataDaVisita);
		}
		
		if (visitante != null) {
			jpql.append("and visitante like :visitante ");
			parametros.put("visitante", visitante.getCpf());
		}
		
		TypedQuery<Visita> query = manager
				.createQuery(jpql.toString(), Visita.class);
		
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

		return query.getResultList();
		
		
	}

}
