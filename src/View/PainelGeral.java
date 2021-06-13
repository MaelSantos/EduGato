package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class PainelGeral extends JPanel {

	private BufferedImage fundo;
	private JLabel lblFundo;
	
	protected PainelGeral(String endereco,String nomeTela) {
		
		inicializar();
		
		try {
			fundo = ImageIO.read(getClass().getClassLoader().getResourceAsStream(endereco));
			lblFundo = new JLabel(new ImageIcon(fundo));
		} catch (Exception e) {
			System.out.println("Erro de inicializaçao - Endereço Errado!!!");
		}
				
		setName(nomeTela);
		setSize(Tela.LARGURA,Tela.ALTURA);
		setLayout(null);//defino o layout como nulo/tenho que dar setBounds em tudo
		setVisible(false);
		
//		add(lblFundo).setBounds(0,0,TelaGeral.LARGURA,TelaGeral.ALTURA);
	
	}
		
	public abstract void inicializar();
	public abstract void draw(Graphics2D g);
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D graficos = (Graphics2D) g;
		
		graficos.drawImage(fundo, 0, 0, null);

		draw(graficos);
		
		try {
			repaint();
			Thread.sleep(50);
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	//metodos de acesso
	public BufferedImage getFundo() {
		return fundo;
	}
}
