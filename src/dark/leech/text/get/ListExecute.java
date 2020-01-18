/*    */ package dark.leech.text.get;
/*    */ 
/*    */ import dark.leech.text.action.Log;
/*    */ import dark.leech.text.enities.ChapterEntity;
/*    */ import dark.leech.text.enities.PluginEntity;
/*    */ import dark.leech.text.listeners.ChangeListener;
/*    */ import dark.leech.text.lua.loader.TocLoader;
/*    */ import dark.leech.text.models.Chapter;
/*    */ import dark.leech.text.models.Pager;
/*    */ import dark.leech.text.models.Properties;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.swing.SwingWorker;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ListExecute
/*    */   extends SwingWorker
/*    */ {
/*    */   private TocLoader loader;
/*    */   private ChangeListener changeListener;
/*    */   private Properties properties;
/*    */   private boolean success;
/*    */   
/*    */   public ListExecute plugin(PluginEntity plugin) {
/* 26 */     this.loader = TocLoader.with(plugin);
/* 27 */     return this;
/*    */   }
/*    */   
/*    */   public ListExecute listener(ChangeListener changeListener) {
/* 31 */     this.changeListener = changeListener;
/* 32 */     return this;
/*    */   }
/*    */   
/*    */   public ListExecute applyTo(Properties properties) {
/* 36 */     this.properties = properties;
/* 37 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Void doInBackground() {
/*    */     try {
/* 44 */       List<ChapterEntity> chapterList = this.loader.load(this.properties.getUrl());
/* 45 */       List<Chapter> chapters = new ArrayList<>();
/*    */       
/* 47 */       if (chapterList != null) {
/* 48 */         for (ChapterEntity chap : chapterList) {
/* 49 */           chapters.add(new Chapter(chap.getUrl(), chap.getName()));
/*    */         }
/*    */       }
/* 52 */       this.properties.setChapList(chapters);
/* 53 */       this.properties.setSize(chapters.size());
/* 54 */       this.success = true;
/* 55 */     } catch (Exception e) {
/* 56 */       Log.add(e);
/*    */     } 
/* 58 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void done() {
/* 63 */     if (this.success)
/* 64 */       if (this.properties.isForum()) {
/* 65 */         List<Pager> pageList = this.properties.getPageList();
/* 66 */         for (int i = 0; i < pageList.size(); i++) {
/* 67 */           Pager pager = pageList.get(i);
/* 68 */           if (pager.getName() == null)
/* 69 */             pager.setName("Trang " + Integer.toString(i + 1)); 
/* 70 */           pager.setId(i);
/*    */         } 
/*    */       } else {
/* 73 */         List<Chapter> chapList = this.properties.getChapList();
/* 74 */         for (int i = 0; i < chapList.size(); i++) {
/* 75 */           ((Chapter)chapList.get(i)).setId(i);
/*    */         }
/*    */       }  
/* 78 */     this.changeListener.doChanger();
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\get\ListExecute.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */