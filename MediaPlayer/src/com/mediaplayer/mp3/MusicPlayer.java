package com.mediaplayer.mp3;

import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer {
	private BufferedInputStream BIS;
	private Player player;
	private long pauseLocation = 0;
	private long trackTotalLength = 0;
	private String filepath;
	private boolean isPlaying = false;
	MusicPlayer() {}
	
	public void pause() {
		if (isPlaying && player != null) {
			try {
				pauseLocation = BIS.available();
				player.close();
				BIS.close();
				isPlaying = false;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	public void resume() {
			
		if (!isPlaying && player != null) {
			try {
				BIS = new BufferedInputStream(new FileInputStream(filepath));
				BIS.skip(trackTotalLength - pauseLocation);
				player = new Player(BIS);
				new Thread(() ->  {
					try {
						player.play();
					} catch (JavaLayerException e) {					
						e.printStackTrace();
					}
				}).start();
				isPlaying = true;
			} catch (IOException | JavaLayerException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public void stop() {
		if (isPlaying && player != null) {
			player.close();
			trackTotalLength = 0;
			pauseLocation = 0;
			isPlaying = false;
		}
	}
	
	public void play(File file){
		if (file == null)
			return;
		stop();
		try {
			filepath = file.getAbsolutePath();
			BIS = new BufferedInputStream(new FileInputStream(file));
			trackTotalLength = BIS.available();
			player = new Player(BIS);
			new Thread(() ->  {
				try {
					player.play();
				} catch (JavaLayerException e) {					
					e.printStackTrace();
				}
			}).start();
			isPlaying = true;
		}
		catch(Exception e) {
			
		}
	}
	
	public boolean isTrackPlaying() {
		return isPlaying;
	}
}
