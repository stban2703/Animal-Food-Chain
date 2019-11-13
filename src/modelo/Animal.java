package modelo;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Animal {
	private String tipo;
	private float posX;
	private float posY;
	private float ancho;
	private float alto;
	private String estado;
	private int edad;
	private String sexo;
	private int velocidad;
	private boolean encuentro;
	// private PImage imagen;
	// private PApplet app;

	public Animal(String tipo, float posX, float posY, float ancho, float alto, String estado, int edad, String sexo,
			int velocidad, boolean encuentro) {
		this.tipo = tipo;
		this.posX = posX;
		this.posY = posY;
		this.ancho = ancho;
		this.alto = alto;
		this.estado = estado;
		this.edad = edad;
		this.sexo = sexo;
		this.velocidad = velocidad;
		this.encuentro = encuentro;
		// this.imagen = imagen;
		//this.app = app;

	}

	/*public void pintar(PImage imagen) {
		app.imageMode(PConstants.CENTER);
		app.image(imagen, this.posX, this.posY, this.ancho, this.alto);
	}*/

}
