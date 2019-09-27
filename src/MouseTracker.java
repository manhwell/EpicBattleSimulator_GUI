// https://www.developer.com/java/data/understanding-and-using-the-java-delegation-event-model.html

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MouseTracker extends JFrame{
    private static final long serialVersionUID = 1L;
    private final JPanel trackPanel;
    private final JLabel statusLabel;

    public MouseTracker(){
        super("Mouse Tracker");
        trackPanel=new JPanel();
        trackPanel.setBackground(Color.BLUE);
        add(trackPanel, BorderLayout.CENTER);

        statusLabel=new JLabel("");
        add(statusLabel, BorderLayout.NORTH);

        trackPanel.addMouseListener(new MouseHandler());
        trackPanel.addMouseMotionListener(new MouseHandler());

    }

    private class MouseHandler implements MouseListener,
            MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            statusLabel.setText(String
                    .format("Clicked at (x=%d, y=%d)", e.getX(),
                            e.getY()));
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            statusLabel.setText("Mouse in scope");

        }

        @Override
        public void mouseExited(MouseEvent e) {
            statusLabel.setText("Mouse out of scope");
        }

    }
}