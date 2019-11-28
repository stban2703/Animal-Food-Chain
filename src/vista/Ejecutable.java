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
		//logica.comerOrganismo();
		//logica.matarOrganismo();
		//logica.reproducirOrganismo();

	}

	public void mousePressed() {
		if (dist(70.758f, 55.282f, mouseX, mouseY) < (55 / 2) && logica.isValidarPausa() == false) {
			logica.pausarSimulacion();
			logica.setValidarPausa(true);
		} else if (dist(70.758f, 55.282f, mouseX, mouseY) < (55 / 2) && logica.isValidarPausa() == true) {
			logica.reanudarSimulacion();
			logica.setValidarPausa(false);
		}

		if (dist(150.626f, 55.282f, mouseX, mouseY) < (55 / 2)) {
			logica.reiniciarSimulacion();
		}

		if (mouseX > 493.567 - 100 && mouseX < 493.567 + 100 && mouseY > 403.759 - 40 && mouseY < 403.759 + 40) {
			logica.comenzarSimulacion();
		}

	}

}
