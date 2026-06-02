import { Routes, Route } from "react-router-dom";

import LoginCliente from "./pages/login_cliente.jsx";
import LoginAdmin from "./pages/login_admin.jsx";
import LoginEmpleado from "./pages/login_empleado.jsx";
import RegistrarCliente from "./pages/registrar_cliente.jsx";
import Home from "./pages/pag_principal_us.jsx";
import Home1 from "./pages/pag_principal_admin.jsx";
import Home2 from "./pages/pag_principal_emple.jsx";
import Reservas from "./pages/mis_reservas.jsx";
import Misalquileres from "./pages/mis_alquileres.jsx";
import HistorialPagos from "./pages/pago_cliente.jsx";


function App() {

  return (

    <Routes>

      <Route
        path="/"
        element={<LoginCliente />}
      />

      <Route
        path="/historial_pagos"
        element={<HistorialPagos />}
      />

      <Route  
        path="/login_empleado"
        element={<LoginEmpleado />}
      />

      <Route
        path="/mis_reservas"
        element={<Reservas />}
      />

      <Route
        path="/mis_alquileres"
        element={<Misalquileres />}
      />

      <Route
        path="/login_admin"
        element={<LoginAdmin />}
      />

      <Route
        path="/registro_cliente"
        element={<RegistrarCliente />}
      />

      <Route
        path="/principal_cliente"
        element={<Home />}
      />

      <Route
        path="/principal_admin"
        element={<Home1 />}
      />

      <Route
        path="/principal_empleado"
        element={<Home2 />}
      />

    </Routes>


  );
}

export default App;