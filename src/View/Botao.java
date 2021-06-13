package View;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ModelVo.Sprite;

public class Botao extends JButton {

	private static ArrayList<ImageIcon> icones = Sprite.getSprite(1200, 1800, 8, 11,"res/Botoes.png");
//	private static Objeto objeto = new Objeto(1200, 1800, 8, 11,"res/Botoes.png");
		
	public static final int INICIAR = 0;
	public static final int AJUDA = 1;
	public static final int SAIR = 2;
	public static final int MAIS = 6;
	public static final int MENOS = 7;
	public static final int CONFIGURACOES = 9;
	public static final int PLAYER = 10;
	public static final int MULTIPLAYER = 54;
	public static final int MENU = 44;
	public static final int CREDITOS = 45;
	public static final int JOGAR = 48;
	public static final int CANCELAR = 50;
	public static final int CONFIRMAR = 51;
	
	private int icone;
	private ImageIcon imagem;//imagem a ser utilizada
	private int largura,altura;//dimensoes do botao
	private int x,y;//cordenadas do botao
	
	public Botao(int x, int y, int icone)
	{	
		//inicializo componentes
		this.x = x;
		this.y = y;
		this.icone = icone;
		
		largura = icones.get(icone).getIconWidth();
		altura = icones.get(icone).getIconHeight();
			
		//retiro as bordas para das impressao de redondo
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		
		//modifico icones 
		setIcon(icones.get(icone));
		setRolloverIcon(icones.get(icone+11));
		setPressedIcon(icones.get(icone+22));
		setDisabledIcon(icones.get(icone+33));
		
		//defino o tamanho e localizaçao dele
		setBounds(x, y, largura, altura);
		
		//defino sua visibilidae
		setVisible(true);
		
		addMouseListener(new MAdapter(icone));
			
	}
	
	public Botao(int x, int y, String endereco)
	{
		//inicializo componentes
		this.x = x;
		this.y = y;
		imagem = new ImageIcon(endereco);
		
		largura = imagem.getIconWidth();
		altura = imagem.getIconHeight();
			
		//retiro as bordas para das impressao de redondo
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		
		//modifico icones 
		setIcon(imagem);
		
		//defino o tamanho e localizaçao dele
		setBounds(x, y, largura, altura);
		
		//defino sua visibilidae
		setVisible(true);
	}
	
	public void draw(Graphics g) {
	
		g.drawImage(icones.get(icone).getImage(), x, y, null);//desenha o botao

	}
	
	private class MAdapter extends MouseAdapter
	{
		private int iconePrincipal;
		
		public MAdapter(int icone) {
			this.iconePrincipal = icone;
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Stub de método gerado automaticamente
			icone = iconePrincipal;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Stub de método gerado automaticamente
			icone += 11;
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Stub de método gerado automaticamente
			icone = iconePrincipal;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Stub de método gerado automaticamente
			icone += 11;
		}
	}

	//metodos de acesso
	public ImageIcon getImagem() {
		return imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
