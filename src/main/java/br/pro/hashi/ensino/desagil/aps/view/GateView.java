package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GateView extends JPanel implements ItemListener {
    private final Gate gate;
    private final JCheckBox[] inputBoxes;
    private final JCheckBox resultBox;
    private final Switch[] interruptores;


    public GateView(Gate gate) {
        this.gate = gate;
        int inputsize = gate.getInputSize();
        inputBoxes = new JCheckBox[inputsize];
        resultBox = new JCheckBox();
        interruptores = new Switch[inputsize];


        JLabel inputLabel = new JLabel("Entrada:");
        JLabel resultLabel = new JLabel("Saída:");


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(inputLabel);
        for (int i = 0; i < inputsize; i++) {
            inputBoxes[i] = new JCheckBox();
            add(inputBoxes[i]);
            interruptores[i] = new Switch();
            inputBoxes[i].addItemListener(this);

        }
        add(resultLabel);
        add(resultBox);
        resultBox.setEnabled(false);
        update();
    }

    private void update() {
        boolean check;
        for (int i = 0; i < gate.getInputSize(); i++) {
            check = inputBoxes[i].isSelected();
            if (check) {
                interruptores[i].turnOn();
                gate.connect(i, interruptores[i]);
            } else {
                interruptores[i].turnOff();
            }
        }
        resultBox.setSelected(gate.read());

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        update();
    }
}
