package com.expressao;

import java.util.ArrayList;

import com.expressao.Valor;

/**
 * @author bldb, efas, jcbr, srmq
 *
 * This class groups different types of ValorConcreto.
 */
public abstract class ValorConcreto<T> implements Valor {
	
	
	private T valor;

	/**
	 * Retorna texto representando o valor string do objeto desta classe.
	 */
	public String toString() {
		return String.valueOf(valor);
	}

	/**
	 * cria um objeto encapsulando o String fornecido
	 */
	public ValorConcreto( T valor) {
		this.valor = valor;
	}


	/**
	 * Retorna o string encapsulado pelo objeto desta classe
	 */
	public T valor(){
		return valor;
	}

	
	/**
	 * Determina igualdade entre  objetos desta classe\
	 * 
	 * 
	 */
	public boolean isEquals(ValorConcreto<T> obj) {
		return valor().equals(obj.valor());
		
		
	}
	
	/**
	 * Retorna o valor deste valor primitivo, i.e., ele mesmo.
	 */
	public Valor avaliar (Expressao externa) {
		return this;
	}

	

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * Ser&aacute; sempre v&aacute;lida.
	 *
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *          <code>false</code> caso contrario.
	 */
	public boolean checaTipo() {
		return true;
	}
	
	public ArrayList<ArrayList <Expressao>> expressionToString() {
		
		ArrayList<ArrayList <Expressao>> list = new ArrayList<>();
		ArrayList <Expressao> clausula = new ArrayList<>();
		
		clausula.add(this);
		list.add(clausula);
		
		return list;
	}

}
