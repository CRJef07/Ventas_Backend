package com.una.backend.api;

import com.una.backend.entity.Venta;
import com.una.backend.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/venta")
public class VentaRest {
    @Autowired
    private VentaRepository ventaRepository;

    @GetMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseStatus(HttpStatus.OK)
    public List<Venta> findAll() {
        return (List<Venta>) ventaRepository.findAll();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Venta> findById(@PathVariable int id) {
        Optional<Venta> venta = ventaRepository.findById(id);
        if (!venta.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(venta.get());
    }

    @PostMapping()
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Venta> create(@RequestBody Venta venta) {
        return ResponseEntity.ok(ventaRepository.save(venta));
    }

    @PutMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Venta> update(@RequestBody Venta venta) {
        if (!ventaRepository.findById(venta.getId_venta()).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ventaRepository.save(venta));
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity delete(@PathVariable int id) {
        if (!ventaRepository.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        ventaRepository.delete(ventaRepository.findById(id).get());
        return ResponseEntity.ok().build();
    }
}
