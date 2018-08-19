package ucn;

public class ListaUsuario {
	private NodoDobleUsuario head;
	private int cant;
	
	public ListaUsuario() {
		this.head = null;
		
	}
	
	
	public boolean RegistrarUsuario(Usuario usuario) {
		if(this.head == null) {
			this.head = new NodoDobleUsuario(usuario);
			this.cant++;
			return true;
		}
		if(!ExisteUsuario(usuario)) {
			NodoDobleUsuario aux = head;
			NodoDobleUsuario NodoUsuario = new NodoDobleUsuario(usuario);
			while(aux.getSig() != null) {
				aux = aux.getSig();
			}
			aux.setSig(NodoUsuario);
			NodoUsuario.setPreb(aux);
			this.cant++;
			return true;
			
		}
		return false;
	
		
	}
	
	
	public boolean ExisteUsuario(Usuario usuario) {
		if(this.head == null) {
			return false;	
		}
		NodoDobleUsuario aux = this.head;
		for(int i=0; i<cant; i++) {
			Usuario _usuario = aux.getUsuario();
			if(_usuario.getEmail().equals(usuario.getEmail())) {
				return true;
			}
			aux = aux.getSig();
		}
		return false;
		
	}
	
	public Usuario BuscarUsuario(String email, String contraseña) {
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
	
	public int getCant() {
		return this.cant;
	}
}
