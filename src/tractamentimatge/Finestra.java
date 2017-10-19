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

    //variables
    private JFrame jFrame;
    private Container cp;
    private JPanel pnlAdalt;
    private JPanel pnlAbaix;
    private JPanel pnlImatge1;
    private JPanel pnlImatge2;
    private JPanel pnlFons;
    private JButton btnMostrar;
    private JButton btnAclarir;
    private JButton btnEnfosquir;
    private JButton btnAclarirMillorat;
    private JButton btnEnfosquirMillorat;
    private JLabel lblImatge1;
    private JLabel lblImatge2;
    private BufferedImage imatge1;
    private BufferedImage imatge2;

    //constructor
    public Finestra() {
        this.crearFinestra();
    }

    //crea la finestra
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

    //crea panel fons
    private JPanel pnlFons() {
        this.pnlFons = new JPanel();
        this.pnlFons.setSize(500, 500);
        this.pnlFons.setLocation(0, 0);
        return this.pnlFons;
    }

    //crea pnlAdalt
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

    //crea pnlAbaix
    private JPanel pnlAbaix() {
        this.pnlAbaix = new JPanel();
        this.pnlAbaix.setSize(455, 60);
        this.pnlAbaix.setLocation(20, 390);
        this.pnlAbaix.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.LIGHT_GRAY));
        this.btnAclarir = new JButton("ACLARIR");
        this.btnEnfosquir = new JButton("ENFOSQUIR");
        this.btnAclarirMillorat = new JButton("ACLARIR.2");
        this.btnEnfosquirMillorat = new JButton("ENFOSQUIR.2");
        this.btnAclarir.addActionListener(this);
        this.btnEnfosquir.addActionListener(this);
        this.btnAclarirMillorat.addActionListener(this);
        this.btnEnfosquirMillorat.addActionListener(this);
        this.pnlAbaix.add(this.btnAclarir);
        this.pnlAbaix.add(this.btnEnfosquir);
        this.pnlAbaix.add(this.btnAclarirMillorat);
        this.pnlAbaix.add(this.btnEnfosquirMillorat);
        return this.pnlAbaix;
    }

    //crea pnlImatge1
    private JPanel pnlImatge1() {
        this.pnlImatge1 = new JPanel();
        this.pnlImatge1.setSize(205, 270);
        this.pnlImatge1.setLocation(20, 100);
        this.pnlImatge1.add(this.lblImatge1);
        return this.pnlImatge1;
    }

    //crea pnlImatge2
    private JPanel pnlImatge2() {
        this.pnlImatge2 = new JPanel();
        this.pnlImatge2.setSize(205, 270);
        this.pnlImatge2.setLocation(270, 100);
        this.pnlImatge2.add(this.lblImatge2);
        return this.pnlImatge2;
    }

    //crea lblImatge1
    private JLabel lblImatge1() {
        this.lblImatge1 = new JLabel();
        return lblImatge1;
    }

    //crea lblImatge2
    private JLabel lblImatge2() {
        this.lblImatge2 = new JLabel();
        return this.lblImatge2;
    }

    //carrega les imatges i les pinta a un label
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

    //ajusta el tamany d'una imatge al tamany d'un panel
    private Image redimensionar(BufferedImage imatge, JPanel panel) {
        Image i = imatge.getScaledInstance(panel.getWidth(), panel.getHeight(), imatge.SCALE_SMOOTH);
        return i;
    }

    //descomposa la imatge i guarda l'RGB de cada pixel a un array
    private int[][] descompondreImatge(BufferedImage imatge) {
        int[][] resultat = new int[imatge.getHeight()][imatge.getWidth()];
        for (int fil = 0; fil < imatge.getHeight(); fil++) {
            for (int col = 0; col < imatge.getWidth(); col++) {
                resultat[fil][col] = imatge.getRGB(col, fil);
            }
        }
        return resultat;
    }

    //torna a formar la imatge a partir de l'array de l'RGB dels pixels
    private void compondreImatge(int[][] array) {
        for (int fil = 0; fil < array.length; fil++) {
            for (int col = 0; col < array[0].length; col++) {
                imatge2.setRGB(col, fil, array[fil][col]);
            }
        }
    }

    //enfosqueix cada pixel reduïnt un 10% els colors del seu RGB
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

    //aclareix cada pixel augmentant un 10% els colors del seu RGB
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

    //enfosqueix cada pixel reduïnt 10 unitats proporcionalment als colors del seu RGB
    private int[][] enfosquirMillorat(int[][] array) {
        int transparencia;
        double vermell, verd, blau, sumaRGB;
        double percVermell, percVerd, percBlau;
        for (int fil = 0; fil < array.length; fil++) {
            for (int col = 0; col < array[0].length; col++) {
                transparencia = (array[fil][col] >> 24) & 0xFF;
                vermell = (array[fil][col] >> 16) & 0xFF;
                verd = (array[fil][col] >> 8) & 0xFF;
                blau = (array[fil][col]) & 0xFF;
                if (vermell == 0 & verd == 0 & blau == 0) {
                    Color color = new Color((int) vermell, (int) verd, (int) blau, (int) transparencia);
                    array[fil][col] = color.getRGB();
                } else {
                    sumaRGB = vermell + verd + blau;
                    percVermell = vermell / sumaRGB;
                    percVerd = verd / sumaRGB;
                    percBlau = blau / sumaRGB;
                    vermell -= 10 * percVermell;
                    if (vermell < 0) {
                        vermell = 0;
                    }
                    verd -= 10 * percVerd;
                    if (verd < 0) {
                        verd = 0;
                    }
                    blau -= 10 * percBlau;
                    if (blau < 0) {
                        blau = 0;
                    }
                    Color color = new Color((int) vermell, (int) verd, (int) blau, (int) transparencia);
                    array[fil][col] = color.getRGB();
                }
            }
        }
        return array;
    }

    private int[][] aclarirMillorat(int[][] array) {
        int transparencia;
        double vermell, verd, blau, sumaRGB;
        double percVermell, percVerd, percBlau;
        for (int fil = 0; fil < array.length; fil++) {
            for (int col = 0; col < array[0].length; col++) {
                transparencia = (array[fil][col] >> 24) & 0xFF;
                vermell = (array[fil][col] >> 16) & 0xFF;
                verd = (array[fil][col] >> 8) & 0xFF;
                blau = (array[fil][col]) & 0xFF;
                if (vermell == 0 & verd == 0 & blau == 0) {
                    Color color = new Color((int) vermell, (int) verd, (int) blau, (int) transparencia);
                    array[fil][col] = color.getRGB();
                } else {
                    sumaRGB = vermell + verd + blau;
                    percVermell = vermell / sumaRGB;
                    percVerd = verd / sumaRGB;
                    percBlau = blau / sumaRGB;
                    vermell += 10 * percVermell;
                    if (vermell > 255) {
                        vermell = 255;
                    }
                    verd += 10 * percVerd;
                    if (verd > 255) {
                        verd = 255;
                    }
                    blau += 10 * percBlau;
                    if (blau > 255) {
                        blau = 255;
                    }
                    Color color = new Color((int) vermell, (int) verd, (int) blau, (int) transparencia);
                    array[fil][col] = color.getRGB();
                }
            }
        }
        return array;
    }

    //events dels botons
    @Override
    public void actionPerformed(ActionEvent e) {
        //event que crida al mètode que carrega les imatges
        if (e.getSource().equals(this.btnMostrar)) {
            this.carregarImatge();
            this.btnMostrar.setText("Recarregar");
        }
        //event que crida a mètodes per enfosquir els pixels de la imatge un 10%
        if (e.getSource().equals(this.btnEnfosquir)) {
            this.compondreImatge(this.enfosquir(this.descompondreImatge(this.imatge2)));
            this.lblImatge2.setIcon(new ImageIcon(this.redimensionar(this.imatge2, this.pnlImatge2)));
        }
        //event que crida a mètodes per aclarir els pixels de la imatge un 10%
        if (e.getSource().equals(this.btnAclarir)) {
            this.compondreImatge(this.aclarir(this.descompondreImatge(this.imatge2)));
            this.lblImatge2.setIcon(new ImageIcon(this.redimensionar(this.imatge2, this.pnlImatge2)));
        }
        //event que crida a mètodes per enfosquir la imatge reduïnt proporcionalment 10 unitats els colors dels seus pixels
        if (e.getSource().equals(this.btnEnfosquirMillorat)) {
            this.compondreImatge(this.enfosquirMillorat(this.descompondreImatge(this.imatge2)));
            this.lblImatge2.setIcon(new ImageIcon(this.redimensionar(this.imatge2, this.pnlImatge2)));
        }
        //event que crida a mètodes per aclarir la imatge augmentant proporcionalment 10 unitats els colors dels seus pixels
        if (e.getSource().equals(this.btnAclarirMillorat)) {
            this.compondreImatge(this.aclarirMillorat(this.descompondreImatge(this.imatge2)));
            this.lblImatge2.setIcon(new ImageIcon(this.redimensionar(this.imatge2, this.pnlImatge2)));
        }
    }
}
