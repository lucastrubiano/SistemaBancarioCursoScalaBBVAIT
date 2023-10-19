package banco
import java.time.LocalDateTime

// Clase Banco
object Banco {
  private var clientes: List[Cliente] = List()
  private var transacciones: List[Transaccion] = List()

  def agregarCliente(cliente: Cliente): Cliente = {
    println("Cliente agregado!")
    clientes = cliente :: clientes
    cliente
  }

  def guardarTransaccion(origen: Cuenta, destino: Cuenta, monto: Double, tipo: String): Unit = {
    if (monto > 0) {
      val fecha = LocalDateTime.now.toString
      val transaccion = new Transaccion(origen, destino, monto, fecha, tipo)
      transacciones = transaccion :: transacciones
    }
  }

  def exportarTransacciones(): List[Transaccion] = {
    println("Exportando transacciones...")
    transacciones.foreach(println)
    transacciones
  }
}