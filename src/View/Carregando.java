package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Carregando extends TelaGeral{

	private JProgressBar pgbProgresso;
	private JLabel lblCarregar;
	
	public Carregando() {
		super();
		
		inicializar();
		configurar();
		setSize(500,500);		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void inicializar() {
		
		pgbProgresso = new JProgressBar(0, 100);
		pgbProgresso.setForeground(Color.GREEN);
		
		lblCarregar = new JLabel("Carregando...");
		lblCarregar.setFont(new Font("Arial", Font.BOLD,28));
		
		add(pgbProgresso).setBounds(20, 400, 460, 50);
		add(lblCarregar).setBounds(20, 300, 200, 100);
		
	}
	
	//metodos de acesso
	public JProgressBar getPgbProgresso() {
		return pgbProgresso;
	}
	
}
