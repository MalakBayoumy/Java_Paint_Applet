import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

class Line extends Shape {

    public Line() {
        
    }

    public Line(int x1, int y1, int x2, int y2, Color c, boolean dotted) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = c;
        this.dotted = dotted; 
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;  
        g2d.setColor(color);  

        // Handle dotted line using dashed stroke
        if (dotted) {
            float[] dash = {5f, 5f};  
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash, 0));
        } else {
            g2d.setStroke(new BasicStroke(2));  // Solid line if not dotted
        }

        g2d.drawLine(x1, y1, x2, y2);  // Draw the line (either solid or dotted)
    }
}
