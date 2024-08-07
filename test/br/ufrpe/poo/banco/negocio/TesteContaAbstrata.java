package br.ufrpe.poo.banco.negocio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufrpe.poo.banco.exceptions.ValorInvalidoException;
import br.ufrpe.poo.banco.exceptions.SaldoInsuficienteException;

public class TesteContaAbstrata {

    private ContaAbstrata conta;

    @Before
    public void setUp() {
        conta = new Conta("12345", 1000);
    }

    /**
     * Testa o construtor da conta.
     */
    @Test
    public void testeConstrutor() {
        assertEquals("12345", conta.getNumero());
        assertEquals(1000, conta.getSaldo(), 0);
    }

    /**
     * Testa o método creditar saldo.
     */
    @Test
    public void testeCreditar() throws ValorInvalidoException {
        conta.creditar(500);
        assertEquals(1500, conta.getSaldo(), 0);
    }

    /**
     * Testa o método debitar saldo com sucesso.
     */
    @Test
    public void testeDebitar() {
        try {
            conta.debitar(200);
            assertEquals(800, conta.getSaldo(), 0);
        } catch (SaldoInsuficienteException e) {
            fail("Não deveria ter lançado exceção: " + e.getMessage());
        }
    }

    /**
     * Testa o método debitar saldo com valor inválido.
     */
    @Test(expected = ValorInvalidoException.class)
    public void testeDebitarValorInvalido() throws ValorInvalidoException,
             SaldoInsuficienteException {
        conta.debitar(-200);
    }

    /**
     * Testa o método debitar saldo com saldo insuficiente.
     */
    @Test(expected = SaldoInsuficienteException.class)
    public void testeDebitarSaldoInsuficiente() throws ValorInvalidoException,
             SaldoInsuficienteException {
        conta.debitar(2000);
    }

    /**
     * Testa o método getNumero.
     */
    @Test
    public void testeGetNumero() {
        assertEquals("12345", conta.getNumero());
    }

    /**
     * Testa o método getSaldo.
     */
    @Test
    public void testeGetSaldo() {
        assertEquals(1000, conta.getSaldo(), 0);
    }
}
