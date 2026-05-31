import { useState } from "react";
import "./manteni_vehi.css";

function MantenimientosPorVehiculo() {

  const [busqueda, setBusqueda] = useState("");

  const vehiculos = [

    {
      id: 1,

      nombre: "BMW M4 Competition",

      placa: "ABC123",

      categoria: "Lujo",

      imagen:
        "https://images.unsplash.com/photo-1555215695-3004980ad54e?q=80&w=2070",

      costoTotal: "$1.250.000",

      mantenimientos: [

        {
          fecha: "2025-05-01",

          descripcion:
            "Cambio de aceite y filtros",

          costo: "$250.000"
        },

        {
          fecha: "2025-05-10",

          descripcion:
            "Cambio de frenos delanteros",

          costo: "$1.000.000"
        }

      ]
    },

    {
      id: 2,

      nombre: "Toyota Prado",

      placa: "DEF456",

      categoria: "SUV",

      imagen:
        "https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?q=80&w=2070",

      costoTotal: "$600.000",

      mantenimientos: [

        {
          fecha: "2025-05-08",

          descripcion:
            "Cambio de llantas delanteras",

          costo: "$600.000"
        }

      ]
    },

    {
      id: 3,

      nombre: "Mazda 3 Touring",

      placa: "GHI789",

      categoria: "Económico",

      imagen:
        "https://images.unsplash.com/photo-1494976388531-d1058494cdd8?q=80&w=2070",

      costoTotal: "$350.000",

      mantenimientos: [

        {
          fecha: "2025-05-12",

          descripcion:
            "Alineación y balanceo",

          costo: "$350.000"
        }

      ]
    }

  ];

  const vehiculosFiltrados = vehiculos.filter(
    (vehiculo) =>
      vehiculo.placa
        .toLowerCase()
        .includes(busqueda.toLowerCase())
  );

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
            Mantenimientos por Vehículo
          </h1>

          <p>
            Consulta historial de mantenimientos y costos totales por vehículo.
          </p>

        </div>

      </section>

      {/* BUSCADOR */}

      <section className="search-section">

        <div className="search-box">

          <input
            type="text"
            placeholder="Buscar vehículo por placa..."
            value={busqueda}
            onChange={(e) =>
              setBusqueda(e.target.value)
            }
          />

        </div>

      </section>

      {/* VEHICULOS */}

      <section className="vehicles-section">

        <div className="vehicles-grid">

          {vehiculosFiltrados.map((vehiculo) => (

            <div
              className="vehicle-card"
              key={vehiculo.id}
            >

              <img
                src={vehiculo.imagen}
                alt={vehiculo.nombre}
              />

              <div className="vehicle-content">

                <div className="vehicle-header">

                  <span className="category">
                    {vehiculo.categoria}
                  </span>

                </div>

                <h2>
                  {vehiculo.nombre}
                </h2>

                <div className="vehicle-info">

                  <div>

                    <strong>
                      Placa
                    </strong>

                    <span>
                      {vehiculo.placa}
                    </span>

                  </div>

                  <div>

                    <strong>
                      Costo Total
                    </strong>

                    <span className="gold-text">
                      {vehiculo.costoTotal}
                    </span>

                  </div>

                </div>

                {/* HISTORIAL */}

                <div className="history-section">

                  <h3>
                    Historial de Mantenimientos
                  </h3>

                  <div className="history-list">

                    {vehiculo.mantenimientos.map(
                      (item, index) => (

                        <div
                          className="history-card"
                          key={index}
                        >

                          <div>

                            <strong>
                              Fecha
                            </strong>

                            <span>
                              {item.fecha}
                            </span>

                          </div>

                          <div>

                            <strong>
                              Costo
                            </strong>

                            <span>
                              {item.costo}
                            </span>

                          </div>

                          <div className="full-width">

                            <strong>
                              Descripción
                            </strong>

                            <span>
                              {item.descripcion}
                            </span>

                          </div>

                        </div>

                      )
                    )}

                  </div>

                </div>

              </div>

            </div>

          ))}

        </div>

      </section>

      {/* FOOTER */}

      <footer className="footer">

        <p>
          Sterling Drive © 2026 — Control total del estado y mantenimiento de la flota.
        </p>

      </footer>

    </div>
  );
}

export default MantenimientosPorVehiculo;