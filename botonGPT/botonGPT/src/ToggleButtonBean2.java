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

public class ToggleButtonBean2 extends JButton implements Serializable {

    private static final long serialVersionUID = 1L;

    private Color auraColor1;
    private Color auraColor2;
    private int auraThickness;
    private boolean toggled;
    private boolean colorToggle;

    private final List<ToggleListener> toggleListeners;

    public ToggleButtonBean2() {
        super("");
        auraThickness = 20; // Grosor predeterminado
        toggleListeners = new ArrayList<>();
        colorToggle = true;

        // Establecer dos colores predefinidos
        auraColor1 = Color.BLUE;
        auraColor2 = Color.RED;

        // Establecer el color aleatorio por defecto
        setAuraColor1(getNextAuraColor(auraColor1, auraColor2));

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setToggled(!toggled);
                // Cambiar el color al hacer clic
                fireToggleEvent();
            }
        });
    }

    private Color getNextAuraColor(Color color1, Color color2) {
        colorToggle = !colorToggle;
        return colorToggle ? color1 : color2;
    }

    public Color getAuraColor1() {
        return auraColor1;
    }
    
    public Color getAuraColor2() {
        return auraColor2;
    }

    public void setAuraColor1(Color auraColor1) {
        this.auraColor1 = auraColor1;
        repaint();
    }
    
    public void setAuraColor2(Color auraColor2) {
        this.auraColor2 = auraColor2;
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


        if(!toggled) {
        	g2d.setColor(getAuraColor1());
        }else {
        	g2d.setColor(getAuraColor2());
        }
        g2d.setStroke(new BasicStroke(auraThickness));
        g2d.drawOval(auraThickness / 2, auraThickness / 2, getWidth() - auraThickness, getHeight() - auraThickness);
        

        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 50);
    }
}


