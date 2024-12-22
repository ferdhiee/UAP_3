import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class PemesananTiketBioskopGUI extends JFrame {
    private JComboBox<String> comboFilm;
    private JComboBox<String> comboJadwal;
    private JSpinner spinnerJumlah;
    private JLabel lblTotalHarga;
    private JButton btnPesan, btnTampilkanPesanan, btnHapusPesanan;

    // Data awal
    private String[] daftarFilm = {"Film A", "Film B", "Film C"};
    private String[] daftarJadwal = {"10:00", "13:00", "16:00", "19:00"};
    private int hargaTiket = 50000;

    // Data pesanan
    private List<String> daftarPesanan = new ArrayList<>();

    public PemesananTiketBioskopGUI() {
        // Pengaturan dasar frame
        setTitle("Aplikasi Pemesanan Tiket Bioskop");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header dengan warna gradasi
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(52, 152, 219), getWidth(), getHeight(), new Color(155, 89, 182)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(getWidth(), 80));
        JLabel lblHeader = new JLabel("Pemesanan Tiket Bioskop", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblHeader.setForeground(Color.WHITE);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(lblHeader, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Main panel untuk form input
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(236, 240, 241));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Input Film
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(createLabel("Pilih Film:"), gbc);

        comboFilm = new JComboBox<>(daftarFilm);
        comboFilm.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(comboFilm, gbc);

        // Input Jadwal
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(createLabel("Pilih Jadwal:"), gbc);

        comboJadwal = new JComboBox<>(daftarJadwal);
        comboJadwal.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(comboJadwal, gbc);

        // Input Jumlah Tiket
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(createLabel("Jumlah Tiket:"), gbc);

        spinnerJumlah = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spinnerJumlah.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(spinnerJumlah, gbc);

        // Total Harga
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(createLabel("Total Harga:"), gbc);

        lblTotalHarga = new JLabel("Rp 0");
        lblTotalHarga.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotalHarga.setForeground(new Color(52, 152, 219));
        gbc.gridx = 1;
        mainPanel.add(lblTotalHarga, gbc);

        // Tombol Pesan Tiket
        btnPesan = createButton("Pesan Tiket", new Color(46, 204, 113));
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(btnPesan, gbc);

        // Tombol Tampilkan Pesanan
        btnTampilkanPesanan = createButton("Tampilkan Pesanan", new Color(52, 152, 219));
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(btnTampilkanPesanan, gbc);

        // Tombol Hapus Pesanan
        btnHapusPesanan = createButton("Hapus Semua Pesanan", new Color(231, 76, 60));
        gbc.gridx = 1;
        gbc.gridy = 6;
        mainPanel.add(btnHapusPesanan, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(52, 73, 94));
        JLabel lblFooter = new JLabel("© 2024 Bioskop Indonesia");
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblFooter.setForeground(Color.WHITE);
        footerPanel.add(lblFooter);
        add(footerPanel, BorderLayout.SOUTH);

        // Event Listener
        spinnerJumlah.addChangeListener(e -> hitungTotalHarga());
        btnPesan.addActionListener(this::pesanTiket);
        btnTampilkanPesanan.addActionListener(this::tampilkanPesanan);
        btnHapusPesanan.addActionListener(this::hapusPesanan);

        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        return label;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
        return button;
    }

    private void hitungTotalHarga() {
        int jumlah = (int) spinnerJumlah.getValue();
        int totalHarga = jumlah * hargaTiket;
        lblTotalHarga.setText("Rp " + totalHarga);
    }

    private void pesanTiket(ActionEvent e) {
        try {
            String filmDipilih = (String) comboFilm.getSelectedItem();
            String jadwalDipilih = (String) comboJadwal.getSelectedItem();
            int jumlahTiket = (int) spinnerJumlah.getValue();
            int totalHarga = jumlahTiket * hargaTiket;

            if (filmDipilih == null || jadwalDipilih == null) {
                throw new IllegalArgumentException("Film dan jadwal harus dipilih!");
            }

            String pesanan = "Film: " + filmDipilih + ", Jadwal: " + jadwalDipilih +
                    ", Jumlah: " + jumlahTiket + ", Harga: Rp " + totalHarga;
            daftarPesanan.add(pesanan);

            JOptionPane.showMessageDialog(this, "Pesanan berhasil ditambahkan:\n" + pesanan);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tampilkanPesanan(ActionEvent e) {
        if (daftarPesanan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak ada pesanan saat ini.");
        } else {
            StringBuilder pesananList = new StringBuilder("Daftar Pesanan:\n");
            for (String pesanan : daftarPesanan) {
                pesananList.append(pesanan).append("\n");
            }
            JOptionPane.showMessageDialog(this, pesananList.toString());
        }
    }

    private void hapusPesanan(ActionEvent e) {
        daftarPesanan.clear();
        JOptionPane.showMessageDialog(this, "Semua pesanan telah dihapus.");
    }

    public static void main(String[] args) {
        new PemesananTiketBioskopGUI();
    }
}
