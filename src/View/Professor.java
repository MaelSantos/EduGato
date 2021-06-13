package View;

import java.awt.Graphics2D;

import javax.swing.JComboBox;

import ModelDao.BaseDeDados;

public class Professor extends PainelGeral {

	private Botao btnCancelar;
	private Botao btnConfirmar;
	
	private CampoDeTexto tfdQuestao,tfdResposta,tfdErro1,tfdErro2,tfdErro3,tfdErro4;
	private JComboBox<String> assunto;
	
	public Professor(String endereco, String nomeTela) {
		super(endereco, nomeTela);
	}

	@Override
	public void inicializar() {
		
		tfdQuestao = new CampoDeTexto(300, 90, "Questão");
		tfdResposta = new CampoDeTexto(300, 190, "Resposta");
		tfdErro1 = new CampoDeTexto(300, 290, "Erro 1");
		tfdErro2 = new CampoDeTexto(300, 390, "Erro 2");
		tfdErro3 = new CampoDeTexto(300, 490, "Erro 3");
		tfdErro4 = new CampoDeTexto(300, 590, "Erro 4");
		
		btnCancelar = new Botao(50, 540, Botao.CANCELAR);
		btnConfirmar = new Botao(600, 540, Botao.CONFIRMAR);	

		assunto = new JComboBox<>();
		
		for(String s : BaseDeDados.buscarAssunto())
			assunto.addItem(s);		
		
		assunto.setEditable(true);
		
		//botoes
		add(btnCancelar);
		add(btnConfirmar);
		
		//campos de texto
		add(tfdQuestao);
		add(tfdResposta);
		add(tfdErro1);
		add(tfdErro2);
		add(tfdErro3);
		add(tfdErro4);
		
		add(assunto).setBounds(270, 10, 300, 40);
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		btnCancelar.draw(g);
		btnConfirmar.draw(g);
		
		tfdQuestao.draw(g);
		tfdResposta.draw(g);
		tfdErro1.draw(g);
		tfdErro2.draw(g);
		tfdErro3.draw(g);
		tfdErro4.draw(g);
	}
			
	public void limparCampo()
	{
		tfdQuestao.setText("");
		tfdResposta.setText("");
		tfdErro1.setText("");
		tfdErro2.setText("");
		tfdErro3.setText("");
		tfdErro4.setText("");
	}

	//metodos de acesso
	public Botao getBtnCancelar() {
		return btnCancelar;
	}

	public Botao getBtnConfirmar() {
		return btnConfirmar;
	}

	public CampoDeTexto getTfdQuestao() {
		return tfdQuestao;
	}

	public CampoDeTexto getTfdResposta() {
		return tfdResposta;
	}

	public CampoDeTexto getTfdErro1() {
		return tfdErro1;
	}

	public CampoDeTexto getTfdErro2() {
		return tfdErro2;
	}

	public CampoDeTexto getTfdErro3() {
		return tfdErro3;
	}

	public CampoDeTexto getTfdErro4() {
		return tfdErro4;
	}

	public JComboBox<String> getAssunto() {
		return assunto;
	}
	
}
