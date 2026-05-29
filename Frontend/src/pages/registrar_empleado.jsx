import "./registrar_empleado.css";

function RegistrarEmpleado() {
  return (
    <div className="register-container">

      {/* LADO IZQUIERDO */}
      <div className="register-left">

        <div className="form-box">

          <h1>Crear Cuenta</h1>

          <p className="subtitle">
            Ingresa tus datos aquí
          </p>

          <form>

            <div className="input-group">
              <label>Nombres</label>
              <input type="text" placeholder="Ingresa tus nombres" />
            </div>

            <div className="input-group">
              <label>Apellidos</label>
              <input type="text" placeholder="Ingresa tus apellidos" />
            </div>

            <div className="input-group">
              <label>Usuario</label>
              <input type="text" placeholder="Ingresa tu nombre de usuario" />
            </div>

            <div className="input-group">
              <label>Documento de Identidad</label>
              <input type="text" placeholder="Ingresa tu número de documento" />
            </div>

             <div className="input-group">
              <label>Salario</label>
              <input type="text" placeholder="Salario mensual" />
            </div>

            <div className="input-group">
              <label>Teléfono</label>
              <input type="text" placeholder="3001234567" />
            </div>

            <div className="input-group">
              <label>Cargo</label>
              <input type="text" placeholder="Tu cargo en la empresa" />
            </div>

            <div className="input-group">
              <label>Sucursal</label>
              <input type="number" placeholder="Id de la sucursal (1-5)" />
            </div>

            <div className="input-group">
              <label>Correo Electrónico</label>
              <input type="email" placeholder="correo@email.com" />
            </div>

            <div className="input-group">
              <label>Contraseña</label>
              <input type="password" placeholder="********" />
            </div>

            <button type="submit">
              Registrarse
            </button>

          </form>

        </div>

      </div>

      {/* LADO DERECHO */}
      <div className="register-right">

        <div className="overlay"></div>

        <div className="right-content">

          <h2>Sterling Drive</h2>

          <p>
            Encuentra vehículos modernos, seguros y listos para cualquier viaje.
          </p>

        </div>

      </div>

    </div>
  );
}

export default RegistrarEmpleado;