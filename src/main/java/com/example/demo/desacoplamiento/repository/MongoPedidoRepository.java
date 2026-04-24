package com.example.demo.desacoplamiento.repository;

import com.example.demo.desacoplamiento.model.Pedido;
import org.springframework.stereotype.Repository;

@Repository("mongoPedidoRepository")
public class MongoPedidoRepository implements PedidoRepository {
    
    @Override
    public void save(Pedido p) {
        System.out.println("Guardado en MongoDB (Desacoplado)");
    }
}
