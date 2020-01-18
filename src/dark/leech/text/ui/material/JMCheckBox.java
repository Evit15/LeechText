/*    */ package dark.leech.text.ui.material;
/*    */ 
/*    */ import dark.leech.text.listeners.ChangeListener;
/*    */ import dark.leech.text.ui.button.SelectButton;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import java.awt.Component;
/*    */ import javax.swing.JLabel;
/*    */ 
/*    */ 
/*    */ public class JMCheckBox
/*    */   extends JMPanel
/*    */ {
/*    */   private boolean TEXT_ON_RIGHT;
/*    */   private JLabel lbName;
/*    */   private String name;
/*    */   private SelectButton checkbox;
/*    */   
/*    */   public JMCheckBox(String name) {
/* 19 */     this.name = name;
/* 20 */     onCreate();
/*    */   }
/*    */   
/*    */   public JMCheckBox(String name, boolean TEXT_ON_RIGHT) {
/* 24 */     this.name = name;
/* 25 */     this.TEXT_ON_RIGHT = TEXT_ON_RIGHT;
/* 26 */     onCreate();
/*    */   }
/*    */   
/*    */   private void onCreate() {
/* 30 */     this.lbName = new JLabel(this.name);
/* 31 */     this.lbName.setFont(FontUtils.TEXT_NORMAL);
/* 32 */     add(this.lbName);
/* 33 */     this.checkbox = new SelectButton();
/* 34 */     this.checkbox.setFont(FontUtils.ICON_NORMAL);
/* 35 */     add((Component)this.checkbox);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBounds(int x, int y, int width, int height) {
/* 40 */     super.setBounds(x, y, width, height);
/* 41 */     if (this.TEXT_ON_RIGHT) {
/* 42 */       this.lbName.setBounds(height, 0, (this.lbName.getPreferredSize()).width, height);
/* 43 */       this.checkbox.setBounds(0, 0, height, height);
/*    */     } else {
/* 45 */       this.lbName.setBounds(0, 0, (this.lbName.getPreferredSize()).width, height);
/* 46 */       this.checkbox.setBounds(width - height, 0, height, height);
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean isChecked() {
/* 51 */     return this.checkbox.isSelected();
/*    */   }
/*    */   
/*    */   public void setChecked(boolean checked) {
/* 55 */     this.checkbox.setSelected(checked);
/*    */   }
/*    */   
/*    */   public void setChangeListener(ChangeListener changeListener) {
/* 59 */     this.checkbox.setChangeListener(changeListener);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\material\JMCheckBox.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */