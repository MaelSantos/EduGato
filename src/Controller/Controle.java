package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import ModelBo.Validar;
import ModelDao.BaseDeDados;
import ModelVo.Jogador;
import ModelVo.Objeto;
import ModelVo.Questao;
import ModelVo.Resultado;
import ModelVo.TileMap;
import View.Ajuda;
import View.Assunto;
import View.Configuracoes;
import View.Creditos;
import View.Entrar;
import View.Fase;
import View.Inventario;
import View.Mensagem;
import View.Menu;
import View.Professor;
import View.Tela;

public class Controle implements ActionListener, KeyListener{
	
	private Tela tela;//Jframe
	private Menu menu;//jpanel
	private Fase fase;//jpanel
	private Ajuda ajuda;//jpanel
	private Creditos creditos;//jpanel
	private Configuracoes configuracoes;//panel
	private Inventario inventario;//jpanel
	private Assunto assunto;//jpanel
	private Entrar entrar;//jpanel
	private Professor professor;//jpanel
	private Mensagem mensagem;//panel
	
	//arraylist
	private ArrayList<Resultado> resultados;
	private ArrayList<Questao> questao;
	
	private Objeto player1, player2;
	
	private boolean proxFase = false;
	private boolean jogando = true;
	int temp = 100;
	
	//Thread para atualizar a fase 
	Atualizar atualizar = new Atualizar();
	
	public Controle(Tela tela, Menu menu, Fase fase, Ajuda ajuda, Configuracoes configuracoes, Creditos creditos, 
			Inventario inventario, Assunto assunto, Entrar entrar, Professor professor,Mensagem mensagem) {
		this.menu = menu;
		this.fase = fase;
		this.ajuda = ajuda;
		this.creditos = creditos;
		this.configuracoes = configuracoes;
		this.inventario = inventario;
		this.assunto = assunto;
		this.entrar = entrar;
		this.tela = tela;
		this.professor = professor;
		this.mensagem = mensagem;
		
		menu.getBtnIniciar().addActionListener(this);
		menu.getBtnAjuda().addActionListener(this);
		menu.getBtnInfo().addActionListener(this);
		menu.getBtnConfiguracoes().addActionListener(this);
		menu.getBtnSair().addActionListener(this);
		
		inventario.getBtnVoltar().addActionListener(this);
		ajuda.getBtnVoltar().addActionListener(this);
		creditos.getBtnVoltar().addActionListener(this);
		
		configuracoes.getBtnVoltar().addActionListener(this);
		configuracoes.getBtnMenosPintura().addActionListener(this);
		configuracoes.getBtnMaisPintura().addActionListener(this);
		configuracoes.getBtnMenosPlayer().addActionListener(this);
		configuracoes.getBtnMaisPlayer().addActionListener(this);
		
		assunto.getBtnPlayer().addActionListener(this);
		assunto.getBtnMultiplayer().addActionListener(this);
		assunto.getBtnVoltar().addActionListener(this);
		
		entrar.getBtnAluno().addActionListener(this);
		entrar.getBtnProfessor().addActionListener(this);
		entrar.getBtnVoltar().addActionListener(this);
		
		professor.getBtnCancelar().addActionListener(this);
		professor.getBtnConfirmar().addActionListener(this);
		
		fase.addKeyListener(this);
		
		tela.setTitle(menu.getName());
		
		player1 = fase.getPlayer1();
		player2 = fase.getPlayer2();
//		bloco = fase.getBloco();
		resultados = fase.getResultados();
		
		atualizar.start();
		
	}
		
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == menu.getBtnIniciar())//açao do botao iniciar
		{
			mudarTela(entrar, menu);
			
		}
		if(e.getSource() == menu.getBtnAjuda())//açao do botao ajuda
		{
			mudarTela(ajuda, menu);	
		}
		
		if(e.getSource() == menu.getBtnInfo())//açao do botao info
		{			
			mudarTela(creditos, menu);
		}
		if(e.getSource() == menu.getBtnConfiguracoes())
		{
			mudarTela(configuracoes,menu);
		}
		
		if(e.getSource() == menu.getBtnSair())//açao do botao sair
		{
			int opcao = mensagem.mensagemConfirmar("Tem Certeza Que Deseja Sair?");
			opcao = mensagem.getEscolha();
			if(opcao == Mensagem.YES)
			{
				System.exit(0);
			}
			
		}
		if(e.getSource() == inventario.getBtnVoltar())
		{
			int verificar = mensagem.mensagemConfirmar("Deseja Retorna Ao Menu?");
			verificar = mensagem.getEscolha();
			if(verificar == Mensagem.YES)
			{	
				finalizarFase();
			}
			else
			{
				fase.requestFocus();
			}
		}
		if(e.getSource() == ajuda.getBtnVoltar())
		{
			mudarTela(menu, ajuda);
		}
		if(e.getSource() == creditos.getBtnVoltar())
		{
			mudarTela(menu, creditos);
		}
		if(e.getSource() == assunto.getBtnVoltar())
		{
			mudarTela(menu, assunto);
		}
		if(e.getSource() == entrar.getBtnVoltar())
		{
			mudarTela(menu, entrar);
		}
		if(e.getSource() == professor.getBtnCancelar())
		{
			mudarTela(menu, professor);
			professor.limparCampo();
		}
		if(e.getSource() == entrar.getBtnAluno())
		{
			mudarTela(assunto, entrar);
		}
		if(e.getSource() == entrar.getBtnProfessor())
		{
			mudarTela(professor, entrar);
		}
		if(e.getSource() == configuracoes.getBtnVoltar())
		{
			mudarTela(menu, configuracoes);
		}
		if(e.getSource() == configuracoes.getBtnMenosPintura())
		{
			Configuracoes.velocidadePintura -= 1;
			configuracoes.getPgbVelocidadePintura().setValue(Configuracoes.velocidadePintura);
		}
		if(e.getSource() == configuracoes.getBtnMaisPintura())
		{
			Configuracoes.velocidadePintura += 1;
			configuracoes.getPgbVelocidadePintura().setValue(Configuracoes.velocidadePintura);
		}
		if(e.getSource() == configuracoes.getBtnMenosPlayer())
		{
			Configuracoes.velocidadePlayer -= 1;
			configuracoes.getPgbVelocidadePlayer().setValue(Configuracoes.velocidadePlayer);
		}
		if(e.getSource() == configuracoes.getBtnMaisPlayer())
		{
			Configuracoes.velocidadePlayer += 1;
			configuracoes.getPgbVelocidadePlayer().setValue(Configuracoes.velocidadePlayer);
		}
		if(e.getSource() == professor.getBtnConfirmar())
		{
			if(Validar.isCampoVazio(professor))
			{
				Questao quest = new Questao(professor.getTfdQuestao().getText().trim(), 
						professor.getTfdResposta().getText().trim(),
						professor.getTfdErro1().getText().trim(), 
						professor.getTfdErro2().getText().trim(), 
						professor.getTfdErro3().getText().trim(), 
						professor.getTfdErro4().getText().trim());
				
				BaseDeDados.salvarQuestao(quest, "res/questoes"+
						professor.getAssunto().getSelectedItem().toString().trim()+".xml");
				
				if(BaseDeDados.salvarAssunto(professor.getAssunto().getSelectedItem().toString().trim()))
				{
					professor.getAssunto().addItem(professor.getAssunto().getSelectedItem().toString().trim());
					assunto.getCbbAssutos().addItem(professor.getAssunto().getSelectedItem().toString().trim());
				}
				
				professor.limparCampo();
				mensagem.exibirMensagem("Questão Cadastrada Com Sucesso!");	
			}
			else
				mensagem.exibirMensagem("Todos os Campos Devem Ser Preenchidos!!!");
		}
		if(e.getSource() == assunto.getBtnPlayer())
		{
			if(Validar.isCampoVazioPlayer(assunto))
			{
				questao = BaseDeDados.buscarQuestoes("res/questoes"+
						(assunto.getCbbAssutos().getSelectedItem()).toString()+".xml");
				inventario.setQuestoes(questao);
				Camera.multiplayer = false;
				iniciarFase();
			}
			else
				mensagem.exibirMensagem("Insira o Nome do Player");
		}
		if(e.getSource() == assunto.getBtnMultiplayer())
		{
			if(Validar.isCampoVazioMultiplayer(assunto))
			{
				questao = BaseDeDados.buscarQuestoes("res/questoes"+
						(assunto.getCbbAssutos().getSelectedItem()).toString()+".xml");
				inventario.setQuestoes(questao);
				Camera.multiplayer = true;
				iniciarFase();
			}
			else
				mensagem.exibirMensagem("Insira o Nome Dos Players");
		}
	}
	
	public void iniciarFase() 
	{
		fase.setLoop(true);
		
		tela.setSize(Tela.LARGURA + 400, Tela.ALTURA);
		
		inventario.getJogador1().setNome(assunto.getTfdNome1().getText().trim());
		inventario.getJogador1().setPontos(0);
		inventario.setBarra_player1(370);
//		inventario.getJogador1().setVida(100);
		
		if(Camera.multiplayer)
		{
			inventario.getJogador2().setNome(assunto.getTfdNome2().getText().trim());
			inventario.getJogador2().setPontos(0);
			inventario.setBarra_player2(370);
//			inventario.getJogador2().setVida(100);
		}
		
		Inventario.questaoAtual = 0;
		inventario.setVisible(true);
		mudarTela(fase, assunto);
		
		if(player1 == null)
			player1 = fase.getPlayer1();
		if(player2 == null)
			player2 = fase.getPlayer2();
		if(resultados == null)
			resultados = fase.getResultados();
				
		inventario.carregarDados(inventario.getJogador2());
		inventario.carregarDados(inventario.getJogador1());
		
		if(Inventario.questaoAtual >= questao.size()-1)
		{
			mensagem.exibirMensagem("Você Já Concluiu Todos Os Problemas De "+
					assunto.getCbbAssutos().getSelectedItem().toString());
			finalizarFase();
			Inventario.questaoAtual = 0;
			return;
		}
				
		configurarResultados();
		temp = resultados.get(0).getY() + player1.getAlturaImg() + 100;
		
		inventario.getTimer().start();
		atualizar.emJogo = true;
	
	}
	
	public void finalizarFase()
	{
		inventario.setVisible(false);
		tela.setSize(Tela.LARGURA, Tela.ALTURA);
		mudarTela(menu, fase);
		fase.setLoop(false);
		player1.setLocale(50, 0);
		player2.setLocale(0, 0);
		inventario.getTimer().stop();
		atualizar.emJogo = false;
		fase.mudarColisao(true);
		
		BaseDeDados.salvarJogador(inventario.getJogador1());
		if(Camera.multiplayer)
			BaseDeDados.salvarJogador(inventario.getJogador2());
		
		for(Resultado res : resultados)
		{
			res.setY(50);
		}
	}
	
	public void atualizarFase()
	{		
		if(inventario.getJogador1().getVida() > 0 && inventario.getJogador2().getVida() > 0)
		{
			if((proxFase == true) && 
					(player1.getY() > temp || player2.getY() > temp))
			{
				
				if (jogando)
				{
					inventario.getJogador1().setPontos(inventario.getJogador1().getPontos() + 10);
					inventario.getJogador1().getAcertos().add(questao.get(Inventario.questaoAtual));
				}
				else
				{
					inventario.getJogador2().setPontos(inventario.getJogador2().getPontos() + 10);
					inventario.getJogador2().getAcertos().add(questao.get(Inventario.questaoAtual));
				}
				
				if(Inventario.questaoAtual < questao.size()-1)
					Inventario.questaoAtual++;
				else
				{
					mensagem.exibirMensagem("Parabéns Por Concluir Os Problemas De "+
				assunto.getCbbAssutos().getSelectedItem().toString());
					finalizarFase();
					Inventario.questaoAtual = 0;
					
				}
				inventario.updateUI();
					
				configurarResultados();
				configurarFase();
			
			}
			else if((proxFase == false) && 
					(player1.getY() > temp || player2.getY() > temp))
			{	
				decrementarInventario();
				configurarResultados();
				configurarFase();
			}
			if(Camera.multiplayer)
			{
				if(player1.getY() > player2.getY()+player2.getAlturaImg()/2)
				{
					player2.setLocale(player1.getX()-10, resultados.get(0).getY()-player2.getAlturaImg()/2);
				}
				if(player2.getY() > player1.getY()+player1.getAlturaImg()/2)
				{
					player1.setLocale(player2.getX() -10, resultados.get(0).getY()-player1.getAlturaImg()/2);
				}				
			}

		}
		else
		{
			mensagem.exibirMensagem("Game Over!!!");
			finalizarFase();
		}
		
	}
	
	public void decrementarInventario()
	{	
		if(jogando)
		{
			inventario.getJogador1().setPontos(inventario.getJogador1().getPontos() - 10);
			inventario.setBarra_player1(inventario.getBarra_player1() - 37);	
			inventario.getJogador1().setVida(inventario.getJogador1().getVida() - 10);
			inventario.getJogador1().getErros().add(questao.get(Inventario.questaoAtual));
		}
		else
		{
			inventario.getJogador2().setPontos(inventario.getJogador2().getPontos() - 10);
			inventario.setBarra_player2(inventario.getBarra_player2() - 37);
			inventario.getJogador2().setVida(inventario.getJogador2().getVida() - 10);
			inventario.getJogador2().getErros().add(questao.get(Inventario.questaoAtual));
		}
		
		inventario.updateUI();
	}
	
	public void mudarTela(JPanel abrirTela, JPanel fecharTela)
	{
		fecharTela.setVisible(false);
		
		abrirTela.setVisible(true);
		abrirTela.requestFocus();
		
		tela.setLocationRelativeTo(null);
		tela.setTitle(abrirTela.getName());

	}

	public void configurarResultados()
	{	
		int i = (int) (Math.random() * resultados.size());
		
		resultados.get(0).setResposta(questao.get(Inventario.questaoAtual).getErro1());
		resultados.get(1).setResposta(questao.get(Inventario.questaoAtual).getErro2());
		resultados.get(2).setResposta(questao.get(Inventario.questaoAtual).getErro3());
		resultados.get(3).setResposta(questao.get(Inventario.questaoAtual).getErro4());
		
		resultados.get(i).setResposta(questao.get(Inventario.questaoAtual).getResposta());
		
	}
	
	public void configurarFase()
	{
		for(Resultado res : resultados)
		{
			res.setY(res.getY() + TileMap.getMapaAltura()/4);
		}
		if(resultados.get(0).getY() >= TileMap.getMapaAltura())
		{
			player1.setLocale(50, 0);
			player2.setLocale(0, 0);
			Camera.x = 0;
			Camera.y = 0;
			for(Resultado res : resultados)
			{
				res.setY(50);	
			}
			temp = (resultados.get(0).getY() + player1.getAlturaImg()) + 100;
		}
		else
			temp = (resultados.get(0).getY() + player1.getAlturaImg()) + 100;
	
		fase.mudarColisao(true);
		
		inventario.getTimer().restart();
		proxFase = false;				
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		 
		int codigo = e.getKeyCode();

		switch (codigo) {

		case KeyEvent.VK_ENTER:
			
			for(Resultado res : resultados)
			{	
				if(player1.getBounds().intersects(res.getBounds()))
				{
					proxFase = questao.get(Inventario.questaoAtual)
							.checarSolucao(res.getResposta());
					fase.mudarColisao(false);
					break;
				}
			}
			jogando = true;
			break;
			
		case KeyEvent.VK_SHIFT:
			if(Camera.multiplayer)
			{
				for(Resultado res : resultados)
				{	
					if(player2.getBounds().intersects(res.getBounds()))
					{
						proxFase = questao.get(Inventario.questaoAtual)
								.checarSolucao(res.getResposta());
						fase.mudarColisao(false);
						break;
					}
				}
				jogando = false;				
			}
			break;
		case KeyEvent.VK_LEFT:
			ControlePlayer.mover1="esquerda";
			break;
		case KeyEvent.VK_RIGHT:	
			ControlePlayer.mover1="direita";
			break;
		case KeyEvent.VK_A:
			ControlePlayer.mover2="esquerda";
			break;
		case KeyEvent.VK_D:
			ControlePlayer.mover2="direita";
			break;
		case KeyEvent.VK_SPACE:
			ControlePlayer.moverBloco = true;
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		int tecla = e.getKeyCode();
		
		if(tecla == KeyEvent.VK_RIGHT ||
				tecla == KeyEvent.VK_LEFT)
			ControlePlayer.mover1 = "";
		
		if(tecla == KeyEvent.VK_A ||
				tecla == KeyEvent.VK_D)
		ControlePlayer.mover2 = "";
		
		if(tecla == KeyEvent.VK_SPACE)
			ControlePlayer.moverBloco = false;
		
		if(player1.getLado() == 1)
			player1.setAparencia(0);
		else
			player1.setAparencia(5);
		
		if(player2.getLado() == 1)
			player2.setAparencia(0);
		else
			player2.setAparencia(5);
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}
	
	private class Atualizar extends Thread
	{		
		boolean emJogo = true;
		
		@Override
		public void run() {
				
			while(true)
			{
				try {
					
					if(emJogo)
						atualizarFase();
	
					sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
		
	}
	
}
