package ModelVo;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class TileMap {

	//dados do mapa e do tileset
	public final static int numeroColunas = 36;
	public final static int numeroLinhas = 84;
	public final static int colunasTileSet = 46;
	public final static int tileSize = 32;
	
	private int camada [][];
	private static int mapaLargura = numeroColunas*tileSize;
	private static int mapaAltura = numeroLinhas *tileSize;
	
	//imagens 
	private BufferedImage tileSet;
	private BufferedImage mapa = new BufferedImage(mapaLargura, mapaAltura, BufferedImage.TYPE_4BYTE_ABGR);
	
	private Graphics2D db = mapa.createGraphics();
	
	public TileMap(String nomeTileset,String nomeMapaMatriz){
		
		try {
			tileSet = ImageIO.read(getClass().getClassLoader().getResourceAsStream(nomeTileset));
		} catch (IOException e) {
			System.out.println("Não conseguir carregar o tile");
			e.printStackTrace();
		}
		
		camada=carregarMatriz(nomeMapaMatriz);	
	}
	
	public void montarMapa (){
		mapa.createGraphics();

		int tile;
		int tileRow;
		int tileCol;

		//calcula a area que vai ser pintada do tileset
		for(int i = 0; i < numeroLinhas; i++){
			for(int j = 0; j < numeroColunas; j++){
				tile = (camada[i][j] != 0) ? (camada[i][j] - 1) : 2302; // se não tiver igual o arquivo gerado ao lido tirar o -1
				tileRow =(tile / colunasTileSet) | 0;
				tileCol =(tile % colunasTileSet) | 0;
				db.drawImage(tileSet,
						(j * tileSize),
						(i * tileSize),
						(j * tileSize) + tileSize,
						(i * tileSize) + tileSize,
						(tileCol * tileSize),
						(tileRow * tileSize),
						(tileCol * tileSize) + tileSize,
						(tileRow * tileSize) + tileSize,
						null);   

			}
		}

	}

	public void desmontarMapa()
	{
		mapa.createGraphics();

		int tile;
		int tileRow;
		int tileCol;

		//calcula a area que vai ser pintada do tileset
		for(int i = 0; i < numeroLinhas; i++){
			for(int j = 0; j < numeroColunas; j++){
				tile = (camada[i][j]); // se não tiver igual o arquivo gerado ao lido tirar o -1
				if(tile != 0)
					tile = 291;
				if(tile == 0)
					tile = 2302;
				tileRow =(tile / colunasTileSet) | 0;
				tileCol =(tile % colunasTileSet) | 0;
				db.drawImage(tileSet,
						(j * tileSize),
						(i * tileSize),
						(j * tileSize) + tileSize,
						(i * tileSize) + tileSize,
						(tileCol * tileSize),
						(tileRow * tileSize),
						(tileCol * tileSize) + tileSize,
						(tileRow * tileSize) + tileSize,
						null);
			}
		}

	}
	
	public ArrayList<Rectangle> montarColisao() 
	{
		ArrayList<Rectangle> tmp=new ArrayList<>();
	
		//calcula as partes do mapa que o player nao podera atravessar
		for (int i = 0; i < numeroLinhas; i++) {
			for (int j = 0; j < numeroColunas; j++) {
				if(camada[i][j] != 0) {
					tmp.add(new Rectangle( (j * tileSize), (i * tileSize), tileSize, tileSize));
				}
			}
		}
//		System.out.println(tmp.size());
		return tmp;
	}
	
	//carregar o a matriz correspondente do mapa (arquivo txt)
	public int[][] carregarMatriz (String nomeMapa){
		int [][] mat = new int [numeroLinhas][numeroColunas];
			
		InputStream input = getClass().getClassLoader().getResourceAsStream(nomeMapa);
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		
		ArrayList<String> linhas = new ArrayList<>();
		
		String linha="";
		
		try {
			while((linha=br.readLine())!= null)
				
			linhas.add(linha);
			
			int coluna=0;
			for (int i =0; i<linhas.size();i++){
				StringTokenizer token = new StringTokenizer(linhas.get(i), ",");
				
				while(token.hasMoreElements()){
					mat[i][coluna] = Integer.parseInt(token.nextToken());
					coluna++;
				}
				coluna=0;
			}	
				
		}catch (IOException e) {
			System.out.println("Não carregou a Matriz!!");
			e.printStackTrace();
		}
		
		return mat;
	}
	
	/**
	 * @return o mapaLargura
	 */
	public static int getMapaLargura() {
		return mapaLargura;
	}

	/**
	 * @return o mapaAltura
	 */
	public static int getMapaAltura() {
		return mapaAltura;
	}
	/**
	 * @return o mapa
	 */
	public BufferedImage getMapa() {
		return mapa;
	}

	public int[][] getCamada() {
		return camada;
	}
}
	