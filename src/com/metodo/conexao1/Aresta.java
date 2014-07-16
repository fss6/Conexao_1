package com.metodo.conexao1;

public class Aresta implements Item {
	
	private Vertice vertice;
	
	public Aresta(Vertice v){
		this.vertice = v;
	}
	
	public Vertice getProximo(){
		return this.vertice;
	}

	@Override
	public void accept(Visitante visitante) {
		visitante.avaliar(this);
	}
}
