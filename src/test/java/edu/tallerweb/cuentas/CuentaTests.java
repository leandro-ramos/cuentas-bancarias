package edu.tallerweb.cuentas;

import org.junit.Assert;
import org.junit.Test;

public class CuentaTests {

	@Test
	public void queVerifiqueLaConsigna() {
		CuentaSueldo cuenta = new CuentaSueldo();
		cuenta.depositar(4000.0);

		Assert.assertEquals(
				"al depositar $ 4000.0 en una cuenta vacía, tiene $ 4000.0",
				4000.0, cuenta.getSaldo(), 0.0);

		cuenta.extraer(500.0);
	
		Assert.assertEquals(
				"al extraer $ 500.0 de una cuenta con $ 4000.0 se obtienen $ 3500.0",
				3500.0, cuenta.getSaldo(), 0.0);
		
		
		CajaAhorros cuentaAhorros = new CajaAhorros();
		cuentaAhorros.depositar(4000.0);

		Assert.assertEquals(
				"al depositar $ 4000.0 en una cuenta vacía, tiene $ 4000.0",
				4000.0, cuentaAhorros.getSaldo(), 0.0);

		cuentaAhorros.extraer(500.0);
	
		Assert.assertEquals(
				"al extraer $ 500.0 de una cuenta con $ 4000.0 se obtienen $ 3500.0",
				3500.0, cuentaAhorros.getSaldo(), 0.0);
		
		
		CuentaCorriente cuentaCorriente = new CuentaCorriente(100.0);
		cuentaCorriente.depositar(4000.0);

		Assert.assertEquals(
				"al depositar $ 4000.0 en una cuenta vacía, tiene $ 4000.0",
				4000.0, cuentaCorriente.getSaldo(), 0.0);

		cuentaCorriente.extraer(500.0);
	
		Assert.assertEquals(
				"al extraer $ 500.0 de una cuenta con $ 4000.0 se obtienen $ 3500.0",
				3500.0, cuentaCorriente.getSaldo(), 0.0);
	}

	@Test(expected=CuentaBancariaException.class)
	public void queVerifiqueLaConsignaException() {
		CuentaSueldo cuenta = new CuentaSueldo();
		cuenta.depositar(3500.0);

		cuenta.extraer(4000.0);
		
		CajaAhorros cuentaAhorros = new CajaAhorros();
		cuentaAhorros.depositar(3500.0);

		cuentaAhorros.extraer(4000.0);
		
		CuentaCorriente cuentaCorriente = new CuentaCorriente(100.0);
		cuentaCorriente.depositar(3500.0);

		cuentaCorriente.extraer(4000.0);
	}
	
	@Test(expected=CuentaBancariaException.class)
	public void queNoSePuedaDepositarMontoNegativo() {
		CuentaSueldo cuenta = new CuentaSueldo();
		cuenta.depositar(-3500.0);

		CajaAhorros cuentaAhorros = new CajaAhorros();
		cuentaAhorros.depositar(-3500.0);

		CuentaCorriente cuentaCorriente = new CuentaCorriente(100.0);
		cuentaCorriente.depositar(-3500.0);
	}
	
	@Test(expected=CuentaBancariaException.class)
	public void queNoSePuedaExtraerMontoNegativo() {
		CuentaSueldo cuenta = new CuentaSueldo();
		cuenta.extraer(-3500.0);

		CajaAhorros cuentaAhorros = new CajaAhorros();
		cuentaAhorros.extraer(-3500.0);

		CuentaCorriente cuentaCorriente = new CuentaCorriente(100.0);
		cuentaCorriente.extraer(-3500.0);
	}

	@Test
	public void queEnCajaDeAhorrosDespuesDeLaQuintaExtraccionSeCobraCostoAdicional() {
		CajaAhorros cuenta = new CajaAhorros();
		cuenta.depositar(10000.0);

		Assert.assertEquals(
				"al depositar $ 10000.0 en una cuenta vacía, tiene $ 10000.0",
				10000.0, cuenta.getSaldo(), 0.0);

		cuenta.extraer(1000.0);
	
		Assert.assertEquals(
				"al extraer $ 1000.0 de una cuenta con $ 10000.0 se obtienen $ 9000.0",
				9000.0, cuenta.getSaldo(), 0.0);
		
		cuenta.extraer(1000.0);
		
		Assert.assertEquals(
				"al extraer $ 1000.0 de una cuenta con $ 9000.0 se obtienen $ 8000.0",
				8000.0, cuenta.getSaldo(), 0.0);
		
		cuenta.extraer(1000.0);
		
		Assert.assertEquals(
				"al extraer $ 1000.0 de una cuenta con $ 8000.0 se obtienen $ 7000.0",
				7000.0, cuenta.getSaldo(), 0.0);
		
		cuenta.extraer(1000.0);
		
		Assert.assertEquals(
				"al extraer $ 1000.0 de una cuenta con $ 7000.0 se obtienen $ 6000.0",
				6000.0, cuenta.getSaldo(), 0.0);
		
		cuenta.extraer(1000.0);
		
		Assert.assertEquals(
				"al extraer $ 1000.0 de una cuenta con $ 6000.0 se obtienen $ 5000.0",
				5000.0, cuenta.getSaldo(), 0.0);
		
		cuenta.extraer(1000.0);
		
		Assert.assertEquals(
				"al extraer $ 1000.0 de una cuenta con $ 5000.0 se obtienen $ 4000.0",
				3994.0, cuenta.getSaldo(), 0.0);
	}
	
	@Test
	public void queEnCuentaCorrienteSeCobreUnPorcentajeAdicionalSobreElDescubiertoUtilizado() {
		CuentaCorriente cuenta = new CuentaCorriente(150.0);
		cuenta.depositar(100.0);

		Assert.assertEquals(
				"al depositar $ 100.0 en una cuenta vacía, tiene $ 100.0",
				100.0, cuenta.getSaldo(), 0.0);

		cuenta.extraer(200.0);
	
		Assert.assertEquals(
				"al extraer $ 200.0 de una cuenta con $ 100.0 se obtienen $ 0.0",
				0.0, cuenta.getSaldo(), 0.0);
		
		Assert.assertEquals(
				"al extraer $ 200.0 de una cuenta con $ 0.0 se obtienen $ 45.0",
				45.0, cuenta.getDescubierto(), 0.0);
		
		cuenta.depositar(200.0);
		
		Assert.assertEquals(
				"al depositar $ 200.0 en una cuenta vacía, tiene $ 95.0",
				95.0, cuenta.getSaldo(), 0.0);
		
		Assert.assertEquals(
				"al depositar $ 200.0 en una cuenta vacía, tiene $ 150.0",
				150.0, cuenta.getDescubierto(), 0.0);
	}

}
