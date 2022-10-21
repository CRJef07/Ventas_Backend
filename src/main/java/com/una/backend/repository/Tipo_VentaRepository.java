package com.una.backend.repository;

import com.una.backend.entity.Tipo_Venta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Tipo_VentaRepository extends CrudRepository<Tipo_Venta, Integer> {
}
