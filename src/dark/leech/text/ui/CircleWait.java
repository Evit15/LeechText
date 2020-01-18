/*    */ package dark.leech.text.ui;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import javax.swing.JLayer;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CircleWait
/*    */ {
/*    */   private JLayer<JPanel> jlayer;
/* 17 */   WaitLayerUI layerUI = new WaitLayerUI();
/*    */   
/*    */   public CircleWait(Dimension dimension) {
/* 20 */     JPanel pn = new JPanel();
/* 21 */     pn.setOpaque(false);
/* 22 */     pn.setPreferredSize(dimension);
/* 23 */     this.jlayer = new JLayer<>(pn, this.layerUI);
/*    */   }
/*    */   
/*    */   public JLayer<JPanel> getJlayer() {
/* 27 */     return this.jlayer;
/*    */   }
/*    */   
/*    */   public void startWait() {
/* 31 */     this.layerUI.start();
/*    */   }
/*    */   
/*    */   public void stopWait() {
/* 35 */     this.layerUI.stop();
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\CircleWait.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */