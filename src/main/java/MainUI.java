import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MainUI extends JFrame implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static MainUI instance;
    private JPanel stats, chartPanel, tablePanel;

    // Should be a reference to a separate object in actual implementation
    private List<String> selectedList;

    private JTextArea selectedTickerList;
    //	private JTextArea tickerList;
    private JTextArea tickerText;
    private JTextArea BrokerText;
    private JComboBox<String> strategyList;
    private Map<String, List<String>> brokersTickers = new HashMap<>();
    private Map<String, String> brokersStrategies = new HashMap<>();
    private List<String> selectedTickers = new ArrayList<>();
    private String selectedStrategy = "";
    private DefaultTableModel dtm;
    private JTable table;

    private TradingStrategy[] tradingStrategies = new TradingStrategy[5];

    public static MainUI getInstance() {
        if (instance == null)
            instance = new MainUI();

        return instance;
    }

    private MainUI() {

        // Set window title
        super("Crypto Trading Tool");

        createTradingStrategies();

        // Set top bar
        JPanel north = new JPanel();

//		north.add(strategyList);

        // Set bottom bar
//		JLabel from = new JLabel("From");
//		UtilDateModel dateModel = new UtilDateModel();
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
//		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
//		@SuppressWarnings("serial")
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new AbstractFormatter() {
//			private String datePatern = "dd/MM/yyyy";
//
//			private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePatern);
//
//			@Override
//			public Object stringToValue(String text) throws ParseException {
//				return dateFormatter.parseObject(text);
//			}
//
//			@Override
//			public String valueToString(Object value) throws ParseException {
//				if (value != null) {
//					Calendar cal = (Calendar) value;
//					return dateFormatter.format(cal.getTime());
//				}
//
//				return "";
//			}
//		});

        JButton trade = new JButton("Perform Trade");
        trade.setActionCommand("refresh");
        trade.addActionListener(this);


        JPanel south = new JPanel();

        south.add(trade);

        dtm = new DefaultTableModel(new Object[]{"Trading Client", "Coin List", "Strategy Name"}, 1);
        table = new JTable(dtm);
        // table.setPreferredSize(new Dimension(600, 300));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions", TitledBorder.CENTER, TitledBorder.TOP));
        Vector<String> strategyNames = new Vector<String>();
        strategyNames.add("None");

        for (TradingStrategy strategy : tradingStrategies) {
            strategyNames.add(strategy.getStrategyName());
        }

        TableColumn strategyColumn = table.getColumnModel().getColumn(2);
        JComboBox comboBox = new JComboBox(strategyNames);
        strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));

        Vector<String> coinNames = new Vector<String>();
        coinNames.add("BTC");
        coinNames.add("ETH");
        coinNames.add("USDT");
        coinNames.add("BNB");
        coinNames.add("USDC");
        coinNames.add("XRP");
        coinNames.add("LUNA");
        coinNames.add("ADA");
        coinNames.add("SOL");
        coinNames.add("AVAX");
        TableColumn coinColumn = table.getColumnModel().getColumn(1);
        JComboBox coinComboBox = new JComboBox(coinNames);
        coinColumn.setCellEditor(new DefaultCellEditor(coinComboBox));
        coinComboBox.setEditable(true);


        JButton addRow = new JButton("Add Row");
        JButton remRow = new JButton("Remove Row");
        addRow.setActionCommand("addTableRow");
        addRow.addActionListener(this);
        remRow.setActionCommand("remTableRow");
        remRow.addActionListener(this);

        scrollPane.setPreferredSize(new Dimension(800, 300));
        table.setFillsViewportHeight(true);


        JPanel east = new JPanel();
//		east.setLayout();
        east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
//		east.add(table);
        east.add(scrollPane);
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(addRow);
        buttons.add(remRow);
        east.add(buttons);
//		east.add(selectedTickerListLabel);
//		east.add(selectedTickersScrollPane);

        // Set charts region
        JPanel west = new JPanel();
        west.setPreferredSize(new Dimension(1250, 650));
        stats = new JPanel();
        stats.setLayout(new GridLayout(2, 2));

        west.add(stats);

        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(east, BorderLayout.EAST);
        getContentPane().add(west, BorderLayout.CENTER);
        getContentPane().add(south, BorderLayout.SOUTH);
//		getContentPane().add(west, BorderLayout.WEST);
    }

    private void createTradingStrategies() {
        tradingStrategies[0] = new TradingStrategy("Strategy-A", "ADA", "buy", 10, new String[]{"BTC", "ADA"}, new String[]{"<=", ">"}, new int[]{50000, 2});
        tradingStrategies[1] = new TradingStrategy("Strategy-B", "ADA", "buy", 10, new String[]{"BTC", "ADA"}, new String[]{"<=", ">"}, new int[]{50000, 2});
        tradingStrategies[2] = new TradingStrategy("Strategy-C", "ADA", "buy", 10, new String[]{"BTC", "ADA"}, new String[]{"<=", ">"}, new int[]{50000, 2});
        tradingStrategies[3] = new TradingStrategy("Strategy-D", "ADA", "buy", 10, new String[]{"BTC", "ADA"}, new String[]{"<=", ">"}, new int[]{50000, 2});
        tradingStrategies[4] = new TradingStrategy("Strategy-E", "ADA", "buy", 10, new String[]{"BTC", "ADA"}, new String[]{"<=", ">"}, new int[]{50000, 2});
    }

    public void updateStats(JComponent component) {
        stats.add(component);
        stats.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ArrayList<Broker> brokerArrayList = new ArrayList<Broker>();

        //Store all coins to use to fetch prices
        Set<String> allCoins = new HashSet<String>();

        String command = e.getActionCommand();

        //Perform Trade
        if ("refresh".equals(command)) {
            for (int count = 0; count < dtm.getRowCount(); count++) {
                Object traderObject = dtm.getValueAt(count, 0);

                if (traderObject == null) {
                    JOptionPane.showMessageDialog(this, "Please fill in Trader name on line " + (count + 1) + ".");
                    return;
                }
                String traderName = traderObject.toString();

                Object coinObject = dtm.getValueAt(count, 1);
                if (coinObject == null) {
                    JOptionPane.showMessageDialog(this, "Please fill in cryptocoin list on line " + (count + 1) + ".");
                    return;
                }
                String[] coinNames = coinObject.toString().split(",");

                allCoins.addAll(Arrays.asList(coinNames));

                Object strategyObject = dtm.getValueAt(count, 2);
                if (strategyObject == null) {
                    JOptionPane.showMessageDialog(this, "Please fill in strategy name on line " + (count + 1) + ".");
                    return;
                }
                String strategyName = strategyObject.toString();

                for (TradingStrategy strategy : tradingStrategies) {
                    if (strategy.getStrategyName().equals(strategyName)) {
                        brokerArrayList.add(new Broker(traderName, strategy, coinNames));
                    }
                }

                System.out.println(traderName + " " + Arrays.toString(coinNames) + " " + strategyName);
            }
            stats.removeAll();

            updateAndTrade(brokerArrayList, allCoins);


            //creator.createCharts();

            // !! test trades below
//            creator.createCharts(new String[]{"Trader-1", "Strategy-A", "ETH", "Buy", "500", "150.3", "13-January-2022"});
//            creator.addToTradeLog(new String[]{"Trader-1", "Strategy-A", "ETH", "Buy", "500", "150.3", "13-January-2022"});
//            creator.addToTradeLog(new String[]{"Trader-2", "Strategy-B", "ETH", "Buy", "500", "150.3", "13-January-2022"});
//            creator.addToTradeLog(new String[]{"Trader-2", "Strategy-B", "ETH", "Buy", "500", "150.3", "13-January-2022"});
//            creator.addToTradeLog(new String[]{"Trader-2", "Strategy-B", "ETH", "Buy", "500", "150.3", "13-January-2022"});
//            creator.addToTradeLog(new String[]{"Trader-2", "Strategy-C", "ETH", "Buy", "500", "150.3", "13-January-2022"});


            //Add a new table row
        } else if ("addTableRow".equals(command)) {//add row
            dtm.addRow(new String[3]);

            //Remove an existing table row
        } else if ("remTableRow".equals(command)) {//remove row
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1)
                dtm.removeRow(selectedRow);
        }
    }

    private void updateAndTrade(ArrayList<Broker> brokerArrayList, Set<String> allCoins){

        DataFetcher fetcher = new DataFetcher();
        double[] allPricesArray = fetcher.getPricesForCoins(allCoins);

        String[] allCoinsArray = allCoins.toArray(new String[0]);

        for (Broker broker : brokerArrayList) {
            double[] tempPrices = new double[broker.getCoins().length];

            for(int i = 0; i < tempPrices.length; i++){
                for(int x = 0; x < allCoinsArray.length; x++){
                    if(broker.getCoins()[i].equalsIgnoreCase(allCoinsArray[x])){
                        tempPrices[i] = allPricesArray[x];
                    }
                }
            }
            broker.setCoinPrices(tempPrices);
            String[] tradeResult = broker.getTradeStrategy().trade(broker.getName(), broker.getCoins(), broker.getCoinPrices());

            if(tradeResult[3].equalsIgnoreCase("Fail")){
                JOptionPane.showMessageDialog(this, "\"" + broker.getTradeStrategy().getStrategyName() + "\" can not be applied as the coins selected for \"" + broker.getName() + "\" are not sufficient.");
            }

            DataVisualizationCreator creator = new DataVisualizationCreator();

            creator.createCharts(tradeResult);
        }
    }
}