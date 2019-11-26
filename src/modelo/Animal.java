package modelo;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class Animal {
	protected String tipo;
	protected float posX;
	protected float posY;
	protected float ancho;
	protected float alto;
	protected String estado;
	protected int edad;
	protected String sexo;
	protected int velocidad;
	protected boolean encuentro;
	protected PApplet app;
	protected int dirX;
	protected int dirY;
	protected ListaAnimal lista;
	protected PVector direccion;
	protected boolean estaVivo;

	public Animal(String tipo, float posX, float posY, float ancho, float alto, String estado, int edad, String sexo,
			int velocidad, boolean encuentro, PApplet app) {
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
		this.dirX = 1;
		this.dirY = 1;
		this.app = app;
		this.lista = new ListaAnimal(app);
		this.estaVivo = true;
		this.encuentro = false;

	}

	public void pintar(PImage imagen) {
		app.imageMode(PConstants.CENTER);
		app.image(imagen, this.posX, this.posY, this.ancho, this.alto);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public float getAncho() {
		return ancho;
	}

	public void setAncho(float ancho) {
		this.ancho = ancho;
	}

	public float getAlto() {
		return alto;
	}

	public void setAlto(float alto) {
		this.alto = alto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public boolean isEncuentro() {
		return encuentro;
	}

	public void setEncuentro(boolean encuentro) {
		this.encuentro = encuentro;
	}

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}

	public int getDirX() {
		return dirX;
	}

	public void setDirX(int dirX) {
		this.dirX = dirX;
	}

	public int getDirY() {
		return dirY;
	}

	public void setDirY(int dirY) {
		this.dirY = dirY;
	}

	public ListaAnimal getLista() {
		return lista;
	}

	public void setLista(ListaAnimal lista) {
		this.lista = lista;
	}

}
