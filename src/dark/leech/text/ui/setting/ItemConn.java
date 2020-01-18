/*    */ package dark.leech.text.ui.setting;
/*    */ 
/*    */ import dark.leech.text.ui.material.JMPanel;
/*    */ import dark.leech.text.ui.material.JMTextField;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import javax.swing.JLabel;
/*    */ 
/*    */ public class ItemConn extends JMPanel {
/*    */   private String title;
/*    */   
/*    */   public ItemConn(String title, String text) {
/* 15 */     this.title = title;
/* 16 */     onCreate();
/* 17 */     setText(text);
/*    */   }
/*    */   private JMTextField tf;
/*    */   private void onCreate() {
/* 21 */     setBackground(Color.white);
/* 22 */     setLayout(null);
/* 23 */     JLabel lbName = new JLabel(this.title);
/* 24 */     lbName.setFont(FontUtils.TEXT_BOLD);
/* 25 */     add(lbName);
/* 26 */     lbName.setBounds(25, 5, 250, 30);
/* 27 */     this.tf = new JMTextField();
/* 28 */     add((Component)this.tf);
/* 29 */     this.tf.setBounds(300, 3, 60, 35);
/* 30 */     setPreferredSize(new Dimension(370, 40));
/*    */   }
/*    */   
/*    */   public String getText() {
/* 34 */     return this.tf.getText();
/*    */   }
/*    */   
/*    */   public void setText(String text) {
/* 38 */     this.tf.setText(text);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\setting\ItemConn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */