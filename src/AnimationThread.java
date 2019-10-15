import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// AnimationThread - subclass of Thread to run the animation
public class AnimationThread extends Thread {

	private AnimationArea animationArea;  // points to JPanel subclass object
	private boolean stopper = true;	// toggles animation on and off
	private int gameOver = 0;

	// constructor for AnimationThread
	public AnimationThread(AnimationArea animationPanel) {
		animationArea = animationPanel;
	}

	// toggleAnimation() - toggles animation on and off
	public void toggleAnimation() {
		this.stopper = !this.stopper;  // flip the stopper attribute
	}

	public boolean isAnimationRunning(){
		return this.stopper;
	}

	// run() method is invoked automatically by the Java VM
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
						/*if (animationArea.getArmy(1).checkDead() == animationArea.getArmy(1).getArmySize()){
							stopper = true;
							JOptionPane.showMessageDialog(null, "Blue team wins!");
						}
						if (animationArea.getArmy(2).checkDead() == animationArea.getArmy(2).getArmySize()){
							stopper = true;
							JOptionPane.showMessageDialog(null, "Red team wins!");
						}*/
					} // end of run() method
				});
			}
			try {
				sleep(2);  // pause between thread launches
			} catch (InterruptedException e) {
			}
		} // end of while loop

	} // end of run() method

} // end AnimationThread
