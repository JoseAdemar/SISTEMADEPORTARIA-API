package com.sistemadeportaria.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
   ENTIDADE_NAO_ENCONTRADA("/visitantes", "Entidade não encontrado");
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String uri) {
		this.uri = "http://localhost:8080" + path;
		this.title = title;
		
	}
	
	
}
