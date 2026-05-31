import { useState } from "react";
import "./listar_stakeholders.css";

function GestionUsuarios() {

  const usuariosData = [

    {
      id: 1,

      nombre: "Juan Pérez",

      correo: "juan@email.com",

      telefono: "3001111111",

      rol: "Cliente",

      estado: "Activo"
    },

    {
      id: 2,

      nombre: "Laura Gómez",

      correo: "laura@email.com",

      telefono: "3002222222",

      rol: "Empleado",

      estado: "Activo"
    },

    {
      id: 3,

      nombre: "Carlos Ramírez",

      correo: "carlos@email.com",

      telefono: "3003333333",

      rol: "Administrador",

      estado: "Inactivo"
    },

    {
      id: 4,

      nombre: "Mariana Torres",

      correo: "mariana@email.com",

      telefono: "3004444444",

      rol: "Cliente",

      estado: "Activo"
    },

    {
      id: 5,

      nombre: "Andrés López",

      correo: "andres@email.com",

      telefono: "3005555555",

      rol: "Empleado",

      estado: "Inactivo"
    }

  ];

  const [usuarios, setUsuarios] =
    useState(usuariosData);

  const [filtro, setFiltro] =
    useState("Todos");

  const toggleEstado = (id) => {

    const actualizados = usuarios.map((usuario) => {

      if (usuario.id === id) {

        return {

          ...usuario,

          estado:
            usuario.estado === "Activo"
              ? "Inactivo"
              : "Activo"

        };

      }

      return usuario;

    });

    setUsuarios(actualizados);

  };

  const usuariosFiltrados =
    filtro === "Todos"
      ? usuarios
      : usuarios.filter(
          (usuario) => usuario.rol === filtro
        );

  return (

    <div className="users-container">

      {/* HEADER */}

      <header className="top-header">

        <div className="back-button">
          ← Volver
        </div>

        <h2 className="logo">
          Sterling Drive
        </h2>

        <div className="profile-circle">
          A
        </div>

      </header>

      {/* HERO */}

      <section className="hero-section">

        <div className="hero-overlay"></div>

        <div className="hero-content">

          <h1>
            Gestión de Usuarios
          </h1>

          <p>
            Administra clientes, empleados y administradores de la plataforma.
          </p>

        </div>

      </section>

      {/* FILTROS */}

      <section className="filters-section">

        <div className="filters-container">

          <button
            className={
              filtro === "Todos"
                ? "active-filter"
                : ""
            }
            onClick={() => setFiltro("Todos")}
          >
            Todos
          </button>

          <button
            className={
              filtro === "Cliente"
                ? "active-filter"
                : ""
            }
            onClick={() => setFiltro("Cliente")}
          >
            Clientes
          </button>

          <button
            className={
              filtro === "Empleado"
                ? "active-filter"
                : ""
            }
            onClick={() => setFiltro("Empleado")}
          >
            Empleados
          </button>

          <button
            className={
              filtro === "Administrador"
                ? "active-filter"
                : ""
            }
            onClick={() => setFiltro("Administrador")}
          >
            Administradores
          </button>

        </div>

      </section>

      {/* LISTADO */}

      <section className="users-section">

        <div className="users-grid">

          {usuariosFiltrados.map((usuario) => (

            <div
              className="user-card"
              key={usuario.id}
            >

              <div className="user-top">

                <div className="avatar">
                  {usuario.nombre.charAt(0)}
                </div>

                <div>

                  <h2>
                    {usuario.nombre}
                  </h2>

                  <span className="role-badge">
                    {usuario.rol}
                  </span>

                </div>

              </div>

              {/* INFO */}

              <div className="user-info">

                <div>

                  <strong>
                    Correo
                  </strong>

                  <span>
                    {usuario.correo}
                  </span>

                </div>

                <div>

                  <strong>
                    Teléfono
                  </strong>

                  <span>
                    {usuario.telefono}
                  </span>

                </div>

                <div>

                  <strong>
                    Estado
                  </strong>

                  <span
                    className={
                      usuario.estado === "Activo"
                        ? "status-active"
                        : "status-inactive"
                    }
                  >
                    {usuario.estado}
                  </span>

                </div>

              </div>

              {/* BOTON */}

              <button
                className={
                  usuario.estado === "Activo"
                    ? "disable-btn"
                    : "enable-btn"
                }
                onClick={() =>
                  toggleEstado(usuario.id)
                }
              >

                {usuario.estado === "Activo"
                  ? "Desactivar Usuario"
                  : "Activar Usuario"}

              </button>

            </div>

          ))}

        </div>

      </section>

      {/* FOOTER */}

      <footer className="footer">

        <p>
          Sterling Drive © 2026 — Administración y control de usuarios.
        </p>

      </footer>

    </div>
  );
}

export default GestionUsuarios;