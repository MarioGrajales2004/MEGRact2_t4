package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductoDTO;

/**
 * Almacén simulado en memoria (sin base de datos).
 * Lo comparten el controlador MVC (formularios) y el REST (JSON).
 */
@Service
public class ProductoService {

    private final List<ProductoDTO> productos = new CopyOnWriteArrayList<>();
    private final AtomicLong contador = new AtomicLong(0);

    public ProductoService() {
        guardar(new ProductoDTO(null, "Laptop", 15999.90, "Computo"));
        guardar(new ProductoDTO(null, "Mouse inalambrico", 299.00, "Perifericos"));
        guardar(new ProductoDTO(null, "Teclado mecanico", 899.50, "Perifericos"));
        guardar(new ProductoDTO(null, "Monitor 24 pulgadas", 3499.00, "Computo"));
    }

    /** Lista todo, o solo los de una categoría si se indica. */
    public List<ProductoDTO> listar(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            return List.copyOf(productos);
        }
        return productos.stream()
                .filter(p -> p.getCategoria() != null && p.getCategoria().equalsIgnoreCase(categoria.trim()))
                .toList();
    }

    public Optional<ProductoDTO> buscarPorId(Long id) {
        return productos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    /** Asigna un id nuevo y guarda el producto. */
    public ProductoDTO guardar(ProductoDTO dto) {
        dto.setId(contador.incrementAndGet());
        productos.add(dto);
        return dto;
    }
}
