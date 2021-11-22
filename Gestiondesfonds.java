 package gestion;

import Mode.Ministeres;
import dbutils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author GOSSIN Esther
 */
  public class Gestiondesfonds{
    private String exercicesfiscale;
    private String ministere;
    private double montantaloue;
    private String signatairedelareception;
    private double pourcentagebrh;
    private double pourcentagebnc;
    private float solde;
    PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;
    StringBuilder sb = null;
   
  //  private Object listfond;

    public Gestiondesfonds() {
    }

    public Gestiondesfonds(String exercicesfiscale, String ministere , double montantaloue , String signatairedelareception, double pourcentagebrh, double pourcentagebnc){
        this.exercicesfiscale =exercicesfiscale;
        this. ministere =  ministere;
        this.montantaloue = montantaloue;
        this.signatairedelareception = signatairedelareception;
         this.pourcentagebrh= pourcentagebrh;
         this.pourcentagebrh= pourcentagebrh;
    }

    public String getExercicesfiscale() {
        return exercicesfiscale;
    }

    public String getMinistere() {
        return ministere;
    }

    public double getMontantaloue() {
        return montantaloue;
    }

    public String getSignatairefonds(){
        return signatairedelareception;
    }
     public double getPourcentagebrh(){
            return pourcentagebrh;
        }
          public double getPourcentagebnc(){
            return pourcentagebnc;
        }
          
    
    //////////les setters
    public void setExercicesfiscale(String exercicesfiscale ){
        this.exercicesfiscale = exercicesfiscale;
    }
     public void setMinistere(String ministere ){
        this.ministere = ministere;
    }
      public void setMontantaloue(double montantaloue ){
        this.montantaloue = montantaloue;
    }
       public void setSignatairedelareception(String signatairedelareception ){
        this.signatairedelareception =signatairedelareception;
    }
         public void setPourcentagebrh(double pourcentagebrh){
       this.pourcentagebrh=pourcentagebrh;
       }
         public void setPourcentagebnc(double pourcentagebnc){
       this.pourcentagebnc=pourcentagebnc;
       }
           
       public boolean validFond(){
        boolean rest = false;  
        if(exercicesfiscale== null || exercicesfiscale.isEmpty()){
            rest=false;
       JOptionPane.showMessageDialog(null, "veuiller saisir une annee ficale valide", "Message", JOptionPane.ERROR_MESSAGE);
       }else if( String.valueOf(montantaloue) == null || String.valueOf(montantaloue).isEmpty()){
           rest=false;
           JOptionPane.showMessageDialog(null, "Le champs Montant doit etre remplir", "Message", JOptionPane.ERROR_MESSAGE);
       }else if (signatairedelareception==null || signatairedelareception.isEmpty()){
           rest = false;
         JOptionPane.showMessageDialog(null," Veuillez remplir le champs Signataire", "Message", JOptionPane.ERROR_MESSAGE);
       } else if (String.valueOf(solde) == null || String.valueOf(solde).isEmpty()){
           rest = false;
           
         JOptionPane.showConfirmDialog(null, "Veuiller entrer le Montant", "Message", JOptionPane.ERROR_MESSAGE);
     
       }else if(getMontantaloue()<=45000){
          setPourcentagebnc(getMontantaloue());
          setPourcentagebrh(0);
          rest=true;
      }else{ 
           setPourcentagebrh(getMontantaloue()*0.85);
          setPourcentagebnc(getMontantaloue()-getPourcentagebrh()); 
                  rest=true;
          
     }
       
     return rest; 
       }
       
     
       
        public int update(Gestiondesfonds a) {
        int b = 0;
        if (validFond()) {
            sb = new StringBuilder();
            sb.append(" UPDATE gestiondesfonds SET ");
            sb.append("exercicesfiscale=?" + ",");
            sb.append("ministere=?" + ",");
            sb.append("montant=?" + ",");
            sb.append("signataire=?");
            sb.append("% alloue BRH=?" + ",");
            sb.append("% alloue BNC=?");
            sb.append(" WHERE signataire=?");
            con = DBConnection.getConnection();
            try {
                st = con.prepareStatement(sb.toString());
                st.setString(1, a.getExercicesfiscale());
                st.setString(2, a.getMinistere());
                st.setDouble(3, a.getMontantaloue());
                st.setString(4, a.getSignatairefonds());
                st.setDouble(4, a.getPourcentagebrh());
                st.setDouble(5, a.getPourcentagebnc());
                int rep = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment modifier cet Fond ?", "Confirmation ", JOptionPane.YES_NO_OPTION);
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
    
       
       public int delete(Gestiondesfonds a) {
        int b = 0;
        if (a.getSignatairefonds()!= null && !a.getSignatairefonds().isEmpty()) {
            sb = new StringBuilder();
            sb.append(" DELETE FROM gestiondesfonds ");
            sb.append(" WHERE SIGNATAIRE=?");
            con = DBConnection.getConnection();
            try {
                st = con.prepareStatement(sb.toString());
                st.setString(1, a.getSignatairefonds());
                int rep = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce Fond ?", "Confirmation ", JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION) {
                    b = st.executeUpdate();
                    if (b == 1) {
                        JOptionPane.showMessageDialog(null, "suppresion effectue avec succes !", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(null, "Impossible de supprimer les informations saisies !", "Message", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) { System.out.println("");
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
      
    

   public  ArrayList<Gestiondesfonds> list() {
        ArrayList<Gestiondesfonds> listgestionfonds = new ArrayList<Gestiondesfonds>();
        String req = " SELECT * FROM gestiondesfonds ";
        con= DBConnection.getConnection();
        try {
            st = con.prepareStatement(req);
            rs = st.executeQuery();
            while (rs.next()) {
                Gestiondesfonds fond = new Gestiondesfonds();
                fond.setExercicesfiscale(rs.getString("exercicesfiscale"));
                fond.setMinistere(rs.getString("ministere"));
                fond.setMontantaloue(rs.getDouble("montant"));
                fond.setSignatairedelareception(rs.getString("signataire"));
                fond.setPourcentagebrh(rs.getDouble("% alloue BRH"));
                fond.setPourcentagebnc(rs.getDouble("% alloue BNC"));
                listgestionfonds.add(fond);
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
        return listgestionfonds;

    }
   
public int enr(Gestiondesfonds a){
    int t = 0;
   if (validFond()) {
            sb = new StringBuilder();
            sb.append(" INSERT INTO gestiondesfonds VALUES( ");
            sb.append("?" + ",");
            sb.append("?" + ",");
            sb.append("?" + ",");
            sb.append("?" + ",");
            sb.append("?" + ",");
            sb.append("?" + ")");
            con = DBConnection.getConnection();
             try {
                st = con.prepareStatement(sb.toString());
                st.setString(1,a.getExercicesfiscale());
                st.setString(2, a.getMinistere());
                st.setDouble(3, a.getMontantaloue());
                st.setString(4, a.getSignatairefonds());
                st.setDouble(5, a.getPourcentagebrh());
                st.setDouble(6, a.getPourcentagebnc());
                t = st.executeUpdate();
                if (t == 1) {
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
        return t;
    
}
   public void recuperer(String id, Double montant) {
       
     int n =0;
     sb = new StringBuilder();
     sb.append(" UPDATE gestiondesfonds SET");
     sb.append(" % alloue BNC=% alloue BNC - ?");
     sb.append(" WHERE gestiondesfonds.ministere=payroll.ministere;");
     
   }

}
  

