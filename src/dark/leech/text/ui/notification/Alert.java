/*    */ package dark.leech.text.ui.notification;
/*    */ 
/*    */ import dark.leech.text.ui.button.BasicButton;
/*    */ import dark.leech.text.ui.button.CloseButton;
/*    */ import dark.leech.text.ui.material.JMDialog;
/*    */ import dark.leech.text.util.AppUtils;
/*    */ import dark.leech.text.util.FontUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Container;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.LayoutManager;
/*    */ import java.awt.Point;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JTextPane;
/*    */ import javax.swing.border.Border;
/*    */ import javax.swing.border.LineBorder;
/*    */ 
/*    */ public class Alert extends JMDialog {
/*    */   private CloseButton labelClose;
/*    */   private JLabel labelTitle;
/*    */   
/*    */   private Alert(String text) {
/* 28 */     this.text = text;
/* 29 */     setAlwaysOnTop(true);
/* 30 */     setModal(true);
/* 31 */     setUndecorated(true);
/* 32 */     onCreate();
/* 33 */     open();
/*    */   }
/*    */   private BasicButton buttonOk; private JTextPane textPane; private String text;
/*    */   public static Alert show(String text) {
/* 37 */     return new Alert(text);
/*    */   }
/*    */   
/*    */   protected void onCreate() {
/* 41 */     super.onCreate();
/* 42 */     Container panel = getContentPane();
/* 43 */     this.labelTitle = new JLabel();
/* 44 */     this.buttonOk = new BasicButton();
/* 45 */     this.textPane = new JTextPane();
/*    */     
/* 47 */     panel.setLayout((LayoutManager)null);
/* 48 */     panel.setBackground(Color.WHITE);
/*    */ 
/*    */     
/* 51 */     this.labelClose = new CloseButton();
/* 52 */     this.labelClose.addActionListener(new ActionListener()
/*    */         {
/*    */           public void actionPerformed(ActionEvent e) {
/* 55 */             Alert.this.close();
/*    */           }
/*    */         });
/* 58 */     panel.add((Component)this.labelClose);
/* 59 */     this.labelClose.setBounds(290, 5, 25, 25);
/*    */ 
/*    */     
/* 62 */     this.labelTitle.setText("Có lỗi xảy ra!");
/* 63 */     this.labelTitle.setFont(FontUtils.TEXT_BOLD);
/* 64 */     panel.add(this.labelTitle);
/* 65 */     this.labelTitle.setBounds(15, 0, 245, 35);
/*    */ 
/*    */     
/* 68 */     this.buttonOk.setText("OK");
/* 69 */     this.buttonOk.addMouseListener(new MouseAdapter()
/*    */         {
/*    */           public void mouseClicked(MouseEvent e) {
/* 72 */             Alert.this.dispose();
/*    */           }
/*    */         });
/* 75 */     panel.add((Component)this.buttonOk);
/* 76 */     this.buttonOk.setBounds(10, 110, 280, 35);
/*    */ 
/*    */     
/* 79 */     this.textPane.setText(this.text);
/* 80 */     this.textPane.setBorder((Border)null);
/* 81 */     this.textPane.setBackground(Color.WHITE);
/* 82 */     this.textPane.setEditable(false);
/* 83 */     this.textPane.setFont(FontUtils.TEXT_NORMAL);
/* 84 */     panel.add(this.textPane);
/* 85 */     this.textPane.setBounds(20, 35, 285, 65);
/* 86 */     panel.setPreferredSize(new Dimension(320, 155));
/* 87 */     getRootPane().setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
/* 88 */     pack();
/* 89 */     Point pointLocation = AppUtils.getLocation();
/* 90 */     setLocation(pointLocation.x + 195 - getWidth() / 2, pointLocation.y + 300 - getHeight() / 2);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\notification\Alert.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */