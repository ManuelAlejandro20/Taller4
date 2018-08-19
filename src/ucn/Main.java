package ucn;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;

public class Main {

	public static void main(String args[]) throws IOException {
		EmiEmiImpl sistema = new EmiEmiImpl();
		LeerUsuarios(sistema);
		Menu(sistema);
		
	}
	public static void LeerUsuarios(EmiEmiImpl sistema) throws IOException { 
		//ArchivoEntrada archivoUsuario = new ArchivoEntrada("la ruta donde tu guardaste el archivo carnal :v");
		ArchivoEntrada archivoUsuario = new ArchivoEntrada("C:\\Users\\aleja\\Documents\\Git\\Taller4\\Archivos\\Usuarios.txt");
		while(!archivoUsuario.isEndFile()) {
			Registro reg = archivoUsuario.getRegistro();
			String email = reg.getString();
			String contraseña = reg.getString();
			String nombre = reg.getString();
			String tipo = reg.getString();
			if(tipo.equals("cliente")) {
				String pais = reg.getString();
				int puntos = reg.getInt();
				Usuario cliente = new Cliente(email,contraseña,nombre,pais,puntos);
				sistema.RegistrarUsuario(cliente);
			}
			else {
				Usuario admin = new Usuario(email,contraseña,nombre);
				sistema.RegistrarUsuario(admin);
			}
			
		}
	}
	
	public static void Menu(EmiEmiImpl sistema) {
		Scanner scanner = new Scanner(System.in);
		boolean salir = false;
		while(!salir) {
			StdOut.println("\n֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎");
			StdOut.println("֎Bienvenido(a) a Emi-Emi֎");
			StdOut.println("֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎֎\n");
			try {
				StdOut.println("1. Iniciar Sesión");
				StdOut.println("2. Registrarse\n");
				StdOut.println("Porfavor escribe un número para comenzar (-1 para finalizar)\n");
				int opcion = scanner.nextInt();
				switch(opcion) {
					case 1:
						
					case 2:
						RegistrarUsuario(sistema);
						StdOut.println(sistema.getñe().getCant());
						sistema.getñe().xd();
						continue;
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
	
	public static void RegistrarUsuario(EmiEmiImpl sistema) {
		Scanner scanner = new Scanner(System.in);
		try {
			StdOut.println("\nIngresa tus datos para registrarte porfavor\n");
			System.out.println("\nEscribe tu correo\n");
			String email = scanner.nextLine();
			System.out.println("\nEscribe tu contraseña\n");
			String contraseña = scanner.nextLine();
			System.out.println("\nEscribe tu nombre\n");
			String nombre = scanner.nextLine();
			System.out.println("\nEscribe tu pais\n");
			String pais = scanner.nextLine();
			Usuario usuario = new Cliente(email,contraseña,nombre,pais,0);
			if(!sistema.RegistrarUsuario(usuario)) {
				throw new ExcepcionUsuarioRepetido();
			}
			else {
				StdOut.println("\nEL usuario "+ nombre +" fue agregado exitosamente\n");
			}
		}catch(ExcepcionUsuarioRepetido e) {
			StdOut.println(e.mensaje());
		}
	}
	
}


