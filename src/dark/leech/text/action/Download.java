/*     */ package dark.leech.text.action;
/*     */ 
/*     */ import dark.leech.text.enities.PluginEntity;
/*     */ import dark.leech.text.get.ChapExecute;
/*     */ import dark.leech.text.listeners.ChangeListener;
/*     */ import dark.leech.text.listeners.DownloadListener;
/*     */ import dark.leech.text.models.Chapter;
/*     */ import dark.leech.text.models.Pager;
/*     */ import dark.leech.text.models.Properties;
/*     */ import dark.leech.text.plugin.PluginManager;
/*     */ import dark.leech.text.util.SettingUtils;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Download
/*     */   implements ChangeListener
/*     */ {
/*     */   public static final int DOWNLOADING = 0;
/*     */   public static final int PAUSE = 1;
/*     */   public static final int COMPLETED = 2;
/*     */   public static final int CHECKING = 3;
/*     */   public static final int CANCEL = 4;
/*     */   public static final int ERROR = 5;
/*     */   private DownloadListener downloadListener;
/*     */   
/*     */   public Download(Properties properties) {
/*  30 */     PluginManager pluginManager = PluginManager.getManager();
/*  31 */     this.pluginGetter = pluginManager.get(properties.getUrl());
/*  32 */     this.properties = properties;
/*  33 */     this.chapList = properties.getChapList();
/*  34 */     this.pageList = properties.getPageList();
/*  35 */     this.size = properties.getSize();
/*     */   }
/*     */   private List<Chapter> chapList; private List<Pager> pageList; private PluginEntity pluginGetter; private Properties properties; private int downloaded; private int status; private int size; private int next;
/*     */   public void addDownloadListener(DownloadListener downloadListener) {
/*  39 */     this.downloadListener = downloadListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void pause() {
/*  44 */     this.status = 1;
/*  45 */     this.downloadListener.updateDownload(this.downloaded, this.status);
/*     */   }
/*     */   
/*     */   public void cancel() {
/*  49 */     this.status = 4;
/*  50 */     this.downloadListener.updateDownload(this.downloaded, this.status);
/*     */   }
/*     */   
/*     */   public void resume() {
/*  54 */     this.status = 0;
/*  55 */     startDownload();
/*  56 */     this.downloadListener.updateDownload(this.downloaded, this.status);
/*     */   }
/*     */ 
/*     */   
/*     */   public void startDownload() {
/*  61 */     this.status = 0;
/*  62 */     this.next = this.next + SettingUtils.MAX_CONN - 1;
/*  63 */     update();
/*  64 */     for (int i = 0; i < SettingUtils.MAX_CONN; i++) {
/*  65 */       if (this.properties.isForum()) {
/*  66 */         forum(this.downloaded + i);
/*     */       } else {
/*  68 */         web(this.downloaded + i);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void forum(int index) {
/*  74 */     if (index >= this.size) {
/*  75 */       update();
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void web(int index) {
/*  88 */     if (index >= this.size) {
/*  89 */       update();
/*     */       return;
/*     */     } 
/*  92 */     (new ChapExecute())
/*  93 */       .plugin(this.pluginGetter)
/*  94 */       .listener(this)
/*  95 */       .charset(this.properties.getCharset())
/*  96 */       .path(this.properties.getSavePath())
/*  97 */       .applyTo(this.chapList.get(index))
/*  98 */       .execute();
/*     */   }
/*     */   
/*     */   private void update() {
/* 102 */     if (this.downloaded >= this.size) {
/* 103 */       this.downloaded = this.size;
/* 104 */       this.status = 3;
/* 105 */       check();
/*     */     } 
/* 107 */     this.downloadListener.updateDownload(this.downloaded, this.status);
/*     */   }
/*     */ 
/*     */   
/*     */   private void check() {
/* 112 */     this.downloadListener.updateDownload(this.downloaded, this.status);
/* 113 */     for (int i = 0; i < this.size; i++) {
/* 114 */       if (this.properties.isForum())
/* 115 */         if (!((Pager)this.pageList.get(i)).isCompleted()) {
/* 116 */           forum(i);
/* 117 */         } else if (!((Chapter)this.chapList.get(i)).isCompleted()) {
/* 118 */           web(i);
/*     */         }  
/*     */     } 
/* 121 */     this.status = 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void doChanger() {
/* 126 */     if (this.status != 3) {
/* 127 */       this.downloaded++;
/* 128 */       this.next++;
/*     */     } 
/* 130 */     if (this.status == 0) {
/* 131 */       update();
/* 132 */       if (this.next < this.size)
/* 133 */         if (this.properties.isForum()) {
/* 134 */           forum(this.next);
/*     */         } else {
/* 136 */           web(this.next);
/*     */         }  
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\action\Download.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */