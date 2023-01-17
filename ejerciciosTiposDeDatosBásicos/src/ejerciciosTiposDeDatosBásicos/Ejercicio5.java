/**
 * 
 */
package ejerciciosTiposDeDatosBásicos;

/**
 * @author Sandra
 *
 */
public class Ejercicio5 {

	public static void main(String[] args) {
		/*
		 * Escribe un programa que declare una variable entera N y asígnale un valor. A continuación escribe las instrucciones que realicen los siguientes:
		 * a. Incrementar N en 77.
		 * b. Decrementarla en 3.
		 * c. Duplicar su valor.
		 * Si por ejemplo N vale 1, la salida del programa será:
		 * Valor inicial de N = 1
		 * N + 77 = 78
		 * N - 3 = 75
		 * N * 2 = 150
		 */
		
		int N = 13, suma = N + 77, resta = suma - 3, mult = resta*2;
		
		System.out.println("Valor inicial de N = "+N);
		System.out.println(N+" + 77 = "+suma);
		System.out.println(suma+" -3 = "+resta);
		System.out.println(resta+" · 2 = "+mult);
	}

}
