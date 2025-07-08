package com.odonto.dentisys.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odonto.dentisys.dto.CitaDTO;
import com.odonto.dentisys.model.Cita;
import com.odonto.dentisys.repository.CitaRepository;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Transactional(readOnly = true)
    public List<CitaDTO> findAll() {
        return citaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CitaDTO> findById(Long id) {
        return citaRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Optional<Cita> findCitaById(Long id) {
        return citaRepository.findById(id);
    }

    @Transactional
    public Cita save(Cita cita) {
        System.out.println("Intentando guardar cita: " + cita);
        System.out.println("Hora de la cita: " + cita.getHoraCita());
        if (cita.getId() != null) {
            Cita existingCita = citaRepository.findById(cita.getId())
                    .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + cita.getId()));
            cita.setCreatedAt(existingCita.getCreatedAt());
        }
        return citaRepository.save(cita);
    }

    @Transactional
    public void deleteById(Long id) {
        citaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CitaDTO> findByPacienteId(Long pacienteId) {
        return citaRepository.findByPacienteId(pacienteId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CitaDTO> findByMedicoId(Long medicoId) {
        return citaRepository.findByMedicoId(medicoId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CitaDTO> findByPacienteIdAndFechaCitaBetween(Long pacienteId, LocalDate fechaInicio,
            LocalDate fechaFin) {
        return citaRepository.findByPacienteIdAndFechaCitaBetween(pacienteId, fechaInicio, fechaFin).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CitaDTO> findByMedicoIdAndFechaCitaBetween(Long medicoId, LocalDate fechaInicio, LocalDate fechaFin) {
        return citaRepository.findByMedicoIdAndFechaCitaBetween(medicoId, fechaInicio, fechaFin).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CitaDTO> findByEstado(String estado) {
        return citaRepository.findByEstado(estado).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CitaDTO convertToDTO(Cita cita) {
        CitaDTO dto = new CitaDTO();
        dto.setId(cita.getId());

        // Manejar paciente null
        if (cita.getPaciente() != null) {
            dto.setPacienteId(cita.getPaciente().getId());
            dto.setPacienteNombre(cita.getPaciente().getNombre() + " " + cita.getPaciente().getApellido());
        } else {
            dto.setPacienteId(null);
            dto.setPacienteNombre("Sin paciente asignado");
        }

        // Manejar médico null (aunque debería ser obligatorio según el esquema)
        if (cita.getMedico() != null) {
            dto.setMedicoId(cita.getMedico().getId());
            dto.setMedicoNombre(cita.getMedico().getNombre() + " " + cita.getMedico().getApellido());
        } else {
            dto.setMedicoId(null);
            dto.setMedicoNombre("Sin médico asignado");
        }

        dto.setFechaCita(cita.getFechaCita());
        dto.setHoraCita(cita.getHoraCita());
        dto.setHoraCitaFin(cita.getHoraCitaFin());
        dto.setEstado(cita.getEstado());
        dto.setObservaciones(cita.getObservaciones());
        dto.setCreatedAt(cita.getCreatedAt().toString());
        dto.setUpdatedAt(cita.getUpdatedAt().toString());
        return dto;
    }
}