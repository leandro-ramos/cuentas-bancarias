package edu.tallerweb.cuentas;

/**
 * La más compleja de las cuentas, ésta permite establecer una
 * cantidad de dinero a girar en descubierto. Es por ello que
 * cada vez que se desee extraer dinero, no sólo se considera
 * el que se posee, sino el límite adicional que el banco
 * estará brindando.
 *
 * Por supuesto esto no es gratis, ya que el banco nos cobrará
 * un 5% como comisión sobre todo el monto en descubierto
 * consumido en la operación.
 *
 * Por ejemplo, si tuviéramos $ 100 en la cuenta, y quisiéramos
 * retirar $ 200 (con un descubierto de $ 150), podremos hacerlo.
 * Pasaremos a deberle al banco $ 105 en total: los $ 100 que
 * nos cubrió, más el 5% adicional sobre el descubierto otorgado.
 */
public class CuentaCorriente extends AbstractCuenta {
	private Double descubiertoTotal;
	private Double descubierto;
	private Double deuda;
	private Double adicional = 0.05;
	/**
	 * Toda cuenta corriente se inicia con un límite total
	 * para el descubierto.
	 * @param descubiertoTotal
	 */
	public CuentaCorriente(final Double descubiertoTotal) {
		this.descubiertoTotal = descubiertoTotal;
		this.descubierto = this.descubiertoTotal;
	}

	/**
	 * Todo depósito deberá cubrir primero el descubierto,
	 * si lo hubiera, y luego contar para el saldo de la
	 * cuenta.
	 * @param monto a depositar
	 */
	public void depositar(final Double monto) {
		if (monto < 0.0) {
			throw new CuentaBancariaException("El monto no puede ser negativo"); }
		if (this.descubierto == this.descubiertoTotal) {
			this.saldo += monto; }
		else {
			if (monto > this.deuda) {
				this.descubierto += this.deuda;
				this.saldo += monto - this.deuda;
				this.deuda = 0.0; }
			else {
				this.descubierto += monto; }
		}

	}

	/**
	 * Se cobrará el 5% de comisión sobre el monto girado
	 * en descubierto.
	 * Por supuesto, no puede extraerse más que el total
	 * de la cuenta, más el descubierto (comisión incluída)
	 * @param monto a extraer
	 */
	public void extraer(final Double monto) {
		if (monto < 0.0) {
			throw new CuentaBancariaException("El monto no puede ser negativo"); }
		if (this.saldo + this.descubierto <= monto) {
			throw new CuentaBancariaException("No hay suficiente saldo para la extraccion"); }
		if (this.saldo > monto) {
			this.saldo -= monto; }
		else {
			this.deuda = monto - this.saldo;
			this.saldo = 0.0;
			this.deuda += this.deuda * this.adicional;
			if (this.deuda > this.descubiertoTotal) {
				throw new CuentaBancariaException("No hay suficiente saldo para la extraccion"); }
			else {
				this.descubierto -= this.deuda;	}
		}
	}

	/**
	 * Permite saber el saldo de la cuenta
	 * @return el saldo de la cuenta
	 */
	public Double getSaldo() {
		return this.saldo;
	}

	/**
	 * Permite saber el saldo en descubierto
	 * @return el descubierto de la cuenta
	 */
	public Double getDescubierto() {
		return this.descubierto;
	}

}
