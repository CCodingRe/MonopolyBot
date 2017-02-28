package deadwood;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class UI extends JFrame {

	private final JPanel mainPanel;
	private final JPanel topPanel;
	private final JPanel bottomPanel;
	private final JScrollPane scrollPane; // makes the text scrollable
	private final JPanel inputPanel;

	private Info_Panel info = new Info_Panel();
	private Cmd_panel cmd = new Cmd_panel();
	private Board board = new Board();

	public UI() {
		Board.loadBoard();

		mainPanel = new JPanel();
		topPanel = new JPanel();
		bottomPanel = new JPanel();

		scrollPane = new JScrollPane();  // this scrollPane is used to make the text area scrollable
		inputPanel = new JPanel();

		//default size of our window and its layout:
		setPreferredSize(new Dimension(1080, 1020));
		getContentPane().setLayout(new GridLayout());
		getContentPane().add(mainPanel);
		setTitle("Monopoly");

		mainPanel.add(topPanel);
		mainPanel.add(bottomPanel);

		mainPanel.setLayout(new GridLayout());  //set layout
		topPanel.add(board.getBoard());
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

		bottomPanel.add(scrollPane);
		scrollPane.setViewportView(info.getInfoPanel());       // the scrollPane should make the textArea scrollable, so we define the viewport
		bottomPanel.add(inputPanel);                // then we add the inputPanel to the bottomPanel, so it under the textArea

		inputPanel.add(cmd);

		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
