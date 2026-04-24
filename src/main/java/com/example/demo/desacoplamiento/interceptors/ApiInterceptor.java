package com.example.demo.desacoplamiento.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ApiInterceptor implements HandlerInterceptor {

    // 1. preHandle: Se ejecuta ANTES de que la petición llegue al Controlador
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // CASO DE USO 1: Medición de tiempos de ejecución (Inicio)
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        // CASO DE USO 2: Registros (Logging)
        System.out.println("====== INTERCEPTOR: PRE-HANDLE ======");
        System.out.println("Petición entrante hacia: " + request.getRequestURI());

        // CASO DE USO 3: Autenticación (Simulada)
        // Si intentan entrar a "/api/seguro", verificamos que manden un Header "Auth" con valor "12345"
        if (request.getRequestURI().contains("/api/seguro")) {
            String authHeader = request.getHeader("Auth");
            if (authHeader == null || !authHeader.equals("12345")) {
                System.out.println("-> ¡ACCESO DENEGADO! Faltan credenciales o son incorrectas.");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Error 401: No tienes permisos para acceder a esta ruta.");
                return false; // IMPORTANTE: "false" corta el flujo, la petición nunca llega al controlador.
            }
            System.out.println("-> Autenticación exitosa.");
        }

        return true; // "true" permite que la petición continúe su camino hacia el Controlador.
    }

    // 2. postHandle: Se ejecuta DESPUÉS del Controlador, pero ANTES de renderizar la respuesta final al cliente
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("====== INTERCEPTOR: POST-HANDLE ======");
        System.out.println("El controlador terminó de procesar la lógica sin arrojar excepciones fatales.");
    }

    // 3. afterCompletion: Se ejecuta al FINALIZAR completamente la petición (como el bloque "finally" de un try-catch)
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("====== INTERCEPTOR: AFTER-COMPLETION ======");
        
        // CASO DE USO 1: Medición de tiempos de ejecución (Fin)
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo total de procesamiento para " + request.getRequestURI() + " : " + (endTime - startTime) + " ms.");
        
        if (ex != null) {
            System.out.println("Ojo: La petición terminó con la siguiente excepción: " + ex.getMessage());
        }
        System.out.println("============================================\n");
    }
}
