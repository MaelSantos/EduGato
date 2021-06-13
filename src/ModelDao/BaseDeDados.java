package ModelDao;

import java.io.OutputStream;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_MULTIPLYPeer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import ModelVo.Jogador;
import ModelVo.Questao;

public class BaseDeDados {

	//utilizo a API XStream para manipula�ao de xml
	private static XStream xstream = new XStream(new DomDriver());

	/**
	 * @return Arraylist Questoes salvo no xml
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Questao> buscarQuestoes(String caminho)
	{	
		File file = new File(caminho);//arquivo do xml
		
		try {
			if(!file.exists())
				file.createNewFile();
			else
			{
				xstream.alias("Questao", Questao.class);
				return (ArrayList<Questao>) xstream.fromXML(file); // retorna os dados do xml para o objeto				
			}

		} catch (Exception e) {
			System.out.println("Erro - Buscar Quest�es: "+e.getMessage()); //se der erro retorna null
		}
		return null;
	}
	
	/**
	 * @return String salva no xml 
	 */
	
	public static int buscarRank(String caminho)
	{	
		File file = new File(caminho);//arquivo do xml
		
		if(!file.exists())//se nao ele existir
			salvarRank("0", caminho);//entao eu crio um novo arquivo xml 	
		else
			try {
				xstream.alias("String", String.class);
				return  Integer.parseInt(""+xstream.fromXML(file)) ; // retorna os dados do xml para o objeto

			} catch (Exception e) {
				System.out.println("Erro - Buscar Rank: "+e.getMessage());
				return -1; //se der erro retorna -1
		}
		return 0;//se retorna 0 significa que ele somente crio o xml
	}

	/**
	 * @return String salva no xml
	 */
	public static String salvarRank(String temp, String caminho)
	{
		try {
			File xmlMap = new File(caminho);//arquivo do xml
			OutputStream streamOut = null;
			if (!(xmlMap.exists()))//se o arquivo nao existir  
				xmlMap.createNewFile();//cria um novo arquivo
			else {//se nao sobrescrevo em cima do arquivo existente 
				xmlMap.delete();//apago o arquivo
				xmlMap.createNewFile();//e crio um novo arquivo
			}		
			streamOut = new FileOutputStream(xmlMap);//salvo os dados

			xstream.toXML(temp, streamOut);//pega os dados do objeto e salva no aquivo
			
		} catch (Exception e) {
			return "Erro - Salvar Rank: "+e.getMessage();
		}
		return temp; //retorno uma string para pre visualiza�ao
		
	}

	/**
	 * @return String salva no xml
	 */
	@SuppressWarnings("unchecked")
	public static String salvarQuestao(Questao obj, String caminho)
	{
		ArrayList<Questao> temps = new ArrayList<>();
		xstream.alias("Questao", Questao.class);
		try {
			File xmlMap = new File(caminho); //cria um arquivo
			OutputStream streamOut = null;
			if (!(xmlMap.exists()))//se o arquivo nao existir  
				xmlMap.createNewFile();//cria um novo arquivo
			else {
				temps = (ArrayList<Questao>) xstream.fromXML(xmlMap);//pego os dados guardados no xml
				xmlMap.delete();//apago o arquivo
				xmlMap.createNewFile();//e crio um novo arquivo
			}		
			streamOut = new FileOutputStream(xmlMap);	

			temps.add(obj);//adiciona o novo dado no array
			xstream.toXML(temps, streamOut);//pega os dados do objeto e salva no aquivo

		} catch (Exception e) {
			return "Erro - Salvar Quest�o: "+e.getMessage();
		}
		return temps.toString(); //retorno uma string para pre visualiza�ao
		
	}

	@SuppressWarnings("unchecked")
	public static void salvarJogador(Jogador jogador)
	{
		ArrayList<Jogador> jogadores = new ArrayList<>();
		ArrayList<Jogador> temp = new ArrayList<>();
		
		try {
			xstream.alias("Jogador", Jogador.class);
			xstream.alias("Questao", Questao.class);
			File xmlMap = new File("res/jogadores.xml");//arquivo do xml
			OutputStream streamOut = null;
			if (!(xmlMap.exists()))//se o arquivo nao existir  
				xmlMap.createNewFile();//cria um novo arquivo
			else {//se nao sobrescrevo em cima do arquivo existente 
				temp = (ArrayList<Jogador>) xstream.fromXML(xmlMap);				
				
				ArrayList<String> nomes = new ArrayList<>();
				boolean add = true;
				int indice = 0;
				
				for(Jogador j : temp)
					nomes.add(j.getNome());
				for(String s : nomes)
				{
					if(jogador.getNome().equalsIgnoreCase(s))
						add = false;
						
					if(add)
						jogadores.add(temp.get(indice));
					else
					{   	
						jogador.setAcertos(
						concatenarListas(temp.get(indice).getAcertos(), jogador.getAcertos()));
						
						jogador.setErros(
								concatenarListas(jogador.getErros(), temp.get(indice).getErros()));
					}
					indice++;
					add = true;
				}
				xmlMap.delete();//apago o arquivo
				xmlMap.createNewFile();//e crio um novo arquivo
			}		
			
			streamOut = new FileOutputStream(xmlMap);//salvo os dados
			jogadores.add(jogador);
			xstream.toXML(jogadores, streamOut);//pega os dados do objeto e salva no aquivo
		} catch (IOException e) {
			System.out.println("Erro - Salvar Jogador: "+e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Jogador> buscarJogador()
	{	
		File file = new File("res/jogadores.xml");//arquivo do xml
		
		try {
		if(!file.exists())
			file.createNewFile();
		else
		{
			xstream.alias("Jogador", Jogador.class);
			xstream.alias("Questao", Questao.class);
			return  (ArrayList<Jogador>) xstream.fromXML(file); // retorna os dados do xml para o objeto
		}
		
		} catch (Exception e) {
			System.out.println("Erro - Buscar Jogador: "+e.getMessage());
		}
	return null;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean salvarAssunto(String assunto)
	{
		ArrayList<String> assuntos = new ArrayList<>();
		ArrayList<String> temp = null;
		boolean add = true;
		
		try {
			xstream.alias("String", String.class);
			File xmlMap = new File("res/assuntos.xml");//arquivo do xml
			OutputStream streamOut = null;
			if (!(xmlMap.exists()))//se o arquivo nao existir  
				xmlMap.createNewFile();//cria um novo arquivo
			else {//se nao sobrescrevo em cima do arquivo existente 
				temp = (ArrayList<String>) xstream.fromXML(xmlMap);				
				
				for(String s : temp)
					assuntos.add(s);
				xmlMap.delete();//apago o arquivo
				xmlMap.createNewFile();//e crio um novo arquivo
			}		
			
			for(String s: assuntos)
				if((assunto.equalsIgnoreCase(s)))
				{
					add = false;
				}
			if(add)
				assuntos.add(assunto);	
			streamOut = new FileOutputStream(xmlMap);//salvo os dados
			xstream.toXML(assuntos, streamOut);//pega os dados do objeto e salva no aquivo
		} catch (Exception e) {
			System.out.println("Erro - Salvar Assunto: "+e.getMessage());
		}		

		return add;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> buscarAssunto()
	{	
		File file = new File("res/assuntos.xml");//arquivo do xml
		
		try {
		if(!file.exists())
			file.createNewFile();
		else
		{
			xstream.alias("String", String.class);
			return  (ArrayList<String>) xstream.fromXML(file); // retorna os dados do xml para o objeto
		}
		
		} catch (Exception e) {
			System.out.println("Erro - Buscar Assunto: "+e.getMessage());
		}
		return null;
	}
	
	public static ArrayList<Questao> concatenarListas(ArrayList<Questao> primeiraLista, ArrayList<Questao> segundaLista) {
				
	    ArrayList<Questao> ret = new ArrayList<>();
	    for (Questao elementoLista : primeiraLista) {
	        if (! (ret.contains(elementoLista))) {
	            ret.add(elementoLista);
	        }
	    }
	    for (Questao elementoLista : segundaLista) {
	        if (! (ret.contains(elementoLista))) {
	            ret.add(elementoLista);
	        }
	    }
	    return ret;
	}
}

