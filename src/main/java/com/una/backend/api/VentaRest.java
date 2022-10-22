package com.una.backend.api;

import com.una.backend.entity.Producto;
import com.una.backend.entity.Venta;
import com.una.backend.repository.ProductoRepository;
import com.una.backend.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/venta")
public class VentaRest {
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ProductoRepository productoRepository;

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
        if (venta.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(venta.get());
    }

    @PostMapping()
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Venta> create(@RequestBody Venta venta) {

        if (venta.getProducto().getCantidad() == 0){
            //return ResponseEntity.internalServerError().build();
            return ResponseEntity.status(99).build();
        }

        //List<Producto> listaProductos = (List<Producto>) productoRepository.findAll();
        //productoRepository.findAll().forEach(producto -> listaProductos.add(producto));

        List<Producto> listaProductos = new ArrayList<>();
        productoRepository.findAll().forEach(listaProductos::add);



        for (Producto producto : listaProductos) {
            if (producto.getId_producto() == venta.getProducto().getId_producto()) {
                venta.setProducto(producto);
                venta.setFecha(new Date());
                if (producto.getCantidad() > 0) { //NO SE PUEDE COMPRAR UN PRODUCTO SI NO TIENE EN EXISTENCIA
                    if (producto.getCantidad() - venta.getCantidad() >= 0) { //NO SE PUEDE COMPRAR SI LO QUE SE QUIERE ES MAYOR QUE LA CANTIDAD EXISTENTE
                        producto.setCantidad(producto.getCantidad() - venta.getCantidad());
                        productoRepository.save(producto);
                        return ResponseEntity.ok(ventaRepository.save(venta));
                    }else{//CUANDO SE QUIERE COMPRAR MÁS DE LA CANTIDAD EXISTENTE
                        //return ResponseEntity.noContent().build();//204
                        return ResponseEntity.status(98).build();
                    }
                }else{//CUANDO NO HAY PRODUCTOS CANTIDAD = 0
                    //return ResponseEntity.notFound().build(); //404
                    return ResponseEntity.status(97).build();
                }
            }
        }
        return ResponseEntity.badRequest().build();//400
    }

    /* VENTAS NO SE EDITAN
    @PutMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Venta> update(@RequestBody Venta venta) {
        if (!ventaRepository.findById(venta.getId_venta()).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ventaRepository.save(venta));
    }*/

    /*CUANDO SE ELIMINA UNA VENTA SE DEVUELVA LA CANTIDAD AL PRODUCTO*/
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Venta> delete(@PathVariable int id) {
        if (ventaRepository.findById(id).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        //ventaRepository.delete(ventaRepository.findById(id).get());
        //ventaRepository.deleteById(id);
        //return ResponseEntity.ok().build();

        List<Producto> listaProductos = new ArrayList<>();
        productoRepository.findAll().forEach(listaProductos::add);

        Venta venta = ventaRepository.findById(id).get();

        for (Producto producto : listaProductos) {
            if (producto.getId_producto() == venta.getProducto().getId_producto()) {
                producto.setCantidad(producto.getCantidad() + venta.getCantidad());
                ventaRepository.delete(venta);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}

