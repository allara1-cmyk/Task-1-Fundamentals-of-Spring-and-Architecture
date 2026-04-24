package com.example.demo.desacoplamiento.strategy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("descuentoNavidad")
public class DescuentoNavidad implements DescuentoStrategy {
    
    @Override
    public double aplicar(double precio) {
        // Aplica un 20% de descuento (multiplica por 0.8)
        return precio * 0.8;
    }
}
