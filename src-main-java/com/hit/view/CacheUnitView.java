package com.hit.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CacheUnitView extends Observable implements View, ActionListener{

	JFrame window;
	JLabel lblMessage;
	
	public CacheUnitView() {
		
		this.window = new JFrame();
		this.window.setLayout(new FlowLayout());
		
		JPanel pnlMain = new  JPanel(new BorderLayout());
		JPanel pnlButtons = new  JPanel(new FlowLayout());
		JPanel pnlLabels = new  JPanel(new BorderLayout());
		
		JButton btnLoadRequest = new JButton("Load a Request");
		btnLoadRequest.setActionCommand("LoadRequest");
		btnLoadRequest.addActionListener(this);
		
		
		JButton btnShowStatistics = new JButton("Show Statistics");
		btnShowStatistics.setActionCommand("ShowStatistics");
		btnShowStatistics.addActionListener(this);
		
		lblMessage = new JLabel();
		lblMessage.setPreferredSize(new Dimension(400, 150));
		
		pnlButtons.add(btnLoadRequest);
		pnlButtons.add(btnShowStatistics);
		pnlLabels.add(lblMessage);
		
		pnlMain.add(pnlButtons, BorderLayout.NORTH);
		pnlMain.add(pnlLabels, BorderLayout.CENTER);
		
		this.window.getContentPane().add(pnlMain);
		
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void start() {
		this.window.pack();
		this.window.setVisible(true);
	}

	@Override
	public <T> void updateUIData(T t) {
		this.lblMessage.setText(t.toString());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(("LoadRequest").equals(e.getActionCommand())) {
			System.out.println("load request clicked");
			this.loadRequestClicked();
		} else if (("ShowStatistics").equals(e.getActionCommand())) {
			System.out.println("show statistics clicked");
			this.showStatisticsClicked();
		}
	}
	
	
	private void loadRequestClicked() {
		this.setChanged();
		this.notifyObservers("LoadRequest");
	}
	
	private void showStatisticsClicked() {
		this.setChanged();
		this.notifyObservers("ShowStatistics");
	}
}
