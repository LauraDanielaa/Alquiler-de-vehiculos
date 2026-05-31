import { useState } from "react";
import "./crear_sucursal.css";

function Sucursales() {
const [selectedBranch, setSelectedBranch] = useState(null);
const [searchId, setSearchId] = useState("");
const [filteredBranches, setFilteredBranches] = useState([]);

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
if (filteredBranches.length === 0 && searchId === "") {
  filteredBranches.push(...sucursales);
}
  const handleSearch = () => {

  if (searchId === "") {
    setFilteredBranches(sucursales);
    return;
  }

  const result = sucursales.filter(
    (sucursal) => sucursal.id === Number(searchId)
  );

  setFilteredBranches(result);
};
if (filteredBranches.length === 0 && searchId === "") {
  filteredBranches.push(...sucursales);
}

  return (

    <div className="branch-container">
    {/* HEADER */}

    <header className="top-header">

      <div className="back-button">

        ← Volver

      </div>

      <div className="profile-section">

        <div className="profile-circle">
          A
        </div>

        <span>
          Administrador
        </span>

      </div>

    </header>

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
            value={searchId}
            onChange={(e) => setSearchId(e.target.value)}
          />

          <button onClick={handleSearch}>
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

          {filteredBranches.map((sucursal) => (

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

                <button
                  className="update-btn"
                  onClick={() => setSelectedBranch(sucursal)}
                >
                  Actualizar
                </button>

              </div>

            </div>

          ))}

        </div>

      </div>
      {/* MODAL ACTUALIZAR */}

{selectedBranch && (

  <div
    className="modal-overlay"
    onClick={() => setSelectedBranch(null)}
  >

    <div
      className="update-modal"
      onClick={(e) => e.stopPropagation()}
    >

      <button
        className="close-modal"
        onClick={() => setSelectedBranch(null)}
      >
        ✕
      </button>

      <h2>
        Actualizar Sucursal
      </h2>

      <p>
        Modifica la información de la sucursal.
      </p>

      <form className="update-form">

        <div className="input-group">
          <label>Nombre</label>

          <input
            type="text"
            defaultValue={selectedBranch.nombre}
          />
        </div>

        <div className="input-group">
          <label>Ciudad</label>

          <input
            type="text"
            defaultValue={selectedBranch.ciudad}
          />
        </div>

        <div className="input-group">
          <label>Teléfono</label>

          <input
            type="text"
            defaultValue={selectedBranch.telefono}
          />
        </div>

        <div className="input-group">
          <label>Dirección</label>

          <input
            type="text"
            defaultValue={selectedBranch.direccion}
          />
        </div>

        <div className="modal-buttons">

          <button className="save-btn">
            Guardar Cambios
          </button>

          <button
            type="button"
            className="cancel-btn"
            onClick={() => setSelectedBranch(null)}
          >
            Cancelar
          </button>

        </div>

      </form>

    </div>

  </div>

)}
    </div>
  );
}

export default Sucursales;