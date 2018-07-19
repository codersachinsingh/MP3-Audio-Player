package com.mediaplayer.mp3;

import java.awt.Dialog;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Playlist {
	private JList<String> jlist;
	private File[] mp3files;
	private String[] filenames;
	private int currentPlayingIndex = -1;
	private JLabel currentPlayingLabel;
	
	public void setJLabel(JLabel label) {
		this.currentPlayingLabel = label;
	}
	public void setJList(JList<String> list) {
		this.jlist = list;
	}
	
	public JList<String> getJList() {
		return jlist;
	}
	
	public void loadFile() {
		JFileChooser jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 File","mp3");
		jfc.addChoosableFileFilter(filter);
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.showOpenDialog(null);
		File file = jfc.getSelectedFile();
		if (file != null) {
			String[] fn = new String[1];
			File[] f = new File[1];
			fn[0] = file.getName();
			jlist.setListData(fn);
			f[0]  = file;
			mp3files = f; 			
		}
	}
	
	public File loadFilesFromFolder() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setCurrentDirectory(new File("D:/"));
		jfc.showOpenDialog(null);
		File file = jfc.getSelectedFile();
		if (file != null) {
			File[] files = file.listFiles(new FileFilter() {

				@Override
				public boolean accept(File pathname) {
					if (pathname.getName().endsWith(".mp3") || pathname.isDirectory())
						return true;
					return false;
				}
				
			});
			
			filenames = new String[files.length];
			for (int i = 0 ; i < files.length ; i++) {
				String name = files[i].getName();
				filenames[i] = name.substring(0, name.length()-4);
			}
			
			jlist.setListData(filenames);
			mp3files = files;
			currentPlayingLabel.setText(filenames[0]);
			currentPlayingIndex = 0;
			jlist.setSelectedIndex(currentPlayingIndex);
			jlist.ensureIndexIsVisible(currentPlayingIndex);
			return mp3files[0];
		}
		else {
			return null;
		}
	}
	public int getCurrentPlayingIndex() {
		return currentPlayingIndex;
	}
	public String getFileNameByIndex(int index) {
		return filenames[index];
	}
	public File next() {
		if (mp3files != null && currentPlayingIndex < mp3files.length -1) {
			currentPlayingLabel.setText(filenames[++currentPlayingIndex]);
			jlist.setSelectedIndex(currentPlayingIndex);
			jlist.ensureIndexIsVisible(currentPlayingIndex);
			return mp3files[currentPlayingIndex];
		}
		return null;
	}
	
	public File previous() {
		if (mp3files != null && currentPlayingIndex > 0) {
			currentPlayingLabel.setText(filenames[--currentPlayingIndex]);
			jlist.setSelectedIndex(currentPlayingIndex);
			jlist.ensureIndexIsVisible(currentPlayingIndex);
			return mp3files[currentPlayingIndex];
		}
		return null;
	}
	public File getByIndex(int index) {
		currentPlayingLabel.setText(filenames[index]);
		currentPlayingIndex = index;
		return mp3files[index];
	}
}
