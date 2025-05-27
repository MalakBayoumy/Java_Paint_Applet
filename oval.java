import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

	class Oval extends Shape {

	
	public Oval() {
		
	}

    public Oval(int x1, int y1, int width, int height, Color c, boolean solid, boolean dotted) {

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
		
		if (solid) {
			g.setColor(color);
			g.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
		} 
		
		else if (dotted) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(color);

			float[] dash = {5f, 5f};
			g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash, 0));

			g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
		} 
		// Default outline
		else {
			g.setColor(color);
			g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
		}
	}

}