package br.ufrpe.poo.banco.dados;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.iterator.IteratorContaAbstrata;
import br.ufrpe.poo.banco.negocio.Conta;


public class RepositorioContasArquivoBinTest {

	private Conta conta;
	private RepositorioContasArquivoBin repo;
	
	@Before
	public void inicializarDados() {
		conta = new Conta("123", 50.0);
		try {
			repo = new RepositorioContasArquivoBin();
			
			repo.inserir(conta);
		} catch (RepositorioException e) {
			fail();
		}				
	}
	
	
	@Test
	public void lerArquivo() {
		try {
			repo.lerArquivo();
			Assert.assertTrue(repo.existe("123"));
		} catch (RepositorioException e) {
			fail("Falha ao ler o arquivo.");
		}
	}
	
//	@Test
//	public void lerArquivoInexistente() {
//		try {
//			RepositorioContasArquivoBin newRepo = new RepositorioContasArquivoBin();
//			newRepo.arquivoContas = new File("file");
//			newRepo.lerArquivo();
//			Assert.assertTrue(newRepo.existe("123"));
//		} catch (RepositorioException e) {
//			fail("Falha ao ler o arquivo.");
//		}
//	}

    @Test
    public void remover() {
    	try {
			repo.remover("123");
			Assert.assertTrue(!repo.existe("123"));
		} catch (RepositorioException e) {
			fail("Falha em remover conta");
		}
    }
    
    @Test
    public void removerContaNaoExistente() {
    	try {
			repo.remover("123");
			Assert.assertTrue(!repo.existe("123"));
			
			Assert.assertFalse(repo.remover("123"));
		} catch (RepositorioException e) {
			fail();
		}
    }

    @Test
    public void atualizar() {
    	Assert.assertTrue(conta.getSaldo() == 50.0);
    	
    	conta.creditar(25.5);
    	
    	try {
			repo.atualizar(conta);
			Assert.assertTrue(conta.getSaldo() == 75.5);
		} catch (RepositorioException e) {
			fail();
		}
    }
    
    @Test
    public void atualizarContaNaoExistente() {
    	
    	Conta contaFalsa = new Conta("9999999", 10.0);
    	
    	try {
    		Assert.assertFalse(repo.atualizar(contaFalsa));
		} catch (RepositorioException e) {
			fail();
		}
    }
    
    @Test
    public void getIterator() {
    	
    	try {
			RepositorioContasArquivoBin newRepo = new RepositorioContasArquivoBin();
			
			newRepo.inserir(conta);
			newRepo.inserir(new Conta("876", 23.0));
			
			IteratorContaAbstrata iterator = newRepo.getIterator();
			
			Assert.assertNotNull(iterator);
			
			Assert.assertTrue(iterator.hasNext());
			
		} catch (RepositorioException e) {
			fail();
		}
    	
    
    }
    
}
