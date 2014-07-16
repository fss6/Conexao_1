package com.metodo.conexao1;

import java.util.ArrayList;

public class VisitanteImplement implements Visitante {

	@Override
	public void avaliar(Grafo g) {
		
		ArrayList<Vertice> vertices = g.getVertices();
		
		for (Vertice vertice : vertices) {
			System.out.println("Verice: " + vertice.getExpressao());
			this.avaliar(vertice);
			
		}
	}

	@Override
	public void avaliar(Vertice v) {

		ArrayList<Aresta> arestas = v.getArestas();
		
		for (Aresta aresta : arestas) {
			System.out.println("Aresta: " + v.getExpressao());
			this.avaliar(aresta);
		}
		System.out.println("-----");
		
	}

	@Override
	public void avaliar(Aresta aresta) {
		
		if(aresta.getProximo() != null){
			this.avaliar(aresta.getProximo());
		}else{
			System.out.println("Aresta: "+ aresta.getProximo().getExpressao());
		}
		
	}

}
