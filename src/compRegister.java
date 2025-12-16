import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class compRegister extends JDialog implements ActionListener {
	private compFile cfile;
	private JComboBox<String> deptCombo;
	private String[] depts = { "HR", "IT", "Finance", "Operations", "Customer Service" };
	private JTextArea taComp;
	private JButton submitBtn, cancelBtn;
	private int cno;
	private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
	private static final Color DARK_BG = new Color(44, 62, 80);
	private static final Color TEXT_COLOR = new Color(255, 255, 255);
	private static final Color SUCCESS_COLOR = new Color(46, 204, 113);

	public compRegister(compFile cfile) {
		this.cfile = cfile;
		cno = cfile.totalComps + 1;

		setTitle("Register Complaint");
		setSize(650, 550);
		setLocationRelativeTo(null);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setResizable(false);

		JPanel mainPanel = createGradientPanel();
		mainPanel.setLayout(new BorderLayout(15, 15));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JLabel titleLabel = new JLabel("File a New Complaint");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setForeground(PRIMARY_COLOR);
		JPanel headerPanel = new JPanel();
		headerPanel.setOpaque(false);
		headerPanel.add(titleLabel);

		JPanel formPanel = createGradientPanel();
		formPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel cnoLabel = new JLabel("Complaint #:");
		cnoLabel.setForeground(TEXT_COLOR);
		cnoLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		formPanel.add(cnoLabel, gbc);

		gbc.gridx = 1;
		JLabel cnoValue = new JLabel(String.valueOf(cno));
		cnoValue.setForeground(SUCCESS_COLOR);
		cnoValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
		formPanel.add(cnoValue, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		JLabel deptLabel = new JLabel("Department:");
		deptLabel.setForeground(TEXT_COLOR);
		deptLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		formPanel.add(deptLabel, gbc);

		gbc.gridx = 1;
		deptCombo = new JComboBox<>(depts);
		deptCombo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		deptCombo.setPreferredSize(new Dimension(250, 30));
		formPanel.add(deptCombo, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		JLabel detailsLabel = new JLabel("Complaint Details:");
		detailsLabel.setForeground(TEXT_COLOR);
		detailsLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		formPanel.add(detailsLabel, gbc);

		gbc.gridy = 3;
		taComp = new JTextArea(10, 40);
		taComp.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		taComp.setLineWrap(true);
		taComp.setWrapStyleWord(true);
		taComp.setBackground(new Color(52, 73, 94));
		taComp.setForeground(TEXT_COLOR);
		taComp.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		JScrollPane scrollPane = new JScrollPane(taComp);
		scrollPane.setPreferredSize(new Dimension(500, 150));
		formPanel.add(scrollPane, gbc);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

		submitBtn = createStyledButton("Submit", PRIMARY_COLOR);
		cancelBtn = createStyledButton("Cancel", new Color(231, 76, 60));

		submitBtn.addActionListener(this);
		cancelBtn.addActionListener(this);

		buttonPanel.add(submitBtn);
		buttonPanel.add(cancelBtn);

		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

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

	private JButton createStyledButton(String text, Color color) {
		JButton btn = new JButton(text);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn.setForeground(TEXT_COLOR);
		btn.setBackground(color);
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setPreferredSize(new Dimension(120, 40));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return btn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitBtn) {
			if (taComp.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter complaint details", "Validation Error", JOptionPane.WARNING_MESSAGE);
				return;
			}
			complaint newComp = new complaint(cno, (String)deptCombo.getSelectedItem(), taComp.getText(), "");
			cfile.addComp(newComp);
			JOptionPane.showMessageDialog(this, "Complaint Registered Successfully!\n\nYour Complaint Number: " + cno, "Success", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else if (e.getSource() == cancelBtn) {
			int result = JOptionPane.showConfirmDialog(this, "Cancel complaint registration?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				dispose();
			}
		}
	}
}
