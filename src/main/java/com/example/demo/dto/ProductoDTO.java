package com.example.demo.dto;

/**
 * DTO (Data Transfer Object) de un producto.
 * Transporta datos entre el formulario/JSON y el controlador, sin exponer
 * ninguna entidad de base de datos. Thymeleaf (th:field) y Jackson (JSON)
 * usan los getters y setters.
 */
public class ProductoDTO {

    private Long id;
    private String nombre;
    private Double precio;
    private String categoria;

    /** Constructor vacío: obligatorio para @ModelAttribute y @RequestBody. */
    public ProductoDTO() {
    }

    public ProductoDTO(Long id, String nombre, Double precio, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
