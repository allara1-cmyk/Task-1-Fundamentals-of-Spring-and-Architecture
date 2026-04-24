package com.example.demo.desacoplamiento.pago;

import org.springframework.stereotype.Component;

@Component("paypalGateway")
public class PaypalGateway implements PagoGateway {
    
    @Override
    public void procesarPago() {
        System.out.println("Pago procesado con PayPal");
    }
}
