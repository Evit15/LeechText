/*    */ package dark.leech.text.ui.material;
/*    */ import dark.leech.text.util.ColorUtils;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.GridLayout;
/*    */ import javax.swing.JLabel;
/*    */ 
/*    */ public class JMMenuItem extends JMenuItem {
/*    */   public JMMenuItem(String text) {
/* 12 */     this.label = new JLabel();
/* 13 */     this.label.setText(text);
/* 14 */     this.label.setHorizontalAlignment(0);
/* 15 */     this.label.setFont(FontUtils.TEXT_NORMAL);
/* 16 */     setBackground(Color.white);
/* 17 */     setBorderPainted(false);
/* 18 */     setLayout(new GridLayout());
/* 19 */     setPreferredSize(new Dimension(90, 30));
/* 20 */     add(this.label);
/*    */   }
/*    */   private JLabel label;
/*    */   
/*    */   protected void paintComponent(Graphics g) {
/* 25 */     super.paintComponent(g);
/* 26 */     Graphics2D g2 = (Graphics2D)g;
/* 27 */     g2.setColor(Color.WHITE);
/* 28 */     this.label.setForeground(ColorUtils.THEME_COLOR);
/* 29 */     if (isArmed()) {
/* 30 */       g2.setColor(ColorUtils.THEME_COLOR);
/* 31 */       this.label.setForeground(Color.WHITE);
/*    */     } 
/* 33 */     g2.fillRect(0, 0, getWidth(), getHeight());
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\material\JMMenuItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */