import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GUIVisualization extends JFrame { // Class to visualize the stock data in a GUI
    private List<Long> searchTimes; // Create lists to store the search, add, remove, and update times
    private List<Long> addTimes;    // Create lists to store the search, add, remove, and update times
    private List<Long> removeTimes; // Create lists to store the search, add, remove, and update times
    private List<Long> updateTimes; // Create lists to store the search, add, remove, and update times
    private List<Integer> searchSizes;  // Create lists to store the search, add, remove, and update sizes
    private List<Integer> addSizes;  // Create lists to store the search, add, remove, and update sizes
    private List<Integer> removeSizes;  // Create lists to store the search, add, remove, and update sizes
    private List<Integer> updateSizes;  // Create lists to store the search, add, remove, and update sizes

    private GraphPanel searchPanel; // Create GraphPanels to display the search, add, remove, and update times
    private GraphPanel addPanel;    // Create GraphPanels to display the search, add, remove, and update times
    private GraphPanel removePanel; // Create GraphPanels to display the search, add, remove, and update times
    private GraphPanel updatePanel; // Create GraphPanels to display the search, add, remove, and update times       

    private static final long TIME_LIMIT = 500000;

    public GUIVisualization() { // Constructor for GUIVisualization
        setTitle("Stock Performance Visualization");    // Set the title of the GUI
        setSize(1200, 800); // Set the size of the GUI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation
        JTabbedPane tabbedPane = new JTabbedPane(); // Create a tabbed pane to display the graphs
        add(tabbedPane);    // Add the tabbed pane to the GUI

        searchPanel = new GraphPanel("Search Times", searchTimes, searchSizes); // Create a GraphPanel for search times
        addPanel = new GraphPanel("Add Times", addTimes, addSizes);     // Create a GraphPanel for add times
        removePanel = new GraphPanel("Remove Times", removeTimes, removeSizes);     // Create a GraphPanel for remove times
        updatePanel = new GraphPanel("Update Times", updateTimes, updateSizes);    // Create a GraphPanel for update times

        tabbedPane.addTab("Search Times", searchPanel); // Add the search panel to the tabbed pane
        tabbedPane.addTab("Add Times", addPanel);   // Add the add panel to the tabbed pane
        tabbedPane.addTab("Remove Times", removePanel); // Add the remove panel to the tabbed pane
        tabbedPane.addTab("Update Times", updatePanel); // Add the update panel to the tabbed pane
    }

    public void setSearchTimes(List<Long> searchTimes, List<Integer> searchSizes) { // Method to set the search times and sizes
        this.searchTimes = searchTimes; // Set the search times
        this.searchSizes = searchSizes; // Set the search sizes
        searchPanel.setTimes(searchTimes, searchSizes); // Set the search times and sizes in the search panel
        searchPanel.repaint();  // Repaint the search panel
    }

    public void setAddTimes(List<Long> addTimes, List<Integer> addSizes) {  // Method to set the add times and sizes
        this.addTimes = addTimes;   // Set the add times
        this.addSizes = addSizes;   // Set the add sizes
        addPanel.setTimes(addTimes, addSizes);  // Set the add times and sizes in the add panel
        addPanel.repaint();     // Repaint the add panel
    }

    public void setRemoveTimes(List<Long> removeTimes, List<Integer> removeSizes) {  // Method to set the remove times and sizes
        this.removeTimes = removeTimes; // Set the remove times
        this.removeSizes = removeSizes; // Set the remove sizes
        removePanel.setTimes(removeTimes, removeSizes);     // Set the remove times and sizes in the remove panel
        removePanel.repaint();  // Repaint the remove panel Repaint use for updating the panel
    }

    public void setUpdateTimes(List<Long> updateTimes, List<Integer> updateSizes) { // Method to set the update times and sizes
        this.updateTimes = updateTimes;     // Set the update times
        this.updateSizes = updateSizes;    // Set the update sizes
        updatePanel.setTimes(updateTimes, updateSizes); // Set the update times and sizes in the update panel
        updatePanel.repaint();  // Repaint the update panel Repaint use for updating the panel
    }   

    private class GraphPanel extends JPanel {   // Class to create a panel to display the graph
        private String title;       // Create variables to store the title, times, and sizes
        private List<Long> times;   // Create variables to store the title, times, and sizes
        private List<Integer> sizes;    // Create variables to store the title, times, and sizes

        public GraphPanel(String title, List<Long> times, List<Integer> sizes) {    // Constructor for GraphPanel
            this.title = title; // Set the title
            this.times = times; // Set the times
            this.sizes = sizes; // Set the sizes
            setBackground(Color.WHITE); // Set the background color
        }

        public void setTimes(List<Long> times, List<Integer> sizes) {   // Method to set the times and sizes
            this.times = times; // Set the times
            this.sizes = sizes; // Set the sizes
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) { // Method to paint the component
            super.paintComponent(g);    // Call the super method
            if (times == null || times.isEmpty() || sizes == null || sizes.isEmpty()) { // Check if the times and sizes are empty
                System.out.println("No data to display for " + title);  // Print out that there is no data to display
                return; 
            }

            // Filter out times that exceed TIME_LIMIT
            List<Integer> filteredIndices = IntStream.range(0, sizes.size())    
                    .filter(i -> times.get(i) <= TIME_LIMIT)    
                    .boxed()    
                    .collect(Collectors.toList());  // Filter out times that exceed TIME_LIMIT

            List<Long> filteredTimes = filteredIndices.stream().map(times::get).collect(Collectors.toList()); 
            List<Integer> filteredSizes = filteredIndices.stream().map(sizes::get).collect(Collectors.toList());

            // Sort filtered times and sizes
            List<Integer> sortedIndices = IntStream.range(0, filteredSizes.size())  // Sort filtered times and sizes
                    .boxed()
                    .sorted((i, j) -> Integer.compare(filteredSizes.get(i), filteredSizes.get(j)))
                    .collect(Collectors.toList());

            List<Long> sortedTimes = sortedIndices.stream().map(filteredTimes::get).collect(Collectors.toList());   // Sort filtered times and sizes
            List<Integer> sortedSizes = sortedIndices.stream().map(filteredSizes::get).collect(Collectors.toList()); // Sort filtered times and sizes

            int width = getWidth(); // Get the width and height of the panel 
            int height = getHeight();   // Get the width and height of the panel
            int padding = 50;   // Set the padding
            int labelPadding = 25;  // Set the label padding
            int pointWidth = 5; // Set the point width

            double xScale = ((double) width - 2 * padding - labelPadding) / (sortedSizes.size() - 1);   // Set the x scale
            double maxTime = sortedTimes.stream().max(Long::compare).orElse(1L);    // Set the max time
            double minTime = sortedTimes.stream().min(Long::compare).orElse(1L);    // Set the min time
            double yScale = ((double) height - 2 * padding - labelPadding) / (maxTime - minTime);   // Set the y scale

            g.drawString(title, (width / 2) - g.getFontMetrics().stringWidth(title) / 2, padding / 2);  // Draw the title

            // Draw white background
            g.setColor(Color.WHITE);
            g.fillRect(padding + labelPadding, padding, width - 2 * padding - labelPadding, height - 2 * padding - labelPadding);
            g.setColor(Color.BLACK);

            // Create hatch marks and grid lines for y axis. The y axis is the time axis. 
            for (int i = 0; i <= 10; i++) { // Create hatch marks and grid lines for y axis
                int x0 = padding + labelPadding;    // Create hatch marks and grid lines for y axis
                int y0 = height - ((i * (height - padding * 2 - labelPadding)) / 10 + padding + labelPadding);  // Create hatch marks and grid lines for y axis
                if (sortedTimes.size() > 0) {   // Create hatch marks and grid lines for y axis
                    g.setColor(Color.BLACK);
                    String yLabel = String.format("%.0f", (minTime + (maxTime - minTime) * i / 10.0));  // Create hatch marks and grid lines for y axis
                    FontMetrics metrics = g.getFontMetrics();   // Create hatch marks and grid lines for y axis
                    int labelWidth = metrics.stringWidth(yLabel);   // Create hatch marks and grid lines for y axis
                    g.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);  // Create hatch marks and grid lines for y axis
                }
                g.drawLine(padding + labelPadding, y0, width - padding, y0);    // Create hatch marks and grid lines for y axis
            }

            // Create hatch marks and grid lines for x axis.
            for (int i = 0; i < sortedSizes.size(); i++) {  // Create hatch marks and grid lines for x axis
                if (sortedSizes.size() > 1) {   // Create hatch marks and grid lines for x axis
                    int x0 = i * (width - padding * 2 - labelPadding) / (sortedSizes.size() - 1) + padding + labelPadding;  // Create hatch marks and grid lines for x axis
                    int x1 = x0;    // Create hatch marks and grid lines for x axis
                    int y0 = height - padding - labelPadding;   // Create hatch marks and grid lines for x axis
                    int y1 = y0 - pointWidth;   // Create hatch marks and grid lines for x axis
                    if ((i % ((int) ((sortedSizes.size() / 10.0)) + 1)) == 0) {  // Create hatch marks and grid lines for x axis
                        g.setColor(Color.BLACK);        // Create hatch marks and grid lines for x axis
                        String xLabel = String.valueOf(sortedSizes.get(i)); // Create hatch marks and grid lines for x axis
                        FontMetrics metrics = g.getFontMetrics();   // Create hatch marks and grid lines for x axis
                        int labelWidth = metrics.stringWidth(xLabel);   // Create hatch marks and grid lines for x axis
                        g.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);    // Create hatch marks and grid lines for x axis
                    }
                    g.drawLine(x0, height - padding - labelPadding, x0, y1);    // Create hatch marks and grid lines for x axis
                }
            }

            // Draw the time points
            g.setColor(Color.BLUE); 
            for (int i = 0; i < sortedTimes.size(); i++) {  //for loop to draw the time points
                int x = (int) (i * xScale + padding + labelPadding);    
                int y = (int) ((maxTime - sortedTimes.get(i)) * yScale + padding);
                g.fillOval(x - pointWidth / 2, y - pointWidth / 2, pointWidth, pointWidth); //fillOval method to draw the time points
            }

            
        }
    }
}
