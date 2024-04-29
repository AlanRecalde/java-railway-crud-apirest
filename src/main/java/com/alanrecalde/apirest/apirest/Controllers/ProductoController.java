



package com.alanrecalde.apirest.apirest.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.alanrecalde.apirest.apirest.Repositories.ProductoRepository;
import com.alanrecalde.apirest.apirest.Entities.Producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/Productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> obtenerProductos(){
        return productoRepository.findAll();

    }

    @GetMapping("/{id}")
    public Producto obtenerProductosPorId(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));
    }


    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);

    }


    @PutMapping("/{id}")
    public Producto actualiziarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontrol el producto que desea eliminar. Id del producto: " + id));

        productoRepository.delete(producto);
        return "El producto con el ID " + id + " Fue eliminado correctamente";
    }


}
