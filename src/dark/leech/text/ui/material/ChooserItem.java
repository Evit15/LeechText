/*     */ package dark.leech.text.ui.material;
/*     */ 
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.JLabel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ChooserItem
/*     */   extends JMPanel
/*     */ {
/*     */   private JLabel labelName;
/*     */   private JLabel labelSelect;
/*     */   private boolean selected;
/*     */   
/*     */   public ChooserItem(String name) {
/* 146 */     setLayout((LayoutManager)null);
/* 147 */     setOpaque(true);
/* 148 */     setBackground(Color.white);
/* 149 */     this.labelName = new JLabel(name);
/* 150 */     this.labelName.setFont(FontUtils.TEXT_NORMAL);
/* 151 */     add(this.labelName);
/* 152 */     this.labelName.setBounds(10, 0, 130, 40);
/* 153 */     this.labelSelect = new JLabel();
/* 154 */     this.labelSelect.setFont(FontUtils.ICON_NORMAL);
/* 155 */     this.labelSelect.setHorizontalAlignment(0);
/* 156 */     add(this.labelSelect);
/* 157 */     this.labelSelect.setBounds(150, 0, 40, 40);
/* 158 */     setPreferredSize(new Dimension(200, 40));
/*     */   }
/*     */   
/*     */   public boolean isSelected() {
/* 162 */     return this.selected;
/*     */   }
/*     */   
/*     */   public void setSelected(boolean selected) {
/* 166 */     this.selected = selected;
/* 167 */     this.labelSelect.setText(selected ? "" : "");
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\material\ChooserItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */