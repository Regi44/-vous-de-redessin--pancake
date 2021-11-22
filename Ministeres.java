package Mode;
import gestion.GestionFondm;
import dbutils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author GOSSIN Esther
 */
public class Ministeres {
    private String code;
    private String nom;
    private String sigle;
    private String adresse;
    private String telephone;
    private String dirigeant;
    private String no_compteBRH;
    private String no_compteBNC;
    PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;
    StringBuilder sb = null;
    public Ministeres() {
    }

    public Ministeres(String code, String nom, String sigle, String adresse, String telephone, String dirigeant, String no_compteBRH, String no_compteBNC) {
        this.code = code;
        this.nom = nom;
        this.sigle = sigle;
        this.adresse = adresse;
        this.telephone = telephone;
        this.dirigeant = dirigeant;
        this.no_compteBRH = no_compteBRH;
        this.no_compteBNC = no_compteBNC;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getSigle() {
        return sigle;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getDirigeant() {
        return dirigeant;
    }

    public String getNo_compteBRH() {
        return no_compteBRH;
    }

    public String getNo_compteBNC() {
        return no_compteBNC;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setDirigeant(String dirigeant) {
        this.dirigeant = dirigeant;
    }

    public void setNo_compteBRH(String no_compteBRH) {
        this.no_compteBRH = no_compteBRH;
    }

    public void setNo_compteBNC(String no_compteBNC) { 
        this.no_compteBNC = no_compteBNC;
    }
    
    
    public boolean validForm(){
     boolean res=false;
     if(nom==null || nom.isEmpty()){
         res = false;
         JOptionPane.showMessageDialog(null,"Il faut saisir le nom du ministre","Message", JOptionPane.ERROR_MESSAGE);  
     } else if(sigle==null){
         res=false;
         JOptionPane.showMessageDialog(null,"Il faut Saisir le Sigle du ministere", "Message", JOptionPane.ERROR_MESSAGE);
     }else if(adresse==null || adresse.isEmpty()){
           res = false;
         JOptionPane.showMessageDialog(null,"Il faut saisir l'adresse du ministere", "Message", JOptionPane.ERROR_MESSAGE);
     } else if (telephone==null || telephone.isEmpty()){
           res = false;
         JOptionPane.showMessageDialog(null,"Il faut saisir le telephone du ministere", "Message", JOptionPane.ERROR_MESSAGE);
     } else if (dirigeant==null || dirigeant.isEmpty()){
           res = false;
         JOptionPane.showMessageDialog(null,"Il faut saisir le nom du dirigant du ministere", "Message", JOptionPane.ERROR_MESSAGE);
     } else if (adresse==null || adresse.isEmpty()){
           res = false;
         JOptionPane.showMessageDialog(null,"Il faut saisir le numero du compte du ministere", "Message", JOptionPane.ERROR_MESSAGE);
     }else{
         res=true;
     }
     return res;
   }
    
      private String GenerCode(){
   return "CM-00" +(int)(10*(Math.random()+1)); 
   }
      
       private String GenerBRH(){
   return "00" +(int)(10000*(Math.random()+1)); 
   }
       
        private String GenerBN(){
   return "10" +(int)(100000*(Math.random()+1)); 
   }
    
    public int save(Ministeres a){
    int b = 0;
   if (validForm()) {
            sb = new StringBuilder();
            sb.append(" INSERT INTO ministre VALUES( ");
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
             //  st.setString(1, null);
                st.setString(1, a. GenerCode());
                st.setString(2, a.getNom());
                st.setString(3, a.getSigle());
                st.setString(4, a.getAdresse());
                st.setString(5, a.getTelephone());
                st.setString(6, a.getDirigeant());
                st.setString(7, a.getNo_compteBRH());
                st.setString(8, a.getNo_compteBNC());
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

    public ArrayList<Ministeres> list() {
        ArrayList<Ministeres> listMinist = new ArrayList<Ministeres>();
        String req = " SELECT * FROM ministre ";
        con = DBConnection.getConnection();
        try {
            st = con.prepareStatement(req);
            rs = st.executeQuery();
            while (rs.next()) {
               Ministeres minist = new Ministeres();
               minist.setCode(rs.getString("code"));
               minist.setNom(rs.getString("nom"));
               minist.setSigle(rs.getString("sigle"));
               minist.setAdresse(rs.getString("adresse"));
               minist.setTelephone(rs.getString("telephone"));
               minist.setDirigeant(rs.getString("dirigeant"));
               minist.setNo_compteBRH(rs.getString("no_compteBRH"));
               minist.setNo_compteBNC(rs.getString("no_compteBNC"));
                
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
   
    
    
    public int update(Ministeres a) {
        int b = 0;
        if (validForm()) {
            sb = new StringBuilder();
            sb.append(" UPDATE ministre SET ");
            sb.append("Nom=?" + ",");
            sb.append("Sigle=?" + ",");
            sb.append("Adresse=?" + ",");
            sb.append("Telephone=?");
            sb.append("Dirigeant=?" + ",");
            sb.append("No_compteBRH=?" + ",");
            sb.append("No_compteBNC=?");
            sb.append(" WHERE Code=?");
            con = DBConnection.getConnection();
            try {
                st = con.prepareStatement(sb.toString());
                st.setString(1, a.getNom());
                st.setString(2, a.getSigle());
                st.setString(3, a.getAdresse());
                st.setString(4, a.getTelephone());
                st.setString(5, a.getDirigeant());
                st.setString(4, a.getNo_compteBRH());
                st.setString(5, a.getNo_compteBNC());
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
    
    
    
 public int delete(Ministeres a) {
        int b = 0;
        if (a.getCode() != null && !a.getCode().isEmpty()) {
            sb = new StringBuilder();
            sb.append(" DELETE FROM ministre ");
            sb.append(" WHERE Code=?");
            con = DBConnection.getConnection();
            try {
                st = con.prepareStatement(sb.toString());
                st.setString(1, a.getCode());
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

    public ArrayList<Ministeres> list(String critere) {
        ArrayList<Ministeres> a = new ArrayList<Ministeres>();
        sb = new StringBuilder();
        sb.append("  SELECT * FROM ministre ");
        sb.append("  WHERE Code like ? ");
        sb.append("  OR NOM like ? ");
        sb.append("  OR Sigle like ? ");
        sb.append("  OR Adresse like ? ");
        sb.append("  OR Telephone like ? ");
        sb.append("  OR Dirigeant like ? ");
        sb.append("  OR N0_compteBRH like ? ");
         sb.append("  OR N0_compteBNC like ? ");
        
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
                Ministeres Minist = new Ministeres();
                Minist.setCode(rs.getString("code"));
                Minist.setNom(rs.getString("Nom"));
                Minist.setSigle(rs.getString("sigle"));
                Minist.setAdresse(rs.getString("adresse"));
                Minist.setTelephone(rs.getString("Telephone"));
                 Minist.setDirigeant(rs.getString("Dirigeant"));
                  Minist.setNo_compteBRH(rs.getString("No_compteBRH"));
                   Minist.setNo_compteBNC(rs.getString("No_compteBNC"));
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
