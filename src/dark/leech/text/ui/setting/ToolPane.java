/*    */ package dark.leech.text.ui.setting;
/*    */ 
/*    */ import dark.leech.text.ui.button.CircleButton;
/*    */ import dark.leech.text.ui.main.App;
/*    */ import dark.leech.text.ui.material.JMPanel;
/*    */ import dark.leech.text.util.AppUtils;
/*    */ import dark.leech.text.util.ColorUtils;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.FileDialog;
/*    */ import java.awt.Frame;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JLabel;
/*    */ 
/*    */ public class ToolPane
/*    */   extends JMPanel
/*    */ {
/*    */   private JLabel lbPath;
/*    */   private boolean selectDirectory = false;
/*    */   
/*    */   public ToolPane(String name, String path, final int type) {
/* 25 */     JLabel lbName = new JLabel();
/* 26 */     this.lbPath = new JLabel();
/*    */     
/* 28 */     lbName.setText(name);
/* 29 */     lbName.setFont(FontUtils.TEXT_BOLD);
/* 30 */     lbName.setBounds(25, 5, 290, 30);
/* 31 */     add(lbName);
/*    */     
/* 33 */     this.lbPath.setText(path);
/* 34 */     this.lbPath.setFont(FontUtils.TEXT_THIN);
/* 35 */     this.lbPath.setForeground(Color.GRAY);
/* 36 */     this.lbPath.setBounds(25, 30, 290, 30);
/* 37 */     add(this.lbPath);
/*    */     
/* 39 */     CircleButton btEdit = new CircleButton("", 23.0F);
/* 40 */     btEdit.setForeground(ColorUtils.THEME_COLOR);
/* 41 */     btEdit.addActionListener(new ActionListener()
/*    */         {
/*    */           public void actionPerformed(ActionEvent e) {
/* 44 */             FileDialog f = new FileDialog((Frame)App.getMain(), "Chọn đường dẫn", type);
/* 45 */             if (type == 1)
/* 46 */               f.setFile("LeechText"); 
/* 47 */             f.setModal(true);
/* 48 */             if (ToolPane.this.getPath().equals("Chưa đặt")) {
/* 49 */               f.setDirectory(System.getProperty("user.dir"));
/*    */             } else {
/* 51 */               f.setDirectory(ToolPane.this.getPath());
/* 52 */             }  f.setVisible(true);
/* 53 */             if (f.getDirectory() != null)
/* 54 */               ToolPane.this.setPath(f.getDirectory() + (ToolPane.this.selectDirectory ? "" : f.getFile())); 
/*    */           }
/*    */         });
/* 57 */     add((Component)btEdit);
/* 58 */     btEdit.setBounds(335, 15, 30, 30);
/* 59 */     setBackground(Color.white);
/* 60 */     setPreferredSize(new Dimension(370, 60));
/* 61 */     setLayout(null);
/*    */   }
/*    */   
/*    */   public void setSelectDirectory(boolean selectDirectory) {
/* 65 */     this.selectDirectory = selectDirectory;
/*    */   }
/*    */   
/*    */   public String getPath() {
/* 69 */     return this.lbPath.getText();
/*    */   }
/*    */   
/*    */   public void setPath(String path) {
/* 73 */     if (path.length() != 0 && 
/* 74 */       path.lastIndexOf(AppUtils.SEPARATOR) == path.length() - 1)
/* 75 */       path = path.substring(0, path.length() - 1); 
/* 76 */     this.lbPath.setText(path);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\setting\ToolPane.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */