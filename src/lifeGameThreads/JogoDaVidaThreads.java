package lifeGameThreads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class JogoDaVidaThreads extends IOException {
	public static int[][] vidaAtual;

	public static int[][] vidaAtualT1_2;
	public static int[][] vidaAtualT2_2;

	public static int[][] vidaAtualT4_1;
	public static int[][] vidaAtualT4_2;
	public static int[][] vidaAtualT4_3;
	public static int[][] vidaAtualT4_4;

	public static void main(String[] args) throws IOException, InterruptedException {

		Scanner entrada = new Scanner(System.in);
		System.out.println("Informe o nome do arquivo (Ex: teste.txt):");
		String nomeDoArquivo = entrada.nextLine();
		System.out.println("Informe a quantidade de gerações que deseja percorrer:");
		int m = entrada.nextInt();
		File arquivoTxt = new File(nomeDoArquivo);
		vidaAtual = criarMatriz(arquivoTxt);

		vidaAtualT1_2 = new int[vidaAtual.length][vidaAtual.length];
		vidaAtualT2_2 = new int[vidaAtual.length][vidaAtual.length];

		vidaAtualT4_1 = new int[vidaAtual.length][vidaAtual.length];
		vidaAtualT4_2 = new int[vidaAtual.length][vidaAtual.length];
		vidaAtualT4_3 = new int[vidaAtual.length][vidaAtual.length];
		vidaAtualT4_4 = new int[vidaAtual.length][vidaAtual.length];

		System.out.println("Informe a forma como deseja executar:\n" + "1 - Sequencial\n" + "2 - Com 2 threads\n"
				+ "3 - Com 4 threads");
		int opcao = entrada.nextInt();
		long tempoInicial = 0;
		long tempoFinal = 0;
		long tempoGasto = 0;

		Thread parte1;
		Thread parte2;
		Thread parte3;
		Thread parte4;

		switch (opcao) {
		case 1:
			tempoInicial = System.currentTimeMillis();
			sequencial(vidaAtual, m);
			tempoFinal = System.currentTimeMillis();
			tempoGasto = (tempoFinal - tempoInicial);
			break;
		case 2:
			tempoInicial = System.currentTimeMillis();
			for (int i = 0; i < m; i++) {
				parte1 = new Thread1_2(vidaAtual);
				parte2 = new Thread2_2(vidaAtual);
				parte1.start();
				parte2.start();
				parte1.join();
				parte2.join();
				preencher2Threads(vidaAtualT1_2, vidaAtualT2_2);
			}
			tempoFinal = System.currentTimeMillis();
			tempoGasto = (tempoFinal - tempoInicial);
			break;
		case 3:
			tempoInicial = System.currentTimeMillis();
			for (int i = 0; i < m; i++) {
				parte1 = new Thread4_1(vidaAtual, m);
				parte2 = new Thread4_2(vidaAtual, m);
				parte3 = new Thread4_3(vidaAtual, m);
				parte4 = new Thread4_4(vidaAtual, m);

				parte1.start();
				parte2.start();
				parte3.start();
				parte4.start();

				parte1.join();
				parte2.join();
				parte3.join();
				parte4.join();
				preencher4Threads(vidaAtualT4_1, vidaAtualT4_2, vidaAtualT4_3, vidaAtualT4_4);
			}
			tempoFinal = System.currentTimeMillis();
			tempoGasto = (tempoFinal - tempoInicial);
			break;
		}

		System.out.println("\nVida após gerações:");
		lerMatriz(vidaAtual);
		System.out.println("\nTempo gasto em milisegundos: " + tempoGasto);
		System.out.println("Tempo gasto em segundos: " + (tempoGasto / 1000));
		System.out.println("\nArquivo criado na pasta do projeto.");
		criarArquivo(vidaAtual);
		entrada.close();
	}

	public static void sequencial(int[][] vidaAtual, int m) throws IOException {

		int[][] futuraGeracao = new int[vidaAtual.length][vidaAtual.length];
		// Esse laço passa o número de gerações solicitado
		for (int i = 0; i < m; i++) {
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
							else if (vidaAtual[j][k] == 1 && ((vidaAtual[j][k - 1] + vidaAtual[j + 1][k - 1]
									+ vidaAtual[j + 1][k]) > 3
									|| (vidaAtual[j][k - 1] + vidaAtual[j + 1][k - 1] + vidaAtual[j + 1][k]) < 2)) {
								futuraGeracao[j][k] = 0;
							} else {
								futuraGeracao[j][k] = vidaAtual[j][k];
							}
						}
					}

					// SE NÃO FOR A PRIMEIRA LINHA E NÃO FOR A ÚLTIMA
				} else if (j > 0 && j < vidaAtual.length - 1) {
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
				} // SE FOR A ÚLTIMA LINHA
				else if (j == vidaAtual.length - 1) {
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
			for (int j = 0; j < futuraGeracao.length; j++) {
				for (int k = 0; k < futuraGeracao.length; k++) {
					vidaAtual[j][k] = futuraGeracao[j][k];
				}
			}
		}
	}

	public static void preencher2ThreadsParte1(int[][] futuraGeracao) {
		for (int k = 0; k < futuraGeracao.length; k++) {
			vidaAtualT1_2[0][k] = futuraGeracao[0][k];
		}

		for (int k = 0; k < futuraGeracao.length; k++) {
			vidaAtualT1_2[vidaAtual.length - 1][k] = futuraGeracao[vidaAtualT1_2.length - 1][k];
		}
	}

	public static void preencher2ThreadsParte2(int[][] futuraGeracao) {
		for (int j = 1; j < futuraGeracao.length - 1; j++) {
			for (int k = 0; k < futuraGeracao.length; k++) {
				vidaAtualT2_2[j][k] = futuraGeracao[j][k];
			}
		}
	}

	public static void preencher2Threads(int[][] vidaAtualT1_2, int[][] vidaAtualT2_2) {
		for (int k = 0; k < vidaAtualT1_2.length; k++) {
			vidaAtual[0][k] = vidaAtualT1_2[0][k];
		}

		for (int k = 0; k < vidaAtualT1_2.length; k++) {
			vidaAtual[vidaAtual.length - 1][k] = vidaAtualT1_2[vidaAtualT1_2.length - 1][k];
		}

		for (int j = 1; j < vidaAtualT2_2.length - 1; j++) {
			for (int k = 0; k < vidaAtualT2_2.length; k++) {
				vidaAtual[j][k] = vidaAtualT2_2[j][k];
			}
		}
	}

	public static void preencher4ThreadsParte1(int[][] futuraGeracao) {
		for (int k = 0; k < futuraGeracao.length; k++) {
			vidaAtualT4_1[0][k] = futuraGeracao[0][k];
		}
	}

	public static void preencher4ThreadsParte2(int[][] futuraGeracao) {
		for (int k = 0; k < futuraGeracao.length; k++) {
			vidaAtualT4_2[vidaAtualT4_2.length - 1][k] = futuraGeracao[vidaAtualT4_2.length - 1][k];
		}
	}

	public static void preencher4ThreadsParte3(int[][] futuraGeracao) {
		for (int j = 1; j < (futuraGeracao.length / 2); j++) {
			for (int k = 0; k < futuraGeracao.length; k++) {
				vidaAtualT4_3[j][k] = futuraGeracao[j][k];
			}
		}
	}

	public static void preencher4ThreadsParte4(int[][] futuraGeracao) {
		for (int j = (futuraGeracao.length / 2); j < futuraGeracao.length - 1; j++) {
			for (int k = 0; k < futuraGeracao.length; k++) {
				vidaAtualT4_4[j][k] = futuraGeracao[j][k];
			}
		}
	}

	public static void preencher4Threads(int[][] vidaAtualT4_1, int[][] vidaAtualT4_2, int[][] vidaAtualT4_3,
			int[][] vidaAtualT4_4) {
		for (int k = 0; k < vidaAtualT4_1.length; k++) {
			vidaAtual[0][k] = vidaAtualT4_1[0][k];
		}

		for (int k = 0; k < vidaAtualT4_2.length; k++) {
			vidaAtual[vidaAtual.length - 1][k] = vidaAtualT4_2[vidaAtual.length - 1][k];
		}

		for (int j = 1; j < (vidaAtualT4_3.length / 2); j++) {
			for (int k = 0; k < vidaAtualT4_3.length; k++) {
				vidaAtual[j][k] = vidaAtualT4_3[j][k];
			}
		}

		for (int j = (vidaAtualT4_4.length / 2); j < vidaAtualT4_4.length - 1; j++) {
			for (int k = 0; k < vidaAtualT4_4.length; k++) {
				vidaAtual[j][k] = vidaAtualT4_4[j][k];
			}
		}
	}

	public static int[][] criarMatriz(File file) throws IOException {

		FileInputStream stream = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);

		String data = null;
		int numLinhas = Integer.valueOf(br.readLine());
		int vidaAtual[][] = new int[numLinhas][numLinhas];
		boolean invalido = false;
		for (int i = 0; i < numLinhas; i++) {
			for (int j = 0; j < numLinhas; j++) {
				String texto = "";
				do{
					texto = String.valueOf(br.read());
					if (texto.equalsIgnoreCase("48") || texto.equalsIgnoreCase("49")) {
						vidaAtual[i][j] = Integer.valueOf(texto)/49;
						invalido = false;
					}else{
						invalido = true;
					}
				}while(invalido);
			}
		}
		br.close();
		return vidaAtual;
	}

	public static void lerMatriz(int[][] vidaAtual) throws IOException {

		for (int i = 0; i < vidaAtual.length; i++) {
			for (int j = 0; j < vidaAtual.length; j++) {
				System.out.print(vidaAtual[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public static File criarArquivo(int vidaAtual[][]) throws IOException {

		File file = new File("lifeGameSaida.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));

		for (int i = 0; i < vidaAtual.length; i++) {
			for (int j = 0; j < vidaAtual.length; j++) {
				writer.write(String.valueOf(vidaAtual[i][j]));
			}
			writer.write("\r\n");
		}
		writer.flush();
		writer.close();
		return file;
	}
}
