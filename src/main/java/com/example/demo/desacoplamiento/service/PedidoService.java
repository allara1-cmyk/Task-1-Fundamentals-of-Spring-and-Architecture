package com.example.demo.desacoplamiento.service;

import com.example.demo.desacoplamiento.model.Pedido;
import com.example.demo.desacoplamiento.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository mysqlRepo;
    private final PedidoRepository mongoRepo;

    // Aquí inyectamos las dependencias a través del constructor (IoC)
    // Usamos @Qualifier para decirle a Spring exactamente cuál de las implementaciones queremos en cada caso.
    // Aunque MysqlPedidoRepository es @Primary, el @Qualifier tiene mayor prioridad y nos permite elegir.
    public PedidoService(
            @Qualifier("mysqlPedidoRepository") PedidoRepository mysqlRepo,
            @Qualifier("mongoPedidoRepository") PedidoRepository mongoRepo) {
        this.mysqlRepo = mysqlRepo;
        this.mongoRepo = mongoRepo;
    }

    public void guardarEnMysql(Pedido p) {
        mysqlRepo.save(p);
    }

    public void guardarEnMongo(Pedido p) {
        mongoRepo.save(p);
    }
}
