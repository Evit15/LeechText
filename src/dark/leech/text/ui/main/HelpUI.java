/*     */ package dark.leech.text.ui.main;
/*     */ 
/*     */ import dark.leech.text.enities.PluginEntity;
/*     */ import dark.leech.text.plugin.PluginManager;
/*     */ import dark.leech.text.ui.PanelTitle;
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.ui.material.JMScrollPane;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import java.awt.Component;
/*     */ import java.awt.Desktop;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.net.URL;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ public class HelpUI
/*     */   extends JMDialog
/*     */ {
/*     */   private StringBuilder INFO;
/*     */   private StringBuilder support;
/*  24 */   private static final String JVM = System.getProperty("java.vm.name");
/*  25 */   private static final String JRE = System.getProperty("java.version");
/*     */ 
/*     */   
/*     */   public HelpUI() {
/*  29 */     this.INFO = new StringBuilder();
/*  30 */     this.INFO.append("<b>LeechText 2019.07.22</b>");
/*  31 */     this.INFO.append("<br>Build: " + "2019.07.22".replace(".", "/") + " at " + "00:00" + " <u>© 2017 Darkrai</u>");
/*  32 */     this.INFO.append("<br>");
/*  33 */     this.INFO.append("<br>JRE: " + JRE);
/*  34 */     this.INFO.append("<br>JVM: " + JVM);
/*  35 */     this.support = new StringBuilder();
/*  36 */     for (PluginEntity pl : PluginManager.getManager().list()) {
/*  37 */       this.support.append("• " + pl.getSource() + "<br>");
/*     */     }
/*  39 */     onCreate();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/*  44 */     super.onCreate();
/*  45 */     PanelTitle pnTitle = new PanelTitle();
/*  46 */     BasicButton btClose = new BasicButton();
/*  47 */     BasicButton btVisit = new BasicButton();
/*  48 */     JLabel lb = new JLabel("Các trang hỗ trợ");
/*  49 */     JTextPane jTInfo = new JTextPane();
/*  50 */     JTextPane jTPage = new JTextPane();
/*  51 */     JMScrollPane sc = new JMScrollPane(jTPage);
/*     */     
/*  53 */     pnTitle.setText("Thông tin");
/*  54 */     pnTitle.addCloseListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  57 */             HelpUI.this.close();
/*     */           }
/*     */         });
/*  60 */     this.container.add((Component)pnTitle);
/*  61 */     pnTitle.setBounds(0, 0, 300, 45);
/*     */ 
/*     */     
/*  64 */     jTInfo.setFont(FontUtils.TEXT_NORMAL);
/*  65 */     jTInfo.setContentType("text/html");
/*  66 */     jTInfo.setText(this.INFO.toString());
/*  67 */     jTInfo.setEditable(false);
/*  68 */     jTInfo.setBorder((Border)null);
/*  69 */     this.container.add(jTInfo);
/*  70 */     jTInfo.setBounds(10, 50, 280, 100);
/*     */     
/*  72 */     lb.setFont(FontUtils.TEXT_NORMAL);
/*  73 */     this.container.add(lb);
/*  74 */     lb.setBounds(10, 145, 200, 30);
/*  75 */     jTPage.setFont(FontUtils.TEXT_NORMAL);
/*  76 */     jTPage.setContentType("text/html");
/*  77 */     jTPage.setText(this.support.toString());
/*  78 */     jTPage.setEditable(false);
/*  79 */     jTPage.setBorder((Border)null);
/*  80 */     this.container.add((Component)sc);
/*  81 */     sc.setBounds(10, 175, 280, 185);
/*  82 */     btClose.setText("ĐÓNG");
/*  83 */     btClose.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  86 */             HelpUI.this.close();
/*     */           }
/*     */         });
/*  89 */     this.container.add((Component)btClose);
/*  90 */     btClose.setBounds(20, 360, 90, 35);
/*     */     
/*  92 */     btVisit.setText("VISIT");
/*  93 */     btVisit.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*     */             try {
/*  97 */               Desktop.getDesktop().browse((new URL("https://www.facebook.com/leechtext/")).toURI());
/*  98 */             } catch (Exception exception) {}
/*     */           }
/*     */         });
/*     */     
/* 102 */     this.container.add((Component)btVisit);
/* 103 */     btVisit.setBounds(200, 360, 90, 35);
/*     */     
/* 105 */     setSize(300, 400);
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\HelpUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */