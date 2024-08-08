package br.ufrpe.poo.banco.negocio;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import br.ufrpe.poo.banco.exceptions.ClienteJaPossuiContaException;
import br.ufrpe.poo.banco.exceptions.ClienteNaoPossuiContaException;

/**
 * Classe de teste respons�vel por testar as condi��es dos m�todos
 * adicionarConta e removerConta da classe Cliente.
 * 
 * @author Aluno
 * 
 */
public class TesteCliente {

	/**
	 * Testa a inser��o de uma nova conta vinculada ao cliente
	 */
	@Test
	public void adicionarContaTest() {
		Cliente c1 = new Cliente("nome", "123");
		try {
			c1.adicionarConta("1");
		} catch (ClienteJaPossuiContaException e) {
			fail();
		}
		assertEquals(c1.procurarConta("1"), 0);
	}

	/**
	 * Testa a condi��o da tentativa de adicionar uma conta j� existente � lista
	 * de contas do cliente
	 * 
	 * @throws ClienteJaPossuiContaException
	 */
	@Test(expected = ClienteJaPossuiContaException.class)
	public void adicionarContaJaExistenteTest()
			throws ClienteJaPossuiContaException {
		Cliente c1 = new Cliente("nome", "123");
		c1.adicionarConta("1"); // adiciona a conta a 1� vez
		c1.adicionarConta("1"); // tentativa de adicionar a mesma conta
								// novamente
	}

	/**
	 * Teste a remo��o de uma conta da lista de contas do cliente
	 */
	@Test
	public void removerContaClienteTest() {
		Cliente c1 = new Cliente("nome", "123");
		try {
			c1.adicionarConta("1"); // adiciona conta com n�mero 1
			c1.removerConta("1"); // remove a conta de n�mero 1
		} catch (Exception e) {
			fail("Exce��o inesperada lancada!");
		}

		assertEquals(c1.procurarConta("1"), -1);
	}

	/**
	 * Testa a remo��o de uma determinada conta que n�o est� vinculada ao
	 * cliente
	 * 
	 * @throws ClienteNaoPossuiContaException
	 */
	@Test(expected = ClienteNaoPossuiContaException.class)
	public void removerContaClienteSemContaTest()
			throws ClienteNaoPossuiContaException {
		Cliente c1 = new Cliente("nome", "123");
		c1.removerConta("1"); // tenta remover a conta de um cliente sem contas
	}
	
	@Test
	public void getAndSetNomeTest()
	{
		Cliente cliente = new Cliente("João","987");
		assertEquals(cliente.getNome(), "João");
		
		cliente.setNome("Joana");
		assertEquals(cliente.getNome(), "Joana");
	}
	
	@Test
	public void getAndSetCPFTest()
	{
		Cliente cliente = new Cliente("João","12345678");
		assertEquals(cliente.getCpf(), "12345678");
		
		cliente.setCpf("87654321");
		assertEquals(cliente.getCpf(), "87654321");
	}
	
	@Test
	public void getContasTest() {
		Cliente cliente = new Cliente("João","12345678");
		try {
			cliente.adicionarConta("10");
			cliente.adicionarConta("20");
			cliente.adicionarConta("30");
			
			ArrayList<String> arrayTeste = new ArrayList<String>(
		            Arrays.asList("10",
	                          "20",
	                          "30"));			
			Assert.assertEquals(arrayTeste, cliente.getContas());
			
		} catch (ClienteJaPossuiContaException e) {
			fail();
		}		
	}
	
	@Test
	public void removerTodasContasTest() {
		Cliente cliente = new Cliente("João","12345678");
		try {
			cliente.adicionarConta("10");
			cliente.adicionarConta("20");
			cliente.adicionarConta("30");
			
			cliente.removerTodasAsContas();		
			
			Assert.assertEquals(null, cliente.getContas());
			
		} catch (ClienteJaPossuiContaException e) {
			fail();
		}		
	}
	
	@Test
	public void consultarNumeroContaTest() {
		Cliente cliente = new Cliente("João","12345678");
		
		try {
			cliente.adicionarConta("10");
			cliente.adicionarConta("20");
		} catch (ClienteJaPossuiContaException e) {
			fail();
		}
		
		assertEquals("20", cliente.consultarNumeroConta(1));
		
	}
	
	@Test
	public void toStringTest() {
		Cliente cliente = new Cliente("João","12345678");
		
		try {
			cliente.adicionarConta("10");
			cliente.adicionarConta("20");
		} catch (ClienteJaPossuiContaException e) {
			fail();
		}
		
		String str = "Nome: João\nCPF: 12345678\nContas: [10, 20]";
		
		assertEquals(str, cliente.toString());
	}
	
	@Test
	public void clientesIguais() {
		Cliente cliente1 = new Cliente("João","12345678");
		Cliente cliente2 = new Cliente("Joana","12345678");
		
		Assert.assertTrue(cliente1.equals(cliente2));
	}
	
	@Test
	public void clientesDiferentes() {
		Cliente cliente1 = new Cliente("João","12345678");
		Cliente cliente2 = new Cliente("Joana","12345679");
		
		Assert.assertFalse(cliente1.equals(cliente2));
	}
	
	@Test
	public void naoClienteEqualsTest() {
		Cliente cliente1 = new Cliente("João","12345678");
		
		Assert.assertFalse(cliente1.equals("João"));
	}
}
