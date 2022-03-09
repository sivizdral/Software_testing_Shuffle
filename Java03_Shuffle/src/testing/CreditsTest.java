package testing;
import static org.junit.Assert.assertEquals;

import org.junit.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import shuff.Credits;

public class CreditsTest {
	private static Credits credits = new Credits(5,10,2);
	
	@Test
	public void testCompareDoubleSmallBig() {
		assertEquals(-1,credits.compare(2.0, 3.0));
	}
	
	@Test
	public void testCompareDoubleBigSmall() {
		assertEquals(1,credits.compare(3.0, 2.0));
	}
	
	@Test
	public void testCompareDoubleEqual() {
		assertEquals(0,credits.compare(2.0, 2.0));
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="ulazcredits.csv")
	public void testCompare(double a, double b, int r) {
		assertEquals(r,credits.compare(a, b));
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="ulaz2.csv")
	public void testCompareInt(int a, int b, int r) {
		assertEquals(r,credits.compare(a, b));
	}
	
	@Test
	public void testMockito() {
		Credits crmock = Mockito.mock(Credits.class);
		Mockito.when(crmock.getPlayer()).thenReturn("Igrac");
		Mockito.verify(crmock);
		assertEquals(credits.compareTo(crmock), 1);
	}
	
	@Test
	public void testCompareIntSmallBig() {
		assertEquals(-1,credits.compare(2, 3));
	}
	
	@Test
	public void testCompareIntBigSmall() {
		assertEquals(1,credits.compare(3, 2));
	}
	
	@Test
	public void testCompareIntEqual() {
		assertEquals(0,credits.compare(2, 2));
	}
	
	@Test
	public void testCompareToEqual() {
		Credits credits2 = new Credits(5,10,2);
		assertEquals(0,credits.compareTo(credits2));
	}
	
	@Test
	public void testCompareToDiffSeconds() {
		Credits credits2 = new Credits(6,10,2);
		assertEquals(-1,credits.compareTo(credits2));
	}
	
	@Test
	public void testCompareToDiffMoves() {
		Credits credits2 = new Credits(5,11,2);
		assertEquals(-1,credits.compareTo(credits2));
	}
	
	@Test
	public void testGetSeconds() {
		assertEquals(5,credits.getTimeInSeconds());
	}
	
	@Test
	public void testGetLevel() {
		assertEquals(2,credits.getLevel());
	}
	
	@Test
	public void testGetMoves() {
		assertEquals(10,credits.getMoves());
	}
	
	@Test
	public void testGetPlayer() {
		credits.setPlayer("Igrac");
		assertEquals("Igrac",credits.getPlayer());
	}
	
	@Test
	public void testGetTime() {
		assertEquals("0:5",credits.getTime());
	}
	
	@Test
	public void testZeroTime() {
		Credits credits2 = new Credits(0,10,2);
		assertEquals("",credits2.getTime());
	}
}
