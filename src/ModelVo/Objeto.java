package ModelVo;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import View.Fase;

public class Objeto extends Sprite{

	private int lado = 1;
	public final static int VELOCIDADE = 3;
	
	public Objeto(int aparencia, int largura, int altura, int colunas, int linhas, int posX, int posY,
			String endereco) throws IOException {
		super(aparencia, largura, altura, colunas, linhas, posX, posY, endereco);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void animar(String direcao) {
		if(!(direcao.equals("")))
		{
			aparencia += 1;
			
			switch (direcao) {
			
			case "esquerda":
				
				if(aparencia < 4 || aparencia > 9)
				{
					aparencia = 5;
				}
				break;
			case "direita":
				
				if(aparencia > 4)
				{
					aparencia = 0;
				}
				break;
			}			
		}
	}
	
	public void mover(String direcao)
	{
		switch (direcao) {
		
		case "baixo": //direita
		
			if(getY() < 2800)
			{
				setY(getY() + VELOCIDADE+2);
			}

			break;
		
		case "cima":
			if(getY() > 0)
			{
				setY(getY() - VELOCIDADE);
			}
			
			break;
			
		case "esquerda": //esquerda

			if(getX() > -20)
			{
				setX(getX() - VELOCIDADE);
			}
			
			lado = 0;
			
			break;
		case "direita": //direita

			if(getX() < 990)
			{
				setX(getX() + VELOCIDADE);
			}
			
			lado = 1;
			
			break;
		}
//		System.out.println("X = "+getX()+" Y = "+getY());
	}
	
	@Override
	public void draw(Graphics2D g) {
				
		g.drawImage(getSprites()[getAparencia()], getX(), getY(), null);
		
//		g.setColor(Color.RED);
//		g.draw(this.getBounds());

	}

	public boolean collision(ArrayList<Rectangle> tmp, int x,int y) {
		Rectangle personagem = new Rectangle(getX()+x+5, getY()+y, getLarguraImg()-10, getAlturaImg()-5);
		for(Rectangle rectangle : tmp) {
			if(rectangle.intersects(personagem)){
				return true;
			}
		}
		return false;
	
	}
	
	@Override
	public void setX(int x) {
	if(!collision(Fase.retangulosColid,x-this.x,0))
		this.x = x;
	
	}
	
	@Override
	public void setY(int y) {
		if(!collision(Fase.retangulosColid,0,y-this.y))
			this.y = y;
	}

	
	//metodos de acesso

	public int getLado() {
		return lado;
	}

	public void setLado(int lado) {
		this.lado = lado;
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x+5, y, getLarguraImg()-10, getAlturaImg()-5);
	}
	
}
