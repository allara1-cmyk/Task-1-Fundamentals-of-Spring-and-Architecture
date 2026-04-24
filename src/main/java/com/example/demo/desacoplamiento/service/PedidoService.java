package com.example.demo.desacoplamiento.service;

import com.example.demo.desacoplamiento.model.Pedido;
import com.example.demo.desacoplamiento.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository repo;

    public PedidoService(PedidoRepository repo) {
        this.repo = repo;
    }

    public void guardar(Pedido p) {
        repo.save(p);
    }
}
