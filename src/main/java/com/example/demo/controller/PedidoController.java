package com.example.demo.controller;

import com.example.acoplamiento_fuerte.Pedido;
import com.example.acoplamiento_fuerte.PedidoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {

    @GetMapping("/api/pedido")
    public String crearPedido() {
        PedidoService service = new PedidoService();
        Pedido p = new Pedido();
        service.guardar(p);
        return "Pedido guardado exitosamente (revisa la consola para ver el mensaje de MysqlPedidoRepository)";
    }
}
