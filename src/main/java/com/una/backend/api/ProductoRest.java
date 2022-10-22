package com.una.backend.api;

import com.una.backend.entity.Producto;
import com.una.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/producto")
public class ProductoRest {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseStatus(HttpStatus.OK)
    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Producto> findById(@PathVariable int id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(producto.get());
    }

    @PostMapping()
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoRepository.save(producto));
    }

    @PutMapping()
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Producto> update(@RequestBody Producto producto) {
        if (productoRepository.findById(producto.getId_producto()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productoRepository.save(producto));
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Producto> delete(@PathVariable int id) {
        if (productoRepository.findById(id).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        productoRepository.delete(productoRepository.findById(id).get());
        return ResponseEntity.ok().build();
    }
}
