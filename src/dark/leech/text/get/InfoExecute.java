/*    */ package dark.leech.text.get;
/*    */ 
/*    */ import dark.leech.text.enities.BookEntity;
/*    */ import dark.leech.text.enities.PluginEntity;
/*    */ import dark.leech.text.listeners.ChangeListener;
/*    */ import dark.leech.text.lua.loader.DetailLoader;
/*    */ import dark.leech.text.models.Properties;
/*    */ import dark.leech.text.util.SyntaxUtils;
/*    */ import javax.swing.SwingWorker;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InfoExecute
/*    */   extends SwingWorker
/*    */ {
/*    */   private DetailLoader loader;
/*    */   private ChangeListener changeListener;
/*    */   private Properties properties;
/*    */   private boolean success;
/*    */   
/*    */   public InfoExecute plugin(PluginEntity pluginEntity) {
/* 22 */     this.loader = DetailLoader.with(pluginEntity);
/* 23 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public InfoExecute listener(ChangeListener changeListener) {
/* 28 */     this.changeListener = changeListener;
/* 29 */     return this;
/*    */   }
/*    */   
/*    */   public InfoExecute applyTo(Properties properties) {
/* 33 */     this.properties = properties;
/* 34 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Void doInBackground() {
/*    */     try {
/* 41 */       BookEntity book = this.loader.load(this.properties.getUrl());
/* 42 */       if (book != null) {
/* 43 */         this.properties.setAuthor(book.getAuthor());
/* 44 */         this.properties.setGioiThieu(book.getIntroduce());
/* 45 */         this.properties.setUrl(book.getUrl());
/* 46 */         this.properties.setCover(book.getCover());
/* 47 */         this.properties.setName(book.getName());
/*    */       } 
/* 49 */       this.success = true;
/* 50 */     } catch (Exception exception) {}
/*    */     
/* 52 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void done() {
/* 57 */     if (this.success && 
/* 58 */       this.properties.getGioiThieu() != null)
/* 59 */       this.properties.setGioiThieu("<p>" + SyntaxUtils.Optimize(this.properties.getGioiThieu()).replace("\n", "</p>\n<p>") + "</p>"); 
/* 60 */     this.changeListener.doChanger();
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\get\InfoExecute.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */