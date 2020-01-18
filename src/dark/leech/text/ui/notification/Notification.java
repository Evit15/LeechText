/*     */ package dark.leech.text.ui.notification;
/*     */ 
/*     */ import dark.leech.text.image.ImageLabel;
/*     */ import dark.leech.text.util.AppUtils;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.LayoutManager;
/*     */ import java.io.InputStream;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JWindow;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.border.LineBorder;
/*     */ 
/*     */ public class Notification
/*     */   extends JWindow
/*     */ {
/*     */   public static int pointX;
/*     */   public static int pointY;
/*     */   private String name;
/*  25 */   private int timeDelay = 5000; private String contentNotification; private String imagePath; private InputStream imageStream;
/*     */   private boolean close = false;
/*     */   
/*     */   public static Notification build() {
/*  29 */     return new Notification();
/*     */   }
/*     */   
/*     */   public Notification title(String name) {
/*  33 */     this.name = name;
/*  34 */     return this;
/*     */   }
/*     */   
/*     */   public Notification content(String contentNotification) {
/*  38 */     this.contentNotification = contentNotification;
/*  39 */     return this;
/*     */   }
/*     */   
/*     */   public Notification path(String imagePath) {
/*  43 */     this.imagePath = imagePath;
/*  44 */     return this;
/*     */   }
/*     */   
/*     */   public Notification input(InputStream imageStream) {
/*  48 */     this.imageStream = imageStream;
/*  49 */     return this;
/*     */   }
/*     */   
/*     */   public Notification delay(int timeDelay) {
/*  53 */     this.timeDelay = timeDelay;
/*  54 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onCreate() {
/*  60 */     setAlwaysOnTop(true);
/*  61 */     setSize(280, 90);
/*  62 */     ImageLabel lbCover = new ImageLabel();
/*  63 */     JLabel lbName = new JLabel();
/*  64 */     JLabel lbNoti = new JLabel();
/*  65 */     JLabel lbTime = new JLabel();
/*  66 */     Container panel1 = getContentPane();
/*  67 */     panel1.setBackground(Color.WHITE);
/*  68 */     panel1.setLayout((LayoutManager)null);
/*     */     
/*  70 */     lbCover.path(this.imagePath).input(this.imageStream).load();
/*  71 */     lbCover.setOpaque(true);
/*  72 */     panel1.add((Component)lbCover);
/*  73 */     lbCover.setBounds(5, 5, 54, 81);
/*     */ 
/*     */     
/*  76 */     lbName.setText(this.name);
/*  77 */     lbName.setFont(FontUtils.TEXT_NORMAL);
/*  78 */     panel1.add(lbName);
/*  79 */     lbName.setBounds(70, 5, 175, 30);
/*     */ 
/*     */     
/*  82 */     lbNoti.setText(this.contentNotification);
/*  83 */     lbNoti.setFont(FontUtils.TEXT_NORMAL);
/*  84 */     panel1.add(lbNoti);
/*  85 */     lbNoti.setBounds(70, 35, 170, 30);
/*     */ 
/*     */     
/*  88 */     Date todaysDate = new Date();
/*  89 */     DateFormat df = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");
/*  90 */     lbTime.setText(df.format(todaysDate));
/*  91 */     lbTime.setFont(FontUtils.TEXT_THIN);
/*  92 */     lbTime.setHorizontalAlignment(4);
/*  93 */     panel1.add(lbTime);
/*  94 */     lbTime.setBounds(130, 65, 140, 20);
/*  95 */     getRootPane().setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void open() {
/* 100 */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           public void run() {
/* 103 */             Notification.this.onCreate();
/* 104 */             Notification.this.setVisible(true);
/* 105 */             int width = AppUtils.width;
/* 106 */             int height = AppUtils.height;
/* 107 */             if (Notification.pointX == 0) Notification.pointX = width; 
/* 108 */             if (Notification.pointY == 0) Notification.pointY = height - Notification.this.getHeight() - 50; 
/* 109 */             final int x = Notification.pointX;
/* 110 */             final int y = Notification.pointY;
/* 111 */             Notification.pointY -= Notification.this.getHeight() + 5;
/* 112 */             (new Thread(new Runnable()
/*     */                 {
/*     */                   public void run() {
/* 115 */                     for (int i = 0; i < Notification.this.getWidth() + 10; i++) {
/* 116 */                       Notification.this.setLocation(x - i, y);
/* 117 */                       AppUtils.pause(3);
/*     */                     } 
/* 119 */                     AppUtils.pause(Notification.this.timeDelay);
/* 120 */                     if (!Notification.this.close) Notification.this.doClose(); 
/*     */                   }
/* 122 */                 })).start();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void doClose() {
/* 129 */     if (this.close)
/* 130 */       return;  this.close = true;
/* 131 */     final int x = (getLocation()).x;
/* 132 */     final int y = (getLocation()).y;
/* 133 */     pointY = y;
/* 134 */     (new Thread(new Runnable()
/*     */         {
/*     */           public void run() {
/* 137 */             for (int i = 0; i < Notification.this.getWidth() + 10; i++) {
/* 138 */               Notification.this.setLocation(x + i, y);
/* 139 */               AppUtils.pause(3);
/*     */             } 
/* 141 */             Notification.this.dispose();
/*     */           }
/* 143 */         })).start();
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\notification\Notification.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */