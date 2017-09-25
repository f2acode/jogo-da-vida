//package felipeaugustox.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class JogoDaVida {

	public static void main (String [] args) throws IOException{
		
		int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a opção desejada:"
				+ "\n 1) Exemplo default"
				+ "\n 2) Gerar aleatório"
				+ "\n 3) Selecionar arquivo"));
		
		switch (opcao) {
		case 1:
			File basicao = new File("lifeGameEntrada.txt");
			int [][] vidaAtual = criarMatriz(basicao);
			int m = 2;
			System.out.println();
			lerMatriz(passarGeracoes(vidaAtual, m));
			break;
		case 2:
			File aleatorio = new File("");
			break;
		case 3:
			break;
		}	
	}
	
	public static int [][] passarGeracoes(int [][] vidaAtual, int m) throws IOException{
		
		int [][] futuraGeracao = new int [vidaAtual.length][vidaAtual.length];
		//Esse laço passa o número de gerações solicitado
		for(int i = 0; i<m;i++){
			
			//Esse laço passa por cada linha
			for(int j= 0;j<vidaAtual.length;j++){
				
				//SE FOR A PRIMEIRA LINHA
				if(j==0){
					//Esse laço passa por cada coluna da linha atual
					for(int k=0;k<vidaAtual.length;k++){
						
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
						}//SE NÃO FOR A PRIMEIRA COLUNA E NÃO FOR A ÚLTIMA
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
						}//SE FOR A ÚLTIMA COLUNA
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
			System.out.println();
			lerMatriz(futuraGeracao);
			System.out.println();
			
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
		lerMatriz(vidaAtual);
		br.close();
		return vidaAtual;
	}
	
	public static File criarArquivo(int geracaoAtual[][])throws IOException{
		
		
		File file = new File("lifeGame.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		writer.write("60 1 1 1 1 1 \r\n");
		writer.write("1 1 1 1 2 2 \r\n");
		writer.write("1 1 1 1 3 1 \r\n");
		writer.write("1 1 1 1 4 1 \r\n");
		
		writer.flush();
		writer.close();
		return file;
	}
	
	public static void lerArquivo(File file) throws IOException{
		FileReader fileReader = new FileReader (file);
		BufferedReader reader = new BufferedReader(fileReader);
		String data = null;
		
		for(int i=0;i < 11;i++){
			data = reader.readLine();
			System.out.print(data + "\n");	
		}
		/*
		 * PARA LER E COLOCAR EM UMA LINHA
		while((data = reader.readLine()) != null){
			//System.out.print(data);
			}  
		*/
		fileReader.close();
		reader.close();
	}
	
	public static void lerMatriz(int [][]vidaAtual) throws IOException{
		
		for(int i=0;i < vidaAtual.length;i++){
			for(int j=0;j<vidaAtual.length;j++){
				System.out.print(vidaAtual[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	public static void lerMatrizBonito(int [][]vidaAtual) throws IOException{
		
		String txt = "";
		for(int i=0;i < vidaAtual.length;i++){
			for(int j=0;j<vidaAtual.length;j++){
				txt += vidaAtual[i][j] + " ";
			}
			txt += "\n";
		}
		JOptionPane.showMessageDialog(null, txt);
		
	}
}
