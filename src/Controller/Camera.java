package Controller;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ModelVo.Objeto;
import ModelVo.Resultado;
import ModelVo.TileMap;
import View.Fase;
import View.Tela;
import View.TelaGeral;

public class Camera {
	
	public static boolean multiplayer = false;
	
	private Objeto player1, player2;
	private ArrayList<TileMap> camadas;
	private ArrayList<Resultado> resultados;
	
	public static int x = 0;
	public static int y = 0;
	
	private BufferedImage tela;
	private Graphics2D g;
		
	public Camera(Objeto player1, Objeto player2, ArrayList<TileMap> camadas, ArrayList<Resultado> resultados) {
		this.player1 = player1;
		this.player2 = player2;
		this.camadas = camadas;
		this.resultados = resultados;
		tela=new BufferedImage(TileMap.getMapaLargura(), TileMap.getMapaAltura(), BufferedImage.TYPE_4BYTE_ABGR);
		g=(Graphics2D)tela.getGraphics();
	
	}
	
	public void renderinzar(){
		
		for(TileMap map : camadas)
		{
			if(!Fase.barras)
			{
				if(!camadas.get(2).equals(map))
					g.drawImage(map.getMapa(), 0, 0,null);					
			}
			else
				g.drawImage(map.getMapa(), 0, 0,null);
		}		
		for(Resultado res : resultados)
		{
			res.draw(g);
		}
		
		player1.draw(g);
		if(multiplayer)
			player2.draw(g);
		
	}
	
	public void atualizarTela(Objeto personagem)
	{
		if(personagem.getX()>TelaGeral.LARGURA/2)
		{
			if(personagem.getX()<(TileMap.getMapaLargura()-TelaGeral.LARGURA/2))
				x=personagem.getX()-(TelaGeral.LARGURA/2);	
		}
		else
		{
			x = 0;
			y = 0;

		}
		if(personagem.getY()>TelaGeral.ALTURA/2)
			if(personagem.getY()<(TileMap.getMapaAltura()-TelaGeral.ALTURA/2))
					y=personagem.getY()-TelaGeral.ALTURA/2;
	}
	
	public void draw(Graphics2D g){
		
		
		if(multiplayer)
		{
			//player1
			if(player1.getLado() == 1)
			{
				if(player1.getX() > player2.getX())
					atualizarTela(player1);
			}
			else
				if(player1.getX() < player2.getX())
					atualizarTela(player1);
			
			//player2
			if(player2.getLado() == 1)
			{
				if((player2.getX() > player1.getX()))
					atualizarTela(player2);			
			}
			else
				if((player2.getX() < player1.getX()))
					atualizarTela(player2);			
		}
		else
			atualizarTela(player1);
		
//		if(x > player1.getX())
//			player1.setX(player1.getX() + 20);
//		if(x > player2.getX())
//			player2.setX(player2.getX() + 20);
//		
//		System.out.println(x+(TileMap.getMapaLargura()-TelaGeral.LARGURA/2));
		
//		if(player1.getX() > x+(TileMap.getMapaLargura()-(TelaGeral.LARGURA/2)-player1.getLarguraImg()))
//			player1.setX(player1.getX() - 20);
//		if(player2.getX() > x+(TileMap.getMapaLargura()-(TelaGeral.LARGURA/2)-player2.getLarguraImg()))
//			player2.setX(player2.getX() - 20);
		
		g.drawImage(tela, -x, -y, null);
		
	}

	public void addCamada(TileMap camada)
	{
		camadas.add(camada);				
	}
}
