import "./ingreso_usuario.css";

function Login() {
  return (
    <div className="register-container">

      {/* LADO IZQUIERDO */}
      <div className="register-left">

        <div className="form-box">

          <h1>Iniciar Sesión</h1>

          <p className="subtitle">
            Accede a Sterling Drive con tu correo y contraseña
          </p>

          <form>

            <div className="input-group">
              <label>Correo Electrónico</label>
              <input
                type="email"
                placeholder="correo@email.com"
              />
            </div>

            <div className="input-group">
              <label>Contraseña</label>
              <input
                type="password"
                placeholder="********"
              />
            </div>

            <button type="submit">
              Ingresar
            </button>

            <p
              style={{
                marginTop: "20px",
                color: "#b8b8b8",
                textAlign: "center",
                fontSize: "14px"
              }}
            >
              ¿No tienes cuenta?{" "}
              <span
                style={{
                  color: "#d4af37",
                  cursor: "pointer",
                  fontWeight: "bold"
                }}
              >
                Regístrate
              </span>
            </p>

          </form>

        </div>

      </div>

      {/* LADO DERECHO */}
      <div className="register-right">

        <div className="overlay"></div>

        <div className="right-content">

          <h2>Sterling Drive</h2>

          <p>
            Vehículos exclusivos para experiencias inolvidables.
          </p>

        </div>

      </div>

    </div>
  );
}

export default Login;