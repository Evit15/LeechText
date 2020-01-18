/*     */ package dark.leech.text.ui.setting;
/*     */ 
/*     */ import dark.leech.text.listeners.ChangeListener;
/*     */ import dark.leech.text.ui.SyntaxDialog;
/*     */ import dark.leech.text.ui.button.CircleButton;
/*     */ import dark.leech.text.ui.button.SelectButton;
/*     */ import dark.leech.text.ui.material.JMPanel;
/*     */ import dark.leech.text.util.ColorUtils;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JLabel;
/*     */ 
/*     */ 
/*     */ public class ItemStyle
/*     */   extends JMPanel
/*     */ {
/*     */   private JLabel labelTitle;
/*     */   private JLabel labelInfo;
/*     */   private SelectButton btSelect;
/*     */   
/*     */   public ItemStyle() {
/*  27 */     this("", "", false);
/*     */   }
/*     */   private CircleButton btEdit; private String name; private String style; private String text;
/*     */   public ItemStyle(String name, String tip, boolean selected) {
/*  31 */     this.name = name;
/*  32 */     onCreate();
/*  33 */     setName(name);
/*  34 */     this.labelInfo.setText(tip);
/*  35 */     this.labelTitle.setText(name);
/*     */   }
/*     */   
/*     */   public boolean isSelected() {
/*  39 */     return this.btSelect.isSelected();
/*     */   }
/*     */   
/*     */   public void setSelected(boolean selected) {
/*  43 */     this.btSelect.setSelected(selected);
/*  44 */     this.btEdit.setVisible(selected);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStyle(String style) {
/*  49 */     this.style = style;
/*     */   }
/*     */   
/*     */   public String getText() {
/*  53 */     return this.text;
/*     */   }
/*     */   
/*     */   public void setText(String text) {
/*  57 */     this.text = text;
/*     */   }
/*     */   
/*     */   private void onCreate() {
/*  61 */     this.labelTitle = new JLabel();
/*  62 */     this.labelInfo = new JLabel();
/*  63 */     this.btSelect = new SelectButton();
/*     */ 
/*     */     
/*  66 */     this.labelTitle.setFont(FontUtils.TEXT_BOLD);
/*  67 */     add(this.labelTitle);
/*  68 */     this.labelTitle.setBounds(25, 5, 290, 30);
/*     */ 
/*     */     
/*  71 */     add(this.labelInfo);
/*  72 */     this.labelInfo.setFont(FontUtils.TEXT_THIN);
/*  73 */     this.labelInfo.setForeground(Color.GRAY);
/*  74 */     this.labelInfo.setBounds(25, 30, 290, 30);
/*     */ 
/*     */     
/*  77 */     add((Component)this.btSelect);
/*  78 */     this.btSelect.setChangeListener(new ChangeListener()
/*     */         {
/*     */           public void doChanger() {
/*  81 */             ItemStyle.this.btEdit.setVisible(ItemStyle.this.btSelect.isSelected());
/*     */           }
/*     */         });
/*  84 */     this.btSelect.setBounds(335, 15, 30, 30);
/*     */ 
/*     */     
/*  87 */     this.btEdit = new CircleButton("");
/*  88 */     this.btEdit.setForeground(ColorUtils.THEME_COLOR);
/*  89 */     this.btEdit.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  92 */             final SyntaxDialog edit = new SyntaxDialog(ItemStyle.this.name, ItemStyle.this.text, ItemStyle.this.style);
/*  93 */             edit.setChangeListener(new ChangeListener()
/*     */                 {
/*     */                   public void doChanger() {
/*  96 */                     ItemStyle.this.text = edit.getText();
/*     */                   }
/*     */                 });
/*  99 */             edit.open();
/*     */           }
/*     */         });
/*     */     
/* 103 */     this.btEdit.setCursor(new Cursor(12));
/* 104 */     add((Component)this.btEdit);
/* 105 */     this.btEdit.setBounds(300, 15, 30, 30);
/* 106 */     setPreferredSize(new Dimension(370, 60));
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\setting\ItemStyle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */