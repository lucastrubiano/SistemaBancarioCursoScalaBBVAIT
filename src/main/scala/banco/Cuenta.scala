package banco

class Cuenta(saldoInicial: Double) {

  protected val tipo = "Cuenta"

  protected var saldo = saldoInicial
  val numeroCuenta: String = java.util.UUID.randomUUID.toString

  def getSaldo: Double = saldo

  // Nuevo método
  def getTipo: String = tipo

  def depositar(monto: Double): Unit = {
    saldo += monto
  }

  def retirar(monto: Double): Unit = {
    if (monto <= saldo) {
      saldo -= monto
    } else {
      throw new Exception("Fondos insuficientes.")
    }
  }

  override def toString: String = {
    s"$tipo | ID: $numeroCuenta Saldo: $saldo"
  }

}

class CuentaAhorro(saldoInicial: Double, tasaInteres: Double) extends Cuenta(saldoInicial){
  override protected val tipo = "CuentaAhorro"
}


class CuentaCorriente(saldoInicial: Double, sobreGiro: Double) extends Cuenta(saldoInicial) {

  override protected val tipo = "CuentaCorriente"

  override def retirar(monto: Double): Unit = {
    if (monto <= saldo+sobreGiro) {
      saldo -= monto
    } else {
      throw new Exception("Fondos insuficientes.")
    }
  }
}


class CuentaInversion(saldoInicial: Double, plazoAnios: Int) extends Cuenta(saldoInicial) {

  override protected val tipo = "CuentaInversion"

  def calcularIntereses(tasaInteresAnual: Double): Double = {
    saldo * tasaInteresAnual/100 * plazoAnios
  }
}