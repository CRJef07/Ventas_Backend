package com.una.backend.repository;

import com.una.backend.entity.Venta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends CrudRepository<Venta, Integer> {
}
