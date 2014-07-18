package com.expressao;


import java.util.ArrayList;

import com.util.Tipo;
import com.util.TipoPrimitivo;

/**
 * Um objeto desta classe representa uma Expressao de Conjuncao logica.
 */
public class ExpAnd extends ExpBinaria{

	/**
	 * Controi uma Expressao de Conjuncao logica  com as sub-expressoes
	 * especificadas. Estas devem ser tais que sua avaliacao retorna
	 * <code>ValorBooleano</code>
	 *
	 * @param esq Expressao da esquerda
	 * @param dir Expressao da direita
	 */
	public ExpAnd(Expressao esq, Expressao dir) {
		super(esq, dir, "^");
	}


	/**
	 * Retorna o valor da Expressao de Conjuncao Logica
	 */
	 public Expressao avaliar(Expressao externa){
		 
		Expressao esq = getEsq();
		Expressao dir = getDir(); 
		 
		if(externa instanceof ExpNot){
			esq = new ExpNot(esq);
			dir = new ExpNot(dir);
			return new ExpOr(esq.avaliar(this),dir.avaliar(this));
		}else{
			return new ExpAnd(esq.avaliar(this),dir.avaliar(this)); 
		}
		
		
	}
	 
	/**
	 * Realiza a verificacao de tipos desta expressao.
	 *
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *          <code>false</code> caso contrario.
	 */
	protected boolean checaTipoElementoTerminal() {
		return (getEsq().getTipo().eString() && getDir().getTipo().eString());
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 *
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo() {
		return TipoPrimitivo.STRING;
	}
	

	@Override
	public ArrayList<ArrayList<Expressao>> expressionToString() {
		
		ArrayList<Expressao> clausula = new ArrayList<>();
		
		ArrayList<ArrayList<Expressao>> result = getEsq().expressionToString();
		
		for (ArrayList<Expressao> e : result) {
			clausula.addAll(e);
		}
		
		result = getDir().expressionToString();
		
		for (ArrayList<Expressao> d : result) {
			clausula.addAll(d);
		}
		
		ArrayList<ArrayList<Expressao>> list = new ArrayList<>();
		list.add(clausula);
		
		return list;
		
	}

}
