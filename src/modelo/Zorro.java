package modelo;

import processing.core.PApplet;
import processing.core.PImage;

public class Zorro extends Animal implements Runnable {
	PImage imagen;
	PApplet app;

	public Zorro(String tipo, float posX, float posY, float ancho, float alto, String estado, int edad, String sexo,
			int velocidad, boolean encuentro) {
		super(tipo, posX, posY, ancho, alto, estado, edad, sexo, velocidad, encuentro);

	}

	public void run() {

	}
}
