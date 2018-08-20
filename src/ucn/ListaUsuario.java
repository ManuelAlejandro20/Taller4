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
	
	public boolean EliminarUsuario(Usuario usuario) {
		if (!ExisteUsuario(usuario)) {
			return false;
		} 
			
		if (this.head.getUsuario().getEmail().equals(usuario.getEmail())) {
			if(this.head.getSig() != null) {
				this.head.getSig().setPreb(null);
				return true;
			} else {
				this.head = null;
				return true;
			}
		}
		
		NodoDobleUsuario aux = this.head.getSig();
		while (aux.getSig() != null) {
			if (aux.getUsuario().getEmail().equals(usuario.getEmail())) {	
				aux.getPreb().setSig(aux.getSig());
				aux.getSig().setPreb(aux.getPreb());
				this.cant--;
				return  true;
			}
			aux = aux.getSig();
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
			return null;
		}
		return null;
	}
	
	public Usuario BuscarUsuario(String email) {
		if(head != null) {
			NodoDobleUsuario aux = this.head;
			for(int i=0; i<cant; i++) {
				Usuario usuario = aux.getUsuario();
				if(usuario.getEmail().equals(email)) {
					return usuario;
				}
				aux = aux.getSig();
			}
			return null;
		}
		return null;
	}
	
	
	public int getCant() {return this.cant;}
}
