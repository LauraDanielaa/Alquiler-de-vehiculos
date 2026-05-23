import "./registro_usuario.css";

function Register() {
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
              <label>Nombre Completo</label>
              <input type="text" placeholder="Ingresa tu nombre" />
            </div>

            <div className="input-group">
              <label>Correo Electrónico</label>
              <input type="email" placeholder="correo@email.com" />
            </div>

            <div className="input-group">
              <label>Contraseña</label>
              <input type="password" placeholder="********" />
            </div>

            <div className="input-group">
              <label>Teléfono</label>
              <input type="text" placeholder="3001234567" />
            </div>

            <div className="input-group">
              <label>Dirección</label>
              <input type="text" placeholder="Tu dirección" />
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

export default Register;