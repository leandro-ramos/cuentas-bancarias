package edu.tallerweb.cuentas;

/**
 * Similar a la CuentaSueldo, pero se pide que luego de la
 * quinta extracción de dinero se cobre un costo adicional
 * por extracción de $ 6
 */
public class CajaAhorros extends AbstractCuenta {
	private Integer cantidadExtraer = 0;
	/**
	 * No hay reglas adicionales para el depósito
	 * @param monto a depositar
	 */
	public void depositar(final Double monto) {
		this.saldo = this.saldo + monto;
	}

	/**
	 * Se cobran $6 adicionales por cada extracción luego de
	 * la quinta.
	 * @param monto a extraer
	 */
	public void extraer(final Double monto) {
		if(this.saldo > monto)
			this.saldo = this.saldo - monto;
		else
			throw new CuentaBancariaException("No hay suficiente saldo para la extraccion");
		cantidadExtraer = cantidadExtraer + 1;
		if(cantidadExtraer > 5) {
			this.saldo = this.saldo - 6;
		}
	}
	/**
	 * Permite saber el saldo de la cuenta
	 * @return el saldo de la cuenta
	 */
	public Double getSaldo() {
		return this.saldo;
	}

}
