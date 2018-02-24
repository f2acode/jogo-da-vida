package lifeGameThreads;

public class Thread4_3 extends Thread {

	int[][] vidaAtual;
	int m;

	public Thread4_3(int[][] vidaAtual, int m) {
		this.vidaAtual = vidaAtual;
		this.m = m;
	}

	public void run() {

		int[][] futuraGeracao = new int[vidaAtual.length][vidaAtual.length];

			// Esse laço passa por cada linha até antes da metade
			for (int j = 0; j < (vidaAtual.length/2); j++) {
				// SE NÃO FOR A PRIMEIRA E NÃO FOR A ÚLTIMA LINHA
				if (j > 0 && j < vidaAtual.length - 1) {
					// Percorre todas as colunas
					for (int k = 0; k < vidaAtual.length; k++) {
						// SE FOR A PRIMEIRA COLUNA
						if (k == 0) {
							// Se o elemento estiver morto e tiver três vizinhos
							if (vidaAtual[j][k] == 0 && ((vidaAtual[j - 1][k] + vidaAtual[j - 1][k + 1]
									+ vidaAtual[j][k + 1] + vidaAtual[j + 1][k + 1] + vidaAtual[j + 1][k]) == 3)) {
								futuraGeracao[j][k] = 1;
							} // Se o elemento estiver vivo e mais do que 3
								// vizinhos ou menos que 2
							else if (vidaAtual[j][k] == 1
									&& ((vidaAtual[j - 1][k] + vidaAtual[j - 1][k + 1] + vidaAtual[j][k + 1]
											+ vidaAtual[j + 1][k + 1] + vidaAtual[j + 1][k]) > 3)
									|| ((vidaAtual[j - 1][k] + vidaAtual[j - 1][k + 1] + vidaAtual[j][k + 1]
											+ vidaAtual[j + 1][k + 1] + vidaAtual[j + 1][k]) < 2)) {
								futuraGeracao[j][k] = 0;
							} else {
								futuraGeracao[j][k] = vidaAtual[j][k];
							}
						} // SE NÃO FOR A PRIMEIRA COLUNA E NÃO FOR A ÚLTIMA
						else if (k > 0 && k < vidaAtual.length - 1) {
							// Se o elemento estiver morto e tiver mais três
							// vizinhos
							if (vidaAtual[j][k] == 0 && ((vidaAtual[j][k - 1] + vidaAtual[j - 1][k - 1]
									+ vidaAtual[j - 1][k] + vidaAtual[j - 1][k + 1] + vidaAtual[j][k + 1]
									+ vidaAtual[j + 1][k + 1] + vidaAtual[j + 1][k] + vidaAtual[j + 1][k - 1]) == 3)) {
								futuraGeracao[j][k] = 1;
							}
							// Se o elemento estiver vivo e tiver menos do que 2
							// vizinhos ou mais do que 3
							else if (vidaAtual[j][k] == 1 &&

									((vidaAtual[j][k - 1] + vidaAtual[j - 1][k - 1] + vidaAtual[j - 1][k]
											+ vidaAtual[j - 1][k + 1] + vidaAtual[j][k + 1] + vidaAtual[j + 1][k + 1]
											+ vidaAtual[j + 1][k] + vidaAtual[j + 1][k - 1]) < 2)

									|| ((vidaAtual[j][k - 1] + vidaAtual[j - 1][k - 1] + vidaAtual[j - 1][k]
											+ vidaAtual[j - 1][k + 1] + vidaAtual[j][k + 1] + vidaAtual[j + 1][k + 1]
											+ vidaAtual[j + 1][k] + vidaAtual[j + 1][k - 1]) > 3)) {
								futuraGeracao[j][k] = 0;
							} else {
								futuraGeracao[j][k] = vidaAtual[j][k];
							}
						}
						// SE FOR A ÚLTIMA COLUNA
						else if (k == vidaAtual.length - 1) {
							// Se o elemento estiver morto e três vizinhos
							if (vidaAtual[j][k] == 0 && ((vidaAtual[j - 1][k] + vidaAtual[j - 1][k - 1]
									+ vidaAtual[j][k - 1] + vidaAtual[j + 1][k - 1] + vidaAtual[j + 1][k]) == 3)) {
								futuraGeracao[j][k] = 1;
							} // Se o elemento estiver vivo, mais que 3 ou menos
								// que 2 vizinhos
							else if (vidaAtual[j][k] == 1 && ((vidaAtual[j - 1][k] + vidaAtual[j - 1][k - 1]
									+ vidaAtual[j][k - 1] + vidaAtual[j + 1][k - 1] + vidaAtual[j + 1][k]) > 3)

									|| ((vidaAtual[j - 1][k] + vidaAtual[j - 1][k - 1] + vidaAtual[j][k - 1]
											+ vidaAtual[j + 1][k - 1] + vidaAtual[j + 1][k]) < 2)) {
								futuraGeracao[j][k] = 0;
							} else {
								futuraGeracao[j][k] = vidaAtual[j][k];
							}
						}
					}
				}
			}
		
		// Transfere o conteúdo da geração como atual para a outra futura
				// geração
				
				JogoDaVidaThreads.preencher4ThreadsParte3(futuraGeracao);
	}
}
