/*     */ package dark.leech.text.ui.download;
/*     */ 
/*     */ import dark.leech.text.action.Download;
/*     */ import dark.leech.text.action.History;
/*     */ import dark.leech.text.image.ImageLabel;
/*     */ import dark.leech.text.listeners.DownloadListener;
/*     */ import dark.leech.text.listeners.RemoveListener;
/*     */ import dark.leech.text.models.Chapter;
/*     */ import dark.leech.text.models.Pager;
/*     */ import dark.leech.text.models.Properties;
/*     */ import dark.leech.text.ui.button.CircleButton;
/*     */ import dark.leech.text.ui.main.InfoUI;
/*     */ import dark.leech.text.ui.material.DropShadowBorder;
/*     */ import dark.leech.text.ui.material.JMPanel;
/*     */ import dark.leech.text.ui.material.JMProgressBar;
/*     */ import dark.leech.text.ui.notification.Notification;
/*     */ import dark.leech.text.util.ColorUtils;
/*     */ import dark.leech.text.util.FileUtils;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import dark.leech.text.util.SettingUtils;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ 
/*     */ public class DownloadLabel
/*     */   extends JMPanel
/*     */   implements DownloadListener
/*     */ {
/*     */   private JMProgressBar load;
/*     */   private JLabel lbName;
/*     */   private JLabel lbStatus;
/*     */   private JLabel lbPercent;
/*     */   private JLabel lbProgress;
/*     */   private ImageLabel pnCover;
/*     */   private CircleButton btDelete;
/*     */   
/*     */   public DownloadLabel() {
/*  43 */     onCreate();
/*  44 */     this.pause = false;
/*     */   }
/*     */   private CircleButton btPR; private CircleButton btInfo; private Properties properties; private Download download; private RemoveListener removeListener; private boolean pause; private int status; private int max;
/*     */   
/*     */   public void importDownload(Properties properties) {
/*  49 */     this.properties = properties;
/*  50 */     this.max = properties.getSize();
/*  51 */     this.btPR.setVisible(false);
/*  52 */     this.btInfo.setVisible(true);
/*     */     
/*  54 */     if (FontUtils.TEXT_BOLD.canDisplayUpTo(properties.getName()) == -1)
/*  55 */       this.lbName.setFont(FontUtils.TEXT_BOLD); 
/*  56 */     this.lbName.setText(properties.getName());
/*  57 */     this.lbStatus.setText("Hoàn tất");
/*  58 */     setValue(this.max);
/*  59 */     this.pnCover.path(properties.getSavePath() + "/data/cover.jpg").load();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addDownload(Properties properties) {
/*  64 */     this.properties = properties;
/*  65 */     this.max = properties.getSize();
/*  66 */     doCreateFolder();
/*  67 */     this.download = new Download(properties);
/*  68 */     this.download.addDownloadListener(this);
/*  69 */     this.download.startDownload();
/*  70 */     if (FontUtils.TEXT_BOLD.canDisplayUpTo(properties.getName()) == -1)
/*  71 */       this.lbName.setFont(FontUtils.TEXT_BOLD); 
/*  72 */     this.lbName.setText(properties.getName());
/*     */     
/*  74 */     this.pnCover.url(properties.getCover())
/*  75 */       .path(properties.getSavePath() + "/data/cover.jpg")
/*  76 */       .load();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRemoveListener(RemoveListener removeListener) {
/*  81 */     this.removeListener = removeListener;
/*     */   }
/*     */ 
/*     */   
/*     */   private void pause() {
/*  86 */     this.download.pause();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void resume() {
/*  92 */     this.download.resume();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void delete() {
/*  98 */     if (this.download != null)
/*  99 */       this.download.cancel(); 
/* 100 */     this.removeListener.removeComponent((Component)this);
/*     */   }
/*     */ 
/*     */   
/*     */   private void doAfterFinish() {
/* 105 */     this.btPR.setVisible(false);
/* 106 */     this.btInfo.setVisible(true);
/* 107 */     if (this.properties.isForum()) {
/* 108 */       ArrayList<Chapter> chapList = new ArrayList<>();
/* 109 */       for (Pager pager : this.properties.getPageList()) {
/* 110 */         for (Chapter ch : pager.getChapter())
/* 111 */           chapList.add(ch); 
/*     */       } 
/* 113 */       this.properties.setChapList(chapList);
/* 114 */       this.properties.setSize(chapList.size());
/*     */     } 
/* 116 */     History.getHistory().save(this.properties);
/* 117 */     Notification.build()
/* 118 */       .title(this.properties.getName())
/* 119 */       .content("Đã gettext xong!")
/* 120 */       .path(this.properties.getSavePath() + "/data/cover.jpg")
/* 121 */       .delay(5000)
/* 122 */       .open();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doAfterError() {
/* 129 */     pause();
/* 130 */     this.btInfo.setVisible(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public Properties getProperties() {
/* 135 */     return this.properties;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setValue(int value) {
/* 140 */     this.load.setPercent(value * 100 / this.max);
/* 141 */     this.lbProgress.setText(Integer.toString(value) + "/" + Integer.toString(this.max));
/* 142 */     this.lbPercent.setText(Integer.toString(this.load.getPercent()) + "%");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStatus(int status) {
/* 148 */     switch (status) {
/*     */       case 0:
/* 150 */         this.btPR.setText("");
/* 151 */         return "Đang tải...";
/*     */       case 1:
/* 153 */         this.btPR.setText("");
/* 154 */         return "Tạm dừng";
/*     */       case 5:
/* 156 */         doAfterError();
/* 157 */         return "Lỗi";
/*     */       case 2:
/* 159 */         doAfterFinish();
/* 160 */         return "Hoàn tất";
/*     */       case 3:
/* 162 */         return "Kiểm tra";
/*     */     } 
/* 164 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   private void onCreate() {
/* 169 */     this.pnCover = new ImageLabel();
/* 170 */     this.load = new JMProgressBar();
/* 171 */     this.lbName = new JLabel();
/* 172 */     this.lbStatus = new JLabel();
/* 173 */     this.lbPercent = new JLabel();
/* 174 */     this.lbProgress = new JLabel();
/*     */ 
/*     */     
/* 177 */     add((Component)this.pnCover);
/* 178 */     this.pnCover.setBounds(5, 5, 55, 80);
/*     */     
/* 180 */     this.load.setOpaque(true);
/* 181 */     this.load.setLayout(null);
/* 182 */     add((Component)this.load);
/* 183 */     this.load.setBounds(65, 70, 305, 4);
/*     */     
/* 185 */     this.lbName.setForeground(ColorUtils.THEME_COLOR);
/* 186 */     add(this.lbName);
/* 187 */     this.lbName.setBounds(65, 5, 230, 30);
/*     */ 
/*     */     
/* 190 */     this.lbStatus.setText("Đang tải...");
/* 191 */     this.lbStatus.setFont(FontUtils.TEXT_NORMAL);
/* 192 */     this.lbStatus.setForeground(ColorUtils.THEME_COLOR);
/* 193 */     this.lbStatus.setHorizontalAlignment(2);
/* 194 */     add(this.lbStatus);
/* 195 */     this.lbStatus.setBounds(65, 40, 95, (this.lbStatus.getPreferredSize()).height);
/*     */ 
/*     */     
/* 198 */     this.lbPercent.setFont(FontUtils.TEXT_NORMAL);
/* 199 */     this.lbPercent.setHorizontalAlignment(4);
/* 200 */     this.lbPercent.setForeground(ColorUtils.THEME_COLOR);
/* 201 */     add(this.lbPercent);
/* 202 */     this.lbPercent.setBounds(330, 40, 40, 30);
/*     */ 
/*     */     
/* 205 */     this.lbProgress.setHorizontalAlignment(4);
/* 206 */     this.lbProgress.setFont(FontUtils.TEXT_NORMAL);
/* 207 */     this.lbProgress.setForeground(ColorUtils.THEME_COLOR);
/* 208 */     add(this.lbProgress);
/* 209 */     this.lbProgress.setBounds(200, 40, 90, 30);
/*     */ 
/*     */     
/* 212 */     this.btPR = new CircleButton("", 20.0F);
/* 213 */     this.btPR.setForeground(ColorUtils.THEME_COLOR);
/* 214 */     this.btPR.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 217 */             DownloadLabel.this.pause = !DownloadLabel.this.pause;
/* 218 */             if (DownloadLabel.this.pause) {
/* 219 */               DownloadLabel.this.pause();
/*     */             } else {
/* 221 */               DownloadLabel.this.resume();
/*     */             }  }
/*     */         });
/* 224 */     add((Component)this.btPR);
/* 225 */     this.btPR.setBounds(310, 5, 30, 30);
/*     */ 
/*     */     
/* 228 */     this.btInfo = new CircleButton("", 20.0F);
/* 229 */     this.btInfo.setForeground(ColorUtils.THEME_COLOR);
/* 230 */     this.btInfo.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 233 */             (new InfoUI(DownloadLabel.this.properties)).open();
/*     */           }
/*     */         });
/* 236 */     add((Component)this.btInfo);
/* 237 */     this.btInfo.setBounds(310, 5, 30, 30);
/* 238 */     this.btInfo.setVisible(false);
/*     */     
/* 240 */     this.btDelete = new CircleButton("", 20.0F);
/* 241 */     this.btDelete.setForeground(ColorUtils.THEME_COLOR);
/* 242 */     this.btDelete.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 245 */             DownloadLabel.this.delete();
/*     */           }
/*     */         });
/* 248 */     add((Component)this.btDelete);
/* 249 */     this.btDelete.setBounds(345, 5, 30, 30);
/* 250 */     setBorder((Border)new DropShadowBorder(SettingUtils.THEME_COLOR, 5, 3));
/* 251 */     setPreferredSize(new Dimension(375, 90));
/*     */   }
/*     */ 
/*     */   
/*     */   private void doCreateFolder() {
/* 256 */     FileUtils.mkdir(this.properties.getSavePath() + "/raw");
/* 257 */     FileUtils.mkdir(this.properties.getSavePath() + "/data");
/* 258 */     FileUtils.mkdir(this.properties.getSavePath() + "/data/Images");
/* 259 */     FileUtils.mkdir(this.properties.getSavePath() + "/data/Text");
/* 260 */     FileUtils.mkdir(this.properties.getSavePath() + "/out");
/*     */   }
/*     */   
/*     */   protected void runOnUiThread(Runnable runnable) {
/* 264 */     (new Thread(runnable)).start();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateDownload(int downloaded, int status) {
/* 269 */     setValue(downloaded);
/* 270 */     if (status != this.status) {
/* 271 */       this.status = status;
/* 272 */       this.lbStatus.setText(getStatus(status));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\download\DownloadLabel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */