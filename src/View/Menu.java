package View;

import java.awt.Graphics2D;

public class Menu extends PainelGeral{

	private Botao btnIniciar;
	private Botao btnAjuda;
	private Botao btnInfo;
	private Botao btnConfiguracoes;
	private Botao btnSair;
	
	public Menu(String endereco,String nomeTela) {
		super(endereco, nomeTela);
		
	}

	public void inicializar()
	{
		//inicializo os botoes
		btnIniciar = new Botao(10, 500, Botao.INICIAR);
				
		btnAjuda = new Botao(170, 500, Botao.AJUDA);
		
		btnInfo = new Botao(330, 500, Botao.CREDITOS);
		
		btnConfiguracoes = new Botao(490, 500, Botao.CONFIGURACOES);
		
		btnSair = new Botao(650, 500, Botao.SAIR);
		
		//adiciono os componetes do menu
		add(btnSair);
		add(btnConfiguracoes);
		add(btnInfo);
		add(btnAjuda);
		add(btnIniciar);
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		btnIniciar.draw(g);
		btnAjuda.draw(g);
		btnInfo.draw(g);
		btnConfiguracoes.draw(g);
		btnSair.draw(g);
		
	}
	
	//metodos de acesso
	public Botao getBtnIniciar() {
		return btnIniciar;
	}

	public Botao getBtnAjuda() {
		return btnAjuda;
	}

	public Botao getBtnInfo() {
		return btnInfo;
	}

	public Botao getBtnSair() {
		return btnSair;
	}

	public Botao getBtnConfiguracoes() {
		return btnConfiguracoes;
	}
		
}
