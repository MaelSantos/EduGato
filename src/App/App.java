package App;

import Controller.Controle;
import View.Ajuda;
import View.Assunto;
import View.Carregando;
import View.Configuracoes;
import View.Creditos;
import View.Entrar;
import View.Fase;
import View.Inventario;
import View.Mensagem;
import View.Menu;
import View.Professor;
import View.Tela;

public class App {

	public static void main(String[] args) {
		
		Carregando carregando = new Carregando();
		
		Menu menu = new Menu("Principal.jpeg", "Menu");

		carregando.getPgbProgresso().setValue(10);
		Ajuda ajuda = new Ajuda("Ajuda.png", "Ajuda");

		carregando.getPgbProgresso().setValue(20);
		Creditos creditos = new Creditos("Informaçoes.jpg", "Créditos");

		carregando.getPgbProgresso().setValue(30);
		Configuracoes configuracoes = new Configuracoes("Principal.jpeg", "Configurações");
		
		carregando.getPgbProgresso().setValue(40);
		Inventario inventario = new Inventario("Inventario.png","Inventario2.png", "Inventario");

		carregando.getPgbProgresso().setValue(50);
		Assunto assunto = new Assunto("Assunto.jpg", "Assunto");
		
		carregando.getPgbProgresso().setValue(60);
		Entrar entrar = new Entrar("Principal.jpeg", "Entrar");
		
		carregando.getPgbProgresso().setValue(70);
		Professor professor = new Professor("Assunto.jpg", "Professor");
		
		carregando.getPgbProgresso().setValue(80);
		Fase fase = new Fase();
		
		carregando.getPgbProgresso().setValue(90);
		Tela tela = new Tela(menu, ajuda, creditos, configuracoes, inventario, assunto, entrar,professor,fase);
		Mensagem mensagem = new Mensagem(tela, "Mensagem", "Mensagem.png");

		carregando.getPgbProgresso().setValue(100);
		Controle controle = new Controle(tela, menu, fase, ajuda, configuracoes, creditos, inventario, assunto, entrar, professor, mensagem);
		
		carregando.setVisible(false);
		carregando.dispose();
	}

}
