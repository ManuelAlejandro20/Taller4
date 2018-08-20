package ucn;

import java.util.ArrayList;
import java.util.Iterator;

public class EmiEmiImpl implements SistemaEmiEmi {
	
	private ListaUsuario usuarios;
	private ArrayList<Producto> productos;
	
	public EmiEmiImpl() {
		this.usuarios = new ListaUsuario();
		this.productos = new ArrayList<Producto>();
	}

	@Override
	public boolean RegistrarUsuario(Usuario usuario) {
		return usuarios.RegistrarUsuario(usuario);
	}
	
	@Override
	public boolean EliminarUsuario(Usuario usuario) {
		return this.usuarios.EliminarUsuario(usuario);
	}
	
	@Override
	public void AñadirProducto(Producto producto) {
		this.productos.add(producto);
	}
	
	@Override
	public void AñadirStock(Producto producto, int stock) {
		producto.AgregarStock(stock);
	}
	
	//region Generics List Methods -- Why don't you work like Visual Studio? D:<
	
	public Producto BuscarProducto (String SKU) {
		
		Iterator<Producto> it = productos.iterator();
		
		while (it.hasNext()) {
			Producto producto = (Producto)it.next();
			if (producto.getSKU().equals(SKU)) {
				return producto;
			}
		}
		
		return null;
	}
	
	//enregion 
	public ListaUsuario getUsuarios () {
		return this.usuarios;
	}
}
