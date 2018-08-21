package ucn;

import java.util.ArrayList;
import java.util.Iterator;

public class EmiEmiImpl implements SistemaEmiEmi {
	
	private ListaUsuario usuarios;
	private ArrayList<Producto> productos;
	private ArrayList<Pais> paises;
	
	public EmiEmiImpl() {
		this.usuarios = new ListaUsuario();
		this.productos = new ArrayList<Producto>();
		this.paises = new ArrayList<Pais>();
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
	
	@Override
	public ArrayList<Producto> BuscarProductos(String fraseClave){
		ArrayList<Producto> productosEncontrados = new ArrayList<Producto>();
		Iterator<Producto> it = this.productos.iterator();
		while (it.hasNext()) {
			Producto producto = (Producto)it.next();
			if (producto.getNombre().contains(fraseClave) || producto.getSKU().contains(fraseClave)) {
				productosEncontrados.add(producto);
			}
		}
		return productosEncontrados;
	}
	
	@Override
	public void DesplegarMercancias(boolean filtrarStock){
		ArrayList<Producto> productosADesplegar = new ArrayList<Producto>();
		Iterator<Producto> it = this.productos.iterator();
		while (it.hasNext()) {
			Producto producto = it.next();
			if (!(producto instanceof Figura) && !(producto instanceof FiguraUsada)) {
				if (filtrarStock) {
					if (producto.getStock() >= 1) {
						productosADesplegar.add((Producto)producto);
					}
				} else {
					productosADesplegar.add((Producto)producto);
				}
			}
		}
		Iterator<Producto> it2 = productosADesplegar.iterator();
		while (it2.hasNext()) {
			Producto producto = (Producto)it2.next();
			StdOut.println("\n- " + producto.deployProducto());
		}
	}
	
	@Override
	public void DesplegarFiguras(boolean filtrarStock){
		ArrayList<Figura> productosADesplegar = new ArrayList<Figura>();
		Iterator<Producto> it = this.productos.iterator();
		while (it.hasNext()) {
			Producto producto = (Producto)it.next();
			if (producto instanceof Figura && !(producto instanceof FiguraUsada)) {
				if (filtrarStock) {
					if (producto.getStock() >= 1) {
						productosADesplegar.add((Figura)producto);
					}
				} else {
					productosADesplegar.add((Figura)producto);
				}
			}
		}
		Iterator<Figura> it2 = productosADesplegar.iterator();
		while (it2.hasNext()) {
			Figura figura = (Figura)it2.next();
			StdOut.println("\n- " + figura.deployProducto());
		}
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
	
	public Pais BuscarPais (String nombre) {
		Iterator<Pais> it = paises.iterator();	
		while (it.hasNext()) {
			Pais pais = (Pais)it.next();
			if (pais.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				return pais;
			}
		}	
		return null;
	}
	
	public void AñadirPais (Pais pais) {
		this.paises.add(pais);
	}
	
	//enregion 
	
	public ListaUsuario getUsuarios () {
		return this.usuarios;
	}
}
