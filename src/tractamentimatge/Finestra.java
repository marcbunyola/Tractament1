package tractamentimatge;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marcbunyola
 */
public class Finestra extends JFrame implements ActionListener {

    private JFrame jFrame;
    private Container cp;
    private JPanel pnlAdalt;
    private JPanel pnlAbaix;
    private JPanel pnlImatge1;
    private JPanel pnlImatge2;
    private JPanel pnlFons;
    private JButton btnMostrar;
    private JButton btnAclarir;
    private JButton btnOscurecer;
    private JLabel lblImatge1;
    private JLabel lblImatge2;
    private BufferedImage imatge1;
    private BufferedImage imatge2;

    public Finestra() {
        this.crearFinestra();
    }

    private void crearFinestra() {
        this.jFrame = new javax.swing.JFrame("Tractament d'imatges");
        this.jFrame.setResizable(false);
        this.jFrame.setSize(500, 500);
        this.jFrame.setLocationRelativeTo(null);
        this.jFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.cp = jFrame.getContentPane();
        this.cp.add(this.lblImatge1());
        this.cp.add(this.lblImatge2());
        this.cp.add(this.pnlAdalt());
        this.cp.add(this.pnlAbaix());
        this.cp.add(this.pnlImatge1());
        this.cp.add(this.pnlImatge2());
        this.cp.add(this.pnlFons());
        this.jFrame.setVisible(true);
    }

    private JPanel pnlAdalt() {
        this.pnlAdalt = new JPanel();
        this.pnlAdalt.setSize(455, 60);
        this.pnlAdalt.setLocation(20, 20);
        this.pnlAdalt.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.LIGHT_GRAY));
        this.btnMostrar = new JButton("MOSTRAR");
        this.btnMostrar.addActionListener(this);
        this.pnlAdalt.add(this.btnMostrar);
        return this.pnlAdalt;
    }

    private JPanel pnlFons() {
        this.pnlFons = new JPanel();
        this.pnlFons.setSize(500, 500);
        this.pnlFons.setLocation(0, 0);
        return this.pnlFons;
    }

    private JPanel pnlAbaix() {
        this.pnlAbaix = new JPanel();
        this.pnlAbaix.setSize(455, 60);
        this.pnlAbaix.setLocation(20, 390);
        this.pnlAbaix.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.LIGHT_GRAY));
        this.btnAclarir = new JButton("ACLARIR");
        this.btnOscurecer = new JButton("ENFOSQUIR");
        this.btnAclarir.addActionListener(this);
        this.btnOscurecer.addActionListener(this);
        this.pnlAbaix.add(this.btnAclarir);
        this.pnlAbaix.add(this.btnOscurecer);
        return this.pnlAbaix;
    }

    private JPanel pnlImatge1() {
        this.pnlImatge1 = new JPanel();
        this.pnlImatge1.setSize(205, 270);
        this.pnlImatge1.setLocation(20, 100);
        this.pnlImatge1.add(this.lblImatge1);
        return this.pnlImatge1;
    }

    private JPanel pnlImatge2() {
        this.pnlImatge2 = new JPanel();
        this.pnlImatge2.setSize(205, 270);
        this.pnlImatge2.setLocation(270, 100);
        this.pnlImatge2.add(this.lblImatge2);
        return this.pnlImatge2;
    }

    private JLabel lblImatge1() {
        this.lblImatge1 = new JLabel();
        return lblImatge1;
    }

    private JLabel lblImatge2() {
        this.lblImatge2 = new JLabel();
        return this.lblImatge2;
    }

    private void carregarImatge() {
        try {
            this.imatge1 = ImageIO.read(new File("C:\\DAM2\\perro.jpg"));
            this.imatge2 = ImageIO.read(new File("C:\\DAM2\\perro.jpg"));
            this.lblImatge1.setIcon(new ImageIcon(this.redimensionar(imatge1, pnlImatge1)));
            this.lblImatge2.setIcon(new ImageIcon(this.redimensionar(imatge2, pnlImatge2)));

        } catch (IOException ex) {
            Logger.getLogger(Finestra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Image redimensionar(BufferedImage imatge, JPanel panel) {
        Image i = imatge.getScaledInstance(panel.getWidth(), panel.getHeight(), imatge.SCALE_SMOOTH);
        return i;
    }

    private int[][] descompondreImatge(BufferedImage imatge) {
        int[][] resultat = new int[imatge.getHeight()][imatge.getWidth()];
        for (int fil = 0; fil < imatge.getHeight(); fil++) {
            for (int col = 0; col < imatge.getWidth(); col++) {
                resultat[fil][col] = imatge.getRGB(col, fil);
            }
        }
        return resultat;
    }

    private void compondreImatge(int[][] array) {
        for (int fil = 0; fil < array.length; fil++) {
            for (int col = 0; col < array[0].length; col++) {
                imatge2.setRGB(col, fil, array[fil][col]);
            }
        }
    }

    private int[][] enfosquir(int[][] array) {
        int transparencia, vermell, verd, blau;
        for (int fil = 0; fil < array.length; fil++) {
            for (int col = 0; col < array[0].length; col++) {
                transparencia = (array[fil][col] >> 24) & 0xFF;
                vermell = (array[fil][col] >> 16) & 0xFF;
                verd = (array[fil][col] >> 8) & 0xFF;
                blau = (array[fil][col]) & 0xFF;
                vermell *= 0.9;
                verd *= 0.9;
                blau *= 0.9;
                Color color = new Color(vermell, verd, blau, transparencia);
                array[fil][col] = color.getRGB();
            }
        }
        return array;
    }

    private int[][] aclarir(int[][] array) {
        int transparencia, vermell, verd, blau;
        for (int fil = 0; fil < array.length; fil++) {
            for (int col = 0; col < array[0].length; col++) {
                transparencia = (array[fil][col] >> 24) & 0xFF;
                vermell = (array[fil][col] >> 16) & 0xFF;
                verd = (array[fil][col] >> 8) & 0xFF;
                blau = (array[fil][col]) & 0xFF;
                vermell *= 1.1;
                if (vermell > 255) {
                    vermell = 255;
                }
                verd *= 1.1;
                if (verd > 255) {
                    verd = 255;
                }
                blau *= 1.1;
                if (blau > 255) {
                    blau = 255;
                }
                Color color = new Color(vermell, verd, blau, transparencia);
                array[fil][col] = color.getRGB();
            }
        }
        return array;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.btnMostrar)) {
            this.carregarImatge();
            this.btnMostrar.setVisible(false);
        }
        if (e.getSource().equals(this.btnOscurecer)) {
            this.compondreImatge(this.enfosquir(this.descompondreImatge(this.imatge2)));
            this.lblImatge2.setIcon(new ImageIcon(this.redimensionar(this.imatge2, this.pnlImatge2)));
        }
        if (e.getSource().equals(this.btnAclarir)) {
            this.compondreImatge(this.aclarir(this.descompondreImatge(this.imatge2)));
            this.lblImatge2.setIcon(new ImageIcon(this.redimensionar(this.imatge2, this.pnlImatge2)));
        }
    }
}
