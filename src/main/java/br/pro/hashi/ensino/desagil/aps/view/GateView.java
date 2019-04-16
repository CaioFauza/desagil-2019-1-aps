package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener, MouseListener {
    private final Switch[] switches;
    private final Gate gate;
    private final Light light;


    private final JCheckBox[] inputBoxes;

    private final Image image;


    public GateView(Gate gate) {
        super(345, 300);
        this.gate = gate;

        int inputSize = gate.getInputSize();
        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];
        light = new Light();
        light.connect(0, gate);

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();
            gate.connect(i, switches[i]);

        }

        light.setR(255);


        JLabel inputLabel = new JLabel("Input");
        JLabel outputLabel = new JLabel("Output");


        add(inputLabel);
        int counter = -1;
        for (JCheckBox inputBox : inputBoxes) {
            counter += 1;
            if (inputSize == 1) {
                add(inputBox, 60, 113, 25, 25);
            } else {
                if (counter == 0) {
                    add(inputBox, 60, 85, 25, 25);
                } else {
                    add(inputBox, 60, 128, 25, 25);
                }


            }

        }
        add(outputLabel);


        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);
        addMouseListener(this);
        update();
    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }
        repaint();


    }

    @Override
    public void mouseClicked(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
        Color temporaryColor;

        if (Math.pow((x - 275.5), 2) + Math.pow((y - 122.5), 2) < 156.25) {
            //x coordinate + radius, y coordinate + radius, radius^2
            temporaryColor = JColorChooser.showDialog(this, null, new Color(light.getR(), light.getG(), light.getB()));
            try {
                light.setR(temporaryColor.getRed());
                light.setG(temporaryColor.getGreen());
                light.setB(temporaryColor.getBlue());

            } catch (Exception ex) {
                ;
            }


            repaint();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
    }

    @Override
    public void mousePressed(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 80, 80, 179, 87, this);
        g.setColor(new Color(light.getR(), light.getG(), light.getB()));
        g.fillOval(263, 110, 25, 25);
        getToolkit().sync();
    }
}
