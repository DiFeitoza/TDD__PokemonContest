package testmockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import controller.PokedexController;
import view.PokedexView;

public class PokedexViewTest {
	PokedexController pokedexControllerMocked = Mockito.mock(PokedexController.class);
	PokedexView pokedexView = PokedexView.getInstace();
	
	@Captor
	ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);
	public AutoCloseable closeable;

	@Before
	public void open() {
		closeable = MockitoAnnotations.openMocks(this);
	}

	@After
	public void release() throws Exception {
		closeable.close();
	}

	/**
	 * A classe pokedexView é responsável por:
	 * - Exbir o menu de opções
	 * - Coletar a entrada do usuário
	 * - Chamar o uma instância de PokedexController para acessar a PokeAPI e exibir os 
	 * dados para o usuário
	 * 
	 * O mais importante aqui é validar os dados de entrada do usuário. Para simular as 
	 * entradas do usuário a mesma estrutura da classe será replicada aqui, porém será 
	 * adaptada para retornar valores de validação
	 * 
	 * Ao fim será testada a interação entre a classe PokedexView e PokedexController
	 **/

	public String validMenuOptions(String input){
		if(input.equals("") || input.equals("n") || input.equals("N")){
			return "Nova consulta";
		} else if(input.equals("v") || input.equals("V")) {
			return "Voltar";
		} else {
			return "Itera o laço";
		}
	}
	
	public boolean validChoicedId(String input) {
		int chooseId;
		if (input.matches("\\d{1,3}")) {
			chooseId = Integer.parseInt(input);
			if (chooseId >= 1 && chooseId <= 151) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	// Validando laço de entrada do menu de opções
	// Só deve permitir as opções:
	// "" (string vazia) representando o <Enter>, 'n' e  'N' para nova consulta
	// 'v' ou 'V' para voltar ao menu anterior
	// demais valores são inválidos e iteram o laço

	@Test
	@Order(1)
	@DisplayName("1. Testando o laço de validação da entrada de opções do menu - Nova Consulta")
	public void test1() {
		assertEquals(validMenuOptions(""), "Nova consulta");
		assertEquals(validMenuOptions("n"), "Nova consulta");
		assertEquals(validMenuOptions("N"), "Nova consulta");
	}
	
	@Test
	@Order(1)
	@DisplayName("2. Testando o laço de validação da entrada de opções do menu - Voltar")
	public void test2() {
		assertEquals(validMenuOptions("v"), "Voltar");
		assertEquals(validMenuOptions("V"), "Voltar");
	}
	
	@Test
	@Order(1)
	@DisplayName("3. Testando o laço de validação da entrada de opções do menu - Itera o laço")
	public void test3() {
		assertEquals(validMenuOptions("123"), "Itera o laço");
		assertEquals(validMenuOptions("abc"), "Itera o laço");
		assertEquals(validMenuOptions("!@$#%"), "Itera o laço");
	}
	
	// Validando a estutura de validação da entrada do usuário para o ID do pokemon.
	// Só devem ser pertido entrar com um número interior positivo entre 1 e 151.
	
	@Test
	@Order(2)
	@DisplayName("4. Testando o laço de validação da entrada de opções do menu - Itera o laço")
	public void test4() {
		assertTrue(validChoicedId("1")); //valor entre 1 e 151
		assertTrue(validChoicedId("10")); //valor entre 1 e 151
		assertTrue(validChoicedId("100")); //valor entre 1 e 151
		assertTrue(validChoicedId("151")); //valor entre 1 e 151
	}
	
	@Test
	@Order(2)
	@DisplayName("5. Testando o laço de validação da entrada de opções do menu - Itera o laço")
	public void test5() {
		assertFalse(validChoicedId("0")); // valor menor que 1
		assertFalse(validChoicedId("152")); // valor maior que 151
		assertFalse(validChoicedId("")); // string vazia
	}
	
	//Por fim iremos testar a interação da classe com o pokedexController
	@Test
	@Order(2)
	@DisplayName("6. Testando comunicação entre as classes PokedexView e PokedexController")
	public void test6() {
		pokedexView.setPokedexController(pokedexControllerMocked);
		pokedexView.printPokedexPokemonFromId(1);
		verify(pokedexControllerMocked, times(1)).printPokedexPokemonFromId(intCaptor.capture());
		assertEquals(intCaptor.getValue(), 1);
	}
}