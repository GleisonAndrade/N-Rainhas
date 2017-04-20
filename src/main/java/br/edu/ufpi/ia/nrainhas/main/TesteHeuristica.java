package br.edu.ufpi.ia.nrainhas.main;

import java.util.List;

import br.edu.ufpi.ia.nrainhas.busca.Busca;
import br.edu.ufpi.ia.nrainhas.modelo.Tabuleiro;

public class TesteHeuristica {

	public static void main(String[] args) {
		int[][] tabuleiroVazio = {{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}};
		
		Tabuleiro tabuleiroInicial = new Tabuleiro(tabuleiroVazio, null, 0);
		
		Busca busca = new Busca(tabuleiroInicial);
		List<Tabuleiro> solucoes = busca.largura();
		long somaTempo = 0;
		int somaVis = 0;
		int somaMaxFilho= 0;
		int somaTotalFIlhos = 0;
		int somaProf = 0;
		
		for (int i = 0; i < 10; i++) {
			busca = new Busca(tabuleiroInicial);
			long inicio = System.currentTimeMillis();
			solucoes = busca.heuristica();
			long fim = System.currentTimeMillis();
			somaTempo += fim - inicio;
			
			somaVis += busca.visitados;
			somaMaxFilho += busca.maximoDeFilhosGerados;
			somaTotalFIlhos += busca.totalDeFilhosGerados;
			somaProf += busca.profundidade;
		}
		
		System.out.println("Tempo médio de Execução: " + (somaTempo / 10)+ " milisegundos");
		System.out.println("Quantidade de visitas: " + (somaVis / 10));
		System.out.println("Quantidade máximo de filhos gerados: " + (somaMaxFilho / 10));
		System.out.println("Quantdade de filhos gerados: " + (somaTotalFIlhos / 10));
		System.out.println("Profundidade da solução: " + (somaProf / 10));
	    
		for (int i = solucoes.size() - 1; i >= 0; i--) {
			System.out.println(solucoes.get(i).toString());
		}

	}

}
