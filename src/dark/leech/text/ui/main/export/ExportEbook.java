/*     */ package dark.leech.text.ui.main.export;
/*     */ 
/*     */ import dark.leech.text.action.export.Ebook;
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
/*     */ import dark.leech.text.util.AppUtils;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JLabel;
/*     */ 
/*     */ 
/*     */ public class ExportEbook
/*     */   extends JMDialog
/*     */   implements ProgressListener
/*     */ {
/*     */   private PanelTitle pnTitle;
/*     */   private SelectBox pnTool;
/*     */   private SelectBox pnType;
/*     */   private SelectBox pnComp;
/*     */   private JMCheckBox cbSplit;
/*     */   private JMCheckBox cbInclude;
/*     */   private BasicButton btOk;
/*     */   private JMProgressBar progressBar;
/*     */   private JLabel labelProgress;
/*     */   private Properties properties;
/*     */   
/*     */   public ExportEbook(Properties properties) {
/*  39 */     this.properties = properties;
/*  40 */     setSize(245, 300);
/*  41 */     runOnUiThread(new Runnable()
/*     */         {
/*     */           public void run() {
/*  44 */             ExportEbook.this.onCreate();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/*  51 */     super.onCreate();
/*  52 */     this.pnTitle = new PanelTitle();
/*  53 */     this.btOk = new BasicButton();
/*  54 */     this.progressBar = new JMProgressBar();
/*  55 */     this.labelProgress = new JLabel();
/*     */     
/*  57 */     this.pnTitle.setText("Tạo Ebook");
/*  58 */     this.pnTitle.addCloseListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  61 */             ExportEbook.this.close();
/*     */           }
/*     */         });
/*  64 */     this.container.add((Component)this.pnTitle);
/*  65 */     this.pnTitle.setBounds(0, 0, 245, 45);
/*     */ 
/*     */ 
/*     */     
/*  69 */     this.pnTool = new SelectBox("Công cụ", new String[] { "Mặc định", "Calibre" }, 0);
/*  70 */     this.pnTool.addChangeListener(new ChangeListener()
/*     */         {
/*     */           public void doChanger() {
/*  73 */             ExportEbook.this.ToolChanger();
/*     */           }
/*     */         });
/*  76 */     this.pnTool.addBlurListener((BlurListener)this);
/*  77 */     this.container.add((Component)this.pnTool);
/*  78 */     this.pnTool.setBounds(10, 90, 215, 30);
/*     */ 
/*     */     
/*  81 */     this.pnType = new SelectBox("Định dạng xuất", new String[] { "EPUB", "MOBI", "AZW3", "PDF" }, 0);
/*  82 */     this.pnType.addChangeListener(new ChangeListener()
/*     */         {
/*     */           public void doChanger() {
/*  85 */             ExportEbook.this.TypeChanger();
/*  86 */             ExportEbook.this.ToolChanger();
/*     */           }
/*     */         });
/*  89 */     this.pnType.addBlurListener((BlurListener)this);
/*  90 */     this.container.add((Component)this.pnType);
/*  91 */     this.pnType.setBounds(10, 55, 215, 30);
/*     */ 
/*     */ 
/*     */     
/*  95 */     this.pnComp = new SelectBox("Mức nén", new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }, 8);
/*  96 */     this.pnComp.addBlurListener((BlurListener)this);
/*  97 */     this.container.add((Component)this.pnComp);
/*  98 */     this.pnComp.setBounds(10, 125, 215, 30);
/*     */ 
/*     */     
/* 101 */     this.cbSplit = new JMCheckBox("Tự động chia quyển");
/* 102 */     this.container.add((Component)this.cbSplit);
/* 103 */     this.cbSplit.setBounds(10, 160, 215, 30);
/*     */ 
/*     */     
/* 106 */     this.cbInclude = new JMCheckBox("Chèn chương ảnh");
/* 107 */     this.container.add((Component)this.cbInclude);
/* 108 */     this.cbInclude.setBounds(10, 195, 215, 30);
/*     */     
/* 110 */     this.labelProgress.setText("Đang xử lý...");
/* 111 */     this.labelProgress.setFont(FontUtils.TEXT_THIN);
/* 112 */     this.container.add(this.labelProgress);
/* 113 */     this.labelProgress.setBounds(15, 225, 210, 25);
/* 114 */     this.labelProgress.setVisible(false);
/*     */     
/* 116 */     this.btOk.setText("OK");
/* 117 */     this.btOk.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 120 */             ExportEbook.this.doClick();
/*     */           }
/*     */         });
/* 123 */     this.container.add((Component)this.btOk);
/* 124 */     this.btOk.setBounds(15, 250, 210, 35);
/* 125 */     this.container.add((Component)this.progressBar);
/* 126 */     this.progressBar.setBounds(15, 250, 210, 35);
/* 127 */     this.progressBar.setVisible(false);
/*     */   }
/*     */ 
/*     */   
/*     */   private void doClick() {
/* 132 */     this.btOk.setVisible(false);
/* 133 */     this.progressBar.setVisible(true);
/* 134 */     this.labelProgress.setVisible(true);
/* 135 */     final Ebook ebook = new Ebook(this.properties);
/* 136 */     ebook.addProgressListener(this);
/* 137 */     ebook.setData(this.pnType.getSelectIndex(), this.pnTool.getSelectText(), this.pnComp.getSelectText(), this.cbSplit.isChecked(), true);
/* 138 */     runOnUiThread(new Runnable()
/*     */         {
/*     */           public void run() {
/* 141 */             ebook.export();
/* 142 */             AppUtils.pause(200);
/* 143 */             ExportEbook.this.btOk.setVisible(true);
/* 144 */             ExportEbook.this.progressBar.setVisible(false);
/* 145 */             ExportEbook.this.progressBar.setPercent(0);
/* 146 */             ExportEbook.this.labelProgress.setVisible(false);
/* 147 */             ExportEbook.this.labelProgress.setText("Đang xử lý...");
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void TypeChanger() {
/* 153 */     switch (this.pnType.getSelectIndex()) {
/*     */       case 0:
/* 155 */         this.pnTool.setModel(new String[] { "Mặc định", "Calibre" }, 0);
/*     */         break;
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/* 160 */         this.pnTool.setModel(new String[] { "Calibre" }, 0);
/*     */         break;
/*     */       case 1:
/* 163 */         this.pnTool.setModel(new String[] { "Calibre", "Kindlegen" }, 0);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void ToolChanger() {
/* 169 */     String toolName = this.pnTool.getSelectText();
/* 170 */     if (toolName.equals("Mặc định")) {
/* 171 */       this.pnComp.setVisible(true);
/* 172 */       this.pnComp.setModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }, 8);
/*     */     } 
/* 174 */     if (toolName.equals("Calibre"))
/* 175 */       switch (this.pnType.getSelectIndex()) {
/*     */         case 0:
/*     */         case 2:
/* 178 */           this.pnComp.setVisible(false);
/*     */           break;
/*     */         case 1:
/* 181 */           this.pnComp.setVisible(true);
/* 182 */           this.pnComp.setModel(new String[] { "old", "both", "new" }, 1);
/*     */           break;
/*     */         case 3:
/* 185 */           this.pnComp.setVisible(true);
/* 186 */           this.pnComp.setModel(new String[] { "a4", "a5", "a6" }, 0);
/*     */           break;
/*     */       }  
/* 189 */     if (toolName.equals("Kindlegen"))
/* 190 */       switch (this.pnType.getSelectIndex()) {
/*     */         case 1:
/* 192 */           this.pnComp.setModel(new String[] { "-c0", "-c1", "-c2" }, 2);
/*     */           break;
/*     */       }  
/*     */   }
/*     */   
/*     */   public void setProgress(int value, String string) {
/* 198 */     this.progressBar.setPercent(value);
/* 199 */     this.labelProgress.setText(string);
/* 200 */     if (string.equals("Hoàn tất!")) {
/* 201 */       Notification.build()
/* 202 */         .title(this.properties.getName())
/* 203 */         .content("Đã tạo ebook xong!")
/* 204 */         .path(this.properties.getSavePath() + "/data/cover.jpg")
/* 205 */         .delay(10000)
/* 206 */         .open();
/* 207 */       close();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\export\ExportEbook.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */