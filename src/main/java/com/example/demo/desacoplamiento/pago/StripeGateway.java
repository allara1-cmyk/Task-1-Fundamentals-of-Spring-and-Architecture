package com.example.demo.desacoplamiento.pago;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("stripeGateway")
public class StripeGateway implements PagoGateway {
    
    @Override
    public void procesarPago() {
        System.out.println("Pago procesado con Stripe");
    }
}
