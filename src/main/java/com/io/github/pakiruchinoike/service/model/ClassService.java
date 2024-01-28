package com.io.github.pakiruchinoike.service.model;

import java.util.List;

import com.io.github.pakiruchinoike.domain.entity.character.Class;
import com.io.github.pakiruchinoike.domain.enums.Classname;
import com.io.github.pakiruchinoike.rest.dto.ClassDTO;

public interface ClassService {
    
    Class save(ClassDTO dto);
    Class findById(Integer id);
    Class findByClassname(Classname classname);
    List<Class> findAll();
    List<Class> find(ClassDTO dto);
    void update(ClassDTO dto, Integer id);
    void delete(Integer id);
    Class createClass(Class c);

}
