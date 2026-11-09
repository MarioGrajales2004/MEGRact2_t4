package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.service.ProductoService;

/** Controlador REST: recibe y devuelve JSON. */
@RestController
public class ProductoRestController {

    private final ProductoService servicio;

    public ProductoRestController(ProductoService servicio) {
        this.servicio = servicio;
    }

    /**
     * POST /api/productos
     * @RequestBody convierte el JSON del cuerpo en un ProductoDTO.
     * Responde 201 Created con un JSON de confirmación.
     */
    @PostMapping("/api/productos")
    public ResponseEntity<Map<String, Object>> crear(@RequestBody ProductoDTO dto) {
        ProductoDTO guardado = servicio.guardar(dto);
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", "Producto creado correctamente");
        respuesta.put("producto", guardado);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}
