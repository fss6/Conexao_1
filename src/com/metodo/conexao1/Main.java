package com.metodo.conexao1;

import com.expressao.ExpNot;
import com.expressao.Expressao;
import com.expressao.ValorLiteral;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Visitante visitante = new VisitanteImplement();
		
		Grafo g = new Grafo();
		
		Expressao p = new ValorLiteral("P");
		Vertice vp = new Vertice(p);//P
		g.addVertice(vp);
		
		Expressao np = new ExpNot(new ValorLiteral("P"));
		Vertice vnp = new Vertice(np);//~P
		//g.addVertice(vnp);
		
		Expressao q = new ValorLiteral("Q");
		Vertice vq = new Vertice(q);//Q
		//g.addVertice(vq);
		
		Expressao nq = new ExpNot(new ValorLiteral("Q"));
		Vertice vnq = new Vertice(nq);//~Q
		//g.addVertice(vnq);
		
		vp.addAresta(new Aresta(vnp));
		vp.addAresta(new Aresta(vq));
		
		vnp.addAresta(new Aresta(vnq));
		vq.addAresta(new Aresta(vnq));
		
		
		visitante.avaliar(g);
		
	}

}
