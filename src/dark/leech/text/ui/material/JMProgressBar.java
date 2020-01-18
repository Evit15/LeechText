/*    */ package dark.leech.text.ui.material;
/*    */ 
/*    */ import dark.leech.text.util.ColorUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ public class JMProgressBar
/*    */   extends JPanel
/*    */ {
/*    */   private int percent;
/*    */   
/*    */   public JMProgressBar() {
/* 15 */     setBackground(Color.WHITE);
/*    */   }
/*    */   
/*    */   public int getPercent() {
/* 19 */     return this.percent;
/*    */   }
/*    */   
/*    */   public void setPercent(int percent) {
/* 23 */     this.percent = percent;
/* 24 */     repaint();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintComponent(Graphics g) {
/* 29 */     super.paintComponent(g);
/* 30 */     int w = getWidth();
/* 31 */     int h = getHeight();
/* 32 */     int loadw = this.percent * w / 100;
/* 33 */     g.setColor(new Color(204, 204, 255));
/* 34 */     g.fillRect(0, 0, w, h);
/* 35 */     g.setColor(ColorUtils.THEME_COLOR);
/* 36 */     g.fillRect(0, 0, loadw, h);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\material\JMProgressBar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */