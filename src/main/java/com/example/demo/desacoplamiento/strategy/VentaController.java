package com.example.demo.desacoplamiento.strategy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping("/calcular")
    public String calcularDescuento(@RequestParam double precio) {
        double total = ventaService.calcularTotal(precio);
        return "El precio original era: $" + precio + "\n" +
               "Con la estrategia aplicada, el total a pagar es: $" + total;
    }
}
