package ucn;

public interface SistemaEmiEmi {

	public boolean RegistrarUsuario(Usuario usuario);
	public boolean EliminarUsuario (Usuario usuario);
	public void AñadirProducto(Producto producto);
	public void AñadirStock(Producto producto, int stock);
	
}
