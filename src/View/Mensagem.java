package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mensagem extends JDialog{

	public static final int YES = 1;
	public static final int NO = 2;
	
	private String mensagem = "Nada";
	private int escolha;
	
	private Botao btnConfirmar;
	private Botao btnCancelar;
	private BufferedImage fundo;
	private Graphics2D gg;
	private JPanel painel;
	private JLabel label;
	
	public Mensagem(Frame owner, String title, String endereco) {
		super(owner, title);
		
		try {
			fundo = ImageIO.read(getClass().getClassLoader().getResourceAsStream(endereco));
		} catch (IOException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
		
		gg = fundo.createGraphics();
		
		inicializar();
		
		setModal(true);
		setBounds(100,150,600,300);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(false);
		
	}
	
//	@Override
	public void inicializar() {
		
		btnConfirmar = new Botao(450, 140, Botao.CONFIRMAR);
		btnCancelar = new Botao(0, 140, Botao.CANCELAR);
		btnCancelar.setVisible(false);
		
		btnConfirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				escolha = YES;
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				escolha = NO;
			}
		});
		
		painel = new JPanel();
		painel.setLayout(null);
		painel.setBounds(100,150,600,300);
		
		label = new JLabel(new ImageIcon(fundo));
		painel.add(btnConfirmar);
		painel.add(btnCancelar);
		painel.add(label).setBounds(0,0,600,300);
		
		add(painel);
		
	}

	public void draw(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Haettenschweiler",Font.LAYOUT_LEFT_TO_RIGHT, 34));
		
		if(mensagem.length() < 40)
			g.drawString(mensagem, 60, 100);
		else
		{
			g.drawString(mensagem, 30, 100);
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);
		draw((Graphics2D) g);
	}
	
	public void On()
	{
		setVisible(true);
		setFocusable(true);
		requestFocus();
		requestFocusInWindow();
	}
	
	public void exibirMensagem(String mensagem)
	{
		this.mensagem = mensagem;
		btnCancelar.setVisible(false);
		On();
		
	}

	public int mensagemConfirmar(String mensagem)
	{
		this.mensagem = mensagem;
		btnCancelar.setVisible(true);
		On();
		
		if(btnConfirmar.isSelected())
			return YES;
		else if(btnCancelar.isSelected())
			return NO;		
		return -1;
	}

	
	public Botao getBtnConfirmar() {
		return btnConfirmar;
	}

	public Botao getBtnCancelar() {
		return btnCancelar;
	}

	public int getEscolha() {
		return escolha;
	}
	
}
