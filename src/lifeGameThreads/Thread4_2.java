package lifeGameThreads;

public class Thread4_2 extends Thread {

	int vidaAtual[][];
	int m;

	public Thread4_2(int[][] vidaAtual, int m) {
		this.vidaAtual = vidaAtual;
		this.m = m;
	}

	public void run() {

		int[][] futuraGeracao = new int[vidaAtual.length][vidaAtual.length];

		for (int j = 0; j < vidaAtual.length; j++) {
			// SE FOR A ULTIMA LINHA
			if (j == vidaAtual.length - 1) {
				// Percorre as colunas
				for (int k = 0; k < vidaAtual.length; k++) {

					// SE FOR A PRIMEIRA COLUNA
					if (k == 0) {
						// Se o elemento estiver morto e três vizinhos
						if (vidaAtual[j][k] == 0
								&& ((vidaAtual[j - 1][k] + vidaAtual[j - 1][k + 1] + vidaAtual[j][k + 1]) == 3)) {
							futuraGeracao[j][k] = 1;
						} // Se o elemento estiver vivo, mais que 3 ou menos
							// que 2 vizinhos
						else if (vidaAtual[j][k] == 1
								&& ((vidaAtual[j - 1][k] + vidaAtual[j - 1][k + 1] + vidaAtual[j][k + 1]) > 3)

								|| ((vidaAtual[j - 1][k] + vidaAtual[j - 1][k + 1] + vidaAtual[j][k + 1]) < 2)) {
							futuraGeracao[j][k] = 0;
						} else {
							futuraGeracao[j][k] = vidaAtual[j][k];
						}
					}
					// SE NÃO FOR A PRIMEIRA COLUNA E NÃO FOR A ÚLTIMA
					else if (k > 0 && k < vidaAtual.length - 1) {
						// Se o elemento estiver morto e três vizinhos
						if (vidaAtual[j][k] == 0 && ((vidaAtual[j][k - 1] + vidaAtual[j - 1][k - 1]
								+ vidaAtual[j - 1][k] + vidaAtual[j - 1][k + 1] + vidaAtual[j][k + 1]) == 3)) {
							futuraGeracao[j][k] = 1;
						} // Se o elemento estiver vivo, mais que 3 ou menos
							// que 2 vizinhos
						else if (vidaAtual[j][k] == 1 && ((vidaAtual[j][k - 1] + vidaAtual[j - 1][k - 1]
								+ vidaAtual[j - 1][k] + vidaAtual[j - 1][k + 1] + vidaAtual[j][k + 1]) > 3)

								|| ((vidaAtual[j][k - 1] + vidaAtual[j - 1][k - 1] + vidaAtual[j - 1][k]
										+ vidaAtual[j - 1][k + 1] + vidaAtual[j][k + 1]) < 2)) {
							futuraGeracao[j][k] = 0;
						} else {
							futuraGeracao[j][k] = vidaAtual[j][k];
						}
					}
					// SE FOR A ÚLTIMA COLUNA
					else {
						// Se o elementos estiver morto e 3 vizinhos
						if (vidaAtual[j][k] == 0
								&& ((vidaAtual[j - 1][k] + vidaAtual[j - 1][k - 1] + vidaAtual[j][k - 1]) == 3)) {
							futuraGeracao[j][k] = 1;
						} // Se o elemento estiver vivo, mais que 3 ou menos
							// que 2 vizinhos
						else if (vidaAtual[j][k] == 1
								&& ((vidaAtual[j - 1][k] + vidaAtual[j - 1][k - 1] + vidaAtual[j][k - 1]) > 3)

								|| ((vidaAtual[j - 1][k] + vidaAtual[j - 1][k - 1] + vidaAtual[j][k - 1]) < 2)) {
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

		JogoDaVidaThreads.preencher4ThreadsParte2(futuraGeracao);
	}
}
