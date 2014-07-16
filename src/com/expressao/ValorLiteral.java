package com.expressao;

import com.util.Tipo;
import com.util.TipoPrimitivo;

/**
 * Este valor primitivo encapsula um String.
 */
public class ValorLiteral extends ValorConcreto<String>{

 	public ValorLiteral(String valor) {
		super(valor);
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 *
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo() {
		return TipoPrimitivo.STRING;
	}

}
