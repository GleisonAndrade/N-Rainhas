package br.edu.ufpi.ia.nrainhas.modelo;

import java.util.ArrayList;
import java.util.List;


public class Tabuleiro {
	public int[][] solucao;
	public int rainha;
	public Tabuleiro pai;

	public Tabuleiro(int[][] solucao, Tabuleiro pai, int rainha) {
		this.solucao = solucao;
		this.pai = pai;
		this.rainha = rainha;
	}
	
	public Tabuleiro(Tabuleiro clone) {
		this.rainha = clone.rainha;
		this.solucao = new int[clone.solucao.length][]; 
		for (int j = 0; j < clone.solucao.length; j++) {
			this.solucao[j] = clone.solucao[j].clone();
		}
		this.pai = clone.pai;
	}

	public Tabuleiro clone() {
		return new Tabuleiro(this);
	}

	public boolean ehSolucao(){
		if(this.rainha == solucao.length){
			return true;
		}
		return false;
	}
	
	public void resolver(int N) {
		if(colocaRainhas(0, N)){
			//print the result
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(" " + solucao[i][j]);
				}
				System.out.println();
			}
		}else{
			System.out.println("SEM SOLUÇÃO");
		}
	}

	public boolean colocaRainhas(int coluna, int N) {
		if(coluna==N){
			return true;
		}
		for (int linha = 0; linha < N; linha++) {
			if (podeColocar(solucao, linha, coluna)) {
				solucao[linha][coluna] = 1;
				if(colocaRainhas(coluna+1, N)){
					return true;
				}
				solucao[linha][coluna]=0;
			}
		}
		return false;

	}

	public List<Tabuleiro> expandeVizinhos(){
		List<Tabuleiro> vizinhos = new ArrayList<Tabuleiro>();
		for (int i = 0; i < solucao.length; i++) {
			int[][] tabuleiro = new int[this.solucao.length][]; 
			for (int j = 0; j < tabuleiro.length; j++) {
				tabuleiro[j] = this.solucao[j].clone();
			}
			
			if (podeColocar(tabuleiro, rainha, i)) {
				tabuleiro[rainha][i] = 1;
				vizinhos.add(new Tabuleiro(tabuleiro, this, this.rainha+1));
			}
		}
		return vizinhos;
	}
	
	public boolean podeColocar(int[][] tabuleiro, int linha, int coluna) {
		for (int i = 0; i < linha; i++) {
			if (tabuleiro[i][coluna] == 1) {
				return false;
			}
		}
		
		for (int i = linha, j = coluna; i >= 0 && j >= 0; i--, j--) {
			if (tabuleiro[i][j] == 1) {
				return false;
			}
		}

		for (int i = linha, j = coluna; i >= 0 && j < tabuleiro.length; i--, j++) {
			if (tabuleiro[i][j] == 1) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < solucao.length; i++) {
			for (int j = 0; j < solucao[i].length; j++) {
				sb.append(solucao[i][j] + " ");
				if(i == 0 && j == solucao.length-1){
					sb.append("rainha=" + rainha);
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
}