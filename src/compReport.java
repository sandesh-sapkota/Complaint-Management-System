import java.awt.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;

public class compReport extends JDialog {
	private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
	private static final Color DARK_BG = new Color(44, 62, 80);
	private static final Color TEXT_COLOR = new Color(255, 255, 255);

	public compReport(compFile cfile) {
		setTitle("Complaint Report");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setResizable(false);

		JPanel mainPanel = createGradientPanel();
		mainPanel.setLayout(new BorderLayout(15, 15));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Header
		JPanel headerPanel = new JPanel();
		headerPanel.setOpaque(false);
		
		JLabel titleLabel = new JLabel("Complaints Report");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
		titleLabel.setForeground(PRIMARY_COLOR);
		
		JLabel subtitleLabel = new JLabel("Total Complaints: " + cfile.totalComps);
		subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		subtitleLabel.setForeground(new Color(200, 200, 200));
		
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		subtitleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		headerPanel.add(titleLabel);
		headerPanel.add(Box.createVerticalStrut(5));
		headerPanel.add(subtitleLabel);

		// Table Panel
		JTable tableAllComps = cfile.returnTable();
		tableAllComps.setRowHeight(25);
		tableAllComps.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tableAllComps.setBackground(new Color(52, 73, 94));
		tableAllComps.setForeground(TEXT_COLOR);
		tableAllComps.setGridColor(new Color(70, 90, 110));
		tableAllComps.setEnabled(false);
		tableAllComps.setSelectionBackground(PRIMARY_COLOR);
		
		JTableHeader header = tableAllComps.getTableHeader();
		header.setBackground(PRIMARY_COLOR);
		header.setForeground(TEXT_COLOR);
		header.setFont(new Font("Segoe UI", Font.BOLD, 12));
		header.setPreferredSize(new Dimension(0, 30));

		JPanel tablePanel = createGradientPanel();
		tablePanel.setLayout(new BorderLayout());
		tablePanel.add(new JScrollPane(tableAllComps));

		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(tablePanel, BorderLayout.CENTER);

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
}
