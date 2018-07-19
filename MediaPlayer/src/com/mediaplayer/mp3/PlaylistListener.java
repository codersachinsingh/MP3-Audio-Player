package com.mediaplayer.mp3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;

public class PlaylistListener implements MouseListener {
	MusicPlayer musicplayer;
	JButton playbutton;
	Playlist playlist;
	public PlaylistListener(MusicPlayer player , JButton playbutton , Playlist playlist) {
		this.musicplayer = player;
		this.playbutton = playbutton;
		this.playlist = playlist;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			try {
			JList<String> l = (JList<String>) e.getSource();
			int index = l.locationToIndex(e.getPoint());
			File file = playlist.getByIndex(index);
			playbutton.setIcon(new ImageIcon(JavaMusicPlayer.class.getResource("/resources/pause.png")));
			musicplayer.play(file);
			}
			catch(Exception ds) {
				ds.printStackTrace();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
