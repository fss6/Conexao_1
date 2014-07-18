package com.metodo.conexao1;

import com.expressao.*;
import com.metodo.conexao1.Conexao1;
import com.util.ParSemComplementarException;
import com.util.Print;

public class Programa{

	private Expressao expressao;
	
	public Programa(Expressao expressao){
		this.expressao = expressao;
	}

	public Expressao executar() {
		
		try {
			Conexao1 c = new Conexao1(expressao);
			c.executar();
		} catch (ParSemComplementarException e) {
			System.out.println(e.getMessage());
			Print.linha();
		}finally{
			Print.texto("Fim da execucao");
		}
		
		return  expressao;		
 	}
	
	
	public boolean checaTipo() {
		return expressao.checaTipo();
 	}
	
	public Expressao getExpressao() {
		return expressao;
	}

}
