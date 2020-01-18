/*     */ package dark.leech.text.util;
/*     */ 
/*     */ import dark.leech.text.action.Log;
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import org.json.JSONObject;
/*     */ import org.jsoup.Connection;
/*     */ import org.jsoup.Jsoup;
/*     */ import org.jsoup.nodes.Document;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Http
/*     */ {
/*     */   private Connection connection;
/*     */   private Connection.Response response;
/*     */   
/*     */   private Http(String url) {
/*  20 */     this.connection = connect(url);
/*     */   }
/*     */   
/*     */   public static Connection connect(String url) {
/*     */     try {
/*  25 */       String cookies = CookiesUtils.getCookies(url);
/*  26 */       if (cookies == null)
/*  27 */         return Jsoup.connect(url)
/*  28 */           .userAgent(SettingUtils.USER_AGENT)
/*  29 */           .ignoreContentType(true)
/*  30 */           .followRedirects(true)
/*  31 */           .maxBodySize(0)
/*  32 */           .timeout(SettingUtils.TIMEOUT); 
/*  33 */       return Jsoup.connect(url)
/*  34 */         .userAgent(SettingUtils.USER_AGENT)
/*  35 */         .header("Cookie", cookies)
/*  36 */         .ignoreContentType(true)
/*  37 */         .followRedirects(true)
/*  38 */         .maxBodySize(0)
/*  39 */         .timeout(SettingUtils.TIMEOUT);
/*  40 */     } catch (Exception e) {
/*  41 */       Log.add(e);
/*  42 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Document get(String url) {
/*     */     try {
/*  49 */       return connect(url).get();
/*  50 */     } catch (IOException e) {
/*  51 */       Log.add(e);
/*  52 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Http cookie(String cookie) {
/*  57 */     this.connection.header("Cookie", cookie);
/*  58 */     return this;
/*     */   }
/*     */   
/*     */   public String cookies() {
/*  62 */     return this.response.header("Set-Cookie");
/*     */   }
/*     */   
/*     */   public static Http request(String url) {
/*  66 */     return new Http(url);
/*     */   }
/*     */   
/*     */   public Http data(String name, String value) {
/*  70 */     this.connection.data(name, value);
/*  71 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Http data(Map<String, String> data) {
/*  76 */     this.connection.data(data);
/*  77 */     return this;
/*     */   }
/*     */   
/*     */   public Http data(String... args) {
/*  81 */     this.connection.data(args);
/*  82 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Http header(String name, String value) {
/*  87 */     this.connection.header(name, value);
/*  88 */     return this;
/*     */   }
/*     */   
/*     */   private void execute() {
/*     */     try {
/*  93 */       this.response = this.connection.execute();
/*  94 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public String string() {
/*  99 */     execute();
/*     */     try {
/* 101 */       if (this.response != null)
/* 102 */         return this.response.body(); 
/* 103 */     } catch (Exception exception) {}
/*     */     
/* 105 */     return null;
/*     */   }
/*     */   
/*     */   public Document document() {
/* 109 */     execute();
/*     */     try {
/* 111 */       if (this.response != null)
/* 112 */         return this.response.parse(); 
/* 113 */     } catch (Exception exception) {}
/*     */     
/* 115 */     return null;
/*     */   }
/*     */   
/*     */   public JSONObject json() {
/* 119 */     execute();
/*     */     try {
/* 121 */       if (this.response != null)
/* 122 */         return new JSONObject(this.response.body()); 
/* 123 */     } catch (Exception exception) {}
/*     */     
/* 125 */     return null;
/*     */   }
/*     */   
/*     */   public byte[] bytes() {
/* 129 */     execute();
/*     */     try {
/* 131 */       if (this.response != null)
/* 132 */         return this.response.bodyAsBytes(); 
/* 133 */     } catch (Exception exception) {}
/*     */     
/* 135 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\util\Http.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */