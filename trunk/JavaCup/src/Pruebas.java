
public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=0;
		double val;
		
		while(i<100){
			val =Math.random()*10;
			System.out.println("valI::"+(((int)val)==0?1:(int)val));
			i++;
		}
		
	}

}
