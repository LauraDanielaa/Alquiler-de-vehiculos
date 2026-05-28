import { useState } from "react";
import "./pag_principal_us.css";

function Home() {

  const carros = [
    {
      id: 1,
      nombre: "BMW M4 Competition",
      categoria: "Lujo",
      descripcion: "Potencia, elegancia y tecnología alemana.",
      imagen:
        "https://images.unsplash.com/photo-1555215695-3004980ad54e?q=80&w=2070",

      galeria: [
        "https://images.unsplash.com/photo-1555215695-3004980ad54e?q=80&w=2070",
        "https://images.unsplash.com/photo-1503376780353-7e6692767b70?q=80&w=2070",
      ],

      marca: "BMW",
      ubicacion: "Bogotá",
      precio: "$850.000 / día",
      disponible: "Disponible",
      transmision: "Automática",
      combustible: "Gasolina",
    },

    {
      id: 2,
      nombre: "Toyota Prado",
      categoria: "SUV",
      descripcion: "Ideal para viajes familiares y terrenos exigentes.",
      imagen:
        "https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?q=80&w=2070",

      galeria: [
        "https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?q=80&w=2070",
        "https://images.unsplash.com/photo-1502877338535-766e1452684a?q=80&w=2070",
      ],

      marca: "Toyota",
      ubicacion: "Medellín",
      precio: "$620.000 / día",
      disponible: "Disponible",
      transmision: "Automática",
      combustible: "Diésel",
    },

    {
      id: 3,
      nombre: "Mazda 3 Touring",
      categoria: "Económico",
      descripcion: "Confort y eficiencia para la ciudad.",
      imagen:
        "https://images.unsplash.com/photo-1494976388531-d1058494cdd8?q=80&w=2070",

      galeria: [
        "https://images.unsplash.com/photo-1494976388531-d1058494cdd8?q=80&w=2070",
        "https://images.unsplash.com/photo-1549399542-7e3f8b79c341?q=80&w=2070",
      ],

      marca: "Mazda",
      ubicacion: "Cali",
      precio: "$250.000 / día",
      disponible: "No disponible",
      transmision: "Manual",
      combustible: "Gasolina",
    },

    {
      id: 4,
      nombre: "Ford Ranger",
      categoria: "Camioneta",
      descripcion: "Fuerza y rendimiento para cualquier desafío.",
      imagen:
        "https://images.unsplash.com/photo-1502877338535-766e1452684a?q=80&w=2070",

      galeria: [
        "https://images.unsplash.com/photo-1502877338535-766e1452684a?q=80&w=2070",
        "https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?q=80&w=2083",
      ],

      marca: "Ford",
      ubicacion: "Barranquilla",
      precio: "$500.000 / día",
      disponible: "Disponible",
      transmision: "Automática",
      combustible: "Diésel",
    },
  ];

  const [selectedCar, setSelectedCar] = useState(null);
  const [filtroUbicacion, setFiltroUbicacion] = useState("Todas");
  const carrosFiltrados =
  filtroUbicacion === "Todas"
    ? carros
    : carros.filter(
        (carro) => carro.ubicacion === filtroUbicacion
      );

  return (
    <div className="home-container">

      {/* HEADER */}

      <header className="header">

        <div className="logo">
          Sterling Drive
        </div>

        <nav>
          <a href="#">Reservas realizadas</a>
          <a href="#">Pagos realizados</a>
          <a href="#">Mi perfil</a>
        </nav>

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

        </div>

      </section>

      {/* VEHICULOS */}

      <section className="cars-section">

        <h2>
          Vehículos Disponibles
        </h2>
        <div className="filters-container">

  <button
    className={filtroUbicacion === "Todas" ? "active-filter" : ""}
    onClick={() => setFiltroUbicacion("Todas")}
  >
    Todas
  </button>

  <button
    className={filtroUbicacion === "Bogotá" ? "active-filter" : ""}
    onClick={() => setFiltroUbicacion("Bogotá")}
  >
    Bogotá
  </button>

  <button
    className={filtroUbicacion === "Medellín" ? "active-filter" : ""}
    onClick={() => setFiltroUbicacion("Medellín")}
  >
    Medellín
  </button>

  <button
    className={filtroUbicacion === "Cali" ? "active-filter" : ""}
    onClick={() => setFiltroUbicacion("Cali")}
  >
    Cali
  </button>

  <button
    className={filtroUbicacion === "Barranquilla" ? "active-filter" : ""}
    onClick={() => setFiltroUbicacion("Barranquilla")}
  >
    Barranquilla
  </button>

</div>

        <div className="cars-grid">

          {carrosFiltrados.map((carro) => (
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

                  <button
                    className="info-btn"
                    onClick={() => setSelectedCar(carro)}
                  >
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

      {/* MODAL */}

      {/* MODAL */}

{selectedCar && (

  <div
    className="modal-overlay"
    onClick={() => setSelectedCar(null)}
  >

    <div
      className="modal-content"
      onClick={(e) => e.stopPropagation()}
    >

      <button
        className="close-modal"
        onClick={() => setSelectedCar(null)}
      >
        ✕
      </button>

      {/* IMAGENES */}

      <div className="modal-images">

        {selectedCar.galeria.map((img, index) => (
          <img key={index} src={img} alt="" />
        ))}

      </div>

      {/* INFORMACION */}

      <div className="modal-info">

        <span className="category">
          {selectedCar.categoria}
        </span>

        <h2>
          {selectedCar.nombre}
        </h2>

        <p>
          {selectedCar.descripcion}
        </p>

        <div className="details-grid">

          <div>
            <strong>Marca</strong>
            <span>{selectedCar.marca}</span>
          </div>

          <div>
            <strong>Ubicación</strong>
            <span>{selectedCar.ubicacion}</span>
          </div>

          <div>
            <strong>Precio</strong>
            <span>{selectedCar.precio}</span>
          </div>

          <div>
            <strong>Estado</strong>
            <span>{selectedCar.disponible}</span>
          </div>

          <div>
            <strong>Transmisión</strong>
            <span>{selectedCar.transmision}</span>
          </div>

          <div>
            <strong>Combustible</strong>
            <span>{selectedCar.combustible}</span>
          </div>

        </div>

        {/* BOTONES */}

        <div className="modal-buttons">

          <button className="rent-btn modal-rent-btn">
            Alquilar Vehículo
          </button>

          <button
            className="info-btn"
            onClick={() => setSelectedCar(null)}
          >
            Cerrar
          </button>

        </div>

      </div>

    </div>

  </div>

)}      {/* FOOTER */}

      <footer className="footer">

        <p>
          Sterling Drive © 2026 — Donde cada viaje comienza con elegancia.
        </p>

      </footer>

    </div>
  );
}

export default Home;