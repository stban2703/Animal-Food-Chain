package modelo;

import processing.core.PApplet;

public class Buitre extends Animal implements Runnable {

	public Buitre(String tipo, float posX, float posY, float ancho, float alto, String estado, int edad, String sexo,
			int velocidad, boolean encuentro, PApplet app) {
		super(tipo, posX, posY, ancho, alto, estado, edad, sexo, velocidad, encuentro, app);

		new Thread(this).start();

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
		 * this.posX += this.velocidad * this.dirX; this.posY += this.velocidad *
		 * this.dirY;
		 * 
		 * if (this.posX >= app.width - this.ancho || this.posX <= 0 + this.ancho) {
		 * this.dirX *= -1; }
		 * 
		 * if (this.posY >= app.height - this.alto || this.posY <= 0 + this.alto) {
		 * this.dirY *= -1; }
		 */

		this.posX += app.random(-1, 1) * this.velocidad;
		this.posY += app.random(-1, 1) * this.velocidad;

		if (this.posX > app.width) {
			this.posX = app.width - 77;
		}

		if (this.posX < 0) {
			this.posX = 77;
		}

		if (this.posY > app.height) {
			this.posY = app.height - 77;
		}

		if (this.posY < 0) {
			this.posY = 77;
		}

	}

}
