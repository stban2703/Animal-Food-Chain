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
		logica.comerOrganismo();
		logica.matarOrganismo();

	}

	public void mousePressed() {
		if (dist(70.758f, 55.282f, mouseX, mouseY) < (55 / 2) && logica.isValidarPausa()==false) {
			logica.pausarSimulacion();
			logica.setValidarPausa(true);
		} else if (dist(70.758f, 55.282f, mouseX, mouseY) < (55 / 2) && logica.isValidarPausa()==true){
			logica.reanudarSimulacion();
			logica.setValidarPausa(false);
		}
		
	}

}
