package modelo;

public class Tiempo {
	private int contador;
	private boolean cambio;

	public Tiempo() {
		this.contador = 0;
		this.cambio = false;
	}

	public boolean isCambio() {
		return cambio;
	}

	public void setCambio(boolean cambio) {
		this.cambio = cambio;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public void sumarContador() {
		this.contador++;
	}

}
