package py.una.bd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import py.una.entidad.Pedido;

public class PedidoDAO {

    public void insertar(Pedido pedido) {
        String sql = "INSERT INTO pedido (estado) VALUES (?) RETURNING id_pedido";
        // Asumiendo que la clase Bd maneja la conexión
        try (Connection conn = Bd.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pedido.getEstado());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                pedido.setIdPedido(rs.getInt("id_pedido"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarIdEnvio(Integer idPedido, Integer idEnvio) {
        String sql = "UPDATE pedido SET id_envio = ? WHERE id_pedido = ?";
        try (Connection conn = Bd.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEnvio);
            pstmt.setInt(2, idPedido);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}