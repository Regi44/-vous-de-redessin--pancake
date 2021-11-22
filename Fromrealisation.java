
package gestion;

import Mode.Ministeres;
import dbutils.DBConnection;
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
 * @author Regi
 */
public class Fromrealisation extends JPanel implements ActionListener, MouseListener {
    
    
   JPanel Img_pn = new image("images/primati.png");
    JLabel lexe=new JLabel("Exercices Fiscale");
    JLabel min = new JLabel("Ministere");
    JLabel lSigle = new JLabel("Numero Projet");
    JLabel lAdresse = new JLabel("Type de Projet");
    JLabel lTelephone = new JLabel("Description Projet");
    JLabel lDirigeant = new JLabel("Nom firme");
    JLabel lNo_compteBRH = new JLabel("Maitre D'ouvrage");
    JLabel lNo_compteBNC = new JLabel("Cout de Projet");
    JLabel lzone = new JLabel("Zone de Projet");

    PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;
    
    JTextField txCode = new JTextField(20);
    JComboBox<String> cbCategorie = new JComboBox<String>();
    JTextField txSigle = new JTextField(20);
    JTextField txAdresse = new JTextField(30);
    JTextField txTelephone = new JTextField(30);
    JTextField txDirigeant = new JTextField(20);
    JTextField txNo_compteBRH = new JTextField(20);
    JTextField txNo_compteBNC = new JTextField(20);
    JTextField txzone= new JTextField(30);
    JButton btSave = new JButton("Enregistrer");
    JButton btNew = new JButton("Modifier");
    JButton btDelete = new JButton("Supprimer");

    JLabel lbtri = new JLabel("Recherche Par critere.");
    JTextField txtri = new JTextField(35);

    JTable table = new JTable();

    Realisation ministeres = new Realisation();
    
    public Fromrealisation(){
        
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
        pForm.add(lexe, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        pForm.add(txCode, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        pForm.add(min, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
           Ministeres Ministeres= new  Ministeres();
        String req = " SELECT nom FROM ministre ";
        con = DBConnection.getConnection();
        try {
             
          st = con.prepareStatement(req);
            rs = st.executeQuery();
            while (rs.next()) {
             Ministeres.update(Ministeres);
         String ministere=rs.getString("nom");
         cbCategorie.addItem(ministere);
           
               
            }
        } catch (SQLException ex) {
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
        gbc.gridx = 0;
        gbc.gridy = 8;
        pForm.add(lzone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        pForm.add(txzone, gbc);

        JPanel pbouton = new JPanel();
        pbouton.setBackground(Color.red);
        pbouton.add(btSave);
        pbouton.add(btNew);
        pbouton.add(btDelete);
        gbc.gridx = 0;
        gbc.gridy = 9;
        pForm.add(pbouton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 9;
        pForm.add(pbouton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 10;
        pForm.add(lbtri, gbc);
        gbc.gridx = 1;
        gbc.gridy = 10;
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
        
        table.setBackground(Color.red);
        table.setForeground(Color.white);
        
        //activ ele tri pa ligne dans le tableau
        table.addMouseListener(this);
        table.setAutoCreateRowSorter(true);
        this.add(Img_pn,null);
        this.add(pForm, BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        loadData();
        txSigle.setEnabled(false);
    }

    public void loadData() {
        Object obj[][] = new Object[ministeres.list().size()][9];
        int compt = 0;
        for (Realisation minist : ministeres.list()) {
            obj[compt][0] = minist.getExercicesfiscale();
            obj[compt][1] = minist.getMinistere();
            obj[compt][2] = minist.getNumero_projet();
            obj[compt][3] = minist.getType_projet();
            obj[compt][4] = minist.getDesc_projet();
            obj[compt][5] = minist.getNom_firm();
            obj[compt][6] = minist.getMaitre_douvrage();
            obj[compt][7] = minist.getCout_projet();
            obj[compt][8] = minist.getZone_projet();
            compt++;
        }
        String header[] = {"EXERCICES FISCALE", "MINISTERE", "NUMERO PROJET", "TYPE DE PROJET", "DESCRIPTION PROJET", "NOM FIRM", "MAITRE D'OUVRAGE", "COUT DE PROJET", "ZONE PROJET"};
        table.setModel(new DefaultTableModel(obj, header));

    }

    public void loadDataCritere() {
             Object obj[][] = new Object[ministeres.list(txtri.getText()).size()][9];
        int compt = 0;
        for (Realisation minist : ministeres.list()) {
            obj[compt][0] = minist.getExercicesfiscale();
            obj[compt][1] = minist.getMinistere();
            obj[compt][2] = minist.getNumero_projet();
            obj[compt][3] = minist.getType_projet();
            obj[compt][4] = minist.getDesc_projet();
            obj[compt][5] = minist.getNom_firm();
            obj[compt][6] = minist.getMaitre_douvrage();
            obj[compt][7] = minist.getCout_projet();
            obj[compt][8] = minist.getZone_projet();
            compt++;
        }
        String header[] = {"EXERCICES FISCALE", "MINISTERE", "NUMERO PROJET", "TYPE DE PROJET", "DESCRIPTION PROJET", "NOM FIRM", "MAITRE D'OUVRAGE", "COUT DE PROJET", "ZONE PROJET"};
        table.setModel(new DefaultTableModel(obj, header));
    }

    public void initialiser() {
        txCode.setText(null);
        cbCategorie.setSelectedIndex(0);
        txSigle.setText(null);
        txAdresse.setText(null);
        txTelephone.setText(null);
        txDirigeant.setText(null);
        txNo_compteBRH.setText(null);
        txNo_compteBNC.setText(null);
        txzone.setText(null);
        btSave.setText("Enregistrer");
        btDelete.setEnabled(false);

    }

    ///action des boutons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btNew) {
            initialiser();
        } else if (e.getSource() == btSave) {
            ministeres.setExercicesfiscale(txCode.getText());
            ministeres.setMinistere(String.valueOf(cbCategorie.getSelectedItem()));
            ministeres.setNumero_projet(txSigle.getText());
            ministeres.setType_projet(txAdresse.getText());
            ministeres.setDesc_projet(txDirigeant.getText());
            ministeres.setNom_firm(txTelephone.getText());
            ministeres.setMaitre_douvrage(txNo_compteBRH.getText());
            ministeres.setCout_projet(Double.parseDouble(String.valueOf(txNo_compteBNC.getText())));
            ministeres.setZone_projet(txzone.getText());
            if(ministeres.save(ministeres)==1){
        
         loadData();
         initialiser();

    }
   } else{
             if (ministeres.update(ministeres) == 1){
                initialiser();
                loadData();
       
             }
            } 
      
       ministeres.setNumero_projet(txSigle.getText());
    if(ministeres.delete(ministeres)==1){
        initialiser();
        loadData();
}
}
   
        
    

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == table) {
            btSave.setText("Modifier");
            btDelete.setEnabled(true);
            int x = table.getSelectedRow();
            txCode.setText(String.valueOf(table.getValueAt(x, 0)));
            cbCategorie.setSelectedItem(String.valueOf(table.getValueAt(x, 1)));
            txSigle.setText(String.valueOf(table.getValueAt(x, 2)));
            txAdresse.setText(String.valueOf(table.getValueAt(x, 3)));
            txTelephone.setText(String.valueOf(table.getValueAt(x, 4)));
            txDirigeant.setText(String.valueOf(table.getValueAt(x, 5)));
            txNo_compteBRH.setText(String.valueOf(table.getValueAt(x, 6)));
            txNo_compteBNC.setText(String.valueOf(table.getValueAt(x, 7)));
            txzone.setText(String.valueOf(table.getValueAt(x, 8)));
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
