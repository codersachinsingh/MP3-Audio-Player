package com.mediaplayer.mp3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class JavaMusicPlayer {

	private JFrame frmJavaMusicPlayer;
	private Playlist playlist;
	private MusicPlayer musicplayer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaMusicPlayer window = new JavaMusicPlayer();
					window.frmJavaMusicPlayer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
     	
	/**
	 * Create the application.
	 */
	public JavaMusicPlayer() {
		playlist = new Playlist();
		musicplayer = new MusicPlayer();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJavaMusicPlayer = new JFrame();
		frmJavaMusicPlayer.getContentPane().setBackground(new Color(255, 160, 122));
		frmJavaMusicPlayer.setTitle("Java Music Player");
		frmJavaMusicPlayer.setIconImage(Toolkit.getDefaultToolkit().getImage(JavaMusicPlayer.class.getResource("/resources/title.png")));
		frmJavaMusicPlayer.setBounds(100, 100, 600, 400);
		frmJavaMusicPlayer.setResizable(false);
		frmJavaMusicPlayer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJavaMusicPlayer.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 594, 264);
		frmJavaMusicPlayer.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(0, 128, 128));
		scrollPane.setBounds(10, 25, 574, 228);
		panel.add(scrollPane);
		
		JList<String> list = new JList<>();
		list.setSelectionForeground(new Color(255, 255, 255));
		list.setSelectionBackground(new Color(220, 20, 60));
		list.setFont(new Font("Candara", Font.PLAIN, 16));
		list.setForeground(new Color(245, 245, 245));
		playlist.setJList(list);
		list.setBorder(null);
		list.setBackground(new Color(0, 128, 128));
		scrollPane.setViewportView(list);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBackground(new Color(220, 20, 60));
		panel_2.setBounds(0, 0, 594, 25);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblJavaMusicPlayer = new JLabel("Java Music Player - Play List");
		lblJavaMusicPlayer.setBounds(203, 0, 184, 22);
		panel_2.add(lblJavaMusicPlayer);
		lblJavaMusicPlayer.setForeground(new Color(255, 255, 255));
		lblJavaMusicPlayer.setFont(new Font("Candara", Font.PLAIN, 16));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 153, 102));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(157, 311, 255, 50);
		frmJavaMusicPlayer.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton previousButton = new JButton("");
		previousButton.setBounds(10, 8, 53, 35);
		panel_1.add(previousButton);
		previousButton.setIcon(new ImageIcon(JavaMusicPlayer.class.getResource("/resources/previous.png")));
		
		JButton playButton = new JButton("");
		playButton.setIcon(new ImageIcon(JavaMusicPlayer.class.getResource("/resources/play.png")));
		playButton.setBounds(99, 8, 53, 35);
		panel_1.add(playButton);
		
		JButton nextButton = new JButton("");
		nextButton.setIcon(new ImageIcon(JavaMusicPlayer.class.getResource("/resources/next.png")));
		nextButton.setBounds(192, 8, 53, 35);
		panel_1.add(nextButton);
		
		JButton btnOpenFile = new JButton("Open File");
		btnOpenFile.setForeground(new Color(255, 255, 255));
		btnOpenFile.setBackground(new Color(220, 20, 60));
		btnOpenFile.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnOpenFile.setBounds(35, 311, 94, 23);
		frmJavaMusicPlayer.getContentPane().add(btnOpenFile);
		
		JButton openFolderButton = new JButton("Open Folder");
		openFolderButton.setForeground(new Color(255, 255, 255));
		openFolderButton.setBackground(new Color(220, 20, 60));
		openFolderButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		openFolderButton.setBounds(15, 338, 132, 23);
		frmJavaMusicPlayer.getContentPane().add(openFolderButton);
		
		JButton btnStopplayback = new JButton("StopPlayback");
		btnStopplayback.setForeground(Color.WHITE);
		btnStopplayback.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnStopplayback.setBackground(new Color(255, 0, 51));
		btnStopplayback.setBorder(null);
		btnStopplayback.setBounds(449, 324, 135, 37);
		frmJavaMusicPlayer.getContentPane().add(btnStopplayback);
		
		JLabel nowPlayingLabel = new JLabel("Now Playing - ");
		nowPlayingLabel.setFont(new Font("Fira Code", Font.PLAIN, 12));
		nowPlayingLabel.setBounds(78, 275, 114, 25);
		frmJavaMusicPlayer.getContentPane().add(nowPlayingLabel);
		
		JLabel songNameLabel = new JLabel("");
		songNameLabel.setBounds(175, 277, 409, 23);
		frmJavaMusicPlayer.getContentPane().add(songNameLabel);
		songNameLabel.setFont(new Font("Fira Code", Font.PLAIN, 14));
		playlist.setJLabel(songNameLabel);
		//**********Listeners******************
		list.addMouseListener(new PlaylistListener(musicplayer,playButton,playlist));
		
		btnStopplayback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				musicplayer.stop();
				songNameLabel.setText("");
			}
			
		});
		
		openFolderButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File firstsong = playlist.loadFilesFromFolder();
				musicplayer.play(firstsong);
			}
			
		});
		
		btnOpenFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playlist.loadFile();
			}
			
		});
		
		previousButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				musicplayer.play(playlist.previous());
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				musicplayer.play(playlist.next());
			}			
		});
		
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (musicplayer.isTrackPlaying()) {
					musicplayer.pause();
					JButton play  = (JButton) e.getSource();
					play.setIcon(new ImageIcon(JavaMusicPlayer.class.getResource("/resources/play.png")));
				}
				else {
					musicplayer.resume();
					JButton play  = (JButton) e.getSource();
					play.setIcon(new ImageIcon(JavaMusicPlayer.class.getResource("/resources/pause.png")));
				}
			}
			
		});
	}
}
