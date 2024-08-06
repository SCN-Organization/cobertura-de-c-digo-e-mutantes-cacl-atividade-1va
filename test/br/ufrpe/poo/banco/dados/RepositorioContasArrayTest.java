package br.ufrpe.poo.banco.dados;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.negocio.Conta;

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
		
		while(repo.getIterator().hasNext()) {
			count++;
		}
	}
	@Test
	public void inserir100() {
		if(count <= 99) {
			Integer i = 0;
			while(count < 100) {				
				try {
					repo.inserir(new Conta(i.toString(), 10.0));
				} catch (RepositorioException e) {
					fail();
				}
				
				i++;
			}
		}
		
		
	}

}
