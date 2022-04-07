import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * MainUI represents the main user interface and all the actions that can be done from it.
 *
 * @author Delaney Hishon
 * @author Samuel Catania
 * @author Meg Zhang
 */

public class MainUI extends JFrame implements ActionListener {
    @Serial
    private static final long serialVersionUID = 1L;

    private static MainUI instance;
    private final JPanel stats;

    private final DefaultTableModel dtm;
    private final JTable table;

    private final TradingStrategy[] tradingStrategies = new TradingStrategy[5];
    private boolean chartsCreated = false;
    private final DataVisualizationCreator creator = new DataVisualizationCreator();

    /**
     * getInstance returns this instance of MainUI.
     *
     * @return this instance of MainUI
     */
    public static MainUI getInstance() {
        if (instance == null)
            instance = new MainUI();

        return instance;
    }

    /**
     * MainUI() instantiates and creates the window, interface, and buttons of the main UI.
     */
    private MainUI() {

        // Set window title
        super("Crypto Trading Tool");

        createTradingStrategies();

        // Set top bar
        JPanel north = new JPanel();

        JButton trade = new JButton("Perform Trade");
        trade.setActionCommand("refresh");
        trade.addActionListener(this);

        JPanel south = new JPanel();

        south.add(trade);

        dtm = new DefaultTableModel(new Object[]{"Trading Client", "Coin List", "Strategy Name"}, 1);
        table = new JTable(dtm);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions", TitledBorder.CENTER, TitledBorder.TOP));
        Vector<String> strategyNames = new Vector<>();
        strategyNames.add("None");

        for (TradingStrategy strategy : tradingStrategies) {
            strategyNames.add(strategy.getStrategyName());
        }

        TableColumn strategyColumn = table.getColumnModel().getColumn(2);
        JComboBox comboBox = new JComboBox(strategyNames);
        comboBox.setEditable(false);
        strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));


        TableColumn coinColumn = table.getColumnModel().getColumn(1);
        coinColumn.setCellEditor(new DefaultCellEditor(new JTextField()));


        JButton addRow = new JButton("Add Row");
        JButton remRow = new JButton("Remove Row");
        addRow.setActionCommand("addTableRow");
        addRow.addActionListener(this);
        remRow.setActionCommand("remTableRow");
        remRow.addActionListener(this);

        scrollPane.setPreferredSize(new Dimension(800, 300));
        table.setFillsViewportHeight(true);


        JPanel east = new JPanel();
        east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
        east.add(scrollPane);
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(addRow);
        buttons.add(remRow);
        east.add(buttons);

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
    }

    /**
     * createTradingStrategies initializes all the trading strategies available in LemonMist.
     */
    private void createTradingStrategies() {

        //If the price of BTC is less or equal $50,000 and the price of ADA more than $2 then buy 10 ADA coins
        tradingStrategies[0] = new TradingStrategy("Strategy-A", "ADA", "Buy", 10, true, new String[]{"BTC", "ADA"}, new String[]{"<=", ">"}, new int[]{50000, 2});

        //If ETH drops below $4000 in price, buy $10,000 worth of ETH
        tradingStrategies[1] = new TradingStrategy("Strategy-B", "ETH", "Buy", 10000, false, new String[]{"ETH"}, new String[]{"<"}, new int[]{3800});

        //If XRP is less than or equal to $1, SOL is greater than or equal to $200, and BNB is greater than $510, sell 1 BTC
        tradingStrategies[2] = new TradingStrategy("Strategy-C", "BTC", "Sell", 1, true, new String[]{"XRP", "SOL", "BNB"}, new String[]{"<=", ">=", ">"}, new int[]{1, 200, 510});

        //If SOL is less than or equal to $130 and LINK is less than or equal to $20, then buy $500 worth of ALGO
        tradingStrategies[3] = new TradingStrategy("Strategy-D", "ALGO", "Buy", 500, false, new String[]{"SOL", "LINK"}, new String[]{"<=", "<="}, new int[]{130, 20});

        //If DOGE is greater than $0 and XRP is greater than $1, then sell 5 ETH
        tradingStrategies[4] = new TradingStrategy("Strategy-E", "ETH", "Sell", 5, true, new String[]{"DOGE", "XRP"}, new String[]{">", ">"}, new int[]{0, 1});
    }

    /**
     * updateStats adds new components (histogram, table) to the UI when they are created.
     *
     * @param component the new component to add to the main UI.
     */
    public void updateStats(JComponent component) {
        stats.add(component);
        stats.revalidate();
    }

    /**
     * actionPerformed waits and reacts to user input.
     *
     * @param e the command received by actionPerformed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        ArrayList<Broker> brokerArrayList = new ArrayList<>();

        //Store all coins to use to fetch prices
        Set<String> allCoins = new HashSet<>();

        String command = e.getActionCommand();

        //Perform Trade
        if ("refresh".equals(command)) {
            // parses through entire table of brokers.
            for (int count = 0; count < dtm.getRowCount(); count++) {
                // create a new traderObject based on current row.
                Object traderObject = dtm.getValueAt(count, 0);

                // if the trader object is null, then the name hasn't been set. Return message.
                if (traderObject == null) {
                    JOptionPane.showMessageDialog(this, "Please fill in Trader name on line " + (count + 1) + ".");
                    return;
                }

//                // check if the trader name already exists. If it does, do not add a new broker.
                int checkDuplicate = isDuplicate(traderObject.toString());
                if (checkDuplicate != 0) {
                    JOptionPane.showMessageDialog(this, "Broker name on line " + (checkDuplicate) + " is a duplicate.");
                    return;
                }

                String traderName = traderObject.toString(); // otherwise, set traderName.

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

            updateAndTrade(brokerArrayList, allCoins);


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

    /**
     * isDuplicate returns true if the brokerName is already the name of an existing broker.
     *
     * @param brokerName the name to check
     * @return the row number that is a duplicate if it is a duplicate, null otherwise.
     */
    private int isDuplicate(String brokerName) {
        int count = 0; // it is a duplicate if count counts 2 on the dtm.
        for (int i = 0; i < dtm.getRowCount(); i++) {
            if (dtm.getValueAt(i, 0).toString().equals(brokerName)) {
                count++;
            }
            if (count == 2) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * This method creates a new trade, and updates the table and histogram.
     *
     * @param brokerArrayList the broker list
     * @param allCoins        a set containing data about all cryptocoins.
     */
    private void updateAndTrade(ArrayList<Broker> brokerArrayList, Set<String> allCoins) {

        DataFetcher fetcher = new DataFetcher();
        double[] allPricesArray = fetcher.getPricesForCoins(allCoins);

        String[] allCoinsArray = allCoins.toArray(new String[0]);

        for (Broker broker : brokerArrayList) {
            double[] tempPrices = new double[broker.getCoins().length];

            for (int i = 0; i < tempPrices.length; i++) {
                for (int x = 0; x < allCoinsArray.length; x++) {
                    if (broker.getCoins()[i].equalsIgnoreCase(allCoinsArray[x])) {
                        tempPrices[i] = allPricesArray[x];
                    }
                }
            }
            broker.setCoinPrices(tempPrices);

            String[] tradeResult = broker.getTradeStrategy().trade(broker.getName(), fetcher.getTargetCoinPrice(broker.getTradeStrategy().getCoinTarget()), broker.getCoins(), broker.getCoinPrices());

            if (tradeResult[3].equalsIgnoreCase("Fail")) {

                if (tradeResult[7].equalsIgnoreCase("1")) {
                    JOptionPane.showMessageDialog(this, "\"" + broker.getTradeStrategy().getStrategyName() + "\" can not be applied as the trade conditions have not been met.", "Trade Condition Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(this, "\"" + broker.getTradeStrategy().getStrategyName() + "\" can not be applied as the coins selected for \"" + broker.getName() + "\" are not sufficient.", "Coin Error", JOptionPane.ERROR_MESSAGE);
                }
                tradeResult = Arrays.copyOf(tradeResult, tradeResult.length - 1);

            }

            if (!chartsCreated) {
                creator.createCharts(tradeResult);
                chartsCreated = true;

            } else {
                creator.addToTradeLog(tradeResult);
            }
        }
    }
}