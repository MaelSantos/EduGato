package View;

public class Tela extends TelaGeral{
	
	private Menu menu;//jpanel
	private Ajuda ajuda;//jpanel
	private Creditos informacoes;//jpanel
	private Configuracoes configuracoes;//jpanel 
	private Inventario inventario;//jpanel
	private Assunto assunto;//panel
	private Entrar entrar;//panel
	private Professor professor;//panel
	private Fase fase;//jpanel implementa runnable
	
	public Tela(Menu menu, Ajuda ajuda, Creditos informacoes,Configuracoes configuracoes, Inventario inventario, 
			Assunto assunto, Entrar entrar,Professor professor, Fase fase) {

		this.menu = menu;
		this.ajuda = ajuda;
		this.informacoes = informacoes;
		this.configuracoes = configuracoes;
		this.inventario = inventario;
		this.assunto = assunto;
		this.entrar = entrar;
		this.professor = professor;
		this.fase = fase;
		
		inicializar();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		configurar();
	}

	public void inicializar() 
	{
		menu.setVisible(true);//jpanel inicial que da acesso aos outros
		
		//adiciono todos os jpanel no jframe
		add(menu);
		add(ajuda);
		add(informacoes);
		add(configuracoes);
		add(assunto);
		add(entrar);
		add(professor);
		add(inventario);
		add(fase);
		
	}
	
	@Override
	public void setTitle(String title) {
		
		super.setTitle("Force Is Power - "+title);//a tela sempre vai ter o nome do jogo
	}
	
}
