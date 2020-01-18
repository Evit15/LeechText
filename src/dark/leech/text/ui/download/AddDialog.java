/*     */ package dark.leech.text.ui.download;
/*     */ 
/*     */ import dark.leech.text.enities.PluginEntity;
/*     */ import dark.leech.text.get.InfoExecute;
/*     */ import dark.leech.text.get.ListExecute;
/*     */ import dark.leech.text.listeners.AddListener;
/*     */ import dark.leech.text.listeners.BlurListener;
/*     */ import dark.leech.text.listeners.ChangeListener;
/*     */ import dark.leech.text.models.Chapter;
/*     */ import dark.leech.text.models.Pager;
/*     */ import dark.leech.text.models.Properties;
/*     */ import dark.leech.text.plugin.PluginManager;
/*     */ import dark.leech.text.ui.CircleWait;
/*     */ import dark.leech.text.ui.ListDialog;
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.material.JMDialog;
/*     */ import dark.leech.text.ui.material.JMTextField;
/*     */ import dark.leech.text.util.FileUtils;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import dark.leech.text.util.SettingUtils;
/*     */ import dark.leech.text.util.SyntaxUtils;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JLayer;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class AddDialog
/*     */   extends JMDialog {
/*     */   private JLabel lbName;
/*     */   private JLabel lbAuthor;
/*     */   private JLabel lbChap;
/*     */   private JMTextField tfName;
/*     */   private JMTextField tfAuthor;
/*     */   private JMTextField tfChap;
/*     */   private BasicButton btShowChap;
/*     */   private BasicButton btOk;
/*     */   private BasicButton btCancel;
/*     */   private CircleWait circleWait;
/*     */   private Properties properties;
/*     */   private AddListener addListener;
/*     */   private PluginEntity plugin;
/*     */   private String url;
/*     */   private boolean doImp;
/*     */   
/*     */   public AddDialog(String url) {
/*  50 */     this.url = url;
/*  51 */     onCreate();
/*  52 */     runOnUiThread(new Runnable()
/*     */         {
/*     */           public void run() {
/*  55 */             AddDialog.this.doLoad();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public AddDialog(Properties properties) {
/*  62 */     this.properties = properties;
/*  63 */     onCreate();
/*  64 */     this.tfName.setEnabled(true);
/*  65 */     this.tfName.setText(properties.getName());
/*  66 */     this.tfAuthor.setEnabled(true);
/*  67 */     this.tfAuthor.setText(properties.getAuthor());
/*  68 */     this.btShowChap.setVisible(true);
/*  69 */     this.tfChap.setEnabled(true);
/*  70 */     this.tfChap.setText("1-" + Integer.toString(properties.getSize()));
/*  71 */     this.btShowChap.setVisible(true);
/*  72 */     this.btOk.setEnabled(true);
/*  73 */     this.doImp = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onCreate() {
/*  78 */     super.onCreate();
/*  79 */     this.lbName = new JLabel();
/*  80 */     this.lbAuthor = new JLabel();
/*  81 */     this.lbChap = new JLabel();
/*  82 */     this.tfName = new JMTextField();
/*  83 */     this.tfAuthor = new JMTextField();
/*  84 */     this.tfChap = new JMTextField();
/*  85 */     this.circleWait = new CircleWait(getPreferredSize());
/*  86 */     JLayer<JPanel> layer = this.circleWait.getJlayer();
/*  87 */     this.container.add(layer);
/*  88 */     layer.setBounds(0, 0, 300, 260);
/*     */     
/*  90 */     this.btOk = new BasicButton();
/*  91 */     this.btOk.setText("OK");
/*  92 */     this.btOk.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  95 */             AddDialog.this.actionAdd();
/*     */           }
/*     */         });
/*  98 */     this.container.add((Component)this.btOk);
/*  99 */     this.btOk.setEnabled(false);
/* 100 */     this.btOk.setBounds(10, 210, 110, 35);
/*     */ 
/*     */     
/* 103 */     this.btCancel = new BasicButton();
/* 104 */     this.btCancel.setText("HỦY");
/* 105 */     this.btCancel.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 108 */             AddDialog.this.close();
/*     */           }
/*     */         });
/* 111 */     this.container.add((Component)this.btCancel);
/* 112 */     this.btCancel.setBounds(180, 210, 110, 35);
/*     */     
/* 114 */     this.lbName.setText("Tên truyện");
/* 115 */     this.lbName.setFont(FontUtils.TEXT_NORMAL);
/* 116 */     this.container.add(this.lbName);
/* 117 */     this.lbName.setBounds(10, 10, 280, 25);
/* 118 */     this.tfName.setEnabled(false);
/* 119 */     this.tfName.setBounds(10, 35, 280, 37);
/* 120 */     this.container.add((Component)this.tfName);
/*     */     
/* 122 */     this.lbAuthor.setText("Tác giả");
/* 123 */     this.lbAuthor.setFont(FontUtils.TEXT_NORMAL);
/* 124 */     this.container.add(this.lbAuthor);
/* 125 */     this.lbAuthor.setBounds(10, 75, 280, 25);
/* 126 */     this.tfAuthor.setEnabled(false);
/* 127 */     this.tfAuthor.setBounds(10, 100, 280, 37);
/* 128 */     this.container.add((Component)this.tfAuthor);
/*     */     
/* 130 */     this.lbChap.setText("Danh sách chương");
/* 131 */     this.lbChap.setFont(FontUtils.TEXT_NORMAL);
/* 132 */     this.container.add(this.lbChap);
/* 133 */     this.lbChap.setBounds(10, 140, 280, 25);
/* 134 */     this.tfChap.setEnabled(false);
/* 135 */     this.tfChap.setBounds(10, 165, 200, 37);
/* 136 */     this.container.add((Component)this.tfChap);
/* 137 */     this.btShowChap = new BasicButton();
/* 138 */     this.btShowChap.setText("Xem DS");
/* 139 */     this.btShowChap.setBounds(220, 165, 70, 37);
/* 140 */     this.btShowChap.setVisible(false);
/* 141 */     this.btShowChap.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 144 */             AddDialog.this.actionShow();
/*     */           }
/*     */         });
/* 147 */     this.container.add((Component)this.btShowChap);
/* 148 */     setSize(300, 260);
/*     */   }
/*     */   
/*     */   public Properties getProperties() {
/* 152 */     return this.properties;
/*     */   }
/*     */   
/*     */   private void doLoad() {
/* 156 */     PluginManager pluginManager = PluginManager.getManager();
/*     */     
/* 158 */     this.plugin = pluginManager.get(this.url);
/*     */ 
/*     */     
/* 161 */     this.circleWait.startWait();
/* 162 */     this.properties = new Properties();
/* 163 */     this.properties.setUrl(this.url);
/* 164 */     InfoExecute getInfo = new InfoExecute();
/* 165 */     final ListExecute getList = new ListExecute();
/*     */     
/* 167 */     getList.plugin(this.plugin)
/* 168 */       .listener(new ChangeListener()
/*     */         {
/*     */           public void doChanger() {
/* 171 */             AddDialog.this.tfChap.setEnabled(true);
/* 172 */             AddDialog.this.tfChap.setText("1-" + Integer.toString(AddDialog.this.properties.getSize()));
/* 173 */             AddDialog.this.btShowChap.setVisible(true);
/* 174 */             AddDialog.this.btOk.setEnabled(true);
/* 175 */             AddDialog.this.circleWait.stopWait();
/*     */           }
/* 178 */         }).applyTo(this.properties);
/* 179 */     getInfo.plugin(this.plugin)
/* 180 */       .listener(new ChangeListener()
/*     */         {
/*     */           public void doChanger() {
/* 183 */             AddDialog.this.tfName.setEnabled(true);
/* 184 */             AddDialog.this.tfName.setText(AddDialog.this.properties.getName());
/* 185 */             AddDialog.this.tfAuthor.setEnabled(true);
/* 186 */             AddDialog.this.tfAuthor.setText(AddDialog.this.properties.getAuthor());
/* 187 */             getList.execute();
/*     */           }
/* 190 */         }).applyTo(this.properties)
/* 191 */       .execute();
/*     */   }
/*     */ 
/*     */   
/*     */   private void actionAdd() {
/* 196 */     if (this.doImp) {
/* 197 */       this.addListener.addDownload(this.properties, this.doImp);
/*     */     } else {
/* 199 */       this.properties.setName(SyntaxUtils.covertString(this.tfName.getText().trim()));
/* 200 */       this.properties.setAuthor(SyntaxUtils.covertString(this.tfAuthor.getText().trim()));
/* 201 */       if (this.properties.getName().length() == 0) {
/* 202 */         this.tfName.addError("Tên truyện không được để trống!");
/*     */         return;
/*     */       } 
/* 205 */       if (this.properties.getAuthor().length() == 0) {
/* 206 */         this.tfAuthor.addError("Tên tác giả không được để trống");
/*     */         return;
/*     */       } 
/* 209 */       String savePath = SyntaxUtils.xoaDau(this.tfName.getText());
/*     */ 
/*     */       
/* 212 */       savePath = savePath.replaceAll("[^a-zA-Z0-9_]", "_").replace("\"", "").trim();
/* 213 */       savePath = FileUtils.validate(SettingUtils.WORKPATH + "/output/" + savePath);
/* 214 */       this.properties.setSavePath(savePath);
/* 215 */       this.properties.setUrl(this.url);
/* 216 */       FileUtils.mkdir(savePath);
/* 217 */       if (parseListChap())
/* 218 */       { this.addListener.addDownload(this.properties, false); }
/*     */       else { return; }
/*     */     
/* 221 */     }  close();
/*     */   }
/*     */   
/*     */   private void actionShow() {
/*     */     final ListDialog list;
/* 226 */     if (this.properties.isForum()) {
/* 227 */       list = new ListDialog(this.properties.getPageList(), this.tfChap.getText(), true);
/*     */     } else {
/* 229 */       list = new ListDialog(this.properties.getChapList(), this.tfChap.getText());
/* 230 */     }  list.setBlurListener((BlurListener)this);
/* 231 */     list.setChangeListener(new ChangeListener()
/*     */         {
/*     */           public void doChanger() {
/* 234 */             AddDialog.this.tfChap.setText(list.getParseList());
/*     */           }
/*     */         });
/* 237 */     list.open();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean parseListChap() {
/* 242 */     int size = 0;
/* 243 */     List<Pager> pageList = this.properties.getPageList();
/* 244 */     List<Chapter> chapList = this.properties.getChapList();
/*     */     
/* 246 */     List<Pager> newPageList = new ArrayList<>();
/* 247 */     List<Chapter> newChapList = new ArrayList<>();
/*     */     try {
/* 249 */       String[] list = this.tfChap.getText().replaceAll("\\s+", "").split(",");
/* 250 */       for (int i = 0; i < list.length; i++) {
/* 251 */         String[] c = list[i].split("-");
/* 252 */         int c0 = Integer.parseInt(c[0]);
/* 253 */         if (c.length == 1) {
/* 254 */           if (this.properties.isForum())
/* 255 */           { newPageList.add(pageList.get(c0 - 1)); }
/* 256 */           else { newChapList.add(chapList.get(c0 - 1)); }
/* 257 */            size++;
/*     */         } else {
/*     */           
/* 260 */           for (int j = c0; j <= Integer.parseInt(c[1]); j++)
/* 261 */           { if (this.properties.isForum())
/* 262 */             { newPageList.add(pageList.get(j - 1)); }
/* 263 */             else { newChapList.add(chapList.get(j - 1)); }
/* 264 */              size++; } 
/*     */         } 
/*     */       } 
/* 267 */     } catch (Exception e) {
/* 268 */       this.tfChap.addError("Lỗi tạo danh sách chương");
/* 269 */       return false;
/*     */     } 
/* 271 */     this.properties.setPageList(newPageList);
/* 272 */     this.properties.setChapList(newChapList);
/* 273 */     this.properties.setSize(size);
/* 274 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAddListener(AddListener addListener) {
/* 279 */     this.addListener = addListener;
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\download\AddDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */