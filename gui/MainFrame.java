package gui;

import model.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class MainFrame extends JFrame {

    private java.util.List<Ucus> ucuslar = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
    private CardLayout cardLayout;
    private JPanel formPanel;

    public MainFrame() {
        setTitle("Havaalanı Otomasyonu");
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---------------- SOL MENÜ ----------------
        JPanel menu = new JPanel(new GridLayout(3, 1, 10, 10));
        menu.setPreferredSize(new Dimension(200, 0));

        JButton btnUcusEkle = new JButton("✈ Uçuş Ekle");
        JButton btnYolcu = new JButton("🧍 Yolcu İşlemleri");

        menu.add(btnUcusEkle);
        menu.add(btnYolcu);

        add(menu, BorderLayout.WEST);

        // ---------------- FORM PANEL ----------------
        cardLayout = new CardLayout();
        formPanel = new JPanel(cardLayout);

        formPanel.add(ucusEklePanel(), "UCUS");
        formPanel.add(yolcuPanel(), "YOLCU");

        add(formPanel, BorderLayout.NORTH);

        btnUcusEkle.addActionListener(e -> cardLayout.show(formPanel, "UCUS"));
        btnYolcu.addActionListener(e -> cardLayout.show(formPanel, "YOLCU"));

        // ---------------- TABLO ----------------
        tableModel = new DefaultTableModel(
                new Object[]{"", "Uçuş Kodu", "Kalkış", "Varış", "Yolcu"}, 0);

        table = new JTable(tableModel) {
            public Class<?> getColumnClass(int col) {
                return col == 0 ? Icon.class : Object.class;
            }
        };
        table.setRowHeight(40);

        add(new JScrollPane(table), BorderLayout.CENTER);

        rastgeleUcusEkle();
        tabloyuGuncelle();
    }

    // ---------------- PANELLER ----------------

    private JPanel ucusEklePanel() {
        JPanel p = new JPanel();

        JTextField kod = new JTextField(6);
        JTextField kalkis = new JTextField(6);
        JTextField varis = new JTextField(6);

        JComboBox<String> sirket = new JComboBox<>(new String[]{"thy", "ajet", "pegasus"});
        JButton ekle = new JButton("Ekle");

        ekle.addActionListener(e -> {
            ucuslar.add(new Ucus(
                    kod.getText(), kalkis.getText(), varis.getText(),
                    sirket.getSelectedItem().toString()
            ));
            tabloyuGuncelle();
        });

        p.add(new JLabel("Kod"));
        p.add(kod);
        p.add(new JLabel("Kalkış"));
        p.add(kalkis);
        p.add(new JLabel("Varış"));
        p.add(varis);
        p.add(sirket);
        p.add(ekle);

        return p;
    }

    private JPanel yolcuPanel() {
        JPanel p = new JPanel();

        JComboBox<Ucus> ucusBox = new JComboBox<>();
        JTextField ad = new JTextField(6);
        JTextField soyad = new JTextField(6);

        JButton ekle = new JButton("Yolcu Ekle");
        JButton sil = new JButton("Son Yolcuyu Sil");

        ekle.addActionListener(e -> {
            Ucus u = (Ucus) ucusBox.getSelectedItem();
            if (u != null) {
                u.yolcuEkle(new Yolcu(ad.getText(), soyad.getText()));
                tabloyuGuncelle();
            }
        });

        sil.addActionListener(e -> {
            Ucus u = (Ucus) ucusBox.getSelectedItem();
            if (u != null && !u.getYolcular().isEmpty()) {
                u.getYolcular().remove(u.getYolcular().size() - 1);
                tabloyuGuncelle();
            }
        });

        p.add(new JLabel("Uçuş"));
        p.add(ucusBox);
        p.add(ad);
        p.add(soyad);
        p.add(ekle);
        p.add(sil);

        // her tablo güncellemede combo yenilenecek
        Timer t = new Timer(500, e -> {
            ucusBox.removeAllItems();
            ucuslar.forEach(ucusBox::addItem);
        });
        t.start();

        return p;
    }

    // ---------------- YARDIMCI ----------------

    private void rastgeleUcusEkle() {
        String[] sehirler = {"IST", "ANK", "IZM", "ANT", "ADA"};
        String[] sirketler = {"thy", "ajet", "pegasus"};
        Random r = new Random();

        for (int i = 0; i < 15; i++) {

            String kalkis = sehirler[r.nextInt(sehirler.length)];
            String varis;

            // kalkış ve varış aynı olamaz
            do {
                varis = sehirler[r.nextInt(sehirler.length)];
            } while (kalkis.equals(varis));

            String sirket = sirketler[r.nextInt(sirketler.length)];
            String prefix = getUcusPrefix(sirket);

            ucuslar.add(new Ucus(
                    prefix + (100 + i),
                    kalkis,
                    varis,
                    sirket
            ));
        }
    }

    private void tabloyuGuncelle() {
        tableModel.setRowCount(0);
        for (Ucus u : ucuslar) {
            ImageIcon icon = getScaledIcon(
                    "assets/" + u.getSirket() + ".png"
            );

            tableModel.addRow(new Object[]{
                    icon,
                    u.getUcusKodu(),
                    u.getKalkis(),
                    u.getVaris(),
                    u.getYolcular().size()
            });
        }
    }

    private String getUcusPrefix(String sirket) {
        return switch (sirket) {
            case "thy" -> "TK";
            case "pegasus" -> "PC";
            case "ajet" -> "VF";
            default -> "XX";
        };
    }


    private ImageIcon getScaledIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(32 * 3, 32, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}
