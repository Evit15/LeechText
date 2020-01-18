/*    */ package dark.leech.text.ui.setting;
/*    */ 
/*    */ import dark.leech.text.listeners.ChangeListener;
/*    */ import dark.leech.text.ui.material.JMPanel;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Cursor;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class Theme
/*    */   extends JMPanel
/*    */ {
/*    */   private Color color;
/*    */   private JPanel colorPn;
/*    */   
/*    */   public Theme(Color color) {
/* 21 */     this.color = color;
/* 22 */     onCreate();
/*    */   }
/*    */   
/*    */   private void onCreate() {
/* 26 */     setBackground(Color.white);
/* 27 */     setLayout(null);
/* 28 */     JLabel labelName = new JLabel("Chủ đề");
/* 29 */     labelName.setFont(FontUtils.TEXT_BOLD);
/* 30 */     add(labelName);
/* 31 */     labelName.setBounds(25, 5, 250, 30);
/* 32 */     this.colorPn = new JPanel();
/* 33 */     this.colorPn.setBackground(this.color);
/* 34 */     this.colorPn.setCursor(new Cursor(12));
/* 35 */     this.colorPn.addMouseListener(new MouseAdapter()
/*    */         {
/*    */           public void mouseClicked(MouseEvent e) {
/* 38 */             Theme.this.clickChooserColor();
/*    */           }
/*    */         });
/* 41 */     add(this.colorPn);
/* 42 */     this.colorPn.setBounds(335, 5, 30, 30);
/* 43 */     setPreferredSize(new Dimension(370, 40));
/*    */   }
/*    */   
/*    */   private void clickChooserColor() {
/* 47 */     final ChooserColor chooserColor = new ChooserColor(this.color);
/*    */     
/* 49 */     chooserColor.setChangeListener(new ChangeListener()
/*    */         {
/*    */           public void doChanger() {
/* 52 */             Theme.this.color = chooserColor.getChooserColor();
/* 53 */             Theme.this.colorPn.setBackground(Theme.this.color);
/*    */           }
/*    */         });
/* 56 */     chooserColor.open();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setThemeColor(Color color) {
/* 61 */     this.colorPn.setBackground(color);
/* 62 */     this.color = color;
/*    */   }
/*    */   
/*    */   public Color getColor() {
/* 66 */     return this.color;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\setting\Theme.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */