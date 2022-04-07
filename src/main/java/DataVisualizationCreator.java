import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 * This class handles the output of the histogram and the table.
 *
 * @author Delaney Hishon
 * @author Meg Zhang
 *
 */
public class DataVisualizationCreator {
	// dataset: number of times trade was executed, row- broker name, col- trade strategy. Data for bar graph.
	private final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	// table columns: "Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date". Data for table.
	private DefaultTableModel tableModel = new DefaultTableModel();

	/**
	 * createCharts initializes a new table and a new histogram.
	 *
	 * @param mostRecentTrade String[] that contains details from the most recent trade.
	 */
    public void createCharts(String[] mostRecentTrade){
    	Object[][] tradeLog = new Object[1][7];
    	// create the object that is used to initialize the table.
		System.arraycopy(mostRecentTrade, 0, tradeLog[0], 0, 7);
        createTableOutput(tradeLog); // creates table
		createBar(mostRecentTrade); // create bar graph
    }

	/**
	 * addToTradeLog adds a new trade to the table, and the histogram.
	 *
	 * @param mostRecentTrade the trade to add to the trade log and the table.
	 */
    public void addToTradeLog(String[] mostRecentTrade) {
		tableModel.addRow(mostRecentTrade); // add a row to the table.
		if (!mostRecentTrade[3].equalsIgnoreCase("Fail")) {
			updateBarGraph(mostRecentTrade); // update the bar graph with new trade info, if trade succeeded
		}

		// update the UI
		MainUI.getInstance().repaint();
		MainUI.getInstance().revalidate();
	}

	/**
	 * createTableOutput initializes and creates the table.
	 *
	 * @param data the Object[][] that contains the initial data
	 */
	private void createTableOutput(Object[][] data){
	    // column names
        Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};

		// generate new tableModel using the data that was passed in.
		tableModel = new DefaultTableModel(data,columnNames);
		// create table by attaching tableModel to the table.
		// the table that is output on the MainUI.
		JTable table = new JTable(tableModel);
		table.setEnabled(false); // make table uneditable

		// the code below was provided by the cs2212 sample GUI code.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        scrollPane.setPreferredSize(new Dimension(800, 300));
        table.setFillsViewportHeight(true);
        MainUI.getInstance().updateStats(scrollPane);
		// the code above was provided by the cs2212 sample GUI code.
    }

	/**
	 * updateBarGraph Updates the bar graph with new information.
	 *
	 * @param data the data to amend.
	 */
	private void updateBarGraph(String[] data){
		// if the trade broker and strategy already exist in the graph
		try {
			dataset.incrementValue(1, data[0], data[1]); // increments value by 1 if it already exists.
		}
		// otherwise, add it to the dataset.
		catch (Exception e) {
			dataset.setValue(1, data[0], data[1]);
		}
		// update GUI
		MainUI.getInstance().revalidate();
	}

	/**
	 * createBar initializes the bar graph.
	 *
	 * @param data trade data for initialization
	 */
	private void createBar(String[] data) {

		if (!data[3].equalsIgnoreCase("Fail")){
			// set value as 1, as trade was executed one time, then the broker name, and the tradeStrategy to the dataset.
			dataset.setValue(1, data[0], data[1]);
		} else {
			// the first trade was a failed trade.
			dataset.setValue(0, data[0], data[1]);
		}
		// the code below was provided by the cs2212 sample GUI code, with slight modifications.
		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();
		plot.setDataset(0, dataset); // set dataset as the plot's dataset.
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(0.1, 20.0)); // set lower to 0.1 so that 1 trade shows up. Lower was originally 1.0 in the provided code.
		plot.setRangeAxis(rangeAxis);
		// the code above was provided by the cs2212 sample GUI code, with slight modifications.

		// the code below was provided by the cs2212 sample GUI code.
		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
		// the code above was provided by the cs2212 sample GUI code.
	}

}