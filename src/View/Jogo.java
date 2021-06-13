package View;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public abstract class Jogo extends JPanel implements Runnable{

    protected BufferedImage fundo;
    protected Graphics2D graficos;
    protected Thread thread;
    private final int FPS = 30;
    private Double averageFPS;
    protected boolean loop;
      
	public Jogo() {
		
		requestFocus();
		setSize(Tela.LARGURA, Tela.ALTURA);
		setLayout(null);
		
		loop = false;
	
	} 
	
	@Override
	public void addNotify() {
		super.addNotify();
		if(thread == null)
		{
			thread =  new Thread(this);
			thread.start();
			
		}
	}
	
	private void gameDraw() {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		g2.drawImage(fundo, 0,0,null);
		g2.dispose();
	}
	
	public abstract void draw();
	public abstract void inicializar();
	public abstract void atualizar();
	
	@Override
	public void run() {

		inicializar();
		requestFocus();
		
		fundo = new BufferedImage(Tela.LARGURA, Tela.ALTURA, BufferedImage.TYPE_4BYTE_ABGR);
		graficos = (Graphics2D) fundo.getGraphics();
		
//		long startTime;
//		long URDTimeMillis;
//		long waitTime;
//		long totalTime = 0;
//
//		int frameCount = 0;
//		int maxFrameCount = 30;
//
//		long tragetTime = 1000 / FPS;
		
		while(true)
		{		
//			startTime = System.nanoTime();
			
			if(loop)
			{
				atualizar();
				draw();
				gameDraw();					
			}
			
//			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
//			waitTime = tragetTime - URDTimeMillis;
			
			try {
				Thread.sleep(Configuracoes.velocidadePintura);
			} catch (Exception e) {
			}
			
//			totalTime += System.nanoTime() - startTime;
//			frameCount++;
//
//			if (frameCount == maxFrameCount) {
//				averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
//				frameCount = 0;
//				totalTime = 0;
//			}
//			gameDraw();
		}

		
	}

	public Thread getThread() {
		return thread;
	}

	/**
	 * @return o loop do jogo
	 */
	public boolean isLoop() {
		return loop;
	}

	/**
	 * @param loop o jogo a ser configurado
	 */
	public void setLoop(boolean loop) {
		this.loop = loop;
	}
}
