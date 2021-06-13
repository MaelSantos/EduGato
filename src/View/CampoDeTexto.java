package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JTextField;

public class CampoDeTexto extends JTextField {

	private static final int LARGURA = 220;
	private static final int ALTURA = 60;
	
	private int x,y;
	private String texto;
	
	public CampoDeTexto(int x, int y, String texto) {
		
		this.x = x;
		this.y = y;
		
		this.texto = texto;
		setToolTipText(texto);
		
		
		
		setBounds(x, y, LARGURA, ALTURA);
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setFont(new Font("Haettenschweiler",Font.LAYOUT_LEFT_TO_RIGHT, 34));
		setFocusable(true);
		setVisible(true);
	}
	
	public void draw(Graphics2D g)
	{
		g.setFont(new Font("Haettenschweiler",Font.LAYOUT_LEFT_TO_RIGHT, 34));
		g.setColor(Color.WHITE);
		g.fillRect(x, y-50, LARGURA-100, ALTURA);
		g.setColor(Color.BLACK);
		g.drawString(texto, x, y-10);
	
	}
	
	@Override
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, LARGURA, ALTURA);
	}
	
}

