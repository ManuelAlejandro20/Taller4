package ucn;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import Excepciones.ExcepcionUsuarioRepetido;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String args[]) throws IOException, ParseException {
		EmiEmiImpl sistema = new EmiEmiImpl();
		LeerUsuarios(sistema);
		LeerProductos(sistema);
		Menu(sistema);
		
	}
	
	public static void LeerUsuarios(EmiEmiImpl sistema) throws IOException { 
		ArchivoEntrada archivoUsuario = new ArchivoEntrada("Usuarios.txt");
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
	
	public static void LeerProductos(EmiEmiImpl sistema) throws IOException, ParseException {
		DateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
		String fechaActual = formato.format(new Date());
		ArchivoEntrada archivoProducto = new ArchivoEntrada("Productos.txt");
		while (!archivoProducto.isEndFile()) {
			Registro reg = archivoProducto.getRegistro();
			String SKU = reg.getString();
			String nombre = reg.getString();
			int precio = reg.getInt();
			String tipo = reg.getString();
			String codigoRelacion = reg.getString();
			int stock = reg.getInt();
			String fechaVenta = reg.getString();
			String condicion = reg.getString();
			String emailDueño = reg.getString();
			if (tipo.equals("MERCHANDISING")) {
				Producto producto = new Producto(SKU, nombre, precio, codigoRelacion, stock);
				sistema.AñadirProducto(producto);
			} else if (tipo.equals("FIGURE")) {
				Date fechaActual1 = formato.parse(fechaActual);
				Date fechaFigura = formato.parse(fechaVenta);
				if (fechaFigura.after(fechaActual1)) {
					condicion = "pre-venta";
					precio -= precio * 0.2;
					//StdOut.print("SKU: " + SKU + "; Precio: " + precio + "; Condicion: " + condicion);
				}
				Producto producto = new Figura(SKU, nombre, precio, codigoRelacion, stock,fechaVenta, condicion);
				sistema.AñadirProducto(producto);
			} else {
				Cliente dueño = (Cliente)sistema.getUsuarios().BuscarUsuario(emailDueño);
				Producto producto = new FiguraUsada(SKU, nombre, precio, codigoRelacion, stock,fechaVenta, condicion, dueño);
				sistema.AñadirProducto(producto);
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
			StdOut.println("\nEscribe tu correo\n");
			String email = scanner.nextLine();
			StdOut.println("\nEscribe tu contraseña\n");
			String contraseña = scanner.nextLine();
			StdOut.println("\nEscribe tu nombre\n");
			String nombre = scanner.nextLine();
			StdOut.println("\nEscribe tu pais\n");
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
		StdOut.println("\nIngresa tu email porfavor\n");
		String email = scanner.nextLine();
		StdOut.println("\nIngresa tu contraseña\n");
		String contraseña = scanner.nextLine();
		Usuario usuario = sistema.getUsuarios().BuscarUsuario(email, contraseña);
		if(usuario == null) {
			StdOut.println("\n XXXXXXX LA CONTRASEÑA QUE INGRESASTE ES INCORRECTA O EL USUARIO NO EXISTE, INTENTALO DE NUEVO XXXXXXX\n");
		}
		else{
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
			StdOut.println("\n◄ Bienvenido(a) de vuelta " + usuario.getNombre() +" ►\n");
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
			StdOut.println("\n♦ Bienvenido(a) de vuelta " + usuario.getNombre() +" ♦\n");
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
						continue;
					case 2:
						AñadirNuevoStock(sistema);
						continue;
					case 3:
						EliminarUsuario(sistema);
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
			StdOut.println("\nEscribe su correo\n");
			String email = scanner.nextLine();
			StdOut.println("\nEscribe su contraseña\n");
			String contraseña = scanner.nextLine();
			StdOut.println("\nEscribe su nombre\n");
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
	
	public static void EliminarUsuario(EmiEmiImpl sistema) {
		Scanner scanner = new Scanner(System.in);
		boolean salir4 = false;
		while (!salir4) {
			StdOut.println("\nIngrese el email del cliente que desea eliminar (-1 para cancelar)\n");
			String email = scanner.nextLine();
			if (email.equals("-1")) {
				salir4 = true;
			} else {
				Usuario usuario = sistema.getUsuarios().BuscarUsuario(email);
				if (usuario == null) {
					StdOut.println("\n XXXXXXX EL USUARIO NO EXISTE, INTENTALO DE NUEVO XXXXXXX\n");
				} else if (!(usuario instanceof Cliente)) {
					StdOut.println("\n XXXXXXX EL USUARIO NO ES UN CLIENTE, INTENTALO DE NUEVO XXXXXXX\n");
				} else {
					boolean salir5 = false;
					while(!salir5) {
						try {
							StdOut.println("\n" + ((Cliente)usuario).deployCliente() + "\n");
							StdOut.println("¿Desea eliminar a este cliente?\n");
							StdOut.println("1. Si");
							StdOut.println("2. No\n");
							int opcion = scanner.nextInt();
							switch (opcion) {
								case 1:
									StdOut.println("\nLa cuenta del cliente " + usuario.getNombre() + " fue eliminada\n");
									sistema.EliminarUsuario(usuario);
									salir5 = true;
									break;
								case 2:
									StdOut.println("\nEliminacion abortada\n");
									salir5 = true;
									break;
								default:
									StdOut.println("\nXXXXXXXXXXXX Escribe una opción valida porfavor XXXXXXXXXXXXX\n");
							}
						}	catch(InputMismatchException e) {
							StdOut.println("\nXXXXXXXXX ESCRIBE UN NÚMERO PORFAVOR XXXXXXXXXXXX\n");
							scanner.nextLine();
						}
					}
					salir4 = true;
				}
			}
		}
	}
	
	public static void AñadirNuevoStock(EmiEmiImpl sistema) {
		Scanner scanner = new Scanner(System.in);
		boolean salir6 = false;
		while (!salir6) {
			StdOut.println("\nIngrese el SKU del producto al cual desea aumentar el stock (-1 para cancelar)\n");
			String SKU = scanner.nextLine();
			if (SKU.equals("-1")) {
				salir6 = true;
			} else {
				Producto producto = sistema.BuscarProducto(SKU);
				if (producto != null) {
					StdOut.println("\nProducto encontrado = SKU: " + producto.getSKU() + "; Nombre: " + producto.getNombre() + "; Stock actual: " + producto.getStock() + "\n");
					StdOut.println("¿Cuantas unidades desea agregar?\n");
					boolean salir7 = false;
					int stockAgregar = scanner.nextInt();
					while (!salir7) {
						try {
							while (stockAgregar <= 0) {
								StdOut.println("\nXXXXXXXXXXXX Escribe una opción valida porfavor XXXXXXXXXXXXX\n");
								stockAgregar = scanner.nextInt();
							}
							salir7 = true;
						} catch(InputMismatchException e) {
							StdOut.println("\nXXXXXXXXX ESCRIBE UN NÚMERO PORFAVOR XXXXXXXXXXXX\n");
							scanner.nextLine();
						}
					}
					sistema.AñadirStock(producto, stockAgregar);
					StdOut.println("\nStock Añadido\n");
					salir6 = true;
				} else {
					DateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
					String fechaActual = formato.format(new Date());
					boolean salir8 = false;
					while (!salir8) {
					try {
							StdOut.println("\nIngrese los datos para añadir el nuevo producto porfavor:\n");
							StdOut.println("\nEscribe el nombre del producto\n");
							String nombre = scanner.nextLine();
							StdOut.println("\nEscribe el precio del producto\n");
							int precio = scanner.nextInt();
							StdOut.println("\nEscribe el codigo de relacion del prodcuto (000 para indicar que no hay productos relacionados)\n");
							String codigoRelacion = scanner.nextLine();
							StdOut.println("\nEscriba el stock inicial del producto\n");
							int stock = -1;
							
							boolean salir11 = false;

							while(stock <= -1) {
								try {
									stock = scanner.nextInt();
								} catch (InputMismatchException e) {
									StdOut.println("\nXXXXXXXXX INGRESE UN STOCK VALIDO PORFAVOR XXXXXXXXXXXX\n");
									scanner.nextInt();
								}
							}
							
							StdOut.println("\nEscribe la fecha de venta en el formato dd/MM/YYYY.\n(Si es una figura y la fecha de venta es superior a la fecha\nactual, automaticamente se pondra como pre-venta y se aplica el descuento del 20%");
							String fechaVenta = scanner.nextLine();
							String condicion = "new";
							
							boolean salir9 = false;
							
							while (!salir9) {
								try {
									Date fechaActual1 = formato.parse(fechaActual);
									Date fechaProducto = formato.parse(fechaVenta);
									if (fechaProducto.after(fechaActual1)) {
										condicion = "pre-venta";
										precio -= precio * 0.2;
										//StdOut.print("SKU: " + SKU + "; Precio: " + precio + "; Condicion: " + condicion);
									}
									salir9 = true;
								} catch (ParseException e) {
									StdOut.println("\nXXXXXXXXX ESCRIBE UNA FECHA VALIDA PORFAVOR XXXXXXXXXXXX\n");
									fechaVenta = scanner.nextLine();
								}
							}
							
							boolean salir10 = false;
							
							while(!salir10) {
								try {
									StdOut.println("\nEscribe el tipo de producto (MERCHANDISIG o FIGURE)");
									String tipo = scanner.nextLine().toUpperCase();
									switch (tipo) {
									
										case "MARCHANDISING":
											Producto productoAgregar = new Producto (SKU, nombre, precio, codigoRelacion, stock);
											sistema.AñadirProducto(productoAgregar);
											StdOut.println("\nProducto agregado al inventario de la tienda\n");
											salir10 = true;
											break;
										case "FIGURE":
											Producto productoAgregar1 = new Figura (SKU, nombre, precio, codigoRelacion, stock, fechaVenta, condicion);
											sistema.AñadirProducto(productoAgregar1);
											StdOut.println("\nProducto agregado al inventario de la tienda\n");
											salir10 = true;
											break;
										default:
											StdOut.println("\nXXXXXXXXXXXX Escribe una opción valida porfavor XXXXXXXXXXXXX\n");		
									}
								} catch(InputMismatchException e) {
									StdOut.println("\nXXXXXXXXX ESCRIBE UNA PALABRA PORFAVOR XXXXXXXXXXXX\n");
									scanner.nextLine();
								}					
							}				
						} catch(InputMismatchException e) {
							StdOut.println("\nXXXXXXXXX ESCRIBE UNA PALABRA PORFAVOR XXXXXXXXXXXX\n");
							scanner.nextLine();
						}
						salir8 = true;
					}
				}
			}
			salir6 = true;
		}
	}
}


