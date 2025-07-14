import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UserInterfaceApp {
    private JFrame frame;
    private JTextArea textArea;
    private JPanel panel;

    public UserInterfaceApp() {
        frame = new JFrame("User Interface I");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem dateTimeItem = new JMenuItem("Show Date & Time");
        JMenuItem saveItem = new JMenuItem("Save to log.txt");
        JMenuItem colorItem = new JMenuItem("Change Green Hue");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add actions
        dateTimeItem.addActionListener(e -> showDateTime());
        saveItem.addActionListener(e -> saveToFile());
        colorItem.addActionListener(e -> changeGreenHue());
        exitItem.addActionListener(e -> System.exit(0));

        // Add menu items to menu
        menu.add(dateTimeItem);
        menu.add(saveItem);
        menu.add(colorItem);
        menu.add(exitItem);
        menuBar.add(menu);

        // Add text area
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Set menu bar and layout
        frame.setJMenuBar(menuBar);
        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }

    private void showDateTime() {
        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        textArea.setText("Current Date & Time: " + formatted);
    }

    private void saveToFile() {
        try (FileWriter writer = new FileWriter("log.txt")) {
            writer.write(textArea.getText());
            JOptionPane.showMessageDialog(frame, "Saved to log.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving to file");
        }
    }

    private void changeGreenHue() {
        Random rand = new Random();
        int green = 100 + rand.nextInt(156); // Between 100-255
        int red = rand.nextInt(100);         // Between 0-99
        int blue = rand.nextInt(100);        // Between 0-99
        Color greenHue = new Color(red, green, blue);
        panel.setBackground(greenHue);

        // Also show color value in the text area for feedback
        textArea.setText("Background color changed to RGB(" + red + ", " + green + ", " + blue + ")");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserInterfaceApp::new);
    }
}
