package lifeGameThreads;

public class Thread4_1 extends Thread {
	int[][] vidaAtual;
	int m;

	public Thread4_1(int[][] vidaAtual, int m) {
		this.m = m;
		this.vidaAtual = vidaAtual;
	}

	public void run() {

		int[][] futuraGeracao = new int[vidaAtual.length][vidaAtual.length];

		// Esse laço passa por cada linha
		for (int j = 0; j < vidaAtual.length; j++) {

			// SE FOR A PRIMEIRA LINHA
			if (j == 0) {
				// Esse laço passa por cada coluna da linha atual
				for (int k = 0; k < vidaAtual.length; k++) {

					// PRIMEIRO ELEMENTO PRIMEIRA LINHA
					if (k == 0) {
						// Se o elemento estiver morto e tiver três vizinhos
						if (vidaAtual[j][k] == 0
								&& ((vidaAtual[j][k + 1] + vidaAtual[j + 1][k] + vidaAtual[j + 1][k + 1]) == 3)) {
							futuraGeracao[j][k] = 1;
						}
						// Se o elemento estiver vivo e menos do que dois
						// vizinhos
						else if (vidaAtual[j][k] == 1
								&& ((vidaAtual[j][k + 1] + vidaAtual[j + 1][k] + vidaAtual[j + 1][k + 1]) < 2)) {
							futuraGeracao[j][k] = 0;
						} else {
							futuraGeracao[j][k] = vidaAtual[j][k];
						}
					}
					// ELEMENTOS DA PRIMEIRA LINHA, APÓS A 1ª COLUNA A ANTES
					// DA ÚLTIMA
					else if (k > 0 && k < vidaAtual.length - 1) {
						// Se o elemento estiver morto e três vizinhos
						if (vidaAtual[j][k] == 0 && ((vidaAtual[j][k - 1] + vidaAtual[j + 1][k - 1]
								+ vidaAtual[j + 1][k] + vidaAtual[j + 1][k + 1] + vidaAtual[j][k + 1]) == 3)) {
							futuraGeracao[j][k] = 1;
						}
						// Vivo e mais do que 3 vizinhos, morte por
						// superpopulação, ou menos do que 2 vizinhos
						// solidão
						else if (vidaAtual[j][k] == 1 && ((vidaAtual[j][k - 1] + vidaAtual[j + 1][k - 1]
								+ vidaAtual[j + 1][k] + vidaAtual[j + 1][k + 1] + vidaAtual[j][k + 1]) > 3)

								|| ((vidaAtual[j][k - 1] + vidaAtual[j + 1][k - 1] + vidaAtual[j + 1][k]
										+ vidaAtual[j + 1][k + 1] + vidaAtual[j][k + 1]) < 2)) {
							futuraGeracao[j][k] = 0;
						} else {
							futuraGeracao[j][k] = vidaAtual[j][k];
						}
					}

					// ÚLTIMO ELEMENTO, PRIMEIRA LINHA
					else if (k == vidaAtual.length - 1) {
						// Se o elemento estiver morto
						if (vidaAtual[j][k] == 0
								&& ((vidaAtual[j][k - 1] + vidaAtual[j + 1][k - 1] + vidaAtual[j + 1][k]) == 3)) {
							futuraGeracao[j][k] = 1;
						}
						// Se o elemento estiver vivo e superpopulação ou
						// solidão
						else if (vidaAtual[j][k] == 1
								&& ((vidaAtual[j][k - 1] + vidaAtual[j + 1][k - 1] + vidaAtual[j + 1][k]) > 3
										|| (vidaAtual[j][k - 1] + vidaAtual[j + 1][k - 1] + vidaAtual[j + 1][k]) < 2)) {
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

		JogoDaVidaThreads.preencher4ThreadsParte1(futuraGeracao);

	}
}
