package project;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.ImageIcon;
import java.io.File;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

    public class PROJECT extends javax.swing.JFrame {
    JLabel l = new JLabel();
    void tree() 
    {
        String path = "C:\\100gb";
        JFrame frame = new JFrame();
        File f = new File(path);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("100GB");
        JTree jTree1 = new JTree(root);
        JPanel PJ = new JPanel();
        PJ.add(jTree1);
        PJ.setBackground(Color.GRAY);
        JScrollPane SC = new JScrollPane(PJ);//ตัวเลื่อนหน้าจอข้างๆ
        JScrollPane v = new JScrollPane(l);//ตัวรูปภาพ
        v.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//เลื่อนแนวนอน  
        v.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JSplitPane p = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,SC,v);//เซ็ตจอ
        p.setResizeWeight(0.3);//แบ่งหน้าจอ
        frame.add(p);
        jTree1.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                TreePath tp = e.getPath();//ข้อมูลที่เลือก  
                DefaultMutableTreeNode newRoot = (DefaultMutableTreeNode)tp.getLastPathComponent();//คืนค่าตัวสุดท้าย tp แล้วแปลงเป็นดีฟอว
                StringBuilder newPath = new StringBuilder();//คลาสเอาไว้จัดการสตริง
                newPath.append("C:\\");//เพิ่มข้อมูลจากด้านบน
                Object[] o = tp.getPath();//เรียกข้อมูล
                for(int i = 0; i< o.length;i++){
                   newPath.append("\\").append(o[i]);//เรียกชื่อที่อยู่
                }
               String patha = newPath.toString();
               System.out.println(patha);
           
               File newFile = new File(patha);
               System.out.println(tp.getLastPathComponent());
                if(newFile.isDirectory())//เช็คว่ามีไฟล์ไหม
                {
                    createfile(newFile,newRoot);
                }
             else
                {
                 l.setIcon(new ImageIcon(patha));
                 }
            }

        
            public void createfile(File newFile, DefaultMutableTreeNode root)
            {
                File[] files = newFile.listFiles();
                for(File f:files){
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(f.getName());
                    root.add(newNode);
                }           
            }
        });
        /////box gui/////
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        PROJECT PJ = new PROJECT();
        PJ.tree();
    }
}


