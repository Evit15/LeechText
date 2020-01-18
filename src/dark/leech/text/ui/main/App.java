/*    */ package dark.leech.text.ui.main;
/*    */ 
/*    */ import dark.leech.text.ui.Animation;
/*    */ import dark.leech.text.util.AppUtils;
/*    */ import dark.leech.text.util.FileUtils;
/*    */ import dark.leech.text.util.SettingUtils;
/*    */ import java.awt.Point;
/*    */ import javax.swing.UIManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class App
/*    */ {
/*    */   private static MainUI mainFrame;
/*    */   private static int openCount;
/*    */   private static int closeCount;
/*    */   
/*    */   public static void main(String[] args) {
/* 19 */     (new Thread(new Runnable()
/*    */         {
/*    */           public void run() {
/*    */             try {
/* 23 */               UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
/* 24 */             } catch (Exception exception) {}
/*    */             
/* 26 */             AppUtils.doLoad();
/* 27 */             FileUtils.init();
/* 28 */             SettingUtils.doLoad();
/* 29 */             App.mainFrame = new MainUI();
/* 30 */             Animation.fadeIn(App.mainFrame);
/* 31 */             App.mainFrame.setVisible(true);
/* 32 */             AppUtils.LOCATION = new Point((App.mainFrame.getLocation()).x, (App.mainFrame.getLocation()).y + 20);
/*    */           }
/* 34 */         })).start();
/*    */   }
/*    */   
/*    */   public static MainUI getMain() {
/* 38 */     return mainFrame;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\App.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */