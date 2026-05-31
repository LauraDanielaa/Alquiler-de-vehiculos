import { useState } from "react";
import "./perfil_cliente.css";

function PerfilCliente() {

  const [showModal, setShowModal] =
    useState(false);

  const [cliente, setCliente] = useState({

    nombre: "Juan",

    apellido: "Pérez",

    telefono: "3001111111",

    email: "juan@email.com",

    direccion: "Carrera 50 # 80-20"

  });

  const [formData, setFormData] =
    useState(cliente);

  const handleChange = (e) => {

    setFormData({

      ...formData,

      [e.target.name]: e.target.value

    });

  };

  const handleSave = () => {

    setCliente(formData);

    setShowModal(false);

  };

  return (

    <div className="profile-container">

      {/* HEADER */}

      <header className="top-header">

        <div className="back-button">
          ← Volver
        </div>

        <h2 className="logo">
          Sterling Drive
        </h2>

        <div className="profile-circle">
          J
        </div>

      </header>

      {/* HERO */}

      <section className="hero-section">

        <div className="hero-overlay"></div>

        <div className="hero-content">

          <h1>
            Mi Perfil
          </h1>

          <p>
            Consulta y administra tu información personal.
          </p>

        </div>

      </section>

      {/* PERFIL */}

      <section className="profile-section">

        <div className="profile-card">

          <div className="profile-header">

            <div className="profile-avatar">
              J
            </div>

            <div>

              <h2>
                {cliente.nombre} {cliente.apellido}
              </h2>

              <span>
                Cliente Sterling Drive
              </span>

            </div>

          </div>

          {/* INFO */}

          <div className="profile-info">

            <div>

              <strong>
                Nombre
              </strong>

              <span>
                {cliente.nombre}
              </span>

            </div>

            <div>

              <strong>
                Apellido
              </strong>

              <span>
                {cliente.apellido}
              </span>

            </div>

            <div>

              <strong>
                Teléfono
              </strong>

              <span>
                {cliente.telefono}
              </span>

            </div>

            <div>

              <strong>
                Correo
              </strong>

              <span>
                {cliente.email}
              </span>

            </div>

            <div className="full-width">

              <strong>
                Dirección
              </strong>

              <span>
                {cliente.direccion}
              </span>

            </div>

          </div>

          {/* BOTON */}

          <button
            className="edit-btn"
            onClick={() => setShowModal(true)}
          >
            Actualizar Información
          </button>

        </div>

      </section>

      {/* MODAL */}

      {showModal && (

        <div
          className="modal-overlay"
          onClick={() => setShowModal(false)}
        >

          <div
            className="modal-content"
            onClick={(e) => e.stopPropagation()}
          >

            <button
              className="close-btn"
              onClick={() => setShowModal(false)}
            >
              ✕
            </button>

            <h2>
              Actualizar Información
            </h2>

            <div className="modal-form">

              <div className="input-group">

                <label>
                  Nombre
                </label>

                <input
                  type="text"
                  name="nombre"
                  value={formData.nombre}
                  onChange={handleChange}
                />

              </div>

              <div className="input-group">

                <label>
                  Apellido
                </label>

                <input
                  type="text"
                  name="apellido"
                  value={formData.apellido}
                  onChange={handleChange}
                />

              </div>

              <div className="input-group">

                <label>
                  Teléfono
                </label>

                <input
                  type="text"
                  name="telefono"
                  value={formData.telefono}
                  onChange={handleChange}
                />

              </div>

              <div className="input-group">

                <label>
                  Correo
                </label>

                <input
                  type="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                />

              </div>

              <div className="input-group full-width">

                <label>
                  Dirección
                </label>

                <input
                  type="text"
                  name="direccion"
                  value={formData.direccion}
                  onChange={handleChange}
                />

              </div>

            </div>

            <div className="modal-buttons">

              <button
                className="save-btn"
                onClick={handleSave}
              >
                Guardar Cambios
              </button>

              <button
                className="cancel-btn"
                onClick={() => setShowModal(false)}
              >
                Cancelar
              </button>

            </div>

          </div>

        </div>

      )}

      {/* FOOTER */}

      <footer className="footer">

        <p>
          Sterling Drive © 2026 — Tu experiencia premium comienza aquí.
        </p>

      </footer>

    </div>
  );
}

export default PerfilCliente;