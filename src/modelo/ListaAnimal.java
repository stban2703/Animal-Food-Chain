package modelo;

import java.util.ArrayList;
import java.util.LinkedList;

import processing.core.PApplet;

public class ListaAnimal {
	private ArrayList<Conejo> conejos;
	private ArrayList<Zorro> zorros;
	private ArrayList<Buitre> buitres;
	private PApplet app;

	public ListaAnimal(PApplet app) {
		conejos = new ArrayList<Conejo>();
		zorros = new ArrayList<Zorro>();
		buitres = new ArrayList<Buitre>();
		this.app = app;

	}

	public void agregarAnimal(String tipo, String estado, int edad, String sexo, int velocidad) {
		float conejoX = (int) app.random(100, 300);
		float conejoY = (int) app.random(100, 300);

		if (tipo.equals("conejo")) {
			conejos.add(new Conejo(tipo, conejoX, conejoY, 53, 53, estado, edad, sexo, velocidad, false));
		}

		if (tipo.equals("zorro")) {
			zorros.add(new Zorro(tipo, (int) app.random(100, 900), (int) app.random(100, 900), 77, 53, estado, edad,
					sexo, velocidad, false));
		}

		if (tipo.equals("buitre")) {
			buitres.add(new Buitre(tipo, (int) app.random(100, 900), (int) app.random(100, 900), 78, 67, estado, edad,
					sexo, velocidad, false));
		}

	}

	public void imprimirLista() {
		System.out.println(conejos.size() + " " + zorros.size() + " " + buitres.size());

	}

}
