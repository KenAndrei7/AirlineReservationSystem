package AirlineReservationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginModule extends JFrame {
    private static final Color PRIMARY_BLUE = new Color(41, 128, 185);
    private static final Color LIGHT_BLUE = new Color(174, 214, 241);
    private static final Color DARK_BLUE = new Color(23, 32, 42);
    private static final Color ACCENT_COLOR = new Color(39, 174, 96);
    private static final Color LIGHT_GRAY = new Color(248, 249, 250);
    private static final Color BORDER_COLOR = new Color(220, 221, 225);
    private static final Color TEXT_COLOR = new Color(52, 58, 64);

    public LoginModule() {
        setTitle("BestFriend Travel - Login");
        setSize(500, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gradient = new GradientPaint(0, 0, LIGHT_BLUE, 0, getHeight(), Color.WHITE);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainContainer.setLayout(new GridBagLayout());
        add(mainContainer);

        JPanel loginCard = createLoginCard();
        mainContainer.add(loginCard);

        setVisible(true);
    }

    private JPanel createLoginCard() {
        RoundedPanel card = new RoundedPanel(22);
        card.setPreferredSize(new Dimension(380, 480));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        card.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));

        JPanel headerPanel = createHeaderPanel();
        card.add(headerPanel);
        card.add(Box.createVerticalStrut(28));

        JPanel iconPanel = createIconPanel();
        card.add(iconPanel);
        card.add(Box.createVerticalStrut(24));

        JLabel welcomeLabel = new JLabel("Welcome Back!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        welcomeLabel.setForeground(DARK_BLUE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(welcomeLabel);

        JLabel subtitleLabel = new JLabel("Please sign in to your account");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitleLabel.setForeground(TEXT_COLOR);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(subtitleLabel);
        card.add(Box.createVerticalStrut(18));

        JPanel formPanel = createFormPanel();
        card.add(formPanel);

        return card;
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel logoLabel = new JLabel("✈ BESTFRIEND");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        logoLabel.setForeground(DARK_BLUE);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel taglineLabel = new JLabel("TOUR & TRAVEL AGENCY");
        taglineLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        taglineLabel.setForeground(TEXT_COLOR);
        taglineLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(logoLabel);
        header.add(Box.createVerticalStrut(4));
        header.add(taglineLabel);

        return header;
    }

    private JPanel createIconPanel() {
        JPanel iconContainer = new JPanel();
        iconContainer.setOpaque(false);
        iconContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JPanel iconCircle = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(0, 0, PRIMARY_BLUE, getWidth(), getHeight(), DARK_BLUE);
                g2.setPaint(gradient);
                g2.fillOval(0, 0, getWidth(), getHeight());
            }
            @Override
            public boolean isOpaque() { return false; }
        };
        iconCircle.setPreferredSize(new Dimension(75, 75));
        iconCircle.setLayout(new GridBagLayout());
        JLabel userIcon = new JLabel("👤");
        userIcon.setFont(new Font("Arial", Font.BOLD, 32));
        userIcon.setForeground(Color.WHITE);
        iconCircle.add(userIcon);
        iconContainer.add(iconCircle);
        return iconContainer;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setForeground(TEXT_COLOR);

        JTextField usernameField = createStyledTextField("Enter your username");

        JPanel usernameFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        usernameFieldPanel.setOpaque(false);
        usernameFieldPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(TEXT_COLOR);

        JPasswordField passwordField = createStyledPasswordField("Enter your password");

        JPanel passwordFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        passwordFieldPanel.setOpaque(false);
        passwordFieldPanel.add(passwordField);

        JButton loginButton = createStyledButton("LOGIN");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(loginButton);

        JLabel forgotPasswordLabel = new JLabel("<html><u>Forgot Password?</u></html>");
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        forgotPasswordLabel.setForeground(PRIMARY_BLUE);
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel forgotPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        forgotPanel.setOpaque(false);
        forgotPanel.add(forgotPasswordLabel);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || username.equals("Enter your username")) {
                showErrorMessage("Please enter your username");
                return;
            }
            if (password.isEmpty() || password.equals("Enter your password")) {
                showErrorMessage("Please enter your password");
                return;
            }
            if (username.equals("admin") && password.equals("admin")) {
                showSuccessMessage("Login successful!");
                dispose();
                SwingUtilities.invokeLater(() -> new BookingModule());
            } else {
                showErrorMessage("Invalid username or password!");
            }
        });

        formPanel.add(usernameLabel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(usernameFieldPanel);
        formPanel.add(Box.createVerticalStrut(18));
        formPanel.add(passwordLabel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(passwordFieldPanel);
        formPanel.add(Box.createVerticalStrut(22));
        formPanel.add(buttonPanel);
        formPanel.add(Box.createVerticalStrut(12));
        formPanel.add(forgotPanel);

        formPanel.add(Box.createVerticalGlue());

        return formPanel;
    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(300, 43));
        field.setPreferredSize(new Dimension(300, 43));
        field.setFont(new Font("Arial", Font.PLAIN, 15));
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(TEXT_COLOR);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });

        return field;
    }

    private JPasswordField createStyledPasswordField(String placeholder) {
        JPasswordField field = new JPasswordField();
        field.setMaximumSize(new Dimension(300, 43));
        field.setPreferredSize(new Dimension(300, 43));
        field.setFont(new Font("Arial", Font.PLAIN, 15));
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
        field.setEchoChar((char) 0); 
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String pwd = String.valueOf(field.getPassword());
                if (pwd.equals(placeholder)) {
                    field.setText("");
                    field.setForeground(TEXT_COLOR);
                    field.setEchoChar('●');
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                String pwd = String.valueOf(field.getPassword());
                if (pwd.isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                    field.setEchoChar((char) 0);                 }
            }
        });

        return field;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(300, 43));
        button.setPreferredSize(new Dimension(300, 43));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(DARK_BLUE);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(PRIMARY_BLUE);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(DARK_BLUE);
            }
        });

        return button;
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    static class RoundedPanel extends JPanel {
        private final int radius;
        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        }
    }
}