package com.example.demo.desacoplamiento.pago;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    private final PagoGateway gateway;

    // Usamos @Qualifier para especificar que queremos usar Stripe por defecto
    // o podríamos usar "paypalGateway" si queremos cambiar la implementación.
    public PagoService(@Qualifier("stripeGateway") PagoGateway gateway) {
        this.gateway = gateway;
    }

    public void pagar() {
        gateway.procesarPago();
    }
}
