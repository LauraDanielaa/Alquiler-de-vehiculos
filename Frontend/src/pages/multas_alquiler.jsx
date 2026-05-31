import { useState } from "react";
import "./multas_alquiler.css";

function MultasPorAlquiler() {

  const [busqueda, setBusqueda] = useState("");

  const multas = [

    {
      id: 1,

      idAlquiler: 101,

      vehiculo: "BMW M4 Competition",

      cliente: "Carlos Ramírez",

      descripcion:
        "Daño en la puerta trasera derecha",

      monto: "$500.000",

      fecha: "2025-06-10",

      estado: "Pendiente",

      imagen:
        "https://images.unsplash.com/photo-1555215695-3004980ad54e?q=80&w=2070"
    },

    {
      id: 2,

      idAlquiler: 102,

      vehiculo: "Toyota Prado",

      cliente: "Laura Gómez",

      descripcion:
        "Retraso en devolución del vehículo",

      monto: "$250.000",

      fecha: "2025-06-15",

      estado: "Pagada",

      imagen:
        "https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?q=80&w=2070"
    },

    {
      id: 3,

      idAlquiler: 103,

      vehiculo: "Mazda 3 Touring",

      cliente: "Andrés Torres",

      descripcion:
        "Rayón en parachoques delantero",

      monto: "$180.000",

      fecha: "2025-06-18",

      estado: "Pendiente",

      imagen:
        "https://images.unsplash.com/photo-1494976388531-d1058494cdd8?q=80&w=2070"
    }

  ];

  const multasFiltradas = multas.filter((multa) =>
    multa.idAlquiler
      .toString()
      .includes(busqueda)
  );

  return (

    <div className="multas-container">

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
            Multas por Alquiler
          </h1>

          <p>
            Consulta penalizaciones asociadas a los alquileres realizados.
          </p>

        </div>

      </section>

      {/* BUSCADOR */}

      <section className="search-section">

        <div className="search-box">

          <input
            type="number"
            placeholder="Buscar por ID de alquiler..."
            value={busqueda}
            onChange={(e) =>
              setBusqueda(e.target.value)
            }
          />

        </div>

      </section>

      {/* LISTADO */}

      <section className="cards-section">

        <div className="cards-grid">

          {multasFiltradas.map((multa) => (

            <div
              className="multa-card"
              key={multa.id}
            >

              <img
                src={multa.imagen}
                alt={multa.vehiculo}
              />

              <div className="card-content">

                <div className="card-header">

                  <span className="status">
                    {multa.estado}
                  </span>

                </div>

                <h2>
                  {multa.vehiculo}
                </h2>

                <p className="cliente">
                  Cliente: {multa.cliente}
                </p>

                <div className="details-grid">

                  <div>
                    <strong>ID Alquiler</strong>

                    <span>
                      #{multa.idAlquiler}
                    </span>
                  </div>

                  <div>
                    <strong>Monto</strong>

                    <span>
                      {multa.monto}
                    </span>
                  </div>

                  <div>
                    <strong>Fecha</strong>

                    <span>
                      {multa.fecha}
                    </span>
                  </div>

                </div>

                <div className="descripcion-box">

                  <strong>
                    Descripción
                  </strong>

                  <p>
                    {multa.descripcion}
                  </p>

                </div>

              </div>

            </div>

          ))}

        </div>

      </section>

      {/* FOOTER */}

      <footer className="footer">

        <p>
          Sterling Drive © 2026 — Seguridad y control en cada alquiler.
        </p>

      </footer>

    </div>
  );
}

export default MultasPorAlquiler;