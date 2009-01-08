package mx.com.infra;

public abstract class SimpleController {
	public abstract String cargaPagina();
	
	public String getCargaPagina() {
		this.cargaPagina();
		return "";
	}
}
