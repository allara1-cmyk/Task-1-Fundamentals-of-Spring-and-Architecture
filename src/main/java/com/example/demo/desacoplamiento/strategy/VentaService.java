package com.example.demo.desacoplamiento.strategy;

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
        // El servicio de venta delega la lógica del descuento a la estrategia inyectada
        return strategy.aplicar(precioOriginal);
    }
}
