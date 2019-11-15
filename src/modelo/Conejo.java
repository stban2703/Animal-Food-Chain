package modelo;

import processing.core.PApplet;
import processing.core.PVector;

public class Conejo extends Animal implements Runnable {

	private PVector direccion;
	private int puntoEncuentroX;
	private int puntoEncuentroY;

	public Conejo(String tipo, float posX, float posY, float ancho, float alto, String estado, int edad, String sexo,
			int velocidad, boolean encuentro, PApplet app) {
		super(tipo, posX, posY, ancho, alto, estado, edad, sexo, velocidad, encuentro, app);
		new Thread(this).start();
		this.puntoEncuentroX = app.width - 53;// (int) this.app.random(53, 947);
		this.puntoEncuentroY = app.height - 53;// (int) this.app.random(53, 547);

	}

	public void run() {
		while (estado.equals("vivo")) {
			mover();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void mover() {
		//this.direccion = new PVector(this.puntoEncuentroX - this.posX, this.puntoEncuentroY - this.posY);
		//this.direccion.normalize();

		/*this.direccion = new PVector(this.puntoEncuentroX - this.posX, this.puntoEncuentroY - this.posY);
		this.direccion.normalize();

		this.posX += (direccion.x * this.velocidad) * this.dirX;
		this.posY += (direccion.y * this.velocidad) * this.dirY;*/
		
		/*if(this.posX < 60) {
			this.puntoEncuentroX = app.width - 53;
		}

		if (this.posX > app.width - 60) {
			this.puntoEncuentroX = 53;
		}
		
		if(this.posY < 60) {
			this.puntoEncuentroY = app.height - 53;
		}
		
		if(this.posY > app.height - 60) {
			this.puntoEncuentroY = 53;
		}*/
		/*
		 * if (this.posX >= 1000 - 53 || this.posX <= 0 + 53) { this.dirX *= -1; }
		 * 
		 * if (this.posY >= 600 - 53 || this.posY <= 112 + 53) { this.dirY *= -1; }
		 */

	}

}
