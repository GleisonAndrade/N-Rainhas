package br.edu.ufpi.ia.nrainhas.busca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.edu.ufpi.ia.nrainhas.modelo.Tabuleiro;

public class Busca {
	private Tabuleiro tabuleiroInicial;
	private List<Tabuleiro> solucao;
	public int visitados;
	public int maximoDeFilhosGerados;
	public int totalDeFilhosGerados;
	public int profundidade;

	
	public Busca(Tabuleiro inicial) {
		this.tabuleiroInicial = inicial;
		this.solucao = new ArrayList<Tabuleiro>();
		this.visitados = 0;
		this.maximoDeFilhosGerados = 0;
		this.totalDeFilhosGerados = 0;
		this.profundidade = 0;
	}
	
	public List<Tabuleiro> gulosa(){
		List<Tabuleiro> tabuleirosGerados = new ArrayList<Tabuleiro>();
		Tabuleiro atual = tabuleiroInicial.clone();
		this.visitados = 0;
		this.maximoDeFilhosGerados = 0;
		this.totalDeFilhosGerados = 0;
		this.profundidade = 0;
		
		while(!atual.ehSolucao()){
			int filhos = 0;
			for (Tabuleiro vizinho : atual.expandeVizinhos()) {
				if (!tabuleirosGerados.contains(vizinho)) {
					tabuleirosGerados.add(vizinho);
					filhos++;
				}
			}
			
			ordenarGuloso(tabuleirosGerados);
			
			atual = tabuleirosGerados.get(0).clone();
			tabuleirosGerados.remove(0);
			
			totalDeFilhosGerados += filhos;
			visitados++;
			
			if (filhos > maximoDeFilhosGerados) {
				maximoDeFilhosGerados = filhos;
			}
		}
		
		if(atual.ehSolucao()){
			while(atual.pai != null){
				this.solucao.add(atual);
				atual = atual.pai;
			}
			solucao.add(tabuleiroInicial);
			profundidade = solucao.size();
			return solucao;
		}
		return null;
	}
	
	public List<Tabuleiro> heuristica(){
		List<Tabuleiro> front = new ArrayList<Tabuleiro>();
		Tabuleiro atual = tabuleiroInicial.clone();
		this.visitados = 0;
		this.maximoDeFilhosGerados = 0;
		this.totalDeFilhosGerados = 0;
		this.profundidade = 0;
		
		while(!atual.ehSolucao()){
			int filhos = 0;
			for (Tabuleiro vizinho : atual.expandeVizinhos()) {
				if (!front.contains(vizinho)) {
					front.add(vizinho);
					filhos++;
				}
			}
			
			ordena(front);
			
			atual = front.get(0).clone();
			front.remove(0);
			totalDeFilhosGerados += filhos;
			visitados++;
			
			if (filhos > maximoDeFilhosGerados) {
				maximoDeFilhosGerados = filhos;
			}
		}
		
		if(atual.ehSolucao()){
			while(atual.pai != null){
				this.solucao.add(atual);
				atual = atual.pai;
			}
			solucao.add(tabuleiroInicial);
			profundidade = solucao.size();
			return solucao;
		}
		return null;
	}
	
	public List<Tabuleiro> largura(){
		List<Tabuleiro> front = new ArrayList<Tabuleiro>();
		Tabuleiro atual = tabuleiroInicial.clone();
		this.visitados = 0;
		this.maximoDeFilhosGerados = 0;
		this.totalDeFilhosGerados = 0;
		this.profundidade = 0;
		
		while(!atual.ehSolucao()){
			int filhos = 0;
			for (Tabuleiro vizinhos : atual.expandeVizinhos()) {
				if (!front.contains(vizinhos)) {
					front.add(vizinhos);
					filhos++;
				}
			}
			atual = front.get(0).clone();
			front.remove(0);
			totalDeFilhosGerados += filhos;
			visitados++;
			
			if (filhos > maximoDeFilhosGerados) {
				maximoDeFilhosGerados = filhos;
			}
		}
		
		if(atual.ehSolucao()){
			while(atual.pai != null){
				this.solucao.add(atual);
				atual = atual.pai;
			}
			solucao.add(tabuleiroInicial);
			profundidade = solucao.size();
			return solucao;
		}
		return null;
	}
	
	public List<Tabuleiro> profundidade(){
		List<Tabuleiro> front = new ArrayList<Tabuleiro>();
		Tabuleiro atual = tabuleiroInicial.clone();
		this.visitados = 0;
		this.maximoDeFilhosGerados = 0;
		this.totalDeFilhosGerados = 0;
		this.profundidade = 0;
		
		while(!atual.ehSolucao()){
			int filhos = 0;
			for (Tabuleiro vizinhos : atual.expandeVizinhos()) {
				if (!front.contains(vizinhos)) {
					front.add(vizinhos);
					filhos++;
				}
			}
			
			atual = front.get(front.size()-1).clone();
			front.remove(front.size()-1);
			totalDeFilhosGerados += filhos;
			visitados++;
			
			if (filhos > maximoDeFilhosGerados) {
				maximoDeFilhosGerados = filhos;
			}
		}
		
		if(atual.ehSolucao()){
			while(atual.pai != null){
				this.solucao.add(atual);
				atual = atual.pai;
			}
			solucao.add(tabuleiroInicial);
			profundidade = solucao.size();
			return solucao;
		}
		return null;
	}
	public void ordena(List<Tabuleiro> boards){
		Collections.sort(boards, new Comparator<Tabuleiro>() {
			@Override
			public int compare(Tabuleiro o1, Tabuleiro o2) {
				if(Integer.valueOf(o2.rainha).compareTo(Integer.valueOf(o1.rainha)) == 0){
					Integer size1 = o1.expandeVizinhos().size();
					Integer size2 = o2.expandeVizinhos().size();
					return size1.compareTo(size2); 
				}else{
					return Integer.valueOf(o2.rainha).compareTo(Integer.valueOf(o1.rainha));
				}
			}
		});
	}
	
	public void ordenarGuloso(List<Tabuleiro> boards){
		Collections.sort(boards, new Comparator<Tabuleiro>() {
			@Override
			public int compare(Tabuleiro o1, Tabuleiro o2) {
				Integer size1 = o1.expandeVizinhos().size();
				Integer size2 = o2.expandeVizinhos().size();
				return size1.compareTo(size2); 
			}
		});
	}
	
	public List<Tabuleiro> getSolucao(){
		return this.solucao;
	}
	
	public Tabuleiro getTabuleiroInicial() {
		return tabuleiroInicial;
	}
}
