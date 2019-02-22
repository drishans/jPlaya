package display;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import main.Player;
import main.Queue;
import utils.Utils;

import javax.swing.JTable;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.Scrollbar;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("unused")
public class jPlayaGUI {

	private JFrame frame;
	private JTextField pathField;

	private File[] album;
	private File songFile;
	public Queue<File> playQ;
	public AdvancedPlayer p;
	public DefaultListModel<File> DLM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel( // match OS default skin
					        UIManager.getSystemLookAndFeelClassName());
					jPlayaGUI window = new jPlayaGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public jPlayaGUI() {
		playQ = new Queue<File>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.BLACK);
		frame.setTitle("jPlaya beta");
		frame.setBounds(100, 100, 462, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); // set to center of the screen

		JButton btnPlay = new JButton("\u25BA");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnPlay.setBounds(10, 420, 45, 35);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// play audio
				try {
					p = new AdvancedPlayer(new FileInputStream(songFile));
					p.play();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "No File Selected",
					        "Error: Failed to Play", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		JButton btnStop = new JButton("\u258A");
		btnStop.setBounds(55, 420, 45, 35);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// pause audio
				try {
					p.stop();
					btnPlay.setText("\u25BA");

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "No File Selected",
					        "Error: Failed to Stop", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnPlay);
		frame.getContentPane().add(btnStop);

		JToggleButton tglbtnRepeat = new JToggleButton("\uD83D\uDD01");
		tglbtnRepeat.setBounds(100, 420, 45, 35);
		frame.getContentPane().add(tglbtnRepeat);

		JToggleButton tglbtnShuffle = new JToggleButton("\uD83D\uDD00");
		tglbtnShuffle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		tglbtnShuffle.setBounds(145, 420, 45, 35);
		frame.getContentPane().add(tglbtnShuffle);

		JSlider sliderVol = new JSlider();
		sliderVol.setPaintLabels(true);
		sliderVol.setPaintTicks(true);
		JLabel lblVolume = new JLabel("Volume: " + sliderVol.getValue() + "%");
		lblVolume.setBounds(200, 420, 80, 13);
		frame.getContentPane().add(lblVolume);
		sliderVol.setBounds(270, 420, 173, 35);
		sliderVol.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				// Player currentVolume = slider.getValue();
				// Player setVolume((double) (sliderVol.getValue() / 100.0));
				lblVolume.setText("Volume: " + sliderVol.getValue() + "%");
			}
		});
		frame.getContentPane().add(sliderVol);

		pathField = new JTextField();
		pathField.setForeground(Color.LIGHT_GRAY);
		pathField.setEditable(false);
		pathField.setText("Song Path");
		pathField.setBounds(10, 10, 288, 19);
		frame.getContentPane().add(pathField);
		pathField.setColumns(10);

		JList<File> playList = new JList<File>();
		playList.setBounds(10, 52, 223, 276);
		frame.getContentPane().add(playList);

		JButton btnOpen = new JButton("Add song to queue");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				open();
				DLM = new DefaultListModel<File>();
				DLM.addElement(songFile);
				playList.setModel(DLM);
			}
		});
		btnOpen.setBounds(308, 9, 135, 21);
		frame.getContentPane().add(btnOpen);

		TextArea consoleUI = new TextArea();
		consoleUI.setText("Welcome to jPlaya -- \u266B \u266A");
		consoleUI.setBounds(0, 335, 448, 77);
		frame.getContentPane().add(consoleUI);

		JRadioButton rdbtnMute = new JRadioButton("mute \uD83D\uDD07");
		rdbtnMute.setBounds(200, 440, 64, 13);
		frame.getContentPane().add(rdbtnMute);

		JButton btnRemoveSongFrom = new JButton("Remove song from playlist");
		btnRemoveSongFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				playList.clearSelection();
			}
		});
		btnRemoveSongFrom.setBounds(243, 308, 200, 21);
		frame.getContentPane().add(btnRemoveSongFrom);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setBounds(243, 39, 195, 262);
		frame.getContentPane().add(tabbedPane);

		JTabbedPane albumTab = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Album Art", null, albumTab, null);

		JTabbedPane infoTab = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Track info", null, infoTab, null);

		JTabbedPane lyricTab = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Lyrics", null, lyricTab, null);

		JLabel lblPlaylist = new JLabel(".:: Playlist ::.");
		lblPlaylist.setForeground(Color.MAGENTA);
		lblPlaylist.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlaylist.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblPlaylist.setBounds(10, 39, 223, 13);
		frame.getContentPane().add(lblPlaylist);

	}

	private void open() {
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Choose Song To Load...");
			chooser.showOpenDialog(null);
			songFile = chooser.getSelectedFile();

			System.out.println("File " + songFile.getName() + ", selected!");

			if (songFile.getName().endsWith(".mp3")) {
				frame.setTitle("jPlaya | MP3 Player");
			} else if (songFile.getName().endsWith(".wav")) {
				frame.setTitle("jPlaya | WAV Player");
			} else if (songFile.getName().endsWith(".flac")) {
				frame.setTitle("jPlaya | FLAC Player");
			} else {
				JOptionPane.showMessageDialog(null,
				        "Invalid File Type Selected", "Error",
				        JOptionPane.ERROR_MESSAGE);
				open();
			}

		} catch (Exception e) {
			// e.printStackTrace();
		}
	}
}