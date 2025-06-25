package com.odonto.dentisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.odonto.dentisys.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    /**
     * Busca todos los elementos del menú para un rol específico
     */
    List<Menu> findByRolIdAndEstadoOrderByOrdenAsc(Integer rolId, Boolean estado);

    /**
     * Busca elementos padre del menú (parent_id es null) para un rol específico
     */
    List<Menu> findByRolIdAndParentIdIsNullAndEstadoOrderByOrdenAsc(Integer rolId, Boolean estado);

    /**
     * Busca elementos hijos del menú para un parent_id específico y rol
     */
    List<Menu> findByRolIdAndParentIdAndEstadoOrderByOrdenAsc(Integer rolId, Integer parentId, Boolean estado);

    /**
     * Query personalizada para obtener el menú completo con estructura jerárquica
     */
    @Query("SELECT m FROM Menu m WHERE m.rolId = :rolId AND m.estado = true ORDER BY m.orden ASC")
    List<Menu> findMenuByRolId(@Param("rolId") Integer rolId);
}