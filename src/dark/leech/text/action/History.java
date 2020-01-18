/*    */ package dark.leech.text.action;
/*    */ 
/*    */ import dark.leech.text.models.Chapter;
/*    */ import dark.leech.text.models.Properties;
/*    */ import dark.leech.text.util.FileUtils;
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.json.JSONArray;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class History
/*    */ {
/*    */   public static History getHistory() {
/* 20 */     return new History();
/*    */   }
/*    */   
/*    */   public Properties load(String path) {
/* 24 */     Properties properties = new Properties();
/*    */     try {
/* 26 */       if (!(new File(path)).exists()) return null; 
/* 27 */       JSONObject obj = new JSONObject(FileUtils.file2string(path));
/* 28 */       JSONObject metadata = obj.getJSONObject("metadata");
/* 29 */       properties.setName(metadata.getString("name"));
/* 30 */       properties.setAuthor(metadata.getString("author"));
/* 31 */       properties.setUrl(metadata.getString("url"));
/* 32 */       properties.setCover(metadata.getString("cover"), "");
/* 33 */       properties.setSize(metadata.getInt("size"));
/* 34 */       properties.setSavePath(metadata.getString("path"));
/* 35 */       properties.setGioiThieu(metadata.getString("gioithieu"));
/* 36 */       JSONArray array = obj.getJSONArray("list");
/* 37 */       List<Chapter> list = new ArrayList<>();
/* 38 */       for (int i = 0; i < array.length(); i++)
/* 39 */         list.add(getChapter(array.getJSONObject(i))); 
/* 40 */       properties.setChapList(list);
/* 41 */     } catch (Exception e) {
/* 42 */       Log.add(e);
/*    */     } 
/* 44 */     return properties;
/*    */   }
/*    */   
/*    */   public void save(Properties properties) {
/* 48 */     JSONObject his = new JSONObject();
/* 49 */     JSONObject metadata = new JSONObject();
/* 50 */     metadata.put("url", properties.getUrl());
/* 51 */     metadata.put("name", properties.getName());
/* 52 */     metadata.put("author", properties.getAuthor());
/* 53 */     metadata.put("cover", properties.getCover());
/* 54 */     metadata.put("size", properties.getSize());
/* 55 */     metadata.put("path", properties.getSavePath());
/* 56 */     metadata.put("gioithieu", properties.getGioiThieu());
/* 57 */     his.put("metadata", metadata);
/* 58 */     JSONArray list = new JSONArray();
/*    */ 
/*    */     
/* 61 */     for (Chapter c : properties.getChapList())
/* 62 */       list.put(getObjectList(c)); 
/* 63 */     his.put("list", list);
/*    */     
/* 65 */     FileUtils.string2file(his.toString(), properties.getSavePath() + "/properties.json");
/*    */   }
/*    */   
/*    */   private JSONObject getObjectList(Chapter chapter) {
/* 69 */     JSONObject obj = new JSONObject();
/* 70 */     obj.put("id", chapter.getId());
/* 71 */     obj.put("error", chapter.isError());
/* 72 */     obj.put("part", chapter.getPartName());
/* 73 */     obj.put("chap", chapter.getChapName());
/* 74 */     obj.put("url", chapter.getUrl());
/* 74 */     obj.put("img", chapter.isImageChapter());
/* 75 */     return obj;
/*    */   }
/*    */   
/*    */   private Chapter getChapter(JSONObject jsonObject) {
/* 79 */     Chapter chapter = new Chapter();
/* 80 */     chapter.setId(jsonObject.getString("id"));
/* 81 */     chapter.setError(jsonObject.getBoolean("error"));
/* 82 */     chapter.setPartName(jsonObject.getString("part"));
/* 83 */     chapter.setChapName(jsonObject.getString("chap"));
/* 84 */     chapter.setUrl(jsonObject.getString("url"));
/* 84 */     chapter.setImageChapter(jsonObject.getBoolean("img"));
/* 85 */     chapter.setCompleted(true);
/* 86 */     return chapter;
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\action\History.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */