/*     */ package dark.leech.text.ui.main.export.config;
/*     */ 
/*     */ import dark.leech.text.action.Config;
/*     */ import dark.leech.text.action.History;
/*     */ import dark.leech.text.listeners.BlurListener;
/*     */ import dark.leech.text.listeners.ChangeListener;
/*     */ import dark.leech.text.models.Chapter;
/*     */ import dark.leech.text.models.Properties;
/*     */ import dark.leech.text.ui.PanelTitle;
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.List;
/*     */ import javax.swing.JLabel;
/*     */ 
/*     */ public class ConfigUI
/*     */   extends JMDialog implements ChangeListener {
/*     */   public static final int NAME = 0;
/*     */   public static final int IMG = 1;
/*     */   public static final int ERROR = 2;
/*     */   public static final int OPTIMIZE = 3;
/*     */   private PanelTitle pnTitle;
/*     */   private BasicButton btOk;
/*     */   private JLabel lbName;
/*     */   private JLabel lbImg;
/*     */   private JLabel lbError;
/*     */   private BasicButton btName;
/*     */   private BasicButton btError;
/*     */   private BasicButton btImg;
/*     */   private BasicButton btList;
/*     */   protected Properties properties;
/*     */   private List<Chapter> chapList;
/*     */   private Config config;
/*     */   private List<Chapter> nameList;
/*     */   private List<Chapter> imgList;
/*     */   private List<Chapter> errorList;
/*     */   
/*     */   public ConfigUI(Properties properties) {
/*  42 */     this.properties = properties;
/*  43 */     this.chapList = properties.getChapList();
/*  44 */     setSize(295, 260);
/*  45 */     runOnUiThread(new Runnable()
/*     */         {
/*     */           public void run() {
/*  48 */             ConfigUI.this.onCreate();
/*  49 */             ConfigUI.this.loadErr();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/*  57 */     super.onCreate();
/*  58 */     this.pnTitle = new PanelTitle();
/*  59 */     this.btOk = new BasicButton();
/*  60 */     this.lbName = new JLabel();
/*  61 */     this.lbImg = new JLabel();
/*  62 */     this.lbError = new JLabel();
/*  63 */     this.btName = new BasicButton();
/*  64 */     this.btError = new BasicButton();
/*  65 */     this.btImg = new BasicButton();
/*  66 */     this.btList = new BasicButton();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     this.pnTitle.setText("Hiệu Chỉnh");
/*  72 */     this.pnTitle.addCloseListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  75 */             ConfigUI.this.close();
/*     */           }
/*     */         });
/*  78 */     this.container.add((Component)this.pnTitle);
/*  79 */     this.pnTitle.setBounds(0, 0, 295, 45);
/*     */ 
/*     */     
/*  82 */     this.btOk.setText("Hoàn Tất");
/*  83 */     this.btOk.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  86 */             ConfigUI.this.saveProperties();
/*  87 */             ConfigUI.this.close();
/*     */           }
/*     */         });
/*  90 */     this.container.add((Component)this.btOk);
/*  91 */     this.btOk.setBounds(170, 210, 100, 35);
/*     */ 
/*     */     
/*  94 */     this.btList.setText("Xem DS");
/*  95 */     this.btList.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  98 */             ConfigUI.this.Optimize();
/*     */           }
/*     */         });
/* 101 */     this.container.add((Component)this.btList);
/* 102 */     this.btList.setBounds(10, 210, 100, 35);
/*     */ 
/*     */     
/* 105 */     this.lbName.setFont(FontUtils.TEXT_NORMAL);
/* 106 */     this.container.add(this.lbName);
/* 107 */     this.lbName.setBounds(10, 60, 175, 35);
/*     */ 
/*     */     
/* 110 */     this.lbImg.setFont(FontUtils.TEXT_NORMAL);
/* 111 */     this.container.add(this.lbImg);
/* 112 */     this.lbImg.setBounds(10, 105, 175, 35);
/*     */ 
/*     */     
/* 115 */     this.lbError.setFont(FontUtils.TEXT_NORMAL);
/* 116 */     this.container.add(this.lbError);
/* 117 */     this.lbError.setBounds(10, 150, 175, 35);
/*     */ 
/*     */     
/* 120 */     this.btName.setText("H.Chỉnh");
/* 121 */     this.btName.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 124 */             ConfigUI.this.editName();
/*     */           }
/*     */         });
/* 127 */     this.container.add((Component)this.btName);
/* 128 */     this.btName.setBounds(185, 60, 95, 35);
/*     */ 
/*     */     
/* 131 */     this.btError.setText("H.Chỉnh");
/* 132 */     this.btError.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 135 */             ConfigUI.this.editError();
/*     */           }
/*     */         });
/* 138 */     this.container.add((Component)this.btError);
/* 139 */     this.btError.setBounds(185, 150, 95, 35);
/*     */ 
/*     */     
/* 142 */     this.btImg.setText("H.Chỉnh");
/* 143 */     this.btImg.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 146 */             ConfigUI.this.editImg();
/*     */           }
/*     */         });
/* 149 */     this.container.add((Component)this.btImg);
/* 150 */     this.btImg.setBounds(185, 105, 95, 35);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadErr() {
/* 156 */     this.config = new Config(this.chapList);
/* 157 */     this.nameList = this.config.checkName();
/* 158 */     this.lbName.setText("Tên chương: " + Integer.toString(this.nameList.size()) + " không hợp lệ");
/* 159 */     if (this.nameList.size() == 0) {
/* 160 */       this.btName.setVisible(false);
/*     */     }
/* 162 */     this.imgList = this.config.checkImg();
/* 163 */     this.lbImg.setText("Chương ảnh: " + Integer.toString(this.imgList.size()) + " chương");
/* 164 */     if (this.imgList.size() == 0) {
/* 165 */       this.btImg.setVisible(false);
/*     */     }
/* 167 */     this.errorList = this.config.checkError();
/* 168 */     this.lbError.setText("Chương lỗi: " + Integer.toString(this.errorList.size()) + " chương");
/* 169 */     if (this.errorList.size() == 0)
/* 170 */       this.btError.setVisible(false); 
/*     */   }
/*     */   
/*     */   private void editName() {
/* 174 */     ListUI listName = new ListUI(this.nameList, "Tên chương không hợp lệ", this.properties.getSavePath());
/* 175 */     listName.setBlurListener((BlurListener)this);
/* 176 */     listName.setAction(0);
/* 177 */     listName.open();
/*     */   }
/*     */   
/*     */   private void editImg() {
/* 181 */     ListUI listImg = new ListUI(this.imgList, "Chương ảnh", this.properties.getSavePath());
/* 182 */     listImg.setBlurListener((BlurListener)this);
/* 183 */     listImg.setAction(1);
/* 184 */     listImg.open();
/*     */   }
/*     */   
/*     */   private void editError() {
/* 188 */     ListUI listError = new ListUI(this.errorList, "Chương lỗi", this.properties.getSavePath());
/* 189 */     listError.setBlurListener((BlurListener)this);
/* 190 */     listError.setProperties(this.properties);
/* 191 */     listError.setAction(2);
/* 192 */     listError.open();
/*     */   }
/*     */   
/*     */   private void Optimize() {
/* 196 */     ListUI list = new ListUI(this.chapList, "Danh sách chương", this.properties.getSavePath());
/* 197 */     list.setBlurListener((BlurListener)this);
/* 198 */     list.setAction(3);
/* 199 */     list.open();
/*     */   }
/*     */   
/*     */   private void saveProperties() {
/* 203 */     this.properties.setSize(this.chapList.size());
/* 204 */     History.getHistory().save(this.properties);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doChanger() {
/* 209 */     repaint();
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\export\config\ConfigUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */