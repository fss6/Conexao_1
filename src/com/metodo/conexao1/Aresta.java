package com.metodo.conexao1;

import com.expressao.Expressao;

public class Aresta{
	
	private Expressao vertice;
	private Expressao vizinho;
	
	public Aresta(Expressao vertice, Expressao vizinho){
		this.vertice = vertice;
		this.vizinho = vizinho;
	}
	
	public Expressao getVertice(){
		return this.vertice;
	}
	
	public Expressao getVizinho(){
		return this.vizinho;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append('(');
		sb.append(this.vertice);
		sb.append(',');
		sb.append(' ');
		sb.append(this.vizinho);
		sb.append(')');
		
		return sb.toString();
	}
	
}
