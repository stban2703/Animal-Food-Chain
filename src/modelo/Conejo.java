package modelo;

import processing.core.PApplet;
import processing.core.PVector;

public class Conejo extends Animal implements Runnable {

	private float puntoEncuentroX;
	private float puntoEncuentroY;

	public Conejo(String tipo, float posX, float posY, float ancho, float alto, String estado, int edad, String sexo,
			int velocidad, boolean encuentro, PApplet app) {
		super(tipo, posX, posY, ancho, alto, estado, edad, sexo, velocidad, encuentro, app);

		new Thread(this).start();
		
		this.puntoEncuentroX = 1;
		this.puntoEncuentroY = 1;

	}

	public void run() {
		while (this.estaVivo == true) {

			if (this.estado.equals("vivo")) {
				mover();
			}

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public void mover() {

		/*
		 * try { this.direccion = new PVector((app.noise(this.puntoEncuentroX) *
		 * app.width) - this.posX, (app.noise(this.puntoEncuentroY) * app.height) -
		 * this.posY); this.direccion.normalize();
		 * 
		 * this.puntoEncuentroX += 0.010f; this.puntoEncuentroY += 0.006f; /*
		 * this.direccion = new PVector(this.puntoEncuentroX - this.posX,
		 * this.puntoEncuentroY - this.posY); this.direccion.normalize();
		 *
		 * 
		 * this.posX += (direccion.x * this.velocidad); // * (this.velocidad));
		 * this.posY += (direccion.y * this.velocidad); // * (this.velocidad));
		 * 
		 * } catch (ArithmeticException e) { e.printStackTrace(); }
		 */

	}

}
