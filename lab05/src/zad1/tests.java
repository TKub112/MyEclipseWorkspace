package zad1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class tests {

	@Test
	void test() {
		Publikacja k1 = new Ksiazka("Adam Mickiewicz", "Pan Tadeusz", 340);
		System.out.println(k1);
		Publikacja k2 = new Ksiazka("Adam Mickiewicz", "Dziady", 130);
		System.out.println(k1);
		Publikacja kk1 = new KsiazkaZOkladkaZwykla(k1);
		System.out.println(kk1);
		Publikacja kk2 = new KsiazkaZOkladkaTwarda(k2);
		System.out.println(kk2);
		Publikacja fakeBook = new KsiazkaZObwoluta(k1);
		System.out.println(fakeBook);
		// wyj�tek! Nie mo�na ob�o�y� obwolut� ksi��ki, kt�ra nie posiada ok�adki
		Publikacja kkk2 = new KsiazkaZObwoluta(kk2); // a tu OK
		System.out.println(kkk2);
		// Publikacja odrzut = new KsiazkaZObwoluta(kkk2);
		// wyj�tek! Obwoluta mo�e by� jedna
		Publikacja dziadyZAutografemWieszcza =
		 new KsiazkaZAutografem(kk2, "Drogiej Hani - Adam Mickiewicz");
		System.out.println(dziadyZAutografemWieszcza);
		// wypisuje: | Adam Mickiewicz | Dziady | 130 | Ok�adka twarda | Drogiej Hani - Adam
		//Mickiewicz | 
		Publikacja dziadyZDwomaAutografami = new KsiazkaZAutografem(dziadyZAutografemWieszcza, "Haniu, to nieprawda, Dziady napisa�em ja,");
		 // wyj�tek! Autograf mo�e by� tylko jeden 
	}

}
