package com.example.demo.desacoplamiento.service;

import com.example.demo.desacoplamiento.model.Pedido;
import com.example.demo.desacoplamiento.repository.PedidoRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PedidoServiceTest {

    @Test
    void testGuardar() {
        // 1. Crear el Mock del repositorio
        PedidoRepository mockRepo = mock(PedidoRepository.class);
        
        // 2. Inyectar el Mock al servicio
        PedidoService service = new PedidoService(mockRepo);

        // 3. Ejecutar la acción
        service.guardar(new Pedido());

        // 4. Verificar
        verify(mockRepo).save(any(Pedido.class));
    }
}
