package mainpackage;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ListIterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;

public class CovidCaseTracker extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private ListIterator<Element> countryname;
	private JButton search;
	String link="https://www.worldometers.info/coronavirus/";
	ImageIcon noconnectionimage;
	private JLabel noconnectionlabel,label2;
	private JLabel label;
	private JLabel countcase;
	private JLabel countdealth;
	private JLabel countrecovered;
	private JTextField countrynamefield;
	private JLabel countrynamelabel;
	private JLabel labelcase,labeldealth,labelrecovered;
	private JLabel lastupdated;
	private JLabel lblPopulation;
	private JLabel population;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CovidCaseTracker frame = new CovidCaseTracker();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public CovidCaseTracker() {
		
		
	this.setCursor(Cursor.WAIT_CURSOR);
	this.setResizable(false);
		Timer timer=new Timer(100,this);
		timer.start();
		setTitle("Corona Case Tracker");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 546);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(128, 128, 128));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 128, 128), 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel covidtrackerlabel = new JLabel("Corona Case Tracker");
		covidtrackerlabel.setBackground(new Color(0, 128, 128));
		covidtrackerlabel.setBounds(0, 0, 498, 66);
		covidtrackerlabel.setOpaque(true);
		covidtrackerlabel.setForeground(new Color(255, 255, 255));
		covidtrackerlabel.setFont(new Font("Candara", Font.BOLD, 25));
		covidtrackerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		covidtrackerlabel.setLabelFor(this);
		
		contentPane.add(covidtrackerlabel);
		
		noconnectionlabel = new JLabel("");
		noconnectionlabel.setBounds(197, 167, 142, 117);
		noconnectionlabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		noconnectionlabel.setVisible(false);
		noconnectionlabel.setIcon(new ImageIcon("./noconnection.png"));
		contentPane.add(noconnectionlabel);
		
		label2 = new JLabel("No Internet Connection");
		label2.setBounds(141, 307, 221, 28);
		label2.setFont(new Font("Microsoft PhagsPa", Font.PLAIN, 20));
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setVisible(false);
		contentPane.add(label2);
		
		search = new JButton("");
		search.setForeground(new Color(253, 245, 230));
		search.setBounds(402, 90, 75, 47);
		search.setToolTipText("Search");
		search.setBackground(Color.WHITE);
		search.setFocusable(false);
		search.addActionListener(this);
		search.setIcon(new ImageIcon("./search.png"));
		contentPane.add(search);
		
		 labelcase = new JLabel("Coronavirus Cases  :");
		labelcase.setBounds(20, 200, 194, 47);
		labelcase.setHorizontalAlignment(SwingConstants.RIGHT);
		labelcase.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(labelcase);
		
		 labeldealth = new JLabel("Dealth  :");
		labeldealth.setBounds(20, 249, 194, 47);
		labeldealth.setHorizontalAlignment(SwingConstants.RIGHT);
		labeldealth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(labeldealth);
		
		labelrecovered = new JLabel("Recovered  :");
		labelrecovered.setBounds(20, 307, 194, 47);
		labelrecovered.setHorizontalAlignment(SwingConstants.RIGHT);
		labelrecovered.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(labelrecovered);
		
		countcase = new JLabel("0");
		countcase.setBounds(241, 200, 194, 47);
		countcase.setHorizontalAlignment(SwingConstants.LEFT);
		countcase.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(countcase);
		
		countdealth = new JLabel("0");
		countdealth.setBounds(241, 249, 194, 47);
		countdealth.setForeground(new Color(255, 0, 0));
		countdealth.setHorizontalAlignment(SwingConstants.LEFT);
		countdealth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(countdealth);
		
		countrecovered = new JLabel("0");
		countrecovered.setBounds(241, 307, 194, 47);
		countrecovered.setForeground(new Color(60, 179, 113));
		countrecovered.setHorizontalAlignment(SwingConstants.LEFT);
		countrecovered.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(countrecovered);
		
		countrynamefield = new JTextField();
		countrynamefield.setBounds(20, 90, 372, 47);
		countrynamefield.setToolTipText("Enter Country Name");
		countrynamefield.setHorizontalAlignment(SwingConstants.CENTER);
		countrynamefield.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		contentPane.add(countrynamefield);
		countrynamefield.setColumns(10);
		
		countrynamelabel = new JLabel("No country choosen");
		countrynamelabel.setHorizontalAlignment(SwingConstants.CENTER);
		countrynamelabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		countrynamelabel.setBounds(10, 142, 460, 47);
		contentPane.add(countrynamelabel);
		
		lastupdated = new JLabel("");
		lastupdated.setBackground(SystemColor.menu);
		lastupdated.setForeground(new Color(128, 128, 128));
		lastupdated.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lastupdated.setHorizontalAlignment(SwingConstants.CENTER);
		lastupdated.setBounds(10, 468, 471, 28);
		contentPane.add(lastupdated);
		
		
		this.getRootPane().setDefaultButton(search);
		
		lblPopulation = new JLabel("Population  :");
		lblPopulation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPopulation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPopulation.setBounds(20, 365, 194, 47);
		contentPane.add(lblPopulation);
		
		population = new JLabel("0");
		population.setHorizontalAlignment(SwingConstants.LEFT);
		population.setForeground(new Color(0, 128, 128));
		population.setFont(new Font("Tahoma", Font.PLAIN, 20));
		population.setBounds(241, 365, 194, 47);
		contentPane.add(population);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!checkconnection())
		{
			noconnectionlabel.setVisible(true);
			label2.setVisible(true);
			countrynamefield.setVisible(false);
			search.setVisible(false);
			labelcase.setVisible(false);
			labeldealth.setVisible(false);
			labelrecovered.setVisible(false);
			countcase.setVisible(false);
			countdealth.setVisible(false);
			countrecovered.setVisible(false);
			countrynamelabel.setVisible(false);
			lblPopulation.setVisible(false);
			population.setVisible(false);
			
		}
		else
		{
		
			noconnectionlabel.setVisible(false);
			label2.setVisible(false);
			countrynamefield.setVisible(true);
			search.setVisible(true);
			labelcase.setVisible(true);
			labeldealth.setVisible(true);
			labelrecovered.setVisible(true);
			countcase.setVisible(true);
			countdealth.setVisible(true);
			countrecovered.setVisible(true);
			countrynamelabel.setVisible(true);
			lblPopulation.setVisible(true);
			population.setVisible(true);
			
		}
		this.setCursor(Cursor.DEFAULT_CURSOR);
		if(e.getSource()==search)
		{
			this.setCursor(Cursor.WAIT_CURSOR);
		
				getData((String) countrynamefield.getText().toLowerCase());
			
		}
		
	}
	public void population(String country)
	{
		try
		{
		String url="https://www.worldometers.info/world-population/"+country+"-population/";
		org.jsoup.nodes.Document doc=Jsoup.connect(url).get();

		StringBuffer sb=new StringBuffer();
		sb=sb.append(doc.text());
		String start="The current population of";
		String pop=sb.substring(sb.indexOf(start)+start.length(),sb.indexOf("as of"));
		population.setText(pop.substring(pop.indexOf(" is ")+4));
		}
		catch(Exception e)
		{
			
		}
	}
	public void  getData(String Country) 
	{
		population(Country);
	
		try
		{
		String url="https://www.worldometers.info/coronavirus/country/"+Country+"/";
		Document doc=Jsoup.connect(url).get();
		
		Elements date=doc.select(".content-inner");
		String datestr = (String) date.toString();
		if(datestr.contains("Last updated:"))
		{
			lastupdated.setText(datestr.substring(datestr.indexOf("Last updated:"),datestr.indexOf("GMT")+3));
		}
		StringBuffer c=new StringBuffer();
		Elements cname=doc.select(".content-inner");
		
		cname.forEach((e)->
		{
			String text=e.select("h1").text();
			c.append(text+"/");
		});
		c.delete(c.indexOf(" Coronavirus Cases: Deaths: Recovered:/"),c.length());
		
		countrynamelabel.setText(c.toString());
		Elements elements=doc.select("#maincounter-wrap");
		StringBuffer str = new StringBuffer();
		
		elements.forEach(e->
		{
			String count=e.select(".maincounter-number>span").text();
			str.append(count+"$");
		}
		
				);
		
		int k[]=new int[5];
		int n=0;
		for(int i=0; i<str.length(); i++)
		{
			if(str.charAt(i)=='$')
			{
				k[n]=i;
				n++;
			}
		}
		countcase.setText(str.substring(0,k[0]));
		countdealth.setText(str.substring(k[0]+1,k[1]));
		countrecovered.setText(str.substring(k[1]+1,k[2]));
		}
		catch(Exception e)
		{
			countrynamelabel.setText("No country Found");
			countcase.setText("0");
			countdealth.setText("0");
			countrecovered.setText("0");
			population.setText("0");
			
			lastupdated.setText("");
		}
	}
	
	
	
	public boolean checkconnection()
	{
		try
		{
		URL url=new URL(link);
		URLConnection connection=url.openConnection();
		connection.connect();
		return true;
		}
		catch (MalformedURLException e) {
	         return false;
	      } catch (IOException e) {
	         return false;
	      }
		
	
	}
}
