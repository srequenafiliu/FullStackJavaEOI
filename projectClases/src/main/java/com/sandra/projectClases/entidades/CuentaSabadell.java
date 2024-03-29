package com.sandra.projectClases.entidades;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sandra.projectClases.enumerados.NivelCatalan;

public final class CuentaSabadell extends Cuenta{
	private NivelCatalan nivelCatalan;

	public NivelCatalan getNivelCatalan() {
		return nivelCatalan;
	}

	public void setNivelCatalan(NivelCatalan nivelCatalan) {
		this.nivelCatalan = nivelCatalan;
	}
	
	public CuentaSabadell() {
		super();
		this.nivelCatalan = NivelCatalan.Bajo;
	}
	
	public CuentaSabadell(String dni_cif, String nombre_cliente, LocalDate fechaNacimientoCliente, String codigo_pais,
			double saldo) {
		super(dni_cif, nombre_cliente, fechaNacimientoCliente, codigo_pais, saldo);
		this.nivelCatalan = NivelCatalan.Bajo;
	}

	public CuentaSabadell(String dni_cif, String nombre_cliente, LocalDate fechaNacimientoCliente, String codigo_pais,
			double saldo, NivelCatalan nivelCatalan) {
		super(dni_cif, nombre_cliente, fechaNacimientoCliente, codigo_pais, saldo);
		this.nivelCatalan = nivelCatalan;
	}
	
	public static List<CuentaSabadell> getListCuentaSabadell(String nombreFichero) {
		Path archivo = getRutaFichero(nombreFichero);
		List<CuentaSabadell> datosBanco = new ArrayList<>();
		try {
			List<String> lineas = Files.readAllLines(archivo);
			for (String linea:lineas) {
				List<String> datosLinea = new ArrayList<>(Arrays.asList(linea.split(";")));
				datosBanco.add(new CuentaSabadell(
						datosLinea.get(0), // dni_cif
						datosLinea.get(1), //nombre_cliente
						LocalDate.parse(datosLinea.get(2), DateTimeFormatter.ofPattern("dd/MM/yyyy")), // fechaNacimiento
						datosLinea.get(3), // codigo_pais
						Double.parseDouble(datosLinea.get(4)))); // saldo
			}
		} catch (NoSuchFileException e) {
			System.err.println("No existe el archivo "+e.getMessage());
		} catch (IOException e) {
			System.err.println("Error leyendo el archivo " + archivo);
		}
		return datosBanco;
	}
	
	@Override
	public String toString() {
		return "DNI-CIF: "+getDni_cif()
				+ "\nNombre del cliente: "+getNombre_cliente()
				+ "\nFecha de nacimiento del cliente: "+getFechaNacimientoCliente().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				+ "\nCódigo del país: "+getCodigo_pais()
				+ "\nSaldo: "+String.format("%.2f", getSaldo())+"€"
				+ "\nNivel de catalán: "+nivelCatalan+"\n";
	}
}
