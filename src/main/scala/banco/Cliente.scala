package banco
import banco.Cuenta

// Clase Cliente
class Cliente(nombre: String, direccion: String) {
  private val id = Cliente.siguienteID()
  private var cuentas: List[Cuenta] = List()

  def agregarCuenta(cuenta: Cuenta): Cuenta = {
    cuentas = cuenta :: cuentas
    cuenta // nuevo
  }

  def consultarSaldoTotal(): Double = {
    cuentas.map(_.getSaldo).sum
  }

  // Nuevo método
  def getCuenta(tipo: String): Cuenta = {
    val tipoCuenta = tipo match {
      case "CA" => "CuentaAhorro"
      case "CC" => "CuentaCorriente"
      case "CI" => "CuentaInversion"
    }
    cuentas.filter(_.getTipo == tipoCuenta).head
  }

  override def toString: String = {
    val imprimirCuentas = cuentas.map(c => c.toString).mkString("\n")
    s"Cliente ============================\nID: $id Nombre: $nombre SaldoTotal: ${consultarSaldoTotal()}\n\nCuentas: \n${imprimirCuentas}\n===================================="
  }
}

object Cliente{
  var idClientes = 0
  
  def siguienteID(): Int = {
    idClientes += 1
    idClientes
  }
}