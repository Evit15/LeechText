/*     */ package dark.leech.text.ui;
/*     */ 
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.ui.material.JMScrollPane;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SyntaxDialog
/*     */   extends JMDialog
/*     */ {
/*     */   private BasicButton ok;
/*     */   private BasicButton cancel;
/*     */   private RSyntaxTextArea edit;
/*     */   private String text;
/*     */   private String title;
/*     */   private String type;
/*     */   
/*     */   public SyntaxDialog(String title, String text, String type) {
/*  26 */     this.title = title;
/*  27 */     this.text = text;
/*  28 */     this.type = type;
/*  29 */     setSize(360, 410);
/*     */     
/*  31 */     onCreate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/*  38 */     super.onCreate();
/*  39 */     PanelTitle pnTitle = new PanelTitle();
/*  40 */     this.ok = new BasicButton();
/*  41 */     this.cancel = new BasicButton();
/*     */     
/*  43 */     pnTitle.setText(this.title);
/*  44 */     pnTitle.addCloseListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  47 */             SyntaxDialog.this.close();
/*     */           }
/*     */         });
/*  50 */     this.container.add(pnTitle);
/*  51 */     pnTitle.setBounds(0, 0, 360, 45);
/*     */     
/*  53 */     this.ok.setText("OK");
/*  54 */     this.container.add((Component)this.ok);
/*  55 */     this.ok.setBounds(180, 365, 70, 30);
/*  56 */     this.ok.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  59 */             SyntaxDialog.this.clickOk();
/*     */           }
/*     */         });
/*  62 */     this.cancel.setText("HỦY");
/*  63 */     this.container.add((Component)this.cancel);
/*  64 */     this.cancel.setBounds(270, 365, 70, 30);
/*  65 */     this.cancel.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  68 */             SyntaxDialog.this.close();
/*     */           }
/*     */         });
/*     */     
/*  72 */     this.edit = new RSyntaxTextArea();
/*  73 */     this.edit.setText(this.text);
/*  74 */     this.edit.setSyntaxEditingStyle("text/html");
/*  75 */     this.edit.setSyntaxEditingStyle(this.type);
/*  76 */     this.edit.setLineWrap(true);
/*  77 */     this.edit.setWrapStyleWord(true);
/*  78 */     this.edit.setMarginLineEnabled(true);
/*  79 */     this.edit.setCodeFoldingEnabled(true);
/*  80 */     Font font = FontUtils.codeFont(12.0F);
/*  81 */     if (this.text != null && 
/*  82 */       font.canDisplayUpTo(this.text) == -1) {
/*  83 */       this.edit.setFont(font);
/*     */     }
/*  85 */     JMScrollPane scrollPane = new JMScrollPane();
/*  86 */     scrollPane.setViewportView(this.edit);
/*  87 */     this.container.add((Component)scrollPane);
/*  88 */     scrollPane.setBounds(10, 47, 348, 310);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/*  94 */     return this.text;
/*     */   }
/*     */ 
/*     */   
/*     */   private void clickOk() {
/*  99 */     this.text = this.edit.getText();
/* 100 */     close();
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\SyntaxDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */