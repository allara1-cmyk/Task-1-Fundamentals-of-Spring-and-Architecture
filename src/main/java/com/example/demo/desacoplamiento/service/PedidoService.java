package com.example.demo.desacoplamiento.service;

import com.example.demo.desacoplamiento.model.Pedido;
import com.example.demo.desacoplamiento.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository repo;

    // Aquí inyectamos la dependencia a través del constructor (IoC)
    // Usamos @Qualifier para decirle a Spring cuál de las implementaciones queremos usar
    public PedidoService(@Qualifier("mysqlPedidoRepository") PedidoRepository repo) {
        this.repo = repo;
    }

    public void guardar(Pedido p) {
        repo.save(p);
    }
}
