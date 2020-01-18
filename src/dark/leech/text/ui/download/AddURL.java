/*     */ package dark.leech.text.ui.download;
/*     */ 
/*     */ import dark.leech.text.enities.PluginEntity;
/*     */ import dark.leech.text.listeners.AddListener;
/*     */ import dark.leech.text.listeners.BlurListener;
/*     */ import dark.leech.text.plugin.PluginManager;
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.button.CircleButton;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.ui.material.JMTextField;
/*     */ import dark.leech.text.util.CookiesUtils;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import dark.leech.text.util.SettingUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JLabel;
/*     */ 
/*     */ public class AddURL extends JMDialog {
/*     */   private BasicButton btOk;
/*     */   private BasicButton btCancel;
/*     */   private JMTextField tfUrl;
/*     */   private CircleButton btAddMul;
/*     */   private JLabel lbUrl;
/*     */   private String url;
/*     */   private AddListener addListener;
/*     */   private String cookies;
/*     */   
/*     */   public AddURL() {
/*  34 */     onCreate();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/*  39 */     super.onCreate();
/*  40 */     this.btOk = new BasicButton();
/*  41 */     this.btCancel = new BasicButton();
/*  42 */     this.btAddMul = new CircleButton("");
/*  43 */     this.tfUrl = new JMTextField();
/*  44 */     this.lbUrl = new JLabel();
/*     */     
/*  46 */     this.btOk.setText("OK");
/*  47 */     this.btOk.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  50 */             AddURL.this.url = AddURL.this.tfUrl.getText();
/*  51 */             AddURL.this.runOnUiThread(new Runnable()
/*     */                 {
/*     */                   public void run() {
/*  54 */                     AddURL.this.checkURL();
/*     */                   }
/*     */                 });
/*     */           }
/*     */         });
/*     */     
/*  60 */     this.container.add((Component)this.btOk);
/*  61 */     this.btOk.setBounds(15, 75, 110, 35);
/*     */ 
/*     */     
/*  64 */     this.btCancel.setText("HỦY");
/*  65 */     this.btCancel.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  68 */             AddURL.this.close();
/*     */           }
/*     */         });
/*  71 */     this.container.add((Component)this.btCancel);
/*  72 */     this.btCancel.setBounds(125, 75, 110, 35);
/*     */     
/*  74 */     this.tfUrl.setText(getClipboard());
/*  75 */     this.tfUrl.setFont(FontUtils.TEXT_NORMAL);
/*  76 */     this.tfUrl.setBounds(15, 30, 220, 37);
/*     */     
/*  78 */     this.container.add((Component)this.tfUrl);
/*     */ 
/*     */     
/*  81 */     this.lbUrl.setText("Nhập URL");
/*  82 */     this.lbUrl.setFont(FontUtils.TEXT_BOLD);
/*  83 */     this.container.add(this.lbUrl);
/*  84 */     this.lbUrl.setBounds(15, 0, 110, 25);
/*     */     
/*  86 */     this.btAddMul.setBounds(220, 5, 25, 25);
/*  87 */     this.btAddMul.setForeground(SettingUtils.THEME_COLOR);
/*  88 */     this.btAddMul.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent actionEvent) {
/*  91 */             if (AddURL.this.tfUrl.getText() == null || AddURL.this.tfUrl.getText().length() < 5) {
/*  92 */               AddURL.this.tfUrl.addError("Xin nhập URL!");
/*     */             } else {
/*  94 */               AddURL.AddOption lg = new AddURL.AddOption();
/*  95 */               lg.setBlurListener((BlurListener)AddURL.this);
/*  96 */               lg.open();
/*     */             } 
/*     */           }
/*     */         });
/* 100 */     this.container.add((Component)this.btAddMul);
/* 101 */     this.container.setBackground(Color.WHITE);
/* 102 */     setSize(255, 125);
/*     */   }
/*     */   
/*     */   public String getUrl() {
/* 106 */     if (this.url.lastIndexOf("/") == this.url.length() - 1)
/* 107 */       this.url = this.url.substring(0, this.url.length() - 1); 
/* 108 */     return this.url;
/*     */   }
/*     */   
/*     */   private void checkURL() {
/* 112 */     PluginEntity pluginGetter = PluginManager.getManager().get(this.url);
/* 113 */     if (pluginGetter == null) {
/* 114 */       this.tfUrl.addError("Liên kết này không được hỗ trợ!");
/* 115 */     } else if (pluginGetter.isChecked()) {
/* 116 */       AddDialog add = new AddDialog(getUrl());
/* 117 */       add.setAddListener(this.addListener);
/* 118 */       if (this.cookies != null && this.cookies.length() != 0)
/* 119 */         CookiesUtils.put(getUrl(), this.cookies); 
/* 120 */       close();
/* 121 */       add.open();
/*     */     } else {
/* 123 */       this.tfUrl.addError("Plugin cho trang này đã bị tắt!");
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getClipboard() {
/*     */     try {
/* 129 */       Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
/* 130 */       if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
/* 131 */         String result = (String)transferable.getTransferData(DataFlavor.stringFlavor);
/* 132 */         if (result.toLowerCase().startsWith("http"))
/* 133 */           return result; 
/*     */       } 
/* 135 */     } catch (Exception exception) {}
/*     */     
/* 137 */     return "";
/*     */   }
/*     */   
/*     */   public void setAddListener(AddListener addListener) {
/* 141 */     this.addListener = addListener;
/*     */   }
/*     */   
/*     */   class AddOption extends JMDialog {
/*     */     private BasicButton btOk;
/*     */     private BasicButton btCancel;
/*     */     private JMTextField tfCookies;
/*     */     
/*     */     public AddOption() {
/* 150 */       setSize(300, 100);
/* 151 */       onCreate();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void onCreate() {
/* 156 */       super.onCreate();
/* 157 */       this.btOk = new BasicButton();
/* 158 */       this.btCancel = new BasicButton();
/* 159 */       this.tfCookies = new JMTextField();
/*     */       
/* 161 */       JLabel label = new JLabel("Cookie");
/* 162 */       label.setBounds(10, 10, 50, 35);
/* 163 */       this.tfCookies.setBounds(60, 10, 230, 35);
/* 164 */       this.btOk.setText("OK");
/* 165 */       this.btOk.setBounds(10, 55, 100, 35);
/* 166 */       this.btCancel.setText("HỦY");
/* 167 */       this.btCancel.setBounds(180, 55, 100, 35);
/* 168 */       this.container.add((Component)this.btOk);
/* 169 */       this.container.add((Component)this.btCancel);
/* 170 */       this.container.add((Component)this.tfCookies);
/* 171 */       this.container.add(label);
/*     */       
/* 173 */       this.btOk.addActionListener(new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent actionEvent) {
/* 176 */               AddURL.this.cookies = AddURL.AddOption.this.tfCookies.getText();
/* 177 */               AddURL.AddOption.this.close();
/*     */             }
/*     */           });
/* 180 */       this.btCancel.addActionListener(new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent actionEvent) {
/* 183 */               AddURL.AddOption.this.close();
/*     */             }
/*     */           });
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\download\AddURL.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */