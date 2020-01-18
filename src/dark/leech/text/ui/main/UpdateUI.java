/*     */ package dark.leech.text.ui.main;
/*     */ 
/*     */ import dark.leech.text.ui.PanelTitle;
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.util.AppUtils;
/*     */ import dark.leech.text.util.FileUtils;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import dark.leech.text.util.Http;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import javax.swing.JLabel;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UpdateUI
/*     */   extends JMDialog
/*     */ {
/*     */   private static final String URL = "https://dl.dropboxusercontent.com/s/c701wrrsbhl63l2/update.json?dl=1";
/*     */   private JSONObject obj;
/*     */   
/*     */   public static void checkUpdate() {
/*  26 */     int VERSION = Integer.parseInt("2019.07.22".replace(".", ""));
/*  27 */     int TIME = Integer.parseInt("00:00".replace(":", ""));
/*     */ 
/*     */     
/*     */     try {
/*  31 */       String update = new String(Http.connect("https://dl.dropboxusercontent.com/s/c701wrrsbhl63l2/update.json?dl=1").execute().bodyAsBytes(), "UTF-8");
/*  32 */       JSONObject obj = new JSONObject(update);
/*  33 */       int version = Integer.parseInt(obj.getString("version").replace(".", ""));
/*  34 */       int time = Integer.parseInt(obj.getString("time").replace(":", ""));
/*     */       
/*  36 */       if (version > VERSION || (version == VERSION && time > TIME)) {
/*  37 */         (new UpdateUI(obj)).open();
/*     */       }
/*     */     }
/*  40 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public UpdateUI(JSONObject obj) {
/*  45 */     this.obj = obj;
/*  46 */     onCreate();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/*  52 */     super.onCreate();
/*  53 */     PanelTitle panelTitle = new PanelTitle();
/*  54 */     BasicButton btUpdate = new BasicButton();
/*  55 */     BasicButton btCancel = new BasicButton();
/*  56 */     JLabel lbInfo = new JLabel("Có bản update mới! v" + this.obj.getString("version") + " " + this.obj.getString("time"));
/*     */     
/*  58 */     lbInfo.setFont(FontUtils.TEXT_NORMAL);
/*  59 */     this.container.add(lbInfo);
/*  60 */     lbInfo.setBounds(25, 60, 250, 25);
/*  61 */     panelTitle.addCloseListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  64 */             UpdateUI.this.close();
/*     */           }
/*     */         });
/*  67 */     panelTitle.setText("Cập nhật");
/*  68 */     this.container.add((Component)panelTitle);
/*  69 */     panelTitle.setBounds(0, 0, 310, 45);
/*     */     
/*  71 */     btUpdate.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  74 */             UpdateUI.this.update();
/*     */           }
/*     */         });
/*  77 */     btUpdate.setText("CẬP NHẬT");
/*  78 */     this.container.add((Component)btUpdate);
/*  79 */     btUpdate.setBounds(45, 100, 110, 35);
/*     */     
/*  81 */     btCancel.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  84 */             UpdateUI.this.close();
/*     */           }
/*     */         });
/*  87 */     btCancel.setText("HỦY");
/*  88 */     this.container.add((Component)btCancel);
/*  89 */     btCancel.setBounds(165, 100, 110, 35);
/*     */     
/*  91 */     setSize(310, 160);
/*  92 */     super.onCreate();
/*     */   }
/*     */   
/*     */   private void update() {
/*  96 */     String cmd = FileUtils.validate("java -jar tools/update.jar ");
/*  97 */     cmd = cmd + "\"" + this.obj.getString("url") + "\" \"" + FileUtils.validate(AppUtils.curDir + "/tools/LeechText.jar") + "\"";
/*     */     try {
/*  99 */       Runtime.getRuntime().exec(cmd);
/* 100 */     } catch (IOException iOException) {}
/*     */     
/* 102 */     System.exit(0);
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\UpdateUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */