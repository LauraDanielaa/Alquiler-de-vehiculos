package base_datos.alquiler_carros.Models.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SucursalRequestDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String ciudad;

    @NotBlank
    private String telefono;

    @NotBlank
    private String direccion;

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank String getCiudad() {
        return ciudad;
    }

    public void setCiudad(@NotBlank String ciudad) {
        this.ciudad = ciudad;
    }

    public @NotBlank String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NotBlank String telefono) {
        this.telefono = telefono;
    }

    public @NotBlank String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NotBlank String direccion) {
        this.direccion = direccion;
    }
}