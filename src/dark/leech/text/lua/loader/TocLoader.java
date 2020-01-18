/*     */ package dark.leech.text.lua.loader;
/*     */ 
/*     */ import dark.leech.text.enities.ChapterEntity;
/*     */ import dark.leech.text.enities.PluginEntity;
/*     */ import dark.leech.text.lua.api.Lua;
/*     */ import dark.leech.text.lua.api.LuaScriptEngine;
/*     */ import dark.leech.text.util.TextUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.luaj.vm2.Globals;
/*     */ import org.luaj.vm2.LuaError;
/*     */ import org.luaj.vm2.LuaTable;
/*     */ import org.luaj.vm2.LuaValue;
/*     */ 
/*     */ public class TocLoader
/*     */ {
/*     */   private PluginEntity plugin;
/*     */   private Listener callback;
/*     */   
/*     */   public static TocLoader with(PluginEntity plugin) {
/*  21 */     return new TocLoader(plugin);
/*     */   }
/*     */   
/*     */   private TocLoader(PluginEntity plugin) {
/*  25 */     this.plugin = plugin;
/*     */   }
/*     */   
/*     */   public List<ChapterEntity> load(String url) {
/*  29 */     List<ChapterEntity> chapterList = new ArrayList<>();
/*  30 */     List<String> pageList = loadPage(url);
/*  31 */     int index = 0;
/*  32 */     if (pageList == null) return new ArrayList<>(); 
/*  33 */     int currentPage = 0;
/*  34 */     for (String pageUrl : pageList) {
/*  35 */       currentPage++;
/*  36 */       List<ChapterEntity> chapterLoader = loadChapterList(pageUrl);
/*  37 */       if (chapterLoader == null || chapterLoader.size() == 0) return new ArrayList<>(); 
/*  38 */       List<ChapterEntity> pageChapterList = new ArrayList<>();
/*     */       
/*  40 */       for (ChapterEntity chapter : chapterLoader) {
/*  41 */         ChapterEntity entity = new ChapterEntity();
/*  42 */         entity.setName(chapter.getName());
/*  43 */         entity.setUrl(chapter.getUrl());
/*  44 */         entity.setId(index);
/*  45 */         pageChapterList.add(entity);
/*  46 */         index++;
/*     */       } 
/*     */       
/*  49 */       if (this.callback != null)
/*  50 */         this.callback.onUpdate(pageChapterList, currentPage / pageList.size()); 
/*  51 */       chapterList.addAll(pageChapterList);
/*     */     } 
/*  53 */     return chapterList;
/*     */   }
/*     */   
/*     */   private List<String> loadPage(String url) {
/*  57 */     if (!TextUtils.isEmpty(this.plugin.getPageGetter())) {
/*  58 */       LuaValue chuck; Globals globals = LuaScriptEngine.getInstance().getGlobals();
/*     */       
/*     */       try {
/*  61 */         chuck = globals.load(this.plugin.getPageGetter());
/*  62 */       } catch (LuaError error) {
/*  63 */         return null;
/*     */       } 
/*  65 */       LuaValue result = chuck.call((LuaValue)LuaValue.valueOf(url));
/*  66 */       if (result instanceof LuaTable) {
/*  67 */         final List<String> urlList = new ArrayList<>();
/*  68 */         Lua.forEach((LuaTable)result, new Lua.TableAction()
/*     */             {
/*     */               public void action(String key, LuaValue value) {
/*  71 */                 urlList.add(value.tojstring());
/*     */               }
/*     */             });
/*  74 */         return urlList;
/*     */       } 
/*     */     } else {
/*  77 */       final List<String> urlList = new ArrayList<>();
/*  78 */       urlList.add(url);
/*  79 */       return urlList;
/*     */     } 
/*  81 */     return null;
/*     */   }
/*     */   
/*     */   private List<ChapterEntity> loadChapterList(String url) {
/*  85 */     for (int i = 0; i < 3; i++) {
/*  86 */       if (this.plugin.getChapGetter() != null) {
/*  87 */         LuaValue chuck; Globals globals = LuaScriptEngine.getInstance().getGlobals();
/*  88 */         final List<ChapterEntity> list = new ArrayList<>();
/*     */         
/*     */         try {
/*  91 */           chuck = globals.load(this.plugin.getTocGetter());
/*  92 */         } catch (LuaError error) {
/*  93 */           return null;
/*     */         } 
/*  95 */         LuaValue result = chuck.call((LuaValue)LuaValue.valueOf(url));
/*  96 */         if (result instanceof LuaTable) {
/*  97 */           Lua.forEach((LuaTable)result, new Lua.TableAction()
/*     */               {
/*     */                 public void action(String key, LuaValue value) {
/* 100 */                   ChapterEntity chapter = new ChapterEntity();
/* 101 */                   chapter.setName(value.get("name").tojstring());
/* 102 */                   chapter.setUrl(value.get("url").tojstring());
/* 103 */                   list.add(chapter);
/*     */                 }
/*     */               });
/*     */         }
/*     */ 
/*     */         
/* 109 */         if (list.size() > 0)
/* 110 */           return list; 
/*     */       } 
/*     */     } 
/* 113 */     return null;
/*     */   }
/*     */   
/*     */   public TocLoader listener(Listener callback) {
/* 117 */     this.callback = callback;
/* 118 */     return this;
/*     */   }
/*     */   
/*     */   public static interface Listener {
/*     */     void onUpdate(List<ChapterEntity> param1List, float param1Float);
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\loader\TocLoader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */