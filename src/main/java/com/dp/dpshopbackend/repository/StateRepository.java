package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Scategory;
import com.dp.dpshopbackend.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    @Query("select p from State p where p.country.code =:code")
    List<State> findByCountryCode(@Param("code") String code);

    List<State> findByOrderByIdDesc();

    @Query("Select DISTINCT act from State act where act.actif=1 ORDER BY act.name")
    List<State> findAll();

}
