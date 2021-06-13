package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JProgressBar;

public class Configuracoes extends PainelGeral{

	public static int velocidadePintura = 30;
	public static int velocidadePlayer = 30;
	
	private Botao btnMenosPintura;
	private Botao btnMaisPintura;
	private Botao btnMenosPlayer;
	private Botao btnMaisPlayer;
	private Botao btnVoltar;
	
	private JProgressBar pgbVelocidadePlayer;
	private JProgressBar pgbVelocidadePintura;
	
	public Configuracoes(String endereco, String nomeTela) {
		super(endereco, nomeTela);
		// TODO Stub de construtor gerado automaticamente
	}

	@Override
	public void inicializar() {
		
		btnMenosPintura = new Botao(100, 100, Botao.MENOS);
		btnMaisPintura = new Botao(600, 100, Botao.MAIS);
		
		btnMenosPlayer = new Botao(100, 300, Botao.MENOS);
		btnMaisPlayer = new Botao(600, 300, Botao.MAIS);
		
		btnVoltar = new Botao(25, 540, Botao.MENU);
		
		pgbVelocidadePlayer = new JProgressBar(0, 100);
		pgbVelocidadePlayer.setValue(velocidadePlayer);
		pgbVelocidadePlayer.setForeground(Color.GREEN);
		
		pgbVelocidadePintura = new JProgressBar(0, 100);
		pgbVelocidadePintura.setValue(velocidadePintura);
		pgbVelocidadePintura.setForeground(Color.GREEN);
		
		add(btnMenosPintura);
		add(btnMaisPintura);
		add(btnMenosPlayer);
		add(btnMaisPlayer);		
		add(btnVoltar);
		
		add(pgbVelocidadePintura).setBounds(240, 165, 370, 50);
		add(pgbVelocidadePlayer).setBounds(240, 365, 370, 50);
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.setFont(new Font("Haettenschweiler",Font.LAYOUT_LEFT_TO_RIGHT, 34));
		g.setColor(Color.WHITE);
		g.drawString("VELOCIDADE DE PINTURA: "+velocidadePintura, 240, 140);
		btnMenosPintura.draw(g);
		btnMaisPintura.draw(g);
		g.drawString("VELOCIDADE DO PLAYER: "+velocidadePlayer, 240, 340);
		btnMenosPlayer.draw(g);
		btnMaisPlayer.draw(g);
		btnVoltar.draw(g);
		
	}

	//metodos de acesso
	public Botao getBtnMenosPintura() {
		return btnMenosPintura;
	}

	public Botao getBtnMaisPintura() {
		return btnMaisPintura;
	}

	public Botao getBtnVoltar() {
		return btnVoltar;
	}

	public Botao getBtnMenosPlayer() {
		return btnMenosPlayer;
	}

	public Botao getBtnMaisPlayer() {
		return btnMaisPlayer;
	}

	public JProgressBar getPgbVelocidadePlayer() {
		return pgbVelocidadePlayer;
	}

	public JProgressBar getPgbVelocidadePintura() {
		return pgbVelocidadePintura;
	}

}
