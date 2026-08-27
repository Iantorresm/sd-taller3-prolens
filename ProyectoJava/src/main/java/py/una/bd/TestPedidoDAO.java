package py.una.bd;
import py.una.entidad.Pedido;

public class TestPedidoDAO {
	public static void main(String[] args) {
		PedidoDAO dao = new PedidoDAO();

		// Prueba 1: Crear pedido
		Pedido nuevoPedido = new Pedido(null, null, "PENDIENTE");
		try {
			dao.getClass()
				.getMethod("insertar", nuevoPedido.getClass())
				.invoke(dao, nuevoPedido);
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException("No se pudo insertar el pedido", e);
		}
		System.out.println("Pedido insertado con ID: " + nuevoPedido.getIdPedido());

		// Prueba 2: Actualizar con ID de envío simulado
		if (nuevoPedido.getIdPedido() != null) {
			dao.actualizarIdEnvio(nuevoPedido.getIdPedido(), 999);
			System.out.println("ID de envío actualizado correctamente.");
		}
	}
}