package com.metodo.conexao1;

import com.expressao.Expressao;

public class Aresta implements Item {
	
	private Expressao vertice;
	private Expressao vizinho;
	private boolean visitada;
	
	public Aresta(Expressao vertice, Expressao vizinho){
		this.vertice = vertice;
		this.vizinho = vizinho;
		this.visitada = false;
	}
	
	public Expressao getVertice(){
		return this.vertice;
	}
	
	public Expressao getVizinho(){
		return this.vizinho;
	}
	
	public boolean foiVisitada(){
		return this.visitada;
	}
	
	public void visitada(){
		this.visitada = true;
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
	
	@Override
	public void accept(Visitante visitante) {
		visitante.avaliar(this);
	}
}
