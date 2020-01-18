/*    */ package dark.leech.text.ui.main.plugin;
/*    */ 
/*    */ import dark.leech.text.enities.PluginEntity;
/*    */ import dark.leech.text.image.ImageLabel;
/*    */ import dark.leech.text.ui.button.SelectButton;
/*    */ import dark.leech.text.ui.material.DropShadowBorder;
/*    */ import dark.leech.text.ui.material.JMPanel;
/*    */ import dark.leech.text.util.Base64;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import dark.leech.text.util.SettingUtils;
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.InputStream;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.border.Border;
/*    */ import javax.swing.event.ChangeEvent;
/*    */ import javax.swing.event.ChangeListener;
/*    */ 
/*    */ public class PluginItem
/*    */   extends JMPanel
/*    */ {
/*    */   private ImageLabel pnIcon;
/*    */   private JLabel lbName;
/*    */   private JLabel lbInfo;
/*    */   private SelectButton btCheckBox;
/*    */   private PluginEntity pluginGetter;
/*    */   
/*    */   public PluginItem(PluginEntity pluginGetter) {
/* 30 */     this.pluginGetter = pluginGetter;
/* 31 */     onCreate();
/*    */   }
/*    */   
/*    */   private void onCreate() {
/* 35 */     this.pnIcon = new ImageLabel();
/* 36 */     this.lbName = new JLabel();
/* 37 */     this.lbInfo = new JLabel();
/* 38 */     this.btCheckBox = new SelectButton();
/*    */     
/* 40 */     add((Component)this.pnIcon);
/* 41 */     this.pnIcon.setBounds(10, 5, 55, 55);
/* 42 */     if (this.pluginGetter.getIcon() == null) {
/* 43 */       this.pnIcon.input(PluginItem.class.getResourceAsStream("/dark/leech/res/img/book.png"))
/* 44 */         .load();
/*    */     } else {
/* 46 */       InputStream in = new ByteArrayInputStream(Base64.decode(this.pluginGetter.getIcon()));
/* 47 */       this.pnIcon.input(in).load();
/*    */     } 
/*    */ 
/*    */     
/* 51 */     this.lbName.setText(this.pluginGetter.getName() + " - v" + this.pluginGetter.getVersion());
/* 52 */     this.lbName.setFont(FontUtils.TEXT_NORMAL);
/* 53 */     add(this.lbName);
/* 54 */     this.lbName.setBounds(70, 5, 220, 25);
/*    */ 
/*    */     
/* 57 */     this.lbInfo.setText(this.pluginGetter.getSource());
/* 58 */     this.lbInfo.setFont(FontUtils.TEXT_THIN);
/* 59 */     add(this.lbInfo);
/* 60 */     this.lbInfo.setBounds(70, 35, 295, 25);
/*    */ 
/*    */     
/* 63 */     this.btCheckBox.setSelected(this.pluginGetter.isChecked());
/* 64 */     this.btCheckBox.addChangeListener(new ChangeListener()
/*    */         {
/*    */           public void stateChanged(ChangeEvent e) {
/* 67 */             PluginItem.this.pluginGetter.setChecked(PluginItem.this.btCheckBox.isSelected());
/*    */           }
/*    */         });
/* 70 */     add((Component)this.btCheckBox);
/* 71 */     this.btCheckBox.setBounds(340, 5, 30, 30);
/*    */     
/* 73 */     setBorder((Border)new DropShadowBorder(SettingUtils.THEME_COLOR, 5, 3));
/* 74 */     setPreferredSize(new Dimension(375, 70));
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\plugin\PluginItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */