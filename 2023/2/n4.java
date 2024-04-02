import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NapacnaVrednost extends RuntimeException {
  @Override
  public String getMessage() {
    return "Vpisana je bila napačne vrednost";
  }
}

public class n4 {
  
  static int fakulteta(int n) {
    if (n<=1) return 1;
    return n*fakulteta(n-1);
  }
  
  public static void main(String[] args) {
    JFrame okno = new JFrame("Računanje fakultete");
    okno.setLayout(new FlowLayout(FlowLayout.LEFT));
    
    JLabel napis = new JLabel("n = ");
    final JTextField nTF = new JTextField(12);
    JButton gumb = new JButton("Izračunaj n!");
    final JTextField fakultetaTF = new JTextField(12);
     
    gumb.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int stevilo;
        try {
          stevilo = Integer.parseInt(nTF.getText());
          fakultetaTF.setText(fakulteta(stevilo)+"");
        } catch (Exception ex) {
          throw new NapacnaVrednost();
        }
      }
    });
    
    okno.add(napis);okno.add(nTF);
    okno.add(gumb);okno.add(fakultetaTF);
    
    okno.pack();
    okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    okno.setVisible(true);
  }
}