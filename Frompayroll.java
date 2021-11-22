/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Frompayroll extends JPanel implements ActionListener, MouseListener{
    JPanel Img_pn = new image("images/primati.png");
     JLabel lExe = new JLabel("Exercices Fiscale");
    JLabel lMinis = new JLabel("Ministere");
    JLabel lNom = new JLabel("Nom Complet Employe");
    JLabel lNumero = new JLabel("Numero Cheque BRH");
    JLabel lMontant = new JLabel("Salaire");
    JLabel lmonth = new JLabel("Mois");
 PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;
    Ministeres Ministeres= new Ministeres();
    JTextField txExe = new JTextField(20);
    JTextField txMinis = new JTextField(20);
    JTextField txNom = new JTextField(20);
    JTextField txNumero = new JTextField(30);
    JTextField txMontant = new JTextField(30);
    
JComboBox cbCategorie = new JComboBox();
JComboBox month = new JComboBox();

    JButton btSave = new JButton("Enregistrer");
    JButton btNew = new JButton("Modifier");
    JButton btDelete = new JButton("Supprimer");

    JLabel lbtri = new JLabel("Recherche Par critere.");
    JTextField txtri = new JTextField(35);

    JTable table = new JTable();

    Payroll payro = new Payroll();
    
    public Frompayroll(){
        
    btNew.setForeground(Color.white);
         btNew.setBackground(Color.red);
          btSave.setForeground(Color.white);
          btSave.setBackground(Color.red);
          btDelete.setForeground(Color.white);
          btDelete.setBackground(Color.red);
        //creation d'une instance de GridBaglayout
         this.setLayout(new BorderLayout());
        JPanel pForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        btDelete.setEnabled(false);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 3, 3, 3);
           // pForm.setLayout(gl);
        gbc.gridx = 0;
        gbc.gridy = 0;
        pForm.add(lExe, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        pForm.add(txExe, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        pForm.add(lMinis, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
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
        
 
      
        pForm.add(lNom, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        pForm.add(txNom, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        pForm.add(lNumero, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        pForm.add(txNumero, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        pForm.add(lMontant, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        pForm.add(txMontant, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        pForm.add(lmonth, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
         pForm.add(month, gbc);
       
        month.addItem("Janvier");
         month.addItem("Fevrier");
          month.addItem("Mars");
           month.addItem("Avril");
            month.addItem("Mai");
             month.addItem("Juin");
              month.addItem("Juillet");
               month.addItem("Aout");
                month.addItem("Septembre");
                 month.addItem("Octobre");
                  month.addItem("Novembre");
                   month.addItem("Decembre");
                   
        
        //ajoutons un panel pour les boutons
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
                //System.out.println("============:"+e.getKeyChar());
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
    //    txExe.setEnabled(false);

    }

     public void loadData() {
        Object obj[][] = new Object[payro.list().size()][6];
        int compt = 0;
        for (Payroll pay : payro.list()) {
            obj[compt][0] = pay.getExercicesfiscale();
            obj[compt][1] = pay.getMinistere();
            obj[compt][2] = pay.getNomEmploye();
            obj[compt][3] = pay.getNumeroChequeBRH();
            obj[compt][4] = pay.getMontantChiffre();
           obj[compt][5] = pay.getMois();
                    
            compt++;
        }
        String header[] = {"EXERCICES FISCALE", "MINISTERE", "NOM COMPLET EMPLOYE", "NUMERO CHEQUE BRH", "SALAIRE EMP","MOIS" };
        table.setModel(new DefaultTableModel(obj, header));

    }

    public void loadDataCritere() {
       Object obj[][] = new Object[payro.list(txtri.getText()).size()][6];
        int compt = 0;
        for (Payroll pay : payro.list()) {
            obj[compt][0] = pay.getExercicesfiscale();
            obj[compt][1] = pay.getMinistere();
            obj[compt][2] = pay.getNomEmploye();
            obj[compt][3] = pay.getNumeroChequeBRH();
            obj[compt][4] = pay.getMontantChiffre();
            obj[compt][5] = pay.getMois();
            compt++;
        }
      String header[] = {"EXERCICE FISCALE", "MINISTERE", "NOM COMPLET EMPLOYE", "NUMERO CHEQUE BRH", "SALAIRE EMP","MOIS" };
        table.setModel(new DefaultTableModel(obj, header));

    }

    public void initialiser() {
        txExe.setText(null);
        txMinis.setText(null);
        txNom.setText(null);
        txNumero.setText(null);
        txMontant.setText(null);
        month.setSelectedIndex(0);
        btSave.setText("Enregistrer");
        btDelete.setEnabled(false);

    }

    ///action des boutons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btNew) {
            initialiser();
        } else if (e.getSource() == btSave) {
            
            payro.setExercicesfiscale(txExe.getText());
            payro.setMinistere(String.valueOf(cbCategorie.getSelectedItem()));;
            payro.setNomEmploye(txNom.getText());
            payro.setNumeroChequeBRH(txNumero.getText());
            payro.setMontantChiffre(Double.parseDouble(String.valueOf(txMontant.getText())));
            payro.setMois(String.valueOf(month.getSelectedItem()));;
            if(payro.save(payro)==1){
                loadData();
             initialiser();
            }
        }else{
             if (payro.update(payro) == 1) {
                initialiser();
                loadData();
       
             }
            } 
    
      payro.setNomEmploye(txMontant.getText());
    if(payro.delete(payro)==1){
        initialiser();
        loadData();
      }  
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == table) {
            btSave.setText("Modifier");
            btDelete.setEnabled(true);
            btNew.setEnabled(false);
            int x = table.getSelectedRow();
            txExe.setText(String.valueOf(table.getValueAt(x, 0)));
            txMinis.setText(String.valueOf(table.getValueAt(x, 1)));
            txNom.setText(String.valueOf(table.getValueAt(x, 2)));
            txMontant.setText(String.valueOf(table.getValueAt(x, 3)));
            txNumero.setText(String.valueOf(table.getValueAt(x, 4)));
            month.setSelectedItem(String.valueOf(table.getValueAt(x, 5)));
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
     //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    
}
