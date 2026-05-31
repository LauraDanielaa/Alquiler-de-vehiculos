import { useState } from "react";
import "./crear_vehiculo.css";

function Vehiculos() {

  const [vehiculos, setVehiculos] = useState([
    {
      id: 1,
      placa: "ABC123",
      marca: "Toyota",
      anio: 2022,
      modelo: "Corolla",
      color: "Blanco",
      kilometraje: 15000,
      precioDia: 150000,
      estado: "DISPONIBLE",
      idCategoria: 1,
      idSucursal: 1
    },

    {
      id: 2,
      placa: "XYZ789",
      marca: "BMW",
      anio: 2023,
      modelo: "M4 Competition",
      color: "Negro",
      kilometraje: 5000,
      precioDia: 850000,
      estado: "DISPONIBLE",
      idCategoria: 2,
      idSucursal: 2
    }
  ]);

  const [filteredVehiculos, setFilteredVehiculos] =
    useState(vehiculos);

  const [searchPlaca, setSearchPlaca] =
    useState("");

  const [nuevoVehiculo, setNuevoVehiculo] = useState({
    placa: "",
    marca: "",
    anio: "",
    modelo: "",
    color: "",
    kilometraje: "",
    precioDia: "",
    estado: "",
    idCategoria: "",
    idSucursal: ""
  });

  /* CREAR VEHICULO */

  const crearVehiculo = (e) => {

    e.preventDefault();

    const vehiculo = {
      id: vehiculos.length + 1,
      ...nuevoVehiculo
    };

    const nuevosVehiculos = [...vehiculos, vehiculo];

    setVehiculos(nuevosVehiculos);

    setFilteredVehiculos(nuevosVehiculos);

    setNuevoVehiculo({
      placa: "",
      marca: "",
      anio: "",
      modelo: "",
      color: "",
      kilometraje: "",
      precioDia: "",
      estado: "",
      idCategoria: "",
      idSucursal: ""
    });
  };

  /* BUSCAR */

  const buscarVehiculo = () => {

    if (searchPlaca === "") {
      setFilteredVehiculos(vehiculos);
      return;
    }

    const resultado = vehiculos.filter(
      (vehiculo) =>
        vehiculo.placa
          .toLowerCase()
          .includes(searchPlaca.toLowerCase())
    );

    setFilteredVehiculos(resultado);
  };

  return (

    <div className="vehicle-container">

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

      <div className="vehicle-hero">

        {/* FORM */}

        <div className="vehicle-form-section">

          <h1>
            Crear Vehículo
          </h1>

          <p>
            Registra nuevos vehículos para la plataforma.
          </p>

          <form
            className="vehicle-form"
            onSubmit={crearVehiculo}
          >

            <div className="grid-form">

              <div className="input-group">
                <label>Placa</label>

                <input
                  type="text"
                  value={nuevoVehiculo.placa}
                  onChange={(e) =>
                    setNuevoVehiculo({
                      ...nuevoVehiculo,
                      placa: e.target.value
                    })
                  }
                  placeholder="ABC123"
                />
              </div>

              <div className="input-group">
                <label>Marca</label>

                <input
                  type="text"
                  placeholder="Toyota"
                  value={nuevoVehiculo.marca}
                  onChange={(e) =>
                    setNuevoVehiculo({
                      ...nuevoVehiculo,
                      marca: e.target.value
                    })
                  }
                />
              </div>

              <div className="input-group">
                <label>Año</label>

                <input
                  type="number"
                  placeholder="2022"
                  value={nuevoVehiculo.anio}
                  onChange={(e) =>
                    setNuevoVehiculo({
                      ...nuevoVehiculo,
                      anio: e.target.value
                    })
                  }
                />
              </div>

              <div className="input-group">
                <label>Modelo</label>

                <input
                  type="text"
                  placeholder="Corolla"
                  value={nuevoVehiculo.modelo}
                  onChange={(e) =>
                    setNuevoVehiculo({
                      ...nuevoVehiculo,
                      modelo: e.target.value
                    })
                  }
                />
              </div>

              <div className="input-group">
                <label>Color</label>

                <input
                  type="text"
                  placeholder="Blanco"
                  value={nuevoVehiculo.color}
                  onChange={(e) =>
                    setNuevoVehiculo({
                      ...nuevoVehiculo,
                      color: e.target.value
                    })
                  }
                />
              </div>

              <div className="input-group">
                <label>Kilometraje</label>

                <input
                  type="number"
                  placeholder="15000"
                  value={nuevoVehiculo.kilometraje}
                  onChange={(e) =>
                    setNuevoVehiculo({
                      ...nuevoVehiculo,
                      kilometraje: e.target.value
                    })
                  }
                />
              </div>

              <div className="input-group">
                <label>Precio por día</label>

                <input
                  type="number"
                  placeholder="150000"
                  value={nuevoVehiculo.precioDia}
                  onChange={(e) =>
                    setNuevoVehiculo({
                      ...nuevoVehiculo,
                      precioDia: e.target.value
                    })
                  }
                />
              </div>

              <div className="input-group">
                <label>Estado</label>

                <input
                  type="text"
                  placeholder="DISPONIBLE"
                  value={nuevoVehiculo.estado}
                  onChange={(e) =>
                    setNuevoVehiculo({
                      ...nuevoVehiculo,
                      estado: e.target.value
                    })
                  }
                />
              </div>

              <div className="input-group">
                <label>ID Categoría</label>

                <input
                  type="number"
                  placeholder="1"
                  value={nuevoVehiculo.idCategoria}
                  onChange={(e) =>
                    setNuevoVehiculo({
                      ...nuevoVehiculo,
                      idCategoria: e.target.value
                    })
                  }
                />
              </div>

              <div className="input-group">
                <label>ID Sucursal</label>

                <input
                  type="number"
                  placeholder="1"
                  value={nuevoVehiculo.idSucursal}
                  onChange={(e) =>
                    setNuevoVehiculo({
                      ...nuevoVehiculo,
                      idSucursal: e.target.value
                    })
                  }
                />
              </div>

            </div>

            <button type="submit">
              Crear Vehículo
            </button>

          </form>

        </div>

        {/* IMAGEN */}

        <div className="vehicle-image">

          <div className="overlay"></div>

          <div className="image-content">

            <h2>
              Gestión Vehicular
            </h2>

            <p>
              Controla y administra toda la flota de Sterling Drive.
            </p>

          </div>

        </div>

      </div>

      {/* BUSQUEDA */}

      <section className="search-section">

        <h2>
          Buscar Vehículo por Placa
        </h2>

        <div className="search-box">

          <input
            type="text"
            placeholder="Ingrese la placa"
            value={searchPlaca}
            onChange={(e) =>
              setSearchPlaca(e.target.value)
            }
          />

          <button onClick={buscarVehiculo}>
            Buscar
          </button>

        </div>

      </section>

      {/* LISTADO */}

      <section className="vehicles-section">

        <h2>
          Vehículos Registrados
        </h2>

        <div className="vehicles-grid">

          {filteredVehiculos.map((vehiculo) => (

            <div
              className="vehicle-card"
              key={vehiculo.id}
            >

              <span className="vehicle-id">
                ID #{vehiculo.id}
              </span>

              <h3>
                {vehiculo.marca} {vehiculo.modelo}
              </h3>

              <div className="vehicle-info">

                <div>
                  <strong>Placa</strong>
                  <span>{vehiculo.placa}</span>
                </div>

                <div>
                  <strong>Año</strong>
                  <span>{vehiculo.anio}</span>
                </div>

                <div>
                  <strong>Color</strong>
                  <span>{vehiculo.color}</span>
                </div>

                <div>
                  <strong>Kilometraje</strong>
                  <span>{vehiculo.kilometraje} km</span>
                </div>

                <div>
                  <strong>Precio Día</strong>
                  <span>${vehiculo.precioDia}</span>
                </div>

                <div>
                  <strong>Estado</strong>
                  <span>{vehiculo.estado}</span>
                </div>

              </div>

            </div>

          ))}

        </div>

      </section>

    </div>
  );
}

export default Vehiculos;