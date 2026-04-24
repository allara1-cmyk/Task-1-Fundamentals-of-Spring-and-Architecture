# Fundamentos de Spring Boot y Arquitectura Desacoplada

Este proyecto es un repositorio de estudio y demostración de conceptos avanzados de Spring Boot, enfocado en el desacoplamiento, inyección de dependencias, patrones de diseño, manejo de errores e interceptores HTTP.

## Conceptos 

### 1. Inyección de Dependencias Avanzada (`@Qualifier` y `@Primary`)
Se demostró cómo manejar múltiples implementaciones de una misma interfaz (`PedidoRepository`).
- **`@Primary`**: Define la implementación por defecto (ej. `MysqlPedidoRepository`).
- **`@Qualifier`**: Permite especificar exactamente qué implementación ("bean") se desea inyectar en un constructor, dándole el poder de elegir entre MySQL y MongoDB en tiempo de ejecución.

### 2. Testing Desacoplado
Se implementó `PedidoServiceTest` usando **Mockito**.
- Aísla la lógica de negocio (`PedidoService`) simulando la base de datos (`mock(PedidoRepository.class)`).
- Verifica el comportamiento (ej. comprobar que se llamó a `save()`) en lugar de verificar el estado de una base de datos real. 

### 3. Desacoplamiento por Configuración (`@Profile`)
Permite cambiar toda la base de datos de la aplicación modificando solo una palabra en un archivo de texto.
- `MysqlPedidoRepository` configurado con `@Profile("dev")`.
- `MongoPedidoRepository` configurado con `@Profile("prod")`.
- Cambiando `spring.profiles.active` en `application.properties`, Spring inyecta automáticamente la dependencia correcta sin tocar el código Java.

### 4. Patrón Strategy + Inversión de Control (IoC)
Se combinó un patrón de diseño clásico (Strategy) con el contenedor de dependencias de Spring.
- Interfaz `DescuentoStrategy` y múltiples comportamientos: `DescuentoNavidad` (20%) y `DescuentoBlackFriday` (50%).
- `VentaService` delega el cálculo a la estrategia inyectada, permitiendo cambiar el algoritmo de descuento sin modificar el servicio principal.

### 5. Manejo Global y Local de Excepciones
Un diseño de API REST profesional requiere códigos HTTP semánticos.
- **Manejo Local**: Uso de `@ExceptionHandler` dentro de `VentaController` para atrapar errores específicos de esa clase (ej. `IllegalArgumentException`).
- **Manejo Global**: Uso de `@ControllerAdvice` (`GlobalExceptionHandler`) para atrapar excepciones en cualquier parte de la aplicación.
- **Mapeo de HTTP Status**:
  - `400 Bad Request`: Para validaciones de cliente (ej. `PrecioInvalidoException`).
  - `404 Not Found`: Cuando no existe un recurso (ej. `RecursoNoEncontradoException`).
  - `403 Forbidden`: Para problemas de permisos/seguridad (ej. `OperacionNoPermitidaException`).
  - `500 Internal Server Error`: Catch-all para excepciones no controladas.

### 6. Interceptores HTTP (`HandlerInterceptor`)
Actúan como filtros bidireccionales ("aduanas") en Spring MVC. Se configuraron en `WebConfig` y se implementaron los tres métodos en `ApiInterceptor`:
- **`preHandle`**: Antes de llegar al controlador. Útil para:
  - **Autenticación**: Verificar Headers (ej. Token) y bloquear acceso devolviendo `false`.
  - **Métricas**: Iniciar un temporizador.
- **`postHandle`**: Después del controlador, antes de la vista. Útil para modificar modelos o verificar que todo salió bien.
- **`afterCompletion`**: Al finalizar la petición (incluso con errores). Útil para:
  - **Auditoría/Métricas**: Calcular e imprimir el tiempo total de ejecución.

---

