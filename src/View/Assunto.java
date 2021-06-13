package View;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComboBox;

import ModelDao.BaseDeDados;

public class Assunto extends PainelGeral{

	private Botao btnVoltar;
	private Botao btnPlayer;
	private Botao btnMultiplayer;
	
	private CampoDeTexto tfdNome1,tfdNome2;
	
	private JComboBox<String> cbbAssutos;
	
	public Assunto(String endereco, String nomeTela) {
		super(endereco, nomeTela);
		
	}
	
	public void inicializar()
	{
		btnVoltar = new Botao(50, 540, Botao.MENU);
		btnPlayer = new Botao(100, 350, Botao.PLAYER);
		btnMultiplayer = new Botao(500, 350, Botao.MULTIPLAYER);;
		
		tfdNome1 = new CampoDeTexto(100, 100, "Player 1: ");
		tfdNome2 = new CampoDeTexto(500, 100, "Player 2: ");
		
		cbbAssutos = new JComboBox<>();
		for(String s : BaseDeDados.buscarAssunto())
			cbbAssutos.addItem(s);
		
		add(btnPlayer);
		add(btnMultiplayer);
		add(btnVoltar);
		
		add(tfdNome1);
		add(tfdNome2);
		
		add(cbbAssutos).setBounds(250, 200, 300, 40);
	}

	@Override
	public void draw(Graphics2D g) {
		
		btnPlayer.draw(g);
		btnMultiplayer.draw(g);
		btnVoltar.draw(g);
		
		tfdNome1.draw(g);
		tfdNome2.draw(g);
	}
	
	//metodos de acesso
	public Botao getBtnVoltar() {
		return btnVoltar;
	}

	public CampoDeTexto getTfdNome1() {
		return tfdNome1;
	}
	
	public CampoDeTexto getTfdNome2() {
		return tfdNome2;
	}

	public JComboBox<String> getCbbAssutos() {
		return cbbAssutos;
	}

	public Botao getBtnPlayer() {
		return btnPlayer;
	}

	public Botao getBtnMultiplayer() {
		return btnMultiplayer;
	}
	
}
