/*    */ package dark.leech.text.plugin;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import dark.leech.text.enities.PluginEntity;
/*    */ import dark.leech.text.util.AppUtils;
/*    */ import dark.leech.text.util.FileUtils;
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PluginManager
/*    */ {
/*    */   private static PluginManager manager;
/*    */   private static ArrayList<PluginEntity> pluginList;
/*    */   
/*    */   private PluginManager() {
/* 20 */     (new Thread(new Runnable()
/*    */         {
/*    */           public void run() {
/* 23 */             PluginManager.pluginList = new ArrayList();
/* 24 */             File[] files = (new File(FileUtils.validate(AppUtils.curDir + "/tools/plugins"))).listFiles();
/* 25 */             if (files == null)
/* 26 */               return;  for (File f : files) {
/* 27 */               if (f.getName().endsWith(".plugin")) {
/*    */                 try {
/* 29 */                   PluginManager.pluginList.add(PluginManager.this.createPlugin(f.getAbsolutePath()));
/* 30 */                 } catch (Exception exception) {}
/*    */               }
/*    */             } 
/* 33 */             PluginUpdate.getUpdate().checkUpdate();
/*    */           }
/* 35 */         })).start();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static PluginManager getManager() {
/* 41 */     if (manager == null) manager = new PluginManager(); 
/* 42 */     return manager;
/*    */   }
/*    */   
/*    */   public void add(String path) {
/* 46 */     pluginList.add(createPlugin(path));
/*    */   }
/*    */ 
/*    */   
/*    */   private PluginEntity createPlugin(String path) {
/* 51 */     PluginEntity entity = (PluginEntity)(new Gson()).fromJson(FileUtils.file2string(path), PluginEntity.class);
/* 52 */     return entity;
/*    */   }
/*    */   
/*    */   public PluginEntity get(String url) {
/* 56 */     for (PluginEntity plugin : pluginList) {
/* 57 */       if (url.matches("(https?://)?" + plugin.getRegex())) return plugin; 
/* 58 */     }  return null;
/*    */   }
/*    */   
/*    */   public ArrayList<PluginEntity> list() {
/* 62 */     return pluginList;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\plugin\PluginManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */