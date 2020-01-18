/*    */ package dark.leech.text.ui.material;
/*    */ 
/*    */ import dark.leech.text.util.ColorUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import javax.swing.JScrollBar;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.border.LineBorder;
/*    */ 
/*    */ public class JMScrollPane extends JScrollPane {
/*    */   public JMScrollPane(Component view) {
/* 13 */     super(view);
/* 14 */     JScrollBar sb = getVerticalScrollBar();
/* 15 */     sb.setUI(new JMScrollBar());
/* 16 */     sb.setBackground(Color.WHITE);
/* 17 */     sb.setPreferredSize(new Dimension(10, 0));
/* 18 */     setBorder(null);
/* 19 */     setHorizontalScrollBarPolicy(31);
/* 20 */     getVerticalScrollBar().setUnitIncrement(20);
/*    */   }
/*    */   
/*    */   public JMScrollPane() {
/* 24 */     JScrollBar sb = getVerticalScrollBar();
/* 25 */     sb.setUI(new JMScrollBar());
/* 26 */     sb.setBackground(Color.WHITE);
/* 27 */     sb.setPreferredSize(new Dimension(10, 0));
/* 28 */     setBorder(new LineBorder(ColorUtils.THEME_COLOR, 0));
/* 29 */     getVerticalScrollBar().setUnitIncrement(20);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\material\JMScrollPane.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */