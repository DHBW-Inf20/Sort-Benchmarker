package Surface;

import javax.swing.*;

public class PlottingWindow extends JFrame
{
    private int width = 800;
    private int height = 800;

    Graphic graphic = new Graphic();

    public PlottingWindow()
    {
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graphic);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void GetPlot (int[] inputSize, int[] workingTime)
    {

    }
}
