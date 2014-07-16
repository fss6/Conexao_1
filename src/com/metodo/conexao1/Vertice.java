package com.metodo.conexao1;

import java.util.ArrayList;

import com.expressao.Expressao;

public class Vertice implements Item {
	
	private ArrayList<Aresta> arestas;
	private Expressao expressao;
	
	public Vertice(Expressao e){
		this.expressao = e;
		this.arestas = new ArrayList<>();
	}
	
	public void addAresta(Aresta a){
		this.arestas.add(a);
	}
	
	public ArrayList<Aresta> getArestas(){
		return this.arestas;
	}
	
	public Expressao getExpressao(){
		return this.expressao;
	}

	@Override
	public void accept(Visitante visitante) {
		// TODO Auto-generated method stub
		visitante.avaliar(this);
	}
	
}
