/*    */ package dark.leech.text.get;
/*    */ 
/*    */ import dark.leech.text.enities.PluginEntity;
/*    */ import dark.leech.text.listeners.ChangeListener;
/*    */ import dark.leech.text.lua.loader.TextLoader;
/*    */ import dark.leech.text.models.Chapter;
/*    */ import dark.leech.text.util.AppUtils;
/*    */ import dark.leech.text.util.FileUtils;
/*    */ import dark.leech.text.util.SettingUtils;
/*    */ import dark.leech.text.util.SyntaxUtils;
/*    */ import javax.swing.SwingWorker;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChapExecute
/*    */   extends SwingWorker<String, Void>
/*    */ {
/*    */   private TextLoader chapGetter;
/*    */   private Chapter chapter;
/*    */   private ChangeListener changeListener;
/*    */   private String text;
/*    */   private String savepath;
/* 24 */   private String chset = "utf-8";
/*    */ 
/*    */   
/*    */   protected String doInBackground() throws Exception {
/* 28 */     AppUtils.pause(SettingUtils.DELAY);
/* 29 */     return this.chapGetter.load(this.chapter.getUrl());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void done() {
/*    */     try {
/* 35 */       this.text = get();
/* 36 */       this.chapter.setError(false);
/* 37 */       this.chapter.setEmpty(false);
/* 38 */       if (this.text == null || this.text.length() == 0) {
/* 39 */         this.chapter.setError(true);
/* 40 */       } else if (this.text.length() < 20000) {
/* 41 */         if ((this.text.split("<img ")).length > 1)
/* 42 */         { this.chapter.setImageChapter(true); }
/* 43 */         else { this.chapter.setEmpty(true); } 
/* 44 */       }  this.chapter.setCompleted(true);
/* 45 */       if (!this.chapter.isEmpty() || !this.chapter.isError())
/* 46 */         write(); 
/* 47 */     } catch (Exception e) {
/* 48 */       this.chapter.setError(true);
/*    */     } 
/* 50 */     this.changeListener.doChanger();
/*    */   }
/*    */ 
/*    */   
/*    */   public ChapExecute plugin(PluginEntity pluginEntity) {
/* 55 */     this.chapGetter = TextLoader.with(pluginEntity);
/* 56 */     return this;
/*    */   }
/*    */   
/*    */   public ChapExecute listener(ChangeListener changeListener) {
/* 60 */     this.changeListener = changeListener;
/* 61 */     return this;
/*    */   }
/*    */   
/*    */   public ChapExecute path(String savepath) {
/* 65 */     this.savepath = savepath;
/* 66 */     return this;
/*    */   }
/*    */   
/*    */   public ChapExecute charset(String chset) {
/* 70 */     this.chset = chset;
/* 71 */     return this;
/*    */   }
/*    */   
/*    */   public ChapExecute applyTo(Chapter chapter) {
/* 75 */     this.chapter = chapter;
/* 76 */     return this;
/*    */   }
/*    */   
/*    */   private void write() {
/* 80 */     FileUtils.string2file(Optimize(this.text), this.savepath + "/raw/" + this.chapter.getId() + ".txt", this.chset);
/*    */   }
/*    */ 
/*    */   
/*    */   private String Optimize(String src) {
/* 85 */     if (SyntaxUtils.REPLACE_FROM != null)
/* 86 */       for (int i = 0; i < SyntaxUtils.REPLACE_FROM.length; i++)
/* 87 */         src = src.replaceAll(SyntaxUtils.REPLACE_FROM[i], SyntaxUtils.REPLACE_TO[i]);  
/* 88 */     return src;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\get\ChapExecute.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */