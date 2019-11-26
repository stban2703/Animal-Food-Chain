package modelo;

import java.util.ArrayList;

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
		float genX = (int) app.random(100, 800);
		float genY = (int) app.random(100, 500);

		if (tipo.equals("conejo")) {
			conejos.add(new Conejo(tipo, genX, genY, 53, 53, estado, edad, sexo, velocidad, false, app));
		}

		if (tipo.equals("zorro")) {
			zorros.add(new Zorro(tipo, genX, genY, 77, 53, estado, edad, sexo, velocidad, false, app));
		}

		if (tipo.equals("buitre")) {
			buitres.add(new Buitre(tipo, genX, genY, 78, 67, estado, edad, sexo, velocidad, false, app));
		}

	}

	public void imprimirLista() {
		System.out.println(conejos.size() + " " + zorros.size() + " " + buitres.size());
	}

	public ArrayList<Conejo> getConejos() {
		return conejos;
	}

	public void setConejos(ArrayList<Conejo> conejos) {
		this.conejos = conejos;
	}

	public ArrayList<Zorro> getZorros() {
		return zorros;
	}

	public void setZorros(ArrayList<Zorro> zorros) {
		this.zorros = zorros;
	}

	public ArrayList<Buitre> getBuitres() {
		return buitres;
	}

	public void setBuitres(ArrayList<Buitre> buitres) {
		this.buitres = buitres;
	}

}
