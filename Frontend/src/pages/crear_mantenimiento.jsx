import { useState } from "react";
import "./crear_mantenimiento.css";

function CrearMantenimiento() {

  const [mantenimientos, setMantenimientos] =
    useState([

      {
        id: 1,

        idVehiculo: 1,

        fecha: "2025-05-01",

        descripcion:
          "Cambio de aceite y filtros",

        costo: "$250.000"
      },

      {
        id: 2,

        idVehiculo: 2,

        fecha: "2025-05-10",

        descripcion:
          "Cambio de llantas delanteras",

        costo: "$600.000"
      }

    ]);

  return (

    <div className="maintenance-container">

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
            Gestión de Mantenimientos
          </h1>

          <p>
            Registra y consulta mantenimientos realizados a los vehículos.
          </p>

        </div>

      </section>

      {/* MAIN */}

      <section className="main-section">

        {/* FORMULARIO */}

        <div className="form-card">

          <h2>
            Crear Mantenimiento
          </h2>

          <form>

            <div className="input-group">

              <label>
                ID Vehículo
              </label>

              <input
                type="number"
                placeholder="1"
              />

            </div>

            <div className="input-group">

              <label>
                Fecha
              </label>

              <input type="date" />

            </div>

            <div className="input-group">

              <label>
                Descripción
              </label>

              <textarea
                placeholder="Cambio de aceite y filtros"
              ></textarea>

            </div>

            <div className="input-group">

              <label>
                Costo
              </label>

              <input
                type="number"
                placeholder="250000"
              />

            </div>

            <button
              type="submit"
              className="create-btn"
            >
              Registrar Mantenimiento
            </button>

          </form>

        </div>

        {/* LISTADO */}

        <div className="maintenance-list">

          <h2>
            Mantenimientos Registrados
          </h2>

          <div className="maintenance-grid">

            {mantenimientos.map((item) => (

              <div
                className="maintenance-card"
                key={item.id}
              >

                <div className="card-header">

                  <span className="maintenance-id">
                    Mantenimiento #{item.id}
                  </span>

                </div>

                <div className="maintenance-info">

                  <div>
                    <strong>ID Vehículo</strong>

                    <span>
                      {item.idVehiculo}
                    </span>
                  </div>

                  <div>
                    <strong>Fecha</strong>

                    <span>
                      {item.fecha}
                    </span>
                  </div>

                  <div className="full-width">

                    <strong>Descripción</strong>

                    <span>
                      {item.descripcion}
                    </span>

                  </div>

                  <div>
                    <strong>Costo</strong>

                    <span>
                      {item.costo}
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

export default CrearMantenimiento;