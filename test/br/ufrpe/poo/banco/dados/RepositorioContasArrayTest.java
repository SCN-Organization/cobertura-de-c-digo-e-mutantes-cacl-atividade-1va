package br.ufrpe.poo.banco.dados;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.negocio.Conta;
import br.ufrpe.poo.banco.negocio.ContaAbstrata;

public class RepositorioContasArrayTest {

	Conta conta;
	RepositorioContasArray repo;
	int count = 0;
	
	@Before
	public void inicilizarConta() {
		conta = new Conta("999", 100.0);
		try {
			repo = new RepositorioContasArray();
			
		} catch (RepositorioException e) {
			fail();
		}
	}
	@Test
	public void inserirTest() {
		try {
			Assert.assertTrue(repo.inserir(conta));
		} catch (RepositorioException e) {
			fail();
		}		
	}
	
	@Test
	public void inserirContaExistente() {
		try {
			repo.inserir(conta);
			Assert.assertFalse(repo.inserir(conta));
		} catch (RepositorioException e) {
			fail();
		}		
	}
	
	@Test
	public void procurarTest() {
		try {
			repo.inserir(conta);
			
			Assert.assertEquals(conta, repo.procurar("999"));
		} catch (RepositorioException e) {
			fail();
		}
	}
	
	@Test
	public void procurarContaInexistenteTest() {
		try {
			repo.inserir(conta);
			
			Assert.assertNull(repo.procurar("1"));
			
		} catch (RepositorioException e) {
			fail();
		}
	}
	
	@Test
	public void removerTest() {
		
		try {
			repo.inserir(conta);
			
			Assert.assertTrue(repo.remover("999"));
		} catch (RepositorioException e) {
			fail();
		}		
	}
	
	@Test
	public void removerContaInexistenteTest() {
		
		try {
			repo.inserir(conta);
			
			Assert.assertFalse(repo.remover("1"));
		} catch (RepositorioException e) {
			fail();
		}
		
	}
	
	 @Test
	    public void atualizar() {
	    	Assert.assertTrue(conta.getSaldo() == 100.0);
	    	
	    	conta.creditar(25.5);
	    	
	    	try {
				repo.atualizar(conta);
				Assert.assertTrue(conta.getSaldo() == 125.5);
			} catch (RepositorioException e) {
				fail();
			}
	    }
	 
	 
	 @Test
	    public void atualizarContaInexistente() {	    	
	    	try {
				Assert.assertFalse(repo.atualizar(conta));
	
			} catch (RepositorioException e) {
				fail();
			}
	    }
	 
	 @Test
	 	public void inserirForaIndice() {

		 try {
			RepositorioContasArray newRepo = new RepositorioContasArray();
			
			Field contasField  = RepositorioContasArray.class.getDeclaredField("contas");
			contasField.setAccessible(true);
			ContaAbstrata[] contas = (ContaAbstrata[]) contasField.get(newRepo);
			
			for(int i = 0; i < contas.length; i++) {
				if(contas[i] == null) {
					contas[i] = new Conta(""+ i, 10.0);
					newRepo.inserir(contas[i]);
				}
			}
			newRepo.inserir(new Conta("100", 10.0));
			
			
			ContaAbstrata[] contasNew = (ContaAbstrata[]) contasField.get(newRepo);

			Assert.assertTrue(contasNew.length == 200);
			
		} catch (NoSuchFieldException | SecurityException | RepositorioException | IllegalArgumentException | IllegalAccessException e) {
			fail();
		}
	 }

}
