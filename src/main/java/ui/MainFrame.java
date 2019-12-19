package ui;

import domain.BrickingBad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class MainFrame extends JFrame {

    private JButton mapEditorButton;
    private JButton gamePlayButton;
    private JPanel contPanel;
    private JPanel mainPanel;
    private MapBuildPanel mapBuildPanel;
    private GamePanel gamePanel;
    private CardLayout cardLayout;

    private static final String MAP_EDITOR_MODE = "Map Editor Mode";
    private static final String GAME_PLAY_MODE = "Game Play Mode";
    private static final int LENGTH = 500;
    private static final int WIDTH = 500;

    public MainFrame(BrickingBad bb) {

        mapEditorButton = new JButton(MAP_EDITOR_MODE);
        gamePlayButton = new JButton(GAME_PLAY_MODE);
        contPanel = new JPanel();
        mainPanel = new JPanel(new GridBagLayout());
        mapBuildPanel = new MapBuildPanel(bb);
        gamePanel = new GamePanel(bb);
        cardLayout = new CardLayout();

        contPanel.setLayout(cardLayout);

        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(new MKeyListener(bb));
        mapBuildPanel.addKeyListener(new MKeyListener(bb));
        mainPanel.addKeyListener(new MKeyListener(bb));
        contPanel.addKeyListener(new MKeyListener(bb));
        this.addKeyListener(new MKeyListener(bb));

        setFocusable(true);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(mapEditorButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(gamePlayButton);

        mapBuildPanel.backToMain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contPanel, "main");
                repaint();
            }
        });

        gamePanel.backToMain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contPanel, "main");
                repaint();
            }
        });

        mapEditorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(contPanel, "edit");
                repaint();
            }
        });

        gamePlayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(contPanel, "game");
                repaint();
            }
        });

        contPanel.add(mainPanel, "main");
        contPanel.add(mapBuildPanel, "edit");
        contPanel.add(gamePanel, "game");

        cardLayout.show(contPanel, "main");

        this.setTitle("BrickingBad");
        this.add(contPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(800, 800);
        this.setVisible(true);
        this.setFocusable(true);

    }

}


class MKeyListener extends KeyAdapter {
    BrickingBad bb;

    private final Set<Integer> pressed = new HashSet<Integer>();

    public MKeyListener(BrickingBad bb) {
        this.bb = bb;
    }

    @Override
    public synchronized void keyPressed(KeyEvent event) {
        System.out.println("keyTyped: " + event.getKeyCode());
        pressed.add(event.getKeyCode());

        if (pressed.contains(KeyEvent.VK_RIGHT)) {
            bb.movePaddleRight();
        }

        if (pressed.contains(KeyEvent.VK_LEFT)) {
            bb.movePaddleLeft();
        }

        if (pressed.contains(KeyEvent.VK_A)) {
            bb.rotatePaddleLeft();
        }

        if (pressed.contains(KeyEvent.VK_D)) {
            bb.rotatePaddleRight();
        }

        if (pressed.contains(KeyEvent.VK_RIGHT)) {
            bb.rotatePaddleRight();
        }

        if (pressed.contains(KeyEvent.VK_RIGHT)) {
            bb.rotatePaddleLeft();
        }
        if (pressed.contains(KeyEvent.VK_LEFT)) {
            bb.rotatePaddleRight();
        }

        if (pressed.contains(KeyEvent.VK_LEFT)) {
            bb.rotatePaddleLeft();
        }

    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
    }


}

