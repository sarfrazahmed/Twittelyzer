package com.main.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import com.form.action.TwitterRestCall;

public class MainForm implements ActionListener {
	private JButton jbuttonTrends, jbuttonTraining;
	private TwitterRestCall twitterRestCall;
	public static DefaultTableModel defaultTableModel, defaultTableModelUser;
	private JTable jTable, jTableUser;
	private JScrollPane jScrollPane, jScrollPaneUser;
	public static JLabel label, label1, label2;


	public void design() {

		twitterRestCall = new TwitterRestCall();
		JFrame frame = new JFrame(
				"Quantifying Political Leaning from Tweets, Retweets, and Retweeters");
		// frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane tab = new JTabbedPane();
		frame.add(tab, BorderLayout.CENTER);
		frame.add(createPanelLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = frame.getSize();
		screenSize.height = screenSize.height / 2;
		screenSize.width = screenSize.width / 2;
		size.height = size.height / 2;
		size.width = size.width / 2;
		frame.setSize(1025, 730);
		frame.setVisible(true);

	}

	public JPanel createPanelLayout() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);

		jPanel.setLayout(null);
		jPanel.setBorder(BorderFactory.createTitledBorder(""));
		jPanel.setForeground(Color.YELLOW);
		jPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
		jPanel.setBackground(new Color(192, 192, 192));

		jbuttonTrends = new JButton("political Tweets");
		jbuttonTrends.setBounds(80, 220, 180, 60);
		jbuttonTrends.addActionListener(this);

		jbuttonTraining = new JButton("Retweeters");
		jbuttonTraining.setBounds(80, 460, 180, 60);
		jbuttonTraining.addActionListener(this);

		jPanel.add(jbuttonTrends);
		jPanel.add(jbuttonTraining);

		label = new JLabel("Political Topic from twitter");
		label.setFont(new Font("Verdana", Font.BOLD, 14));
		label.setBounds(490, 110, 360, 30);
		jPanel.add(label);

		label2 = new JLabel("Political Topic Retwitters");
		label2.setFont(new Font("Verdana", Font.BOLD, 14));
		label2.setBounds(500, 360, 360, 30);
		jPanel.add(label2);

		label1 = new JLabel(
				"Quantifying Political Leaning from Tweets, Retweets, and Retweeters");
		label1.setFont(new Font("Verdana", Font.BOLD, 14));
		label1.setBounds(220, 10, 700, 30);
		jPanel.add(label1);

		defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("User ID");
		defaultTableModel.addColumn("Text and URL");
		defaultTableModel.addColumn("In and out Time");
		jTable = new JTable(defaultTableModel);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(250);
		jScrollPane = new JScrollPane(jTable);
		jScrollPane.setBounds(300, 150, 600, 200);

		defaultTableModelUser = new DefaultTableModel();
		defaultTableModelUser.addColumn("Id");
		defaultTableModelUser.addColumn("User Name");
		defaultTableModelUser.addColumn("Screen Name");
		defaultTableModelUser.addColumn("Retweet");
		jTableUser = new JTable(defaultTableModelUser);
		jScrollPaneUser = new JScrollPane(jTableUser);
		jScrollPaneUser.setBounds(300, 400, 600, 200);

		jPanel.add(jScrollPane);
		jPanel.add(jScrollPaneUser);

		return jPanel;
	}

	public static void main(String[] args) {
		MainForm mainForm = new MainForm();
		mainForm.design();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jbuttonTrends) {
			twitterRestCall.getTrends();
		}else if (e.getSource() == jbuttonTraining) {
			twitterRestCall.getTweetSearch();
		}
	}

	public long convertStringtoDate(String time) {
		String[] inSplitter = time.split(" ");
		Calendar calendar = Calendar.getInstance();
		String getInMonth = inSplitter[1].toUpperCase();
		int month = 0;
		int date = Integer.parseInt(inSplitter[2]);
		if (getInMonth.contains("JAN")) {
			month = Calendar.JANUARY;
		} else if (getInMonth.contains("FEB")) {
			month = Calendar.FEBRUARY;
		} else if (getInMonth.contains("MAR")) {
			month = Calendar.MARCH;
		} else if (getInMonth.contains("APR")) {
			month = Calendar.APRIL;
		} else if (getInMonth.contains("MAY")) {
			month = Calendar.MAY;
		} else if (getInMonth.contains("JUN")) {
			month = Calendar.JUNE;
		} else if (getInMonth.contains("JUL")) {
			month = Calendar.JULY;
		} else if (getInMonth.contains("AUG")) {
			month = Calendar.AUGUST;
		} else if (getInMonth.contains("SEP")) {
			month = Calendar.SEPTEMBER;
		} else if (getInMonth.contains("OCT")) {
			month = Calendar.OCTOBER;
		} else if (getInMonth.contains("NOV")) {
			month = Calendar.NOVEMBER;
		} else if (getInMonth.contains("DEC")) {
			month = Calendar.DECEMBER;
		}
		String timeStamp = inSplitter[3];
		calendar.set(2017, month, date);
		int hour = Integer.parseInt(timeStamp.split(":")[0]);
		int minutes = Integer.parseInt(timeStamp.split(":")[1]);
		int secs = Integer.parseInt(timeStamp.split(":")[2]);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, secs);
		System.out.println(calendar.getTime());
		return calendar.getTimeInMillis();
	}
}
