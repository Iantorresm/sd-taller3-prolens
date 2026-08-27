package py.una.entidad;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PedidoJSON {

    // Convierte un objeto Pedido a un String en formato JSON
    @SuppressWarnings("unchecked")
    public static String objetoString(Pedido pedido) {
        JSONObject obj = new JSONObject();
        obj.put("idPedido", pedido.getIdPedido());
        obj.put("idEnvio", pedido.getIdEnvio());
        obj.put("estado", pedido.getEstado());

        return obj.toJSONString();
    }

    // Convierte un String en formato JSON a un objeto Pedido
    public static Pedido stringObjeto(String str) throws Exception {
        Pedido pedido = new Pedido();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(str);
            JSONObject jObj = (JSONObject) obj;

            // json-simple lee los números enteros como Long, por eso se castean primero a
            // Long y luego a Integer
            if (jObj.get("idPedido") != null) {
                pedido.setIdPedido(((Long) jObj.get("idPedido")).intValue());
            }
            if (jObj.get("idEnvio") != null) {
                pedido.setIdEnvio(((Long) jObj.get("idEnvio")).intValue());
            }
            if (jObj.get("estado") != null) {
                pedido.setEstado((String) jObj.get("estado"));
            }

        } catch (ParseException e) {
            throw new Exception("Error parseando el JSON de Pedido: " + e.getMessage());
        }

        return pedido;
    }
}