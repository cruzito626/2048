package j2048frontend.gui.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TerminarEvento implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
