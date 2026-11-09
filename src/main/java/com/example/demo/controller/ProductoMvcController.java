package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.service.ProductoService;

/**
 * Controlador MVC (@Controller): sus métodos devuelven el NOMBRE de una vista
 * Thymeleaf (templates/productos/*.html), no datos JSON.
 */
@Controller
@RequestMapping("/productos")
public class ProductoMvcController {

    /** Propiedad personalizada de application.properties. */
    @Value("${app.desarrollador.nombre}")
    private String desarrollador;

    private final ProductoService servicio;

    public ProductoMvcController(ProductoService servicio) {
        this.servicio = servicio;
    }

    /** A) GET /productos/lista[?categoria=X] -> @RequestParam opcional. */
    @GetMapping("/lista")
    public String lista(@RequestParam(name = "categoria", required = false) String categoria, Model model) {
        model.addAttribute("desarrollador", desarrollador);
        model.addAttribute("productos", servicio.listar(categoria));
        model.addAttribute("categoriaFiltro", categoria);
        return "productos/lista";
    }

    /** B) GET /productos/detalle/{id} -> @PathVariable. */
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable("id") Long id, Model model) {
        model.addAttribute("desarrollador", desarrollador);
        model.addAttribute("producto", servicio.buscarPorId(id).orElse(null));
        return "productos/detalle";
    }

    /** C1) GET /productos/nuevo -> formulario con un DTO vacío. */
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("desarrollador", desarrollador);
        model.addAttribute("productoDTO", new ProductoDTO());
        return "productos/formulario";
    }

    /** C2) POST /productos/guardar -> @ModelAttribute llena el DTO con los campos del formulario. */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ProductoDTO productoDTO) {
        servicio.guardar(productoDTO);
        // Patrón Post/Redirect/Get: evita reenviar el formulario al recargar.
        return "redirect:/productos/lista";
    }
}
