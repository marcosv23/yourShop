package domain.entity;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCode {
	private static String code;
	
	public static String generate(Integer year,String ordersCount){
		var numberPart = nextNumberPart(ordersCount);
		var formattedOrderNumber = Strings.padStart(numberPart,8,'0');
		code = year.toString().concat(formattedOrderNumber);
		return code;
	}

	private static String nextNumberPart(String count){
		var numberPart = Integer.parseInt(count);
		return  String.valueOf(numberPart +1);
	}
}
