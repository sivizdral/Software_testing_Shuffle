package testing;

import static org.junit.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import org.junit.*;
import org.mockito.Mockito;

import shuff.AnimatedJButton;
import shuff.ShuffleGame;

public class ShuffleGameTest {
	private static ShuffleGame game = new ShuffleGame(3);
	
	@Before
	public void before() {
		game.main(null);
	}
	
	
	@Test
	public void testRun() {
		game.run(3);
		assertEquals(game.level, 3);
	}
	
	@Test
	public void testGetComponent() {
		JButton button = (JButton) game.getComponent(1, 3);
		assertEquals(button, null);
		JButton button2 = (JButton) game.getComponent(1, 1);
		assertEquals(button2.getText().length(), 1);
		
		button = (JButton) game.getComponent(3, 7);
		assertEquals(button, null);
		button = (JButton) game.getComponent(3, 1);
		assertEquals(button.getText().length(), 1);
		
		button = (JButton) game.getComponent(0, 0);
		assertEquals(button, null);
		button = (JButton) game.getComponent(0, 3);
		assertEquals(button.getText().length(), 1);
		
		button = (JButton) game.getComponent(2, 2);
		assertEquals(button, null);
		button = (JButton) game.getComponent(2, 1);
		assertEquals(button.getText().length(), 1);
		
		button = (JButton) game.getComponent(5, 1);
		assertEquals(button, null);
	}
	
	@Test
	public void testActionPerformed() {
		AnimatedJButton button = new AnimatedJButton("Natpis", 2);
		ActionEvent e4 = new ActionEvent(button, 1, "ReStart");
		game.actionPerformed(e4);
		ActionEvent e = new ActionEvent(button, 1, "PAUSE");
		game.actionPerformed(e);
		assertEquals(button.getActionCommand(), "START");
		ActionEvent e2 = new ActionEvent(button, 1, "START");
		game.actionPerformed(e2);
		assertEquals(button.getActionCommand(), "PAUSE");
		ActionEvent e3 = new ActionEvent(button, 1, "level0");
		game.actionPerformed(e3);
		assertEquals(game.level, 3);
		ActionEvent e5 = new ActionEvent(button, 1, "Instructions");
		game.actionPerformed(e5);
		assertEquals(game.helpDialog.getTitle(), "Instructions");
//		JPanel panel = (JPanel) game.helpDialog.getContentPane().getComponents()[1];
//		JButton but = (JButton) panel.getComponents()[0];
//		but.doClick();
//		try {
//			Robot robot = new Robot();
//			robot.mouseMove(80, 60);
//			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//			
//		} catch (AWTException e1) {
//			e1.printStackTrace();
//		}
		ActionEvent e6 = new ActionEvent(button, 1, "About Shuffle");
		game.actionPerformed(e6);
		assertEquals(game.helpDialog.getTitle(), "Shuffle Game - Version 1.0");
		game.helpDialog.dispose();
		ActionEvent e7 = new ActionEvent(button, 1, "High Scores");
		game.actionPerformed(e7);
		game.helpDialog.dispose();
	}
	
	@Test
	public void mockito() {
		JDialog dialog = Mockito.mock(JDialog.class);
		Mockito.when(dialog.getBackground()).thenReturn(Color.WHITE);
		JPanel panel = game.getCloseButtonPanel(dialog);
		Mockito.verify(dialog);
		JPanel p2 = new JPanel();
		JButton okButton = new JButton( "OK" ) ;
        okButton.setMnemonic('O') ;
        p2.add( okButton ) ;
        BevelBorder bb = new BevelBorder( BevelBorder.RAISED );
        panel.setBorder(bb);
        p2.setBorder( bb) ;
		assertEquals(panel.equals(p2), true);
	}
	
	@Test
	public void mockito2() {
		ActionEvent event = Mockito.mock(ActionEvent.class);
		Mockito.when(event.getActionCommand()).thenReturn("PAUSE");
		JButton bttn = new AnimatedJButton("Dugme", 2);
		Mockito.when(event.getSource()).thenReturn(bttn);
		game.actionPerformed(event);
		assertEquals(bttn.getActionCommand(), "START");
		ActionEvent e2 = new ActionEvent(bttn, 1, "START");
		game.actionPerformed(e2);
	}
	
	@Test
	public void testWinningPosition() {
		JButton btn = new JButton();
		ActionEvent e4 = new ActionEvent(btn, 1, "ReStart");
		game.actionPerformed(e4);
		JButton buttons[] = new JButton[9];
		JLabel label = null;
		for (int i = 0; i < 9; i++) {
			if (game.buttonPanel.getComponent(i) instanceof JLabel) {
				label = (JLabel) game.buttonPanel.getComponent(i);
			} else {
				JButton button = (JButton) game.buttonPanel.getComponent(i);
				buttons[Integer.parseInt(button.getText()) - 1] = button;
			}
		}
		for (int i = 0; i < 8; i++) {
			game.buttonPanel.add(buttons[i], i);
			game.matrix[i] = i + 1;
		}
		game.buttonPanel.add(label, 8);
		game.matrix[8] = 0;
		game.blankPosition = 8;
		ActionEvent e = new ActionEvent(buttons[7], 1, "number");
		
		game.actionPerformed(e);
		assertEquals(game.analyseResult(), false);
		game.actionPerformed(e);
		assertEquals(game.gameStatus, false);
	}	
	
	@Test
	public void testHighScore() {
		game.showScoreDialog(20, 30);
		game.showScoreDialog(30, 30);
		game.showScoreDialog(40, 30);
		game.showScoreDialog(50, 30);
		game.showScoreDialog(60, 30);
		game.showScoreDialog(70, 30);
		game.showScoreDialog(80, 30);
		game.showScoreDialog(90, 30);
		game.showScoreDialog(100, 30);
		game.showScoreDialog(110, 30);
		game.showScoreDialog(20, 10);
		game.showHighScoreDialog();
	}
	
	@Test
	public void getWrongPosition() {
		assertEquals(game.getPosition(20),0);
	}
	
	@After
	public void close() {
		game.actionPerformed(new ActionEvent(new JButton(), 1, "CLOSE"));
		game = new ShuffleGame(3);
	}
	
}
