/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.maxton;

import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.JApplet;

/**
 *
 * @author Max
 */
public class RootPanel extends javax.swing.JPanel {
    private JApplet applet;
    private MainMenuPanel mainMenuPanel;
    private GamePanel gamePanel;
    /**
     * Creates new form RootPanel
     */
    public RootPanel() {
        initComponents();
    }
    public void init(){
        mainMenu();
    }
    public void mainMenu(){
        mainMenuPanel = new MainMenuPanel(this);
        this.removeAll();
        this.revalidate();
        this.repaint();
        this.addFullSize(mainMenuPanel);
    }
    public void startGame(){
        this.removeAll();
        this.mainMenuPanel = null;
        gamePanel = new GamePanel(this);
        this.revalidate();
        this.repaint();
        this.addFullSize(gamePanel);
        gamePanel.repaint();
    }
    public void postGame(long score){
        this.removeAll();
        this.gamePanel = null;
        this.revalidate();
        this.repaint();
        this.addFullSize(new PostGamePanel(this,score));
        
    }
    public void highScorePanel(){
        this.removeAll();
        this.revalidate();
        this.repaint();
        this.addFullSize(new HighScorePanel(this));
    }
    private void addFullSize(Component c){
        GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(c, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(c, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        this.revalidate();
        this.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 320, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 480, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents
  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
}