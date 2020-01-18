/*     */ package dark.leech.text.lua.api;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jsoup.Connection;
/*     */ import org.jsoup.Jsoup;
/*     */ import org.jsoup.nodes.Document;
/*     */ 
/*     */ public class CacheResponse
/*     */   implements Connection.Response {
/*     */   private byte[] data;
/*     */   
/*     */   public CacheResponse(Connection.Response response) {
/*  18 */     this.data = response.bodyAsBytes();
/*     */   }
/*     */ 
/*     */   
/*     */   public URL url() {
/*  23 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Connection.Response url(URL url) {
/*  28 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Connection.Method method() {
/*  33 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Connection.Response method(Connection.Method method) {
/*  38 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String header(String name) {
/*  43 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> headers(String name) {
/*  48 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Connection.Response header(String name, String value) {
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Connection.Response addHeader(String name, String value) {
/*  58 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasHeader(String name) {
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasHeaderWithValue(String name, String value) {
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Connection.Response removeHeader(String name) {
/*  73 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, String> headers() {
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, List<String>> multiHeaders() {
/*  83 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String cookie(String name) {
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Connection.Response cookie(String name, String value) {
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCookie(String name) {
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Connection.Response removeCookie(String name) {
/* 103 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, String> cookies() {
/* 108 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int statusCode() {
/* 113 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String statusMessage() {
/* 118 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String charset() {
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Connection.Response charset(String charset) {
/* 128 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String contentType() {
/* 133 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Document parse() throws IOException {
/* 138 */     return Jsoup.parse(new String(this.data, Charset.forName("UTF-8")));
/*     */   }
/*     */ 
/*     */   
/*     */   public String body() {
/* 143 */     return new String(this.data, Charset.forName("UTF-8"));
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] bodyAsBytes() {
/* 148 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public Connection.Response bufferUp() {
/* 153 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public BufferedInputStream bodyStream() {
/* 158 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\text\lua\api\CacheResponse.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */