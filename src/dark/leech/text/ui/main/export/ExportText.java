/*     */ package dark.leech.text.ui.main.export;
/*     */ 
/*     */ import dark.leech.text.action.export.Text;
/*     */ import dark.leech.text.listeners.BlurListener;
/*     */ import dark.leech.text.listeners.ChangeListener;
/*     */ import dark.leech.text.listeners.ProgressListener;
/*     */ import dark.leech.text.models.Properties;
/*     */ import dark.leech.text.ui.PanelTitle;
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.material.JMCheckBox;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.ui.material.JMProgressBar;
/*     */ import dark.leech.text.ui.material.SelectBox;
/*     */ import dark.leech.text.ui.notification.Notification;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ 
/*     */ public class ExportText
/*     */   extends JMDialog
/*     */   implements ProgressListener, ChangeListener {
/*     */   private PanelTitle pnTitle;
/*     */   private SelectBox sbStyle;
/*     */   private SelectBox sbType;
/*     */   private JMCheckBox cbToc;
/*     */   private JMCheckBox cbCss;
/*     */   private BasicButton btOk;
/*     */   private JMProgressBar progressBar;
/*     */   private Properties properties;
/*     */   
/*     */   public ExportText(Properties properties) {
/*  32 */     this.properties = properties;
/*  33 */     setSize(245, 255);
/*  34 */     runOnUiThread(new Runnable()
/*     */         {
/*     */           public void run() {
/*  37 */             ExportText.this.onCreate();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/*  44 */     super.onCreate();
/*  45 */     this.pnTitle = new PanelTitle();
/*  46 */     this.btOk = new BasicButton();
/*  47 */     this.progressBar = new JMProgressBar();
/*     */ 
/*     */     
/*  50 */     this.pnTitle.setText("Xuất Text");
/*  51 */     this.pnTitle.addCloseListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  54 */             ExportText.this.close();
/*     */           }
/*     */         });
/*  57 */     this.container.add((Component)this.pnTitle);
/*  58 */     this.pnTitle.setBounds(0, 0, 245, 45);
/*     */ 
/*     */     
/*  61 */     this.sbStyle = new SelectBox("Định dạng xuất", new String[] { "HTML", "TXT" }, 0);
/*  62 */     this.sbStyle.addChangeListener(this);
/*  63 */     this.sbStyle.addBlurListener((BlurListener)this);
/*  64 */     this.container.add((Component)this.sbStyle);
/*  65 */     this.sbStyle.setBounds(15, 60, 200, 30);
/*     */ 
/*     */ 
/*     */     
/*  69 */     this.sbType = new SelectBox("Kiểu xuất", new String[] { "Tách", "Gộp" }, 0);
/*  70 */     this.sbType.addBlurListener((BlurListener)this);
/*  71 */     this.sbType.addChangeListener(this);
/*  72 */     this.container.add((Component)this.sbType);
/*  73 */     this.sbType.setBounds(15, 95, 200, 30);
/*     */     
/*  75 */     this.cbToc = new JMCheckBox("Đính kèm mục lục");
/*  76 */     this.container.add((Component)this.cbToc);
/*  77 */     this.cbToc.setBounds(15, 130, 200, 30);
/*  78 */     this.cbToc.setVisible((this.sbStyle.getSelectIndex() == 0));
/*     */     
/*  80 */     this.cbCss = new JMCheckBox("Đính kèm CSS");
/*  81 */     this.container.add((Component)this.cbCss);
/*  82 */     this.cbCss.setBounds(15, 165, 200, 30);
/*  83 */     this.cbCss.setVisible((this.sbStyle.getSelectIndex() == 0 && this.sbType.getSelectIndex() == 1));
/*     */     
/*  85 */     this.btOk.setText("XUẤT");
/*  86 */     this.btOk.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  89 */             (new Thread(new Runnable()
/*     */                 {
/*     */                   public void run() {
/*  92 */                     ExportText.this.doExport();
/*     */                   }
/*  94 */                 })).start();
/*     */           }
/*     */         });
/*  97 */     this.container.add((Component)this.btOk);
/*  98 */     this.btOk.setBounds(15, 210, 210, 35);
/*  99 */     this.progressBar.setPercent(0);
/* 100 */     this.container.add((Component)this.progressBar);
/* 101 */     this.progressBar.setBounds(15, 210, 210, 35);
/* 102 */     this.progressBar.setVisible(false);
/*     */   }
/*     */ 
/*     */   
/*     */   private void doExport() {
/* 107 */     this.progressBar.setVisible(true);
/* 108 */     this.btOk.setVisible(false);
/* 109 */     Text exportText = new Text(this.properties, this.sbStyle.getSelectIndex(), this.cbToc.isChecked(), this.cbCss.isChecked(), this.sbType.getSelectIndex());
/* 110 */     exportText.addProgressListener(this);
/* 111 */     exportText.export();
/*     */     
/* 113 */     Notification.build()
/* 114 */       .title(this.properties.getName())
/* 115 */       .content("Đã xuất text xong!")
/* 116 */       .path(this.properties.getSavePath() + "/data/cover.jpg")
/* 117 */       .delay(10000)
/* 118 */       .open();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProgress(int value, String string) {
/* 123 */     this.progressBar.setPercent(value);
/* 124 */     if (value == 100) {
/* 125 */       close();
/*     */     }
/*     */   }
/*     */   
/*     */   public void doChanger() {
/* 130 */     this.cbToc.setVisible((this.sbStyle.getSelectIndex() == 0));
/* 131 */     this.cbCss.setVisible((this.sbStyle.getSelectIndex() == 0 && this.sbType.getSelectIndex() == 1));
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\export\ExportText.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */