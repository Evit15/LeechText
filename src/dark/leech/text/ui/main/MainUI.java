/*     */ package dark.leech.text.ui.main;
import dark.leech.text.image.GaussianBlurFilter;
/*     */ import dark.leech.text.listeners.AddListener;
/*     */ import dark.leech.text.listeners.BlurListener;
import dark.leech.text.plugin.PluginManager;
/*     */ import dark.leech.text.ui.Animation;
/*     */ import dark.leech.text.ui.button.CircleButton;
/*     */ import dark.leech.text.ui.button.CloseButton;
/*     */ import dark.leech.text.ui.download.AddURL;
/*     */ import dark.leech.text.ui.download.DownloadUI;
/*     */ import dark.leech.text.ui.main.plugin.PluginUI;
/*     */ import dark.leech.text.ui.material.JMMenuItem;
/*     */ import dark.leech.text.ui.material.JMPopupMenu;
/*     */ import dark.leech.text.util.AppUtils;
/*     */ import dark.leech.text.util.ColorUtils;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import dark.leech.text.util.GraphicsUtils;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Point;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseMotionAdapter;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.awt.image.RescaleOp;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.border.LineBorder;
/*     */ 
/*     */ public class MainUI extends JFrame implements BlurListener, ActionListener {
/*     */   private DownloadUI downloadUI;
/*     */   private SettingUI setting;
/*     */   private JPanel appBar;
/*     */   private CircleButton btAdd;
/*     */   private CircleButton btMenu;
/*     */   private JPanel statusBar;
/*     */   private CircleButton btBack;
/*     */   private CircleButton btOk;
/*     */   private JLabel lbLogo;
/*     */   private JPanel pnHeader;
/*  55 */   private int i = 0; private JMPopupMenu menu; private JMMenuItem pnSetting; private JMenuItem pnHelp; private JMMenuItem pnPlugin; private CloseButton btExit; private JLabel lbStatus; private Container container; private Point initialClick; private BufferedImage blurBuffer; private BufferedImage backBuffer; private Timer timer;
/*     */   
/*     */   public MainUI() {
/*  58 */     setLocation(AppUtils.width - 420, AppUtils.height - 650);
/*  59 */     setSize(390, 555);
/*  60 */     getRootPane().setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
/*  61 */     setUndecorated(true);
/*  62 */     setTitle("LeechText");
/*  63 */     setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/dark/leech/res/icon.png")));
/*  64 */     (new Thread(new Runnable()
/*     */         {
/*     */           public void run() {
/*  67 */             MainUI.this.onCreate();
/*     */           }
/*  69 */         })).start();
/*     */   }
/*     */ 
/*     */   
/*     */   private void onCreate() {
/*  74 */     this.container = getContentPane();
/*  75 */     this.container.setLayout((LayoutManager)null);
/*  76 */     this.container.setBackground(Color.WHITE);
/*  77 */     onCreateStatusBar();
/*  78 */     onCreateAppBar();
/*  79 */     createPanelHeaderUI();
/*  80 */     createPopupMenu();
/*  81 */     this.downloadUI = new DownloadUI();
/*  82 */     this.downloadUI.add(this.appBar);
/*  83 */     this.appBar.setBounds(0, 0, 390, 55);
/*  84 */     this.container.add((Component)this.downloadUI);
/*  85 */     this.downloadUI.setBounds(0, 20, 390, 535);
/*  86 */     this.setting = new SettingUI();
/*  87 */     this.setting.add(this.pnHeader);
/*  88 */     this.setting.setBounds(390, 20, 390, 535);
/*  89 */     this.pnHeader.setBounds(0, 0, 390, 55);
/*  90 */     this.container.add(this.setting);
/*  91 */     checkUpdate();
/*     */   }
/*     */   
/*     */   private void actionExit() {
/*  95 */     Animation.fadeOut(this);
/*     */   }
/*     */   
/*     */   private void actionAdd() {
/*  99 */     AddURL addURL = new AddURL();
/* 100 */     addURL.setAddListener((AddListener)this.downloadUI);
/* 101 */     addURL.open();
/*     */   }
/*     */ 
/*     */   
/*     */   private void onCreateStatusBar() {
/* 106 */     this.statusBar = new JPanel();
/* 107 */     this.statusBar.setBackground(ColorUtils.STATUS_BAR);
/* 108 */     this.statusBar.setLayout((LayoutManager)null);
/*     */ 
/*     */     
/* 111 */     this.btExit = new CloseButton();
/* 112 */     this.btExit.setFont(FontUtils.iconFont(18.0F));
/* 113 */     this.btExit.addActionListener(this);
/* 114 */     this.statusBar.add((Component)this.btExit);
/* 115 */     this.btExit.setBounds(360, 0, 20, 20);
/*     */     
/* 117 */     this.lbStatus = new JLabel();
/* 118 */     this.lbStatus.setForeground(Color.white);
/* 119 */     this.lbStatus.setFocusable(false);
/* 120 */     this.lbStatus.setFont(FontUtils.textFont(13.0F, 0));
/* 121 */     this.statusBar.add(this.lbStatus);
/* 122 */     this.lbStatus.setBounds(5, 0, 315, 20);
/* 123 */     this.timer = new Timer(1000, new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 126 */             DateFormat dateFormat = new SimpleDateFormat("HH:mm");
/* 127 */             Date date = new Date();
/* 128 */             MainUI.this.lbStatus.setText(dateFormat.format(date));
/*     */           }
/*     */         });
/* 131 */     this.container.add(this.statusBar);
/* 132 */     this.statusBar.setBounds(0, 0, 390, 20);
/* 133 */     this.statusBar.addMouseListener(new MouseAdapter() {
/*     */           public void mousePressed(MouseEvent e) {
/* 135 */             MainUI.this.initialClick = e.getPoint();
/* 136 */             MainUI.this.getComponentAt(MainUI.this.initialClick);
/*     */           }
/*     */         });
/* 139 */     this.statusBar.addMouseMotionListener(new MouseMotionAdapter()
/*     */         {
/*     */           public void mouseDragged(MouseEvent e) {
/* 142 */             MainUI.this.movieWindows(e);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onCreateAppBar() {
/* 150 */     this.appBar = new JPanel();
/* 151 */     this.appBar.setBackground(ColorUtils.THEME_COLOR);
/* 152 */     this.appBar.setLayout((LayoutManager)null);
/*     */     
/* 154 */     this.btAdd = new CircleButton("", 25.0F);
/* 155 */     this.btAdd.addActionListener(this);
/* 156 */     this.appBar.add((Component)this.btAdd);
/* 157 */     this.btAdd.setBounds(305, 5, 45, 45);
/*     */ 
/*     */     
/* 160 */     JLabel logo = new JLabel();
/* 161 */     logo.setText("Leech Text");
/* 162 */     logo.setFont(FontUtils.TITLE_BIG);
/* 163 */     logo.setForeground(Color.white);
/* 164 */     logo.setHorizontalAlignment(0);
/* 165 */     this.appBar.add(logo);
/* 166 */     logo.setBounds(20, 0, (logo.getPreferredSize()).width, 55);
/*     */     
/* 168 */     this.btMenu = new CircleButton("", 25.0F);
/* 169 */     this.btMenu.addActionListener(this);
/* 170 */     this.appBar.add((Component)this.btMenu);
/* 171 */     this.btMenu.setBounds(355, 5, 30, 45);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createPanelHeaderUI() {
/* 176 */     this.pnHeader = new JPanel();
/* 177 */     this.pnHeader.setBackground(ColorUtils.THEME_COLOR);
/* 178 */     this.pnHeader.setLayout((LayoutManager)null);
/*     */     
/* 180 */     this.btBack = new CircleButton("", 25.0F);
/* 181 */     this.btBack.addActionListener(this);
/* 182 */     this.pnHeader.add((Component)this.btBack);
/* 183 */     this.btBack.setBounds(5, 5, 45, 45);
/*     */     
/* 185 */     this.lbLogo = new JLabel();
/* 186 */     this.lbLogo.setText("Cài đặt");
/* 187 */     this.lbLogo.setFont(FontUtils.TITLE_BIG);
/* 188 */     this.lbLogo.setForeground(Color.white);
/* 189 */     this.lbLogo.setHorizontalAlignment(0);
/* 190 */     this.pnHeader.add(this.lbLogo);
/* 191 */     this.lbLogo.setBounds(55, 0, 100, 55);
/*     */     
/* 193 */     this.btOk = new CircleButton("", 25.0F);
/* 194 */     this.btOk.addActionListener(this);
/* 195 */     this.pnHeader.add((Component)this.btOk);
/* 196 */     this.btOk.setBounds(335, 5, 45, 45);
/*     */   }
/*     */   
/*     */   private void createPopupMenu() {
/* 200 */     ActionListener actionListener = new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 203 */           if (e.getSource() == MainUI.this.pnSetting) {
/* 204 */             Animation.go((JPanel)MainUI.this.downloadUI, MainUI.this.setting);
/* 205 */             MainUI.this.setting.load();
/*     */           } 
/* 207 */           if (e.getSource() == MainUI.this.pnHelp)
/* 208 */             (new HelpUI()).open(); 
/* 209 */           if (e.getSource() == MainUI.this.pnPlugin)
/* 210 */             (new PluginUI()).open(); 
/*     */         }
/*     */       };
/* 213 */     this.menu = new JMPopupMenu();
/* 214 */     this.pnSetting = new JMMenuItem("Cài đặt");
/* 215 */     this.pnSetting.addActionListener(actionListener);
/* 216 */     this.menu.add((JMenuItem)this.pnSetting);
/* 217 */     this.pnPlugin = new JMMenuItem("Plugins");
/* 218 */     this.pnPlugin.addActionListener(actionListener);
/* 219 */     this.menu.add((JMenuItem)this.pnPlugin);
/* 220 */     this.pnHelp = (JMenuItem)new JMMenuItem("Thông tin");
/* 221 */     this.pnHelp.addActionListener(actionListener);
/* 222 */     this.menu.add(this.pnHelp);
/* 223 */     this.container.add((Component)this.menu);
/*     */   }
/*     */   
/*     */   private void movieWindows(MouseEvent e) {
/* 227 */     int thisX = (getLocation()).x;
/* 228 */     int thisY = (getLocation()).y;
/* 229 */     int xMoved = thisX + e.getX() - thisX + this.initialClick.x;
/* 230 */     int yMoved = thisY + e.getY() - thisY + this.initialClick.y;
/* 231 */     int X = thisX + xMoved;
/* 232 */     X = (X < 10) ? 10 : X;
/* 233 */     X = (X + getWidth() > AppUtils.width) ? (AppUtils.width - getWidth() - 10) : X;
/* 234 */     int Y = thisY + yMoved;
/* 235 */     Y = (Y + getHeight() > AppUtils.height) ? (AppUtils.height - getHeight() - 10) : Y;
/* 236 */     Y = (Y < 10) ? 10 : Y;
/* 237 */     setLocation(X, Y);
/* 238 */     AppUtils.LOCATION = getLocation();
/*     */   }
/*     */   
/*     */   private void checkUpdate() {
/* 242 */     this.i = 0;
/* 243 */     final String[] s = { ".", "..", "...", "...." };
/* 244 */     Timer time = new Timer(200, new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 247 */             MainUI.this.lbStatus.setText("Đang kiểm tra cập nhật" + s[MainUI.this.i = (MainUI.this.i + 1) % 4]);
/*     */           }
/*     */         });
/* 250 */     time.start();
/*     */     
/* 252 */     PluginManager.getManager();
/* 253 */     UpdateUI.checkUpdate();
/* 254 */     time.stop();
/* 255 */     this.timer.start();
/*     */   }
/*     */ 
/*     */   
/*     */   private void createBlur() {
/* 260 */     Component root = getRootPane();
/* 261 */     this.blurBuffer = GraphicsUtils.createCompatibleImage(getWidth(), getHeight());
/* 262 */     Graphics2D g2 = this.blurBuffer.createGraphics();
/* 263 */     root.paint(g2);
/* 264 */     g2.dispose();
/*     */     
/* 266 */     this.backBuffer = this.blurBuffer;
/* 267 */     this.blurBuffer = GraphicsUtils.createThumbnailFast(this.blurBuffer, getWidth() / 2);
/* 268 */     this.blurBuffer = (new GaussianBlurFilter(3)).filter(this.blurBuffer, null);
/* 269 */     RescaleOp op = new RescaleOp(0.9F, 0.0F, null);
/* 270 */     this.blurBuffer = op.filter(this.blurBuffer, (BufferedImage)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlur(boolean blur) {
/* 275 */     if (blur) {
/* 276 */       createBlur();
/*     */     } else {
/* 278 */       this.blurBuffer = null;
/* 279 */     }  repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/* 285 */     super.paint(g);
/* 286 */     if (isVisible() && this.blurBuffer != null) {
/* 287 */       Graphics2D g2 = (Graphics2D)g.create();
/* 288 */       g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 289 */       g2.drawImage(this.backBuffer, 0, 0, (ImageObserver)null);
/* 290 */       g2.setComposite(AlphaComposite.SrcOver.derive(0.9F));
/* 291 */       g2.drawImage(this.blurBuffer, 0, 0, getWidth(), getHeight(), null);
/* 292 */       g2.dispose();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 298 */     if (e.getSource() == this.btMenu) {
/* 299 */       this.menu.show((Component)this.btMenu, this.btMenu.getWidth() / 2 - 70, this.btMenu.getHeight() / 2 - 20);
/*     */     }
/* 301 */     if (e.getSource() == this.btExit) {
/* 302 */       actionExit();
/*     */     }
/* 304 */     if (e.getSource() == this.btAdd) {
/* 305 */       actionAdd();
/*     */     }
/*     */     
/* 308 */     if (e.getSource() == this.btBack) {
/* 309 */       Animation.go(this.setting, (JPanel)this.downloadUI);
/*     */     }
/* 311 */     if (e.getSource() == this.btOk) {
/* 312 */       this.setting.save();
/* 313 */       Animation.go(this.setting, (JPanel)this.downloadUI);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\MainUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */