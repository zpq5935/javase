package book.fengkuang.unit07_basicClassLib;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class testLocaleList {
	public static void main2(String[] args) {
		Locale[] locales = Locale.getAvailableLocales();
		for (Locale l : locales) {
			System.out.println(l.getDisplayCountry() +"="+ l.getCountry() 
				+ ", "+l.getDisplayLanguage() +"="+ l.getLanguage());
		}
	}
	
	@Test
	public void test01(){
		ResourceBundle resourceBundle = ResourceBundle.getBundle("mess",
				Locale.getDefault(Locale.Category.FORMAT));
		System.out.println(resourceBundle.getString("hello"));
	}
	
	
	
}
