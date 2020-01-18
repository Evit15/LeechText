/*     */ package dark.leech.text.ui.main;
/*     */ 
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.material.JMCheckBox;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.ui.material.JMTextField;
/*     */ import dark.leech.text.util.ColorUtils;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JLabel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LoginUI
/*     */   extends JMDialog
/*     */ {
/*     */   private JLabel lbTitle;
/*     */   private JMTextField tfUser;
/*     */   private JMTextField tfPass;
/*     */   private JMCheckBox cbAuto;
/*     */   private JLabel lbUser;
/*     */   private JLabel lbPass;
/*     */   private BasicButton btCancel;
/*     */   private BasicButton btLogin;
/*     */   private String url;
/*     */   
/*     */   public LoginUI(String url) {
/*  35 */     setUndecorated(true);
/*  36 */     setModal(true);
/*  37 */     onCreate();
/*  38 */     this.url = url;
/*     */   }
/*     */   
/*     */   protected void onCreate() {
/*  42 */     super.onCreate();
/*  43 */     this.lbTitle = new JLabel();
/*  44 */     this.tfUser = new JMTextField();
/*  45 */     this.tfPass = new JMTextField();
/*  46 */     this.cbAuto = new JMCheckBox("Ghi nhớ");
/*  47 */     this.cbAuto.setVisible(false);
/*  48 */     this.lbUser = new JLabel();
/*  49 */     this.lbPass = new JLabel();
/*  50 */     this.btCancel = new BasicButton();
/*  51 */     this.btLogin = new BasicButton();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  57 */     this.lbTitle.setText("Đăng nhập");
/*  58 */     this.lbTitle.setHorizontalAlignment(0);
/*  59 */     this.lbTitle.setBackground(ColorUtils.THEME_COLOR);
/*  60 */     this.lbTitle.setOpaque(true);
/*  61 */     this.lbTitle.setFont(FontUtils.TITLE_NORMAL);
/*  62 */     this.lbTitle.setForeground(Color.white);
/*  63 */     this.container.add(this.lbTitle);
/*  64 */     this.lbTitle.setBounds(0, 0, 235, 60);
/*  65 */     this.container.add((Component)this.tfUser);
/*  66 */     this.container.add((Component)this.tfPass);
/*     */ 
/*     */     
/*  69 */     this.container.add((Component)this.cbAuto);
/*  70 */     this.cbAuto.setBounds(30, 205, 100, 25);
/*     */ 
/*     */     
/*  73 */     this.lbUser.setText("Tài khoản");
/*  74 */     this.tfUser.setBounds(30, 100, 175, 30);
/*  75 */     this.container.add(this.lbUser);
/*  76 */     this.lbUser.setBounds(30, 70, 175, 25);
/*     */ 
/*     */     
/*  79 */     this.lbPass.setText("Mật khẩu");
/*  80 */     this.tfPass.setBounds(30, 165, 175, 30);
/*  81 */     this.container.add(this.lbPass);
/*  82 */     this.lbPass.setBounds(30, 135, 175, 25);
/*     */     
/*  84 */     this.btCancel.setText("Hủy");
/*  85 */     this.btCancel.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  88 */             LoginUI.this.close();
/*     */           }
/*     */         });
/*  91 */     this.container.add((Component)this.btCancel);
/*  92 */     this.btCancel.setBounds(35, 240, 75, 30);
/*     */ 
/*     */     
/*  95 */     this.btLogin.setText("Login");
/*  96 */     this.btLogin.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  99 */             LoginUI.this.doLogin();
/*     */           }
/*     */         });
/*     */     
/* 103 */     this.container.add((Component)this.btLogin);
/* 104 */     this.btLogin.setBounds(125, 240, 80, 30);
/* 105 */     setSize(235, 290);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doLogin() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatus(String err) {
/* 141 */     if (err.length() == 0) { close(); }
/* 142 */     else if (err.indexOf("không tồn tại") > -1)
/* 143 */     { this.tfUser.addError("Tài khoản không tồn tại"); }
/* 144 */     else { this.tfPass.addError("Mật khẩu sai"); }
/*     */   
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\LoginUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */