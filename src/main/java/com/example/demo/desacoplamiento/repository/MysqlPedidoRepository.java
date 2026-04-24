package com.example.demo.desacoplamiento.repository;

import com.example.demo.desacoplamiento.model.Pedido;
import org.springframework.stereotype.Repository;

@Repository("mysqlPedidoRepository")
public class MysqlPedidoRepository implements PedidoRepository {

    @Override
    public void save(Pedido p) {
        System.out.println("Guardado en MySQL (Desacoplado)");
    }
}
