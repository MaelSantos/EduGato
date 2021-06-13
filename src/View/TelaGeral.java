package View;

import javax.swing.JFrame;

public abstract class TelaGeral extends JFrame{

	//constantes estaticas para utilzaçao dos jpanel 
	public static final int LARGURA = 800;
	public static final int ALTURA = 700;
	
	public TelaGeral() {
		
//		inicializar();
//		configurar();
		
	}
	
	public abstract void inicializar();
	
	public void configurar()
	{
		setSize(LARGURA,ALTURA);//defino o tamanho da tela
		
		setResizable(false);//restrinjo o redimensionamento da tela
		 
		setUndecorated(true);
		
		setLayout(null);//tem que dar setBounds em todos os jpanel
		
		setLocationRelativeTo(null);//a tela começa no centro 
					
		setVisible(true);//a tela começa visivel
		
	}
	
}
