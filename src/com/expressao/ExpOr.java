package com.expressao;

import java.util.ArrayList;

import com.util.Tipo;
import com.util.TipoPrimitivo;

/**
 * Um objeto desta classe representa uma Disjuncao Logica.
 */
public class ExpOr extends ExpBinaria {

	/**
	 * Controi uma Expressao de disjuncao logica  com as sub-expressoes
	 * especificadas.Estas devem ser tais que sua avaliacao resulta em
	 * <code>ValorBooleano</code>
	 *
	 * @param esq Expressao da esquerda
	 * @param dir Expressao da direita
	 */
	public ExpOr(Expressao esq, Expressao dir){
		super(esq, dir, "v");
	}


	/**
	 * Retorna o valor da Expressao de disjuncao logica
	 */
	public Expressao avaliar(Expressao externa){
		
		Expressao esq = getEsq();
		Expressao dir = getDir();
		
		if(externa instanceof ExpNot){
			esq = new ExpNot(esq);
			dir = new ExpNot(dir);
			return new ExpAnd(esq.avaliar(this),dir.avaliar(this));
		}else{
			return new ExpOr(esq,dir);
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
		
		
		ArrayList<ArrayList<Expressao>> list = new ArrayList<>();
		
		ArrayList<ArrayList<Expressao>> esq = getEsq().expressionToString();
		list.addAll(esq);
		
		ArrayList<ArrayList<Expressao>> dir = getDir().expressionToString();
		list.addAll(dir);
		
		return list;
	}

}
