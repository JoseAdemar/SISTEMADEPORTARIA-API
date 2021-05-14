package com.sistemadeportaria.api.infrastructure.repository;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sistemadeportaria.api.model.Visitante;
import com.sistemadeportaria.api.repository.VisitanteRepositoryQueries;

@Repository
public class VisitanteRepositoryImpl implements VisitanteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Visitante> find(String nome, String cpf, String telefone) {

		var jpql = new StringBuilder();
		jpql.append("from Visitante where 0 = 0 ");
		
		var parametros = new HashMap<String, Object>();

		if (StringUtils.hasLength(nome)) {

			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}

		if (cpf != null) {

			jpql.append("and cpf like :cpf ");
			parametros.put("cpf", cpf);
		}

		if (telefone != null) {

			jpql.append("and telefone like :telefone ");
			parametros.put("telefone", telefone);
		}

		TypedQuery<Visitante> query = manager.createQuery(jpql.toString(), Visitante.class);

				parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
				return query.getResultList();

	}

}
