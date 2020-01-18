/*    */ package dark.leech.text.ui.notification;
/*    */ import dark.leech.text.util.AppUtils;
/*    */ import dark.leech.text.util.ColorUtils;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import dark.leech.text.util.GraphicsUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.awt.GridLayout;
/*    */ import java.awt.geom.RoundRectangle2D;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JWindow;
/*    */ 
/*    */ public class Toast extends JWindow {
/* 14 */   private int timeShow = 2000; private static int toastCount;
/*    */   private String text;
/* 16 */   private Font fontText = FontUtils.TITLE_THIN;
/*    */ 
/*    */   
/*    */   public static Toast Build() {
/* 20 */     return new Toast();
/*    */   }
/*    */   
/*    */   public Toast time(int timeShow) {
/* 24 */     this.timeShow = timeShow;
/* 25 */     return this;
/*    */   }
/*    */   
/*    */   public Toast content(String text) {
/* 29 */     this.text = text;
/* 30 */     return this;
/*    */   }
/*    */   
/*    */   public Toast font(Font fontText) {
/* 34 */     this.fontText = fontText;
/* 35 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public void open() {
/* 40 */     if (toastCount != 0) {
/* 41 */       AppUtils.pause(1000);
/* 42 */       open();
/*    */     } else {
/* 44 */       doOpen();
/*    */     } 
/*    */   }
/*    */   private void doOpen() {
/* 48 */     toastCount++;
/* 49 */     setLayout(new GridLayout());
/* 50 */     setAlwaysOnTop(true);
/* 51 */     getContentPane().setBackground(ColorUtils.THEME_COLOR);
/* 52 */     JLabel label = new JLabel("  " + this.text + "  ");
/*    */     
/* 54 */     label.setForeground(Color.white);
/* 55 */     label.setFont(this.fontText);
/* 56 */     getContentPane().add(label);
/* 57 */     pack();
/* 58 */     setShape(new RoundRectangle2D.Double(0.0D, 0.0D, getWidth(), getHeight(), 5.0D, 5.0D));
/* 59 */     setVisible(true);
/* 60 */     final int x = AppUtils.getX() + 195 - getWidth() / 2;
/* 61 */     final int y = AppUtils.getY() + 80;
/*    */     
/* 63 */     (new Thread(new Runnable()
/*    */         {
/*    */           public void run() {
/*    */             try {
/*    */               int i;
/* 68 */               for (i = 0; i < 10; i++) {
/* 69 */                 Toast.this.setLocation(x, y + i);
/* 70 */                 if (GraphicsUtils.TRANSLUCENT_SUPPORT)
/* 71 */                   Toast.this.setOpacity(i / 10.0F); 
/* 72 */                 AppUtils.pause(20);
/*    */               } 
/* 74 */               AppUtils.pause(Toast.this.timeShow);
/* 75 */               for (i = 0; i < 10; i++) {
/* 76 */                 Toast.this.setLocation(x, y - i);
/* 77 */                 if (GraphicsUtils.TRANSLUCENT_SUPPORT)
/* 78 */                   Toast.this.setOpacity((10 - i) / 10.0F); 
/* 79 */                 AppUtils.pause(20);
/*    */               } 
/* 81 */             } catch (Exception exception) {}
/*    */             
/*    */             Toast.toastCount--;
/* 84 */             Toast.this.dispose();
/*    */           }
/* 87 */         })).start();
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\notification\Toast.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */