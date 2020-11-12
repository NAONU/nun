import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Random;

public class Home1 extends JFrame {

    Home1() {
        add(new Home_Start());

        setTitle("PICTURE MATCHING");
        //pack();//ใช้กับกริด
        setSize(1000, 800);
        setResizable(false);//เปลียนขนาดไม่ได้
        setVisible(true);//แสดงหน้าจอ
        setLocationRelativeTo(null); //center
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ปิดทุกอย่าง
    }

    public static void main(String[] args) {
        new Home1();

    }
}

class Home_Start extends JPanel {

    int mx, my;
    ImageIcon Home_Screen = new ImageIcon(this.getClass().getResource("h.png"));
    ImageIcon catsky = new ImageIcon(this.getClass().getResource("catsky.gif"));
    ImageIcon dolphin = new ImageIcon(this.getClass().getResource("dolphin.gif"));
    ImageIcon pic = new ImageIcon(this.getClass().getResource("pic.png"));
    ImageIcon mat = new ImageIcon(this.getClass().getResource("mat.png"));
    ImageIcon pom = new ImageIcon(this.getClass().getResource("pom.gif"));
    ImageIcon rab = new ImageIcon(this.getClass().getResource("rabbit.gif"));
    Timer listen = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });

    public Home_Start() {
        setSize(1000, 800);
        listen.start();
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                mx = e.getX();
                my = e.getY();
            }
        });
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(mx);
                System.out.println(my);
                if (mx >= 617 && mx <= 775 && my >= 350 && my <= 389) {
                    easy es = new easy();
                    add(es);//add class easy
                    es.setFocusable(true);//center

                }
                if (mx >= 618 && mx <= 850 && my >= 421 && my <= 460) {
                    normal na = new normal();
                    add(na);//add class normal
                    na.setFocusable(true);
                }
                if (mx >= 620 && mx <= 770 && my >= 491 && my <= 528) {
                    hard h = new hard();
                    add(h);//add class hard
                    h.setFocusable(true);
                }
                if (mx >= 618 && mx <= 773 && my >= 290 && my <= 347){
                    s s = new s();
                    add(s);//add class hard
                    s.setFocusable(true);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(Home_Screen.getImage(), 0, 0, 1000, 800, this);
        g.drawImage(catsky.getImage(), 425, 300, 210, 210, this);
        g.drawImage(pic.getImage(), 333, 20, 600, 200, this);
        g.drawImage(mat.getImage(), 333, 130, 600, 200, this);
        g.drawImage(pom.getImage(), 180, 500, 200, 200, this);
        g.drawImage(dolphin.getImage(), 620, 520, 180, 180, this);

        g.setFont(new Font("Monospaced", Font.PLAIN, 64));
        if (mx >= 617 && mx <= 775 && my >= 350 && my <= 389) {
            g.setColor(Color.green);
            g.drawString("EASY", 620, 390);
            g.drawImage(rab.getImage(), 770, 350, 50, 45, this);
        } else {
            g.setColor(Color.black);
            g.drawString("EASY", 620, 390);
        }
        if (mx >= 618 && mx <= 850 && my >= 421 && my <= 460) {
            g.setColor(Color.BLUE);
            g.drawString("NORMAL", 620, 460);
            g.drawImage(rab.getImage(), 840, 420, 50, 45, this);
        } else {
            g.setColor(Color.black);
            g.drawString("NORMAL", 620, 460);
        }
        if (mx >= 620 && mx <= 770 && my >= 491 && my <= 528) {
            g.setColor(Color.red);
            g.drawString("HARD", 620, 530);
            g.drawImage(rab.getImage(), 766, 487, 50, 45, this);
        } else {
            g.setColor(Color.black);
            g.drawString("HARD", 620, 530);
        }
        if (mx >= 618 && mx <= 773 && my >= 290 && my <= 347) {
            g.setColor(Color.cyan);
            g.drawString("SuperEasy", 618, 347);
            //g.drawImage(rab.getImage(), 766, 487, 50, 45, this);
        } else {
            g.setColor(Color.black);
            g.drawString("SuperEasy", 618, 347);
        }

    }
}
