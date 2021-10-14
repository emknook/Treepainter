package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import controller.MainController;

@SuppressWarnings("serial")
public class PaintingFrame extends JFrame {

	private MainController m;
	private PaintingPanel p;
	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	private ActionListener loadFile;
	private ActionListener saveFile;
	private ActionListener exitFile;
	private ActionListener addLeafTree;
	private ActionListener addPineTree;
	private ActionListener add100Trees;
	private ActionListener clearTrees;
	private ActionListener arialFont;
	private ActionListener helveticaFont;
	private ActionListener courierFont;
	private ActionListener timesFont;
	private ActionListener addCoins;
	private ActionListener removeCoins;
	private ItemListener enableCoins;
	private ItemListener playWorld;
	
	public PaintingFrame(MainController m){
		this.m = m;
		p = new PaintingPanel(m,m.getW());
		initializeActionListeners();
		initializeMenuBar();
		initialize();
	}

	private void initializeActionListeners() {
		loadFile = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				m.loadFile();
			}
		};   
		saveFile = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				m.saveFile();
			}
		};     
		exitFile = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};     
		addLeafTree = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				m.addLeafTree();
			}
		};  
		addPineTree = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				m.addPineTree();
			}
		};  
		add100Trees = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				m.add100Trees();
				repaint();
			}
		};  
		clearTrees = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				m.clearTrees();
			}
		};  
		arialFont = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setFont(PaintingPanel.FontType.ARIAL);
			}
		};    
		helveticaFont = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setFont(PaintingPanel.FontType.HELVETICA);
			}
		}; 
		courierFont  = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setFont(PaintingPanel.FontType.COURIER);
			}
		}; 
		timesFont = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setFont(PaintingPanel.FontType.TIMES);
			}
		};   
		addCoins = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				m.addCoins();
			}
		};
		removeCoins = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				m.removeCoins();
			}
		};
		enableCoins = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				m.setCoins(((AbstractButton) e.getSource()).isSelected());				
			}
		};
		playWorld = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				m.playWorld(((AbstractButton) e.getSource()).isSelected());				
			}
		};
	}

	private void initializeMenuBar() {
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem load = new JMenuItem("load painting...");
		load.addActionListener(loadFile);
		JMenuItem save = new JMenuItem("save painting...");
		save.addActionListener(saveFile);
		JMenuItem exit = new JMenuItem("exit");
		exit.addActionListener(exitFile);
		JMenu tree = new JMenu("Tree");
		JMenuItem leafTree = new JMenuItem("add leaf tree");
		leafTree.addActionListener(addLeafTree);
		JMenuItem pineTree = new JMenuItem("add pine tree");
		pineTree.addActionListener(addPineTree);
		JMenuItem add100 = new JMenuItem("add 100 trees");
		add100.addActionListener(add100Trees);
		JMenuItem clear = new JMenuItem("clear all trees");
		clear.addActionListener(clearTrees);
		JMenu font = new JMenu("Autograph Font");
		JRadioButtonMenuItem arial = new JRadioButtonMenuItem("Arial");
		arial.addActionListener(arialFont);
		JRadioButtonMenuItem helvetica = new JRadioButtonMenuItem("Helvetica");
		helvetica.addActionListener(helveticaFont);
		JRadioButtonMenuItem courier = new JRadioButtonMenuItem("Courier");
		courier.addActionListener(courierFont);
		JRadioButtonMenuItem times = new JRadioButtonMenuItem("Times new roman");
		times.addActionListener(timesFont);
		ButtonGroup group = new ButtonGroup();
		arial.setSelected(true);
		group.add(arial);
		group.add(helvetica);
		group.add(courier);
		group.add(times);
		JMenu movie = new JMenu("Movie");
		JCheckBoxMenuItem play = new JCheckBoxMenuItem("play");
		play.addItemListener(playWorld);
		JMenu game = new JMenu("Game");
		JMenuItem addCoinsItem = new JMenuItem("add coins");
		addCoinsItem.addActionListener(addCoins);
		game.add(addCoinsItem);
		JMenuItem removeCoinsItem = new JMenuItem("remove coins");
		removeCoinsItem.addActionListener(removeCoins);
		game.add(removeCoinsItem);
		JCheckBoxMenuItem enableCoinsItem = new JCheckBoxMenuItem("enable movie coins");
		enableCoinsItem.addItemListener(enableCoins);
		game.add(enableCoinsItem);
		file.add(load);
		file.add(save);
		file.add(exit);
		tree.add(leafTree);
		tree.add(pineTree);
		tree.add(add100);
		tree.add(clear);
		font.add(arial);
		font.add(helvetica);
		font.add(courier);
		font.add(times);
		movie.add(play);
		bar.add(file);
		bar.add(tree);
		bar.add(font);
		bar.add(movie);
		bar.add(game);
		this.setJMenuBar(bar);
	}

	private void initialize() {
		this.setTitle("Marcy Knook - Painting");
		this.setContentPane(p);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));
		this.setSize(WIDTH, HEIGHT);
		DisplayScreen ds = new DisplayScreen();
		this.setLocation(ds.getCenteredPoint(this));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		this.setVisible(true);
	}
}
