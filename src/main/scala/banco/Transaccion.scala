package banco

// Clase Transaccion
class Transaccion(origen: Cuenta, destino: Cuenta, monto: Double, fecha: String, tipo: String) {
  // tipo puede ser: transferencia (Cuenta a Cuenta), deposito (Nada a Cuenta), retiro (Cuenta a Nada)

  override def toString: String = {
    s"Transaccion  | CuentaOrigen: $origen CuentaDestino: $destino Monto: $monto Fecha: $fecha Tipo: $tipo"
  }
}