package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.Timer;

import Controller.Camera;
import ModelDao.BaseDeDados;
import ModelVo.Jogador;
import ModelVo.Questao;

public class Inventario extends PainelGeral {

	public static int questaoAtual = 0;
	private static int maiorPontuacao;
	
	private Jogador jogador1,jogador2;
	
	private int barra_player1 = 370;
	private int barra_player2 = 370;

	ArrayList<Questao> questoes;
	
	private Botao btnVoltar;
	
	private Timer timer;
	private int minutos = 1;
	private int segundos = 00;
	
	private BufferedImage fundo2;
	
	public Inventario(String fundo1, String fundo2, String nomeTela) {
		super(fundo1, nomeTela);
		
		try {
			this.fundo2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream(fundo2));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setBounds(800,0,400,700);
	}
	@Override
	public void inicializar() {
		
		btnVoltar = new Botao(135, 540, Botao.MENU);
		maiorPontuacao = BaseDeDados.buscarRank("res/rank.xml");
		questoes = new ArrayList<>();
		jogador1 = new Jogador("", 0);
		jogador2 = new Jogador("", 0);
		
		add(btnVoltar);
		
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(segundos == 00)
				{
					if(minutos == 0 && segundos == 0)
					{
						barra_player1 = 0;
						barra_player2 = 0;
						jogador1.setVida(0);
						jogador2.setVida(0);
						timer.stop();
					}
					else
					{
						minutos --;
						segundos = 60;						
					}
				}
				else
					segundos--;
				
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D graficos = (Graphics2D)g;
		
		if(!Camera.multiplayer)
			graficos.drawImage(fundo2, 0, 0, null);
		else
			graficos.drawImage(getFundo(), 0, 0, null);
		
		draw(graficos);
		
		try {
			Thread.sleep(50);
			repaint();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Haettenschweiler",Font.LAYOUT_LEFT_TO_RIGHT, 34));
		
		
		g.drawString(""+maiorPontuacao, 100, 360);
		g.drawString(completaComZero(minutos)+":"+completaComZero(segundos), 90, 545);
		
		//Player 1
		//trata as cores de acordo com a vida do player
		if(jogador1.getVida() >= 50)//se a vida for maior ou igual a 50
			g.setColor(new Color(0, 128, 0));//cor verde
		else if(jogador1.getVida() < 50 && jogador1.getVida() > 30)//entre 50 e 30
			g.setColor(new Color(255,215,0));//cor amarelo
		else if(jogador1.getVida() <= 30 && jogador1.getVida() > 10)//entre 30 e 10
			g.setColor(Color.ORANGE);//laranja
		else if(jogador1.getVida() <= 10)//de 10 para baixo
			g.setColor(Color.RED);//vermelho
		
		g.fillRect(15, 120, barra_player1, 28);	
		g.setColor(Color.BLACK);
		g.drawString(""+jogador1.getNome()+" : "+jogador1.getPontos(), 200, 105);
		g.drawString(""+jogador1.getVida()+"%", 170, 145);
		
		//player 2
		if(Camera.multiplayer)
		{
			//trata as cores de acordo com a vida do player
			if(jogador2.getVida() >= 50)//se a vida for maior ou igual a 50
				g.setColor(new Color(0, 128, 0));//cor verde
			else if(jogador2.getVida() < 50 && jogador2.getVida() > 30)//entre 50 e 30
				g.setColor(new Color(255,215,0));//cor amarelo
			else if(jogador2.getVida() <= 30 && jogador2.getVida() > 10)//entre 30 e 10
				g.setColor(Color.ORANGE);//laranja
			else if(jogador2.getVida() <= 10)//de 10 para baixo
				g.setColor(Color.RED);//vermelho
			
			g.fillRect(15, 270, barra_player2, 28);
			g.setColor(Color.BLACK);
			g.drawString(""+jogador2.getNome()+" : "+jogador2.getPontos(), 200, 255);
			g.drawString(""+jogador2.getVida()+"%", 170, 295);
			
			if(jogador2.getPontos() > maiorPontuacao)
			{
				maiorPontuacao = jogador2.getPontos();
				BaseDeDados.salvarRank(""+maiorPontuacao, "res/rank.xml");
			}
		}
		
		questoes.get(questaoAtual).draw(g);
		
		btnVoltar.draw(g);

		if(jogador1.getPontos() > maiorPontuacao)
		{
			maiorPontuacao = jogador1.getPontos();
			BaseDeDados.salvarRank(""+maiorPontuacao, "res/rank.xml");
		}
		
	}
	
	private String completaComZero(int i) {  

        if( i < 10 ) {  
            return "0"+i;  
        } else {  
            return  ""+i;  
        }  
    }  

	public void carregarDados(Jogador jogador)//carrega os dados do jogador guardado na memoria(xml)
	{	
		ArrayList<Jogador> jog = BaseDeDados.buscarJogador();
		boolean add = false;
		
		for(Jogador j : jog)
			if(j.getNome().equalsIgnoreCase(jogador.getNome()))
			{
				jogador.setAcertos(j.getAcertos());
				jogador.setErros(j.getErros());
				jogador.setVida(j.getVida());
				if(jogador.getVida() < 0)
				{
					jogador.setVida(100);
				}
				jogador.setPontos(j.getPontos());
				
				add = true;
			}
		
		if(add)
		{
			questaoAtual = 0;
			for(Questao q : jogador.getAcertos())
				if(questoes.contains(q))
					questaoAtual++;			
		}

		if(questaoAtual > questoes.size() -1)
			questaoAtual = questoes.size() -1;
		
		barra_player1 = 370;
		barra_player2 = 370;		
		barra_player1 = (int) (370*((double)jogador1.getVida()/100));
		barra_player2 =  (int) (370*((double)jogador2.getVida()/100));
		
	}
	
	//metodos de acesso
	public JButton getBtnVoltar() {
		return btnVoltar;
	}

	public Timer getTimer() {
		
		minutos = 1;
		segundos = 00;
		
		return timer;
	}

	public ArrayList<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(ArrayList<Questao> questoes) {
		this.questoes = questoes;
	}

	public Jogador getJogador1() {
		return jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}

	public int getBarra_player1() {
		return barra_player1;
	}

	public void setBarra_player1(int barra_player1) {
		this.barra_player1 = barra_player1;
	}

	public int getBarra_player2() {
		return barra_player2;
	}

	public void setBarra_player2(int barra_player2) {
		this.barra_player2 = barra_player2;
	}

}
