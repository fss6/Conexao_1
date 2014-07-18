package com.metodo.conexao1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.expressao.ExpNot;
import com.expressao.ExpUnaria;
import com.expressao.Expressao;
import com.expressao.ValorLiteral;
import com.util.ParSemComplementarException;
import com.util.Print;

public class Grafo{
	
	private HashMap<Expressao, ArrayList<Expressao>> vertices;
	
	public Grafo(){
		this.vertices = new HashMap<>();
	}
	
	public void explorarGrafo(Expressao startVertice, ArrayList<Expressao> path) 
			throws ParSemComplementarException{
		
		if(path == null)
			path = new ArrayList<>();
		
		path.add(startVertice);
		
		for (Expressao filho : vertices.get(startVertice)) {

			
			if( vertices.get(filho).size() > 0){
				explorarGrafo(filho, path);
			}else{
				path.add(filho);
				Print.objeto("Path", path);
				
				if(!existeComplementar(path))
					throw new ParSemComplementarException("Path sem complementar");
				
				
				path.remove(path.size()-1);
				Print.linha();
			}
			
		}
		
		path.remove(path.size()-1);
		
	}
	
	private boolean existeComplementar(ArrayList<Expressao> path){
		
		boolean result = false;
		ValorLiteral v1 = null;
		ValorLiteral v2 = null;
		
		int i = 0;
		
		while( i < path.size() -1){
			
			//Verifica expressoes ExpNot
			if ( path.get(i) instanceof ExpNot)
				v1 = (ValorLiteral) ((ExpUnaria) path.get(i)).getExp();
			else
				v1 = (ValorLiteral) path.get(i);
			
			
			for( int j = i+1 ; j < path.size() ; j++){
				
				//Verifica se e uma expressao ExpNot
				if (path.get(j) instanceof ExpNot)
					v2 = (ValorLiteral) ((ExpUnaria) path.get(j)).getExp();
				else
					v2 = (ValorLiteral) path.get(j);
				
				//Se o valor das expressoes sao igual
				if(v1.valor().equals(v2.valor())){
					
					//complementar recebe true se
					if (path.get(i) instanceof ExpNot && path.get(j) instanceof ValorLiteral
							|| path.get(i) instanceof ValorLiteral && path.get(j) instanceof ExpNot){
						result = true;
						System.out.println("Par complementar: (" + path.get(i) + ", " + path.get(j) + ")");
						j = path.size();
						i = path.size();
					}
					
				}
				
			}
			
			i += 1;
			
		}
		
		return result;
	}
	
    public Set<Expressao> getVertices(){	
		return this.vertices.keySet();
	}
    
    public List<Aresta> getArestas(){
    	
    	return gerarArestas();
    	
    }
    
    private List<Aresta> gerarArestas(){
    	
    	List<Aresta> result = new ArrayList<Aresta>();
    	
    	for (Expressao vertice : this.vertices.keySet()) {
    		for (Expressao vizinho : this.vertices.get(vertice)) {
				result.add(new Aresta(vertice, vizinho));
			}
		}
    	
    	return result;
    }
    
	public void addVertice(Expressao vertice){
		
		if(!this.vertices.containsKey(vertice)){
			ArrayList<Expressao> arestas = new ArrayList<>();
			this.vertices.put(vertice, arestas);
		}
		
	}
	
	public void addAresta(Aresta aresta){
		
		Expressao vertice = aresta.getVertice();
		Expressao vizinho = aresta.getVizinho();
		
		this.vertices.get(vertice).add(vizinho);
		
	}
	
	
}
