package ModelVo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Sprite {

	private int larguraImg,alturaImg;
	
	protected BufferedImage personagem;
	protected int largura, altura;
	protected int linhas, colunas;
	protected int x, y;
	protected BufferedImage[] sprites;
	protected int aparencia;

	protected Sprite(int aparencia, int largura, int altura, int colunas, int linhas, int x, int y, String endereco) throws IOException {

		try {

			this.personagem = ImageIO.read(getClass().getClassLoader().getResourceAsStream(endereco));
			this.aparencia = aparencia;
			this.largura = largura;
			this.altura = altura;

			this.linhas = colunas;
			this.colunas = linhas;
			this.x = x;
			this.y = y;

			sprites = new BufferedImage[colunas * linhas];

			for (int i = 0; i < colunas; i++) {
				for (int j = 0; j < linhas; j++) {
					sprites[(i * linhas) + j] = personagem.getSubimage(i * (largura/colunas), 
							j * (altura/linhas), largura/colunas, altura/linhas);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Nao foi possivel carregar a Sprite");
		}
		
		larguraImg = sprites[0].getWidth();
		alturaImg = sprites[0].getHeight();
	}

	public static ArrayList<ImageIcon> getSprite(int largura, int altura, int colunas,
			int linhas, String endereco)
	{
		BufferedImage[] sprites = new BufferedImage[colunas * linhas];
		try {
			BufferedImage personagem = ImageIO.read(new File(endereco));

			for (int i = 0; i < colunas; i++) {
				for (int j = 0; j < linhas; j++) {
					sprites[(i * linhas) + j] = personagem.getSubimage(i * (largura/colunas), 
							j * (altura/linhas), largura/colunas, altura/linhas);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Nao foi possivel carregar a Sprite");
		}
		
		ArrayList<ImageIcon> icones = new ArrayList<>();
		
		for(BufferedImage img : sprites)
			icones.add(new ImageIcon(img));
//			icon.addAll((Collection<? extends ImageIcon>) img);
		
		return icones;
	}
	
	public abstract void animar(String direcao);
	
	public abstract void draw(Graphics2D g);
	
	public abstract void mover(String direcao);
	
	public void setLocale(int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	
	public BufferedImage getPersonagem() {
		return personagem;
	}

	public int getX() {
		return x;
	}

	public void setX(int posX) {
		this.x = posX;
	}

	public int getY() {
		return y;
	}

	public void setY(int posY) {
		this.y = posY;
	}

	public int getAparencia() {
		return aparencia;
	}

	public void setAparencia(int aparencia) {
		this.aparencia = aparencia;
	}
	
	public int getLarguraImg() {
		return larguraImg;
	}
	
	public int getAlturaImg() {
		return alturaImg;
	}
	
	public BufferedImage[] getSprites() {
		return sprites;
	}

}
