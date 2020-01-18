/*    */ package dark.leech.text.ui.material;
/*    */ 
/*    */ import dark.leech.text.models.Chapter;
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Paint;
/*    */ import java.awt.Rectangle;
/*    */ import java.util.List;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.table.DefaultTableModel;
/*    */ import javax.swing.table.JTableHeader;
/*    */ import javax.swing.table.TableColumnModel;
/*    */ 
/*    */ public class JMTable
/*    */   extends JTable {
/*    */   public JMTable(List<Chapter> chapter) {
/* 18 */     this();
/* 19 */     Object[][] data = new Object[chapter.size()][3];
/* 20 */     for (int i = 0; i < chapter.size(); i++) {
/* 21 */       data[i][0] = ((Chapter)chapter.get(i)).getId();
/* 22 */       data[i][1] = ((Chapter)chapter.get(i)).getPartName();
/* 23 */       data[i][2] = ((Chapter)chapter.get(i)).getChapName();
/*    */     } 
/* 25 */     Object[] head = { "", "", "" };
/* 26 */     setModel(new DefaultTableModel(data, head) {
/* 27 */           Class<?>[] columnTypes = new Class[] { String.class, String.class, String.class };
/* 28 */           boolean[] columnEditable = new boolean[] { false, true, true };
/*    */ 
/*    */ 
/*    */ 
/*    */           
/*    */           public Class<?> getColumnClass(int columnIndex) {
/* 34 */             return this.columnTypes[columnIndex];
/*    */           }
/*    */ 
/*    */           
/*    */           public boolean isCellEditable(int rowIndex, int columnIndex) {
/* 39 */             return this.columnEditable[columnIndex];
/*    */           }
/*    */         });
/*    */     
/* 43 */     TableColumnModel cm = getColumnModel();
/* 44 */     cm.getColumn(0).setMaxWidth(40);
/* 45 */     cm.getColumn(1).setMinWidth(100);
/* 46 */     cm.getColumn(1).setMaxWidth(80);
/*    */   }
/*    */ 
/*    */   
/*    */   public JMTable() {
/* 51 */     setTableHeader((JTableHeader)null);
/* 52 */     setShowGrid(false);
/* 53 */     setRowHeight(25);
/* 54 */     setOpaque(false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void paintComponent(Graphics g) {
/* 61 */     int width = getWidth();
/* 62 */     int height = getHeight();
/* 63 */     Graphics2D g2 = (Graphics2D)g;
/* 64 */     Paint oldPaint = g2.getPaint();
/* 65 */     g2.setColor(Color.WHITE);
/* 66 */     g2.fillRect(0, 0, width, height);
/* 67 */     g2.setPaint(oldPaint);
/* 68 */     for (int row : getSelectedRows()) {
/* 69 */       Rectangle start = getCellRect(row, 0, true);
/* 70 */       Rectangle end = getCellRect(row, getColumnCount() - 1, true);
/* 71 */       g2.setColor(Color.GRAY);
/* 72 */       g2.drawRect(start.x, start.y, end.x + end.width - start.x, start.height);
/*    */     } 
/* 74 */     super.paintComponent(g);
/*    */   }
/*    */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\material\JMTable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */