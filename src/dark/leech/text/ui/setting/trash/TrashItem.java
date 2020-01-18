/*     */ package dark.leech.text.ui.setting.trash;
/*     */ 
/*     */ import dark.leech.text.listeners.BlurListener;
/*     */ import dark.leech.text.listeners.ChangeListener;
/*     */ import dark.leech.text.listeners.RemoveListener;
/*     */ import dark.leech.text.models.Trash;
/*     */ import dark.leech.text.ui.button.CircleButton;
/*     */ import dark.leech.text.ui.button.SelectButton;
/*     */ import dark.leech.text.ui.material.DropShadowBorder;
/*     */ import dark.leech.text.ui.material.JMPanel;
/*     */ import dark.leech.text.util.ColorUtils;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import dark.leech.text.util.SettingUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ class TrashItem
/*     */   extends JMPanel
/*     */ {
/*     */   private JLabel labelName;
/*     */   private CircleButton buttonEdit;
/*     */   private CircleButton buttonDelete;
/*     */   private SelectButton btSelect;
/*     */   private Trash trash;
/*     */   private RemoveListener removeListener;
/*     */   private BlurListener blurListener;
/*     */   
/*     */   public TrashItem(Trash trash) {
/*  34 */     this.trash = trash;
/*  35 */     gui();
/*     */   }
/*     */   
/*     */   private void gui() {
/*  39 */     setBackground(Color.white);
/*  40 */     setLayout(null);
/*  41 */     this.labelName = new JLabel();
/*  42 */     this.btSelect = new SelectButton();
/*     */     
/*  44 */     this.labelName.setText(this.trash.getTip());
/*  45 */     this.labelName.setFont(FontUtils.TEXT_NORMAL);
/*  46 */     add(this.labelName);
/*  47 */     this.labelName.setBounds(10, 5, 180, 30);
/*     */     
/*  49 */     this.buttonEdit = new CircleButton("");
/*  50 */     this.buttonEdit.setForeground(ColorUtils.THEME_COLOR);
/*  51 */     this.buttonEdit.setToolTipText("Sửa");
/*     */     
/*  53 */     this.buttonEdit.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  56 */             TrashItem.this.doEdit();
/*     */           }
/*     */         });
/*  59 */     add((Component)this.buttonEdit);
/*  60 */     this.buttonEdit.setBounds(220, 5, 30, 30);
/*     */     
/*  62 */     this.btSelect.setSelected(this.trash.isReplace());
/*  63 */     add((Component)this.btSelect);
/*  64 */     this.btSelect.setBounds(250, 5, 30, 30);
/*     */     
/*  66 */     this.buttonDelete = new CircleButton("");
/*  67 */     this.buttonDelete.setForeground(ColorUtils.THEME_COLOR);
/*  68 */     this.buttonDelete.setToolTipText("Xóa");
/*  69 */     this.buttonDelete.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  72 */             TrashItem.this.actionDelete();
/*     */           }
/*     */         });
/*  75 */     add((Component)this.buttonDelete);
/*  76 */     this.buttonDelete.setBounds(280, 5, 30, 30);
/*     */     
/*  78 */     setBorder((Border)new DropShadowBorder(SettingUtils.THEME_COLOR, 5, 3));
/*  79 */     setPreferredSize(new Dimension(300, 45));
/*     */   }
/*     */   
/*     */   public void addRemoveListener(RemoveListener removeListener) {
/*  83 */     this.removeListener = removeListener;
/*     */   }
/*     */   
/*     */   public void addBlurListener(BlurListener blurListener) {
/*  87 */     this.blurListener = blurListener;
/*     */   }
/*     */   
/*     */   public Trash getTrash() {
/*  91 */     this.trash.setReplace(this.btSelect.isSelected());
/*  92 */     return this.trash;
/*     */   }
/*     */   
/*     */   private void actionDelete() {
/*  96 */     this.removeListener.removeComponent((Component)this);
/*     */   }
/*     */   
/*     */   private void doEdit() {
/* 100 */     final TrashItemIDialog tiDialog = new TrashItemIDialog(this.trash);
/* 101 */     tiDialog.setBlurListener(this.blurListener);
/* 102 */     tiDialog.setChangeListener(new ChangeListener()
/*     */         {
/*     */           public void doChanger() {
/* 105 */             TrashItem.this.trash = tiDialog.getTrash();
/* 106 */             TrashItem.this.labelName.setText(TrashItem.this.trash.getTip());
/*     */           }
/*     */         });
/* 109 */     tiDialog.open();
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\setting\trash\TrashItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */