/*    */ package dark.leech.text.ui.material;
/*    */ 
/*    */ import dark.leech.text.listeners.BlurListener;
/*    */ import dark.leech.text.listeners.ChangeListener;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Color;
/*    */ import java.awt.Cursor;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class SelectBox
/*    */   extends JMPanel
/*    */ {
/*    */   private int selectIndex;
/*    */   private String[] list;
/*    */   private JLabel labelName;
/*    */   private BlurListener blurListener;
/*    */   private ChangeListener changeListener;
/*    */   
/*    */   public SelectBox(String name, String[] list, int selectIndex) {
/* 24 */     setLayout(new BorderLayout());
/* 25 */     this.selectIndex = selectIndex;
/* 26 */     this.list = list;
/*    */     
/* 28 */     JPanel panelChooser = new JPanel(new BorderLayout());
/* 29 */     JLabel labelTitle = new JLabel(name);
/* 30 */     labelTitle.setFont(FontUtils.TEXT_NORMAL);
/* 31 */     add(labelTitle, "West");
/* 32 */     panelChooser.setBackground(Color.WHITE);
/* 33 */     panelChooser.setOpaque(false);
/* 34 */     panelChooser.setCursor(new Cursor(12));
/* 35 */     this.labelName = new JLabel(list[selectIndex]);
/* 36 */     this.labelName.setHorizontalAlignment(4);
/* 37 */     this.labelName.setFont(FontUtils.TEXT_NORMAL);
/* 38 */     panelChooser.add(this.labelName, "West");
/* 39 */     panelChooser.addMouseListener(new MouseAdapter()
/*    */         {
/*    */           public void mouseClicked(MouseEvent e) {
/* 42 */             SelectBox.this.doClick();
/*    */           }
/*    */         });
/*    */     
/* 46 */     JLabel label = new JLabel("");
/* 47 */     label.setFont(FontUtils.ICON_NORMAL);
/* 48 */     label.setHorizontalAlignment(0);
/* 49 */     panelChooser.add(label, "East");
/* 50 */     add(panelChooser, "East");
/*    */   }
/*    */   
/*    */   public void setModel(String[] list, int selectIndex) {
/* 54 */     this.list = list;
/* 55 */     this.selectIndex = selectIndex;
/* 56 */     this.labelName.setText(list[selectIndex]);
/*    */   }
/*    */   
/*    */   public void addBlurListener(BlurListener blurListener) {
/* 60 */     this.blurListener = blurListener;
/*    */   }
/*    */   
/*    */   public void addChangeListener(ChangeListener changeListener) {
/* 64 */     this.changeListener = changeListener;
/*    */   }
/*    */   
/*    */   private void doClick() {
/* 68 */     final JMDialogChooser dc = new JMDialogChooser(this.list, this.selectIndex);
/* 69 */     dc.setBlurListener(this.blurListener);
/* 70 */     dc.setChangeListener(new ChangeListener()
/*    */         {
/*    */           public void doChanger() {
/* 73 */             SelectBox.this.selectIndex = dc.getSelectIndex();
/* 74 */             SelectBox.this.labelName.setText(SelectBox.this.list[SelectBox.this.selectIndex]);
/* 75 */             if (SelectBox.this.changeListener != null)
/* 76 */               SelectBox.this.changeListener.doChanger(); 
/*    */           }
/*    */         });
/* 79 */     dc.open();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSelectIndex() {
/* 84 */     return this.selectIndex;
/*    */   }
/*    */   
/*    */   public String getSelectText() {
/* 88 */     return this.list[this.selectIndex];
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\material\SelectBox.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */