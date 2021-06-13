package ModelVo;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Jogador")
public class Jogador {
	
	private String nome;
	private int pontos;
	private int vida = 100;
	
	private ArrayList<Questao> acertos;
	private ArrayList<Questao> erros;
	
	public Jogador(String nome, int pontos) {
		this.nome = nome;
		this.pontos = pontos;
		this.acertos = new ArrayList<>();
		this.erros = new ArrayList<>();
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public ArrayList<Questao> getAcertos() {
		return acertos;
	}

	public void setAcertos(ArrayList<Questao> acertos) {
		this.acertos = acertos;
	}

	public ArrayList<Questao> getErros() {
		return erros;
	}

	public void setErros(ArrayList<Questao> erros) {
		this.erros = erros;
	}
	
}
