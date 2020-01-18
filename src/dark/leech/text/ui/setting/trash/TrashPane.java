/*    */ package dark.leech.text.ui.setting.trash;
/*    */ 
/*    */ import dark.leech.text.listeners.ChangeListener;
/*    */ import dark.leech.text.models.Trash;
/*    */ import dark.leech.text.ui.button.CircleButton;
/*    */ import dark.leech.text.ui.material.JMPanel;
/*    */ import dark.leech.text.util.ColorUtils;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.util.List;
/*    */ import javax.swing.JLabel;
/*    */ 
/*    */ public class TrashPane
/*    */   extends JMPanel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private List<Trash> trash;
/*    */   private CircleButton buttonEdit;
/*    */   
/*    */   public TrashPane() {
/* 25 */     this((List<Trash>)null);
/*    */   }
/*    */   
/*    */   public TrashPane(List<Trash> trash) {
/* 29 */     this.trash = trash;
/* 30 */     JLabel labelName = new JLabel();
/* 31 */     JLabel labelTip = new JLabel();
/*    */     
/* 33 */     labelName.setText("Lọc rác");
/* 34 */     labelName.setFont(FontUtils.TEXT_BOLD);
/* 35 */     labelName.setBounds(25, 5, 290, 30);
/* 36 */     add(labelName);
/*    */     
/* 38 */     labelTip.setText("Tùy chỉnh lọc rác khi gettext");
/* 39 */     labelTip.setFont(FontUtils.TEXT_THIN);
/* 40 */     labelTip.setForeground(Color.GRAY);
/* 41 */     labelTip.setBounds(25, 30, 290, 30);
/* 42 */     add(labelTip);
/*    */     
/* 44 */     this.buttonEdit = new CircleButton("");
/* 45 */     this.buttonEdit.setForeground(ColorUtils.THEME_COLOR);
/* 46 */     this.buttonEdit.addActionListener(new ActionListener()
/*    */         {
/*    */           public void actionPerformed(ActionEvent e) {
/* 49 */             TrashPane.this.actionEdit();
/*    */           }
/*    */         });
/* 52 */     add((Component)this.buttonEdit);
/* 53 */     this.buttonEdit.setBounds(335, 15, 30, 30);
/* 54 */     setBackground(Color.white);
/* 55 */     setPreferredSize(new Dimension(370, 60));
/* 56 */     setLayout(null);
/*    */   }
/*    */   
/*    */   public List<Trash> getTrash() {
/* 60 */     return this.trash;
/*    */   }
/*    */   
/*    */   public void setTrash(List<Trash> trash) {
/* 64 */     this.trash = trash;
/*    */   }
/*    */   
/*    */   private void actionEdit() {
/* 68 */     final TrashUI trashUI = new TrashUI(this.trash);
/* 69 */     trashUI.setChangeListener(new ChangeListener()
/*    */         {
/*    */           public void doChanger() {
/* 72 */             TrashPane.this.trash = trashUI.getTrash();
/*    */           }
/*    */         });
/* 75 */     trashUI.open();
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\setting\trash\TrashPane.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */