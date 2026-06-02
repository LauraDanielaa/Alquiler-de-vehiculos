
import "./login_cliente.css";

import { useState } from "react";

import { useNavigate } from "react-router-dom";

import { login } from "../services/authService";

function LoginCliente() {

  const navigate = useNavigate();

  // Estados
  const [username, setUsername] = useState("");

  const [password, setPassword] = useState("");

  // Función login
  const handleLogin = async (e) => {

    e.preventDefault();

    try {

      const response = await login({
        username,
        password
      });

      console.log(response);

      // Guardar token
      localStorage.setItem("token", response.token);

      localStorage.setItem("rol", response.rol);

      localStorage.setItem("username", response.username);

      alert("Inicio de sesión exitoso");

      // Redirección según rol
      if (response.rol === "CLIENTE") {
        navigate("/principal-cliente");
      }

      if (response.rol === "EMPLEADO") {
        navigate("/principal-empleado");
      }

      if (response.rol === "ADMIN") {
        navigate("/principal-admin");
      }

    } catch (error) {

      console.log(error);

      alert("Usuario o contraseña incorrectos");
    }
  };

  return (

    <div className="register-container">

      {/* LADO IZQUIERDO */}
      <div className="register-left">

        <div className="form-box">

          <h1>Iniciar Sesión</h1>

          <p className="subtitle">
            Accede a Sterling Drive con tu usuario y contraseña
          </p>

          <form onSubmit={handleLogin}>

            <div className="input-group">

              <label>Username</label>

              <input
                type="text"
                placeholder="nombre de usuario"

                value={username}

                onChange={(e) => setUsername(e.target.value)}
              />

            </div>

            <div className="input-group">

              <label>Contraseña</label>

              <input
                type="password"
                placeholder="********"

                value={password}

                onChange={(e) => setPassword(e.target.value)}
              />

            </div>

            <button onClick={() => navigate("/principal_cliente")} type="submit">
              Ingresar
            </button>

            <div className="extra-login-options">

              <p className="redirect-text" onClick={() => navigate("/login_empleado")}>
                ¿Eres empleado?
              </p>

              <p className="redirect-text" onClick={() => navigate("/login_admin")}>
                ¿Eres administrador?
              </p>

            </div>

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
                onClick={() => navigate("/registro_cliente")}

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

export default LoginCliente;

