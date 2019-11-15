package modelo;

import processing.core.PApplet;

public class Zorro extends Animal implements Runnable {

	public Zorro(String tipo, float posX, float posY, float ancho, float alto, String estado, int edad, String sexo,
			int velocidad, boolean encuentro, PApplet app) {
		super(tipo, posX, posY, ancho, alto, estado, edad, sexo, velocidad, encuentro, app);
		new Thread(this).start();

	}

	public void run() {

	}
}
