package com.metodo.conexao1;

import java.util.ArrayList;

import com.expressao.*;
import com.util.ParSemComplementarException;
import com.util.Print;

public class Conexao1{

	private Expressao expressao;
	private ArrayList<Expressao> clausulas;
	private ArrayList<ArrayList<Expressao>> conjunto;
	private Grafo grafo;
	
	public Conexao1(Expressao expressao){
		this.expressao = expressao;
		this.clausulas = new ArrayList<>();
		this.conjunto = new ArrayList<>();
		this.grafo = new Grafo();
	}

	public void executar() throws ParSemComplementarException {
		
		Print.expressao("Expressao", this.expressao);
		
		this.expressao = expressao.avaliar(expressao);
		
		Print.expressao("FND",this.expressao);
		
		separarClausulas(expressao);
		
		montarGrafo();
		
		Print.texto("Explorando Grafo");
		
		for (Expressao startVertice : conjunto.get(0)) {
			this.grafo.explorarGrafo(startVertice, null);
		}
		
	}
	
	private void montarGrafo() throws ParSemComplementarException{
		
		Print.texto("Montando Grafo");
		
		int i = 0;
		int j = 0;
		int k = 0;
		
		while( i < conjunto.size() - 1){
			
			while(j < conjunto.get(i).size()){
				
				Expressao vertice = conjunto.get(i).get(j);
				this.grafo.addVertice(vertice);
			
				while( k < conjunto.get(i+1).size()){
					
					Expressao vizinho = conjunto.get(i+1).get(k);
					this.grafo.addVertice(vizinho);
					this.grafo.addAresta(new Aresta(vertice, vizinho));
					k += 1;
				}
				
				k = 0;
				j += 1;
			}
			
			j = 0;
			i += 1 ;
		}
		
		Print.objeto("Vertices", this.grafo.getVertices());
		Print.objeto("Arestas", this.grafo.getArestas());
		
	}
	
	private void separarClausulas(Expressao exp){
		
		
		this.clausulas.add(exp);
		int index = 0;
		
		
		while( index < clausulas.size()){
			
			exp = this.clausulas.get(index);
			
			//Verifica se a expressao e instancia da classe ExpAnd
			if(exp instanceof ExpOr){
				Expressao esq = ((ExpOr) exp).getEsq();
				this.clausulas.add(esq);
				Expressao dir = ((ExpOr) exp).getDir();
				this.clausulas.add(dir);
				this.clausulas.remove(exp);
			}else{
				index += 1;
			}
			
		}//end while
		
		for (Expressao expressao : this.clausulas) {
			
			this.conjunto.addAll(expressao.expressionToString());
		}
		
		Print.objeto("Clausulas", this.conjunto);
		
	}
	
	public boolean checaTipo() {
		return expressao.checaTipo();
 	}
	
	public Expressao getExpressao() {
		return expressao;
	}

}
