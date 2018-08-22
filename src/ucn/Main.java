package ucn;
import java.util.*;
import Excepciones.ExcepcionUsuarioRepetido;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
				if (sistema.BuscarPais(pais) == null) {
					Pais nuevoPais = new Pais(pais);
					sistema.AñadirPais(nuevoPais);
				}
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
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaActual = LocalDate.parse(LocalDate.now().format(formato), formato);
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
				LocalDate fechaFigura = LocalDate.parse(fechaVenta, formato);
				if (fechaFigura.isAfter(fechaActual)) {
					condicion = "pre-venta";
					precio -= precio * 0.2;
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
				if (sistema.BuscarPais(pais) == null) {
					Pais nuevoPais = new Pais(pais);
					sistema.AñadirPais(nuevoPais);
				}
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
						BuscarProductos(sistema);
						continue;
					case 2:
						DesplegarCatalogo(sistema);
						continue;
					case 3:
						continue;
					case 4:
						continue;
					case 5:
						AgregarFiguraUsada(sistema, usuario);
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
						ExportarDatos(sistema);
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
		boolean salir = false;
		while (!salir) {
			StdOut.println("\nIngrese el email del cliente que desea eliminar (-1 para cancelar)\n");
			String email = scanner.nextLine();
			if (email.equals("-1")) {
				salir = true;
			} else {
				Usuario usuario = sistema.getUsuarios().BuscarUsuario(email);
				if (usuario == null) {
					StdOut.println("\n XXXXXXX EL USUARIO NO EXISTE, INTENTALO DE NUEVO XXXXXXX\n");
				} else if (!(usuario instanceof Cliente)) {
					StdOut.println("\n XXXXXXX EL USUARIO NO ES UN CLIENTE, INTENTALO DE NUEVO XXXXXXX\n");
				} else {
					boolean salir2 = false;
					while(!salir2) {
						try {
							StdOut.println("\n" + ((Cliente)usuario).deployUsuario() + "\n");
							StdOut.println("¿Desea eliminar a este cliente?\n");
							StdOut.println("1. Si");
							StdOut.println("2. No\n");
							int opcion = scanner.nextInt();
							switch (opcion) {
								case 1:
									StdOut.println("\nLa cuenta del cliente " + usuario.getNombre() + " fue eliminada\n");
									sistema.EliminarUsuario(usuario);
									salir2 = true;
									break;
								case 2:
									StdOut.println("\nEliminacion abortada\n");
									salir2 = true;
									break;
								default:
									StdOut.println("\nXXXXXXXXXXXX Escribe una opción valida porfavor XXXXXXXXXXXXX\n");
							}
						}	catch(InputMismatchException e) {
							StdOut.println("\nXXXXXXXXX ESCRIBE UN NÚMERO PORFAVOR XXXXXXXXXXXX\n");
							scanner.nextLine();
						}
					}
					salir = true;
				}
			}
		}
	}
	
	public static void AñadirNuevoStock(EmiEmiImpl sistema) {
		Scanner scanner = new Scanner(System.in);
		boolean salir = false;
		while (!salir) {
			StdOut.println("\nIngrese el SKU del producto al cual desea aumentar el stock (-1 para cancelar)\n");
			String SKU = scanner.nextLine();
			if (SKU.equals("-1")) {
				salir = true;
			} else {
				Producto producto = sistema.BuscarProducto(SKU);
				if (producto != null) {
					StdOut.println("\nProducto encontrado = SKU: " + producto.getSKU() + "; Nombre: " + producto.getNombre() + "; Stock actual: " + producto.getStock() + "\n");
					StdOut.println("¿Cuantas unidades desea agregar?\n");
					boolean salir2 = false;
					int stockAgregar = scanner.nextInt();
					while (!salir2) {
						try {
							while (stockAgregar < 0) {
								StdOut.println("\nXXXXXXXXXXXX Escribe una opción valida porfavor XXXXXXXXXXXXX\n");
								stockAgregar = scanner.nextInt();
							}
							salir2 = true;
						} catch(InputMismatchException e) {
							if (stockAgregar < 0) {
								StdOut.println("\nXXXXXXXXX VERIFICA QUE SEA UN NUMERO PORFAVOR XXXXXXXXXXXX\n");
							} else {
							StdOut.println("\nXXXXXXXXX ESCRIBE UN NÚMERO PORFAVOR XXXXXXXXXXXX\n");
							}
							scanner.nextLine();
						}
					}
					sistema.AñadirStock(producto, stockAgregar);
					StdOut.println("\nStock Añadido\n");
					salir = true;
				} else {
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate fechaActual = LocalDate.parse(LocalDate.now().format(formato), formato);
					boolean salir3 = false;
					while (!salir3) {
					try {
							StdOut.println("\nIngrese los datos para añadir el nuevo producto porfavor:\n");
							StdOut.println("\nEscribe el nombre del producto\n");
							String nombre = scanner.nextLine();
							StdOut.println("\nEscribe el precio del producto\n");
							int precio = scanner.nextInt();
							scanner.nextLine();
							StdOut.println("\nEscribe el codigo de relacion del prodcuto (000 para indicar que no hay productos relacionados)\n");
							String codigoRelacion = scanner.nextLine();
							StdOut.println("\nEscriba el stock inicial del producto\n");
							int stock = -1;

							while(stock <= -1) {
								try {
									stock = scanner.nextInt();
									scanner.nextLine();
								} catch (InputMismatchException e) {
									StdOut.println("\nXXXXXXXXX INGRESE UN STOCK VALIDO PORFAVOR XXXXXXXXXXXX\n");
									scanner.nextInt();
								}
							}
							
							StdOut.println("\nEscribe la fecha de venta en el formato dd/MM/YYYY.\n(Si es una figura y la fecha de venta es superior a la fecha\nactual, automaticamente se pondra como pre-venta y se aplica el descuento del 20%\n");
							String fechaVenta = scanner.nextLine();
							String condicion = "new";
							
							boolean salir4 = false;
							
							while (!salir4) {
								try {
									LocalDate fechaFigura = LocalDate.parse(fechaVenta, formato);
									if (fechaFigura.isAfter(fechaActual)) {
										condicion = "pre-venta";
										precio -= precio * 0.2;
									}
									salir4 = true;
								} catch (DateTimeParseException e) {
									StdOut.println("\nXXXXXXXXX ESCRIBE UNA FECHA VALIDA PORFAVOR XXXXXXXXXXXX\n");
									fechaVenta = scanner.nextLine();
								}
							}
							
							String []fechaVector = fechaVenta.split("/");
							int añoConfirmacion = Integer.parseInt(fechaVector[2]);
							if(añoConfirmacion >= 2040) {
								StdOut.println("\nEspero que estes vivo para cuando este producto se venda\n");
							} else if (añoConfirmacion >= 2022) {
								StdOut.println("\n¿Para ese entonces no deberia de existir SAO?, espero que logres vender este producto");
							} else if (añoConfirmacion <= 1950) {
								StdOut.println("\nTienes una reliquia entre manos, felicidades\n");
							}
							
							boolean salir5 = false;
							
							while(!salir5) {
								try {
									StdOut.println("\nEscribe el tipo de producto (MERCHANDISING o FIGURE)");
									String tipo = scanner.nextLine().toUpperCase();
									switch (tipo) {
									
										case "MERCHANDISING":
											Producto productoAgregar = new Producto (SKU, nombre, precio, codigoRelacion, stock);
											sistema.AñadirProducto(productoAgregar);
											StdOut.println("\nProducto agregado al inventario de la tienda\n");
											salir5 = true;
											break;
										case "FIGURE":
											Producto productoAgregar1 = new Figura (SKU, nombre, precio, codigoRelacion, stock, fechaVenta, condicion);
											sistema.AñadirProducto(productoAgregar1);
											StdOut.println("\nProducto agregado al inventario de la tienda\n");
											salir5 = true;
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
						salir3 = true;
					}
				}
			}
			salir = true;
		}
	}
	
	public static void BuscarProductos(EmiEmiImpl sistema) {
		Scanner scanner = new Scanner(System.in);
		boolean salir = false;
		while (!salir) {
			StdOut.println("\nIngresa una palabra o frase de entre 4 a 20 caracteres, relacionada con el producto buscado (-1 para cancelar)\n");
			String fraseClave = scanner.nextLine();
			fraseClave = fraseClave.replaceAll(" ", "");
			if (fraseClave.equals("-1")) {
				salir = true;
			}
			if (fraseClave.length() >= 4 && fraseClave.length() <= 20) {
				ArrayList<Producto> productosEncontrados = sistema.BuscarProductos(fraseClave);
				if (productosEncontrados.isEmpty()) {
					StdOut.println("\nNo hay productos que coincidan con la busqueda\n");
					salir = true;
				} else {
					Iterator<Producto> it = productosEncontrados.iterator();
					while (it.hasNext()) {
						Producto producto = (Producto)it.next();
						if (producto instanceof Figura) {
							StdOut.println("\n- " + ((Figura)producto).deployProducto() + "\n");
						} else if (producto instanceof FiguraUsada) {
							StdOut.println("\n- " + ((FiguraUsada)producto).deployProducto() + "\n");
						} else {
							StdOut.println("\n- " + producto.deployProducto() + "\n");
						}
					}
					salir = true;
				}
			} else {
				StdOut.println("\nXXXXXXXXXXXX La palabra o frase que ingresaste es muy pequeña o demaciado larga XXXXXXXXXXXXX\n");
			}
		}
	}
	
	public static void DesplegarCatalogo (EmiEmiImpl sistema) {
		Scanner scanner = new Scanner(System.in);
		boolean salir = false;
		while (!salir) {
			try {
				StdOut.println("\n¿Que desea desplegar? (-1 para cancelar)\n");
				StdOut.println("1. Mercancia");
				StdOut.println("2. Figuras\n");
				int opcion = scanner.nextInt();
				switch (opcion) {
					case 1:
						StdOut.println("\n¿Desea mostrar los productos fuera de stock?\n");
						StdOut.println("1. Si");
						StdOut.println("2. No\n");
						boolean salir2 = false;
						boolean opcionIncorrecta = false;
						while (!salir2) {
							Scanner scanner2 = new Scanner(System.in);
							int opcion2 = scanner2.nextInt();
							switch (opcion2) {
								case 1:
									sistema.DesplegarMercancias(false);
									break;
								case 2:
									sistema.DesplegarMercancias(true);
									break;
								case (-1):
									salir2 = true;
									continue;
								default:
									StdOut.println("\nXXXXXXXXXXXX Escribe una opción valida porfavor XXXXXXXXXXXXX\n");
									opcionIncorrecta = true;
							}
							if (opcionIncorrecta) {
								StdOut.println("\n¿Desea mostrar los productos fuera de stock?\n");
								opcionIncorrecta = false;
							} else {
								StdOut.println("\n¿Desea mostrar los productos fuera de stock? (-1 para terminar)\n");
							}
							StdOut.println("1. Si");
							StdOut.println("2. No\n");
						}
						salir = true;
						break;
					case 2:
						StdOut.println("\n¿Desea mostrar los productos fuera de stock?\n");
						StdOut.println("1. Si");
						StdOut.println("2. No\n");
						boolean opcionIncorrecta2 = false;
						boolean salir3 = false;
						while (!salir3) {
							Scanner scanner2 = new Scanner(System.in);
							int opcion2 = scanner2.nextInt();
							switch (opcion2) {
								case 1:
									sistema.DesplegarFiguras(false);
									break;
								case 2:
									sistema.DesplegarFiguras(true);
									break;
								case (-1):
									salir3 = true;
									continue;
								default:
									StdOut.println("\nXXXXXXXXXXXX Escribe una opción valida porfavor XXXXXXXXXXXXX\n");
									opcionIncorrecta2 = true;
							}
							if (opcionIncorrecta2) {
								StdOut.println("\n¿Desea mostrar los productos fuera de stock?\n");
								opcionIncorrecta2 = false;
							} else {
								StdOut.println("\n¿Desea mostrar los productos fuera de stock? (-1 para terminar)\n");
							}
							StdOut.println("1. Si");
							StdOut.println("2. No\n");
						}
						salir = true;
						break;
					case -1:
						salir = true;
					default:
						StdOut.println("\nXXXXXXXXXXXX Escribe una opción valida porfavor XXXXXXXXXXXXX\n");
				}
			} catch (InputMismatchException e) {
				StdOut.println("\nXXXXXXXXX ESCRIBE UN NUMERO PORFAVOR XXXXXXXXXXXX\n");
				scanner.nextLine();
			}	
		}
	}
	
	public static void AgregarFiguraUsada (EmiEmiImpl sistema, Usuario dueño) {
		boolean salir = false;
		while (!salir) {
			StdOut.println("\nEscriba el SKU de la figura usada que desea abonar (-1 para cancelar)\n");
			String SKU = StdIn.readString();
			if (SKU.equals("-1")) {
				salir = true;
			}
			if (sistema.BuscarProducto(SKU) != null && (sistema.BuscarProducto(SKU) instanceof Figura)){
				Figura figuraEnTienda = (Figura)sistema.BuscarProducto(SKU);
				if (!figuraEnTienda.getCondicion().equals("new")) {
					StdOut.println("\nXXXXXXXXX NO SE PUEDEN ABONAR FIGURAS EN ESTADO DE PRE-VENTA O QUE YA ESTEN ABONADAS XXXXXXXXXXXX\n");
					return;
				} else {
					StdOut.println("\nFigura encontrada = SKU: " + figuraEnTienda.getSKU() + "; Nombre: " + figuraEnTienda.getNombre() + "; Precio en tienda: " + figuraEnTienda.getPrecio() + "\n");
					StdOut.println("\nIngresa los siguientes datos para poder abonar la figura\n");
					String condicion = "";
					int precio = 0;
					boolean salir2 = false;
					while (!salir2) {
						Scanner scanner = new Scanner(System.in);
						try {
							StdOut.println("\nCondicion de la figura\n");
							StdOut.println("A. Sellado");
							StdOut.println("B. Aparentemente nuevo");
							StdOut.println("C. Abierto");
							StdOut.println("D. Daños menores");
							StdOut.println("E. Daños visibles");
							StdOut.println("F. Piezas extraviadas\n");
							condicion = scanner.nextLine().toUpperCase();
							boolean letraEquivocada = true;
							switch (condicion) {
								case "A":
									letraEquivocada = false;
									break;
								case "B":
									letraEquivocada = false;
									break;
								case "C":
									letraEquivocada = false;
									break;
								case "D":
									letraEquivocada = false;
									break;
								case "E":
									letraEquivocada = false;
									break;
								case "F":
									letraEquivocada = false;
									break;
								default:
									StdOut.println("\nXXXXXXXXXXXX Escribe una opción valida porfavor XXXXXXXXXXXXX\n");
							}
							if (letraEquivocada) {
								StdOut.println();
							} else {
								StdOut.println("\nIngresa el precio que le pondras a tu figura\n");
								precio = scanner.nextInt();
								salir2 = true;
							}
						} catch (InputMismatchException e) {
							StdOut.println("\nXXXXXXXXX ESCRIBE UN NUMERO PORFAVOR XXXXXXXXXXXX\n");
							scanner.nextLine();
						}
					}
					sistema.AñadirFiguraUsada(SKU, condicion, precio, (Cliente)dueño);
					StdOut.println("\nSu figura fue agregada\n");
					salir = true;
				}
			} else {
				StdOut.print("\nXXXXXXXXX LA FIGURA NO EXISTE O EL SKU INGRESADO NO CORRESPONDE A UNA FIGURA XXXXXXXXXXXX\n");
				salir = true;
			}
		}
	}

	public static void ExportarDatos(EmiEmiImpl sistema) {
		sistema.ExportarDatos();
		StdOut.println("\n✓✓✓✓✓✓ Los datos del sistema fueron desplegados ✓✓✓✓✓✓\n");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


