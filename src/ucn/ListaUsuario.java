package ucn;

public class ListaUsuario {
	private NodoDobleUsuario head;
	private int cant;
	
	public ListaUsuario(Usuario usuario) {
		this.head = new NodoDobleUsuario(usuario);
		this.cant++;
		
	}
	
	public void agregarusuario(Usuario usuario) {
		NodoDobleUsuario aux = head;
		NodoDobleUsuario nuevonodo = new NodoDobleUsuario(usuario);
		while(aux.getSig()!=null) {
			aux = aux.getSig();
		}
		aux.setSig(nuevonodo);
		nuevonodo.setPreb(aux);
		
	}
	
	public Usuario buscarusuario(String email, String contraseña) {
		if(head != null) {
			NodoDobleUsuario aux = this.head;
			for(int i=0; i<cant; i++) {
				Usuario usuario = aux.getUsuario();
				if(usuario.getEmail().equals(email) && usuario.getContraseña().equals(contraseña)) {
					return usuario;
				}
				aux = aux.getSig();
				
			}
			StdOut.println("\n XXXXXXX EL USUARIO NO EXISTE, INTENTALO DE NUEVO XXXXXXX\n");
			return null;
		}
		StdOut.println("\nXXXXXX EL USUARIO NO FUE ENCONTRADO, PORFAVOR INTENTALO DE NUEVO XXXXXXX\n");
		return null;
	}
}
