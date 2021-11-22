  package gestion;

/*import java.awt.BorderLayout;
 import javax.swing.JFrame;
 import javax.swing.JScrollPane;
 import javax.swing.JTabbedPane;
 import javax.swing.JTable;

 /**
 *
 * @author GOSSIN Esther
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Regi
 */
//public class InternalFrame extends JFrame{ 
public class NewGestionfond extends JFrame  {

    public NewGestionfond() {
        this.setTitle("Gestion des Fonds");
        this.setLayout(new BorderLayout());
        JLabel lTitle = new JLabel("Gestion des Fonds");
        JPanel pTitle = new JPanel();
        pTitle.add(lTitle);
        this.add(pTitle, BorderLayout.SOUTH);
        
       
        JPanel Img_pn = new image("images/user_64.png");
              //Creation de JTAbedpane
        final JTabbedPane tb = new JTabbedPane(JTabbedPane.TOP);
        tb.setFont(new Font("Californian fb", 14, 18));
        tb.setForeground(Color.red);
        
        // Creation d'un Jpanel
        final Fromministere p = new Fromministere();
        final GestionFondm k = new GestionFondm();
        JPanel plogin=new JPanel();
       
        tb.addTab("Login",plogin);
       
        final JButton b=new JButton("Connecter");
        final JTextField tUser=new JTextField(35);
        final JPasswordField txPwd=new JPasswordField(35);
        plogin.setBackground(Color.black);
  
         this.add(Img_pn,null);
        plogin.add(tUser);
        plogin.add(txPwd);
          plogin.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
             tb.addTab("Gestion Ministere", k); 
             tb.addTab("Gestion des Fonds",p);
             tb.addTab("Payroll des employes", new JPanel());
             tb.addTab("Gestion des projet a realisees", new JPanel());
          
        
         
             
                }
            }
          });

        this.add(tb, BorderLayout.CENTER);
        this.setSize(800,800);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
}
