import { useState } from "react";
import "./crear_multa.css";

function CrearMulta() {

  const [multas, setMultas] = useState([
    {
      id: 1,
      idAlquiler: 101,
      descripcion: "Daño en la puerta trasera derecha",
      monto: "$500.000",
      fecha: "2025-06-10"
    },

    {
      id: 2,
      idAlquiler: 102,
      descripcion: "Retraso en devolución del vehículo",
      monto: "$250.000",
      fecha: "2025-06-15"
    }
  ]);

  return (

    <div className="multa-container">

      {/* HEADER */}

      <header className="top-header">

        <div className="back-button">
          ← Volver
        </div>

        <h2 className="logo">
          Sterling Drive
        </h2>

        <div className="profile-section">

          <div className="profile-circle">
            A
          </div>

        </div>

      </header>

      {/* HERO */}

      <section className="hero-section">

        <div className="hero-overlay"></div>

        <div className="hero-content">

          <h1>
            Registro de Multas
          </h1>

          <p>
            Gestiona penalizaciones y daños asociados a los alquileres.
          </p>

        </div>

      </section>

      {/* CONTENIDO */}

      <section className="main-section">

        {/* FORMULARIO */}

        <div className="form-card">

          <h2>
            Crear Multa
          </h2>

          <form>

            <div className="input-group">

              <label>
                ID Alquiler
              </label>

              <input
                type="number"
                placeholder="101"
              />

            </div>

            <div className="input-group">

              <label>
                Descripción
              </label>

              <textarea
                placeholder="Daño en la puerta trasera derecha"
              ></textarea>

            </div>

            <div className="input-group">

              <label>
                Monto
              </label>

              <input
                type="number"
                placeholder="500000"
              />

            </div>

            <div className="input-group">

              <label>
                Fecha
              </label>

              <input
                type="date"
              />

            </div>

            <button
              type="submit"
              className="create-btn"
            >
              Registrar Multa
            </button>

          </form>

        </div>

        {/* LISTADO */}

        <div className="multas-list">

          <h2>
            Multas Registradas
          </h2>

          <div className="multas-grid">

            {multas.map((multa) => (

              <div
                className="multa-card"
                key={multa.id}
              >

                <div className="multa-header">

                  <span className="multa-id">
                    Multa #{multa.id}
                  </span>

                </div>

                <div className="multa-info">

                  <div>
                    <strong>ID Alquiler</strong>

                    <span>
                      {multa.idAlquiler}
                    </span>
                  </div>

                  <div>
                    <strong>Monto</strong>

                    <span>
                      {multa.monto}
                    </span>
                  </div>

                  <div className="full-width">
                    <strong>Descripción</strong>

                    <span>
                      {multa.descripcion}
                    </span>
                  </div>

                  <div>
                    <strong>Fecha</strong>

                    <span>
                      {multa.fecha}
                    </span>
                  </div>

                </div>

              </div>

            ))}

          </div>

        </div>

      </section>

    </div>
  );
}

export default CrearMulta;