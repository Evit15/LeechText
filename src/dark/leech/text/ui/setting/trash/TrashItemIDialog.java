/*     */ package dark.leech.text.ui.setting.trash;
/*     */ 
/*     */ import dark.leech.text.models.Trash;
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.ui.material.JMTextField;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JLabel;
/*     */ 
/*     */ 
/*     */ 
/*     */ class TrashItemIDialog
/*     */   extends JMDialog
/*     */ {
/*     */   private JMTextField textSrc;
/*     */   private JMTextField textTo;
/*     */   private JMTextField textTip;
/*     */   private BasicButton ok;
/*     */   private BasicButton cancel;
/*     */   private Trash trash;
/*     */   
/*     */   public TrashItemIDialog() {
/*  26 */     this(new Trash());
/*     */   }
/*     */   
/*     */   public TrashItemIDialog(Trash trash) {
/*  30 */     this.trash = trash;
/*  31 */     onCreate();
/*  32 */     setTrash(trash);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/*  37 */     super.onCreate();
/*  38 */     JLabel labelSrc = new JLabel();
/*  39 */     JLabel labelTo = new JLabel();
/*  40 */     JLabel labelTip = new JLabel();
/*  41 */     this.textSrc = new JMTextField();
/*  42 */     this.textTo = new JMTextField();
/*  43 */     this.textTip = new JMTextField();
/*  44 */     this.ok = new BasicButton();
/*  45 */     this.cancel = new BasicButton();
/*     */     
/*  47 */     labelSrc.setText("Tìm");
/*  48 */     labelSrc.setFont(FontUtils.TEXT_NORMAL);
/*  49 */     this.container.add(labelSrc);
/*  50 */     this.container.add((Component)this.textSrc);
/*  51 */     this.textSrc.setBounds(10, 35, 280, 37);
/*  52 */     labelSrc.setBounds(10, 10, 280, 25);
/*  53 */     labelTo.setText("Thay thế bởi");
/*  54 */     labelTo.setFont(FontUtils.TEXT_NORMAL);
/*  55 */     this.container.add(labelTo);
/*  56 */     this.container.add((Component)this.textTo);
/*  57 */     this.textTo.setBounds(10, 100, 280, 37);
/*  58 */     labelTo.setBounds(10, 75, 280, 25);
/*  59 */     labelTip.setText("Mô tả");
/*  60 */     labelTip.setFont(FontUtils.TEXT_NORMAL);
/*  61 */     this.container.add(labelTip);
/*  62 */     this.container.add((Component)this.textTip);
/*  63 */     this.textTip.setBounds(10, 160, 280, 37);
/*  64 */     labelTip.setBounds(10, 135, 280, 25);
/*  65 */     this.ok.setText("XONG");
/*  66 */     this.ok.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  69 */             TrashItemIDialog.this.check();
/*     */           }
/*     */         });
/*  72 */     this.container.add((Component)this.ok);
/*  73 */     this.ok.setBounds(105, 210, 90, 30);
/*  74 */     this.cancel.setText("HỦY");
/*  75 */     this.cancel.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  78 */             TrashItemIDialog.this.close();
/*     */           }
/*     */         });
/*  81 */     this.container.add((Component)this.cancel);
/*  82 */     this.cancel.setBounds(200, 210, 90, 30);
/*  83 */     setSize(300, 250);
/*     */   }
/*     */   
/*     */   public Trash getTrash() {
/*  87 */     this.trash.setSrc(this.textSrc.getText());
/*  88 */     this.trash.setTo((this.textTo.getText() == null) ? "" : this.textTo.getText());
/*  89 */     this.trash.setTip(this.textTip.getText());
/*  90 */     this.trash.setReplace(this.trash.isReplace());
/*  91 */     return this.trash;
/*     */   }
/*     */   
/*     */   public void setTrash(Trash trash) {
/*  95 */     this.trash = trash;
/*  96 */     this.textSrc.setText(trash.getSrc());
/*  97 */     this.textTo.setText(trash.getTo());
/*  98 */     this.textTip.setText(trash.getTip());
/*     */   }
/*     */   
/*     */   private void check() {
/* 102 */     if (this.textSrc.getText().length() == 0) {
/* 103 */       this.textSrc.addError("Nội dung không được để trống!");
/*     */       return;
/*     */     } 
/* 106 */     close();
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\setting\trash\TrashItemIDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */