import { useState } from "react";
import "./mis_alquileres.css";

function Misalquileres() {

  const reservas = [

    {
      id: 1,

      nombre: "BMW M4 Competition",

      categoria: "Lujo",

      imagen:
        "https://images.unsplash.com/photo-1555215695-3004980ad54e?q=80&w=2070",

      fechaInicio: "2026-06-01",

      fechaFin: "2026-06-08",

      ubicacion: "Bogotá",

      precio: "$850.000 / día",

      estado: "Reserva Confirmada"
    },

    {
      id: 2,

      nombre: "Toyota Prado",

      categoria: "SUV",

      imagen:
        "https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?q=80&w=2070",

      fechaInicio: "2026-06-15",

      fechaFin: "2026-06-20",

      ubicacion: "Medellín",

      precio: "$620.000 / día",

      estado: "Pendiente"
    },

    {
      id: 3,

      nombre: "Mazda 3 Touring",

      categoria: "Económico",

      imagen:
        "https://images.unsplash.com/photo-1494976388531-d1058494cdd8?q=80&w=2070",

      fechaInicio: "2026-07-01",

      fechaFin: "2026-07-05",

      ubicacion: "Cali",

      precio: "$250.000 / día",

      estado: "Finalizada"
    }

  ];

  const [showPagoModal, setShowPagoModal] =
    useState(false);

  const [alquilerSeleccionado,
    setAlquilerSeleccionado] = useState(null);

  return (

    <div className="reservas-container">

      {/* HEADER */}

      <header className="header">

        <div className="logo">
          Sterling Drive
        </div>

        <nav>

          <a href="#">
            Mis reservas
          </a>

          <a href="#">
            Pagos realizados
          </a>

          <a href="#">
            Mi perfil
          </a>

        </nav>

        <div className="profile-section">

          <div className="profile-circle">
            A
          </div>

        </div>

      </header>

      {/* HERO */}

      <section className="hero-reservas">

        <div className="hero-overlay"></div>

        <div className="hero-content">

          <h1>
            Mis Reservas
          </h1>

          <p>
            Consulta los vehículos que has reservado y el historial de tus alquileres.
          </p>

        </div>

      </section>

      {/* LISTADO */}

      <section className="reservas-section">

        <h2>
          Vehículos Reservados
        </h2>

        <div className="reservas-grid">

          {reservas.map((reserva) => (

            <div
              className="reserva-card"
              key={reserva.id}
            >

              <img
                src={reserva.imagen}
                alt={reserva.nombre}
              />

              <div className="reserva-info">

                <span className="categoria">
                  {reserva.categoria}
                </span>

                <h3>
                  {reserva.nombre}
                </h3>

                <div className="details-grid">

                  <div>
                    <strong>Fecha Inicio</strong>

                    <span>
                      {reserva.fechaInicio}
                    </span>
                  </div>

                  <div>
                    <strong>Fecha Fin</strong>

                    <span>
                      {reserva.fechaFin}
                    </span>
                  </div>

                  <div>
                    <strong>Ubicación</strong>

                    <span>
                      {reserva.ubicacion}
                    </span>
                  </div>

                  <div>
                    <strong>Precio</strong>

                    <span>
                      {reserva.precio}
                    </span>
                  </div>

                  <div>
                    <strong>Estado</strong>

                    <span>
                      {reserva.estado}
                    </span>
                  </div>

                </div>

                {/* BOTONES */}

                <div className="card-buttons">

                  <button className="details-btn">
                    Ver Detalles
                  </button>

                  <button
                    className="pay-btn"
                    onClick={() => {
                      setShowPagoModal(true);
                      setAlquilerSeleccionado(reserva);
                    }}
                  >
                    Registrar Pago
                  </button>

                </div>

              </div>

            </div>

          ))}

        </div>

      </section>

      {/* MODAL PAGO */}

      {showPagoModal && (

        <div
          className="modal-overlay"
          onClick={() =>
            setShowPagoModal(false)
          }
        >

          <div
            className="payment-modal"
            onClick={(e) =>
              e.stopPropagation()
            }
          >

            <button
              className="close-modal"
              onClick={() =>
                setShowPagoModal(false)
              }
            >
              ✕
            </button>

            <h2>
              Registrar Pago
            </h2>

            <p>
              Completa la información del pago.
            </p>

            <form className="payment-form">

              <div className="input-group">

                <label>
                  ID Alquiler
                </label>

                <input
                  type="text"
                  value={alquilerSeleccionado?.id}
                  disabled
                />

              </div>

              <div className="input-group">

                <label>
                  Monto
                </label>

                <input
                  type="number"
                  placeholder="1350000"
                />

              </div>

              <div className="input-group">

                <label>
                  Método de Pago
                </label>

                <select>

                  <option>
                    TARJETA
                  </option>

                  <option>
                    EFECTIVO
                  </option>

                  <option>
                    TRANSFERENCIA
                  </option>

                </select>

              </div>

              <button
                type="submit"
                className="confirm-payment-btn"
              >
                Confirmar Pago
              </button>

            </form>

          </div>

        </div>

      )}

      {/* FOOTER */}

      <footer className="footer">

        <p>
          Sterling Drive © 2026 — Donde cada viaje comienza con elegancia.
        </p>

      </footer>

    </div>
  );
}

export default Misalquileres;