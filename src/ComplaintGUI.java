import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ComplaintGUI extends JFrame implements WindowListener {
	private compFile cfile;
	private final String password = "implementer";
	private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
	private static final Color SECONDARY_COLOR = new Color(52, 73, 94);
	private static final Color ACCENT_ORANGE = new Color(230, 126, 34);
	private static final Color ACCENT_GREEN = new Color(46, 204, 113);
	private static final Color DARK_BG = new Color(44, 62, 80);
	private static final Color TEXT_COLOR = new Color(255, 255, 255);
	private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
	private static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 14);
	private static final Font BUTTON_FONT = new Font("Segoe UI", Font.PLAIN, 14);

	public ComplaintGUI() {
		String tmpPath = System.getProperty("java.io.tmpdir");
		cfile = new compFile(tmpPath + "comps.txt");

		setTitle("Complaint Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		addWindowListener(this);

		// Main panel with gradient background
		JPanel mainPanel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gradient = new GradientPaint(0, 0, DARK_BG, 0, getHeight(), SECONDARY_COLOR);
				g2d.setPaint(gradient);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		mainPanel.setLayout(new BorderLayout(20, 20));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		// Header Panel
		JPanel headerPanel = new JPanel();
		headerPanel.setOpaque(false);
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		
		JLabel titleLabel = new JLabel("Complaint Management");
		titleLabel.setFont(TITLE_FONT);
		titleLabel.setForeground(PRIMARY_COLOR);
		titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		JLabel subtitleLabel = new JLabel("Manage and track your complaints efficiently");
		subtitleLabel.setFont(SUBTITLE_FONT);
		subtitleLabel.setForeground(new Color(200, 200, 200));
		subtitleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		headerPanel.add(titleLabel);
		headerPanel.add(Box.createVerticalStrut(8));
		headerPanel.add(subtitleLabel);

		// Buttons Panel with 2x2 grid
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setOpaque(false);
		buttonsPanel.setLayout(new GridLayout(2, 2, 20, 20));

		ModernButton registerBtn = new ModernButton("Register Complaint", PRIMARY_COLOR);
		ModernButton statusBtn = new ModernButton("Check Status", PRIMARY_COLOR);
		ModernButton adminBtn = new ModernButton("Admin Panel", ACCENT_ORANGE);
		ModernButton reportBtn = new ModernButton("View Report", ACCENT_GREEN);

		registerBtn.addActionListener(e -> new compRegister(cfile));
		statusBtn.addActionListener(e -> new compStatus(cfile));
		adminBtn.addActionListener(e -> handleAdminAccess());
		reportBtn.addActionListener(e -> new compReport(cfile));

		buttonsPanel.add(registerBtn);
		buttonsPanel.add(statusBtn);
		buttonsPanel.add(adminBtn);
		buttonsPanel.add(reportBtn);

		// Footer Panel
		JPanel footerPanel = new JPanel();
		footerPanel.setOpaque(false);
		JLabel footerLabel = new JLabel("© 2025 Complaint Management System");
		footerLabel.setForeground(new Color(150, 150, 150));
		footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		footerPanel.add(footerLabel);

		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(buttonsPanel, BorderLayout.CENTER);
		mainPanel.add(footerPanel, BorderLayout.SOUTH);

		add(mainPanel);
		setVisible(true);
	}

	private void handleAdminAccess() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel label = new JLabel("Enter Admin Password:");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		JPasswordField pwd = new JPasswordField(15);
		pwd.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel.add(label);
		panel.add(pwd);
		
		int result = JOptionPane.showConfirmDialog(this, panel, "Admin Access Required", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			String entered = new String(pwd.getPassword());
			if (entered.equals(password)) {
				new compCheck(cfile);
			} else {
				JOptionPane.showMessageDialog(this, "Invalid password!", "Access Denied", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// Modern Button Class with hover effects and rounded corners
	static class ModernButton extends JButton {
		private Color bgColor;
		private Color hoverColor;

		public ModernButton(String text, Color bgColor) {
			super(text);
			this.bgColor = bgColor;
			this.hoverColor = new Color(
				Math.min(bgColor.getRed() + 30, 255),
				Math.min(bgColor.getGreen() + 30, 255),
				Math.min(bgColor.getBlue() + 30, 255)
			);
			setFont(BUTTON_FONT);
			setForeground(TEXT_COLOR);
			setFocusPainted(false);
			setBorderPainted(false);
			setContentAreaFilled(false);
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			setPreferredSize(new Dimension(200, 80));

			addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					setBackground(hoverColor);
				}
				public void mouseExited(MouseEvent e) {
					setBackground(bgColor);
				}
			});
		}

		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(getModel().isArmed() || getModel().isPressed() ? hoverColor : bgColor);
			g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
			super.paintComponent(g);
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		cfile.exit();
	}

	public void windowOpened(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}
