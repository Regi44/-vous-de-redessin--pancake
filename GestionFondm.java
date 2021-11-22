package gestion;

import gestion.Fromministere;
import Mode.Ministeres;
import dbutils.DBConnection;
import static dbutils.DBConnection.con;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class GestionFondm extends JPanel implements ActionListener, MouseListener {
    PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;
    StringBuilder sb = null;
    JLabel lCode = new JLabel("Exercise Fiscale");
    JLabel lNom = new JLabel("Ministere");
    JLabel lSigle = new JLabel("Montant");
    JLabel lAdresse = new JLabel("Signataire");


              JTextField txCode = new JTextField(20);
 public static JComboBox cbCategorie = new JComboBox();
    JTextField txSigle = new JTextField(20);
    JTextField txAdresse = new JTextField(30);


    JButton btSave = new JButton("Enregistrer");
  
    JButton btNew = new JButton("Modifier");
    JButton btDelete = new JButton("Supprimer");
    

    JLabel lbtri = new JLabel("Recherche Par critere.");
    JTextField txtri = new JTextField(35);

    JTable table = new JTable();
    JPanel Img_pn = new image("images/primati.png");
    Ministeres ministeres= new Ministeres();
    Gestiondesfonds gesf= new  Gestiondesfonds();

    public GestionFondm() {
        
        //couleur background bouton
        
         btNew.setBackground(Color.red);
          btSave.setForeground(Color.white);
          btSave.setBackground(Color.red);
          btDelete.setForeground(Color.white);
          btDelete.setBackground(Color.red);
            btNew.setForeground(Color.white);
            
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
          Fromministere ftr= new  Fromministere();
        ftr.loadData();
        String req = " SELECT nom FROM ministre ";
        con = DBConnection.getConnection();
        try {
            st = con.prepareStatement(req);
            rs = st.executeQuery();
            while (rs.next()) {
         String ministere=rs.getString("nom");
         cbCategorie.addItem(ministere);
           
               
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
        pForm.add(cbCategorie, gbc);
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
        
   
        
        txtri.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                System.out.println("============:"+e.getKeyChar());
                loadDataCritere();
            }
        });

        //action
        btNew.addActionListener(this);
        btSave.addActionListener(this);
        btDelete.addActionListener(this);
        
//////couleur arriere-plan du tableau
        
        table.setBackground(Color.white);
        table.setForeground(Color.red);
        //activ ele tri pa ligne dans le tableau
        table.addMouseListener(this);
        table.setAutoCreateRowSorter(true);
        this.add(Img_pn,null);
        this.add(pForm, BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
         loadData();
      
      // txCode.setEnabled(false);
    }
    
         

    public void loadData() {
        Object obj[][] = new Object[gesf.list().size()][6];
        int compt = 0;
        for (Gestiondesfonds fond : gesf.list()) {
            obj[compt][0] = fond.getExercicesfiscale();
            obj[compt][1] = fond.getMinistere();
            obj[compt][2] = fond.getMontantaloue();
            obj[compt][3] = fond.getSignatairefonds();
            obj[compt][4] = fond.getPourcentagebrh();
            obj[compt][5] = fond.getPourcentagebnc();
            compt++;
        }
        String header[] = {"Exercises Fiscale", "Ministere", "Montant a loue", "Signataire", "% Alloue BRH", "% Alloue BNC" };
        table.setModel(new DefaultTableModel(obj, header));

    }
   
    
 

    public void loadDataCritere() {
        Object obj[][] = new Object[gesf.list().size()][6];
        int compt = 0;
        for (Gestiondesfonds minist : gesf.list()) {
            obj[compt][0] = minist.getExercicesfiscale();
            obj[compt][1] = minist.getMinistere();
            obj[compt][2] = minist.getMontantaloue();
            obj[compt][3] = minist.getSignatairefonds();
            obj[compt][4] = minist.getPourcentagebrh();
            obj[compt][5] = minist.getPourcentagebnc();
            compt++;
        }
        String header[] = {"exercicesfiscale", "Ministere", "montant", "signataire", "% Alloue BRH", "% Alloue BNC" };
        table.setModel(new DefaultTableModel(obj, header));
    }

    public void initialiser() {
        txCode.setText(null);
        cbCategorie.setSelectedIndex(0);
        txSigle.setText(null);
        txAdresse.setText(null);
        btSave.setText("Enregistrer");
        btDelete.setEnabled(false);

    }

    ///action des boutons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btNew) {
            initialiser();
        } else if (e.getSource() == btSave) {
          gesf.setExercicesfiscale(txCode.getText());
            gesf.setMinistere(String.valueOf(cbCategorie.getSelectedItem()));;
            gesf.setMontantaloue(Double.parseDouble(String.valueOf(txSigle.getText())));
            gesf.setSignatairedelareception(txAdresse.getText());
            gesf.setPourcentagebrh(gesf.getPourcentagebrh());
            gesf.setPourcentagebnc(gesf.getPourcentagebnc());
            if(gesf.enr(gesf)==1){
                
                loadData();
               initialiser();
            }
        }
        else{
            if (gesf.update(gesf) == 1) {
                initialiser();
                loadData();
       
             }
            } 
      {
       gesf.setSignatairedelareception(txAdresse.getText());
    if(gesf.delete(gesf)==1){
        initialiser();
        loadData();
      }  
   }
        }
   

    

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == table) {
            btSave.setText("Modifier");
            btNew.setVisible(false);
            btDelete.setEnabled(true);
            int x = table.getSelectedRow();
            txCode.setText(String.valueOf(table.getValueAt(x, 0)));
            cbCategorie.setSelectedItem(String.valueOf(table.getValueAt(x, 1)));
            txSigle.setText(String.valueOf(table.getValueAt(x, 2)));
            txAdresse.setText(String.valueOf(table.getValueAt(x, 3)));
            
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
