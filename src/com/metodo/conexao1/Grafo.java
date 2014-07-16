package com.metodo.conexao1;

import java.util.ArrayList;

public class Grafo implements Item {
	
	private ArrayList<Vertice> vertices;
	
	public Grafo(){
		this.vertices = new ArrayList<>();
	}
	
	public void addVertice(Vertice v){
		this.vertices.add(v);
	}

	@Override
	public void accept(Visitante visitante) {
		// TODO Auto-generated method stub
		visitante.avaliar(this);
	}
	
	public ArrayList<Vertice> getVertices(){
		return this.vertices;
	}
	
}
