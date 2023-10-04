package fitsystem;

import java.awt.*;


public class GridBagConstraintsFactory {
    
    
    private GridBagConstraintsFactory() {}
    
    public static GridBagConstraints createGbc(int gridx, int gridy) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        int gap = 3;
        gbc.insets = new Insets(gap, gap + 2 * gap * gridx, gap, gap);
        return gbc;
    }
    
    public static GridBagConstraints createGbc(int gridx, int gridy, double weightx, double weighty, int fill) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = fill;
        int gap = 3;
        gbc.insets = new Insets(gap, gap + 2 * gap * gridx, gap, gap);
        return gbc;
    }
    
    public static GridBagConstraints createGbc(int gridx, int gridy, double weightx, double weighty, int fill, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = fill;
        gbc.anchor = anchor;
        int gap = 3;
        gbc.insets = new Insets(gap, gap + 2 * gap * gridx, gap, gap);
        return gbc;
    }
    
}
