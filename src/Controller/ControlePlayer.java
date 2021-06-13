package Controller;

import ModelVo.Objeto;
import ModelVo.TileMap;
import View.Configuracoes;
import View.Fase;
import View.TelaGeral;

public class ControlePlayer extends Thread {

	private Objeto personagem;
	private int play = 0;
	
	protected static String mover1 = "";
	protected static String mover2 = "";
	protected static boolean moverBloco = false;
		
	public ControlePlayer(Objeto player1, int play) {
		this.personagem = player1;
		this.play = play;
		start();
	}
	
	@Override
	public void run() {
			
		while(true)
		{		
			if(play == 1)
				mover(personagem, mover1);
			else if(play == 2)
				mover(personagem, mover2);			
			
			try {
				sleep(Configuracoes.velocidadePlayer);
			} catch (Exception e) {
			}
		}
		
	}
	
	public void mover(Objeto personagem, String direcao)
	{
		if(personagem != null && Fase.retangulosColid != null)
		{	
			if(!(personagem.getX() > Camera.x+
					(TileMap.getMapaLargura()-(TelaGeral.LARGURA/2)-personagem.getLarguraImg())))
			{
				if(!(personagem.getX() < Camera.x))
				{
					personagem.mover("baixo");
					personagem.mover(direcao);									
				}
				else
					personagem.mover("direita");
			}
			else
				personagem.mover("esquerda");
			personagem.animar(direcao);											
		}
	}	
}
