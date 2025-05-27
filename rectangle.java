import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

	class Rectangle extends Shape {

    public Rectangle() {
    }
    
    public Rectangle(int x1, int y1, int width, int height, Color c,boolean solid, boolean dotted) {

        this.x1 = x1;
        this.x2 = width;
        this.y1 = y1;
        this.y2 = height;
        this.color = c;
        this.solid = solid;
        this.dotted = dotted;
    }
	
	@Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        if (dotted) {
            float[] dash = {5, 5};
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash, 0));
        } else {
            g2d.setStroke(new BasicStroke(2));
        }

        // Draw the rectangle based on the solid flag
        if (solid) {
            g2d.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
        } else {
            g2d.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
        }
    }
}
		
