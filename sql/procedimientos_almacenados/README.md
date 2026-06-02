Procedimientos Almacenados

Se realizaron tres procedimientos

1. Calcular Total de Alquiler (calcular_total_alquiler)

Calcula el valor total a pagar por un alquiler.
Considera
Días de alquiler.
Precio diario del vehículo.
Multas asociadas.

2. Registrar Alquiler (registrar_alquiler)

Automatiza el proceso de creación de un alquiler.
Verifica disponibilidad del vehículo.
Valida que el usuario sea cliente.
Crea el alquiler.
Actualiza el estado del vehículo a "Alquilado".

3. Finalizar Alquiler (finalizar_alquiler)
Gestiona el cierre de un alquiler.
Verifica que el alquiler esté activo.
Comprueba que no existan pagos pendientes.
Cambia el estado del alquiler a "Finalizado".
Libera el vehículo para futuros alquileres.
