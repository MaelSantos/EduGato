package ModelBo;

import View.Assunto;
import View.Professor;

public class Validar {

	/**
	 * @return true caso todos os campos estejam preenchidos
	 * -  false caso algum campo de texto esteja vazio
	 */
	public static boolean isCampoVazio(Professor professor)
	{
		if(professor.getTfdQuestao().getText().trim().isEmpty() ||
				professor.getTfdResposta().getText().trim().isEmpty() ||
				professor.getTfdErro1().getText().trim().isEmpty() ||
				professor.getTfdErro2().getText().trim().isEmpty() ||
				professor.getTfdErro3().getText().trim().isEmpty() ||
				professor.getTfdErro4().getText().trim().isEmpty() ||
				professor.getAssunto().getSelectedItem().toString().trim().isEmpty())
			return false;
		
		return true;
	}
	
	public static boolean isCampoVazioPlayer(Assunto assunto)
	{
		if(assunto.getTfdNome1().getText().trim().isEmpty())
			return false;
		
		return true;
	}
	
	public static boolean isCampoVazioMultiplayer(Assunto assunto)
	{
		if(assunto.getTfdNome1().getText().trim().isEmpty() ||
				assunto.getTfdNome2().getText().trim().isEmpty())
			return false;
		
		return true;
	}
	
}
