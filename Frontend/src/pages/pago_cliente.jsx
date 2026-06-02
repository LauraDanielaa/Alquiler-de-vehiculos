import "./pago_cliente.css";

function HistorialPagos() {

  const pagos = [

    {
      id: 1,

      vehiculo: "BMW M4 Competition",

      categoria: "Lujo",

      imagen:
        "https://images.unsplash.com/photo-1555215695-3004980ad54e?q=80&w=2070",

      monto: "$1.350.000",

      metodo: "TARJETA",

      fecha: "2026-06-10",

      estado: "Pagado"
    },

    {
      id: 2,

      vehiculo: "Toyota Prado",

      categoria: "SUV",

      imagen:
        "https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?q=80&w=2070",

      monto: "$620.000",

      metodo: "EFECTIVO",

      fecha: "2026-06-18",

      estado: "Pagado"
    },

    {
      id: 3,

      vehiculo: "Mazda 3 Touring",

      categoria: "Económico",

      imagen:
        "https://images.unsplash.com/photo-1494976388531-d1058494cdd8?q=80&w=2070",

      monto: "$250.000",

      metodo: "TRANSFERENCIA",

      fecha: "2026-07-05",

      estado: "Pendiente"
    }

  ];

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

        <div className="profile-circle">
          J
        </div>

      </header>

      {/* HERO */}

      <section className="hero-section">

        <div className="hero-overlay"></div>

        <div className="hero-content">

          <h1>
            Historial de Pagos
          </h1>

          <p>
            Consulta todos los pagos realizados en tus reservas y alquileres.
          </p>

        </div>

      </section>

      {/* LISTADO */}

      <section className="payments-section">

        <div className="payments-grid">

          {pagos.map((pago) => (

            <div
              className="payment-card"
              key={pago.id}
            >

              <img
                src={pago.imagen}
                alt={pago.vehiculo}
              />

              <div className="payment-content">

                <div className="payment-top">

                  <span className="category">
                    {pago.categoria}
                  </span>

                  <span
                    className={
                      pago.estado === "Pagado"
                        ? "status-paid"
                        : "status-pending"
                    }
                  >
                    {pago.estado}
                  </span>

                </div>

                <h2>
                  {pago.vehiculo}
                </h2>

                {/* INFO */}

                <div className="payment-info">

                  <div>

                    <strong>
                      Monto
                    </strong>

                    <span className="gold-text">
                      {pago.monto}
                    </span>

                  </div>

                  <div>

                    <strong>
                      Método de Pago
                    </strong>

                    <span>
                      {pago.metodo}
                    </span>

                  </div>

                  <div>

                    <strong>
                      Fecha de Pago
                    </strong>

                    <span>
                      {pago.fecha}
                    </span>

                  </div>

                </div>

                {/* BOTON */}

                <button className="details-btn">
                  Ver Detalles
                </button>

              </div>

            </div>

          ))}

        </div>

      </section>

      {/* FOOTER */}

      <footer className="footer">

        <p>
          Sterling Drive © 2026 — Tu historial financiero siempre disponible.
        </p>

      </footer>

    </div>
  );
}

export default HistorialPagos;