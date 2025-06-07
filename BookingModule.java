package AirlineReservationSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class BookingModule extends JFrame {
    private JTextField fromField, toField, departDate, returnDate;
    private JTextArea bookingSummary;
    private JSpinner travelers;
    private JComboBox<String> classType;
    private JRadioButton oneWay, roundTrip, multiCity, regularFare, studentFare;

    private static final Color PRIMARY_BLUE = new Color(41, 128, 185);
    private static final Color LIGHT_BLUE = new Color(174, 214, 241);
    private static final Color DARK_BLUE = new Color(23, 32, 42);
    private static final Color ACCENT_COLOR = new Color(39, 174, 96);
    private static final Color LIGHT_GRAY = new Color(248, 249, 250);
    private static final Color BORDER_COLOR = new Color(220, 221, 225);
    private static final Color TEXT_COLOR = new Color(52, 58, 64);

    public BookingModule() {
        setTitle("BestFriend Travel - Flight Booking");
        setSize(1200, 800);
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
        mainContainer.setLayout(new BorderLayout());
        add(mainContainer);

        JPanel headerPanel = createHeaderPanel();
        mainContainer.add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        JLabel mainTitle = new JLabel("WHERE TO FLY?");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 48));
        mainTitle.setForeground(DARK_BLUE);
        mainTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(mainTitle);

        JLabel subtitle = new JLabel("Find Countless Flight Options & Deals To Various Destination Around The World");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitle.setForeground(TEXT_COLOR);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(subtitle);
        
        contentPanel.add(Box.createVerticalStrut(40));

        JPanel bookingCard = createBookingCard();
        contentPanel.add(bookingCard);

        contentPanel.add(Box.createVerticalStrut(30));

        bookingSummary = new JTextArea(8, 0);
        bookingSummary.setEditable(false);
        bookingSummary.setFont(new Font("Arial", Font.PLAIN, 14));
        bookingSummary.setBackground(Color.WHITE);
        bookingSummary.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1), 
                "Booking Summary", 0, 0, new Font("Arial", Font.BOLD, 16), TEXT_COLOR),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JScrollPane scrollPane = new JScrollPane(bookingSummary);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        contentPanel.add(scrollPane);

        mainContainer.add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(20, 60, 0, 60));

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setOpaque(false);
        JLabel logo = new JLabel("✈ BESTFRIEND");
        logo.setFont(new Font("Arial", Font.BOLD, 24));
        logo.setForeground(DARK_BLUE);
        logoPanel.add(logo);
        
        JLabel tagline = new JLabel("TOUR & TRAVEL AGENCY");
        tagline.setFont(new Font("Arial", Font.PLAIN, 12));
        tagline.setForeground(TEXT_COLOR);
        logoPanel.add(Box.createHorizontalStrut(10));
        logoPanel.add(tagline);

       

        header.add(logoPanel, BorderLayout.WEST);
        

        return header;
    }

    private JPanel createBookingCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 3, 3, new Color(0, 0, 0, 20)),
                BorderFactory.createLineBorder(BORDER_COLOR, 1)
            ),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        JPanel cardHeader = new JPanel(new BorderLayout());
        cardHeader.setOpaque(false);
        
        JLabel flightsLabel = new JLabel("✈ Flights");
        flightsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        flightsLabel.setForeground(TEXT_COLOR);
        
        JLabel supportLabel = new JLabel("🎧 Customer Support");
        supportLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        supportLabel.setForeground(TEXT_COLOR);
        supportLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        cardHeader.add(flightsLabel, BorderLayout.WEST);
        cardHeader.add(supportLabel, BorderLayout.EAST);
        
        card.add(cardHeader);
        card.add(Box.createVerticalStrut(25));

        JPanel flightTypePanel = createFlightTypePanel();
        card.add(flightTypePanel);
        card.add(Box.createVerticalStrut(25));

        JPanel inputPanel = createInputPanel();
        card.add(inputPanel);
        card.add(Box.createVerticalStrut(20));

        JPanel bottomPanel = createBottomPanel();
        card.add(bottomPanel);
        card.add(Box.createVerticalStrut(20));

        
        JPanel footerPanel = createFooterPanel();
        card.add(footerPanel);

        return card;
    }

    private JPanel createFlightTypePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setOpaque(false);

        oneWay = createStyledRadioButton("One Way", true);
        roundTrip = createStyledRadioButton("Round Trip", false);
        multiCity = createStyledRadioButton("Multi-City", false);

        ButtonGroup flightGroup = new ButtonGroup();
        flightGroup.add(oneWay);
        flightGroup.add(roundTrip);
        flightGroup.add(multiCity);

        panel.add(oneWay);
        panel.add(Box.createHorizontalStrut(5));
        panel.add(roundTrip);
        panel.add(Box.createHorizontalStrut(5));
        panel.add(multiCity);
        panel.add(Box.createHorizontalStrut(30));

        classType = new JComboBox<>(new String[]{"Coach", "Business", "First Class"});
        classType.setFont(new Font("Arial", Font.PLAIN, 14));
        classType.setBackground(Color.WHITE);
        classType.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        classType.setPreferredSize(new Dimension(120, 35));
        panel.add(classType);

        return panel;
    }

    private JRadioButton createStyledRadioButton(String text, boolean selected) {
        JRadioButton btn = new JRadioButton(text, selected);
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        btn.setForeground(TEXT_COLOR);
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if (selected) {
            btn.setBackground(DARK_BLUE);
            btn.setForeground(Color.WHITE);
        }
        
        btn.addActionListener(e -> {
            for (Component comp : btn.getParent().getComponents()) {
                if (comp instanceof JRadioButton) {
                    JRadioButton rb = (JRadioButton) comp;
                    if (rb.isSelected()) {
                        rb.setBackground(DARK_BLUE);
                        rb.setForeground(Color.WHITE);
                    } else {
                        rb.setBackground(Color.WHITE);
                        rb.setForeground(TEXT_COLOR);
                    }
                }
            }
        });
        
        return btn;
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 15, 0));
        panel.setOpaque(false);

        fromField = createStyledTextField("Origin", "From");
        toField = createStyledTextField("Destination", "To");
        departDate = createStyledTextField("Depart Date", "📅 Date");
        returnDate = createStyledTextField("Return Date", "📅 Return");

        panel.add(createFieldPanel(fromField, "From"));
        panel.add(createFieldPanel(toField, "To"));
        panel.add(createFieldPanel(departDate, "Depart"));
        panel.add(createFieldPanel(returnDate, "Return"));

        return panel;
    }

    private JPanel createFieldPanel(JTextField field, String label) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        fieldLabel.setForeground(Color.GRAY);
        fieldLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 3, 0));
        
        panel.add(fieldLabel, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        
        return panel;
    }

    private JTextField createStyledTextField(String placeholder, String label) {
        JTextField field = new JTextField(placeholder);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setForeground(Color.GRAY);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setPreferredSize(new Dimension(200, 45));

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

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JPanel farePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        farePanel.setOpaque(false);

        regularFare = createStyledRadioButton("Regular Fare", true);
        studentFare = createStyledRadioButton("Student Fare", false);

        ButtonGroup fareGroup = new ButtonGroup();
        fareGroup.add(regularFare);
        fareGroup.add(studentFare);

        farePanel.add(regularFare);
        farePanel.add(Box.createHorizontalStrut(10));
        farePanel.add(studentFare);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setOpaque(false);

        JLabel travelerLabel = new JLabel("Traveler");
        travelerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        travelerLabel.setForeground(Color.GRAY);

        travelers = new JSpinner(new SpinnerNumberModel(1, 1, 9, 1));
        travelers.setFont(new Font("Arial", Font.PLAIN, 14));
        travelers.setPreferredSize(new Dimension(60, 35));

        JButton searchButton = new JButton("🔍");
        searchButton.setFont(new Font("Arial", Font.BOLD, 18));
        searchButton.setBackground(DARK_BLUE);
        searchButton.setForeground(Color.WHITE);
        searchButton.setPreferredSize(new Dimension(60, 45));
        searchButton.setBorder(null);
        searchButton.setFocusPainted(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.addActionListener(this::processBooking);

        rightPanel.add(travelerLabel);
        rightPanel.add(travelers);
        rightPanel.add(searchButton);

        panel.add(farePanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.EAST);

        return panel;
    }

    private JPanel createFooterPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setOpaque(false);

        JLabel myBooking = new JLabel("📄 My Booking");
        JLabel flightStatus = new JLabel("⚪ Flight Status");

        myBooking.setFont(new Font("Arial", Font.BOLD, 14));
        myBooking.setForeground(TEXT_COLOR);
        myBooking.setCursor(new Cursor(Cursor.HAND_CURSOR));

        flightStatus.setFont(new Font("Arial", Font.PLAIN, 14));
        flightStatus.setForeground(Color.GRAY);
        flightStatus.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.add(myBooking);
        panel.add(Box.createHorizontalStrut(40));
        panel.add(flightStatus);

        return panel;
    }

    private void processBooking(ActionEvent e) {
        String from = fromField.getText().trim();
        String to = toField.getText().trim();
        String depart = departDate.getText().trim();
        String ret = returnDate.getText().trim();
        int numTravelers = (Integer) travelers.getValue();
        String fare = regularFare.isSelected() ? "Regular" : "Student";
        String flightType = oneWay.isSelected() ? "One Way" : roundTrip.isSelected() ? "Round Trip" : "Multi-City";
        String flightClass = (String) classType.getSelectedItem();

        if (from.equals("Origin") || to.equals("Destination") || depart.equals("Depart Date") ||
            from.isEmpty() || to.isEmpty() || depart.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please fill in Origin, Destination, and Depart Date.", 
                "Missing Fields", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder summary = new StringBuilder();
        summary.append("✅ BOOKING CONFIRMED!\n");
        summary.append("═══════════════════════════════════════\n\n");
        summary.append("FLIGHT DETAILS:\n");
        summary.append("From: ").append(from).append("\n");
        summary.append("To: ").append(to).append("\n");
        summary.append("Departure: ").append(depart).append("\n");
        
        if (roundTrip.isSelected() && !ret.equals("Return Date") && !ret.isEmpty()) {
            summary.append("Return: ").append(ret).append("\n");
        }
        
        summary.append("\nBOOKING INFORMATION:\n");
        summary.append("Travelers: ").append(numTravelers).append("\n");
        summary.append("Flight Type: ").append(flightType).append("\n");
        summary.append("Class: ").append(flightClass).append("\n");
        summary.append("Fare Type: ").append(fare).append("\n\n");
        summary.append("Thank you for choosing BestFriend Travel!");

        bookingSummary.setText(summary.toString());
    }
}