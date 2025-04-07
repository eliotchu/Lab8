import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Paint extends JFrame {
    private ArrayList<Point> points = new ArrayList<>();
    private boolean isDrawing = false;

    public Paint(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isDrawing = true;
                points.clear();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDrawing = false;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(isDrawing){
                    points.add(e.getPoint());
                    repaint();
                }
            }
        });
    }

    @Override
    public void paintComponents(Graphics g){
        super.paintComponents(g);
}
}
