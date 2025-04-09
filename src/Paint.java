import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Paint extends JPanel {

    static class DrawingSession {
        ArrayList<Point> points;
        Color color;

        public DrawingSession(ArrayList<Point> points, Color color) {
            this.points = points;
            this.color = color;
        }
    }

    private ArrayList<Point> points = new ArrayList<>();
    private ArrayList<DrawingSession> allPoints = new ArrayList<>();
    private boolean isDrawing = false;
    private Color color = Color.BLACK;

    public void colorPicker(){
        Color newColor = JColorChooser.showDialog(this, "Choose color", color);
        if(newColor != null){
            color = newColor;
        }
    }

    public Paint(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isDrawing = true;
                points = new ArrayList<>();
                points.add(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDrawing = false;
                allPoints.add(new DrawingSession(points, color));
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
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(DrawingSession drawing : allPoints) {
            g.setColor(drawing.color);
            for(int i = 1; i < drawing.points.size(); i++) {
                g.drawLine(drawing.points.get(i - 1).x, drawing.points.get(i - 1).y, drawing.points.get(i).x, drawing.points.get(i).y);
            }
        }
        g.setColor(color);
        for(int i = 1; i < points.size(); i++) {
            g.drawLine(points.get(i - 1).x, points.get(i - 1).y, points.get(i).x, points.get(i).y);
        }
    }

    public static void main(String[] args){
        JFrame jframe = new JFrame("Paint app");
        Paint paint = new Paint();
        JButton colorButton = new JButton("Color Picker");
        colorButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                paint.colorPicker();
            }
        });
        JButton clearButton = new JButton("Clear canvas");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.allPoints.clear();
                paint.points.clear();
                paint.repaint();
            }
        });
        JPanel jpanel = new JPanel();
        jpanel.add(colorButton);
        jpanel.add(clearButton);
        jframe.setLayout(new BorderLayout());
        jframe.add(jpanel, BorderLayout.SOUTH);
        jframe.add(paint, BorderLayout.CENTER);
        jframe.setSize(600, 600);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setVisible(true);
    }
}
