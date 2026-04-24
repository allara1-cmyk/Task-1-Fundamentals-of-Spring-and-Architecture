package com.example.demo.desacoplamiento.pago;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping("/procesar")
    public String procesarPago() {
        pagoService.pagar();
        return "El pago ha sido procesado. Por favor, revisa la consola para ver qué Gateway se utilizó.";
    }
}
