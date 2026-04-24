package com.example.demo.desacoplamiento.controller;

import com.example.demo.desacoplamiento.model.Pedido;
import com.example.demo.desacoplamiento.service.PedidoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoControllerIoC {

    private final PedidoService pedidoService;

    // Inyectamos el servicio mediante el constructor
    public PedidoControllerIoC(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/api/pedido-desacoplado")
    public String crearPedidoDesacoplado() {
        Pedido p = new Pedido();
        pedidoService.guardar(p);
        return "Pedido guardado exitosamente. Revisa la consola.";
    }
}
