/*     */ package dark.leech.text.ui.material;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class JMDialogChooser
/*     */   extends JMDialog
/*     */ {
/*     */   private List<ChooserItem> listItem;
/*     */   private int selectIndex;
/*     */   private String[] list;
/*     */   
/*  96 */   private MouseAdapter mo = new MouseAdapter()
/*     */     {
/*     */       public void mouseClicked(MouseEvent arg0) {
/*  99 */         for (int i = 0; i < JMDialogChooser.this.listItem.size(); i++) {
/* 100 */           if (arg0.getSource() == JMDialogChooser.this.listItem.get(i))
/* 101 */             JMDialogChooser.this.selectIndex = i; 
/* 102 */         }  JMDialogChooser.this.close();
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JMDialogChooser(String[] list, int selectIndex) {
/* 110 */     this.selectIndex = selectIndex;
/* 111 */     this.list = list;
/* 112 */     onCreate();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/* 117 */     super.onCreate();
/* 118 */     this.container.setLayout(new GridBagLayout());
/* 119 */     this.container.setBackground(Color.WHITE);
/* 120 */     this.listItem = new ArrayList<>();
/* 121 */     JPanel body = new JPanel();
/* 122 */     body.setBackground(Color.WHITE);
/* 123 */     body.setLayout(new BoxLayout(body, 1));
/* 124 */     for (int i = 0; i < this.list.length; i++) {
/* 125 */       ChooserItem ch = new ChooserItem(this.list[i]);
/* 126 */       ch.addMouseListener(this.mo);
/* 127 */       this.listItem.add(ch);
/* 128 */       body.add(ch);
/*     */     } 
/* 130 */     ((ChooserItem)this.listItem.get(this.selectIndex)).setSelected(true);
/* 131 */     this.container.add(body);
/* 132 */     pack();
/*     */   }
/*     */   
/*     */   public int getSelectIndex() {
/* 136 */     return this.selectIndex;
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\material\JMDialogChooser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */