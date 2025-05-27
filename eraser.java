import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

class Eraser extends Shape {

    public Eraser(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = Color.WHITE; 
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);  

        g2d.setStroke(new BasicStroke(10));  
   
        g2d.fillRect(x1, y1, 15,15);  
    }
}
