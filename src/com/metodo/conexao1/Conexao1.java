package com.metodo.conexao1;

import java.util.ArrayList;

import com.expressao.*;

public class Conexao1{

	private Expressao expressao;
	private ArrayList<Expressao> clausulas;
	private ArrayList<ArrayList<Expressao>> conjunto;
	
	public Conexao1(Expressao expressao){
		this.expressao = expressao;
		this.clausulas = new ArrayList<>();
		this.conjunto = new ArrayList<>();
	}

	public void executar() {
		
		printExpressao("Expressao", expressao);
		printExpressao("FND",expressao.avaliar(expressao));
		separarClausulas(expressao);
		/*
		int i = 0;
		int j = 0;
		Expressao test = null;
		
		while (i < conjunto.size()-1){
			
			j = 0;
			
			while (j < conjunto.get(i).size()){
				
				test = conjunto.get(i).get(j);
				
				//remove se o par complementar for encontrado
				if(encontrarComplementar(conjunto.get(i).get(j), i + 1)){
					
					conjunto.get(i).remove(j);
					j = 0;
					
				}else{
					j += 1;
				}
				
				printClausulas("Resolucao ( " + test + " )");
				
			}//end while
			
			i += 1;
			
		}//end while
		*/
		
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
			
			System.out.println("Cla: " + clausulas);
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
