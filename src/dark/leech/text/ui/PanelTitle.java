/*    */ package dark.leech.text.ui;
/*    */ 
/*    */ import dark.leech.text.ui.button.CloseButton;
/*    */ import dark.leech.text.util.ColorUtils;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.LayoutManager;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class PanelTitle
/*    */   extends JPanel {
/* 15 */   JLabel lbTitle = new JLabel();
/* 16 */   CloseButton btClose = new CloseButton();
/*    */   
/*    */   public void setText(String text) {
/* 19 */     this.lbTitle.setText(text);
/*    */   }
/*    */   
/*    */   public void addCloseListener(ActionListener actionListener) {
/* 23 */     this.btClose.addActionListener(actionListener);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBounds(int x, int y, int width, int height) {
/* 28 */     super.setBounds(x, y, width, height);
/* 29 */     setBackground(ColorUtils.THEME_COLOR);
/* 30 */     setLayout((LayoutManager)null);
/* 31 */     this.lbTitle.setFont(FontUtils.TITLE_NORMAL);
/* 32 */     this.lbTitle.setForeground(Color.WHITE);
/* 33 */     add(this.lbTitle);
/* 34 */     this.lbTitle.setBounds(20, 0, width - 60, height);
/* 35 */     add((Component)this.btClose);
/* 36 */     this.btClose.setBounds(width - 35, 10, height - 20, height - 20);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\PanelTitle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */