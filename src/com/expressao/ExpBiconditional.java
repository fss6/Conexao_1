package com.expressao;

import java.util.ArrayList;

import com.util.Tipo;
import com.util.TipoPrimitivo;

/**
 * Um objeto desta classe representa uma Expressao de Conjuncao logica.
 */
public class ExpBiconditional extends ExpBinaria{

	/**
	 * Controi uma Expressao de Conjuncao logica  com as sub-expressoes
	 * especificadas. Estas devem ser tais que sua avaliacao retorna
	 * <code>ValorBooleano</code>
	 *
	 * @param esq Expressao da esquerda
	 * @param dir Expressao da direita
	 */
	public ExpBiconditional(Expressao esq, Expressao dir) {
		super(esq, dir, "<->");//(P <-> Q)
	}


	/**
	 * Retorna o valor da Expressao de Conjuncao Logica
	 */
	 public Expressao avaliar(Expressao externa){ 
		
		Expressao esq = getEsq();
		Expressao dir = getDir();
		
		Expressao exp1 = new ExpAnd(esq, dir);//(P ^ Q)
		Expressao exp2 = new ExpAnd(new ExpNot(esq),new ExpNot(dir));//(~P ^ ~Q)
		Expressao expressao = new ExpOr(exp1.avaliar(this), exp2.avaliar(this));//(P ^ Q) v (~P ^ ~Q)
		
		return expressao;
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
		// TODO Auto-generated method stub
		return null;
	}

}
