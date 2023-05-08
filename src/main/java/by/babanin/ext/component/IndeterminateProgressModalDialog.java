package by.babanin.ext.component;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public final class IndeterminateProgressModalDialog extends JDialog {

    private static final int DEFAULT_WIDTH = 350;

    public IndeterminateProgressModalDialog(Frame owner) {
        super(owner, "", ModalityType.APPLICATION_MODAL);
        init();
    }

    public IndeterminateProgressModalDialog(Dialog owner) {
        super(owner, "", ModalityType.APPLICATION_MODAL);
        init();
    }

    public IndeterminateProgressModalDialog(Window owner) {
        super(owner, "", ModalityType.APPLICATION_MODAL);
        init();
    }

    public void init() {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setMinimumSize(new Dimension(DEFAULT_WIDTH, progressBar.getMinimumSize().height));
        progressBar.setPreferredSize(progressBar.getMinimumSize());
        JPanel panel = new JPanel();
        panel.add(progressBar);

        setContentPane(panel);
        setResizable(false);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(getOwner());
        setMinimumSize(getSize());
    }
}
