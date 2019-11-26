package modelo;

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
	private PImage imagenConejo;
	private PImage imagenZorro;
	private PImage imagenBuitre;
	private PImage imagenPausa;
	private PImage imagenReanudar;
	private boolean validarPausa;
	private PApplet app;

	public Logica(PApplet app) {
		this.pantalla = 1;
		this.pantallaInicio = app.loadImage("inicio.jpg");
		this.pantallaJuego = app.loadImage("juego.jpg");
		this.pantallaFin = app.loadImage("fin.jpg");
		this.lista = new ListaAnimal(app);
		this.datosOrganismo = app.loadStrings("../src/imports/organismos.txt");
		this.imagenConejo = app.loadImage("data/conejo.png");
		this.imagenZorro = app.loadImage("data/zorro.png");
		this.imagenBuitre = app.loadImage("data/buitre.png");
		this.imagenPausa = app.loadImage("data/pausa.png");
		this.imagenReanudar = app.loadImage("data/reanudar.png");
		this.validarPausa = false;
		cargarOrganismo();
		this.app = app;

	}

	public void cargarOrganismo() {
		for (String texto : datosOrganismo) {
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
			for (int i = 0; i < lista.getConejos().size(); i++) {
				lista.getConejos().get(i).pintar(this.imagenConejo);
			}

			
			/*
			 * for (int i = 0; i < lista.getConejos().size() - 1; i++) { float conejoX =
			 * lista.getConejos().get(i).getPosX(); float conejoY =
			 * lista.getConejos().get(i).getPosY();
			 * 
			 * float conejoDosX = lista.getConejos().get(i + 1).getPosX(); float conejoDosY
			 * = lista.getConejos().get(i + 1).getPosY();
			 * 
			 * if (PApplet.dist(conejoX, conejoY, conejoDosX, conejoDosY) < 53 && conejoX <
			 * app.width/2 && conejoY < app.height/2) {
			 * lista.getConejos().get(i).setPosX(lista.getConejos().get(i).getPosX() + 50);
			 * lista.getConejos().get(i).setPosY(lista.getConejos().get(i).getPosY() + 50);
			 * }
			 * 
			 * if (PApplet.dist(conejoX, conejoY, conejoDosX, conejoDosY) < 53 && conejoX >
			 * app.width/2 && conejoY > app.height/2) {
			 * lista.getConejos().get(i).setPosX(lista.getConejos().get(i).getPosX() - 50);
			 * lista.getConejos().get(i).setPosY(lista.getConejos().get(i).getPosY() - 50);
			 * }
			 * 
			 * if (PApplet.dist(conejoX, conejoY, conejoDosX, conejoDosY) < 53 && conejoX <
			 * app.width/2 && conejoY > app.height/2) {
			 * lista.getConejos().get(i).setPosX(lista.getConejos().get(i).getPosX() + 50);
			 * lista.getConejos().get(i).setPosY(lista.getConejos().get(i).getPosY() - 50);
			 * }
			 * 
			 * if (PApplet.dist(conejoX, conejoY, conejoDosX, conejoDosY) < 53 && conejoX >
			 * app.width/2 && conejoY < app.height/2) {
			 * lista.getConejos().get(i).setPosX(lista.getConejos().get(i).getPosX() - 50);
			 * lista.getConejos().get(i).setPosY(lista.getConejos().get(i).getPosY() + 50);
			 * } }
			 */

			for (int i = 0; i < lista.getZorros().size(); i++) {
				lista.getZorros().get(i).pintar(this.imagenZorro);
			}

			for (int i = 0; i < lista.getBuitres().size(); i++) {
				lista.getBuitres().get(i).pintar(this.imagenBuitre);
			}
			
			if (this.validarPausa == true) {
				app.image(imagenPausa, app.width/2, app.height/2);
				app.image(imagenReanudar, 70.758f, 55.282f);
			}


			break;
		case 2:
			app.imageMode(PConstants.CORNER);
			app.image(this.pantallaFin, 0, 0, 1000, 600);
			break;
		default:
			break;

		}

	}

	public void comerOrganismo() {

		for (int j = 0; j < this.lista.getZorros().size(); j++) {
			for (int i = 0; i < this.lista.getConejos().size(); i++) {
				float conejoX = this.lista.getConejos().get(i).getPosX();
				float conejoY = this.lista.getConejos().get(i).getPosY();
				float zorroX = this.lista.getZorros().get(j).getPosX();
				float zorroY = this.lista.getZorros().get(j).getPosY();

				if (PApplet.dist(zorroX, zorroY, conejoX, conejoY) < 53) {
					try {
						this.lista.getConejos().get(i).setEstado("muerto");
						this.lista.getConejos().remove(i);

					} catch (IndexOutOfBoundsException e) {
						e.printStackTrace();
					}

				}

			}

		}

	}

	public void matarOrganismo() {
		for (int j = 0; j < this.lista.getZorros().size()-1; j++) {
			Zorro zorroUno = this.lista.getZorros().get(j);
			Zorro zorroDos = this.lista.getZorros().get(j + 1);
			float zorroUnoX = this.lista.getZorros().get(j).getPosX();
			float zorroUnoY = this.lista.getZorros().get(j).getPosY();
			float zorroDosX = this.lista.getZorros().get(j + 1).getPosX();
			float zorroDosY = this.lista.getZorros().get(j + 1).getPosY();

			if (PApplet.dist(zorroUnoX, zorroUnoY, zorroDosX, zorroDosY) < 77 && zorroUno.getSexo().equals(zorroDos.getSexo())) {
				zorroDos.setEstado("muerto");
			} /*else if (PApplet.dist(zorroUnoX, zorroUnoY, zorroDosX, zorroDosY) < 77 && zorroUno.isEncuentro() == false) {
				
			}*/

		}
	}

	public void pausarSimulacion() {

		for (int i = 0; i < lista.getConejos().size(); i++) {
			lista.getConejos().get(i).setEstado("pausa");
		}

		for (int i = 0; i < lista.getZorros().size(); i++) {
			lista.getZorros().get(i).setEstado("pausa");
		}

	}

	public void reanudarSimulacion() {

		for (int i = 0; i < lista.getConejos().size(); i++) {
			lista.getConejos().get(i).setEstado("vivo");
		}

		for (int i = 0; i < lista.getZorros().size(); i++) {
			lista.getZorros().get(i).setEstado("vivo");
		}

	}

	public boolean isValidarPausa() {
		return validarPausa;
	}

	public void setValidarPausa(boolean validarPausa) {
		this.validarPausa = validarPausa;
	}

}
