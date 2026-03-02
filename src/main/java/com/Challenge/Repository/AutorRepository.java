package com.Challenge.Repository;

import com.Challenge.Model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Al ser ambos Integer, el operador >= funcionará perfecto
    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anio AND (a.fechaDeFallecimiento IS NULL OR a.fechaDeFallecimiento >= :anio)")
    List<Autor> autoresVivosEnDeterminadoAnio(Integer anio);
}