/*     */ package dark.leech.text.ui.main;
/*     */ 
/*     */ import dark.leech.text.image.ImageLabel;
/*     */ import dark.leech.text.listeners.BlurListener;
/*     */ import dark.leech.text.models.Properties;
/*     */ import dark.leech.text.ui.PanelTitle;
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.button.CircleButton;
/*     */ import dark.leech.text.ui.main.export.ExportEbook;
/*     */ import dark.leech.text.ui.main.export.ExportText;
/*     */ import dark.leech.text.ui.main.export.config.ConfigUI;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.ui.material.JMTextField;
/*     */ import dark.leech.text.util.AppUtils;
/*     */ import dark.leech.text.util.FileUtils;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class InfoUI
/*     */   extends JMDialog {
/*     */   private PanelTitle pnTitle;
/*     */   private JPanel pnCover;
/*     */   private CircleButton btEdit;
/*     */   private ImageLabel lbCover;
/*     */   private BasicButton btConfig;
/*     */   private BasicButton btText;
/*     */   private BasicButton btExport;
/*     */   private JLabel lbName;
/*     */   private JMTextField tfName;
/*     */   private JLabel lbAuthor;
/*     */   private JMTextField tfAuthor;
/*     */   private JLabel lbStatus;
/*     */   private GioiThieu gioiThieu;
/*     */   private Properties properties;
/*     */   
/*     */   public InfoUI(Properties properties) {
/*  47 */     this.properties = properties;
/*  48 */     setSize(340, 265);
/*  49 */     runOnUiThread(new Runnable()
/*     */         {
/*     */           public void run() {
/*  52 */             InfoUI.this.onCreate();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/*  59 */     super.onCreate();
/*  60 */     this.pnTitle = new PanelTitle();
/*  61 */     this.pnCover = new JPanel();
/*  62 */     this.lbCover = new ImageLabel();
/*  63 */     this.btConfig = new BasicButton();
/*  64 */     this.btText = new BasicButton();
/*  65 */     this.btExport = new BasicButton();
/*  66 */     this.lbName = new JLabel();
/*  67 */     this.lbAuthor = new JLabel();
/*  68 */     this.lbStatus = new JLabel();
/*  69 */     this.gioiThieu = new GioiThieu(this.properties);
/*     */ 
/*     */     
/*  72 */     this.pnTitle.setText(this.properties.getName());
/*  73 */     this.pnTitle.addCloseListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  76 */             InfoUI.this.close();
/*     */           }
/*     */         });
/*  79 */     this.container.add((Component)this.pnTitle);
/*  80 */     this.pnTitle.setBounds(0, 0, 340, 45);
/*     */     
/*  82 */     this.pnCover.setLayout((LayoutManager)null);
/*     */ 
/*     */     
/*  85 */     this.btEdit = new CircleButton("", 16.0F);
/*  86 */     this.btEdit.setForeground(Color.BLACK);
/*  87 */     this.btEdit.setBackground(Color.darkGray);
/*  88 */     this.btEdit.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  91 */             FileDialog file = new FileDialog((Frame)null, "Chọn ảnh", 0);
/*  92 */             file.setLocation(AppUtils.getLocation());
/*  93 */             file.setModal(true);
/*  94 */             file.setVisible(true);
/*  95 */             if (file.getFile() != null) {
/*  96 */               FileUtils.copyFile(file.getDirectory() + file.getFile(), InfoUI.this.properties.getSavePath() + "/data/cover.jpg");
/*  97 */               InfoUI.this.lbCover.path(InfoUI.this.properties.getSavePath() + "/data/cover.jpg")
/*  98 */                 .load();
/*     */             } 
/*     */           }
/*     */         });
/* 102 */     this.pnCover.add((Component)this.btEdit);
/* 103 */     this.btEdit.setBounds(70, 120, 30, 30);
/*     */ 
/*     */     
/* 106 */     this.pnCover.add((Component)this.lbCover);
/* 107 */     this.lbCover.setBounds(0, 0, 100, 150);
/*     */ 
/*     */     
/* 110 */     this.container.add(this.pnCover);
/* 111 */     this.pnCover.setBounds(5, 50, 100, 150);
/*     */ 
/*     */     
/* 114 */     this.btConfig.setText("Hiệu Chỉnh");
/* 115 */     this.btConfig.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mouseClicked(MouseEvent e) {
/* 118 */             InfoUI.this.doConfig();
/*     */           }
/*     */         });
/* 121 */     this.container.add((Component)this.btConfig);
/* 122 */     this.btConfig.setBounds(10, 215, 100, 35);
/*     */ 
/*     */     
/* 125 */     this.btText.setText("Xuất Text");
/* 126 */     this.btText.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mouseClicked(MouseEvent e) {
/* 129 */             InfoUI.this.exportText();
/*     */           }
/*     */         });
/* 132 */     this.container.add((Component)this.btText);
/* 133 */     this.btText.setBounds(115, 215, 100, 35);
/*     */ 
/*     */     
/* 136 */     this.btExport.setText("Xuất Ebook");
/* 137 */     this.btExport.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mouseClicked(MouseEvent e) {
/* 140 */             InfoUI.this.exportEbook();
/*     */           }
/*     */         });
/* 143 */     this.container.add((Component)this.btExport);
/* 144 */     this.btExport.setBounds(225, 215, 100, 35);
/*     */ 
/*     */     
/* 147 */     this.lbName.setText("Tên truyện");
/* 148 */     this.lbName.setFont(FontUtils.TEXT_NORMAL);
/* 149 */     this.container.add(this.lbName);
/* 150 */     this.lbName.setBounds(115, 50, 220, 20);
/* 151 */     this.tfName = new JMTextField();
/* 152 */     this.tfName.setText(this.properties.getName());
/* 153 */     this.tfName.setBounds(115, 75, 220, 30);
/* 154 */     this.container.add((Component)this.tfName);
/*     */ 
/*     */ 
/*     */     
/* 158 */     this.lbAuthor.setText("Tác giả");
/* 159 */     this.lbAuthor.setFont(FontUtils.TEXT_NORMAL);
/* 160 */     this.container.add(this.lbAuthor);
/* 161 */     this.lbAuthor.setBounds(115, 110, 220, 20);
/* 162 */     this.tfAuthor = new JMTextField();
/* 163 */     this.tfAuthor.setText(this.properties.getAuthor());
/* 164 */     this.tfAuthor.setBounds(115, 135, 220, 30);
/* 165 */     this.container.add((Component)this.tfAuthor);
/*     */     
/* 167 */     this.container.add((Component)this.gioiThieu);
/* 168 */     this.gioiThieu.addBlurListener((BlurListener)this);
/* 169 */     this.gioiThieu.setBounds(115, 170, 220, 30);
/*     */ 
/*     */     
/* 172 */     this.lbStatus.setFont(FontUtils.TEXT_NORMAL);
/* 173 */     this.container.add(this.lbStatus);
/* 174 */     this.lbStatus.setBounds(115, 170, 220, 30);
/* 175 */     this.lbCover.path(this.properties.getSavePath() + "/data/cover.jpg")
/* 176 */       .load();
/*     */   }
/*     */ 
/*     */   
/*     */   private void doConfig() {
/* 181 */     this.properties.setName(this.tfName.getText());
/* 182 */     this.properties.setAuthor(this.tfAuthor.getText());
/* 183 */     ConfigUI configUI = new ConfigUI(this.properties);
/* 184 */     configUI.setBlurListener((BlurListener)this);
/* 185 */     configUI.open();
/*     */   }
/*     */   
/*     */   private void exportText() {
/* 189 */     this.properties.setName(this.tfName.getText());
/* 190 */     this.properties.setAuthor(this.tfAuthor.getText());
/* 191 */     ExportText export = new ExportText(this.properties);
/* 192 */     export.setBlurListener((BlurListener)this);
/* 193 */     export.open();
/*     */   }
/*     */   
/*     */   private void exportEbook() {
/* 197 */     this.properties.setName(this.tfName.getText());
/* 198 */     this.properties.setAuthor(this.tfAuthor.getText());
/* 199 */     ExportEbook export = new ExportEbook(this.properties);
/* 200 */     export.setBlurListener((BlurListener)this);
/* 201 */     export.open();
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\InfoUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */