package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
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
	private Tiempo contadorTiempo;
	private PApplet app;

	public Logica(PApplet app) {
		this.pantalla = 0;
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
		this.contadorTiempo = new Tiempo();
		cargarOrganismo();
		incremento();
		this.app = app;
	}

	// Carga el .txt
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

	// Pintar lo que se ve en cada pantalla
	public void pintarPantallas() {
		switch (this.pantalla) {
		case 0:
			app.imageMode(PConstants.CORNER);
			app.image(this.pantallaInicio, 0, 0, 1000, 600);
			break;
		case 1:
			app.imageMode(PConstants.CORNER);
			app.image(this.pantallaJuego, 0, 0, 1000, 600);

			// Pintar conejos
			for (int i = 0; i < lista.getConejos().size(); i++) {
				lista.getConejos().get(i).pintar(this.imagenConejo);

				// Activar reproduccion
				if (lista.getConejos().get(i).isEncuentro() == true && app.frameCount % 10 == 0) {
					lista.getConejos().get(i).setEncuentro(false);
				}
			}

			// Pintar zorros
			for (int i = 0; i < lista.getZorros().size(); i++) {
				lista.getZorros().get(i).pintar(this.imagenZorro);

				// Activar reproduccion
				if (lista.getZorros().get(i).isEncuentro() == true && app.frameCount % 60 == 0) {
					lista.getZorros().get(i).setEncuentro(false);
				}
			}

			// Pintar buitres
			for (int i = 0; i < lista.getBuitres().size(); i++) {
				lista.getBuitres().get(i).pintar(this.imagenBuitre);

				// Activar reproduccion
				if (lista.getBuitres().get(i).isEncuentro() == true && app.frameCount % 30 == 0) {
					lista.getBuitres().get(i).setEncuentro(false);
				}
			}

			// Pausar Juego
			if (this.validarPausa == true) {
				app.image(imagenPausa, app.width / 2, app.height / 2);
				app.image(imagenReanudar, 70.758f, 55.282f);
			}

			// Pintar contador de años
			app.textSize(36);
			app.text("Años: " + contadorTiempo.getContador(), 30, app.height - 30);

			if (contadorTiempo.getContador() == 10) {
				pantalla = 2;
			}

			comerOrganismo();
			matarOrganismo();
			reproducirOrganismo();

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

		// Zorro come y mata al conejo
		for (int j = 0; j < this.lista.getZorros().size(); j++) {
			for (int i = 0; i < this.lista.getConejos().size(); i++) {
				float conejoX = this.lista.getConejos().get(i).getPosX();
				float conejoY = this.lista.getConejos().get(i).getPosY();
				float zorroX = this.lista.getZorros().get(j).getPosX();
				float zorroY = this.lista.getZorros().get(j).getPosY();

				if (PApplet.dist(zorroX, zorroY, conejoX, conejoY) < (53)) {
					try {
						this.lista.getConejos().get(i).setEstaVivo(false);
						this.lista.getConejos().get(i).setEstado("muerto");
						this.lista.getConejos().remove(i);

					} catch (IndexOutOfBoundsException e) {
						e.printStackTrace();
					}

				}

			}

		}

		// Buitre se come a los zorros muertos
		for (int i = 0; i < this.lista.getBuitres().size(); i++) {
			for (int j = 0; j < this.lista.getZorros().size(); j++) {
				float buitreX = this.lista.getBuitres().get(i).getPosX();
				float buitreY = this.lista.getBuitres().get(i).getPosY();
				float zorroX = this.lista.getZorros().get(j).getPosX();
				float zorroY = this.lista.getZorros().get(j).getPosY();

				if (PApplet.dist(buitreX, buitreY, zorroX, zorroY) < (78)
						&& this.lista.getZorros().get(j).getEstado().equals("muerto")) {
					try {
						this.lista.getZorros().remove(j);

					} catch (IndexOutOfBoundsException e) {
						e.printStackTrace();
					}

				}

			}

		}

	}

	public void matarOrganismo() {

		// Zorro macho mata otro zorro macho
		for (int j = 0; j < this.lista.getZorros().size() - 1; j++) {
			Zorro zorroUno = this.lista.getZorros().get(j);
			Zorro zorroDos = this.lista.getZorros().get(j + 1);
			float zorroUnoX = this.lista.getZorros().get(j).getPosX();
			float zorroUnoY = this.lista.getZorros().get(j).getPosY();
			float zorroDosX = this.lista.getZorros().get(j + 1).getPosX();
			float zorroDosY = this.lista.getZorros().get(j + 1).getPosY();

			if (PApplet.dist(zorroUnoX, zorroUnoY, zorroDosX, zorroDosY) < (77)
					&& zorroUno.getSexo().equals(zorroDos.getSexo()) && zorroUno.getEstado().equals("vivo")
					&& zorroDos.getEstado().equals("vivo")) {
				zorroDos.setEstado("muerto");
				zorroDos.setEstaVivo(false);
			}

		}

		// Conejo muere por la edad
		for (int j = 0; j < this.lista.getConejos().size(); j++) {
			Conejo conejo = this.lista.getConejos().get(j);
			if (conejo.getEdad() == 5) {
				conejo.setEstado("muerto");
				conejo.setEstaVivo(false);
			}

		}

		// Zorro muere por la edad
		for (int j = 0; j < this.lista.getZorros().size(); j++) {
			Zorro zorro = this.lista.getZorros().get(j);
			if (zorro.getEdad() == 5) {
				zorro.setEstado("muerto");
				zorro.setEstaVivo(false);
			}
		}

		// Buitre muere por la edad
		for (int j = 0; j < this.lista.getBuitres().size(); j++) {
			Buitre buitre = this.lista.getBuitres().get(j);
			if (buitre.getEdad() == 8) {
				buitre.setEstado("muerto");
				buitre.setEstaVivo(false);
			}

		}

	}

	public void reproducirOrganismo() {
		// Conejo macho reproduce con hembra
		for (int j = 0; j < this.lista.getConejos().size() - 1; j++) {
			Conejo conejoUno = this.lista.getConejos().get(j);
			Conejo conejoDos = this.lista.getConejos().get(j + 1);
			float conejoUnoX = this.lista.getConejos().get(j).getPosX();
			float conejoUnoY = this.lista.getConejos().get(j).getPosY();
			float conejoDosX = this.lista.getConejos().get(j + 1).getPosX();
			float conejoDosY = this.lista.getConejos().get(j + 1).getPosY();
			int variarGenero = (int) app.random(0, 2);
			String generoNuevo = "";

			if (variarGenero == 0) {
				generoNuevo = "macho";
			} else {
				generoNuevo = "hembra";
			}

			// Validar encuentro y desactivar la reproduccion
			if (PApplet.dist(conejoUnoX, conejoUnoY, conejoDosX, conejoDosY) < (77)
					&& !conejoUno.getSexo().equals(conejoDos.getSexo()) && conejoUno.getEstado().equals("vivo")
					&& conejoDos.getEstado().equals("vivo") && conejoUno.isEncuentro() == false
					&& conejoDos.isEncuentro() == false) {

				lista.getConejos().add(new Conejo("conejo", conejoUno.getPosX(), conejoUno.getPosY(), 53, 53, "vivo", 0,
						generoNuevo, 20, true, app));

				conejoUno.setEncuentro(true);

				conejoDos.setEncuentro(true);
			}
		}

		// Zorro macho reproduce con hembra
		for (int j = 0; j < this.lista.getZorros().size() - 1; j++) {
			Zorro zorroUno = this.lista.getZorros().get(j);
			Zorro zorroDos = this.lista.getZorros().get(j + 1);
			float zorroUnoX = this.lista.getZorros().get(j).getPosX();
			float zorroUnoY = this.lista.getZorros().get(j).getPosY();
			float zorroDosX = this.lista.getZorros().get(j + 1).getPosX();
			float zorroDosY = this.lista.getZorros().get(j + 1).getPosY();
			int variarGenero = (int) app.random(0, 2);
			String generoNuevo = "";

			if (variarGenero == 0) {
				generoNuevo = "macho";
			} else {
				generoNuevo = "hembra";
			}

			// Validar encuentro y desactivar la reproduccion
			if (PApplet.dist(zorroUnoX, zorroUnoY, zorroDosX, zorroDosY) < (77)
					&& !zorroUno.getSexo().equals(zorroDos.getSexo()) && zorroUno.getEstado().equals("vivo")
					&& zorroDos.getEstado().equals("vivo") && zorroUno.isEncuentro() == false
					&& zorroDos.isEncuentro() == false) {

				lista.getZorros().add(new Zorro("zorro", zorroUno.getPosX(), zorroUno.getPosY(), 77, 53, "vivo", 0,
						generoNuevo, 25, true, app));

				zorroUno.setEncuentro(true);

				zorroDos.setEncuentro(true);
			}
		}

		// Buitre reproduce con cualquier otro
		for (int j = 0; j < this.lista.getBuitres().size() - 1; j++) {
			Buitre buitreUno = this.lista.getBuitres().get(j);
			Buitre buitreDos = this.lista.getBuitres().get(j + 1);
			float buitreUnoX = this.lista.getBuitres().get(j).getPosX();
			float buitreUnoY = this.lista.getBuitres().get(j).getPosY();
			float buitreDosX = this.lista.getBuitres().get(j + 1).getPosX();
			float buitreDosY = this.lista.getBuitres().get(j + 1).getPosY();
			int variarGenero = (int) app.random(0, 2);
			String generoNuevo = "";

			if (variarGenero == 0) {
				generoNuevo = "macho";
			} else {
				generoNuevo = "hembra";
			}

			// Validar encuentro y desactivar la reproduccion
			if (PApplet.dist(buitreUnoX, buitreUnoY, buitreDosX, buitreDosY) < (77)
					&& buitreUno.getEstado().equals("vivo") && buitreDos.getEstado().equals("vivo")
					&& buitreUno.isEncuentro() == false && buitreDos.isEncuentro() == false) {

				lista.getBuitres().add(new Buitre("buitre", buitreUno.getPosX(), buitreUno.getPosY(), 78, 67, "vivo", 0,
						generoNuevo, 5, true, app));

				buitreUno.setEncuentro(true);

				buitreDos.setEncuentro(true);
			}
		}
	}

	public void incremento() {

		Timer timer = new Timer(3650, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarPausa == false) {
					contadorTiempo.sumarContador();
					for (int i = 0; i < lista.getConejos().size(); i++) {
						lista.getConejos().get(i).crecer();
					}

					for (int i = 0; i < lista.getZorros().size(); i++) {
						lista.getZorros().get(i).crecer();

					}

					for (int i = 0; i < lista.getBuitres().size(); i++) {
						lista.getBuitres().get(i).crecer();
					}

				}
			}
		});
		timer.start();

	}

	public void pausarSimulacion() {
		if (pantalla == 1) {
			for (int i = 0; i < lista.getConejos().size(); i++) {
				lista.getConejos().get(i).setEstado("pausa");
			}

			for (int i = 0; i < lista.getZorros().size(); i++) {
				lista.getZorros().get(i).setEstado("pausa");
			}

			for (int i = 0; i < lista.getBuitres().size(); i++) {
				lista.getBuitres().get(i).setEstado("pausa");
			}
		}

	}

	public void reanudarSimulacion() {
		if (pantalla == 1) {
			for (int i = 0; i < lista.getConejos().size(); i++) {
				lista.getConejos().get(i).setEstado("vivo");
			}

			for (int i = 0; i < lista.getZorros().size(); i++) {
				lista.getZorros().get(i).setEstado("vivo");
			}

			for (int i = 0; i < lista.getBuitres().size(); i++) {
				lista.getBuitres().get(i).setEstado("vivo");
			}
		}

	}

	public void reiniciarSimulacion() {
		if (pantalla == 1) {

			lista.getConejos().clear();

			lista.getZorros().clear();

			lista.getBuitres().clear();

			validarPausa = false;

			cargarOrganismo();

			contadorTiempo.setContador(0);

			pantalla = 0;
		}
	}

	public boolean isValidarPausa() {
		return validarPausa;
	}

	public void setValidarPausa(boolean validarPausa) {
		this.validarPausa = validarPausa;
	}

	public void comenzarSimulacion() {
		if (pantalla == 0) {
			pantalla = 1;
		}

	}

}
