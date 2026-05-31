import { useState } from "react";
import "./todos_pagos.css";

function Pagos() {

  const pagos = [

    {
      id: 1,

      idAlquiler: 101,

      cliente: "Laura Gómez",

      vehiculo: "BMW M4 Competition",

      monto: "$1.350.000",

      metodo: "TARJETA",

      estado: "PAGADO",

      fecha: "2026-06-01"
    },

    {
      id: 2,

      idAlquiler: 102,

      cliente: "Carlos Ramírez",

      vehiculo: "Toyota Prado",

      monto: "$950.000",

      metodo: "EFECTIVO",

      estado: "PENDIENTE",

      fecha: "2026-06-05"
    },

    {
      id: 3,

      idAlquiler: 103,

      cliente: "Andrea Torres",

      vehiculo: "Mazda 3 Touring",

      monto: "$620.000",

      metodo: "TRANSFERENCIA",

      estado: "PAGADO",

      fecha: "2026-06-10"
    }

  ];

  const [busquedaId, setBusquedaId] =
    useState("");

  const [busquedaAlquiler,
    setBusquedaAlquiler] = useState("");

  const pagosFiltrados = pagos.filter((pago) => {

    const coincideId =
      busquedaId === "" ||
      pago.id.toString().includes(busquedaId);

    const coincideAlquiler =
      busquedaAlquiler === "" ||
      pago.idAlquiler.toString().includes(busquedaAlquiler);

    return coincideId && coincideAlquiler;
  });

  return (

    <div className="payments-container">

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
            Gestión de Pagos
          </h1>

          <p>
            Consulta pagos realizados y pagos asociados a alquileres.
          </p>

        </div>

      </section>

      {/* FILTROS */}

      <section className="filters-section">

        <div className="filter-box">

          <label>
            Buscar Pago por ID
          </label>

          <input
            type="number"
            placeholder="Ej: 1"
            value={busquedaId}
            onChange={(e) =>
              setBusquedaId(e.target.value)
            }
          />

        </div>

        <div className="filter-box">

          <label>
            Buscar Pago por Alquiler
          </label>

          <input
            type="number"
            placeholder="Ej: 101"
            value={busquedaAlquiler}
            onChange={(e) =>
              setBusquedaAlquiler(e.target.value)
            }
          />

        </div>

      </section>

      {/* LISTADO */}

      <section className="payments-section">

        <h2>
          Pagos Registrados
        </h2>

        <div className="payments-grid">

          {pagosFiltrados.map((pago) => (

            <div
              className="payment-card"
              key={pago.id}
            >

              <div className="payment-header">

                <span className="payment-id">
                  Pago #{pago.id}
                </span>

                <span className="payment-status">
                  {pago.estado}
                </span>

              </div>

              <h3>
                {pago.vehiculo}
              </h3>

              <div className="payment-info">

                <div>
                  <strong>Cliente</strong>

                  <span>
                    {pago.cliente}
                  </span>
                </div>

                <div>
                  <strong>ID Alquiler</strong>

                  <span>
                    {pago.idAlquiler}
                  </span>
                </div>

                <div>
                  <strong>Monto</strong>

                  <span>
                    {pago.monto}
                  </span>
                </div>

                <div>
                  <strong>Método</strong>

                  <span>
                    {pago.metodo}
                  </span>
                </div>

                <div>
                  <strong>Fecha</strong>

                  <span>
                    {pago.fecha}
                  </span>
                </div>

              </div>

            </div>

          ))}

        </div>

      </section>

    </div>
  );
}

export default Pagos;