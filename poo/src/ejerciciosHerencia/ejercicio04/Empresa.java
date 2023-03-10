package ejerciciosHerencia.ejercicio04;

import java.util.Arrays;
import java.util.Objects;

public class Empresa {
	private String nombre;
	private Direccion direccion;
	private Empleado[] empleados;
	private static int numEmpleados;
	
	public Empresa() {
		// Si faltan, dará un error NullPointerException
		direccion = new Direccion();
		empleados = new Empleado[numEmpleados];
	}

	public Empresa(String nombre, Direccion direccion, Empleado[] empleados) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.empleados = empleados;
	}
	
	public Empresa(Empresa e) {
		super();
		this.nombre = e.nombre;
		this.direccion = new Direccion(e.direccion);
		this.empleados = new Empleado[Empresa.numEmpleados];
		for (int i=0; i<numEmpleados; i++) if (e.getEmpleados()[i] != null) this.empleados[i] = new Empleado(e.getEmpleados()[i]);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Empleado[] getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Empleado[] empleados) {
		this.empleados = empleados;
	}

	public static int getNumEmpleados() {
		return numEmpleados;
	}

	public static void setNumEmpleados(int numEmpleados) {
		Empresa.numEmpleados = numEmpleados;
	}

	@Override
	public String toString() {
		return "Empresa [nombre=" + nombre + ", direccion=" + direccion + ", empleados=" + Arrays.toString(empleados)
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
}
