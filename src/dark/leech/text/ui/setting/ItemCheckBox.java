/*    */ package dark.leech.text.ui.setting;
/*    */ 
/*    */ import dark.leech.text.ui.material.JMPanel;
/*    */ import dark.leech.text.util.ColorUtils;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.JLabel;
/*    */ 
/*    */ public class ItemCheckBox
/*    */   extends JMPanel {
/*    */   private JLabel lbName;
/*    */   private JLabel lbSelect;
/*    */   private boolean selected;
/*    */   private String name;
/*    */   
/*    */   public ItemCheckBox(String name) {
/* 20 */     this.name = name;
/* 21 */     onCreate();
/* 22 */     setSelected(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSelected() {
/* 27 */     return this.selected;
/*    */   }
/*    */   
/*    */   public void setSelected(boolean selected) {
/* 31 */     this.lbSelect.setText(selected ? "" : "");
/* 32 */     this.selected = selected;
/*    */   }
/*    */   
/*    */   private void onCreate() {
/* 36 */     setBackground(Color.white);
/* 37 */     addMouseListener(new MouseAdapter()
/*    */         {
/*    */           public void mouseClicked(MouseEvent e) {
/* 40 */             ItemCheckBox.this.setSelected(!ItemCheckBox.this.selected);
/*    */           }
/*    */         });
/* 43 */     setLayout(null);
/* 44 */     this.lbName = new JLabel(this.name);
/* 45 */     this.lbSelect = new JLabel();
/* 46 */     this.lbName.setFont(FontUtils.TEXT_BOLD);
/* 47 */     this.lbName.setBounds(25, 5, 310, 30);
/* 48 */     this.lbSelect.setForeground(ColorUtils.THEME_COLOR);
/* 49 */     this.lbSelect.setFont(FontUtils.ICON_NORMAL);
/* 50 */     this.lbSelect.setHorizontalAlignment(0);
/* 51 */     this.lbSelect.setBounds(335, 5, 30, 30);
/* 52 */     add(this.lbName);
/* 53 */     add(this.lbSelect);
/* 54 */     setPreferredSize(new Dimension(370, 40));
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\setting\ItemCheckBox.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */