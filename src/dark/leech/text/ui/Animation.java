/*    */ package dark.leech.text.ui;
/*    */ 
/*    */ import dark.leech.text.util.AppUtils;
/*    */ import dark.leech.text.util.GraphicsUtils;
/*    */ import java.awt.Window;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ public class Animation
/*    */ {
/*    */   public static void go(final JPanel from, final JPanel to) {
/* 13 */     final int y = from.getY();
/* 14 */     (new Thread(new Runnable()
/*    */         {
/*    */           public void run() {
/* 17 */             for (int i = 20; i >= 0; i--) {
/* 18 */               from.setLocation(i - 390, y);
/* 19 */               to.setLocation(i, y);
/* 20 */               AppUtils.pause(50 / (i + 1));
/*    */             } 
/*    */           }
/* 23 */         })).start();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void fadeIn(final Window dialog) {
/* 28 */     final int x = (dialog.getLocation()).x;
/* 29 */     final int y = (dialog.getLocation()).y - 20;
/*    */     
/* 31 */     (new Thread(new Runnable()
/*    */         {
/*    */           public void run()
/*    */           {
/* 35 */             for (int i = 0; i <= 20; i++) {
/* 36 */               dialog.setLocation(x, y + i);
/* 37 */               dialog.repaint();
/* 38 */               if (GraphicsUtils.TRANSLUCENT_SUPPORT)
/* 39 */                 dialog.setOpacity(0.05F * i); 
/* 40 */               AppUtils.pause(i * 2);
/*    */             
/*    */             }
/*    */           
/*    */           }
/* 45 */         })).start();
/*    */   }
/*    */   
/*    */   public static void fadeOut(final Window dialog) {
/* 49 */     if (dialog instanceof JDialog)
/* 50 */       ((JDialog)dialog).setModal(false); 
/* 51 */     final int x = (dialog.getLocation()).x;
/* 52 */     final int y = (dialog.getLocation()).y;
/* 53 */     (new Thread(new Runnable()
/*    */         {
/*    */           public void run()
/*    */           {
/* 57 */             for (int i = 1; i <= 20; i++) {
/* 58 */               dialog.setLocation(x, y + i);
/* 59 */               dialog.repaint();
/* 60 */               if (GraphicsUtils.TRANSLUCENT_SUPPORT)
/* 61 */                 dialog.setOpacity(0.05F * (20 - i)); 
/* 62 */               AppUtils.pause(i * 2);
/*    */             } 
/* 64 */             dialog.dispose();
/* 65 */             if (dialog instanceof dark.leech.text.ui.main.MainUI)
/* 66 */               System.exit(0); 
/*    */           }
/* 68 */         })).start();
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\Animation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */