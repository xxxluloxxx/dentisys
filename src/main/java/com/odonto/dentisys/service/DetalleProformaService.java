package com.odonto.dentisys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.model.DetalleProforma;
import com.odonto.dentisys.model.Proforma;
import com.odonto.dentisys.repository.DetalleProformaRepository;

@Service
public class DetalleProformaService {

    @Autowired
    private DetalleProformaRepository detalleProformaRepository;

    @Transactional(readOnly = true)
    public List<DetalleProforma> findAll() {
        return detalleProformaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public DetalleProforma findById(Long id) {
        return detalleProformaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de proforma no encontrado con ID: " + id));
    }

    @Transactional
    public DetalleProforma save(DetalleProforma detalleProforma) {
        return detalleProformaRepository.save(detalleProforma);
    }

    @Transactional
    public void deleteById(Long id) {
        detalleProformaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<DetalleProforma> findByProforma(Proforma proforma) {
        return detalleProformaRepository.findByProforma(proforma);
    }
}