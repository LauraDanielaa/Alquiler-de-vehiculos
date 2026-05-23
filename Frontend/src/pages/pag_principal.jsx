import "./pag_principal.css";

function Home() {

  const carros = [
    {
      id: 1,
      nombre: "BMW M4 Competition",
      categoria: "Lujo",
      descripcion: "Potencia, elegancia y tecnología alemana.",
      imagen:
        "https://images.unsplash.com/photo-1555215695-3004980ad54e?q=80&w=2070",
    },
    {
      id: 2,
      nombre: "Toyota Prado",
      categoria: "SUV",
      descripcion: "Ideal para viajes familiares y terrenos exigentes.",
      imagen:
        "https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?q=80&w=2070",
    },
    {
      id: 3,
      nombre: "Mazda 3 Touring",
      categoria: "Económico",
      descripcion: "Confort y eficiencia para la ciudad.",
      imagen:
        "https://images.unsplash.com/photo-1494976388531-d1058494cdd8?q=80&w=2070",
    },
    {
      id: 4,
      nombre: "Ford Ranger",
      categoria: "Camioneta",
      descripcion: "Fuerza y rendimiento para cualquier desafío.",
      imagen:
        "https://images.unsplash.com/photo-1502877338535-766e1452684a?q=80&w=2070",
    },
  ];

  return (
    <div className="home-container">

      {/* HEADER */}

      <header className="header">

        <div className="logo">
          Sterling Drive
        </div>

        <nav>
          <a href="#">Inicio</a>
          <a href="#">Vehículos</a>
          <a href="#">Servicios</a>
        </nav>

        <div className="header-buttons">
          <button className="login-btn">
            Ingresar
          </button>

          <button className="register-btn">
            Registrarse
          </button>
        </div>

      </header>

      {/* HERO */}

      <section className="hero">

        <div className="hero-overlay"></div>

        <div className="hero-content">

          <h1>
            Conduce el lujo que mereces
          </h1>

          <p>
            Reserva vehículos premium, SUV y camionetas modernas para cualquier ocasión.
          </p>

          <button>
            Explorar Vehículos
          </button>

        </div>

      </section>

      {/* VEHICULOS */}

      <section className="cars-section">

        <h2>
          Vehículos Disponibles
        </h2>

        <div className="cars-grid">

          {carros.map((carro) => (
            <div className="car-card" key={carro.id}>

              <img src={carro.imagen} alt={carro.nombre} />

              <div className="car-info">

                <span className="category">
                  {carro.categoria}
                </span>

                <h3>
                  {carro.nombre}
                </h3>

                <p>
                  {carro.descripcion}
                </p>

                <div className="card-buttons">

                  <button className="info-btn">
                    Más información
                  </button>

                  <button className="rent-btn">
                    Alquilar
                  </button>

                </div>

              </div>

            </div>
          ))}

        </div>

      </section>

      {/* FOOTER */}

      <footer className="footer">

        <p>
          Sterling Drive © 2026 — Donde cada viaje comienza con elegancia.
        </p>

      </footer>

    </div>
  );
}

export default Home;