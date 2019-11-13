package vista;

import modelo.Logica;
import processing.core.PApplet;

public class Ejecutable extends PApplet {
	private Logica logica;

	public static void main(String[] args) {
		PApplet.main(Ejecutable.class.getName());

	}

	public void settings() {
		size(1000, 600);

	}

	public void setup() {
		logica = new Logica(this);

	}
	
	public void draw() {
		logica.pintarPantallas();
		
	}
	
	public void mousePressed() {
		logica.clicksMouse();
		
	}
}
