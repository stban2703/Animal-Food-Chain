package modelo;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Logica {
	private int pantalla;
	private PImage pantallaInicio;
	private PImage pantallaJuego;
	private PImage pantallaFin;
	private ListaAnimal lista;
	private String[] datosOrganismo;
	private PApplet app;

	public Logica(PApplet app) {
		// this.datosOrganismos
		this.pantalla = 1;
		this.pantallaInicio = app.loadImage("inicio.jpg");
		this.pantallaJuego = app.loadImage("juego.jpg");
		this.pantallaFin = app.loadImage("fin.jpg");
		this.lista = new ListaAnimal(app);
		this.datosOrganismo = app.loadStrings("../src/imports/organismos.txt");
		cargarTexto();
		this.app = app;

	}

	public void cargarTexto() {
		for (String texto: datosOrganismo) {
			String[] textoDivido = texto.split(",");
			int edad = Integer.parseInt(textoDivido[2]);
			int velocidad = Integer.parseInt(textoDivido[4]);
			
			String tipo = textoDivido[0];
			String estado = textoDivido[1];
			String sexo = textoDivido[3];
			
			lista.agregarAnimal(tipo, estado, edad, sexo, velocidad);
			
		}

	}

	public void pintarPantallas() {
		switch (this.pantalla) {
		case 0:
			app.imageMode(PConstants.CORNER);
			app.image(this.pantallaInicio, 0, 0, 1000, 600);
			break;
		case 1:
			app.imageMode(PConstants.CORNER);
			app.image(this.pantallaJuego, 0, 0, 1000, 600);
			break;
		case 2:
			app.imageMode(PConstants.CORNER);
			app.image(this.pantallaFin, 0, 0, 1000, 600);
			break;
		default:
			break;

		}

	}
	
	public void clicksMouse() {
		lista.imprimirLista();
	}

}
