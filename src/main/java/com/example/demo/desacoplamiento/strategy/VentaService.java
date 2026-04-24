package com.example.demo.desacoplamiento.strategy;

import com.example.demo.desacoplamiento.exceptions.PrecioInvalidoException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    private final DescuentoStrategy strategy;
    
    // Inyectamos la estrategia. Por defecto usamos Navidad, 
    // pero gracias al polimorfismo podemos inyectar la de BlackFriday si queremos.
    public VentaService(@Qualifier("descuentoNavidad") DescuentoStrategy strategy) {
        this.strategy = strategy;
    }

    public double calcularTotal(double precioOriginal) {
        // Lanzar excepción que será manejada de forma GLOBAL
        if (precioOriginal <= 0) {
            throw new PrecioInvalidoException("El precio original no puede ser menor o igual a cero.");
        }
        
        // Lanzar excepción que será manejada de forma LOCAL (en el controlador)
        if (precioOriginal > 10000) {
            throw new IllegalArgumentException("El precio supera el límite máximo permitido para el sistema.");
        }

        // El servicio de venta delega la lógica del descuento a la estrategia inyectada
        return strategy.aplicar(precioOriginal);
    }
}
