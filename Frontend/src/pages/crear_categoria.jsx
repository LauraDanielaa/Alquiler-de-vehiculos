import { useState } from "react";
import "./crear_categoria.css";

function Categorias() {

  const [categorias, setCategorias] = useState([
    {
      id: 1,
      nombreCategoria: "SUV",
      descripcion: "Vehículos SUV de alto rendimiento"
    },

    {
      id: 2,
      nombreCategoria: "Lujo",
      descripcion: "Vehículos premium y exclusivos"
    },

    {
      id: 3,
      nombreCategoria: "Económico",
      descripcion: "Vehículos accesibles y eficientes"
    }
  ]);

  const [nombreCategoria, setNombreCategoria] = useState("");
  const [descripcion, setDescripcion] = useState("");

  const crearCategoria = (e) => {

    e.preventDefault();

    const nuevaCategoria = {
      id: categorias.length + 1,
      nombreCategoria,
      descripcion
    };

    setCategorias([...categorias, nuevaCategoria]);

    setNombreCategoria("");
    setDescripcion("");
  };

  return (

    <div className="category-container">

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

      <div className="category-hero">

        {/* FORMULARIO */}

        <div className="category-form-section">

          <h1>
            Crear Categoría
          </h1>

          <p>
            Registra nuevas categorías de vehículos para la plataforma.
          </p>

          <form
            className="category-form"
            onSubmit={crearCategoria}
          >

            <div className="input-group">

              <label>
                Nombre de Categoría
              </label>

              <input
                type="text"
                placeholder="SUV"
                value={nombreCategoria}
                onChange={(e) =>
                  setNombreCategoria(e.target.value)
                }
              />

            </div>

            <div className="input-group">

              <label>
                Descripción
              </label>

              <textarea
                placeholder="Vehículos SUV de alto rendimiento"
                value={descripcion}
                onChange={(e) =>
                  setDescripcion(e.target.value)
                }
              />

            </div>

            <button type="submit">
              Crear Categoría
            </button>

          </form>

        </div>

        {/* IMAGEN */}

        <div className="category-image">

          <div className="overlay"></div>

          <div className="image-content">

            <h2>
              Gestión Inteligente
            </h2>

            <p>
              Organiza los vehículos según su categoría y mejora la experiencia de los usuarios.
            </p>

          </div>

        </div>

      </div>

      {/* LISTADO */}

      <section className="categories-section">

        <h2>
          Categorías Registradas
        </h2>

        <div className="categories-grid">

          {categorias.map((categoria) => (

            <div
              className="category-card"
              key={categoria.id}
            >

              <span className="category-id">
                ID #{categoria.id}
              </span>

              <h3>
                {categoria.nombreCategoria}
              </h3>

              <p>
                {categoria.descripcion}
              </p>

            </div>

          ))}

        </div>

      </section>

    </div>
  );
}

export default Categorias;