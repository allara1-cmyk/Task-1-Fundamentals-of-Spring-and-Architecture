package com.example.demo.desacoplamiento.strategy;

import org.springframework.stereotype.Component;

@Component("descuentoBlackFriday")
public class DescuentoBlackFriday implements DescuentoStrategy {
    
    @Override
    public double aplicar(double precio) {
        // Aplica un 50% de descuento (multiplica por 0.5)
        return precio * 0.5;
    }
}
