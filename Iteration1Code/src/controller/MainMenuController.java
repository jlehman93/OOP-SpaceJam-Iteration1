package controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import utilities.Scaling;
import view.MainMenuButton;
import view.View;
import view.ImagePanel;

public class MainMenuController {
	private View mainMenu;
	private JButton startGameButton;
	private JButton loadGameButton;
	private JButton quitGameButton;
	private JLabel background;
	private JLabel title;
	private JPanel buttons;
	private JPanel b1;
	private JPanel b2;
	private JPanel b3;
	
	private BufferedImage backgroundImage;
	
	public MainMenuController(){
		
		mainMenu = new View();
		
		//Image scaledImage = originalImage.getScaledInstance(jPanel.getWidth(),jPanel.getHeight(),Image.SCALE_SMOOTH);
		
		backgroundImage = new BufferedImage(1, 1, 1);
		try {
			BufferedImage backgroundImage = ImageIO.read(new File("src/res/img/main_menu_bg.gif"));
		} catch (Exception e) {System.out.println("Didn't find.");}
		
		background = new JLabel(new ImageIcon("src/res/img/main_menu_bg.gif"));
		startGameButton = new MainMenuButton("<html><span style='font-size:20px; font-family:Papyrus;'>New</span></html>");
		loadGameButton = new MainMenuButton("<html><span style='font-size:20px; font-family:Papyrus'>Load</span></html>");
		quitGameButton = new MainMenuButton("<html><span style='font-size:20px; font-family:Papyrus'>Quit</span></html>");
		title = new JLabel("<html><span style='font-size:40px; font-family:Papyrus; color: 00478f'><u>The Dave After Tomorrow</u></span></html>", JLabel.CENTER);
		buttons = new JPanel();
		b1 = new JPanel();
		b2 = new JPanel();
		b3 = new JPanel();
		
		JPanel backGroundPanel = new ImagePanel("src/res/img/main_menu_bg.gif");
		backGroundPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 700, 50));
		Insets insets = backGroundPanel.getInsets();
		Dimension size = backGroundPanel.getPreferredSize();
		
		
		buttons.setLayout(new GridLayout(4,1));
		
		mainMenu.getCanvas().setPreferredSize(new Dimension(Scaling.MAIN_MENU_WIDTH, Scaling.MAIN_MENU_HEIGHT));
		mainMenu.getCanvas().setBorder(new LineBorder(Color.black, 5));
		mainMenu.getCanvas().setLayout(new BorderLayout());
		
		//b1.add(startGameButton);
		//b2.add(loadGameButton);
		//b3.add(quitGameButton);
		
		//buttons.add(new JPanel());
		//buttons.add(b1);
		//buttons.add(b2);
		//buttons.add(b3);
		
		startGameButton.setMaximumSize(new Dimension(200,50));
		loadGameButton.setMaximumSize(new Dimension(200,50));
		quitGameButton.setMaximumSize(new Dimension(200,50));
		
		buttons.setOpaque(false);
		buttons.setBackground(new Color(0,0,0,0));
		
		backGroundPanel.add(title);
		//backGroundPanel.add(buttons, BorderLayout.CENTER);
		backGroundPanel.add(startGameButton);
		backGroundPanel.add(loadGameButton);
		backGroundPanel.add(quitGameButton);
		//backGroundPanel.add(b2, BorderLayout.CENTER);
		//backGroundPanel.add(b3, BorderLayout.CENTER);
		buttons.setBounds(insets.left + 25, insets.top + 25, Scaling.MAIN_MENU_WIDTH, Scaling.MAIN_MENU_HEIGHT);
		
		mainMenu.getCanvas().add(backGroundPanel);
		//mainMenu.getCanvas().add(title, BorderLayout.NORTH);
		//mainMenu.getCanvas().add(buttons,BorderLayout.CENTER);
		//mainMenu.getCanvas().add(background);
		
		startGameButton.addActionListener(new StartButtonListener());
		quitGameButton.addActionListener(new QuitButtonListener());
		loadGameButton.addActionListener(new LoadButtonListener());
	}
	
	public View getView(){
		return mainMenu;
	}
	
	public class StartButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			mainMenu.setNext("Character");
			mainMenu.setRedraw(true);
		}
	}
	
	public class LoadButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			mainMenu.setNext("Game");
			mainMenu.setRedraw(true);
		}
	}
	
	public class QuitButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			mainMenu.setNext("Quit");
		}
	}
	
}
