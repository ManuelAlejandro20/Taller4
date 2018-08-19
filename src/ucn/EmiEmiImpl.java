package ucn;

public class EmiEmiImpl implements EmiEmi {
	ListaUsuario usuarios;
	
	public EmiEmiImpl() {
		usuarios = new ListaUsuario();
	}

	@Override
	public boolean RegistrarUsuario(Usuario usuario) {
		return usuarios.RegistrarUsuario(usuario);
	}

}
