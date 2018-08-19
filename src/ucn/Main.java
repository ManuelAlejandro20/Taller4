package ucn;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;

public class Main {

	public static void main(String args[]) throws IOException {
		//EmiEmiImpl sistema = new EmiEmiImpl();
		leer_usuarios();
		menu();
		
	}
	public static void leer_usuarios() throws IOException { 
		ArchivoEntrada archivo_usuario = new ArchivoEntrada("C:\\Users\\aleja\\Documents\\Git\\Taller4\\Archivos\\Usuarios.txt");
		while(!archivo_usuario.isEndFile()) {
			Registro reg = archivo_usuario.getRegistro();
			String email = reg.getString();
			String contraseña = reg.getString();
			String nombre = reg.getString();
			String tipo = reg.getString();
			if(tipo.equals("cliente")) {
				String pais = reg.getString();
				int puntos = reg.getInt();
				Usuario cliente = new Cliente(email,contraseña,nombre,pais,puntos);
				//Se agrega el cliente a la lista
			}
			else {
				Usuario admin = new Usuario(email,contraseña,nombre);
				//Se agrega el admin a la lista 
			}
			
		}
	}
	
	public static void menu() {
		Scanner scanner = new Scanner(System.in);
		boolean salir = false;
		while(!salir) {
			StdOut.println("\n֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎");
			StdOut.println("֎Bienvenido a Emi-Emi֎");
			StdOut.println("֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎\n");
			try {
				StdOut.println("1. Iniciar Sesión");
				StdOut.println("2. Registrarse\n");
				StdOut.println("Porfavor escribe un número para comenzar (-1 para finalizar)\n");
				int opcion = scanner.nextInt();
				switch(opcion) {
					case 1:
						
					case 2:
						
					case -1:
						salir = true;
						break;
					default:
						StdOut.println("\nXXXXXXXXXXXX Escribe una opción valida porfavor XXXXXXXXXXXXX\n");
				
				}
			}catch(InputMismatchException e) {
				StdOut.println("\nXXXXXXXXX ESCRIBE UN NÚMERO PORFAVOR XXXXXXXXXXXX\n");
				scanner.nextLine();
			}
			
		}
		scanner.close();
	}
	
	public static void iniciar_sesion() {
		
		
	}
	
}
