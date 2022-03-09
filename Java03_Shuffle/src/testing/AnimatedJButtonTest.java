package testing;
import static org.junit.Assert.assertEquals;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import org.junit.*;
import org.mockito.Mockito;

import shuff.AnimatedJButton;

public class AnimatedJButtonTest {
	private static AnimatedJButton button = new AnimatedJButton("Dugme", 2);
	
	@Test
	public void testSetTekst() {
		button.setLabelName("Natpis");
		assertEquals("Natpis", button.text);
	}
	
	@Test
	public void testGetString() {
		assertEquals("P  r  o  b  a", button.getString("Proba", 2));
	}
	
	@Test
	public void testMouse() {
		MouseEvent me = new MouseEvent(button, 0, 0, 0, 100, 100, 1, false);
		button.mouseClicked(me);
		button.mousePressed(me);
		button.mouseReleased(me);
		button.mouseEntered(me);
		assertEquals(Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ), button.getCursor());
		button.mouseExited(me);
		assertEquals(Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ), button.getCursor());
	}
	
	@Test
	public void testActionPerformed() {
		ActionEvent e = new ActionEvent(button, 1, " ");
		button.actionPerformed(e);
		assertEquals(button.getText(), button.getString( "Dugme" , 0 ));
		button.actionPerformed(e);
		assertEquals(button.getText(), button.getString( "Dugme" , 1 ));
		button.actionPerformed(e);
		assertEquals(button.getText(), button.getString( "Dugme" , 2 ));
		button.actionPerformed(e);
		assertEquals(button.getText(), button.getString( "Dugme" , 3 ));
		button.actionPerformed(e);
		assertEquals(button.getText(), button.getString( "Dugme" , 2 ));
		button.actionPerformed(e);
		assertEquals(button.getText(), button.getString( "Dugme" , 1 ));
	}
	
}
