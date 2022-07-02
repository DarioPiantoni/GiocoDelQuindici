package GiocoDelQuindici;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Interfaccia extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textOutput;
	private JPanel contenitoreTasti;
	private final int DIMENSIONE=4;
	private JButton[][] tasti;//matrice di bottoni
	private int posizionei;//posizione riga del 16
	private int posizionej;//posizione colonna del 16
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaccia frame = new Interfaccia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Interfaccia() {
		setTitle("Gioco Del Quindici");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contenitoreTasti = new JPanel();
		contenitoreTasti.setBounds(35, 23, 381, 314);
		contentPane.add(contenitoreTasti);
		contenitoreTasti.setLayout(new GridLayout(4, 4, 10, 10));
		
		textOutput = new JTextField();
		textOutput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textOutput.setHorizontalAlignment(SwingConstants.CENTER);
		textOutput.setEditable(false);
		textOutput.setBounds(35, 362, 381, 36);
		contentPane.add(textOutput);
		textOutput.setColumns(10);
		
		tasti=generaNumeri();
		
		
	}
	public JButton[][] generaNumeri()
	{
		//combinazione vincente spostando solo un bottone
		/*JButton[][]tasti=new JButton[DIMENSIONE][DIMENSIONE];
		tasti[0][0]=new JButton("1");
		tasti[0][1]=new JButton("2");
		tasti[0][2]=new JButton("3");
		tasti[0][3]=new JButton("4");
		tasti[1][0]=new JButton("5");
		tasti[1][1]=new JButton("6");
		tasti[1][2]=new JButton("7");
		tasti[1][3]=new JButton("8");
		tasti[2][0]=new JButton("9");
		tasti[2][1]=new JButton("10");
		tasti[2][2]=new JButton("11");
		tasti[2][3]=new JButton("12");
		tasti[3][0]=new JButton("13");
		tasti[3][1]=new JButton("14");
		tasti[3][2]=new JButton("16");
		tasti[3][2].setVisible(false);
		posizionei=3;
		posizionej=2;
		tasti[3][3]=new JButton("15");
		for(int i=0;i<DIMENSIONE;i++)
		{
			for(int j=0;j<DIMENSIONE;j++)
			{
				 contenitoreTasti.add(tasti[i][j]);
				 tasti[i][j].setActionCommand(tasti[i][j].getText());
				 tasti[i][j].addActionListener(this);
			}
	     }*/
		
		
		//genera numeri casuali non ripetuti
		Random r=new Random();
		JButton[][]tasti=new JButton[DIMENSIONE][DIMENSIONE];
		Integer[]duplicati=new Integer[DIMENSIONE*DIMENSIONE];
		int contDuplicati=0;
		int n;
		boolean risultato;
		
		for(int i=0;i<DIMENSIONE;i++)
		{
			for(int j=0;j<DIMENSIONE;j++)
			{
				do{
					n=r.nextInt(16)+1;
					risultato=duplicato(duplicati,n,contDuplicati);
				    if(risultato==true)
				    {
				         contDuplicati++;
				         tasti[i][j]=new JButton(n+"");
				    	 contenitoreTasti.add(tasti[i][j]);
				    	 tasti[i][j].setActionCommand(tasti[i][j].getText());
				    	 tasti[i][j].addActionListener(this);
				    	 if(tasti[i][j].getText().equals("16"))
				    	 {
				    		 tasti[i][j].setVisible(false);
				    		 posizionei=i;
				    		 posizionej=j;
				    	 }
				    }
				}while(risultato==false);
			    
			 }
	     }
		 return tasti;
	}
	
	
	private boolean duplicato(Integer[] duplicati,int valore,int nDuplicati)
	{
		for(int i=0;i<nDuplicati;i++)
				if(duplicati[i]==valore)
					return false;
		duplicati[nDuplicati]=valore;
		return true;
}
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean entrato=false;
		boolean ordinato=true;
		int numero;
		
		for(int i=0;i<DIMENSIONE;i++)
			for(int j=0;j<DIMENSIONE;j++)
			{
				//verifico i 4 casi di spostamento dei bottoni
				if(i==(posizionei-1) && j==posizionej && entrato==false)
			    {
					if(e.getActionCommand().equals(tasti[i][j].getText()))
					{
						numero=Integer.parseInt(tasti[i][j].getText());
						tasti[i][j].setText("16");
						tasti[i][j].setActionCommand(tasti[i][j].getText());
						tasti[i][j].setVisible(false);
						posizionei=i;
						posizionej=j;
						tasti[i+1][j].setText(numero+"");
						tasti[i+1][j].setActionCommand(tasti[i+1][j].getText());
						tasti[i+1][j].setVisible(true);
						entrato=true;
					}
				}
				else if(j==(posizionej-1) && i==posizionei && entrato==false)
				{
					if(e.getActionCommand().equals(tasti[i][j].getText()))
					{
						numero=Integer.parseInt(tasti[i][j].getText());
						tasti[i][j].setText("16");
						tasti[i][j].setActionCommand(tasti[i][j].getText());
						tasti[i][j].setVisible(false);
						posizionei=i;
						posizionej=j;
						tasti[i][j+1].setText(numero+"");
						tasti[i][j+1].setActionCommand(tasti[i][j+1].getText());
						tasti[i][j+1].setVisible(true);
						entrato=true;
					}
				}
				else if(i==(posizionei+1) && j==posizionej && entrato==false)
				{
					if(e.getActionCommand().equals(tasti[i][j].getText()))
					{
						numero=Integer.parseInt(tasti[i][j].getText());
						tasti[i][j].setText("16");
						tasti[i][j].setActionCommand(tasti[i][j].getText());
						tasti[i][j].setVisible(false);
						posizionei=i;
						posizionej=j;
						tasti[i-1][j].setText(numero+"");
						tasti[i-1][j].setActionCommand(tasti[i-1][j].getText());
						tasti[i-1][j].setVisible(true);
						entrato=true;
					}
				}
				else if(j==((posizionej+1)) && i==posizionei && entrato==false)
				{
					if(e.getActionCommand().equals(tasti[i][j].getText()))
					{
						numero=Integer.parseInt(tasti[i][j].getText());
						tasti[i][j].setText("16");
						tasti[i][j].setActionCommand(tasti[i][j].getText());
						tasti[i][j].setVisible(false);
						posizionei=i;
						posizionej=j;
						tasti[i][j-1].setText(numero+"");
						tasti[i][j-1].setActionCommand(tasti[i][j-1].getText());
						tasti[i][j-1].setVisible(true);
						entrato=true;
					}
				}
			}
			
		
			//sezione per verificare la vittoria
			for(int i=0;i<DIMENSIONE;i++)
			    for(int j=0;j<DIMENSIONE;j++)
			    	if(Integer.parseInt(tasti[i][j].getText())!=(i*DIMENSIONE+j+1))
							ordinato=false;
									
			if(ordinato==true)
			{
				textOutput.setText("Hai vinto");
				for(int i=0;i<DIMENSIONE;i++)
					for(int j=0;j<DIMENSIONE;j++)
					{
						tasti[i][j].setBackground(Color.YELLOW);
						tasti[i][j].setEnabled(false);
					}
			}    
	}
}
