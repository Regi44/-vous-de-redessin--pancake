package gestion;

import Mode.Ministeres;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GOSSIN Esther
 */
public class Fromministere extends JPanel implements ActionListener, MouseListener {

    JLabel lCode = new JLabel("CODE");
    JLabel lNom = new JLabel("NOM MINISTERE");
    JLabel lSigle = new JLabel("Sigle");
    JLabel lAdresse = new JLabel("ADRESSE");
    JLabel lTelephone = new JLabel("TELEPHONE");
    JLabel lDirigeant = new JLabel("Dirige par le Ministre");
    JLabel lNo_compteBRH = new JLabel("Numero compte BRH");
    JLabel lNo_compteBNC = new JLabel("Numero compte BNC");

    JTextField txCode = new JTextField(30);
    JTextField txNom = new JTextField(30);
    JTextField txSigle = new JTextField(30);
    JTextField txAdresse = new JTextField(30);
    JTextField txTelephone = new JTextField(30);
    JTextField txDirigeant = new JTextField(30);
    JTextField txNo_compteBRH = new JTextField(30);
    JTextField txNo_compteBNC = new JTextField(30);
    
    JButton btSave = new JButton("Enregistrer");
    JButton btNew = new JButton("Modifier");
    JButton btDelete = new JButton("Supprimer");

    JLabel lbtri = new JLabel("Recherche Par critere.");
    JTextField txtri = new JTextField(35);
    JPanel Img_pn = new image("images/primati.png");
    
    JTable table = new JTable();

    Ministeres ministeres = new Ministeres();

    public Fromministere() {
        
        btNew.setForeground(Color.white);
         btNew.setBackground(Color.red);
          btSave.setForeground(Color.white);
          btSave.setBackground(Color.red);
          btDelete.setForeground(Color.white);
          btDelete.setBackground(Color.red);
        // creation d'une instance de gridBagLayout
        this.setLayout(new BorderLayout());
        JPanel pForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        btDelete.setEnabled(false);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 3, 3, 3);
        // pForm.setLayout(gl);
        gbc.gridx = 0;
        gbc.gridy = 0;
        pForm.add(lCode, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        pForm.add(txCode, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        pForm.add(lNom, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        pForm.add(txNom, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        pForm.add(lSigle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        pForm.add(txSigle, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        pForm.add(lAdresse, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        pForm.add(txAdresse, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        pForm.add(lTelephone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        pForm.add(txTelephone, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        pForm.add(lDirigeant, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        pForm.add(txDirigeant, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        pForm.add(lNo_compteBRH, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        pForm.add(txNo_compteBRH, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        pForm.add(lNo_compteBNC, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        pForm.add(txNo_compteBNC, gbc);
 
        JPanel pbouton = new JPanel();
        pbouton.setBackground(Color.red);
        pbouton.add(btSave);
        pbouton.add(btNew);
        pbouton.add(btDelete);
        gbc.gridx = 0;
        gbc.gridy = 8;
        pForm.add(pbouton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        pForm.add(pbouton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 9;
        pForm.add(lbtri, gbc);
        gbc.gridx = 1;
        gbc.gridy = 9;
        pForm.add(txtri, gbc);
        
        
        txtri.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
               System.out.println("============:"+e.getKeyChar());
                loadDataCritere();
            }
        });

        //action
        txtri.addActionListener(this);
        btNew.addActionListener(this);
        btSave.addActionListener(this);
        btDelete.addActionListener(this);

        //////couleur arriere-plan du tableau
        
        table.setBackground(Color.red);
        table.setForeground(Color.white);
        
        //activ ele tri pa ligne dans le tableau
        table.addMouseListener(this);
        table.setAutoCreateRowSorter(true);
        this.add(Img_pn,null);
        this.add(pForm, BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        loadData();
        txCode.setEnabled(false);
    }

     public void loadDatamin() {
       Object obj[][] = new Object[ministeres.list().size()][8];
       int compt = 0;
       for (Ministeres minist : ministeres.list()) {           
           obj[compt][1] = minist.getNom();
         compt++;  
       }
       LinkedList minister= new LinkedList();
       minister.add(obj);
        
   

//  
//
}
//        
       

    
    public void loadData() {
        Object obj[][] = new Object[ministeres.list().size()][8];
        int compt = 0;
        for (Ministeres minist : ministeres.list()) {
            obj[compt][0] = minist.getCode();
            obj[compt][1] = minist.getNom();
            obj[compt][2] = minist.getSigle();
            obj[compt][3] = minist.getAdresse();
            obj[compt][4] = minist.getTelephone();
            obj[compt][5] = minist.getDirigeant();
            obj[compt][6] = minist.getNo_compteBRH();
            obj[compt][7] = minist.getNo_compteBRH();
            compt++;
        }
        String header[] = {"CODE", "NOM", "SIGLE", "ADRESSE", "TELEPHONE", "DIRIGEANT", "NO COMPTE BRH", "NO COMPTE BNC"};
        table.setModel(new DefaultTableModel(obj, header));

    }

    public void loadDataCritere() {
        Object obj[][] = new Object[ministeres.list(txtri.getText()).size()][8];
        int compt = 0;
        for (Ministeres minist : ministeres.list()) {
            obj[compt][0] = minist.getCode();
            obj[compt][1] = minist.getNom();
            obj[compt][2] = minist.getSigle();
            obj[compt][3] = minist.getAdresse();
            obj[compt][4] = minist.getTelephone();
            obj[compt][5] = minist.getDirigeant();
            obj[compt][6] = minist.getNo_compteBRH();
            obj[compt][7] = minist.getNo_compteBRH();
            compt++;
        }
        String header[] = {"CODE", "NOM", "SIGLE", "ADRESSE", "TELEPHONE", "DIRIGEANT", "NO COMPTE BRH", "NO COMPTE BNC"};
        table.setModel(new DefaultTableModel(obj, header));
    }

    public void initialiser() {
        txCode.setText(null);
        txNom.setText(null);
        txSigle.setText(null);
        txAdresse.setText(null);
        txTelephone.setText(null);
        txDirigeant.setText(null);
        txNo_compteBRH.setText(null);
        txNo_compteBNC.setText(null);
        btSave.setText("Enregistrer");
        btDelete.setEnabled(false);

    }

    ///action des boutons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btNew) {
            initialiser();
        } else if (e.getSource() == btSave) {
            ministeres.setCode(txCode.getText());
            ministeres.setNom(txNom.getText());
            ministeres.setSigle(txSigle.getText());
            ministeres.setAdresse(txAdresse.getText());
            ministeres.setDirigeant(txDirigeant.getText());
            ministeres.setTelephone(txTelephone.getText());
            ministeres.setNo_compteBRH(txNo_compteBRH.getText());
            ministeres.setNo_compteBNC(txNo_compteBNC.getText());
            if(ministeres.save(ministeres)==1){
                loadData();
             initialiser();
   
            }
        }else{
             if (ministeres.update(ministeres) == 1) {
                initialiser();
                loadData();
       
             }
            } 
    
       ministeres.setCode(txCode.getText());
    if(ministeres.delete(ministeres)==1){
        initialiser();
        loadData();
      }  
   }
        
    

    
  @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == table) {
            btSave.setText("Modifier");
            btNew.setEnabled(false);
            btDelete.setEnabled(true);
            int x = table.getSelectedRow();
            txCode.setText(String.valueOf(table.getValueAt(x, 0)));
            txNom.setText(String.valueOf(table.getValueAt(x, 1)));
            txSigle.setText(String.valueOf(table.getValueAt(x, 2)));
            txAdresse.setText(String.valueOf(table.getValueAt(x, 3)));
            txTelephone.setText(String.valueOf(table.getValueAt(x, 4)));
            txDirigeant.setText(String.valueOf(table.getValueAt(x, 5)));
            txNo_compteBRH.setText(String.valueOf(table.getValueAt(x, 6)));
            txNo_compteBNC.setText(String.valueOf(table.getValueAt(x, 7)));
        }
    }
    

    @Override
    public void mousePressed(MouseEvent e) {
      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }



    

   
}
