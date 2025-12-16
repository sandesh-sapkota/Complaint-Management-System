import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;

public class compCheck extends JDialog implements ActionListener {
	private compFile cfile;
	private JTextArea taSoln;
	private JTextField tfCompNum;
	private JButton submitBtn;
	private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
	private static final Color SUCCESS_COLOR = new Color(46, 204, 113);
	private static final Color DARK_BG = new Color(44, 62, 80);
	private static final Color TEXT_COLOR = new Color(255, 255, 255);

	public compCheck(compFile cfile) {
		this.cfile = cfile;
		setTitle("Admin Panel - Manage Complaints");
		setSize(900, 700);
		setLocationRelativeTo(null);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setResizable(false);

		JPanel mainPanel = createGradientPanel();
		mainPanel.setLayout(new BorderLayout(15, 15));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Header
		JLabel titleLabel = new JLabel("Admin Panel");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setForeground(PRIMARY_COLOR);
		JPanel headerPanel = new JPanel();
		headerPanel.setOpaque(false);
		headerPanel.add(titleLabel);

		// Table Panel
		JTable tableAllComps = cfile.returnTable();
		tableAllComps.setRowHeight(25);
		tableAllComps.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tableAllComps.setBackground(new Color(52, 73, 94));
		tableAllComps.setForeground(TEXT_COLOR);
		tableAllComps.setGridColor(new Color(70, 90, 110));
		tableAllComps.setEnabled(false);
		
		JTableHeader header = tableAllComps.getTableHeader();
		header.setBackground(PRIMARY_COLOR);
		header.setForeground(TEXT_COLOR);
		header.setFont(new Font("Segoe UI", Font.BOLD, 12));

		JPanel tablePanel = createGradientPanel();
		tablePanel.setLayout(new BorderLayout());
		tablePanel.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(PRIMARY_COLOR), "All Complaints",
			javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
			new Font("Segoe UI", Font.BOLD, 12), TEXT_COLOR));
		tablePanel.add(new JScrollPane(tableAllComps));

		// Solution Panel
		JPanel solutionPanel = createGradientPanel();
		solutionPanel.setLayout(new GridBagLayout());
		solutionPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel cnoLabel = new JLabel("Complaint No.:");
		cnoLabel.setForeground(TEXT_COLOR);
		cnoLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		solutionPanel.add(cnoLabel, gbc);

		gbc.gridx = 1;
		tfCompNum = new JTextField(20);
		tfCompNum.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfCompNum.setPreferredSize(new Dimension(250, 32));
		tfCompNum.setBackground(new Color(52, 73, 94));
		tfCompNum.setForeground(TEXT_COLOR);
		tfCompNum.setCaretColor(TEXT_COLOR);
		tfCompNum.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR));
		solutionPanel.add(tfCompNum, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JLabel solLabel = new JLabel("Solution:");
		solLabel.setForeground(SUCCESS_COLOR);
		solLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		solutionPanel.add(solLabel, gbc);

		gbc.gridy = 2;
		taSoln = new JTextArea(5, 40);
		taSoln.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		taSoln.setLineWrap(true);
		taSoln.setWrapStyleWord(true);
		taSoln.setBackground(new Color(52, 73, 94));
		taSoln.setForeground(TEXT_COLOR);
		taSoln.setCaretColor(TEXT_COLOR);
		taSoln.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR));
		JScrollPane scrollPane = new JScrollPane(taSoln);
		scrollPane.setPreferredSize(new Dimension(500, 100));
		solutionPanel.add(scrollPane, gbc);

		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		submitBtn = createStyledButton("Submit Solution", PRIMARY_COLOR);
		submitBtn.addActionListener(this);
		solutionPanel.add(submitBtn, gbc);

		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(tablePanel, BorderLayout.CENTER);
		mainPanel.add(solutionPanel, BorderLayout.SOUTH);

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
		btn.setPreferredSize(new Dimension(150, 40));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return btn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (submitBtn == (JButton) e.getSource()) {
			String cnoStr = tfCompNum.getText().trim();
			String sol = taSoln.getText().trim();

			if (cnoStr.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter complaint number", "Validation Error", JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (sol.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter a solution", "Validation Error", JOptionPane.WARNING_MESSAGE);
				return;
			}

			try {
				int cno = Integer.parseInt(cnoStr);
				if (!cfile.findComp(cno)) {
					JOptionPane.showMessageDialog(this, "Complaint not found", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					cfile.addSoln(cno, sol);
					JOptionPane.showMessageDialog(this, "Solution added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (Exception e1) {
					int opt = JOptionPane.showConfirmDialog(this, "Solution already exists. Overwrite?", "Confirm", JOptionPane.YES_NO_OPTION);
					if (opt == JOptionPane.YES_OPTION) {
						cfile.overwriteSoln(cno, sol);
						JOptionPane.showMessageDialog(this, "Solution updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
				}
			} catch (NumberFormatException exc) {
				JOptionPane.showMessageDialog(this, "Please enter a valid complaint number", "Validation Error", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
