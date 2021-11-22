/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;

/**
 *
 * @author Regi
 */
class image extends JPanel {
 Image img=null;
  Image imgFiltre=null;
   public image(String S){
    Toolkit kit=Toolkit.getDefaultToolkit();
    img=kit.getImage(S);
    MediaTracker mt=new MediaTracker(this);
    imgFiltre=createImage(new FilteredImageSource(img.getSource(),new CropImageFilter(0,0,100,0)));
    mt.addImage(imgFiltre,0);
    try{
      mt.waitForID(0);
    }catch(Exception ex){
//      this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }


    setVisible(true);
    setSize(350,190);
  }

  public void paint(Graphics g){
    super.paint(g);
    g.drawImage(img,0,0,this);

  }

  public static void main(String[] args){
    image F=new image("primati.png");
    JFrame JF=new JFrame("lhkjl");
    JF.getContentPane().add(F);
    JF.pack();
    JF.show();
  }
}
    
    