import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * AnimationThread() provides a thread to run the simulation on.
 * @author C2C Manuel Riolo
 */
public class AnimationThread extends Thread {

	private AnimationArea animationArea;  // points to JPanel subclass object
	private boolean stopper = true;	// toggles animation on and off
	private int gameOver = 0;

	/**
	 * Creates a new AnimationThread.
	 * @param animationPanel is the area where the simulation will run.
	 */
	public AnimationThread(AnimationArea animationPanel) {
		animationArea = animationPanel;
	}

	/**
	 * Toggles the animation of the simulation.
	 */
	public void toggleAnimation() {
		this.stopper = !this.stopper;  // flip the stopper attribute
	}

	/**
	 * Sets the animations state.
	 * @param newState is the new state of the animation stopper.
	 */
	public void setStopper(boolean newState){
		this.stopper = newState;
	}

	/**
	 * Returns a boolean value of whether the simulation is currently running or not.
	 * @return the current state of the animation.
	 */
	public boolean isAnimationRunning(){
		return this.stopper;
	}

	/**
	 * Runs the animation.
	 */
	public void run() {
		while (true) {
			if (!stopper) {  // for toggling on/off the animation
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							gameOver = animationArea.animate(animationArea.getGraphics());  // runs the animation
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
							e.printStackTrace();
						}
						if(gameOver == 1){
							stopper = true;
						}
					} // end of run() method
				});
			}
			try {
				sleep(2);  // pause between thread launches
			} catch (InterruptedException e) {
			}
		} // end of while loop

	} // end of run() method

	/**
	 * Gets the animationArea the simulation is running on
	 * @return the animationArea the simulation is running on.
	 */
	public AnimationArea getAnimationArea(){
		return this.animationArea;
	}
}
