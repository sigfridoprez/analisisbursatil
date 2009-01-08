package mx.com.faces.seriesoperadas.util;

public class ValorVO {
	double dblRendimiento;
	int    intX;
	
	public ValorVO(double dblRendimiento,int    intX){
		this.setDblRendimiento(dblRendimiento);
		this.setIntX(intX);
	}
	
	public double getDblRendimiento() {
		return dblRendimiento;
	}
	public void setDblRendimiento(double dblRendimiento) {
		this.dblRendimiento = dblRendimiento;
	}
	public int getIntX() {
		return intX;
	}
	public void setIntX(int intX) {
		this.intX = intX;
	}
	
}
