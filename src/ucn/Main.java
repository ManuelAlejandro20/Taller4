package ucn;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		menu();
		
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
