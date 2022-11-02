package yourShop.domain.entity.unit;

import domain.entity.OrderCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderCodeTest {
	
	@Test
	@DisplayName("Deve gerar um código válido para o pedido")
	void shouldGetAValidOrderCode(){
		var orderCode = OrderCode.generate(2022,"1");
		assertEquals("202200000002", orderCode);
	}
}
