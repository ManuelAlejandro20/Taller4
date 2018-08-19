package ucn;
import java.util.InputMismatchException;
import java.util.Scanner;
import Excepciones.ExcepcionUsuarioRepetido;
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
						IniciarSesion(sistema);
						continue;
					case 2:
						RegistrarUsuario(sistema);
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

	public static void IniciarSesion(EmiEmiImpl sistema) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nIngresa tu email porfavor\n");
		String email = scanner.nextLine();
		System.out.println("\nIngresa tu contraseña\n");
		String contraseña = scanner.nextLine();
		Usuario usuario = sistema.usuarios.BuscarUsuario(email, contraseña);
		if(usuario != null) {
			if(usuario.getClass().getSimpleName().equals("Cliente")) {
				SubMenuCliente(sistema, usuario);
			}
			else {
				
				SubMenuAdmin(sistema, usuario);
			}
		}
	}
	
	public static void SubMenuCliente(EmiEmiImpl sistema, Usuario usuario){
		Scanner scanner = new Scanner(System.in);
		boolean salir2 = false;
		while(!salir2) {
			StdOut.println("◄ Bienvenido(a) de vuelta " + usuario.getNombre() +" ►");
			try {
				StdOut.println("1. Buscar productos");
				StdOut.println("2. Ver Catálogo entero");
				StdOut.println("3. Añadir productos a mi carrito de compras");
				StdOut.println("4. Confirmar Compra");
				StdOut.println("5. Añadir figura usada\n");
				StdOut.println("¿Qué es lo que se te ofrece el día de hoy? (-1 para cerrar sesión)\n");
				int opcion = scanner.nextInt();
				switch(opcion) {
					case 1:
						continue;
					case 2:
						continue;
					case 3:
						continue;
					case 4:
						continue;
					case 5:
						continue;
					case -1:
						salir2 = true;
						StdOut.println("\nGracias por usar el servicio Online de EmiEmi esperamos verte pronto "+usuario.getNombre()+" :)\n");
						break;
					default:
						StdOut.println("\nXXXXXXXXXXXX Escribe una opción valida porfavor XXXXXXXXXXXXX\n");
				
				}
			}catch(InputMismatchException e) {
				StdOut.println("\nXXXXXXXXX ESCRIBE UN NÚMERO PORFAVOR XXXXXXXXXXXX\n");
				scanner.nextLine();
			}
			
		}
	}
	
	public static void SubMenuAdmin(EmiEmiImpl sistema, Usuario usuario){
		Scanner scanner = new Scanner(System.in);
		boolean salir3 = false;
		while(!salir3) {
			StdOut.println("♦ Bienvenido(a) de vuelta " + usuario.getNombre() +" ♦");
			try {
				StdOut.println("1. Registrar Nuevo Administrador");
				StdOut.println("2. Añadir Nuevo Stock");
				StdOut.println("3. Eliminar la cuenta de un cliente");
				StdOut.println("4. Exportar Analisis de ventas\n");
				StdOut.println("¿Qué es lo que desas hacer? (-1 para cerrar sesión)\n");
				int opcion = scanner.nextInt();
				switch(opcion) {
					case 1:
						RegistrarAdmin(sistema);
						sistema.usuarios.xd();
						continue;
					case 2:
						continue;
					case 3:
						continue;
					case 4:
						continue;
					case -1:
						salir3 = true;
						StdOut.println("\n"+usuario.getNombre()+" cerró sesión\n");
						break;
					default:
						StdOut.println("\nXXXXXXXXXXXX Escribe una opción valida porfavor XXXXXXXXXXXXX\n");
				
				}
			}catch(InputMismatchException e) {
				StdOut.println("\nXXXXXXXXX ESCRIBE UN NÚMERO PORFAVOR XXXXXXXXXXXX\n");
				scanner.nextLine();
			}
			
		}
	}

	public static void RegistrarAdmin(EmiEmiImpl sistema) {
		Scanner scanner = new Scanner(System.in);
		try {
			StdOut.println("\nIngresa los datos para registrar al nuevo Administrador porfavor\n");
			System.out.println("\nEscribe su correo\n");
			String email = scanner.nextLine();
			System.out.println("\nEscribe su contraseña\n");
			String contraseña = scanner.nextLine();
			System.out.println("\nEscribe su nombre\n");
			String nombre = scanner.nextLine();
			Usuario usuario = new Usuario(email,contraseña,nombre);
			if(!sistema.RegistrarUsuario(usuario)) {
				throw new ExcepcionUsuarioRepetido();
			}
			else {
				StdOut.println("\nEL Administrador "+ nombre +" fue agregado exitosamente\n");
			}
		}catch(ExcepcionUsuarioRepetido e) {
			StdOut.println(e.mensaje());
		}
		
	}

}


