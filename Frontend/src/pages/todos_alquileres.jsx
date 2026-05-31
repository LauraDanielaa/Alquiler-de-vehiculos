import { useState } from "react";
import "./todos_alquileres.css";

function AlquileresAdmin() {

  const alquileres = [

    {
      id: 1,

      usuario: "Laura Gómez",

      carro: "BMW M4 Competition",

      categoria: "Lujo",

      estado: "ACTIVO",

      precio: "$850.000 / día",

      ubicacion: "Bogotá",

      fechaInicio: "2026-06-01",

      fechaFin: "2026-06-10",

      imagen:
        "https://images.unsplash.com/photo-1555215695-3004980ad54e?q=80&w=2070"
    },

    {
      id: 2,

      usuario: "Carlos Ramírez",

      carro: "Toyota Prado",

      categoria: "SUV",

      estado: "FINALIZADO",

      precio: "$620.000 / día",

      ubicacion: "Medellín",

      fechaInicio: "2026-05-01",

      fechaFin: "2026-05-08",

      imagen:
        "https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?q=80&w=2070"
    },

    {
      id: 3,

      usuario: "Andrea Torres",

      carro: "Mazda 3 Touring",

      categoria: "Económico",

      estado: "ACTIVO",

      precio: "$250.000 / día",

      ubicacion: "Cali",

      fechaInicio: "2026-06-15",

      fechaFin: "2026-06-20",

      imagen:
        "https://images.unsplash.com/photo-1494976388531-d1058494cdd8?q=80&w=2070"
    }

  ];

  const [estadoFiltro, setEstadoFiltro] =
    useState("TODOS");

  const alquileresFiltrados =
    estadoFiltro === "TODOS"
      ? alquileres
      : alquileres.filter(
          (alquiler) =>
            alquiler.estado === estadoFiltro
        );

  return (

    <div className="rentals-container">

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

          <span>
            Administrador
          </span>

        </div>

      </header>

      {/* HERO */}

      <section className="hero-section">

        <div className="hero-overlay"></div>

        <div className="hero-content">

          <h1>
            Gestión de Alquileres
          </h1>

          <p>
            Consulta y administra todos los alquileres realizados por los clientes.
          </p>

        </div>

      </section>

      {/* FILTROS */}

      <section className="filter-section">

        <h2>
          Buscar por Estado
        </h2>

        <div className="filter-buttons">

          <button
            className={
              estadoFiltro === "TODOS"
                ? "active-filter"
                : ""
            }
            onClick={() =>
              setEstadoFiltro("TODOS")
            }
          >
            Todos
          </button>

          <button
            className={
              estadoFiltro === "ACTIVO"
                ? "active-filter"
                : ""
            }
            onClick={() =>
              setEstadoFiltro("ACTIVO")
            }
          >
            Activos
          </button>

          <button
            className={
              estadoFiltro === "FINALIZADO"
                ? "active-filter"
                : ""
            }
            onClick={() =>
              setEstadoFiltro("FINALIZADO")
            }
          >
            Finalizados
          </button>

        </div>

      </section>

      {/* LISTADO */}

      <section className="rentals-section">

        <h2>
          Alquileres Registrados
        </h2>

        <div className="rentals-grid">

          {alquileresFiltrados.map((alquiler) => (

            <div
              className="rental-card"
              key={alquiler.id}
            >

              <img
                src={alquiler.imagen}
                alt={alquiler.carro}
              />

              <div className="rental-info">

                <span className="categoria">
                  {alquiler.categoria}
                </span>

                <h3>
                  {alquiler.carro}
                </h3>

                <div className="info-grid">

                  <div>
                    <strong>Cliente</strong>

                    <span>
                      {alquiler.usuario}
                    </span>
                  </div>

                  <div>
                    <strong>Estado</strong>

                    <span>
                      {alquiler.estado}
                    </span>
                  </div>

                  <div>
                    <strong>Ubicación</strong>

                    <span>
                      {alquiler.ubicacion}
                    </span>
                  </div>

                  <div>
                    <strong>Precio</strong>

                    <span>
                      {alquiler.precio}
                    </span>
                  </div>

                  <div>
                    <strong>Inicio</strong>

                    <span>
                      {alquiler.fechaInicio}
                    </span>
                  </div>

                  <div>
                    <strong>Fin</strong>

                    <span>
                      {alquiler.fechaFin}
                    </span>
                  </div>

                </div>

                <div className="card-buttons">

                  <button className="details-btn">
                    Ver Detalles
                  </button>

                  <button className="status-btn">
                    Cambiar Estado
                  </button>

                </div>

              </div>

            </div>

          ))}

        </div>

      </section>

    </div>
  );
}

export default AlquileresAdmin;