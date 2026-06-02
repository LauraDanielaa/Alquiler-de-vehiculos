import "./mis_reservas.css";
import { Link, useNavigate } from "react-router-dom";

function Reservas() {
  const navigate = useNavigate();
  return (

    <div className="reservation-container">

      {/* HEADER */}

      <header className="reservation-header">

        {/* BOTON VOLVER */}

        <div className="header-left">

          <button onClick={() => navigate("/principal_cliente")} className="back-btn">
            ← Volver
          </button>

        </div>

        {/* TITULO */}

        <div className="header-center">

          <h1>
            Reservar Vehículo
          </h1>

          <p>
            Completa la información para confirmar tu reserva.
          </p>

        </div>

        {/* PERFIL */}

        <div className="header-right">

          <div className="profile-circle">
            Usuario
          </div>

        </div>

      </header>

      {/* CARD PRINCIPAL */}

      <div className="reservation-card">

        {/* IMAGEN */}

        <div className="reservation-image">

          <img
            src="https://images.unsplash.com/photo-1555215695-3004980ad54e?q=80&w=2070"
            alt="Vehiculo"
          />

        </div>

        {/* FORMULARIO */}

        <div className="reservation-form">

          <h2>
            BMW M4 Competition
          </h2>

          <span className="reservation-category">
            Lujo
          </span>

          <p className="reservation-description">
            Vehículo premium con transmisión automática, ideal para experiencias de lujo y alto rendimiento.
          </p>

          {/* FORM */}

          <form>

            <div className="input-group">

              <label>
                Fecha de inicio
              </label>

              <input type="date" />

            </div>

            <div className="input-group">

              <label>
                Fecha de finalización
              </label>

              <input type="date" />

            </div>

            <div className="reservation-info">

              <div>
                <strong>Ubicación</strong>
                <span>Bogotá</span>
              </div>

              <div>
                <strong>Precio</strong>
                <span>$850.000 / día</span>
              </div>

            </div>

            {/* BOTONES */}

            <div className="reservation-buttons">

              <button
                type="submit"
                className="confirm-btn"
              >
                Confirmar Reserva
              </button>

              <button
                type="button"
                className="cancel-btn"
              >
                Cancelar
              </button>

            </div>

          </form>

        </div>

      </div>

    </div>
  );
}

export default Reservas;