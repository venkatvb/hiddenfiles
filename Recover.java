import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.Writer;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class Recover extends JFrame implements ActionListener
{
	JButton b, select;
	JFileChooser chooser;
	String a1 = "cd /d ";
	String a2 = "";
	String a3 = "attrib -s -h -r /D /S ";
	String a4 = "del /S *.lnk ";
	JLabel lab;
	File file;
	void wrong()
	{
		lab.setText("First Choose a folder");
	}
	void correct()
	{
		lab.setText("");
	}
	void del()
	{
		file.delete();
	}
	void make()
	{
		b = new JButton("clickme");
		select = new JButton("Choose");
		lab = new JLabel();
		setLayout(new FlowLayout());
		add(b);
		add(select);
		add(lab);
		setSize(300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		b.addActionListener(this);
		select.addActionListener(this);
	}
	public static void main(String af[])throws Exception
	{
		JFrame myFrame = new JFrame("Shortcut Remover");
		Recover r = new Recover();
		r.make();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals(b))
		{
			try{  
				if(a2.equals(""))
				{
					wrong();
				}
				else
				{
					file = new File(a2+"vb.bat");
					OutputStream fos = new FileOutputStream(file);
					fos.write(a1.getBytes());
					fos.write(a2.getBytes());
					fos.write(13);
					fos.write(10);
					fos.write(a3.getBytes());
					fos.write(13);
					fos.write(10);
					fos.write(a4.getBytes());
					fos.write(13);
					fos.write(10);
					fos.close();
					Process p = Runtime.getRuntime().exec(a2+"vb.bat");
					p.waitFor();
					del();
				}
		}catch(Exception ea){
			System.out.println("exception"+ea);
		}
		}
		else
		{
			int result;

		    chooser = new JFileChooser(); 
		    chooser.setCurrentDirectory(new java.io.File("."));
		    chooser.setDialogTitle("select");
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    //
		    // disable the "All files" option.
		    //
		    chooser.setAcceptAllFileFilterUsed(false);
		    //    
		    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
		     a2 = chooser.getCurrentDirectory().toString();
		      correct();
		      }
		    else {
		      
		      }
		}
	}
	
}
