package View;

import java.awt.Graphics2D;

public class Entrar extends PainelGeral {

	private Botao btnAluno,btnProfessor,btnVoltar;
	
	public Entrar(String endereco, String nomeTela) {
		super(endereco, nomeTela);
		// TODO Stub de construtor gerado automaticamente
	}

	@Override
	public void inicializar() {
		btnAluno = new Botao(200, 300, "res/Botao Aluno.png");
		btnProfessor = new Botao(440, 300, "res/Botao Professor.png");
		btnVoltar = new Botao(50, 540, Botao.MENU);
		
		add(btnAluno);
		add(btnProfessor);
		add(btnVoltar);
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.drawImage(btnAluno.getImagem().getImage(), btnAluno.getX(), btnAluno.getY(), null);
		g.drawImage(btnProfessor.getImagem().getImage(), btnProfessor.getX(), btnProfessor.getY(), null);
		btnVoltar.draw(g);
		
	}
	
	//metodos de acesso
	public Botao getBtnAluno() {
		return btnAluno;
	}

	public Botao getBtnProfessor() {
		return btnProfessor;
	}

	public Botao getBtnVoltar() {
		return btnVoltar;
	}

}
