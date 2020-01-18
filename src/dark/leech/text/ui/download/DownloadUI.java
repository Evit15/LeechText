/*     */ package dark.leech.text.ui.download;
/*     */ import dark.leech.text.action.History;
/*     */ import dark.leech.text.listeners.AddListener;
/*     */ import dark.leech.text.models.Properties;
/*     */ import dark.leech.text.ui.material.JMPanel;
/*     */ import dark.leech.text.ui.material.JMScrollPane;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.awt.dnd.DropTarget;
/*     */ import java.awt.dnd.DropTargetDragEvent;
/*     */ import java.awt.dnd.DropTargetDropEvent;
/*     */ import java.awt.dnd.DropTargetListener;
/*     */ import java.io.File;
/*     */ import java.util.List;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class DownloadUI extends JPanel implements AddListener, RemoveListener, DropTargetListener {
/*     */   private JMPanel download;
/*     */   
/*     */   public DownloadUI() {
/*  25 */     onCreate();
/*     */   }
/*     */   private JMScrollPane scrollPane;
/*     */   private void onCreate() {
/*  29 */     setLayout(null);
/*  30 */     this.download = new JMPanel(new GridBagLayout());
/*  31 */     this.download.setBackground(Color.white);
/*  32 */     GridBagConstraints gbc = new GridBagConstraints();
/*  33 */     gbc.gridwidth = 0;
/*  34 */     gbc.weightx = 1.0D;
/*  35 */     gbc.weighty = 1.0D;
/*  36 */     this.scrollPane = new JMScrollPane((Component)this.download);
/*     */     
/*  38 */     JPanel demo = new JPanel();
/*  39 */     demo.setBackground(new Color(0, 0, 0, 0));
/*  40 */     this.download.add(demo, gbc);
/*     */     
/*  42 */     add((Component)this.scrollPane);
/*  43 */     this.scrollPane.setBounds(0, 55, 390, 475);
/*  44 */     new DropTarget(this, this);
/*     */   }
/*     */   
/*     */   public void actionAdd(Properties properties, boolean imp) {
/*  48 */     GridBagConstraints gbc = new GridBagConstraints();
/*  49 */     gbc.gridwidth = 0;
/*  50 */     gbc.weightx = 1.0D;
/*  51 */     gbc.fill = 2;
/*  52 */     DownloadLabel dl = new DownloadLabel();
/*     */     
/*  54 */     if (imp) { dl.importDownload(properties); }
/*  55 */     else { dl.addDownload(properties); }
/*     */     
/*  57 */     dl.addRemoveListener(this);
/*  58 */     this.download.add((Component)dl, gbc, 0);
/*  59 */     this.download.updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeComponent(Component comp) {
/*  64 */     this.download.remove(comp);
/*  65 */     this.download.updateUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drop(DropTargetDropEvent event) {
/*  70 */     event.acceptDrop(1);
/*  71 */     Transferable transferable = event.getTransferable();
/*  72 */     DataFlavor[] flavors = transferable.getTransferDataFlavors();
/*  73 */     for (DataFlavor flavor : flavors) {
/*     */       try {
/*  75 */         if (flavor.isFlavorJavaFileListType()) {
/*  76 */           List<File> files = (List<File>)transferable.getTransferData(flavor);
/*  77 */           for (File file : files) {
/*  78 */             String path = file.getPath();
/*  79 */             AddDialog dialog = new AddDialog(History.getHistory().load(path));
/*  80 */             dialog.setAddListener(this);
/*  81 */             dialog.open();
/*     */           } 
/*     */         } 
/*  84 */       } catch (Exception exception) {}
/*     */     } 
/*     */     
/*  87 */     event.dropComplete(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dragEnter(DropTargetDragEvent dtde) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void dragOver(DropTargetDragEvent dtde) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropActionChanged(DropTargetDragEvent dtde) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void dragExit(DropTargetEvent dte) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDownload(Properties properties, boolean imp) {
/* 110 */     actionAdd(properties, imp);
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\download\DownloadUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */