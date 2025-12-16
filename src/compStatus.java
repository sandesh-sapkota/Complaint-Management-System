import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class compStatus extends JDialog implements ActionListener {
	private compFile cfile;
	private JTextField tfCompNum;
	private JTextArea taStatus;
	private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
	private static final Color SUCCESS_COLOR = new Color(46, 204, 113);
	private static final Color DARK_BG = new Color(44, 62, 80);
	private static final Color TEXT_COLOR = new Color(255, 255, 255);

	public compStatus(compFile cfile) {
		this.cfile = cfile;
		setTitle("Check Complaint Status");
		setSize(650, 400);
		setLocationRelativeTo(null);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setResizable(false);

		JPanel mainPanel = createGradientPanel();
		mainPanel.setLayout(new BorderLayout(15, 15));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JLabel titleLabel = new JLabel("Check Your Complaint Status");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setForeground(PRIMARY_COLOR);
		JPanel headerPanel = new JPanel();
		headerPanel.setOpaque(false);
		headerPanel.add(titleLabel);

		JPanel inputPanel = createGradientPanel();
		inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
		JLabel label = new JLabel("Enter Complaint Number:");
		label.setForeground(TEXT_COLOR);
		label.setFont(new Font("Segoe UI", Font.BOLD, 12));
		tfCompNum = new JTextField(15);
		tfCompNum.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tfCompNum.setPreferredSize(new Dimension(200, 35));
		tfCompNum.addActionListener(this);
		inputPanel.add(label);
		inputPanel.add(tfCompNum);

		JPanel statusPanel = createGradientPanel();
		statusPanel.setLayout(new BorderLayout(10, 10));
		statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setForeground(SUCCESS_COLOR);
		statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));

		taStatus = new JTextArea();
		taStatus.setEditable(false);
		taStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		taStatus.setBackground(new Color(52, 73, 94));
		taStatus.setForeground(TEXT_COLOR);
		taStatus.setLineWrap(true);
		taStatus.setWrapStyleWord(true);
		taStatus.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		JScrollPane scrollPane = new JScrollPane(taStatus);

		statusPanel.add(statusLabel, BorderLayout.NORTH);
		statusPanel.add(scrollPane, BorderLayout.CENTER);

		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(inputPanel, BorderLayout.WEST);
		mainPanel.add(statusPanel, BorderLayout.CENTER);

		add(mainPanel);
		setVisible(true);
	}

	private JPanel createGradientPanel() {
		return new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gradient = new GradientPaint(0, 0, DARK_BG, 0, getHeight(), new Color(52, 73, 94));
				g2d.setPaint(gradient);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int compNum = Integer.parseInt(tfCompNum.getText());
			String status = cfile.getSoln(compNum);
			if (status == null) {
				taStatus.setText("Complaint not found. Please check the complaint number.");
			} else if (status.isEmpty()) {
				taStatus.setText("Complaint is under review. No solution has been provided yet.");
			} else {
				taStatus.setText("SOLUTION:\n\n" + status);
			}
		} catch (NumberFormatException exc) {
			taStatus.setText("Please enter a valid complaint number.");
		}
	}
}
