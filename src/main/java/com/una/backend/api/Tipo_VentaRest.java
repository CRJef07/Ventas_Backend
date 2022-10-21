package com.una.backend.api;

import com.una.backend.entity.Tipo_Venta;
import com.una.backend.repository.Tipo_VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/tipo_venta")
public class Tipo_VentaRest {
    @Autowired
    private Tipo_VentaRepository tipoVentaRepository;

    @GetMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseStatus(HttpStatus.OK)
    public List<Tipo_Venta> findAll() {
        return (List<Tipo_Venta>) tipoVentaRepository.findAll();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    //public ResponseEntity<Persona> findById(@PathVariable int id)
    public ResponseEntity<Tipo_Venta> findById(@PathVariable int id) {
        Optional<Tipo_Venta> tipo_venta = tipoVentaRepository.findById(id);
        if (!tipo_venta.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tipo_venta.get());
    }

    @PostMapping()
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Tipo_Venta> create(@RequestBody Tipo_Venta tipo_venta) {
        return ResponseEntity.ok(tipoVentaRepository.save(tipo_venta));
    }

    @PutMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Tipo_Venta> update(@RequestBody Tipo_Venta tipo_venta) {
        if (!tipoVentaRepository.findById(tipo_venta.getId_tipo_venta()).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tipoVentaRepository.save(tipo_venta));
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity delete(@PathVariable int id){
        if (!tipoVentaRepository.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        tipoVentaRepository.delete(tipoVentaRepository.findById(id).get());
        return ResponseEntity.ok().build();
    }

}
