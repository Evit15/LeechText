/*    */ package dark.leech.text.plugin;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import dark.leech.text.action.Log;
/*    */ import dark.leech.text.enities.PluginEntity;
/*    */ import dark.leech.text.ui.notification.Toast;
/*    */ import dark.leech.text.util.AppUtils;
/*    */ import dark.leech.text.util.FileUtils;
/*    */ import dark.leech.text.util.Http;
/*    */ import org.json.JSONArray;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PluginUpdate
/*    */ {
/*    */   private static final String URL = "https://dl.dropboxusercontent.com/s/nfsg7x1f1hvdzjb/plugins.json?dl=1";
/*    */   private static PluginUpdate pluginUpdate;
/*    */   
/*    */   public static PluginUpdate getUpdate() {
/* 25 */     if (pluginUpdate == null) pluginUpdate = new PluginUpdate(); 
/* 26 */     return pluginUpdate;
/*    */   }
/*    */ 
/*    */   
/*    */   public void checkUpdate() {
/*    */     try {
/* 32 */       String js = Http.request("https://dl.dropboxusercontent.com/s/nfsg7x1f1hvdzjb/plugins.json?dl=1").string();
/* 33 */       JSONArray objArr = new JSONArray(js);
/* 34 */       for (int i = 0; i < objArr.length(); i++) {
/* 35 */         JSONObject obj = objArr.getJSONObject(i);
/* 36 */         boolean have = false;
/* 37 */         for (PluginEntity pluginGetter : PluginManager.getManager().list()) {
/* 38 */           if (obj.getString("uuid").equals(pluginGetter.getUuid())) {
/* 39 */             have = true;
/* 40 */             if (obj.getDouble("version") > pluginGetter.getVersion()) {
/*    */ 
/*    */               
/* 43 */               String path = AppUtils.curDir + "/tools/plugins/" + pluginGetter.getUuid() + ".plugin";
/*    */               
/* 45 */               String json = Http.request(obj.getString("url")).string();
/*    */               
/* 47 */               PluginEntity entity = (PluginEntity)(new Gson()).fromJson(json, PluginEntity.class);
/* 48 */               entity.setChecked(true);
/* 49 */               FileUtils.string2file((new Gson()).toJson(entity), path);
/* 50 */               pluginGetter.apply(entity);
/* 51 */               Toast.Build()
/* 52 */                 .content("Đã update plugin " + pluginGetter.getName() + " v" + pluginGetter.getVersion())
/* 53 */                 .time(3000)
/* 54 */                 .open();
/*    */             } 
/*    */           } 
/*    */         } 
/* 58 */         if (!have) {
/*    */ 
/*    */           
/* 61 */           String path = AppUtils.curDir + "/tools/plugins/" + obj.getString("uuid") + ".plugin";
/*    */           
/* 63 */           String json = Http.request(obj.getString("url")).string();
/*    */           
/* 65 */           PluginEntity entity = (PluginEntity)(new Gson()).fromJson(json, PluginEntity.class);
/* 66 */           entity.setChecked(true);
/* 67 */           FileUtils.string2file((new Gson()).toJson(entity), path);
/* 68 */           PluginManager.getManager().add(path);
/* 69 */           Toast.Build()
/* 70 */             .content("Đã tải xuống plugin " + obj.getString("name"))
/* 71 */             .time(3000)
/* 72 */             .open();
/*    */         } 
/*    */       } 
/* 75 */     } catch (Exception e) {
/* 76 */       Log.add(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\plugin\PluginUpdate.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */