package com.example.demo.desacoplamiento.repository;

import com.example.demo.desacoplamiento.model.Pedido;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("prod")
@Repository
public class MongoPedidoRepository implements PedidoRepository {
    
    @Override
    public void save(Pedido p) {
        System.out.println("Guardado en MongoDB (Desacoplado - Perfil PROD)");
    }
}
