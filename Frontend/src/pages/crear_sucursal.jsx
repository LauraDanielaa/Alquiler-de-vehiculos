import "./crear_sucursal.css";

function Sucursales() {

  const sucursales = [
    {
      id: 1,
      nombre: "Sucursal Norte",
      ciudad: "Bogotá",
      telefono: "6019999999",
      direccion: "Av 19 # 200-50"
    },

    {
      id: 2,
      nombre: "Sucursal Centro",
      ciudad: "Medellín",
      telefono: "6047777777",
      direccion: "Cra 45 # 10-20"
    },

    {
      id: 3,
      nombre: "Sucursal Premium",
      ciudad: "Cali",
      telefono: "6028888888",
      direccion: "Calle 5 # 80-15"
    }
  ];

  return (

    <div className="branch-container">

      {/* HEADER */}

      <div className="branch-header">

        <div className="branch-form-section">

          <h1>
            Crear Sucursal
          </h1>

          <p>
            Registra nuevas sucursales para Sterling Drive.
          </p>

          <form className="branch-form">

            <div className="input-group">
              <label>Nombre</label>
              <input
                type="text"
                placeholder="Sucursal Norte"
              />
            </div>

            <div className="input-group">
              <label>Ciudad</label>
              <input
                type="text"
                placeholder="Bogotá"
              />
            </div>

            <div className="input-group">
              <label>Teléfono</label>
              <input
                type="text"
                placeholder="6019999999"
              />
            </div>

            <div className="input-group">
              <label>Dirección</label>
              <input
                type="text"
                placeholder="Av 19 # 200-50"
              />
            </div>

            <button className="create-btn">
              Crear Sucursal
            </button>

          </form>

        </div>

        {/* IMAGEN */}

        <div className="branch-image">

          <div className="overlay"></div>

          <div className="image-content">

            <h2>
              Sterling Drive
            </h2>

            <p>
              Administra las sucursales y mantén el control de cada sede.
            </p>

          </div>

        </div>

      </div>

      {/* BUSQUEDA */}

      <div className="search-section">

        <h2>
          Buscar sucursal por ID
        </h2>

        <div className="search-box">

          <input
            type="number"
            placeholder="Ingrese ID de la sucursal"
            min="1"
            max="5"
          />

          <button>
            Buscar
          </button>

        </div>

      </div>

      {/* LISTADO */}

      <div className="branches-section">

        <h2>
          Sucursales Registradas
        </h2>

        <div className="branches-grid">

          {sucursales.map((sucursal) => (

            <div className="branch-card" key={sucursal.id}>

              <span className="branch-id">
                ID #{sucursal.id}
              </span>

              <h3>
                {sucursal.nombre}
              </h3>

              <p>
                {sucursal.ciudad}
              </p>

              <div className="branch-info">

                <div>
                  <strong>Teléfono</strong>
                  <span>{sucursal.telefono}</span>
                </div>

                <div>
                  <strong>Dirección</strong>
                  <span>{sucursal.direccion}</span>
                </div>

              </div>

              <div className="branch-buttons">

                <button className="info-btn">
                  Ver información
                </button>

                <button className="update-btn">
                  Actualizar
                </button>

              </div>

            </div>

          ))}

        </div>

      </div>

    </div>
  );
}

export default Sucursales;