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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;

/**
 *
 * @author Jaures
 */
//public class InternalFrame extends JFrame{ 
public class GestionMinisteres extends JFrame {

    JPanel Img_pn = new image("images/primati.png");
    Fromrealisation j = new Fromrealisation();
    Frompayroll l = new Frompayroll();
    GestionFondm k = new GestionFondm();
    Fromministere p = new Fromministere();

    public GestionMinisteres() {

        this.setTitle("Gestion des Ministeres");
        this.setLayout(new BorderLayout());
        JLabel lTitle = new JLabel("Gestion Ministeres");
        JPanel pTitle = new JPanel();
        pTitle.add(lTitle);
        this.add(pTitle, BorderLayout.NORTH);

        //Creation de JTAbedpane
        final JTabbedPane tb = new JTabbedPane(JTabbedPane.TOP);
        tb.setFont(new Font("Californian fb", 14, 18));
        // Creation d'un Jpanel

        JPanel plogin = new JPanel(new GridBagLayout());

        tb.addTab("Login", plogin);

        final JButton b = new JButton("Connecter");
        final JLabel user = new JLabel("USERNAME");
        final JTextField tUser = new JTextField(35);
        final JLabel mot = new JLabel("PASSWORD");
        final JPasswordField txPwd = new JPasswordField(35);
       

        // creation d'une instance de gridBagLayout
        this.setLayout(new BorderLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(4, 4, 4, 4);
        // pForm.setLayout(gl);
        gbc.gridx = 0;
        gbc.gridy = 0;
        plogin.add(user, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        plogin.add(tUser, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        plogin.add(mot, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        plogin.add(txPwd, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        plogin.add(b, gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (b.getText().equalsIgnoreCase("Connecter")) {
                    if (tUser.getText().equalsIgnoreCase("admin")
                            && txPwd.getText().equalsIgnoreCase("123")) {
                        tb.addTab("Gestion Ministere", (p));
                        tb.addTab("Gestion des Fonds", (k));
                        tb.addTab("Payroll des employes", (l));
                        tb.addTab("Gestion des projet a realisees", (j));

                        tb.setSelectedIndex(1);
                        user.setVisible(false);
                        tUser.setVisible(false);
                        mot.setVisible(false);
                        txPwd.setVisible(false);
                        b.setText("Deconnecter");
                    } else {
                        JOptionPane.showMessageDialog(null, "Le nom d'utilisateur et/ou le mot de passe est incorrect !", "Message", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    user.setVisible(true);
                    tUser.setVisible(true);
                    mot.setVisible(true);
                    txPwd.setVisible(true);
                    tUser.setText(null);
                    txPwd.setText(null);
                    b.setText("Connecter");

                    tb.remove(4);
                    tb.remove(3);
                    tb.remove(2);
                    tb.remove(1);

                }
            }
        });

        this.add(tb, BorderLayout.CENTER);
        this.setSize(800, 800);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

}
