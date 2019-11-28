package modelo;

import processing.core.PApplet;
import processing.core.PVector;

public class Conejo extends Animal implements Runnable {

	public Conejo(String tipo, float posX, float posY, float ancho, float alto, String estado, int edad, String sexo,
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
		this.direccion = new PVector((int) app.random(0, app.width) - this.posX,
				(int) app.random(0, app.height) - this.posY);
		this.direccion.normalize();

		this.posX += (direccion.x * this.velocidad);
		this.posY += (direccion.y * this.velocidad);

		if (this.posX > app.width) {
			this.posX = app.width - 53;
		}

		if (this.posX < 0) {
			this.posX = 53;
		}

		if (this.posY > app.height) {
			this.posY = app.height - 53;
		}

		if (this.posY < 0) {
			this.posY = 53;
		}

	}

}
