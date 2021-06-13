package ModelVo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Questao")
public class Questao {

	private String questao;
	private String resposta;
	
	private String erro1, erro2, erro3, erro4;

	public Questao(String questao, String resposta, String erro1, String erro2, String erro3, String erro4) {
		
		this.questao = questao;
		this.resposta = resposta;
		this.erro1 = erro1;
		this.erro2 = erro2;
		this.erro3 = erro3;
		this.erro4 = erro4;
	}

	public boolean checarSolucao(String escolha)
	{
		if(resposta.equalsIgnoreCase(escolha))
			return true;
		
		return false;
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 21));
		
		if(questao.length() < 37)
			g.drawString(questao, 5, 440);
		else if(questao.length() >= 37 && questao.length() <= 74)
		{
			String nova1 = questao.substring(0, 37);
			String nova2 = questao.substring(37, questao.length());
			g.drawString(nova1+"...", 5, 440);
			g.drawString(nova2, 5, 460);
		}
		else if(questao.length() > 74)
		{
			String nova1 = questao.substring(0, 37);
			String nova2 = questao.substring(37, 74);
			String nova3 = questao.substring(74, questao.length());
			g.drawString(nova1+"...", 5, 440);
			g.drawString(nova2+"...", 5, 460);
			g.drawString(nova3, 5, 480);
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Questao) {
			Questao quest = (Questao) obj;
			return quest.getQuestao().trim().equalsIgnoreCase(questao.trim());
		}
		return false;
	}
	
	public String getQuestao() {
		return questao;
	}

	public String getResposta() {
		return resposta;
	}

	public String getErro1() {
		return erro1;
	}

	public String getErro2() {
		return erro2;
	}

	public String getErro3() {
		return erro3;
	}

	public String getErro4() {
		
		return erro4;
	}
	
}

