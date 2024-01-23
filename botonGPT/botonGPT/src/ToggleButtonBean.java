import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.event.EventListenerList;
import java.io.Serializable;

public class ToggleButtonBean extends JButton implements Serializable {

    private static final long serialVersionUID = 1L;

    private Color auraColor;
    private int auraThickness;
    private boolean toggled;

    private final List<ToggleListener> toggleListeners;

    public ToggleButtonBean() {
        super("");
        auraColor = Color.BLACK; // Color predeterminado
        auraThickness = 5; // Grosor predeterminado
        toggleListeners = new ArrayList<>();

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setToggled(!toggled);
                fireToggleEvent();
            }
        });
    }

    public Color getAuraColor() {
        return auraColor;
    }

    public void setAuraColor(Color auraColor) {
        this.auraColor = auraColor;
        repaint();
    }

    public int getAuraThickness() {
        return auraThickness;
    }

    public void setAuraThickness(int auraThickness) {
        this.auraThickness = auraThickness;
        repaint();
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
        repaint();
    }

    public void addToggleListener(ToggleListener listener) {
        toggleListeners.add(listener);
    }

    public void removeToggleListener(ToggleListener listener) {
        toggleListeners.remove(listener);
    }

    protected void fireToggleEvent() {
        for (ToggleListener listener : toggleListeners) {
            listener.onToggle(toggled);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (toggled) {
            g2d.setColor(auraColor);
            g2d.setStroke(new BasicStroke(auraThickness));
            g2d.drawOval(auraThickness / 2, auraThickness / 2, getWidth() - auraThickness, getHeight() - auraThickness);
        }

        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 50);
    }
}

