import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class normal extends JFrame {

    normal() {

        add(new MemoryGameNormal());
        setTitle("NORMAL");
        setSize(1000, 800);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        new normal();
    }
}

class MemoryGameNormal extends JPanel {

    static String files[] = {"plant1.png", "starfish1.png", "hat1.png","kaprun1.png","coconut1.png","crab1.png"};

    ImageIcon gameover = new ImageIcon(this.getClass().getResource("gameover1.gif"));
    ImageIcon Normal = new ImageIcon(this.getClass().getResource("Normal.png"));
    ImageIcon rabbit = new ImageIcon(this.getClass().getResource("rabbit2.gif"));
    ImageIcon Home_Screen = new ImageIcon(this.getClass().getResource("sakura.gif"));
    static JButton buttons[];
    ImageIcon closedIcon;
    int numButtons;
    ImageIcon icons[];
    Timer myTimer;

    int Second = 50;
    int numClicks = 0;
    int oddClickIndex = 0;
    int currentIndex = 0;
    boolean aa[] = {false,false,false,false,false,false};
    int x = 0;
    Thread Draw = new Thread(new Runnable() {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                    repaint();
                } catch (InterruptedException ex) {

                }

            }
        }
    });
    Thread time = new Thread(new Runnable() {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    Second--;

                } catch (InterruptedException ex) {

                }
            }
        }
    });

    public MemoryGameNormal() {

// Set the title.
// Specify an action for the close button.
// Create a BorderLayout manager.
        setSize(1000, 800);
        time.start();
        Draw.start();

        closedIcon = new ImageIcon(this.getClass().getResource("mirror1.png"));
        numButtons = files.length * 2;
        buttons = new JButton[numButtons];
        icons = new ImageIcon[numButtons];
        for (int i = 0, j = 0; i < files.length; i++) {
            icons[j] = new ImageIcon(this.getClass().getResource(files[i]));
            buttons[j] = new JButton("");
            buttons[j].addActionListener(new ImageButtonListener());
            buttons[j].setIcon(closedIcon);
            add(buttons[j++]);

            icons[j] = icons[j - 1];
            buttons[j] = new JButton("");
            buttons[j].addActionListener(new ImageButtonListener());
            buttons[j].setIcon(closedIcon);
            add(buttons[j++]);
        }

// randomize icons array
        Random gen = new Random();
        for (int i = 0; i < numButtons; i++) {
            int rand = gen.nextInt(numButtons);
            ImageIcon temp = icons[i];
            icons[i] = icons[rand];
            icons[rand] = temp;
        }
        myTimer = new Timer(1000, new TimerListener());
        myTimer.start();
    }

    class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            buttons[currentIndex].setIcon(closedIcon);
            buttons[oddClickIndex].setIcon(closedIcon);
            myTimer.stop();
        }
    }

    class ImageButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

// we are waiting for timer to pop - no user clicks accepted
            if (myTimer.isRunning()) {
                return;
            }

            numClicks++;
            System.out.println(numClicks);

// which button was clicked?
            for (int i = 0; i < numButtons; i++) {
                if (e.getSource() == buttons[i]) {
                    buttons[i].setIcon(icons[i]);
                    currentIndex = i;
                }
            }

// check for even click
            if (numClicks % 2 == 0) {

// check whether same position is clicked twice!
                if (currentIndex == oddClickIndex) {
                    numClicks--;

                    return;
                }
// are two images matching?
                if (icons[currentIndex] != icons[oddClickIndex]) {
// show images for 1 sec, before flipping back

                    myTimer.start();

                } else {
                    System.out.println(aa[x]);
                    aa[x] = true;
                    System.out.println(aa[x]);
                    x++;
                }
            } else {
// we just record index for odd clicks
                oddClickIndex = currentIndex;

            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Home_Screen.getImage(), 0, 0, 1000, 800, this);
        g.drawImage(rabbit.getImage(), 350, 600, 120, 120, this);
        g.drawImage(Normal.getImage(), 0, -33, 990, 800, this);
        g.setFont(new Font("Monospaced", Font.PLAIN, 64));
        g.setColor(Color.white);
        g.drawString(String.valueOf(x) + " SCORE ", 100, 700);
        if (Second < 10) {
            g.drawString(String.valueOf(Second) + " SECOUND", 500, 700);

        } else {
            g.drawString(String.valueOf(Second) + " SECOUND", 500, 700);
        }

        if (Second == 0) {
            for (int i = 0; i < files.length*2; i++) {
                remove(buttons[i]);
            }
            gameover start = new gameover();
            add(start);
            g.setColor(Color.red);
            g.drawString(String.valueOf(x), 800, 600);
            Draw.stop();
            myTimer.stop();
            start.setFocusable(true);
        }
        if (aa[0]==true&&aa[1]==true&&aa[2]==true&&aa[3]==true&&aa[4]==true&&aa[5]==true) {
            for (int i = 0; i < files.length*2; i++) {
                remove(buttons[i]);
            }
            gamewin start = new gamewin();
            add(start);
            Draw.stop();
            time.stop();
            start.setFocusable(true);

        }

    }

    class gameover extends JPanel {

        ImageIcon gameover = new ImageIcon(this.getClass().getResource("gameover1.gif"));
        int mx, my;
        Thread Draw = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                        repaint();
                    } catch (InterruptedException ex) {

                    }

                }
            }
        });

        public gameover() {
            setSize(1000, 800);
            Draw.start();
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
                    if (mx >= 769 && mx <= 880 && my >= 672 && my <= 690) {
                        System.exit(0);
                    }

                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(gameover.getImage(), 0, 0, 1000, 800, this);
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Monospaced", Font.PLAIN, 50));
            g.drawString(String.valueOf(x), 500, 700);
            g.drawString("TIME OUT", 100, 700);
            if (mx >= 769 && mx <= 880 && my >= 672 && my <= 690) {
                g.setColor(Color.RED);
                g.drawString("EXIT", 775, 700);
            } else {
                g.setColor(Color.YELLOW);
                g.drawString("EXIT", 775, 700);
            }

        }
    }

    class gamewin extends JPanel {

        ImageIcon youwin = new ImageIcon(this.getClass().getResource("win_star.gif"));
        Thread Draw = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                        repaint();
                    } catch (InterruptedException ex) {

                    }

                }
            }
        });

        public gamewin() {
            setSize(1000, 800);
            Draw.start();

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font("Monospaced", Font.PLAIN, 50));
            g.drawImage(youwin.getImage(), 0, 0, 1000, 800, this);
            g.setColor(Color.white);
            g.drawString(String.valueOf(30 - Second) + " SECOUND", 100, 700);
            g.setColor(Color.white);
            g.drawString("You SCORE " + String.valueOf(x), 550, 700);

        }
    }

}
