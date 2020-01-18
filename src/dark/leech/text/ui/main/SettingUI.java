/*     */ package dark.leech.text.ui.main;
/*     */ 
/*     */ import dark.leech.text.ui.button.BasicButton;
/*     */ import dark.leech.text.ui.material.JMScrollPane;
/*     */ import dark.leech.text.ui.notification.Toast;
/*     */ import dark.leech.text.ui.setting.ItemConn;
/*     */ import dark.leech.text.ui.setting.ItemStyle;
/*     */ import dark.leech.text.ui.setting.Theme;
/*     */ import dark.leech.text.ui.setting.ToolPane;
/*     */ import dark.leech.text.ui.setting.trash.TrashPane;
/*     */ import dark.leech.text.util.ColorUtils;
/*     */ import dark.leech.text.util.FontUtils;
/*     */ import dark.leech.text.util.SettingUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ public class SettingUI extends JPanel {
/*     */   private JPanel body;
/*     */   private JMScrollPane scrollPane;
/*     */   private ItemConn maxConn;
/*     */   private ItemConn reConn;
/*     */   private ItemConn timeConn;
/*     */   private ItemConn delayConn;
/*     */   private ItemConn userAgent;
/*     */   private ItemStyle htmlStyle;
/*     */   private ItemStyle txtStyle;
/*     */   private ItemStyle cssStyle;
/*     */   private ItemStyle dropStyle;
/*     */   private TrashPane trash;
/*     */   private ToolPane kindlegen;
/*     */   private ToolPane calibre;
/*     */   private ToolPane workPath;
/*     */   private Theme theme;
/*     */   private BasicButton defaultButton;
/*     */   
/*     */   public SettingUI() {
/*  46 */     setLayout((LayoutManager)null);
/*  47 */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           public void run() {
/*  50 */             SettingUI.this.onCreate();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void load() {
/*  56 */     (new Thread(new Runnable()
/*     */         {
/*     */           public void run() {
/*  59 */             SettingUI.this.updateStatus();
/*     */           }
/*  61 */         })).start();
/*     */   }
/*     */   
/*     */   private void onCreate() {
/*  65 */     setBackground(Color.WHITE);
/*  66 */     this.body = new JPanel(new GridBagLayout());
/*  67 */     this.body.setBackground(Color.white);
/*  68 */     GridBagConstraints gi = new GridBagConstraints();
/*  69 */     gi.gridwidth = 0;
/*  70 */     gi.weightx = 1.0D;
/*  71 */     gi.weighty = 1.0D;
/*  72 */     this.scrollPane = new JMScrollPane(this.body);
/*     */     
/*  74 */     JPanel demo = new JPanel();
/*  75 */     demo.setBackground(Color.WHITE);
/*  76 */     this.body.add(demo, gi);
/*  77 */     add((Component)this.scrollPane);
/*  78 */     this.scrollPane.setBounds(0, 55, 390, 440);
/*     */     
/*  80 */     GridBagConstraints gbc = new GridBagConstraints();
/*  81 */     gbc.gridwidth = 0;
/*  82 */     gbc.weightx = 1.0D;
/*  83 */     gbc.fill = 2;
/*     */     
/*  85 */     this.maxConn = new ItemConn("Số kết nối tối đa", "5");
/*  86 */     this.reConn = new ItemConn("Số kết nối lại khi bị lỗi", "3");
/*  87 */     this.timeConn = new ItemConn("Thời gian kết nối tối đa", "10000");
/*  88 */     this.delayConn = new ItemConn("Thời gian chờ giữa các kết nối", "100");
/*  89 */     this.userAgent = new ItemConn("User Agent", "");
/*     */     
/*  91 */     this.htmlStyle = new ItemStyle("Tùy chỉnh HTML SYNTAX", "Tùy chỉnh cấu trúc HTML khi lưu", false);
/*  92 */     this.htmlStyle.setStyle("text/html");
/*  93 */     this.txtStyle = new ItemStyle("Tùy chỉnh TXT SYNTAX", "Tùy chỉnh cấu trúc TXT khi lưu", false);
/*  94 */     this.cssStyle = new ItemStyle("Tùy chỉnh CSS SYNTAX", "Tùy chỉnh cấu trúc CSS khi lưu", false);
/*  95 */     this.cssStyle.setStyle("text/css");
/*  96 */     this.dropStyle = new ItemStyle("Tùy chỉnh DropCaps", "Tùy chỉnh cấu trúc DropCaps khi lưu", false);
/*  97 */     this.dropStyle.setStyle("text/html");
/*     */     
/*  99 */     this.trash = new TrashPane(SettingUtils.TRASH);
/* 100 */     this.kindlegen = new ToolPane("Đường dẫn Kindlegen", "", 0);
/* 101 */     this.calibre = new ToolPane("Đường dẫn Calibre", "", 0);
/* 102 */     this.workPath = new ToolPane("Thư mục lưu trữ", "", 1);
/* 103 */     this.workPath.setSelectDirectory(true);
/* 104 */     this.theme = new Theme(SettingUtils.THEME_COLOR);
/*     */     
/* 106 */     this.body.add((Component)this.theme, gbc, 0);
/* 107 */     this.body.add((Component)this.calibre, gbc, 0);
/* 108 */     this.body.add((Component)this.kindlegen, gbc, 0);
/* 109 */     this.body.add((Component)this.workPath, gbc, 0);
/* 110 */     this.body.add((Component)this.trash, gbc, 0);
/* 111 */     this.body.add(Label("Khác"), gbc, 0);
/* 112 */     this.body.add((Component)this.cssStyle, gbc, 0);
/* 113 */     this.body.add((Component)this.txtStyle, gbc, 0);
/* 114 */     this.body.add((Component)this.htmlStyle, gbc, 0);
/* 115 */     this.body.add((Component)this.dropStyle, gbc, 0);
/* 116 */     this.body.add(Label("Cấu trúc lưu"), gbc, 0);
/* 117 */     this.body.add((Component)this.userAgent, gbc, 0);
/* 118 */     this.body.add((Component)this.delayConn, gbc, 0);
/* 119 */     this.body.add((Component)this.reConn, gbc, 0);
/* 120 */     this.body.add((Component)this.timeConn, gbc, 0);
/* 121 */     this.body.add((Component)this.maxConn, gbc, 0);
/* 122 */     this.body.add(Label("Kết nối"), gbc, 0);
/*     */ 
/*     */     
/* 125 */     this.defaultButton = new BasicButton();
/* 126 */     this.defaultButton.setText("Khôi phục mặc định");
/* 127 */     this.defaultButton.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 130 */             SettingUtils.doDefault();
/* 131 */             SettingUI.this.updateStatus();
/* 132 */             Toast.Build()
/* 133 */               .font(FontUtils.TITLE_NORMAL)
/* 134 */               .content("Đã khôi phục mặc định!")
/* 135 */               .open();
/*     */           }
/*     */         });
/* 138 */     add((Component)this.defaultButton);
/* 139 */     this.defaultButton.setBounds(0, 495, 390, 35);
/*     */   }
/*     */ 
/*     */   
/*     */   private JPanel Label(String name) {
/* 144 */     JPanel panel = new JPanel();
/* 145 */     JLabel label = new JLabel(name);
/* 146 */     label.setForeground(ColorUtils.THEME_COLOR.darker());
/* 147 */     label.setFont(FontUtils.TITLE_NORMAL);
/* 148 */     panel.add(label);
/* 149 */     label.setBounds(10, 0, 200, 30);
/* 150 */     panel.setLayout((LayoutManager)null);
/* 151 */     panel.setBackground(Color.WHITE);
/* 152 */     panel.setPreferredSize(new Dimension(370, 30));
/* 153 */     return panel;
/*     */   }
/*     */   
/*     */   private void updateStatus() {
/* 157 */     this.maxConn.setText(toString(SettingUtils.MAX_CONN));
/* 158 */     this.reConn.setText(toString(SettingUtils.RE_CONN));
/* 159 */     this.timeConn.setText(toString(SettingUtils.TIMEOUT));
/* 160 */     this.delayConn.setText(toString(SettingUtils.DELAY));
/* 161 */     this.userAgent.setText(SettingUtils.USER_AGENT);
/*     */     
/* 163 */     this.htmlStyle.setSelected(SettingUtils.IS_HTML_SELECTED);
/* 164 */     this.htmlStyle.setText(SettingUtils.HTML_SYNTAX);
/* 165 */     this.txtStyle.setSelected(SettingUtils.IS_TXT_SELECTED);
/* 166 */     this.txtStyle.setText(SettingUtils.TXT_SYNTAX);
/* 167 */     this.cssStyle.setSelected(SettingUtils.IS_CSS_SELECTED);
/* 168 */     this.cssStyle.setText(SettingUtils.CSS_SYNTAX);
/* 169 */     this.dropStyle.setSelected(SettingUtils.IS_DROP_SELECTED);
/* 170 */     this.dropStyle.setText(SettingUtils.DROP_SYNTAX);
/*     */     
/* 172 */     this.trash.setTrash(SettingUtils.TRASH);
/* 173 */     this.workPath.setPath(SettingUtils.WORKPATH);
/* 174 */     this.calibre.setPath(SettingUtils.CALIBRE);
/* 175 */     this.kindlegen.setPath(SettingUtils.KINDLEGEN);
/* 176 */     this.theme.setThemeColor(SettingUtils.THEME_COLOR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/* 181 */     SettingUtils.MAX_CONN = toInt(this.maxConn.getText());
/* 182 */     SettingUtils.RE_CONN = toInt(this.reConn.getText());
/* 183 */     SettingUtils.TIMEOUT = toInt(this.timeConn.getText());
/* 184 */     SettingUtils.DELAY = toInt(this.delayConn.getText());
/* 185 */     SettingUtils.USER_AGENT = this.userAgent.getText();
/*     */ 
/*     */     
/* 188 */     SettingUtils.IS_DROP_SELECTED = this.dropStyle.isSelected();
/* 189 */     SettingUtils.IS_HTML_SELECTED = this.htmlStyle.isSelected();
/* 190 */     SettingUtils.IS_TXT_SELECTED = this.txtStyle.isSelected();
/* 191 */     SettingUtils.IS_CSS_SELECTED = this.cssStyle.isSelected();
/* 192 */     SettingUtils.DROP_SYNTAX = this.dropStyle.getText();
/* 193 */     SettingUtils.HTML_SYNTAX = this.htmlStyle.getText();
/* 194 */     SettingUtils.TXT_SYNTAX = this.txtStyle.getText();
/* 195 */     SettingUtils.CSS_SYNTAX = this.cssStyle.getText();
/*     */     
/* 197 */     SettingUtils.TRASH = this.trash.getTrash();
/* 198 */     SettingUtils.WORKPATH = this.workPath.getPath();
/* 199 */     SettingUtils.CALIBRE = this.calibre.getPath();
/* 200 */     SettingUtils.KINDLEGEN = this.kindlegen.getPath();
/* 201 */     SettingUtils.THEME_COLOR = this.theme.getColor();
/* 202 */     SettingUtils.doSave();
/*     */   }
/*     */   
/*     */   private String toString(int i) {
/* 206 */     return Integer.toString(i);
/*     */   }
/*     */   
/*     */   private int toInt(String s) {
/* 210 */     return Integer.parseInt(s);
/*     */   }
/*     */ }


/* Location:              D:\GitHub\LeechText\tools\LeechText.jar!\dark\leech\tex\\ui\main\SettingUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */