package com.metodo.conexao1;

import java.util.ArrayList;

import com.expressao.*;

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

	public void executar() {
		
		printExpressao("Expressao", expressao);
		printExpressao("FND",expressao.avaliar(expressao));
		separarClausulas(expressao);
		montarGrafo();
		
	}
	
	private void montarGrafo(){
		
		System.out.println("Montar Grafo...");
		System.out.println();
		
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
		
		
		Expressao startVertice = conjunto.get(0).get(0);
		System.out.println("Vertices: "+ this.grafo.getVertices());
		System.out.println("Arestas: "+this.grafo.getArestas());
		System.out.println("Path: " + this.grafo.explorarGrafo(startVertice, null));
		
	}
	
	private boolean encontrarComplementar(Expressao exp, int proximo){
		
		boolean complementar = false;
		ValorLiteral v1 = null;
		ValorLiteral v2 = null;
		
		//Verifica expressoes ExpNot
		if (exp instanceof ExpNot)
			v1 = (ValorLiteral) ((ExpUnaria) exp).getExp();
		
		//Verifica expressoes ValorLiteral
		if (exp instanceof ValorLiteral)
			v1 = (ValorLiteral) exp;
		
		//Laco para iterar o conjunto: [ clausula_1, clausula_2, clausula_n]
		for ( int k = proximo ; k < conjunto.size() ; k++){
			int i=0;
			
			//Laco para iterar as clausulas: [ expressao_1, expressao_2, expressao_n ]
			while ( i < conjunto.get(k).size()){
					
				//Verifica se e uma expressao ExpNot
				if (conjunto.get(k).get(i) instanceof ExpNot)
					v2 = (ValorLiteral) ((ExpUnaria) conjunto.get(k).get(i)).getExp();
				
				//Verifica se e uma expressao ValorLiteral
				if (conjunto.get(k).get(i) instanceof ValorLiteral)
					v2 = (ValorLiteral) conjunto.get(k).get(i);
				
				//Se o valor das expressoes sao igual
				if(v1.valor().equals(v2.valor())){
					
					//complementar recebe true se
					if (exp instanceof ExpNot && conjunto.get(k).get(i) instanceof ValorLiteral
							|| exp instanceof ValorLiteral && conjunto.get(k).get(i) instanceof ExpNot)
						complementar = true;
					
					//Remove a expressao
					conjunto.get(k).remove(i);
					
					i = 0;//zera o index
					
				}else{//end if
					i += 1;
				}
						
			}// end while
			
		}//end for
		
		return complementar;
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
		
		printClausulas("Clausulas");
		
	}
	
	//Imprime uma dada expressao
	private void printExpressao(String titulo, Expressao exp){
		
		this.expressao = exp;
		System.out.println("----------------- " + titulo + " -----------------");
		System.out.println();
		System.out.println(this.expressao);
		System.out.println();
		
	}
	
	//Imprime todas as clausulas existentes
	private void printClausulas(String titulo){
		
		System.out.println("----------------- " + titulo + " -----------------");
		System.out.println();
		System.out.println(this.conjunto);
		System.out.println();
		
	}
	
	public boolean checaTipo() {
		return expressao.checaTipo();
 	}
	
	public Expressao getExpressao() {
		return expressao;
	}

}
