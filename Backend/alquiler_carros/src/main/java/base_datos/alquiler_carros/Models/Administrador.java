package base_datos.alquiler_carros.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@PrimaryKeyJoinColumn(name = "id_usuario")
@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Administrador extends Usuario {
    @Column(name = "nivel_acceso")
    private String nivelAcceso;
}
