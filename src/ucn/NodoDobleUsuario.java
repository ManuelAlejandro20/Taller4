package ucn;
/**
 * Nodo Doble que almacena un usuario
 * 
 * @author Matthew Gonzalez Mansilla - 
 *
 */
public class NodoDobleUsuario {

	// El Nodo siguiente a este
	private NodoDobleUsuario sig;
	
	// El Nodo anterir a este
	private NodoDobleUsuario preb;
	
	// El usuario que almacena
	private Usuario usuario;
	
	/**
	 * Rutina contructora para un nodo doble usuario
	 * 
	 * @param usuario:
	 * 				El usuario que almacenara
	 */
	public NodoDobleUsuario(Usuario usuario) {
		this.usuario = usuario;
		this.sig = null;
		this.preb = null;
	}
	
	/**
	 * @return El nodo siguiene a este
	 */
	public NodoDobleUsuario getSig() {
		return sig;
	}
	
	/**
	 * Cambia el nodo siguiete a este
	 * 
	 * @param sig:
	 * 			El nuevo nodo siguiente
	 */
	public void setSig(NodoDobleUsuario sig) {
		this.sig = sig;
	}

	/**
	 * @return El nodo anterior a este
	 */
	public NodoDobleUsuario getPreb() {
		return preb;
	}
	
	/**
	 * Cambia el nodo anterior a este
	 * 
	 * @param preb:
	 * 			El nuevo nodo anterior
	 */
	public void setPreb(NodoDobleUsuario preb) {
		this.preb = preb;
	}
	
	/**
	 * @return El usuario almacenado en este nodo
	 */
	public Usuario getUsuario() {
		return usuario;
	}
}
