package com.poo.entities;

    import java.awt.BorderLayout;
    import java.awt.Color;
    import java.awt.Font;
    import java.awt.GridLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    import javax.swing.ImageIcon;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JOptionPane;
    import javax.swing.JPanel;
    import javax.swing.SwingConstants;

public class Game extends JFrame{
    
	ImageIcon O = new ImageIcon(getClass().getResource("Circulo.jpeg"));
	ImageIcon X = new ImageIcon(getClass().getResource("X.jpeg"));

	JPanel Screen = new JPanel(new GridLayout(3, 3, 10, 10));
	
	Block[] blocks = new Block[9];
	
	int rounds = 0;
	
	final int PLAYER_1 = 1;
	final int PLAYER_2 = 2;
	
	int playerTurn = PLAYER_1;
	
	JLabel lInformation = new JLabel("Vez Do Jogador "+PLAYER_1);
	
	public Game() {
		configureWindow();
		configureScreen();
	}
	
	public void configureScreen() {
		add(BorderLayout.CENTER,Screen);
		add(BorderLayout.NORTH,lInformation);
		Screen.setBackground(Color.lightGray);
		lInformation.setFont(new Font("Times New Roman",Font.BOLD,35));
		lInformation.setForeground(Color.RED);
		lInformation.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i=0;i<9;i++) {
			Block block = new Block();
			blocks[i] = block;
			Screen.add(block);
		}
	}
	
	public void changeTurn(){
		if(playerTurn==1) {
			playerTurn=2;
			lInformation.setText("Vez Do Jogador 2");
			lInformation.setForeground(Color.GREEN);
		} else {
			playerTurn=1;
			lInformation.setText("Vez Do Jogador 1");
			lInformation.setForeground(Color.RED);
		}
	}
	
	public boolean foreheadVictory(int jog) {
		if(blocks[0].whichPlayer==jog && blocks[1].whichPlayer==jog && blocks[2].whichPlayer==jog) {
			return true;
		}
		if(blocks[3].whichPlayer==jog && blocks[4].whichPlayer==jog && blocks[5].whichPlayer==jog) {
			return true;
		}
		if(blocks[6].whichPlayer==jog && blocks[7].whichPlayer==jog && blocks[8].whichPlayer==jog) {
			return true;
		}
		if(blocks[0].whichPlayer==jog && blocks[3].whichPlayer==jog && blocks[6].whichPlayer==jog) {
			return true;
		}
		if(blocks[1].whichPlayer==jog && blocks[4].whichPlayer==jog && blocks[7].whichPlayer==jog) {
			return true;
		}
		if(blocks[2].whichPlayer==jog && blocks[5].whichPlayer==jog && blocks[8].whichPlayer==jog) {
			return true;
		}
		if(blocks[0].whichPlayer==jog && blocks[4].whichPlayer==jog && blocks[8].whichPlayer==jog) {
			return true;
		}
		if(blocks[2].whichPlayer==jog && blocks[4].whichPlayer==jog && blocks[6].whichPlayer==jog) {
			return true;
		}
		return false;
	}

	public void configureWindow() {
		setTitle("Jogo da Velha");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
	public class Block extends JButton{
		int whichPlayer = 0;
		public Block() {
			setBackground(Color.WHITE);
			addActionListener(e->{
				if(whichPlayer==0) {
					if(playerTurn==PLAYER_1) {
						setIcon(O);
						whichPlayer = PLAYER_1;
					} else {
						setIcon(X);
						whichPlayer = PLAYER_2;
					}
					if(foreheadVictory(whichPlayer)) {
						JOptionPane.showMessageDialog(null,"Jogador "+whichPlayer+" Venceu!");
						System.exit(0);
					}
					rounds++;
					if(rounds==9) {
						JOptionPane.showMessageDialog(null,"Deu velha!");
						System.exit(0);
					}
					changeTurn();
				}
			});
		}
	}
	   
}