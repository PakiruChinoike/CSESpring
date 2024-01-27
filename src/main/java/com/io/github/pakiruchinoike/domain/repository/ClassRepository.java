package com.io.github.pakiruchinoike.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.io.github.pakiruchinoike.domain.entity.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer>{

    @Query(" select c from Class c left join fetch c.abilities where c.id=:id ")
    Optional<Class> findByIdFetchAbilities(@Param("id") Integer id);

    @Query(" select c from Class c left join fetch c.abilities where c.classname=:classname ")
    Optional<Class> findByClassname(@Param("classname") String classname);
}
