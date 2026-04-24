package com.example.demo.desacoplamiento.strategy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @GetMapping("/buscar")
    public String buscarVenta(@RequestParam String id) {
        // Simulamos que el recurso no existe para disparar el HTTP 404
        if (id.equals("0")) {
            throw new com.example.demo.desacoplamiento.exceptions.RecursoNoEncontradoException("No se encontró ninguna venta con el ID: " + id);
        }
        return "Venta encontrada: " + id;
    }

    @GetMapping("/borrar")
    public String borrarVenta(@RequestParam String id) {
        // Simulamos que no tiene permisos para disparar el HTTP 403
        throw new com.example.demo.desacoplamiento.exceptions.OperacionNoPermitidaException("No tienes rol de Administrador para borrar ventas.");
    }

    // ENDPOINT PARA PROBAR EL INTERCEPTOR DE AUTENTICACIÓN
    @GetMapping("/seguro")
    public String endpointSeguro() {
        return "¡Felicidades! Tienes el Header de Auth correcto y pasaste el Interceptor.";
    }

    // MANEJO LOCAL DE EXCEPCIONES:
    // Este método solo atrapará IllegalArgumentException si se lanza
    // DENTRO de este controlador (o los servicios que él llama).
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleLocalException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("ERROR LOCAL EN VENTAS: " + ex.getMessage());
    }
}
