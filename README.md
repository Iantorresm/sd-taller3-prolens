# Óptica Prolens (e-vision) - Sistema de Gestión y Pedidos

Este repositorio contiene el sistema interno de Óptica Prolens, encargado de gestionar los pedidos locales de los clientes y comunicarse mediante sockets (UDP y TCP) con el sistema de logística de Global Express.

## Tecnologías Utilizadas

* **Java8** (JDK 1.8+)
* **PostgreSQL** (Base de datos relacional)
* **Maven** (Gestión de dependencias)
* **json-simple** (Serialización y deserialización de JSON)

## Estructura del Proyecto

* `py.una.entidad`: Clases de dominio (`Pedido`) y sus conversores JSON (`PedidoJSON`).
* `py.una.bd`: Capa de persistencia (`PedidoDAO`, `Bd`) y pruebas unitarias (`TestPedidoDAO`).
* `py.una.server.tcp` / `udp`: Implementación de clientes y servidores de red.

## Configuración y Ejecución

1. Configura la conexión a tu base de datos PostgreSQL local en el archivo de configuración de conexión (`Bd.java`).
2. Ejecuta el script SQL correspondiente para crear la tabla `pedido`.
3. Ejecuta los tests de persistencia (`TestPedidoDAO`) para verificar la conexión.
4. Ejecuta los servicios de red (`TCPClient`, `TCPServer`, `UDPClient`) según los flujos requeridos.
