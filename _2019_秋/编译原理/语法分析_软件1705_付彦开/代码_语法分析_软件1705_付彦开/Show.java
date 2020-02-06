package com.Bianyi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class Show extends JFrame implements ActionListener{
	
	public char[] buf;
	
	JMenuBar jmb=null;
	JMenu[] jm=new JMenu[10];
	JMenuItem[] jmi=new JMenuItem[16];
	
	JPanel jp1,jp2;
	JTextArea jta1;
	JScrollPane scrollPane_1;
	
	static JTextArea jta2;
	
	JButton jb;
	
	public Show()
	{
		
	}
	
	public void showMain() 
	{
		jmb=new JMenuBar();
		
		jm[0]=new JMenu("文件");
		jm[1]=new JMenu("编辑");
		jm[2]=new JMenu("搜索");
		jm[3]=new JMenu("视图");
		jm[4]=new JMenu("项目");
		jm[5]=new JMenu("运行");
		jm[6]=new JMenu("工具");
		
		jmi[0]=new JMenuItem("新建");
		jmi[1]=new JMenuItem("打开项目或文件");
		jmi[2]=new JMenuItem("保存");
		jmi[3]=new JMenuItem("另存为");
		jmi[4]=new JMenuItem("另存项目为");
		jmi[5]=new JMenuItem("全部保存");
		jmi[6]=new JMenuItem("关闭");
		jmi[7]=new JMenuItem("关闭项目");
		jmi[8]=new JMenuItem("全部关闭");
		jmi[9]=new JMenuItem("参数");
		jmi[10]=new JMenuItem("导入");
		jmi[11]=new JMenuItem("导出");
		jmi[12]=new JMenuItem("打印");
		jmi[13]=new JMenuItem("打印设置");
		jmi[14]=new JMenuItem("清除历史");
		jmi[15]=new JMenuItem("退出");
		
		for(int i=0;i<15;i++)
		{
			jm[0].add(jmi[i]);
		}
		
		for(int i=0;i<7;i++)
		{
			jmb.add(jm[i]);
		}
		
		jmi[1].setActionCommand("open");
		jmi[1].addActionListener(this);
		
		jp1=new JPanel();
		jp2=new JPanel();
		
		jta1=new JTextArea(20,60);	//创建多行文本框
		jta1.setLineWrap(true);	//设置多行文本框自动换行
		jta1.setFont(new Font("宋体", Font.PLAIN, 16));
		jp1.add(jta1);
		
		jta2=new JTextArea(20,60);	//创建多行文本框
		jta2.setLineWrap(true);	//设置多行文本框自动换行
		jta2.setFont(new Font("宋体", Font.PLAIN, 16));
		jp2.add(jta2);
		scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(28, 209, 752, 265);
        jp2.add(scrollPane_1);                
        jta2.setBounds(28, 209, 752, 265);
        scrollPane_1.setViewportView(jta2);
		
        
        jb=new JButton("词法分析");
        jmb.add(jb);
        jb.setActionCommand("cifafenxi");
        jb.addActionListener(this);
		
		this.setLayout(new GridLayout(1,2));
		this.setJMenuBar(jmb);
		this.add(jp1);
		this.add(jp2);
		this.setTitle("编译原理");
		this.setSize(1000,480);
		this.setLocation(140,100);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void jta2Add(String a)
	{
		jta2.append(a);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("open")){
			JFileChooser jf = new JFileChooser();
			jf.showOpenDialog(this);//显示打开的文件对话框
			File f =  jf.getSelectedFile();//使用文件类获取选择器选择的文件
			String s = "";
			s= f.getAbsolutePath();//返回路径名
			Deal de=new Deal();
			try {
				buf=de.openFile(s);
				String str=new String(buf);
				jta2.setText("");
				jta1.setText("");
				jta1.append(str);
				de.cifafenxi(buf);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		if(e.getActionCommand().equals("cifafenxi"))
		{
			jta2.setText("");
			Deal de=new Deal();
			String str=jta1.getText();
			de.cifafenxi(str.toCharArray());
		}
	}
}
