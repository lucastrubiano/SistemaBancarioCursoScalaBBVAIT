import banco._

// Funcion realizarTransferencia()
// * Debe recibir una Cuenta de Origen, una Cuenta de Destino y el monto que desea transferir.
// * Debe validar el monto (no puedo transferir una cantidad negativa ni cero)
// * Debe probar hacer la transferencia. La cuenta origen debe tener fondos suficientes.
// * Si sale bien la transferencia, guardar la transaccion
def realizarTransferencia(cuentaOrigen: Cuenta, cuentaDestino: Cuenta, monto: Double): Unit = {
  if (monto > 0) {
    try {
      cuentaOrigen.retirar(monto)
      cuentaDestino.depositar(monto)
      println(s"Transferencia de $monto realizada con exito")
      Banco.guardarTransaccion(cuentaOrigen, cuentaDestino, monto, "Transferencia")
    } catch {
      case e: Exception => println(e.getMessage)
    }
  } else {
    println("El monto debe ser mayor a 0")
  }
}


// Funcion realizarDeposito()
// * Debe recibir una Cuenta y el monto que desea depositar.
// * Debe validar el monto (no puedo depositar una cantidad negativa ni cero)
// * Debe probar hacer el deposito
// * Si sale bien el deposito, guardar la transaccion
def realizarDeposito(cuenta: Cuenta, monto: Double): Unit = {
  if (monto > 0) {
    try {
      cuenta.depositar(monto)
      println(s"Deposito de $monto realizado con exito")
      Banco.guardarTransaccion(null, cuenta, monto, "Deposito")
    } catch {
      case e: Exception => println(e.getMessage)
    }
  } else {
    println("El monto debe ser mayor a 0")
  }
}

// Funcion realizarRetiro()
// * Debe recibir una Cuenta y el monto que desea retirar.
// * Debe validar el monto (no puedo retirar una cantidad negativa ni cero)
// * Debe probar hacer el retiro
// * Si sale bien el retiro, guardar la transaccion
def realizarRetiro(cuenta: Cuenta, monto: Double): Unit = {
  if (monto > 0) {
    try {
      cuenta.retirar(monto)
      println(s"Retiro de $monto realizado con exito")
      Banco.guardarTransaccion(cuenta, null, monto, "Retiro")
    } catch {
      case e: Exception => println(e.getMessage)
    }
  } else {
    println("El monto debe ser mayor a 0")
  }
}

// Funcion generarIntereses()
// * Debe recibir una Cuenta y una tasa de interes
// * Debe calcular los intereses de la cuenta
// * Debe probar hacer el deposito de los intereses
// * Si sale bien el deposito, guardar la transaccion
def generarIntereses(cuenta: Cuenta, tasa: Double): Unit = {
  if (cuenta.getTipo == "CuentaInversion") {
    try {
      val intereses = cuenta.asInstanceOf[CuentaInversion].calcularIntereses(tasa)
      cuenta.depositar(intereses)
      println("Intereses generados con exito")
      Banco.guardarTransaccion(null, cuenta, intereses, "Intereses")
    } catch {
      case e: Exception => println(e.getMessage)
    }
  } else {
    println("La cuenta no es de inversion")
  }
}

@main
def main(): Unit = {

  val cl1 = Banco.agregarCliente(new Cliente("Lucas", "123"))
  cl1.agregarCuenta(new CuentaAhorro(100, 5))
  cl1.agregarCuenta(new CuentaCorriente(200, 80))

  println(cl1)

  val cl2 = Banco.agregarCliente(new Cliente("Martina", "321"))
  cl2.agregarCuenta(new CuentaAhorro(200, 5))
  cl2.agregarCuenta(new CuentaCorriente(400, 100))
  cl2.agregarCuenta(new CuentaInversion(500, 1))

  println(cl2)

  println("INICIO DE OPERACIONES")

  // Cliente 1 hace un retiro de su CC de 250
  realizarRetiro(cl1.getCuenta("CC"), 250)
  println(cl1)

  // Cliente 1 hace un deposito de su CA de 50
  realizarDeposito(cl1.getCuenta("CA"), 50)
  println(cl1)

  // Cliente 1 trata de hacer un retiro de su CC de 1000 (Debe fallar)
  realizarRetiro(cl1.getCuenta("CC"), 1000)
  println(cl1)

  // Cliente 1 hace una transferencia de su CA a su CC de 50
  realizarTransferencia(cl1.getCuenta("CA"), cl1.getCuenta("CC"), 50)
  println(cl1)

  // Cliente 1 hace una transferencia de su CC a su CA de 500 (Debe fallar)
  realizarTransferencia(cl1.getCuenta("CC"), cl1.getCuenta("CA"), 500)
  println(cl1)

  println(cl2)
  // Cliente 1 hace una transferencia al Cliente 2 de CC a CA de 50
  realizarTransferencia(cl1.getCuenta("CC"), cl2.getCuenta("CA"), 50)
  println(cl1)
  println(cl2)

  // Cliente 2 hace un retiro de su CI de 1000 (Debe fallar)
  realizarRetiro(cl2.getCuenta("CI"), 1000)
  println(cl2)

  // Cliente 2 hace un retiro de su CC de 100
  realizarRetiro(cl2.getCuenta("CC"), 100)
  println(cl2)

  // Cliente 2 hace una generacion de intereses de su CA de 5%
  generarIntereses(cl2.getCuenta("CI"), 5)
  println(cl2)

  // Exportar las transacciones
  Banco.exportarTransacciones()
}