package com.util;

import com.expressao.Expressao;

public class Print {

	public static void texto(String texto){
		System.out.println();
		System.out.println("---( "+ texto +" )---");
		System.out.println();
	}
	
	public static void linha(){
		System.out.println("------------------------------------");
	}
	
	public static void expressao(String texto, Expressao exp){
		System.out.println("---( " + texto + " )---");
		System.out.println();
		System.out.println(exp);
		System.out.println();

	}
	
	public static void objeto(String texto, Object obj){
		System.out.println( texto + ": "+ obj.toString());
	}

}
