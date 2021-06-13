package ModelVo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Resultado {

	private int x, y;
	private int largura, altura;
	private String resposta;
	
	public Resultado(int x, int y, int largura, int altura)
	{
		this.x = x;
		this.y = y;
		this.largura = largura;
		this.altura = altura;
		
		resposta = "nada";

	}

	public void draw(Graphics2D g) {
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.BLACK);
		
		g.drawString(resposta, x+largura/4, y+altura/2);
	}

	//metodos de acesso
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, largura, altura);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
}
