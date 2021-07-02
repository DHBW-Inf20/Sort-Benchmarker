package Surface;

import javax.swing.*;
import java.awt.*;

public class Graphic extends JPanel
{
    private int strokeWidth = 2;

    public void GetGraphic (Graphics graphics, int[] xPoints, int[] yPoints)
    {
        int numberOfPoints = xPoints.length;

        super.paintComponent(graphics);
        this.setBackground(Color.WHITE);

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(strokeWidth));
        graphics2D.drawPolyline(xPoints, yPoints, numberOfPoints);

        graphics2D.setFont(new Font("TimesRoman", Font.BOLD,10));
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString("0", 0, 800);
        graphics2D.drawString(String.valueOf(xPoints[-1]), 800, 800);
        for (int i = 0; i < numberOfPoints - 1; i++)
        {
            graphics2D.drawString(String.valueOf(xPoints[i]),
                    Math.round(Math.log10(xPoints[i])),800);

        }
    }
}

