package ims.ui.report;

import static org.junit.Assert.fail;

import java.awt.BorderLayout;
import java.awt.Container;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

public class MyiReportViewer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2443146870257325672L;

	private MyiReportViewer() {
		super("Report Viewer");
		initComponents();
		setBounds(10, 10, 600, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public MyiReportViewer(String fileName) {
		this(fileName, null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MyiReportViewer(String fileName, HashMap parameters) {
		this();
		/* load the required JDBC driver and create the connection */
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/inventory", "root", "root");

			/*
			 * Here the parameter file should be in .jasper extension i.e. the
			 * compiled report
			 */
			JasperReport jasperReport = JasperCompileManager.compileReport(fileName);
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					parameters, con);
			JRViewer viewer = new JRViewer(print);
			Container c = getContentPane();
			c.setLayout(new BorderLayout());
			c.add(viewer);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,
				394, Short.MAX_VALUE);
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 274,
				Short.MAX_VALUE));
		pack();
	}
	
	public static void main(String[] args) {
		String jasperXMLFileName = MyiReportViewer.class.getResource(
				"/reports/ireports37/chapter10/MyListOfProductWithImage.jrxml").getPath();
		MyiReportViewer myiReportViewer = new MyiReportViewer(jasperXMLFileName);
		myiReportViewer.setVisible(true);
	}
	
}
