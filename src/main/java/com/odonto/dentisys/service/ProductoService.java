package com.odonto.dentisys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.model.Producto;
import com.odonto.dentisys.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Transactional
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Transactional
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Producto> findByCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    @Transactional(readOnly = true)
    public List<Producto> findByEstado(Boolean estado) {
        return productoRepository.findByEstado(estado);
    }
}