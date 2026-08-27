package py.una.entidad;

public class Pedido {
	private Integer idPedido;
	private Integer idEnvio;
	private String estado;

	public Pedido() {
	}

	public Pedido(Integer idPedido, Integer idEnvio, String estado) {
		this.idPedido = idPedido;
		this.idEnvio = idEnvio;
		this.estado = estado;
	}

	// Getters y Setters
	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio(Integer idEnvio) {
		this.idEnvio = idEnvio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}