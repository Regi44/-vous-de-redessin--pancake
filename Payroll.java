package gestion;

import dbutils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

/**
 *
 * @author Regi
 */
public class Payroll {

    private String exercicesfiscale;
    private String ministere;
    private String nomEmloye;
    private String numeroChequeBRH;
    private Double montantChiffre;
    private String mois;
    PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;
    StringBuilder sb = null;

    public Payroll() {
    }
     public Payroll(String exercicesfiscale, String ministere, String nomEmloye, String numeroChequeBRH,Double montantChiffre,String mois) {
        this.exercicesfiscale = exercicesfiscale;
        this.ministere = ministere;
        this.nomEmloye = nomEmloye;
        this.numeroChequeBRH = numeroChequeBRH;
        this.montantChiffre = montantChiffre;
        this.mois= mois;
    }
public String getExercicesfiscale(){
    return exercicesfiscale;
}
public String getMinistere(){
    return ministere;
}

public String getNomEmploye(){
    return nomEmloye;
}
public String getNumeroChequeBRH(){
    return numeroChequeBRH;
}
public Double getMontantChiffre(){
    return montantChiffre;
}
public String getMois(){
    return mois;
}
 
 public void setExercicesfiscale(String exercicesfiscale) {
        this.exercicesfiscale = exercicesfiscale;
    }
 public void setMinistere(String ministere) {
        this.ministere = ministere;
    }
 public void setNomEmploye(String nomEmploye) {
        this.nomEmloye = nomEmploye;
    }
 public void setNumeroChequeBRH(String numeroChequeBRH) {
        this.numeroChequeBRH = numeroChequeBRH;
    }
 public void setMontantChiffre(Double montantChiffre) {
        this.montantChiffre = montantChiffre;
    }
 
 
 public void setMois(String mois){
     this.mois= mois;
 }
 
  public boolean validForm(){
     boolean res=false;
     if(nomEmloye==null || nomEmloye.isEmpty()){
         res = false;
         JOptionPane.showMessageDialog(null,"Il faut saisir le nom complet de l'employe","Message", JOptionPane.ERROR_MESSAGE);  
     } else if(numeroChequeBRH==null || numeroChequeBRH.isEmpty()){
         res=false;
         JOptionPane.showMessageDialog(null,"Il faut Saisir le numero du cheque", "Message", JOptionPane.ERROR_MESSAGE);
     }else if(montantChiffre==0 ){
           res = false;
         JOptionPane.showMessageDialog(null,"le salaire doit etre superieur a 0", "Message", JOptionPane.ERROR_MESSAGE);

     }else{
         res=true;
     }
     return res;
   }
    
    public int save(Payroll a){
        Frompayroll fr= new Frompayroll();
    int b = 0;
   if (validForm()) {
            sb = new StringBuilder();
            sb.append(" INSERT INTO payroll VALUES(");
            sb.append("?" + ",");
            sb.append("?" + ",");
            sb.append("?" + ",");
            sb.append("?" + ",");
            sb.append("?" + ",");
            sb.append("?" + ")");
      
            con = DBConnection.getConnection();
             try {
                st = con.prepareStatement(sb.toString());
                st.setString(1, a.getExercicesfiscale());
                st.setString(2, a.getMinistere());
                st.setString(3, a.getNomEmploye());
                st.setString(4, a.getNumeroChequeBRH());
                st.setDouble(5, a.getMontantChiffre());
                st.setString(6, a.getMois());
              
                b = st.executeUpdate();
                if (b == 1) {
                    JOptionPane.showMessageDialog(null, "Enregistrement effectue avec succes !", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(null, "Impossible d'eregistrer les informations saisies !", "Message", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        try {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
        }
        return b;
    
    }

    public ArrayList<Payroll> list() {
        ArrayList<Payroll> listPayrol = new ArrayList<Payroll>();
        String req = " SELECT * FROM payroll ";
        con = DBConnection.getConnection();
        try {
            st = con.prepareStatement(req);
            rs = st.executeQuery();
            while (rs.next()) {
               Payroll pay = new Payroll();
               pay.setExercicesfiscale(rs.getString("exercicesfiscale"));
               pay.setMinistere(rs.getString("ministere"));
               pay.setNomEmploye(rs.getString("nom_employe"));
               pay.setNumeroChequeBRH(rs.getString("numero_cheque"));
               pay.setMontantChiffre(rs.getDouble("montant_chiffre"));
               pay.setMois(rs.getString("mois"));
          
                
                listPayrol.add(pay);
            }
        } catch (SQLException ex) {

        }
        try {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
        }
        return listPayrol;

    }
    
    
    public int update(Payroll a) {
        int b = 0;
        if (validForm()) {
            sb = new StringBuilder();
            sb.append(" UPDATE payroll SET ");
            sb.append("exercicesfiscale=?" + ",");
            sb.append("ministere=?" + ",");
            sb.append("nom_employe=?" + ",");
            sb.append("numero_cheque=?");
            sb.append("montant_chiffre=?" + ",");
            sb.append("mois=?" + ",");
           
            con = DBConnection.getConnection();
            try {
                st = con.prepareStatement(sb.toString());
                st.setString(1, a.getExercicesfiscale());
                st.setString(2, a.getMinistere());
                st.setString(3, a.getNomEmploye());
                st.setString(4, a.getNumeroChequeBRH());
                st.setDouble(5, a.getMontantChiffre());
                int rep = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment modifier ce Payroll ?", "Confirmation ", JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION) {
                    b = st.executeUpdate();
                    if (b == 1) {
                        JOptionPane.showMessageDialog(null, "Modification effectue avec succes !", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(null, "Impossible de modifier les informations saisies !", "Message", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
            }
        }
        try {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
        }
        return b;
    }
 public int delete(Payroll a) {
        int b = 0;
        if (a.getNomEmploye() != null && !a.getNomEmploye().isEmpty()) {
            sb = new StringBuilder();
            sb.append(" DELETE FROM payroll ");
            sb.append(" WHERE nom_employe=?");
            con = DBConnection.getConnection();
            try {
                st = con.prepareStatement(sb.toString());
                st.setString(1, a.getNomEmploye());
                int rep = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer le Payroll de cet employe ?", "Confirmation ", JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION) {
                    b = st.executeUpdate();
                    if (b == 1) {
                        JOptionPane.showMessageDialog(null, "suppresion effectue avec succes !", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(null, "Impossible de supprimer les informations saisies !", "Message", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
            }
        }
        try {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
        }
        return b;
    }

    public ArrayList<Payroll> list(String critere) {
        ArrayList<Payroll> a = new ArrayList<Payroll>();
        sb = new StringBuilder();
        sb.append("  SELECT * FROM Payroll ");
        sb.append("  WHERE exercicesfiscale like ? ");
        sb.append("  OR ministere like ? ");
        sb.append("  OR nom_employe like ? ");
        sb.append("  OR numero_cheque like ? ");
        sb.append("  OR montant_chiffre like ? ");
        sb.append("  OR mois like ? ");


        try {
            con = DBConnection.getConnection();
            st = con.prepareStatement(sb.toString());
            st.setString(1, "%" + critere + "%");
            st.setString(2, "%" + critere + "%");
            st.setString(3, "%" + critere + "%");
            st.setString(4, "%" + critere + "%");
            st.setString(5, "%" + critere + "%");
            rs = st.executeQuery();
            while (rs.next()) {
                Payroll pay = new Payroll();
                pay.setExercicesfiscale(rs.getString("exercicesfiscale"));
                pay.setMinistere(rs.getString("ministere"));
                pay.setNomEmploye(rs.getString("nom_employe"));
                pay.setNumeroChequeBRH(rs.getString("numero_cheque"));
                pay.setMontantChiffre(rs.getDouble("montant_chiffre"));
                pay.setMois(rs.getString("mois"));
                a.add(pay);
            }
        } catch (SQLException ex) {

        }
        try {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
        }
        return a;
        
     }
    
    
   
}

      