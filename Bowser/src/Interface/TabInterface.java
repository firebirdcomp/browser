/*
 * Project by Firebird. All rigths reserved to Felpi and Luis.
 */
package Interface;

import Interpreter.Interpreter;
import Render.Render;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author luise
 */
public class TabInterface extends javax.swing.JPanel {
    JPanel tabBlock;
    
    /**
     * Creates new form TabInterface
     */
    public TabInterface() {
        initComponents();
//        Create new panel which have everything from tab content insite it
        tabBlock = new javax.swing.JPanel();
//        Set table name (page's title)
        Random rand = new Random();
        // Obtain a number between [0 - 49].
        int number = rand.nextInt(50);
        tabBlock.setName("Nova Guia"+number);
        
//        Init toolbar and add to the panel
        ToolbarInterface toolbar = new ToolbarInterface();
        tabBlock.add(toolbar);
        
//        Init editor panel
        JEditorPane editorPanel = new JEditorPane();
        editorPanel.setSize(1366,700);
        JScrollPane scrollPane = new JScrollPane(editorPanel);

//      Criar arvore
        Interpreter interpreter = new Interpreter();
        interpreter.Interpreter("");

//      Renderiza arvore
        Render render = new Render();
        render.startRender(interpreter, editorPanel);
        
        
        tabBlock.add(scrollPane);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(768, 525));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
