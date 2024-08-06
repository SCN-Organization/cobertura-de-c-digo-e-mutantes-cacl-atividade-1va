package br.ufrpe.poo.banco.dados;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.negocio.Conta;

import java.io.FileInputStream;

public class RepositorioContasArquivoBinTest{

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
    public void atualizar() {
    	Assert.assertTrue(conta.getSaldo() == 50.0);
    	
    	conta.creditar(25.5);
    	
    	try {
			repo.atualizar(conta);
			Assert.assertTrue(conta.getSaldo() == 75.5);
		} catch (RepositorioException e) {
			fail("Falha ao atualizar conta");
		}
    }
    
//    @Test
//    public void lerArquivoException() {
//    	when(repo.lerArquivo()).thenReturn(null); 
//    }
}
