/*    */ package dark.leech.text.ui.material;
/*    */ import dark.leech.text.animation.RippleEffect;
/*    */ import dark.leech.text.util.SettingUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.LayoutManager;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class JMPanel extends JPanel {
/*    */   public JMPanel(LayoutManager layoutManager) {
/* 15 */     super(layoutManager);
/* 16 */     setBackground(Color.WHITE);
/* 17 */     this.rippleEffect = RippleEffect.applyTo(this);
/* 18 */     addMouseListener(new MouseAdapter()
/*    */         {
/*    */           public void mouseClicked(MouseEvent e) {
/* 21 */             JMPanel.this.repaint();
/*    */           }
/*    */         });
/*    */   }
/*    */   RippleEffect rippleEffect;
/*    */   public JMPanel() {
/* 27 */     setLayout((LayoutManager)null);
/* 28 */     setBackground(Color.WHITE);
/* 29 */     this.rippleEffect = RippleEffect.applyTo(this);
/* 30 */     addMouseListener(new MouseAdapter()
/*    */         {
/*    */           public void mouseClicked(MouseEvent e) {
/* 33 */             JMPanel.this.repaint();
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void paintComponent(Graphics g) {
/* 41 */     super.paintComponent(g);
/* 42 */     Graphics2D g2 = (Graphics2D)g;
/* 43 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 44 */     g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/* 45 */     g2.setColor(SettingUtils.THEME_COLOR);
/* 46 */     this.rippleEffect.paint(g2);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\material\JMPanel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */