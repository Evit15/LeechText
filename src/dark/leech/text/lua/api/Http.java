/*     */ package dark.leech.text.lua.api;
/*     */ 
/*     */ import dark.leech.text.util.SettingUtils;
/*     */ import dark.leech.text.util.TextUtils;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jsoup.Connection;
/*     */ import org.jsoup.Jsoup;
/*     */ import org.luaj.vm2.LuaBoolean;
/*     */ import org.luaj.vm2.LuaTable;
/*     */ import org.luaj.vm2.LuaValue;
/*     */ import org.luaj.vm2.lib.jse.CoerceJavaToLua;
/*     */ 
/*     */ public class Http
/*     */ {
/*     */   private static final String TAG = "Http";
/*     */   private String url;
/*     */   private boolean syncCookie = true;
/*     */   private boolean skipCookie = false;
/*     */   private Connection connection;
/*     */   private Connection.Response response;
/*  23 */   private static List<CacheResponse> cache = new ArrayList<>();
/*  24 */   private static List<String> cacheUrl = new ArrayList<>();
/*     */   
/*     */   private static CookieManager cookieManager;
/*     */   
/*     */   static {
/*     */     try {
/*  30 */       cookieManager = CookieManager.getInstance();
/*  31 */     } catch (Throwable throwable) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Http request(Object url) {
/*  39 */     if (url instanceof String)
/*  40 */     { this.url = (String)url; }
/*  41 */     else { this.url = url.toString(); }
/*  42 */      this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  48 */       .connection = Jsoup.connect(this.url).header("User-Agent", SettingUtils.USER_AGENT).followRedirects(true).ignoreContentType(true).ignoreHttpErrors(true).timeout(SettingUtils.TIMEOUT).maxBodySize(0);
/*  49 */     return this;
/*     */   }
/*     */   
/*     */   public Http headers(LuaTable headers) {
/*  53 */     Lua.forEach(headers, new Lua.TableAction()
/*     */         {
/*     */           public void action(String key, LuaValue value) {
/*  56 */             Http.this.connection.header(key, value.tojstring());
/*     */           }
/*     */         });
/*  59 */     return this;
/*     */   }
/*     */   
/*     */   public Http body(Object body) {
/*  63 */     this.connection.requestBody(body.toString());
/*  64 */     return this;
/*     */   }
/*     */   
/*     */   public Http params(LuaTable params) {
/*  68 */     Lua.forEach(params, new Lua.TableAction()
/*     */         {
/*     */           public void action(String key, LuaValue value) {
/*  71 */             Http.this.connection.data(key, value.tojstring());
/*     */           }
/*     */         });
/*  74 */     return this;
/*     */   }
/*     */   
/*     */   public LuaValue cookies() {
/*  78 */     if (this.response != null)
/*  79 */       return (LuaValue)LuaValue.valueOf(this.response.header("Set-Cookie")); 
/*  80 */     return (LuaValue)LuaValue.valueOf("");
/*     */   }
/*     */   
/*     */   public Http cookies(Object object) {
/*  84 */     this.skipCookie = true;
/*  85 */     this.connection.header("Cookie", object.toString());
/*  86 */     return this;
/*     */   }
/*     */   
/*     */   public Http cookie(Object sync, Object skip) {
/*  90 */     if (sync instanceof Boolean) {
/*  91 */       this.syncCookie = ((Boolean)sync).booleanValue();
/*  92 */     } else if (sync instanceof LuaBoolean) {
/*  93 */       this.syncCookie = ((LuaBoolean)sync).booleanValue();
/*     */     } 
/*  95 */     if (skip instanceof Boolean) {
/*  96 */       this.skipCookie = ((Boolean)skip).booleanValue();
/*  97 */     } else if (skip instanceof LuaBoolean) {
/*  98 */       this.skipCookie = ((LuaBoolean)skip).booleanValue();
/*  99 */     }  return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Http timeout(Object value) {
/*     */     try {
/* 105 */       this.connection.timeout(Integer.parseInt(value.toString()));
/* 106 */     } catch (Exception exception) {}
/*     */     
/* 108 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Http post() {
/* 113 */     this.connection.method(Connection.Method.POST);
/* 114 */     return this;
/*     */   }
/*     */   
/*     */   public Http get() {
/* 118 */     this.connection.method(Connection.Method.GET);
/* 119 */     return this;
/*     */   }
/*     */   
/*     */   public LuaValue html() {
/*     */     try {
/* 124 */       return CoerceJavaToLua.coerce(call().parse());
/* 125 */     } catch (Exception e) {
/* 126 */       e.printStackTrace();
/* 127 */       return LuaValue.NIL;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Connection.Response call() {
/* 132 */     int index = cacheUrl.indexOf(this.url);
/* 133 */     if (index != -1)
/* 134 */       return cache.get(index); 
/*     */     try {
/* 136 */       if (!this.skipCookie && 
/* 137 */         cookieManager != null) {
/* 138 */         String cookie = cookieManager.getCookie(this.url);
/* 139 */         if (cookie != null) {
/* 140 */           this.connection.header("Cookie", cookie);
/*     */         }
/*     */       } 
/* 143 */       this.response = this.connection.execute();
/*     */       
/* 145 */       if (this.syncCookie) {
/* 146 */         String cookie = this.response.header("Set-Cookie");
/* 147 */         if (cookieManager != null && !TextUtils.isEmpty(cookie)) {
/* 148 */           cookieManager.setCookie(this.url, cookie);
/*     */         }
/*     */       } 
/* 151 */       synchronized (Http.class) {
/* 152 */         if (cache.size() > 5) {
/* 153 */           cacheUrl.remove(0);
/* 154 */           cache.remove(0);
/*     */         } 
/* 156 */         if (this.response.statusCode() == 200 && this.response.method() == Connection.Method.GET) {
/* 157 */           cacheUrl.add(this.url);
/* 158 */           cache.add(new CacheResponse(this.response));
/*     */         } 
/*     */       } 
/* 161 */       return this.response;
/* 162 */     } catch (IOException e) {
/* 163 */       e.printStackTrace();
/* 164 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public LuaValue string() {
/*     */     try {
/* 170 */       String htm = call().body();
/* 171 */       if (!TextUtils.isEmpty(htm))
/* 172 */       { if (htm.charAt(0) == '﻿')
/* 173 */           htm = htm.substring(1);  }
/* 174 */       else { return LuaValue.NIL; }
/* 175 */        return (LuaValue)LuaValue.valueOf(htm);
/* 176 */     } catch (Exception e) {
/* 177 */       e.printStackTrace();
/* 178 */       return LuaValue.NIL;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public LuaValue table() {
/*     */     try {
/* 185 */       return Json.to_table(string());
/* 186 */     } catch (Exception e) {
/* 187 */       return LuaValue.NIL;
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] raw() {
/*     */     try {
/* 193 */       return call().bodyAsBytes();
/* 194 */     } catch (Exception e) {
/* 195 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\api\Http.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */