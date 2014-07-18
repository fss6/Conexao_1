package com.metodo.conexao1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.expressao.Expressao;

public class Grafo{
	
	private HashMap<Expressao, ArrayList<Expressao>> vertices;
	
	public Grafo(){
		this.vertices = new HashMap<>();
	}
	
	
	public ArrayList<Expressao> explorarGrafo(Expressao startVertice, ArrayList<Expressao> path){
		
		if(path == null)
			path = new ArrayList<>();
			
		path.add(startVertice);
		
		for (Expressao filho : vertices.get(startVertice)) {
			
			if( vertices.get(filho).size() > 0){
				explorarGrafo(filho, path);
			}else{
				path.add(filho);
				System.out.println(path);
				path.remove(path.size()-1);
			}
			
		}
		
		path.remove(path.size()-1);
		
		return path;
		
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
