import java.awt.*;

public class Main {
    public static void main(String[] args) {
        PaintBrush app = new PaintBrush();
        Frame frame = new Frame();
        app.init();
        frame.add(app);
        frame.setSize(900, 900);
        frame.setVisible(true);
    }
}
