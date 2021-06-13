package View;

import java.awt.Graphics2D;

import javax.swing.JButton;

public class Creditos extends PainelGeral{

	private Botao btnVoltar;
	
	public Creditos(String endereco,String nomeTela) {
		
		super(endereco,nomeTela);
	
	}

	@Override
	public void inicializar() {
		
		btnVoltar = new Botao(650, 540, Botao.MENU);
		
		add(btnVoltar);
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		btnVoltar.draw(g);
	}
	
	public JButton getBtnVoltar() {
		return btnVoltar;
	}
	
}
