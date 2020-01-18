/*    */ package dark.leech.text.util;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ 
/*    */ public class Base64 {
/*    */   public static String encode(byte[] data) {
/*  7 */     char[] tbl = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 13 */     StringBuilder buffer = new StringBuilder();
/* 14 */     int pad = 0;
/* 15 */     for (int i = 0; i < data.length; i += 3) {
/*    */       
/* 17 */       int b = (data[i] & 0xFF) << 16 & 0xFFFFFF;
/* 18 */       if (i + 1 < data.length) {
/* 19 */         b |= (data[i + 1] & 0xFF) << 8;
/*    */       } else {
/* 21 */         pad++;
/*    */       } 
/* 23 */       if (i + 2 < data.length) {
/* 24 */         b |= data[i + 2] & 0xFF;
/*    */       } else {
/* 26 */         pad++;
/*    */       } 
/*    */       
/* 29 */       for (int k = 0; k < 4 - pad; k++) {
/* 30 */         int c = (b & 0xFC0000) >> 18;
/* 31 */         buffer.append(tbl[c]);
/* 32 */         b <<= 6;
/*    */       } 
/*    */     } 
/* 35 */     for (int j = 0; j < pad; j++) {
/* 36 */       buffer.append("=");
/*    */     }
/*    */     
/* 39 */     return buffer.toString();
/*    */   }
/*    */   
/*    */   public static byte[] decode(String data) {
/* 43 */     int[] tbl = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 59 */     byte[] bytes = data.getBytes();
/* 60 */     ByteArrayOutputStream buffer = new ByteArrayOutputStream();
/* 61 */     for (int i = 0; i < bytes.length; ) {
/* 62 */       int b = 0;
/* 63 */       if (tbl[bytes[i]] != -1) {
/* 64 */         b = (tbl[bytes[i]] & 0xFF) << 18;
/*    */       }
/*    */       else {
/*    */         
/* 68 */         i++;
/*    */         
/*    */         continue;
/*    */       } 
/* 72 */       int num = 0;
/* 73 */       if (i + 1 < bytes.length && tbl[bytes[i + 1]] != -1) {
/* 74 */         b |= (tbl[bytes[i + 1]] & 0xFF) << 12;
/* 75 */         num++;
/*    */       } 
/* 77 */       if (i + 2 < bytes.length && tbl[bytes[i + 2]] != -1) {
/* 78 */         b |= (tbl[bytes[i + 2]] & 0xFF) << 6;
/* 79 */         num++;
/*    */       } 
/* 81 */       if (i + 3 < bytes.length && tbl[bytes[i + 3]] != -1) {
/* 82 */         b |= tbl[bytes[i + 3]] & 0xFF;
/* 83 */         num++;
/*    */       } 
/*    */       
/* 86 */       while (num > 0) {
/* 87 */         int c = (b & 0xFF0000) >> 16;
/* 88 */         buffer.write((char)c);
/* 89 */         b <<= 8;
/* 90 */         num--;
/*    */       } 
/* 92 */       i += 4;
/*    */     } 
/* 94 */     return buffer.toByteArray();
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\util\Base64.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */