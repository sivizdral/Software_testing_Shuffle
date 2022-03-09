package testing;
import static org.junit.Assert.assertEquals;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import org.junit.*;
import shuff.SecondsCounter;

public class SecondsCounterTest {
	
	private static SecondsCounter counter = new SecondsCounter();
	
	@Before
	public void start() {
		counter.setLabel(new JLabel("Labela"));
		counter.start();
	}
	
	@Test
	public void testActionPerformed() {
		
		ActionEvent e = new ActionEvent(counter, 1, " ");
		counter.actionPerformed(e);
		assertEquals(counter.secondsCounterLabel.getText(), "00 : 01");
	}
	
	@Test
	public void testGetTimeElapsed() throws InterruptedException {
			Thread.sleep(10000);
			assertEquals(counter.getTimeElapsed(), "00 Minutes & 09 Seconds ");
			assertEquals(counter.getTimeElapsedInSeconds(), 9);
	}
	
	@Test
	public void testTwentySecondsElapsed() throws InterruptedException {
		counter.pause();
		counter.enable();
		Thread.sleep(20000);
		assertEquals(counter.getTimeElapsed(), "00 Minutes & 19 Seconds ");
	}
	
	@Test
	public void testMinuteElapsed() throws InterruptedException {
		counter.reStart();
		Thread.sleep(70000);
		assertEquals(counter.getTimeElapsed(), "1 Minutes & 09 Seconds ");
	}
	
	@After
	public void stop() {
		counter.stop();
		counter.finalize();
		counter = new SecondsCounter();
	}
	
}
