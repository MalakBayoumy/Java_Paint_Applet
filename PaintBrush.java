import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PaintBrush extends Applet implements MouseListener, MouseMotionListener, ActionListener {
    private ArrayList<Shape> shapes = new ArrayList<>();
    private String currentShapeType = "pencil";
    private Color currentColor = Color.BLACK;
    private boolean solid = false;
	private boolean dotted = false;
    private int startX, startY, endX, endY;

    private Button btnRed, btnGreen, btnBlue, btnClear, btnUndo, btnrect, btnoval, btnline, btnpencil, btneraser;
    private Checkbox chksolid;
	private Checkbox chkdotted;

    private Shape currentShape;


    public void init() {
        btnRed = new Button(" ");
        btnGreen = new Button(" ");
        btnBlue = new Button(" ");

        btnClear = new Button("Clear All");
        btnUndo = new Button("Undo");
        btnrect = new Button("Rectangle");
        btnoval = new Button("Oval");
        btnline = new Button("Line");
        btneraser = new Button("Eraser");
        btnpencil = new Button("Pencil");

        btnRed.setBackground(Color.RED);
        btnGreen.setBackground(Color.GREEN);
        btnBlue.setBackground(Color.BLUE);

	 // Label for Color Group
		add(new Label("Colors"));
		add(btnRed);
		add(btnGreen);
		add(btnBlue);
		
		add(new Label("  ")); // Add space between groups

		// Label for Shapes Group
		add(new Label("Shapes"));
		add(btnline);
		add(btnrect);
		add(btnoval);

		add(new Label("  ")); // Add space between groups

		// Label for Drawing Tools (Pencil, Eraser)
		add(new Label("Drawing Tools"));
		add(btnpencil);
		add(btneraser);

		add(new Label("  ")); // Add space between groups

		// Undo and Clear Buttons
		add(new Label("Actions"));
		add(btnUndo);
		add(btnClear);

		add(new Label("  ")); // Space between groups

		// solid and dotted options
		chksolid = new Checkbox("solid");
		chkdotted = new Checkbox("dotted");
		add(chksolid);
		add(chkdotted);

        btnRed.addActionListener(this);
        btnGreen.addActionListener(this);
        btnBlue.addActionListener(this);
        btnline.addActionListener(this);
        btnrect.addActionListener(this);
        btnoval.addActionListener(this);
        btnpencil.addActionListener(this);
        btnClear.addActionListener(this);
        btnUndo.addActionListener(this);
        btneraser.addActionListener(this);

        chksolid.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                solid = chksolid.getState();
            }
        });

        chkdotted.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                dotted = chkdotted.getState();
            }
        });

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        for (Shape shape : shapes) {
            shape.draw(g);
        }
        if (currentShape != null)
            currentShape.draw(g);
    }

    @Override
    public void update(Graphics g) {
        // create offscreen image
        Image buffer = createImage(getWidth(), getHeight());
        // paint on the offscreen
        paint(buffer.getGraphics());
        // draw offscreen on the on screen
        g.drawImage(buffer, 0, 0, null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRed) currentColor = Color.RED;
        else if (e.getSource() == btnGreen) currentColor = Color.GREEN;
        else if (e.getSource() == btnBlue) currentColor = Color.BLUE;
        else if (e.getSource() == btnline) {
            currentShapeType = "Line";
            currentShape = new Line();
        }
        else if (e.getSource() == btnrect){
            currentShapeType = "Rectangle";
            currentShape = new Rectangle();
        }
            
        else if (e.getSource() == btnoval) {
            currentShapeType = "Oval";
            currentShape = new Oval();
        }
        else if (e.getSource() == btnpencil) currentShapeType = "Pencil";
        else if (e.getSource() == btneraser) {
            currentShapeType = "Eraser";
            currentColor = Color.BLUE;
        }
        else if (e.getSource() == btnClear) {
            shapes.clear();
            repaint();
        } else if (e.getSource() == btnUndo) {
            if (!shapes.isEmpty()) shapes.remove(shapes.size() - 1);
            repaint();
        }
    }

public void mousePressed(MouseEvent e) {
    // Start coordinates when the user clicks the mouse
    startX = e.getX();
    startY = e.getY();
}

public void mouseReleased(MouseEvent e) {
    // End coordinates when the user releases the mouse
    endX = e.getX();
    endY = e.getY();
    Shape shape = null;

    // Create the shape based on the current selection
    switch (currentShapeType) {
        case "Rectangle":
		shape = new Rectangle( startX, startY, Math.abs(endX), Math.abs(endY), currentColor, solid, dotted);
            break;
        case "Oval":
            shape = new Oval(startX, startY, Math.abs(endX), Math.abs(endY), currentColor, solid, dotted);
            break;
        case "Line":
            shape = new Line(startX, startY, endX, endY, currentColor, dotted);
            break;
        case "Pencil":
            shape = new Pencil(startX, startY, endX, endY, currentColor, dotted);
            break;
        case "Eraser":
            shape = new Eraser(startX, startY, endX, endY);  
            break;
    }

    // Store the created shape in the shapes array
    if (shape != null) {
        shapes.add(shape);
    }
    switch (currentShapeType) {
        case "Rectangle":
            currentShape = new Rectangle();
            break;
        case "Oval":
            currentShape = new Oval();
            break;
        case "Line":
            currentShape = new Line();
            break;
        default:
            break;
    }
    // Final repaint to draw the shape after mouse release
    repaint();
}

   public void mouseDragged(MouseEvent e) {
    // Make sure we're drawing a Pencil or Eraser (or other shapes if desired)
    if (currentShapeType.equals("Pencil") || currentShapeType.equals("Eraser")
    || currentShapeType.equals("Rectangle") || currentShapeType.equals("Oval") || currentShapeType.equals("Line")
    ) {
        endX = e.getX();
        endY = e.getY();
        Shape shape = null;

        // Handle Pencil - drawing continuous lines
        if (currentShapeType.equals("Pencil")) {
            shape = new Pencil(startX, startY, endX, endY, currentColor, dotted);
        } 
        // Handle Eraser - erase parts of the canvas
        else if (currentShapeType.equals("Eraser")) {
            shape = new Eraser(startX, startY, endX, endY);
        }

        else if (currentShapeType.equals("Rectangle")) {
            currentShape.setProperties(startX, startY, endX, endY, currentColor,solid, dotted);
        } else if (currentShapeType.equals("Oval")) {
            currentShape.setProperties(startX, startY, endX, endY, currentColor,solid, dotted);
        } else if (currentShapeType.equals("Line")) {
            currentShape.setProperties(startX, startY, endX, endY, currentColor,solid, dotted);
        }

        // If shape is created, add it to the list and update the starting point
        if (shape != null) {
            shapes.add(shape);
            startX = endX;
            startY = endY;
        }
        repaint();
    }
}

    public void mouseMoved(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
