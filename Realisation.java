/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import dbutils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Regi
 */
public class Realisation {
     private String exercicesfiscale;
    private String ministere;
    private String numero_projet;
    private String type_projet;
    private String desc_projet;
    private String nom_firm;
    private String maitre_douvrage;
    private double cout_projet;
    private String zone_projet;
    PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;
    StringBuilder sb = null;
    
    
    
    public Realisation(){
    }
    public Realisation(String exercicesfiscale, String ministere, String numero_projet, String type_projet, String desc_projet, String nom_firm, String maitre_douvrage, double cout_projet, String zone_projet){
     this.exercicesfiscale=exercicesfiscale;
    this.ministere=ministere;
    this.numero_projet=numero_projet;
    this.type_projet=type_projet;
    this.desc_projet=desc_projet;
    this.nom_firm=nom_firm;
    this.maitre_douvrage=maitre_douvrage;
    this.cout_projet=cout_projet;
    this.zone_projet=zone_projet;
    }
    public String getExercicesfiscale(){
    return exercicesfiscale;
}
    public String getMinistere(){
        return ministere;
    }
    public String getNumero_projet(){
        return numero_projet;
    }
    public String getType_projet(){
        return type_projet;
    }
    public String getDesc_projet(){
        return desc_projet;
    }
    public String getNom_firm(){
        return nom_firm;
    }
    public String getMaitre_douvrage(){
        return maitre_douvrage;
    }
    public double getCout_projet(){
        return cout_projet;
    }
    public String getZone_projet(){
        return zone_projet;
    }
       public void setExercicesfiscale(String exercicesfiscale){
    this.exercicesfiscale=exercicesfiscale;
}
    public void setMinistere(String ministere){
        this.ministere= ministere;
    }
    public void setNumero_projet(String numero_projet){
        this.numero_projet=numero_projet;
    }
    public void setType_projet(String type_projet){
       this.type_projet=type_projet;
    }
    public void setDesc_projet(String desc_projet){
        this.desc_projet= desc_projet;
    }
    public void setNom_firm(String nom_firm){
        this.nom_firm=nom_firm;
    }
    public void setMaitre_douvrage(String maitre_douvrage){
        this.maitre_douvrage=maitre_douvrage;
    }
    public void setCout_projet(double cout_projet){
        this.cout_projet=cout_projet;
    }
    public void setZone_projet(String zone_projet){
        this.zone_projet=zone_projet;
    }
     public boolean validForm(){
     boolean res=false;
     if(zone_projet==null || zone_projet.isEmpty()){
         res = false;
         JOptionPane.showMessageDialog(null,"Il faut saisir le zone du projet","Message", JOptionPane.ERROR_MESSAGE);  
     } else if(nom_firm==null){
         res=false;
         JOptionPane.showMessageDialog(null,"Il faut Saisir le nom du firm", "Message", JOptionPane.ERROR_MESSAGE);
     }else if(desc_projet==null || desc_projet.isEmpty()){
           res = false;
         JOptionPane.showMessageDialog(null,"Il faut saisir la description du projet", "Message", JOptionPane.ERROR_MESSAGE);
     } else if (nom_firm==null || nom_firm.isEmpty()){
           res = false;
         JOptionPane.showMessageDialog(null,"Il faut saisir le telephone du ministere", "Message", JOptionPane.ERROR_MESSAGE);
     } else if (maitre_douvrage==null|| maitre_douvrage.isEmpty()){
           res = false;
         JOptionPane.showMessageDialog(null,"Il faut saisir le maitre d'ouvrage", "Message", JOptionPane.ERROR_MESSAGE);
     }
     else{
         res=true;
     }
     return res;
   }
    
    public int save(Realisation a){
    int b = 0;
   if (validForm()) {
            sb = new StringBuilder();
            sb.append(" INSERT INTO realisation VALUES( ");
            sb.append("?" + ",");
            sb.append("?" + ",");
            sb.append("?" + ",");
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
                st.setString(3, null);
                st.setString(4, a.getType_projet());
                st.setString(5, a.getDesc_projet());
                st.setString(6, a.getNom_firm());
                st.setString(7, a.getMaitre_douvrage());
                st.setDouble(8, a.getCout_projet());
                st.setString(9, a.getZone_projet());
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

    public ArrayList<Realisation> list() {
        ArrayList<Realisation> listMinist = new ArrayList<Realisation>();
        String req = " SELECT * FROM realisation ";
        con = DBConnection.getConnection();
        try {
            st = con.prepareStatement(req);
            rs = st.executeQuery();
            while (rs.next()) {
               Realisation minist = new Realisation();
               minist.setExercicesfiscale(rs.getString("exercicesfiscale"));
               minist.setMinistere(rs.getString("ministere"));
               minist.setNumero_projet(rs.getString("numero_projet"));
               minist.setType_projet(rs.getString("type_projet"));
               minist.setDesc_projet(rs.getString("desc_projet"));
               minist.setNom_firm(rs.getString("nom_firm"));
               minist.setMaitre_douvrage(rs.getString("maitre_douvrage"));
               minist.setCout_projet(rs.getDouble("cout_projet"));
               minist.setZone_projet(rs.getString("zone_projet"));
                
                listMinist.add(minist);
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
        return listMinist;

    }
    
    
    public int update(Realisation a) {
        int b = 0;
        if (validForm()) {
            sb = new StringBuilder();
            sb.append(" UPDATE realisation SET ");
            sb.append("Exercicesfiscale=?" + ",");
            sb.append("Ministere=?" + ",");
            sb.append("Numero_projet=?" + ",");
            sb.append("Type_projet=?" + ",");
            sb.append("Desc_projet=?");
            sb.append("Nom_firm=?" + ",");
            sb.append("Maitre_douvrage=?" + ",");
            sb.append("Cout_projet=?" + ",");
            sb.append("Zone_projet=?");
            sb.append(" WHERE Numero_projet=?");
            con = DBConnection.getConnection();
            try {
                st = con.prepareStatement(sb.toString());
                st.setString(1, a.getMinistere());
                
                st.setString(3, a.getType_projet());
                st.setString(4, a.getDesc_projet());
                st.setString(5, a.getNom_firm());
                st.setString(4, a.getMaitre_douvrage());
                st.setDouble(5, a.getCout_projet());
                st.setString(5, a.getZone_projet());
                int rep = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment modifier ce Ministeres ?", "Confirmation ", JOptionPane.YES_NO_OPTION);
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
 public int delete(Realisation a) {
        int b = 0;
        if (a.getNumero_projet() != null && !a.getNumero_projet().isEmpty()) {
            sb = new StringBuilder();
            sb.append(" DELETE FROM realisation ");
            sb.append(" WHERE Numero_projet=?");
            con = DBConnection.getConnection();
            try {
                st = con.prepareStatement(sb.toString());
                st.setString(2, a.getNumero_projet());
                int rep = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce Ministere ?", "Confirmation ", JOptionPane.YES_NO_OPTION);
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

    public ArrayList<Realisation> list(String critere) {
        ArrayList<Realisation> a = new ArrayList<Realisation>();
        sb = new StringBuilder();
        sb.append("  SELECT * FROM realisation ");
        sb.append("  WHERE Exercices_fiscale like ? ");
        sb.append("  OR Ministere like ? ");
        sb.append("  OR Numero_projet like ? ");
        sb.append("  OR Type_projet like ? ");
        sb.append("  OR Desc_projet like ? ");
        sb.append("  OR Nom_firm like ? ");
        sb.append("  OR Maitre_douvrage like ? ");
         sb.append("  OR Zone_projet like ? ");
        
        try {
            con = DBConnection.getConnection();
            st = con.prepareStatement(sb.toString());
            st.setString(1, "%" + critere + "%");
            st.setString(2, "%" + critere + "%");
            st.setString(3, "%" + critere + "%");
            st.setString(4, "%" + critere + "%");
            st.setString(5, "%" + critere + "%");
            st.setString(6, "%" + critere + "%");
            st.setString(7, "%" + critere + "%");
            st.setString(7, "%" + critere + "%");
            rs = st.executeQuery();
            while (rs.next()) {
                Realisation Minist = new Realisation();
                Minist.setExercicesfiscale(rs.getString("exercicesfiscale"));
                Minist.setMinistere(rs.getString("ministere"));
                Minist.setNumero_projet(rs.getString("numero_projet"));
                Minist.setType_projet(rs.getString("numero_projet"));
                Minist.setDesc_projet(rs.getString("desc_projet"));
                 Minist.setNom_firm(rs.getString("nom_firm"));
                Minist.setMaitre_douvrage(rs.getString("maitre_douvrage"));
                Minist.setCout_projet(rs.getDouble("cout_projet"));
               Minist.setZone_projet(rs.getString("zone_projet"));
               
                a.add(Minist);
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


