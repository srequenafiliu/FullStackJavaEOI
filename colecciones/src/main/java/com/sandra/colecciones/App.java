package com.sandra.colecciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.sandra.colecciones.ejercicio02.Empleado;
import com.sandra.colecciones.ejercicio02.Empresa;
import com.sandra.colecciones.ejercicio03.Persona03;
import com.sandra.colecciones.entidades.Persona;

public class App {
	
	public static void ejemplosListas() {
		// Lista que almacena números
		List<Integer> numeros = new ArrayList<>();
		// Podemos establecer una capacidad inicial reservada
		List<Integer> numeros2 = new ArrayList<>(12);
		// Se puede crear una lista a partir de un array
		Integer[] array = {2, 3, 5};
		List<Integer> numeros3 = new ArrayList<>(Arrays.asList(array)); // Lista con valores iniciales
		// Se puede crear una lista a partir de otra colección
		List<Integer> numeros4 = new ArrayList<Integer>(numeros2);
		
		// Arrays y listas multidimensionales
		Integer[][] matriz = new Integer[4][5];
		List<List<Integer>> listaDeListas= new ArrayList<List<Integer>>();
		Integer[][][] show_red = new Integer[1024][728][128];
		List<List<List<Integer>>> lista3Dimensiones= new ArrayList<List<List<Integer>>>();
		
		List<String> cadenas = new ArrayList<>(); // Desde Java 8 no hace falta poner la segunda vez el tipo de lista
		cadenas.add("Hola"); // Añadir en la última posición
		cadenas.add("Adiós");
		cadenas.add("Hola"); // Las listas permiten elementos repetidos
		cadenas.add(1, "Mundo"); // Añadir en una posición determinada ya existente
		System.out.println("Creación de la lista cadenas:");
		cadenas.forEach(e->System.out.println(e));
		
		List<String> otra = Arrays.asList("Hola", "en", "minúscula"); // Otra forma de crear una lista con valores iniciales, pero dará errores con los métodos de listas
		cadenas.addAll(otra);
		System.out.println("Se ha añadido la lista otra a la lista cadenas:");
		cadenas.forEach(e->System.out.println(e));
		
		System.out.println("Tamaño lista numeros3: "+numeros3.size());
		numeros3.clear();
		System.out.println("Tamaño lista numeros3: "+numeros3.size());
		
		if(cadenas.contains("hola")) System.out.println("La lista contiene hola"); // Case sensitive
		else if(cadenas.contains("Hola")) System.out.println("La lista contiene Hola");

		System.out.println(cadenas.indexOf("Hola"));
		System.out.println(cadenas.lastIndexOf("Hola"));
		
		List<String> otra2 = new ArrayList<>(Arrays.asList("Hola", "en", "minúscula"));
		cadenas.remove("Mundo");
		cadenas.removeAll(otra2);
		cadenas.forEach(e->System.out.println(e));
		cadenas.removeIf(e->e.equals("Hola"));
		cadenas.forEach(e->System.out.println(e));
	}
	
	public static void ejemploMap() {
		// Diccionario que almacena cadenas y el índice también es una cadena
		Map<String, String> traducciones = new HashMap<>();
		traducciones.put("mesa", "table");
		traducciones.put("silla", "chair");
		traducciones.put("cabeza", "head");
		traducciones.put("botella", "bottle");
		// {mesa=table, cabeza=head, botella=bottle, silla=chair}
		System.out.println(traducciones);
		
		// Acceder a los valores
		String palabra = traducciones.get("cabeza");
		System.out.println(palabra); // head
		
		// Recorrer los valores
		for(String key: traducciones.keySet()) {
			 System.out.println(key + " en inglés es: " + traducciones.get(key));
		}
		for(String value: traducciones.values()) {
		 System.out.println("Palabra: " + value + " (no puedo saber la clave...)");
		}
		// Con funcional sería
		traducciones.forEach((clave, valor) -> System.out.println(clave + " en inglés es: " + valor));
		
		Map<Integer, Persona> personas = new HashMap<>();
		personas.put(1, new Persona("Fran", 10000));
		personas.put(2, new Persona("Pepe", 10000));
		personas.put(3, new Persona("Sandra", 10000));
		personas.put(4, new Persona("Helena", 10000));
		
		Persona persona3 = personas.get(3);
		System.out.println(persona3);
	}
	
	public static void ejemploSet() {
		// No se permiten elementos duplicados como ocurre en las listas
		Set <Persona> personas = new HashSet<>();
		personas.add(new Persona("Fran", 10000));
		personas.add(new Persona("Sandra", 10000));
		personas.add(new Persona("Fran", 20000)); // La ignora por el equals de la clase Persona
		personas.add(new Persona("Fran", 30000)); // La ignora por el equals de la clase Persona
		personas.add(new Persona("fran", 10000));
		System.out.println("El número de personas es "+personas.size());
	}
	
	public static void ejercicio1() {
		/*
		 * 1. Crea una lista con los números 10, 20, 30 y 40. A continuación haz las siguientes operaciones:
		 * a. Añade los números 50 y 60 al final.
		 * b. Borra el número 20.
		 * c. Añade los valores 25 y 26 entre el 10 y el 30 (posición 1).
		 * d. Dale la vuelta a la lista y muéstrala por pantalla
		 * Los valores finales deberían ser: 60, 50, 30, 26, 25, 10
		 */
		List<Integer> numeros = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
		Collections.addAll(numeros, 50, 60);
		numeros.removeIf(e->e==20);
		numeros.addAll(1, Arrays.asList(25, 26));
		Collections.reverse(numeros);
		// numeros.sort(Comparator.reverseOrder()); // Es otra manera de ordenar inversamente, NO de darle la vuelta a la lista
		System.out.println("Valores finales: "+numeros);
		System.out.println("Valores finales: "+numeros.stream().map(Object::toString).collect(Collectors.joining(", ")));
		// numeros.forEach(e->System.out.println(e));
	}
	
	public static void ejercicio2() {
		/*
		 * A partir del apartado 4 de los ejercicios de herencia (Empresa con
		 * empleados). Cambia el array de empleados por una lista. A partir de ahora no
		 * necesitarás el atributo numEmpleados, por lo que elimínalo.
		 */
		Empresa empresa = new Empresa();
		Collections.addAll(empresa.getEmpleados(), new Empleado("Fran", 10000), new Empleado("Dani", 20000), new Empleado("Paco", 10000));
		Empresa copia = new Empresa(empresa);
		copia.getEmpleados().get(0).setNombre("Francisco");
		copia.getDireccion().setNumero(10);
		copia.getEmpleados().add(new Empleado("Nuevo", 30000));
		
		System.out.println("Empresa original:");
		System.out.println(empresa);
		System.out.println("Empresa copia:");
		System.out.println(copia);
	}
	
	public static void ejercicio3() {
		/*
		 * Crea un mapa donde la clave sea un dni y el valor sera un objeto del tipo Persona
		 * (con los atributos nombre y edad). Añade 3 personas con sus correspondientes dnis
		 * como clave. Finalmente recórrelo y muestra las personas con su dni.
		 */
		Map<String, Persona03> personas = new HashMap<>();
		personas.put("12345678A", new Persona03("Sandra", 23));
		personas.put("98765432Z", new Persona03("Jorge", 25));
		personas.put("95340762J", new Persona03("María", 24));
		for(String dni: personas.keySet()) System.out.println("El dni "+dni+" corresponde a la siguiente persona:\n"+personas.get(dni));
	}
	
	public static void ejercicio4() {
		/*
		 * Crea una lista de números enteros con varios valores. A continuación genera
		 * otra lista que contenga los números de la original divididos entre 2, y
		 * después borra aquellos que sean impares.
		 */
		List<Integer> numeros = new ArrayList<>();
		Collections.addAll(numeros, 15, 20, 27, 30, 33, 38);
		System.out.println("Lista de números original: "+numeros);
		List<Integer> numeros2 = new ArrayList<>(numeros);
		numeros2.replaceAll(n->n/2);
		System.out.println("Lista de números dividida entre 2: "+numeros2);
		numeros2.removeIf(n->n%2!=0);
		System.out.println("Números pares de la lista anterior: "+numeros2);
		
	}
	
	public static void ejercicio5() {
		/*
		 * Crea un conjunto (Set) de cadenas. Pregunta al usuario que escriba una
		 * palabra, hasta que escriba la palabra “salir”.
		 * a. Si la palabra no está en el conjunto añádela
		 * b. Si ya está en el conjunto, informa al usuario de que está repetida.
		 * c. Cuando el usuario escriba “salir”, debes mostrar la lista de palabras introducidas.
		 */
		Scanner sc = new Scanner (System.in);
		Set <String> cadenas = new HashSet<>();
		Collections.addAll(cadenas, "Hola", "Ejercicio", "Listas", "Mapas", "Conjuntos", "Adiós");
		String cadena;
		do {
			System.out.print("Introduzca una cadena: ");
			cadena = sc.nextLine();
			if (cadena.equalsIgnoreCase("salir")) cadenas.forEach(e->System.out.println(e));
			else {
				if(cadenas.contains(cadena)) System.err.println("La cadena "+cadena+" ya se encuentra en el conjunto.");
				else cadenas.add(cadena);
			}
		} while (!cadena.equalsIgnoreCase("salir"));
		sc.close();
	}
	
    public static void main(String[] args) {
        // ejemplosListas();
    	// ejemploMap();
    	// ejemploSet();
    	// ejercicio1();
    	// ejercicio2();
    	// ejercicio3();
    	// ejercicio4();
    	// ejercicio5();
    }
}
