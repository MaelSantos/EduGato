package View;

import java.awt.Graphics2D;

import javax.swing.JButton;

public class Ajuda extends PainelGeral{

	private Botao btnVoltar;
	
	public Ajuda(String endereco,String telaNome)
	{
		super(endereco,telaNome);			
	}

	@Override
	public void inicializar() {
	
		btnVoltar = new Botao(25, 540, Botao.MENU);
		
		add(btnVoltar);
				
	}
	
	@Override
	public void draw(Graphics2D g) {
		btnVoltar.draw(g);
	}
	
	
	/**
	 * @return the Button voltar
	 */
	public JButton getBtnVoltar() {
		return btnVoltar;
	}

}
