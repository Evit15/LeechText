/*    */ package dark.leech.text.ui.button;
/*    */ 
/*    */ import dark.leech.text.listeners.ChangeListener;
/*    */ import dark.leech.text.util.ColorUtils;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JButton;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SelectButton
/*    */   extends JButton
/*    */ {
/*    */   private boolean selected;
/*    */   private ChangeListener changeListener;
/*    */   
/*    */   public SelectButton() {
/* 21 */     setSelected(this.selected);
/* 22 */     setBackground(Color.WHITE);
/* 23 */     setForeground(ColorUtils.BUTTON_TEXT);
/* 24 */     setFont(FontUtils.ICON_NORMAL);
/* 25 */     setHorizontalAlignment(0);
/* 26 */     setVerticalAlignment(0);
/* 27 */     setFocusable(false);
/* 28 */     addActionListener(new ActionListener()
/*    */         {
/*    */           public void actionPerformed(ActionEvent e) {
/* 31 */             SelectButton.this.setSelected(!SelectButton.this.selected);
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   public void setSelected(boolean selected) {
/* 37 */     setText(selected ? "" : "");
/* 38 */     this.selected = selected;
/* 39 */     if (this.changeListener != null) this.changeListener.doChanger(); 
/*    */   }
/*    */   
/*    */   public boolean isSelected() {
/* 43 */     return this.selected;
/*    */   }
/*    */   
/*    */   public void setChangeListener(ChangeListener changeListener) {
/* 47 */     this.changeListener = changeListener;
/*    */   }
/*    */   
/*    */   public void setBounds(int x, int y, int width, int height) {
/* 51 */     super.setBounds(x, y, width, height);
/* 52 */     Color bc = ColorUtils.BUTTON_CLICK;
/* 53 */     Color background = new Color(bc.getRed(), bc.getGreen(), bc.getBlue(), 100);
/* 54 */     ButtonUI styledButton = new ButtonUI(true);
/* 55 */     styledButton.setRolloverBackground(background);
/* 56 */     setUI(styledButton);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\button\SelectButton.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */