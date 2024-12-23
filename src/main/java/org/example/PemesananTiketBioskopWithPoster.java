package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class PemesananTiketBioskopWithPoster extends JFrame {
    private JComboBox<String> comboFilm;
    private JComboBox<String> comboJadwal;
    private JSpinner spinnerJumlah;
    private JLabel lblTotalHarga;
    private JLabel lblPoster; // Label untuk menampilkan poster
    private JButton btnPesan, btnTampilkanPesanan, btnHapusPesanan, btnTambahPoster;

    private String[] daftarFilm = {"Film A", "Film B", "Film C"};
    private String[] daftarJadwal = {"10:00", "13:00", "16:00", "19:00"};
    private int hargaTiket = 50000;

    private List<String> daftarPesanan = new ArrayList<>();
    private String[] posterPaths = {null, null, null}; // Menyimpan path file poster untuk setiap film

    public PemesananTiketBioskopWithPoster() {
        setTitle("Aplikasi Pemesanan Tiket Bioskop");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 152, 219));
        JLabel lblHeader = new JLabel("Pemesanan Tiket Bioskop", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblHeader.setForeground(Color.WHITE);
        headerPanel.add(lblHeader);
        add(headerPanel, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Pilih Film
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(createLabel("Pilih Film:"), gbc);

        comboFilm = new JComboBox<>(daftarFilm);
        comboFilm.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        comboFilm.addActionListener(this::updatePoster);
        gbc.gridx = 1;
        mainPanel.add(comboFilm, gbc);

        // Pilih Jadwal
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(createLabel("Pilih Jadwal:"), gbc);

        comboJadwal = new JComboBox<>(daftarJadwal);
        comboJadwal.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        mainPanel.add(comboJadwal, gbc);

        // Jumlah Tiket
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
        lblTotalHarga.setForeground(new Color(46, 204, 113));
        gbc.gridx = 1;
        mainPanel.add(lblTotalHarga, gbc);

        // Poster Film
        lblPoster = new JLabel();
        lblPoster.setPreferredSize(new Dimension(300, 450)); // Dimensi lebih besar
        lblPoster.setHorizontalAlignment(SwingConstants.CENTER);
        lblPoster.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        lblPoster.setText("Poster Film");
        lblPoster.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblPoster.setHorizontalTextPosition(SwingConstants.CENTER);
        lblPoster.setVerticalTextPosition(SwingConstants.BOTTOM);
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(lblPoster, gbc);

        // Tombol Tambah Poster
        btnTambahPoster = createButton("Tambah Poster", new Color(52, 152, 219));
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(btnTambahPoster, gbc);

        // Tombol Pesan Tiket
        btnPesan = createButton("Pesan Tiket", new Color(46, 204, 113));
        gbc.gridx = 1;
        gbc.gridy = 6;
        mainPanel.add(btnPesan, gbc);

        // Tombol Tampilkan Pesanan
        btnTampilkanPesanan = createButton("Tampilkan Pesanan", new Color(241, 196, 15));
        gbc.gridx = 1;
        gbc.gridy = 7;
        mainPanel.add(btnTampilkanPesanan, gbc);

        // Tombol Hapus Pesanan
        btnHapusPesanan = createButton("Hapus Pesanan", new Color(231, 76, 60));
        gbc.gridx = 1;
        gbc.gridy = 8;
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

        // Event Listeners
        spinnerJumlah.addChangeListener(e -> hitungTotalHarga());
        btnPesan.addActionListener(this::pesanTiket);
        btnTampilkanPesanan.addActionListener(this::tampilkanPesanan);
        btnHapusPesanan.addActionListener(this::hapusPesanan);
        btnTambahPoster.addActionListener(this::tambahPoster);

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
        String filmDipilih = (String) comboFilm.getSelectedItem();
        String jadwalDipilih = (String) comboJadwal.getSelectedItem();
        int jumlahTiket = (int) spinnerJumlah.getValue();
        int totalHarga = jumlahTiket * hargaTiket;

        if (filmDipilih == null || jadwalDipilih == null) {
            JOptionPane.showMessageDialog(this, "Film dan jadwal harus dipilih!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String pesanan = "Film: " + filmDipilih + ", Jadwal: " + jadwalDipilih +
                ", Jumlah: " + jumlahTiket + ", Harga: Rp " + totalHarga;
        daftarPesanan.add(pesanan);
        JOptionPane.showMessageDialog(this, "Pesanan berhasil ditambahkan:\n" + pesanan);
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

    private void tambahPoster(ActionEvent e) {
        int indexFilm = comboFilm.getSelectedIndex();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "jpeg"));
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            posterPaths[indexFilm] = filePath;
            updatePoster(null);
            JOptionPane.showMessageDialog(this, "Poster berhasil ditambahkan untuk " + daftarFilm[indexFilm]);
        }
    }

    private void updatePoster(ActionEvent e) {
        int indexFilm = comboFilm.getSelectedIndex();

        if (posterPaths[indexFilm] != null) {
            ImageIcon poster = new ImageIcon(new ImageIcon(posterPaths[indexFilm])
                    .getImage().getScaledInstance(180, 200, Image.SCALE_SMOOTH));
            lblPoster.setIcon(poster);
            lblPoster.setText("");
        } else {
            lblPoster.setIcon(null);
            lblPoster.setText("Poster Film");
        }
    }

    public static void main(String[] args) {
        new PemesananTiketBioskopWithPoster();
    }
}
