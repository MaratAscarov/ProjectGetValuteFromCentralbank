package GetValute;


public class Main {
	public static void main(String[] args) {
				
		OutValutes ov = new OutValutes();
		System.out.println("Курс на " + ov.getCurrentDateForCourse());
		
		//2 - фунт стерлингов 
		//9 - доллар США
		//10 - евро
		String valute = ov.getValuteList().get(9).toString();
		String nominal = ov.getNominalValute(valute);
		String name = ov.getNameValute(valute);
		String value = ov.getValueValute(valute);
		
		System.out.println(nominal + " " + name + " = " + value + " рублей");
	
		valute = ov.getValuteList().get(2).toString();
	    nominal = ov.getNominalValute(valute);
		name = ov.getNameValute(valute);
		value = ov.getValueValute(valute);
		
		System.out.println(nominal + " " + name + " = " + value + " рублей");

		valute = ov.getValuteList().get(10).toString();
	    nominal = ov.getNominalValute(valute);
		name = ov.getNameValute(valute);
		value = ov.getValueValute(valute);
		
		System.out.println(nominal + " " + name + " = " + value + " рублей");
	
	//	for(int i = 0; i < ov.getValuteList().size(); i++)
	//		System.out.println("i = " + i + " " + ov.getValuteList().get(i));
		}

}


