/*     */ package dark.leech.text.ui.main;
/*     */ 
/*     */ import dark.leech.text.listeners.BlurListener;
/*     */ import dark.leech.text.listeners.ChangeListener;
/*     */ import dark.leech.text.models.Properties;
/*     */ import dark.leech.text.ui.SyntaxDialog;
/*     */ import dark.leech.text.ui.button.CircleButton;
/*     */ import dark.leech.text.ui.button.SelectButton;
/*     */ import dark.leech.text.ui.material.JMPanel;
/*     */ import dark.leech.text.util.ColorUtils;
/*     */ import dark.leech.text.util.FileUtils;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
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
/*     */ class GioiThieu
/*     */   extends JMPanel
/*     */ {
/*     */   private JLabel lbName;
/*     */   private CircleButton btEdit;
/*     */   private SelectButton btSelect;
/*     */   private Properties properties;
/*     */   private BlurListener blurListener;
/*     */   
/*     */   public GioiThieu(Properties properties) {
/* 215 */     this.properties = properties;
/* 216 */     onCreate();
/*     */   }
/*     */   
/*     */   private void onCreate() {
/* 220 */     this.lbName = new JLabel();
/* 221 */     this.btSelect = new SelectButton();
/*     */     
/* 223 */     this.lbName.setText("Thêm trang giới thiệu");
/* 224 */     this.lbName.setFont(FontUtils.TEXT_NORMAL);
/* 225 */     add(this.lbName);
/* 226 */     this.lbName.setBounds(0, 0, 150, 30);
/*     */     
/* 228 */     this.btEdit = new CircleButton("");
/* 229 */     this.btEdit.setForeground(ColorUtils.THEME_COLOR);
/* 230 */     this.btEdit.setToolTipText("Sửa");
/*     */     
/* 232 */     this.btEdit.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 235 */             GioiThieu.this.doEdit();
/*     */           }
/*     */         });
/* 238 */     add((Component)this.btEdit);
/* 239 */     this.btEdit.setBounds(160, 0, 30, 30);
/* 240 */     this.btEdit.setVisible(false);
/* 241 */     this.btSelect.setSelected(false);
/* 242 */     add((Component)this.btSelect);
/* 243 */     this.btSelect.setBounds(190, 0, 30, 30);
/* 244 */     this.btSelect.setChangeListener(new ChangeListener()
/*     */         {
/*     */           public void doChanger() {
/* 247 */             GioiThieu.this.doChanger();
/*     */           }
/*     */         });
/* 250 */     setPreferredSize(new Dimension(300, 40));
/*     */   }
/*     */   
/*     */   private void doChanger() {
/* 254 */     this.btEdit.setVisible(this.btSelect.isSelected());
/* 255 */     this.properties.setAddGt(this.btSelect.isSelected());
/* 256 */     if (this.properties.isAddGt()) {
/* 257 */       File file = new File(this.properties.getSavePath() + "/raw/gioithieu.txt");
/* 258 */       if (!file.exists()) {
/* 259 */         FileUtils.string2file(this.properties.getGioiThieu(), this.properties.getSavePath() + "/raw/gioithieu.txt");
/*     */       } else {
/* 261 */         this.properties.setGioiThieu(FileUtils.file2string(this.properties.getSavePath() + "/raw/gioithieu.txt"));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void doEdit() {
/* 266 */     final SyntaxDialog editDialog = new SyntaxDialog("Giới thiệu", this.properties.getGioiThieu(), "text/html");
/* 267 */     editDialog.setBlurListener(this.blurListener);
/* 268 */     editDialog.setChangeListener(new ChangeListener()
/*     */         {
/*     */           public void doChanger() {
/* 271 */             GioiThieu.this.properties.setGioiThieu(editDialog.getText());
/* 272 */             if (GioiThieu.this.properties.isAddGt())
/* 273 */               FileUtils.string2file(GioiThieu.this.properties.getGioiThieu(), GioiThieu.this.properties.getSavePath() + "/raw/gioithieu.txt"); 
/*     */           }
/*     */         });
/* 276 */     editDialog.open();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addBlurListener(BlurListener blurListener) {
/* 281 */     this.blurListener = blurListener;
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\GioiThieu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */