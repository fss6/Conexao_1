package com.metodo.conexao1;

import com.expressao.*;
import com.metodo.conexao1.Conexao1;

public class Programa{

	private Expressao expressao;
	
	public Programa(Expressao expressao){
		this.expressao = expressao;
	}

	public Expressao executar() {
		
		Conexao1 c = new Conexao1(expressao);
		c.executar();
		
		return  expressao;		
 	}
	
	
	public boolean checaTipo() {
		return expressao.checaTipo();
 	}
	
	public Expressao getExpressao() {
		return expressao;
	}

}
