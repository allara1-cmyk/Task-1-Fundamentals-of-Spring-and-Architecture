package com.example.acoplamiento_fuerte;

public class PedidoService {
    private MysqlPedidoRepository repo = new MysqlPedidoRepository();

    public void guardar(Pedido p) {
        repo.save(p);
    }
}
