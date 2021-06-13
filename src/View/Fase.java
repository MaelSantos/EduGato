package View;

import java.awt.Rectangle;
import java.util.ArrayList;

import Controller.Camera;
import Controller.ControlePlayer;
import ModelVo.Objeto;
import ModelVo.Resultado;
import ModelVo.TileMap;

public class Fase extends Jogo {

	private Objeto player1,player2;
	private Camera camera;
			
	private ArrayList<TileMap> camadas;
	
	private TileMap camada_terra;
	private TileMap camada_fundo;
	private TileMap camada_cenario;
	private TileMap camada_barras;
	
	private ArrayList<Resultado> resultados;
	public static ArrayList<Rectangle> retangulosColid;
	
	public static boolean barras = true;

	public Fase() {
		
		setName("Fase");
		setVisible(false);

	}

	@Override
	public void inicializar() {
			
		resultados = new ArrayList<>();
		for(int i = 0; i < 4; i++)
			resultados.add(new Resultado(200+(i*220), 50, 100, 100));
		
		try {
			player1 = new Objeto(0, 233, 833, 2, 5, 0, 20, "player_1.png");
			player2 = new Objeto(0, 233, 833, 2, 5, 50, 20, "player_2.png");
			
			camada_fundo = new TileMap("tileset.png", "vector_fundo.txt");
			camada_cenario = new TileMap("tileset.png", "vector_cenario.txt");
			camada_barras = new TileMap("tileset.png", "vector_barras.txt");
			camada_terra = new TileMap("tileset.png", "vector_terra.txt");

		} catch (Exception e) {
			System.out.println("Falha Na Inicializaçao");
			e.printStackTrace();
		}
		
		new ControlePlayer(player1,1);
		new ControlePlayer(player2,2);
		
		camadas = new ArrayList<>();
	
		camadas.add(camada_fundo);
		camadas.add(camada_cenario);
		camadas.add(camada_barras);
		camadas.add(camada_terra);
		
		camera = new Camera(player1,player2, camadas, resultados);
		
		retangulosColid = camada_terra.montarColisao();
		mudarColisao(true);
		
	}

	@Override
	public void atualizar() {
	
		camada_barras.montarMapa();		
		camada_fundo.montarMapa();
		camada_terra.montarMapa();
		camada_cenario.montarMapa();
		
		camera.renderinzar();
		
	}

	@Override
	public void draw() {
		
		camera.draw(graficos);
		
//		player2.draw(graficos);
	}

	public void mudarColisao(boolean condicao)
	{
		if(condicao)
		{
			barras = true;
			for(Rectangle rec : camada_barras.montarColisao())
				retangulosColid.add(rec);
		}
		else
		{
			barras = false;
			retangulosColid = camada_terra.montarColisao();
		}
	}
	
	//metodos de acesso
	public Objeto getPlayer1() {
		return player1;
	}
	public Objeto getPlayer2() {
		return player2;
	}
	public ArrayList<Resultado> getResultados() {
		return resultados;
	}

}
