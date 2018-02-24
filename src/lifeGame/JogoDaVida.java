package lifeGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class JogoDaVida {

	public static void main (String [] args) throws IOException{
		
			Scanner entrada = new Scanner(System.in);
		    System.out.println("Informe o nome do arquivo (Ex: teste.txt):");
		    String nomeDoArquivo = entrada.nextLine();
		    System.out.println("Informe a quantidade de gerações que deseja percorrer:");
		    int m = entrada.nextInt();
			File arquivoTxt = new File(nomeDoArquivo);
			int [][] vidaAtual = criarMatriz(arquivoTxt);
			passarGeracoes(vidaAtual, m);
			System.out.println("\nVida após gerações:");
			lerMatriz(vidaAtual);
			System.out.println("\nArquivo criado na pasta do projeto.");
			criarArquivo(vidaAtual);
			entrada.close();
	}
	
	public static int [][] passarGeracoes(int [][] vidaAtual, int m) throws IOException{
		
		int [][] futuraGeracao = new int [vidaAtual.length][vidaAtual.length];
		//Esse laço passa o número de gerações solicitado
		for(int i=0; i<m; i++){
			//Esse laço passa por cada linha
			for(int j=0; j<vidaAtual.length; j++){
				
				//SE FOR A PRIMEIRA LINHA
				if(j==0){
					//Esse laço passa por cada coluna da linha atual
					for(int k=0; k<vidaAtual.length; k++){
						
						//PRIMEIRO ELEMENTO PRIMEIRA LINHA
						if(k ==0){
							//Se o elemento estiver morto e tiver três vizinhos
							if(vidaAtual[j][k] == 0 && ((vidaAtual[j][k+1] + vidaAtual[j+1][k] + vidaAtual[j+1][k+1]) == 3)){
								futuraGeracao[j][k] = 1;
							}
							//Se o elemento estiver vivo e menos do que dois vizinhos
							else if(vidaAtual[j][k] == 1 && ((vidaAtual[j][k+1] + vidaAtual[j+1][k] + vidaAtual[j+1][k+1]) <2)){
								futuraGeracao[j][k] = 0;
							}else{
								futuraGeracao[j][k] = vidaAtual[j][k];
							}
						}
						//ELEMENTOS DA PRIMEIRA LINHA, APÓS A 1ª COLUNA A ANTES DA ÚLTIMA
						else if(k > 0 && k < vidaAtual.length-1){
							//Se o elemento estiver morto e três vizinhos
							if(vidaAtual[j][k] == 0 &&((vidaAtual[j][k-1] + vidaAtual[j+1][k-1] + vidaAtual[j+1][k] +
									vidaAtual[j+1][k+1] + vidaAtual[j][k+1]) == 3)){
								futuraGeracao[j][k] = 1;
							}
							//Vivo e mais do que 3 vizinhos, morte por superpopulação, ou menos do que 2 vizinhos solidão
							else if(vidaAtual[j][k] == 1 && 
									((vidaAtual[j][k-1] + vidaAtual[j+1][k-1] + vidaAtual[j+1][k] 
									+ vidaAtual[j+1][k+1] + vidaAtual[j][k+1]) > 3)
									
									|| ((vidaAtual[j][k-1] + vidaAtual[j+1][k-1] + vidaAtual[j+1][k] 
											+ vidaAtual[j+1][k+1] + vidaAtual[j][k+1]) < 2)){
								futuraGeracao[j][k] = 0;
							}else{
								futuraGeracao[j][k] = vidaAtual[j][k];
							}
						}
						
						//ÚLTIMO ELEMENTO, PRIMEIRA LINHA
						else if(k ==vidaAtual.length-1){
							//Se o elemento estiver morto
							if(vidaAtual[j][k] == 0 && ((vidaAtual[j][k-1] + vidaAtual[j+1][k-1] + vidaAtual[j+1][k]) == 3)){
								futuraGeracao[j][k] = 1;
							}
							//Se o elemento estiver vivo e superpopulação ou solidão
							else if(vidaAtual[j][k] == 1 && 
									((vidaAtual[j][k-1] + vidaAtual[j+1][k-1] + vidaAtual[j+1][k]) >3
									|| (vidaAtual[j][k-1] + vidaAtual[j+1][k-1] + vidaAtual[j+1][k]) < 2)){
								futuraGeracao[j][k] = 0;
							}else{
								futuraGeracao[j][k] = vidaAtual[j][k];
							}
						}
					}
					
					//SE NÃO FOR A PRIMEIRA LINHA E NÃO FOR A ÚLTIMA
				}else if(j > 0 && j<vidaAtual.length-1){
					//Percorre todas as colunas
						for(int k=0;k<vidaAtual.length;k++){
							//SE FOR A PRIMEIRA COLUNA
							if(k ==0){
								//Se o elemento estiver morto e tiver três vizinhos
								if(vidaAtual[j][k] == 0 && 
										((vidaAtual[j-1][k] + vidaAtual[j-1][k+1] + vidaAtual[j][k+1] +
										vidaAtual[j+1][k+1] + vidaAtual[j+1][k]) == 3)){
									futuraGeracao[j][k] = 1;
								}//Se o elemento estiver vivo e mais do que 3 vizinhos ou menos que 2
								else if(vidaAtual[j][k] == 1 && 
										((vidaAtual[j-1][k] + vidaAtual[j-1][k+1] + vidaAtual[j][k+1] +
												vidaAtual[j+1][k+1] + vidaAtual[j+1][k])>3)
										||((vidaAtual[j-1][k] + vidaAtual[j-1][k+1] + vidaAtual[j][k+1] +
												vidaAtual[j+1][k+1] + vidaAtual[j+1][k])<2)){
									futuraGeracao[j][k] = 0;
								}else{
									futuraGeracao[j][k] = vidaAtual[j][k];
								}
							}//SE NÃO FOR A PRIMEIRA COLUNA E NÃO FOR A ÚLTIMA
							else if(k > 0 && k <vidaAtual.length-1){
								//Se o elemento estiver morto e tiver mais três vizinhos
								if(vidaAtual[j][k] == 0 && ((vidaAtual[j][k-1] + vidaAtual[j-1][k-1] + vidaAtual[j-1][k] +
										vidaAtual[j-1][k+1] + vidaAtual[j][k+1] + vidaAtual[j+1][k+1] + 
										vidaAtual[j+1][k] + vidaAtual[j+1][k-1]) == 3)){
									futuraGeracao[j][k] = 1;
										}
								//Se o elemento estiver vivo e tiver menos do que 2 vizinhos ou mais do que 3
								else if(vidaAtual[j][k] ==1 &&
										
										((vidaAtual[j][k-1] + vidaAtual[j-1][k-1] + vidaAtual[j-1][k] +vidaAtual[j-1][k+1] +
										 vidaAtual[j][k+1] + vidaAtual[j+1][k+1] + vidaAtual[j+1][k] + vidaAtual[j+1][k-1]) <2)
										
										||((vidaAtual[j][k-1] + vidaAtual[j-1][k-1] + vidaAtual[j-1][k] +vidaAtual[j-1][k+1] +
												 vidaAtual[j][k+1] + vidaAtual[j+1][k+1] + vidaAtual[j+1][k] + vidaAtual[j+1][k-1]) >3)){
									futuraGeracao[j][k] = 0;
								}else{
									futuraGeracao[j][k] = vidaAtual[j][k];
								}
							}
							//SE FOR A ÚLTIMA COLUNA
							else if(k ==vidaAtual.length-1){
								//Se o elemento estiver morto e três vizinhos
								if(vidaAtual[j][k] == 0 
										&& ((vidaAtual[j-1][k] + vidaAtual[j-1][k-1] + vidaAtual[j][k-1] +
												vidaAtual[j+1][k-1] + vidaAtual[j+1][k]) ==3)){
									futuraGeracao[j][k] = 1;
								}//Se o elemento estiver vivo, mais que 3 ou menos que 2 vizinhos
								else if(vidaAtual[j][k] == 1
										&& ((vidaAtual[j-1][k] + vidaAtual[j-1][k-1] + vidaAtual[j][k-1] +
												vidaAtual[j+1][k-1] + vidaAtual[j+1][k]) > 3)
										
										|| ((vidaAtual[j-1][k] + vidaAtual[j-1][k-1] + vidaAtual[j][k-1] +
												vidaAtual[j+1][k-1] + vidaAtual[j+1][k]) <2)){
									futuraGeracao[j][k] = 0;
								}else{
									futuraGeracao[j][k] = vidaAtual[j][k];
								}
							}
					}
				}//SE FOR A ÚLTIMA LINHA
				else if(j == vidaAtual.length-1){
					//Percorre as colunas
					for(int k=0;k<vidaAtual.length;k++){
						
						//SE FOR A PRIMEIRA COLUNA
						if(k ==0){
							//Se o elemento estiver morto e três vizinhos
							if(vidaAtual[j][k] == 0 
									&& ((vidaAtual[j-1][k] + vidaAtual[j-1][k+1] + vidaAtual[j][k+1]) ==3)){
								futuraGeracao[j][k] = 1;
							}//Se o elemento estiver vivo, mais que 3 ou menos que 2 vizinhos
							else if(vidaAtual[j][k] == 1
									&& ((vidaAtual[j-1][k] + vidaAtual[j-1][k+1] + vidaAtual[j][k+1]) > 3)
									
									|| ((vidaAtual[j-1][k] + vidaAtual[j-1][k+1] + vidaAtual[j][k+1]) <2)){
								futuraGeracao[j][k] = 0;
							}else{
								futuraGeracao[j][k] = vidaAtual[j][k];
							}
						}
						//SE NÃO FOR A PRIMEIRA COLUNA E NÃO FOR A ÚLTIMA
						else if(k > 0 && k < vidaAtual.length-1){
							//Se o elemento estiver morto e três vizinhos
							if(vidaAtual[j][k] == 0 
									&& ((vidaAtual[j][k-1] + vidaAtual[j-1][k-1] + vidaAtual[j-1][k] + 
											vidaAtual[j-1][k+1] + vidaAtual[j][k+1]) ==3)){
								futuraGeracao[j][k] = 1;
							}//Se o elemento estiver vivo, mais que 3 ou menos que 2 vizinhos
							else if(vidaAtual[j][k] == 1
									&& ((vidaAtual[j][k-1] + vidaAtual[j-1][k-1] + vidaAtual[j-1][k] + 
											vidaAtual[j-1][k+1] + vidaAtual[j][k+1]) > 3)
									
									|| ((vidaAtual[j][k-1] + vidaAtual[j-1][k-1] + vidaAtual[j-1][k] + 
											vidaAtual[j-1][k+1] + vidaAtual[j][k+1]) <2)){
								futuraGeracao[j][k] = 0;
							}else{
								futuraGeracao[j][k] = vidaAtual[j][k];
							}
						}
						//SE FOR A ÚLTIMA COLUNA
						else{
							//Se o elementos estiver morto e 3 vizinhos
							if(vidaAtual[j][k] == 0 
									&& ((vidaAtual[j-1][k] + vidaAtual[j-1][k-1] + vidaAtual[j][k-1]) ==3)){
								futuraGeracao[j][k] = 1;
							}//Se o elemento estiver vivo, mais que 3 ou menos que 2 vizinhos
							else if(vidaAtual[j][k] == 1
									&& ((vidaAtual[j-1][k] + vidaAtual[j-1][k-1] + vidaAtual[j][k-1]) > 3)
									
									|| ((vidaAtual[j-1][k] + vidaAtual[j-1][k-1] + vidaAtual[j][k-1]) <2)){
								futuraGeracao[j][k] = 0;
							}else{
								futuraGeracao[j][k] = vidaAtual[j][k];
							}
						}
					}
				}
			}			
			//Transfere o conteúdo da geração como atual para a outra futura geração
			for(int j=0;j<futuraGeracao.length;j++){
				for(int k=0;k<futuraGeracao.length;k++){
					vidaAtual[j][k] = futuraGeracao[j][k];
				}
			}
		}
		return futuraGeracao;
	}
	
	public static int [][] criarMatriz (File file) throws IOException{
		
		FileInputStream stream = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
		
		String data = null;
		int numLinhas = Integer.valueOf(br.readLine()); 
		int vidaAtual [][] = new int [numLinhas][numLinhas];
		
		for(int i=0;i < numLinhas;i++){
			data = br.readLine();
			for(int j=0; j< numLinhas;j++){
				vidaAtual[i][j] = (data.charAt(j))/49;
			}
		}
		br.close();
		return vidaAtual;
	}
	
	public static void lerMatriz(int [][]vidaAtual) throws IOException{
		
		for(int i=0;i < vidaAtual.length;i++){
			for(int j=0;j<vidaAtual.length;j++){
				System.out.print(vidaAtual[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	public static File criarArquivo(int vidaAtual[][])throws IOException{
		
		File file = new File("lifeGameSaida.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		for(int i=0;i<vidaAtual.length;i++){
			for(int j=0;j<vidaAtual.length;j++){
				writer.write(String.valueOf(vidaAtual[i][j]));
			}
			writer.write("\r\n");
		}
		writer.flush();
		writer.close();
		return file;
	}
}
